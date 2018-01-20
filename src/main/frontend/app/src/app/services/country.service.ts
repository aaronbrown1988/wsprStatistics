/**
 * Created by aaron on 27/05/17.
 */
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import {SearchParamService} from "./searchParam.service";
import {Logger} from "./logger.service";
import {Subscription} from "rxjs";
import {SearchParams} from "../models/search-params";

// todo This service should really cache these results.
@Injectable()
export class CountryService {
  private countryUrl = 'api/country';  // URL to web API

  subscription: Subscription;


  constructor(private http: Http, private callsignService: SearchParamService, private logger: Logger) {
    this.subscription = this.callsignService.update$.subscribe(
      update => {
        this.getCountries(update)
      });
    logger.log(this.subscription)

  }


  public getCountries(update: SearchParams): Observable<any[]> {
    this.logger.log("country service has " + update.callsign);
    return this.http.get(this.countryUrl + '/' + update.callsign + '/' + update.band)
      .map(this.extractData);

  }

  public getTimeData(tx: string, rx: string): Observable<any[]> {
    this.logger.log("tx: " + tx + " rx: " + rx);
    return this.http.get(this.countryUrl + '/time/' + tx + '/' + rx).map(this.extractData)
  }

  public getBandData(tx: string, rx: string): Observable<any[]> {
    this.logger.log("tx: " + tx + " rx: " + rx);
    return this.http.get(this.countryUrl + '/band/' + tx + '/' + rx).map(this.extractData)
  }

  public getCountryList(): Observable<any[]> {
    return this.http.get(this.countryUrl + '/list').map(this.extractData);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || body || {};
  }


}
