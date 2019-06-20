import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcademicActionCardComponent } from './academic-action-card.component';

describe('AcademicActionCardComponent', () => {
  let component: AcademicActionCardComponent;
  let fixture: ComponentFixture<AcademicActionCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcademicActionCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcademicActionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
