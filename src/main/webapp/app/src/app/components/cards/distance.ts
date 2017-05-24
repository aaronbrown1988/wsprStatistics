import {Component} from "@angular/core";
import {DistanceData} from "../../models/distanceData";
import {DistanceService} from "../../services/distance.service";

@Component({
  selector: 'distance',
  template: `
<div class="card">
<h4 class="card-title">Distance</h4>
<table class="table table-striped table-hover">

  <thead>
    <th>Band (MHz)</th>
  <th> Avg.</th>
<th>Min. </th>
<th>Max. </th>
</thead>
<tbody>
  <tr *ngFor="let row of data">
    <td>{{row.band}}</td>
<td>{{row.average}}</td>
<td>{{row.min}}</td>
<td>{{row.max}}</td>
</tr>
</tbody>
</table>
</div>
`

})
export class Distance {
  data: DistanceData[]

  constructor(private distanceService: DistanceService) {
  }

  ngOnInit() {
    this.distanceService.getDistance().subscribe(
      data => this.data = data
    )
  }

}
