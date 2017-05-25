import {Component} from "@angular/core";

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
  <tr ng-repeat="(key,value) in countryCtrl.data">
  <td>{{key}}</td>
<td>{{value}}</td>
</tr>
</tbody>
</table>
</p-panel>
      `
})
export class Country {

}
