import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAlumnusComponent } from './get-alumnus.component';

describe('GetAlumnusComponent', () => {
  let component: GetAlumnusComponent;
  let fixture: ComponentFixture<GetAlumnusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAlumnusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAlumnusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
