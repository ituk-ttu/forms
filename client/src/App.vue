<template>
      <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <first-form></first-form>
      </div>
</template>

<script>
import FirstForm from './components/FirstForm'

export default {
    name: 'app',
    components: {
        FirstForm
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
        margin-top: 60px;
    }
</style>
