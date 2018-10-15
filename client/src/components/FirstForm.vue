<template>
    <form>
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
                {{d1p1}} inimest on valinud selle paketi<br>
                <input type="radio" name="package_day1" value="1" v-model="package_day1" :disabled="d1p1 >= max">
                Pakett 11 - Lorem Ipsum
            </p>
            <p>
                {{d1p2}} inimest on valinud selle paketi<br>
                <input type="radio" name="package_day1" value="2" v-model="package_day1" :disabled="d1p2 >= max">

                Pakett 12 - Lorem Ipsum
            </p>
            <p>
                {{d1p3}} inimest on valinud selle paketi<br>
                <input type="radio" name="package_day1" value="3" v-model="package_day1" :disabled="d1p3 >= max">
                Pakett 13 - Lorem Ipsum
            </p>
        </div>
        <div v-if="checked_d2">
            <p>
                {{d2p1}} inimest on valinud selle paketi<br>
                <input type="radio" name="package_day2" value="1" v-model="package_day2" :disabled="d2p1 >= max">
                Pakett 21 - Lorem Ipsum
            </p>
            <p>
                {{d2p2}} inimest on valinud selle paketi<br>
                <input type="radio" name="package_day2" value="2" v-model="package_day2" :disabled="d2p2 >= max">
                Pakett 22 - Lorem Ipsum
            </p>
            <p>
                {{d2p3}} inimest on valinud selle paketi<br>
                <input type="radio" name="package_day2" value="3" v-model="package_day2" :disabled="d2p3 >= max">
                Pakett 23 - Lorem Ipsum
            </p>
        </div>
        <button type="button" v-on:click="submit" :disabled="!shouldSubmit">Submit</button>
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
                package_day2: 'none',
                shouldSubmit: true,
                d1p1: 0,
                d1p2: 0,
                d1p3: 0,
                d2p1: 0,
                d2p2: 0,
                d2p3: 0,
                max: 2
            }
        },
        methods: {
            submit: function() {
                axios.post("http://localhost:8080/submit", this.getFormData()
                ).then(response => {
                    if (response.data.status === "OK") {
                        this.shouldSubmit = false;
                    }
                }).catch(error =>
                    console.log("Something broke during submitting...")
                );
            },
            getFormData: function() {
                return {
                    "name":  this.name,
                    "email": this.email,
                    "checkedDay1": this.checked_d1,
                    "checkedDay2": this.checked_d2,
                    "packageDay1": this.package_day1,
                    "packageDay2": this.package_day2
                }
            }
        },
        mounted () {
            axios.get("http://localhost:8080/count"
            ).then(response => {
                if (response.data.status === "OK") {
                    this.d1p1 = response.data.data.d1p1;
                    this.d1p2 = response.data.data.d1p2;
                    this.d1p3 = response.data.data.d1p3;
                    this.d2p1 = response.data.data.d2p1;
                    this.d2p2 = response.data.data.d2p2;
                    this.d2p3 = response.data.data.d2p3;
                } else {
                    this.d1p1 = NaN;
                    this.d1p2 = NaN;
                    this.d1p3 = NaN;
                    this.d2p1 = NaN;
                    this.d2p2 = NaN;
                    this.d2p3 = NaN;
                }

            }).catch(error =>
                console.log("Something broke during mounting...")
            )
        }
    }
</script>

<style>

</style>