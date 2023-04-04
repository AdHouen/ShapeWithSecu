import { TestBed } from '@angular/core/testing';

import { MuscleService } from './muscle.service';

describe('MuscleService', () => {
  let service: MuscleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MuscleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
