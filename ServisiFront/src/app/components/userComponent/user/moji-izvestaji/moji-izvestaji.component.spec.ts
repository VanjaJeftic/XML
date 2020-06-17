import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MojiIzvestajiComponent } from './moji-izvestaji.component';

describe('MojiIzvestajiComponent', () => {
  let component: MojiIzvestajiComponent;
  let fixture: ComponentFixture<MojiIzvestajiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MojiIzvestajiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MojiIzvestajiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
