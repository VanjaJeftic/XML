import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelVozilaComponent } from './model-vozila.component';

describe('ModelVozilaComponent', () => {
  let component: ModelVozilaComponent;
  let fixture: ComponentFixture<ModelVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
