import { TestBed } from '@angular/core/testing';

import { EntrainementService } from './entrainement.service';

describe('EntrainementService', () => {
  let service: EntrainementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntrainementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
