import {Injectable} from "@angular/core";

@Injectable()
export class DashboardService {

  state: string;

  constructor() {
    this.state = "callsign"
  }

}