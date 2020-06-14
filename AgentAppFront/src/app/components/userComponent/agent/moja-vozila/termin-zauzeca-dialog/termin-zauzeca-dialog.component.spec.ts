import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TerminZauzecaDialogComponent } from './termin-zauzeca-dialog.component';

describe('TerminZauzecaDialogComponent', () => {
  let component: TerminZauzecaDialogComponent;
  let fixture: ComponentFixture<TerminZauzecaDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TerminZauzecaDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TerminZauzecaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
