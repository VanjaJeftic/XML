import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazSvihVrstaMenjacaComponent } from './prikaz-svih-vrsta-menjaca.component';

describe('PrikazSvihVrstaMenjacaComponent', () => {
  let component: PrikazSvihVrstaMenjacaComponent;
  let fixture: ComponentFixture<PrikazSvihVrstaMenjacaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazSvihVrstaMenjacaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazSvihVrstaMenjacaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
