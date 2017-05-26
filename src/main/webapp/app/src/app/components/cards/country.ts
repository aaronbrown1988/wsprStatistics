import {Component} from "@angular/core";
import {CallsignService} from "../../services/callsign.service";
import {CountryService} from "../../services/country.service";

@Component({
    selector: 'country',
    template: `
<p-panel header="Country">
<table class="table table-striped table-hover">
<thead>
  <th>Country</th>
<th>Count</th>
</thead>
<tbody>
  <tr *ngFor="let row of data | mapStatisticsToIterable">
  <td>{{row.key}}</td>
<td>{{row.value}}</td>
</tr>
</tbody>
</table>
</p-panel>
      `
})
export class Country {
  data: {};

  constructor(private callsignService: CallsignService, private countryService: CountryService) {
    callsignService.update$.subscribe(callsign => {
      this.update(callsign)
    })
  }

  update(callsign: string) {
    this.countryService.getCountries(callsign).subscribe(data => {
      this.data = data
    })
  }

}
