import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NousChoisirComponent } from './nous-choisir.component';

describe('NousChoisirComponent', () => {
  let component: NousChoisirComponent;
  let fixture: ComponentFixture<NousChoisirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NousChoisirComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NousChoisirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
