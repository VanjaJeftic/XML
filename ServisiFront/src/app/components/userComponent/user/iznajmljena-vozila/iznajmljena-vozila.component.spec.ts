import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IznajmljenaVozilaComponent } from './iznajmljena-vozila.component';

describe('IznajmljenaVozilaComponent', () => {
  let component: IznajmljenaVozilaComponent;
  let fixture: ComponentFixture<IznajmljenaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IznajmljenaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IznajmljenaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
