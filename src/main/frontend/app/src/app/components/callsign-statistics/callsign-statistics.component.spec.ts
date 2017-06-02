import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {CallsignStatisticsComponent} from "./callsign-statistics.component";

describe('CallsignStatisticsComponent', () => {
  let component: CallsignStatisticsComponent;
  let fixture: ComponentFixture<CallsignStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CallsignStatisticsComponent]
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
