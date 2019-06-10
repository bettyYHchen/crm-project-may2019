import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMocksComponent } from './get-mocks.component';

describe('GetMocksComponent', () => {
  let component: GetMocksComponent;
  let fixture: ComponentFixture<GetMocksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetMocksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetMocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
