<template>
  <div class="hello">
    <h1>WSPR Statistics</h1>
    <hr></hr>
    <div class="row">
      <div class="col-md-5">
        <distance></distance>
      </div>
      <div class="col-md-5">
        <country></country>
      </div>
    </div>
  </div>
</template>

<script>
import Distance from './Distance.vue'
import Country from './Country.vue'
export default {
  name: 'hello',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  components: {
    Distance,
    Country
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
