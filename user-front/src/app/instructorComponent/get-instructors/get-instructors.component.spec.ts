import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetInstructorsComponent } from './get-instructors.component';

describe('GetInstructorsComponent', () => {
  let component: GetInstructorsComponent;
  let fixture: ComponentFixture<GetInstructorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetInstructorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetInstructorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
