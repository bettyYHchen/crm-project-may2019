import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidemenuAdminComponent } from './sidemenu-admin.component';

describe('SidemenuAdminComponent', () => {
  let component: SidemenuAdminComponent;
  let fixture: ComponentFixture<SidemenuAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidemenuAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidemenuAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
