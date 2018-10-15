<template>
    <div>
        <h3>Päev 1, kõik inimesed {{d1p1 + d1p2 + d1p3}}</h3>
        Pakett 1: {{d1p1}}<br>
        Pakett 2: {{d1p2}}<br>
        Pakett 2: {{d1p3}}<br>
        <h3>Päev 2, kõik inimesed {{d2p1 + d2p2 + d2p3}}</h3>
        Pakett 1: {{d2p1}}<br>
        Pakett 2: {{d2p2}}<br>
        Pakett 2: {{d2p3}}<br>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'AdminView',
        data: function () {
            return {
                d1p1: 0,
                d1p2: 0,
                d1p3: 0,
                d2p1: 0,
                d2p2: 0,
                d2p3: 0
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