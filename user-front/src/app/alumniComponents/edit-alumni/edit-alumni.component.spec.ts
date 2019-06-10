import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAlumniComponent } from './edit-alumni.component';

describe('EditAlumniComponent', () => {
  let component: EditAlumniComponent;
  let fixture: ComponentFixture<EditAlumniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditAlumniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAlumniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
