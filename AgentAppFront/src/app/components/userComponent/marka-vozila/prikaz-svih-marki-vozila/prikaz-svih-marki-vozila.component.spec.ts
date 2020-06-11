import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazSvihMarkiVozilaComponent } from './prikaz-svih-marki-vozila.component';

describe('PrikazSvihMarkiVozilaComponent', () => {
  let component: PrikazSvihMarkiVozilaComponent;
  let fixture: ComponentFixture<PrikazSvihMarkiVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazSvihMarkiVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazSvihMarkiVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
