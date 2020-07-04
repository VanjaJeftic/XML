import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CenovniciComponent } from './cenovnici.component';

describe('CenovniciComponent', () => {
  let component: CenovniciComponent;
  let fixture: ComponentFixture<CenovniciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CenovniciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CenovniciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
