import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KomentarFormaComponent } from './komentar-forma.component';

describe('KomentarFormaComponent', () => {
  let component: KomentarFormaComponent;
  let fixture: ComponentFixture<KomentarFormaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KomentarFormaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KomentarFormaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
