import {Component, OnInit} from "@angular/core";
import {DashboardService} from "../../services/dashboard.service";
import {MenuItem} from "primeng/primeng";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  private items: MenuItem[];

  constructor(private dashboard: DashboardService) {
  }

  ngOnInit() {
    this.items = [
      {label: 'Callsign', icon: 'fa-wifi', command: (event) => this.state('callsign')},
      {label: 'Country', icon: 'fa-street-view', command: (event) => this.state('country')},
      {label: 'Global', icon: 'fa-globe'}
    ]
  }

  state = function (destination: string) {
    this.dashboard.state = destination;
  }

}
