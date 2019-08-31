import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {BandSelectComponent} from "./band-select.component";
import { NO_ERRORS_SCHEMA } from "@angular/core";
import { SearchParamService } from "app/services/searchParam.service";

describe('BandSelectComponent', () => {
  let component: BandSelectComponent;
  let fixture: ComponentFixture<BandSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BandSelectComponent],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [{provide: SearchParamService, useValue: searchParamServiceStub }]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

let searchParamServiceStub: Partial<SearchParamService>;
