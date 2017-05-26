import {Pipe, PipeTransform} from "@angular/core";
/**
 * Created by aaron on 26/05/17.
 */
@Pipe({name: 'mapStatisticsToIterable'})
export class mapStatisticsToIterable implements PipeTransform {
  transform(value, args: string[]): any {
    let keys = [];
    for (let key in value) {
      value[key].band = key;
      keys.push(value[key]);
    }
    return keys;
  }
}
