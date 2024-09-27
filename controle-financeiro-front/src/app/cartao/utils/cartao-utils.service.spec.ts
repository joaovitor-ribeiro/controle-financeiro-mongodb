import { TestBed } from '@angular/core/testing';

import { CartaoUtilsService } from './cartao-utils.service';

describe('CartaoUtilsService', () => {
  let service: CartaoUtilsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CartaoUtilsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
