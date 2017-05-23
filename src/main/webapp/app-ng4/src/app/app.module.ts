import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {Country} from "./components/cards/country";
import {Distance} from "./components/cards/distance";
import {Logger} from "./services/logger.service";
import {DistanceService} from "./services/distance.service";

@NgModule({
    declarations: [
        AppComponent,
        Country,
        Distance
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,

    ],
  providers: [Logger,
    DistanceService
  ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
