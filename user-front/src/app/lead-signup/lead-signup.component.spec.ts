import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeadSignupComponent } from './lead-signup.component';

describe('LeadSignupComponent', () => {
  let component: LeadSignupComponent;
  let fixture: ComponentFixture<LeadSignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeadSignupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeadSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
