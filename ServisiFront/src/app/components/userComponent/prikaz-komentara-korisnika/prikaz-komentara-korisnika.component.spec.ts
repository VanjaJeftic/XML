import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazKomentaraKorisnikaComponent } from './prikaz-komentara-korisnika.component';

describe('PrikazKomentaraKorisnikaComponent', () => {
  let component: PrikazKomentaraKorisnikaComponent;
  let fixture: ComponentFixture<PrikazKomentaraKorisnikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazKomentaraKorisnikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazKomentaraKorisnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
