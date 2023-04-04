import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalculBmiComponent } from './calcul-bmi.component';

describe('CalculBmiComponent', () => {
  let component: CalculBmiComponent;
  let fixture: ComponentFixture<CalculBmiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalculBmiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalculBmiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
