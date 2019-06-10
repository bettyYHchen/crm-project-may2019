import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetClientProfileComponent } from './get-client-profile.component';

describe('GetClientProfileComponent', () => {
  let component: GetClientProfileComponent;
  let fixture: ComponentFixture<GetClientProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetClientProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetClientProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
