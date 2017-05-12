<template>
    <form>
      <input id="callsign" v-model="callsign">
      <button v-on:click= search>Search</button>
    </form>
</template>

<script>
export default {
  name: 'search',
  data () {
    return {
      callsign: 'Enter your callsign here'
    }
  },
  methods: {
    search: function () {
      alert(this.callsign)
    }
  }
}
</script>
