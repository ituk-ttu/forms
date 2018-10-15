<template>
      <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <first-form></first-form>
            <admin-view></admin-view>
      </div>
</template>

<script>
      import FirstForm from './components/FirstForm'
      import AdminView from './components/Admin'

      export default {
          name: 'app',
          components: {
              FirstForm,
              AdminView
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
