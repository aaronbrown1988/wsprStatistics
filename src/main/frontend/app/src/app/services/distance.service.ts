import {DistanceData} from "../models/distanceData";
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import {CallsignService} from "./callsign.service";
import {Logger} from "./logger.service";
import {Subscription} from "rxjs";


@Injectable()
export class DistanceService {
  private distanceUrl = 'api/stats';  // URL to web API

  subscription: Subscription;


  constructor(private http: Http, private callsignService: CallsignService, private logger: Logger) {
    this.subscription = this.callsignService.update$.subscribe(
      callsign => {
        this.getDistance(callsign)
      });
    logger.log(this.subscription)

  }


  public getDistance(call: string): Observable<DistanceData[]> {
    this.logger.log("distance service has " + call);
    return this.http.get(this.distanceUrl + '/' + call + '/distance/band/all')
      .map(this.extractData);

  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || body || {};
  }


}
