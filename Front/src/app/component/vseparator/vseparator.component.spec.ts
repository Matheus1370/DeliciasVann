import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VSeparatorComponent } from './vseparator.component';

describe('VSeparatorComponent', () => {
  let component: VSeparatorComponent;
  let fixture: ComponentFixture<VSeparatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VSeparatorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VSeparatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
