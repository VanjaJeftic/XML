import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaVozilaComponent } from './izmena-vozila.component';

describe('IzmenaVozilaComponent', () => {
  let component: IzmenaVozilaComponent;
  let fixture: ComponentFixture<IzmenaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
