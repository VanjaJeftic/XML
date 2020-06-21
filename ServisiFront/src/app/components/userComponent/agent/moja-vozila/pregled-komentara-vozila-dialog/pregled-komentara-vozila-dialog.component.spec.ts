import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledKomentaraVozilaDialogComponent } from './pregled-komentara-vozila-dialog.component';

describe('PregledKomentaraVozilaDialogComponent', () => {
  let component: PregledKomentaraVozilaDialogComponent;
  let fixture: ComponentFixture<PregledKomentaraVozilaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PregledKomentaraVozilaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledKomentaraVozilaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
