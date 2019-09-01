import { Injectable } from '@angular/core';
import { RecordRTCPromisesHandler } from 'recordrtc'
import {fft} from 'fft-js'

@Injectable({
  providedIn: 'root'
})
export class WaterfallService {

  constructor() { }



  initAudioRecorder = async function () {
    this.stream = await navigator.mediaDevices.getUserMedia({ video: false, audio: true });
    this.recorder = new RecordRTCPromisesHandler(this.stream, {
      type: 'audio'
    });
    return this.stream
  }

  closeAudio = async function() {
    this.recorder.destory();
    this.stream.stop();
  }

  getSamples = async function () {
    this.recorder.startRecording();
    const sleep = m => new Promise(r => setTimeout(r, m));
    await sleep(3000);

    await this.recorder.stopRecording();
    let blob = await this.recorder.getBlob();
    console.log(`Done collecting audio samples`)
    return blob
  }

  decode = async function() {
    let samples = this.getSamples();
    let phasors = fft(samples)
    console.log(`Calculated the following FFT ${phasors}`)
    
  }


}
