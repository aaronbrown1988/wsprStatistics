import {Component, OnInit} from "@angular/core";
import {SelectItem} from "primeng/primeng";
import {SearchParamService} from "../../services/searchParam.service";

@Component({
  selector: 'band-select',
  templateUrl: './band-select.component.html',
  styleUrls: ['./band-select.component.css']
})
export class BandSelectComponent implements OnInit {


  bands: SelectItem[];

  selectedBand: string;

  constructor(private searchParam: SearchParamService) {
    this.bands = [];
    this.bands.push({
      label: "All Bands",
      value: "all"
    });
    this.bands.push({label: "160m", value: 1});
    this.bands.push({label: "80m", value: 3});
    this.bands.push({label: "60m", value: 5});
    this.bands.push({label: "40m", value: 7});
    this.bands.push({label: "30m", value: 10});
    this.bands.push({label: "20m", value: 14});
    this.bands.push({label: "17m", value: 18});
    this.bands.push({label: "15m", value: 21});
    this.bands.push({label: "12m", value: 24});
    this.bands.push({label: "10m", value: 28});
    this.bands.push({label: "6m", value: 50});
    this.bands.push({label: "4m", value: 70});
    this.bands.push({label: "2m", value: 144});
    this.bands.push({label: "70cm", value: 432});
    this.bands.push({label: "23cm", value: 1296});
  }

  onChange() {
    this.searchParam.setBand(this.selectedBand);
  }

  ngOnInit() {
  }


}
