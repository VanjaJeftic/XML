import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MojIzvestajDialogComponent } from './moj-izvestaj-dialog.component';

describe('MojIzvestajDialogComponent', () => {
  let component: MojIzvestajDialogComponent;
  let fixture: ComponentFixture<MojIzvestajDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MojIzvestajDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MojIzvestajDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
