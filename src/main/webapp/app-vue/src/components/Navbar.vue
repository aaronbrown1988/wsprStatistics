<template>
  <b-nav fill id="navbar">

    <b-link class="navbar-brand" to="#"><span>WSPR Statistics</span>
    </b-link>
    <span class="col-md-2"></span>
    <search/>

    <b-nav-item>Home</b-nav-item>
    <b-nav-item>About</b-nav-item>
    <b-nav-item>Help</b-nav-item>
  </b-nav>
</template>

<script>
import Search from './Search.vue'

export default {
  name: 'navbar',
  components: {Search}
}
</script>

<style>
#navbar {
  background: #2196f3;
  color: #ffffff;
  padding-left: 10px;
  margin-bottom: 10px;

}

li {
  color: #ffffff;
}

a {
  color: #ffffff !important;
}
</style>
