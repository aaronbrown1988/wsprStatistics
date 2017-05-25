/**
 * Created by aaron on 24/05/17.
 */
export class CallsignService {
  private callsign: string

  setCallsign(callsign: string) {
    this.callsign = callsign;
  }

  getCallsign() {
    return this.callsign
  }
}
