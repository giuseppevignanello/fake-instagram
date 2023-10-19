<script setup>
import { ref } from 'vue';
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const search = ref(false);
const searchTerm = ref('');
const searchResults = ref(null);


function searchPhoto() {
    axios.get(`${apiUrl}/filter/${searchTerm.value}`)
        .then((response) => {
            search.value = true;
            searchResults.value = response.data;
        })
}
</script>

<template>
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6">
                <form @submit.prevent="searchPhoto">
                    <label for="search">Search a Photo</label>
                    <input class="form-control" type="text" v-model="searchTerm" id="search"
                        aria-label="default input example">
                    <button type="submit" class="btn btn-primary mt-2">Search</button>
                </form>
            </div>
        </div>
    </div>
</template>


<style lang="scss" scoped></style>