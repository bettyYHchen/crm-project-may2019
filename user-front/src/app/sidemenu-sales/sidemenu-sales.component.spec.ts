import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidemenuSalesComponent } from './sidemenu-sales.component';

describe('SidemenuSalesComponent', () => {
  let component: SidemenuSalesComponent;
  let fixture: ComponentFixture<SidemenuSalesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidemenuSalesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidemenuSalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
