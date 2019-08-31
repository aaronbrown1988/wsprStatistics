import { Component, OnInit } from '@angular/core';

import {Button} from 'primeng/button'
import { WaterfallService } from 'app/services/waterfall.service';

@Component({
  selector: 'app-waterfall',
  templateUrl: './waterfall.component.html',
  styleUrls: ['./waterfall.component.css']
})
export class WaterfallComponent implements OnInit {

  constructor(waterFallService: WaterfallService) {
   
   }

  async ngOnInit() {
  }

  

}


