import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {DashboardComponent} from "./dashboard.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { DashboardService } from "app/services/dashboard.service";

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let dashboardServiceStub: Partial<DashboardService>

  beforeEach(async(() => {
    dashboardServiceStub = {
      state: 'None'
    }
    TestBed.configureTestingModule({
      declarations: [DashboardComponent],
      providers: [{provides: DashboardService, useClass: dashboardServiceStub}],
      schemas: [NO_ERRORS_SCHEMA]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
