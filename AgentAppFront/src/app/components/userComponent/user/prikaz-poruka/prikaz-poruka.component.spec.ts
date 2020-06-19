import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazPorukaComponent } from './prikaz-poruka.component';

describe('PrikazPorukaComponent', () => {
  let component: PrikazPorukaComponent;
  let fixture: ComponentFixture<PrikazPorukaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazPorukaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazPorukaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
