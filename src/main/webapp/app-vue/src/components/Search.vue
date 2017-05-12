<template>

  <b-form-input id="callsign" size="8" type="text" placeholder="Enter your callsign" v-model="callsign">
  </b-form-input>
</template>

<script>
export default {
  name: 'search',
  methods: {
    search: function () {
      alert(this.callsign)
    }
  }
}
</script>
