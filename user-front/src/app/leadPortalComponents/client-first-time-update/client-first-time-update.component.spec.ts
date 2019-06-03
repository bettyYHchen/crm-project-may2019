import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientFirstTimeUpdateComponent } from './client-first-time-update.component';

describe('ClientFirstTimeUpdateComponent', () => {
  let component: ClientFirstTimeUpdateComponent;
  let fixture: ComponentFixture<ClientFirstTimeUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientFirstTimeUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientFirstTimeUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
