import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaMarkeVozilaComponent } from './izmena-marke-vozila.component';

describe('IzmenaMarkeVozilaComponent', () => {
  let component: IzmenaMarkeVozilaComponent;
  let fixture: ComponentFixture<IzmenaMarkeVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzmenaMarkeVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzmenaMarkeVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
