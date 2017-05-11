<template>
    <form>
        <label for="callsign">Callsign:</label>
        <input id="callsign">
        <button>Search</button>
    </form>
</template>

<script>
export default {
  name: 'search',
  data () {
    return {
      callsign: 'Enter your callsign here'
    }
  }
}
</script>
