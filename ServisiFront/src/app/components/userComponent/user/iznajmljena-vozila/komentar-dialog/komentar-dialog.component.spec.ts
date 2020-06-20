import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KomentarDialogComponent } from './komentar-dialog.component';

describe('KomentarDialogComponent', () => {
  let component: KomentarDialogComponent;
  let fixture: ComponentFixture<KomentarDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KomentarDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KomentarDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
