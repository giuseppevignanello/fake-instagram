
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import sendMessage from "./SendMessage.vue";
const apiUrl = "http://localhost:8080/api/v1.0";
const photos = ref(null);
const showCategories = ref(false);
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
function getAll() {
    axios.get(apiUrl)
        .then((response) => {
            photos.value = response.data;
        })
        .catch((error) => {
            console.error(error.message);
        })
}

onMounted(() => {
    getAll();
})

</script>

<template>
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6">
                <form @submit.prevent="searchPhoto">
                    <label class="form-label" for="search">Search a Photo</label>
                    <input class="form-control" type="text" v-model="searchTerm" id="search"
                        aria-label="default input example">
                    <button type="submit" class="btn btn-primary mt-2">Search</button>
                </form>
            </div>
        </div>
    </div>
    <div class="container  mt-3">
        <div v-if="search">
            <ul v-if="searchResults.length > 0" class="list-unstyled">
                <li v-for="photo in searchResults">
                    <div v-if="photo.visible" class="d-flex flex-column">
                        <div>
                            <h6>{{ photo.title }}</h6>
                        </div>
                        <div class="d-flex justify-content-center ">
                            <div class="photo_image">
                                <img :src="photo.url" :alt="photo.tilte">
                            </div>
                        </div>

                        <p>{{ photo.description }}</p>
                        <h6>Categories</h6>
                        <ul>
                            <li v-for="category in photo.categories">
                                {{ category.name }}
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
            <div v-else>
                Nessun risultato
            </div>
        </div>
        <div class="container mt-3">
            <ul v-if="!search" class="list-unstyled row row-cols-12 align-items-center">
                <li v-for="photo in photos">
                    <div v-if="photo.visible" class="d-flex flex-column justify-content-center">
                        <div class="photo_header d-flex align-items-center gap-2">
                            <div class="photo_header_img">
                                <img src="https://picsum.photos/200/300" alt="">
                            </div>
                            <div class="d-flex gap-2 align-items-center">
                                <h6 class="photo_title">{{ photo.title }}</h6>
                                <p class="title_days mt-2"> - 2 g</p>
                            </div>
                        </div>

                        <div class="mt-3">
                            <div class="photo_image">
                                <img :src="photo.url" :alt="photo.tilte">
                            </div>
                        </div>
                        <div class="icons mt-2 d-flex gap-2">
                            <div>
                                <i class="fa-regular fa-heart fa-xl"></i>
                            </div>
                            <div data-bs-toggle="modal" data-bs-target="#sendMessageModal">
                                <i class="fa-regular fa-message fa-xl"></i>
                            </div>
                            <div data-bs-toggle="modal" data-bs-target="#sendMessageModal">
                                <i class="fa-regular fa-paper-plane"></i>
                            </div>
                        </div>
                        <div class="liked_by mt-2 d-flex gap-1">
                            <div class="liked_img">
                                <img src="https://picsum.photos/200/300" alt="">
                            </div>
                            <p>Liked by <strong> Vignanello </strong> and <strong> others </strong></p>
                        </div>
                        <span class="mb-1 photo_description"> <strong> description </strong> {{ photo.description }}</span>
                        <span @click="showCategories = !showCategories" class="show_categories">Mostra tutte le
                            categorie</span>
                        <ul class="list-unstyled" :class="{ 'd-none': !showCategories }">
                            <li v-for="category in photo.categories">
                                {{ category.name }}
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <sendMessage></sendMessage>
    </div>
</template>


<style lang="scss" scoped></style>