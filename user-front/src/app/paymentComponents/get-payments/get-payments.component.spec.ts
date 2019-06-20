import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPaymentsComponent } from './get-payments.component';

describe('GetPaymentsComponent', () => {
  let component: GetPaymentsComponent;
  let fixture: ComponentFixture<GetPaymentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPaymentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
