/**
 * Created by aaron on 27/05/17.
 */
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import {CallsignService} from "./callsign.service";
import {Logger} from "./logger.service";
import {Subscription} from "rxjs";

// todo This service should really cache these results.
@Injectable()
export class CountryService {
  private countryUrl = 'api/country';  // URL to web API

  subscription: Subscription;


  constructor(private http: Http, private callsignService: CallsignService, private logger: Logger) {
    this.subscription = this.callsignService.update$.subscribe(
      callsign => {
        this.getCountries(callsign)
      });
    logger.log(this.subscription)

  }


  public getCountries(call: string): Observable<any[]> {
    this.logger.log("country service has " + call);
    return this.http.get(this.countryUrl + '/' + call + '/all')
      .map(this.extractData);

  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || body || {};
  }


}
