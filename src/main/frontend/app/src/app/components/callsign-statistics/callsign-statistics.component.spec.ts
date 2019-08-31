import { async, ComponentFixture, TestBed } from "@angular/core/testing";
import { CallsignStatisticsComponent } from "./callsign-statistics.component";
import { Component } from "@angular/core";

describe('CallsignStatisticsComponent', () => {
  let component: CallsignStatisticsComponent;
  let fixture: ComponentFixture<CallsignStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        CallsignStatisticsComponent,
        SearchStubComponent,
        bandSelectStubComponent,
        DistanceStubComponent,
        CountryStubComponent
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CallsignStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});


@Component({ selector: 'search', template: '' })
class SearchStubComponent { }

@Component({ selector: 'country', template: '' })
class CountryStubComponent { }

@Component({ selector: 'distance', template: '' })
class DistanceStubComponent { }


@Component({ selector: 'band-select', template: '' })
class bandSelectStubComponent { }