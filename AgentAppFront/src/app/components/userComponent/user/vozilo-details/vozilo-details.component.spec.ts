import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloDetailsComponent } from './vozilo-details.component';

describe('VoziloDetailsComponent', () => {
  let component: VoziloDetailsComponent;
  let fixture: ComponentFixture<VoziloDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoziloDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoziloDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
