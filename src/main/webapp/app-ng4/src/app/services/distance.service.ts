import {DistanceData} from "../models/distanceData";
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";


@Injectable()
export class DistanceService {
  private distanceUrl = 'api/distance';  // URL to web API
  constructor(private http: Http) {
  }

  public getDistance(): Observable<DistanceData[]> {
    return this.http.get(this.distanceUrl)
      .map(this.extractData);

  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || {};
  }

}
