import {Pipe, PipeTransform} from "@angular/core";
/**
 * Created by aaron on 26/05/17.
 */
@Pipe({name: 'mapToIterable'})
export class mapToIterable implements PipeTransform {
  transform(value, args: string[]): any {
    let keys = [];
    for (let key in value) {
      keys.push({
        key: key,
        value: value[key]
      });
    }
    return keys;
  }
}
