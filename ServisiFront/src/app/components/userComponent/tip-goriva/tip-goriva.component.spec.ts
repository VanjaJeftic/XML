import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipGorivaComponent } from './tip-goriva.component';

describe('TipGorivaComponent', () => {
  let component: TipGorivaComponent;
  let fixture: ComponentFixture<TipGorivaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipGorivaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipGorivaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
