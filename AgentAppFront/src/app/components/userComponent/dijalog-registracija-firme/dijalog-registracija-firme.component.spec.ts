import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DijalogRegistracijaFirmeComponent } from './dijalog-registracija-firme.component';

describe('DijalogRegistracijaFirmeComponent', () => {
  let component: DijalogRegistracijaFirmeComponent;
  let fixture: ComponentFixture<DijalogRegistracijaFirmeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DijalogRegistracijaFirmeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DijalogRegistracijaFirmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
