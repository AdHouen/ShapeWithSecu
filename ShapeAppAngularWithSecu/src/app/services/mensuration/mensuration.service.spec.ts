import { TestBed } from '@angular/core/testing';

import { MensurationService } from './mensuration.service';

describe('MensurationService', () => {
  let service: MensurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MensurationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
