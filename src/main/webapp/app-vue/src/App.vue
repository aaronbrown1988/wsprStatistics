<template>
  <div id="app" class="container-fluid">

    <navbar/>

    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
        <sidebar/>
      </div>
      <div class="col-sm-0 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <div id="view">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <footer/>
  </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import Navbar from '@/components/Navbar.vue'
import Sidebar from '@/components/Sidebar.vue'
export default {
  name: 'app',
  components: {
    Footer,
    Navbar,
    Sidebar
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  padding: 0;
}

#view {
  margin-top: 10px;
}
</style>
