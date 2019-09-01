import { TestBed } from '@angular/core/testing';

import { WaterfallService } from './waterfall.service';

describe('WaterfallService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WaterfallService = TestBed.get(WaterfallService);
    expect(service).toBeTruthy();
  });

  it('should be initialisable', () => {
    const service: WaterfallService = TestBed.get(WaterfallService)
    expect(service.initAudioRecorder()).not.toBeNull();
    expect(service['stream']).not.toBeNull()
  });

  it('should stop listening when its done', () => {
    const service: WaterfallService = TestBed.get(WaterfallService)
    service.initAudioRecorder();
    expect(service.closeAudio()).toBeTruthy()
    
  }
  )

  it('should capture raw audio', () => {
    const service: WaterfallService = TestBed.get(WaterfallService)
    service.initAudioRecorder();
    let samples = service.getSamples()
    expect(samples).toBeTruthy();
    console.dir(samples)
  })
});
