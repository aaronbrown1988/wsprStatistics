import { Component, OnInit } from '@angular/core';
import { RecordRTCPromisesHandler} from 'recordrtc'

@Component({
  selector: 'app-waterfall',
  templateUrl: './waterfall.component.html',
  styleUrls: ['./waterfall.component.css']
})
export class WaterfallComponent implements OnInit {

  constructor() {
   
   }

  async ngOnInit() {
    await initAudioRecorder();
  }

  

}
async function initAudioRecorder() {
  let stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
  let recorder = new RecordRTCPromisesHandler(stream, {
    type: 'video'
  });
}

