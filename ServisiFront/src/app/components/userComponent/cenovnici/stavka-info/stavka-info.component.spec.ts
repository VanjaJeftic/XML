import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StavkaInfoComponent } from './stavka-info.component';

describe('StavkaInfoComponent', () => {
  let component: StavkaInfoComponent;
  let fixture: ComponentFixture<StavkaInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StavkaInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StavkaInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
