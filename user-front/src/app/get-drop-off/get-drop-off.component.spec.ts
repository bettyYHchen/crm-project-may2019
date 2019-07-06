import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDropOffComponent } from './get-drop-off.component';

describe('GetDropOffComponent', () => {
  let component: GetDropOffComponent;
  let fixture: ComponentFixture<GetDropOffComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetDropOffComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetDropOffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
