import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {Country} from "./components/cards/country";
import {Distance} from "./components/cards/distance";
import {Logger} from "./services/logger.service";
import {DistanceService} from "./services/distance.service";
import {CallsignService} from "./services/callsign.service";
import {Search} from "./components/search";
import {PanelModule} from "primeng/primeng";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
    declarations: [
        AppComponent,
        Country,
      Distance,
      Search
    ],
    imports: [
        BrowserModule,
      BrowserAnimationsModule,
      FormsModule,
        HttpModule,
      PanelModule

    ],
  providers: [Logger,
    DistanceService,
    CallsignService
  ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
