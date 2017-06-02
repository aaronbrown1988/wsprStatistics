import {Component} from "@angular/core";
import {DistanceData} from "../../../models/distanceData";
import {DistanceService} from "../../../services/distance.service";
import {SearchParamService} from "../../../services/searchParam.service";
import {Logger} from "../../../services/logger.service";
import {SearchParams} from "../../../models/search-params";

// todo Convert this to a data table from primefaces
@Component({
  selector: 'distance',
  template: `

<p-panel header="Distance">

<table class="table table-striped table-hover">

  <thead>
    <th>Band (MHz)</th>
  <th> Avg.</th>
<th>Min. </th>
<th>Max. </th>
</thead>
<tbody>
  <tr *ngFor="let row of data | mapStatisticsToIterable">
    <td>{{row.band }}</td>
<td>{{row.average|number:3.0-3}}</td>
<td>{{row.min|number:3.0-3}}</td>
<td>{{row.max|number:3.0-3}}</td>
</tr>
</tbody>
</table>
</p-panel>
`

})
export class Distance {
  data: DistanceData[];

  constructor(private callsignService: SearchParamService, private distanceService: DistanceService, private logger: Logger) {
    callsignService.update$.subscribe(update => {
      this.logger.log("Component subscribed and got " + update.callsign);
      this.updateData(update)
    })
  }

  updateData(update: SearchParams) {
    this.distanceService.getDistance(update).subscribe(data => {
      this.data = data
    })
  }


}


