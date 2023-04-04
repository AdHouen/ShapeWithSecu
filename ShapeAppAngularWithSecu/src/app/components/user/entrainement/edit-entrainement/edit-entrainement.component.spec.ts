import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEntrainementComponent } from './edit-entrainement.component';

describe('EditEntrainementComponent', () => {
  let component: EditEntrainementComponent;
  let fixture: ComponentFixture<EditEntrainementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditEntrainementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditEntrainementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
