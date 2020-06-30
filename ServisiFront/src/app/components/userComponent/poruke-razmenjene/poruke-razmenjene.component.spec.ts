import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PorukeRazmenjeneComponent } from './poruke-razmenjene.component';

describe('PorukeRazmenjeneComponent', () => {
  let component: PorukeRazmenjeneComponent;
  let fixture: ComponentFixture<PorukeRazmenjeneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PorukeRazmenjeneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PorukeRazmenjeneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
