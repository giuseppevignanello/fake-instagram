
<script setup>
import { ref } from 'vue';
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const search = ref(false);
const searchTerm = ref('');
function searchPhoto() {
    axios.get(`${apiUrl}/filter/${searchTerm.value}`)
        .then((response) => {
            search.value = true;
            photos.value = response.data;
        })
}
</script>

<template>
    <div class="d-flex py-2 justify-content-between align-items-center border-bottom">
        <div class="logo ms-4">
            <img src="logo.png" alt="">
        </div>
        <div>
            <div class="mb-3">
                <form @submit.prevent="searchPhoto">
                    <div class="d-flex align-items-center gap-2">
                        <input class="form-control" type="text" v-model="searchTerm" id="search"
                            aria-label="default input example">
                        <button type="submit" class="btn bg_gradient text-white">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>


<style lang="scss" scoped></style>