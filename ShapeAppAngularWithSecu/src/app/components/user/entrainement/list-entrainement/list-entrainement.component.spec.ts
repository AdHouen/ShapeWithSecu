import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListEntrainementComponent } from './list-entrainement.component';

describe('ListEntrainementComponent', () => {
  let component: ListEntrainementComponent;
  let fixture: ComponentFixture<ListEntrainementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListEntrainementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListEntrainementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
