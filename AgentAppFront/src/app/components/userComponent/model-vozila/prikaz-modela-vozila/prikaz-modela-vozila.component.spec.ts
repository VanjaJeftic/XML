import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazModelaVozilaComponent } from './prikaz-modela-vozila.component';

describe('PrikazModelaVozilaComponent', () => {
  let component: PrikazModelaVozilaComponent;
  let fixture: ComponentFixture<PrikazModelaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazModelaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazModelaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
