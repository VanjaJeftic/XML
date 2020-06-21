import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetLozinkeComponent } from './reset-lozinke.component';

describe('ResetLozinkeComponent', () => {
  let component: ResetLozinkeComponent;
  let fixture: ComponentFixture<ResetLozinkeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResetLozinkeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetLozinkeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
