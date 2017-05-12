
<template>

  <b-card header="Distance by Band">
    <div id="chart_div"></div>
    <table class="table table-striped table-hover">
        <thead>
        <th>Band (MHz)</th>
        <th> Avg Distance (km)</th>
        <th>Minimum Distance (km)</th>
        <th>Maximum Distance (km)</th>
        <th>Count</th>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
  </b-card>
</template>

<script>
export default {
  name: 'distance',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

