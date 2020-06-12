import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SvaVozilaComponent } from './sva-vozila.component';

describe('SvaVozilaComponent', () => {
  let component: SvaVozilaComponent;
  let fixture: ComponentFixture<SvaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SvaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SvaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
