<template>
  <div id="sidebar" class="sidebar">
    <b-nav pills fill vertical class="nav-sidebar">
      <b-nav-item>Distance</b-nav-item>
      <b-nav-item>Country</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
export default {
  name: 'sidebar'
}
</script>

<style>
#sidebar {
  background-color: #3f51b5;
  color: #ffffff;
}

a {
  color: #fff;
}
</style>
