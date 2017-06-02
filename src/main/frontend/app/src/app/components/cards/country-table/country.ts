import {Component} from "@angular/core";
import {SearchParamService} from "../../../services/searchParam.service";
import {CountryService} from "../../../services/country.service";
import {SearchParams} from "../../../models/search-params";

// todo Convert this to a data table from primefaces

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
  <tr *ngFor="let row of data | mapToIterable">
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

  constructor(private callsignService: SearchParamService, private countryService: CountryService) {
    callsignService.update$.subscribe(update => {
      this.updateData(update)
    })
  }

  updateData(update: SearchParams) {
    this.countryService.getCountries(update).subscribe(data => {
      this.data = data
    })
  }

}
