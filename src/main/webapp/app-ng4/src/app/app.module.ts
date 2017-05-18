import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {Country} from "./cards/country";
import {Distance} from "./cards/distance";

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
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
