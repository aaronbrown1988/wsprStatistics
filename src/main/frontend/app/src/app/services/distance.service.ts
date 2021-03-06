import {DistanceData} from "../models/distanceData";
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable, Subscription} from "rxjs";
import {SearchParamService} from "./searchParam.service";
import {Logger} from "./logger.service";
import {SearchParams} from "../models/search-params";
import { map } from "rxjs/operators";


@Injectable()
export class DistanceService {
  private distanceUrl = 'api/stats';  // URL to web API

  subscription: Subscription;


  constructor(private http: Http, private searchParamService: SearchParamService, private logger: Logger) {
    this.subscription = this.searchParamService.update$.subscribe(
      callsign => {
        this.getDistance(callsign)
      });
    logger.log(this.subscription)

  }


  public getDistance(update: SearchParams): Observable<DistanceData[]> {
    this.logger.log("distance service has " + update.callsign);
    return this.http.get(this.distanceUrl + '/' + update.callsign + '/distance/band/' + update.band)
      .pipe(map(this.extractData))

  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || body || {};
  }


}
