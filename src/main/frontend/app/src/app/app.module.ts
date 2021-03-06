import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {Country} from "./components/cards/country-table/country";
import {Distance} from "./components/cards/distance-table/distance";
import {Logger} from "./services/logger.service";
import {DistanceService} from "./services/distance.service";
import {SearchParamService} from "./services/searchParam.service";
import {Search} from "./components/callsignSearch";
import {PanelModule, MenuModule, DropdownModule} from "primeng/primeng";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {mapToIterable} from "./pipes/mapToIterable.pipe";
import {mapStatisticsToIterable} from "./pipes/mapStatisticsToIterable.pipe";
import {CountryService} from "./services/country.service";
import {DashboardService} from "./services/dashboard.service";
import {CallsignStatisticsComponent} from "./components/callsign-statistics/callsign-statistics.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {CountryStatisticsComponent} from "./components/country-statistics/country-statistics.component";
import {SidebarComponent} from "./components/sidebar/sidebar.component";
import {DistanceGraphComponent} from "./components/cards/distance-graph/distance-graph.component";
import {BandSelectComponent} from "./components/band-select/band-select.component";

@NgModule({
  declarations: [
    AppComponent,
    Country,
    Distance,
    Search,
    mapToIterable,
    mapStatisticsToIterable,
    CallsignStatisticsComponent,
    DashboardComponent,
    CountryStatisticsComponent,
    SidebarComponent,
    DistanceGraphComponent,
    BandSelectComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    PanelModule,
    MenuModule,
    DropdownModule

  ],
  providers: [Logger,
    DistanceService,
    SearchParamService,
    CountryService,
    DashboardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
