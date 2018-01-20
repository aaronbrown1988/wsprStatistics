import {Component, OnInit} from "@angular/core";
import {CountryService} from "../../services/country.service";
import {SelectItem} from "primeng/primeng";
import {Logger} from "../../services/logger.service";


@Component({
  selector: 'app-country-statistics',
  templateUrl: './country-statistics.component.html',
  styleUrls: ['./country-statistics.component.css']
})
export class CountryStatisticsComponent implements OnInit {

  private logger = new Logger();

  countryList: SelectItem[];
  txLocation: string;
  rxLocation: string;

  bandData: {};
  timeData: {};

  constructor(private countryService: CountryService) {
    this.countryList = [];
  }


  onChange() {
    if (this.txLocation != null && this.rxLocation != null) {
      this.logger.log("Getting Country Data!");
      this.countryService.getBandData(this.txLocation, this.rxLocation).subscribe(data => {
        this.bandData
      });
      this.countryService.getTimeData(this.txLocation, this.rxLocation).subscribe(data => {
        this.timeData
      });
    }
  }

  ngOnInit() {
    this.countryService.getCountryList().subscribe((response) => {
      response.forEach((item) => {
        this.countryList.push({label: item, value: item})
      })
    });
  }

}
