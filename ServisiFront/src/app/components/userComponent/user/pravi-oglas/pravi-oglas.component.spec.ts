import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { PraviOglasComponent } from './pravi-oglas.component';

describe('PraviOglasComponent', () => {
  let component: PraviOglasComponent;
  let fixture: ComponentFixture<PraviOglasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PraviOglasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PraviOglasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
