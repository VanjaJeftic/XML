import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzvestajDialogComponent } from './izvestaj-dialog.component';

describe('IzvestajDialogComponent', () => {
  let component: IzvestajDialogComponent;
  let fixture: ComponentFixture<IzvestajDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzvestajDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzvestajDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
