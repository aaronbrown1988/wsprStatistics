<template>
  <b-card header="Country">
    <table class="table table-striped table-hover">
      <thead>
      <th>Country</th>
      <th>Count</th>
      </thead>
      <tbody>
      <tr ng-repeat="(key,value) in countryCtrl.data">
        <td>{{key}}</td>
        <td>{{value}}</td>
      </tr>
      </tbody>
    </table>
  </b-card>

</template>

<script>
export default {
  name: 'country'
}

</script>
