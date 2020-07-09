import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SviOglasiComponent } from './svi-oglasi.component';

describe('SviOglasiComponent', () => {
  let component: SviOglasiComponent;
  let fixture: ComponentFixture<SviOglasiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SviOglasiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SviOglasiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
