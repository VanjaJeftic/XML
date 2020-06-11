import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaModelaVozilaComponent } from './izmena-modela-vozila.component';

describe('IzmenaModelaVozilaComponent', () => {
  let component: IzmenaModelaVozilaComponent;
  let fixture: ComponentFixture<IzmenaModelaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaModelaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaModelaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
