
import Search from '@/components/Search'
<template>
<div>
    <h2>Search for statistics for a given Callsign</h2>

    <div id="search"></div>
    <search></search>

    <h3> Average Distance by band</h3>
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

</div>
</template>

<script>
import Search from './search.vue'
export default {
  name: 'distance',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  components: {Search}
}
</script>

