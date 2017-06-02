import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Logger} from "./logger.service";
/**
 * Created by aaron on 24/05/17.
 */

@Injectable()
export class CallsignService {

  callsign: string;

  private logger = new Logger();

  private updateSource = new Subject<string>();

  update$ = this.updateSource.asObservable();

  setCallsign(callsign: string) {
    this.callsign = callsign;
    this.updateSource.next(callsign);
    this.logger.log(this.updateSource);
    this.logger.log("Set callsign called with " + callsign)
  }

  getCallsign() {
    return this.callsign
  }
}
