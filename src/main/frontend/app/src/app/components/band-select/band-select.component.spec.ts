import {async, ComponentFixture, TestBed} from "@angular/core/testing";
import {BandSelectComponent} from "./band-select.component";

describe('BandSelectComponent', () => {
  let component: BandSelectComponent;
  let fixture: ComponentFixture<BandSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BandSelectComponent]
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
