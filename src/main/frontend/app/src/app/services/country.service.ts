/**
 * Created by aaron on 27/05/17.
 */
import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";
import {map} from "rxjs/operators"
import {SearchParamService} from "./searchParam.service";
import {Logger} from "./logger.service";
import {SearchParams} from "../models/search-params";

// todo This service should really cache these results.
@Injectable()
export class CountryService {
  private countryUrl = 'api/country';  // URL to web API

  subscription: Subscription;


  constructor(private http: HttpClient, private callsignService: SearchParamService, private logger: Logger) {
    this.subscription = this.callsignService.update$.subscribe(
      update => {
        this.getCountries(update)
      });
    logger.log(this.subscription)

  }


  public getCountries(update: SearchParams): Observable<any[]> {
    this.logger.log("country service has " + update.callsign);
    return this.http.get(this.countryUrl + '/' + update.callsign + '/' + update.band)
    .pipe(map(this.extractData))
  }

  public getTimeData(tx: string, rx: string): Observable<any[]> {
    this.logger.log("tx: " + tx + " rx: " + rx);
    return this.http.get(this.countryUrl + '/time/' + tx + '/' + rx).pipe(map(this.extractData))
  }

  public getBandData(tx: string, rx: string): Observable<any[]> {
    this.logger.log("tx: " + tx + " rx: " + rx);
    return this.http.get(this.countryUrl + '/band/' + tx + '/' + rx).pipe(map(this.extractData))
  }

  public getCountryList(): Observable<any[]> {
    return this.http.get(this.countryUrl + '/list').pipe(map(this.extractData))
  }

  private extractData(res: any) {
    let body = res;
    return body.data || body || {};
  }


}
