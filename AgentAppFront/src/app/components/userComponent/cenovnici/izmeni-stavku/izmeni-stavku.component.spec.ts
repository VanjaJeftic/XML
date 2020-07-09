import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmeniStavkuComponent } from './izmeni-stavku.component';

describe('IzmeniStavkuComponent', () => {
  let component: IzmeniStavkuComponent;
  let fixture: ComponentFixture<IzmeniStavkuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmeniStavkuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmeniStavkuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
