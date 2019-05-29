import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidemenuAccountComponent } from './sidemenu-account.component';

describe('SidemenuAccountComponent', () => {
  let component: SidemenuAccountComponent;
  let fixture: ComponentFixture<SidemenuAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidemenuAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidemenuAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
