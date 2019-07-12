import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RedirectUploadHelperComponent } from './redirect-upload-helper.component';

describe('RedirectUploadHelperComponent', () => {
  let component: RedirectUploadHelperComponent;
  let fixture: ComponentFixture<RedirectUploadHelperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RedirectUploadHelperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RedirectUploadHelperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
