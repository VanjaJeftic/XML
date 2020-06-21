import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostaviOglasComponent } from './postavi-oglas.component';

describe('PostaviOglasComponent', () => {
  let component: PostaviOglasComponent;
  let fixture: ComponentFixture<PostaviOglasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostaviOglasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostaviOglasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
