import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VrstaMenjacaComponent } from './vrsta-menjaca.component';

describe('VrstaMenjacaComponent', () => {
  let component: VrstaMenjacaComponent;
  let fixture: ComponentFixture<VrstaMenjacaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VrstaMenjacaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VrstaMenjacaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
