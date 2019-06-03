import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhatIamComponent } from './what-iam.component';

describe('WhatIamComponent', () => {
  let component: WhatIamComponent;
  let fixture: ComponentFixture<WhatIamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhatIamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhatIamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
