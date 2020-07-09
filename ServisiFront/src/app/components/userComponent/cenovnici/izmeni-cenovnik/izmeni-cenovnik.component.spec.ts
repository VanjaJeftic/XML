import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmeniCenovnikComponent } from './izmeni-cenovnik.component';

describe('IzmeniCenovnikComponent', () => {
  let component: IzmeniCenovnikComponent;
  let fixture: ComponentFixture<IzmeniCenovnikComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmeniCenovnikComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmeniCenovnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
