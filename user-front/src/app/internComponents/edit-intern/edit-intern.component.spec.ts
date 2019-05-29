import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInternComponent } from './edit-intern.component';

describe('EditInternComponent', () => {
  let component: EditInternComponent;
  let fixture: ComponentFixture<EditInternComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditInternComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInternComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
