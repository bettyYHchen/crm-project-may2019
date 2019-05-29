import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetInternsComponent } from './get-interns.component';

describe('GetInternsComponent', () => {
  let component: GetInternsComponent;
  let fixture: ComponentFixture<GetInternsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetInternsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetInternsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
