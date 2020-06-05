import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KlasaVozilaComponent } from './klasa-vozila.component';

describe('KlasaVozilaComponent', () => {
  let component: KlasaVozilaComponent;
  let fixture: ComponentFixture<KlasaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KlasaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KlasaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
