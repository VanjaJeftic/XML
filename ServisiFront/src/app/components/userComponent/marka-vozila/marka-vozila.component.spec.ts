import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarkaVozilaComponent } from './marka-vozila.component';

describe('MarkaVozilaComponent', () => {
  let component: MarkaVozilaComponent;
  let fixture: ComponentFixture<MarkaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarkaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarkaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
