import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Logger} from "./logger.service";
import {SearchParams} from "../models/search-params";
/**
 * Created by aaron on 24/05/17.
 */

@Injectable()
export class SearchParamService {

  callsign: string;

  private logger = new Logger();

  private updateSource = new Subject<SearchParams>();

  update$ = this.updateSource.asObservable();

  band = "all";

  setCallsign(callsign: string) {
    this.callsign = callsign;
    this.updateSource.next({
      callsign: this.callsign,
      band: this.band
    });
    this.logger.log(this.updateSource);
    this.logger.log("Set callsign called with " + callsign)
  }

  setBand(band: string) {
    this.band = band;
    if (this.callsign != null) {
      this.updateSource.next({
        callsign: this.callsign,
        band: this.band
      });
    }

  }

  getCallsign() {
    return this.callsign
  }
}
