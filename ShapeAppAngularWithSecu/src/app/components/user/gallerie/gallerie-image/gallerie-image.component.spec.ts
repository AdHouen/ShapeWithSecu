import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GallerieImageComponent } from './gallerie-image.component';

describe('GallerieImageComponent', () => {
  let component: GallerieImageComponent;
  let fixture: ComponentFixture<GallerieImageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GallerieImageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GallerieImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
