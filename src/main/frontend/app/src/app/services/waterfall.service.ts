import { Injectable } from '@angular/core';
import { RecordRTCPromisesHandler} from 'recordrtc'

@Injectable({
  providedIn: 'root'
})
export class WaterfallService {

  constructor() { }



  initAudioRecorder = async function() {
    let stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
    let recorder = new RecordRTCPromisesHandler(stream, {
      type: 'audio'
    });
  }
}
