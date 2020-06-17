import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaKlaseVozilaComponent } from './izmena-klase-vozila.component';

describe('IzmenaKlaseVozilaComponent', () => {
  let component: IzmenaKlaseVozilaComponent;
  let fixture: ComponentFixture<IzmenaKlaseVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaKlaseVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaKlaseVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
