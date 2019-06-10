import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetResumesComponent } from './get-resumes.component';

describe('GetResumesComponent', () => {
  let component: GetResumesComponent;
  let fixture: ComponentFixture<GetResumesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetResumesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetResumesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
