/**
 * Created by aaron on 24/05/17.
 */
import {Component} from "@angular/core";
import {CallsignService} from "../services/callsign.service";
import {Logger} from "../services/logger.service";


@Component({
  selector: 'search',
  template: `
    <input type="text" class="form-control" id="name"
         required
         [(ngModel)]="callsign" (keyup.enter)="setCallsign()" name="callsign">
  
`
})

export class Search {
  private callsign: string;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


  constructor(private callsignService: CallsignService, private logger: Logger) {
  }

  ngOnInit() {
    this.callsign = "";
  }


  setCallsign() {
    this.callsignService.setCallsign(this.callsign)
  }
}
