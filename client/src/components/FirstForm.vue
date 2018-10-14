<template>
    <form id="app">
        <p>
            <input type="text" name="name" v-model="name">
            Nimi
        </p>
        <p>
            <input type="text" name="email" v-model="email">
            Email
        </p>
        <p>
            <input type="checkbox" name="checked_d1" v-model="checked_d1">
            Day 1
        </p>
        <p>
            <input type="checkbox" name="checked_d2" v-model="checked_d2">
            Day 2
        </p>
        <div v-if="checked_d1">
            <p>
                <input type="radio" name="package_day1" value="1" v-model="package_day1">
                Pakett 11 - Lorem Ipsum
            </p>
            <p>
                <input type="radio" name="package_day1" value="2" v-model="package_day1">
                Pakett 12 - Lorem Ipsum
            </p>
            <p>
                <input type="radio" name="package_day1" value="3" v-model="package_day1">
                Pakett 13 - Lorem Ipsum
            </p>
        </div>
        <div v-if="checked_d2">
            <p>
                <input type="radio" name="package_day2" value="1" v-model="package_day2">
                Pakett 21 - Lorem Ipsum
            </p>
            <p>
                <input type="radio" name="package_day2" value="2" v-model="package_day2">
                Pakett 22 - Lorem Ipsum
            </p>
            <p>
                <input type="radio" name="package_day2" value="3" v-model="package_day2">
                Pakett 23 - Lorem Ipsum
            </p>
        </div>
        <button type="button" v-on:click="test">Submit</button>
        <p><pre>data: {{ $data }}</pre></p>
    </form>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'FirstForm',
        data: function () {
            return {
                name: '',
                email: '',
                checked_d1: false,
                checked_d2: false,
                package_day1: 'none',
                package_day2: 'none'
            }
        },
        methods: {
            test: function() {
                axios.post("http://localhost:8080/submit", this._data
                ).then(function (value) {
                    console.log(value);
                }).catch(function (reason) {
                    console.log(reason);
                });
            }
        }
    }
</script>