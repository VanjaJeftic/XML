import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaVrstaMenjacaComponent } from './izmena-vrsta-menjaca.component';

describe('IzmenaVrstaMenjacaComponent', () => {
  let component: IzmenaVrstaMenjacaComponent;
  let fixture: ComponentFixture<IzmenaVrstaMenjacaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaVrstaMenjacaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaVrstaMenjacaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
