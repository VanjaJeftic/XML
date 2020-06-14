import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MojaVozilaComponent } from './moja-vozila.component';

describe('MojaVozilaComponent', () => {
  let component: MojaVozilaComponent;
  let fixture: ComponentFixture<MojaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MojaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MojaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
