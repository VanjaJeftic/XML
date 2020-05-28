import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloDetailsDialogComponent } from './vozilo-details-dialog.component';

describe('VoziloDetailsDialogComponent', () => {
  let component: VoziloDetailsDialogComponent;
  let fixture: ComponentFixture<VoziloDetailsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoziloDetailsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoziloDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
