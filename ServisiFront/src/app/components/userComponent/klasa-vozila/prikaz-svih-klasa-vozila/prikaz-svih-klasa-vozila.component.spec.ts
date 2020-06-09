import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazSvihKlasaVozilaComponent } from './prikaz-svih-klasa-vozila.component';

describe('PrikazSvihKlasaVozilaComponent', () => {
  let component: PrikazSvihKlasaVozilaComponent;
  let fixture: ComponentFixture<PrikazSvihKlasaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazSvihKlasaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazSvihKlasaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
