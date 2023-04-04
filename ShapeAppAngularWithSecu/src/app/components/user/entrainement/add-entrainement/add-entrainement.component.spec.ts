import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEntrainementComponent } from './add-entrainement.component';

describe('AddEntrainementComponent', () => {
  let component: AddEntrainementComponent;
  let fixture: ComponentFixture<AddEntrainementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEntrainementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEntrainementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
