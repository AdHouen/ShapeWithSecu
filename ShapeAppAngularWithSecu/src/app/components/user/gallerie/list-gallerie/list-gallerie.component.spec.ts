import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListGallerieComponent } from './list-gallerie.component';

describe('ListGallerieComponent', () => {
  let component: ListGallerieComponent;
  let fixture: ComponentFixture<ListGallerieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListGallerieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListGallerieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
