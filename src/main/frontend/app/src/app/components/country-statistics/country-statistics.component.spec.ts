import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {CountryStatisticsComponent} from "./country-statistics.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { CountryService } from "app/services/country.service";

describe('CountryStatisticsComponent', () => {
  let component: CountryStatisticsComponent;
  let fixture: ComponentFixture<CountryStatisticsComponent>;
  let countryServiceStub: Partial<CountryService>;


  beforeEach(async(() => {
    countryServiceStub = {
      
    }
    TestBed.configureTestingModule({
      declarations: [CountryStatisticsComponent],
      providers: [{provides: CountryService, useValue: countryServiceStub }],
      schemas: [NO_ERRORS_SCHEMA]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CountryStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
