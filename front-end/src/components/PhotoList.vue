
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import sendMessage from "./SendMessage.vue";
const apiUrl = "http://localhost:8080/api/v1.0";
const photos = ref(null);

function like(id) {
    const heart = document.getElementById(id + "_heart");
    if (heart.classList.contains("text-danger")) {
        axios.post(`${apiUrl}/likes_decrement/${id}`, id)
            .catch((error) => {
                console.error(error)
            })
    } else {
        axios.post(`${apiUrl}/likes_increment/${id}`, id)
            .catch((error) => {
                console.error(error)
            })
    }

    heart.classList.toggle("text-danger")
}

function showCategories(id) {
    const categories = document.getElementById(id + "_category");
    categories.classList.toggle("d-none")

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
    <div class="my_container">
        <div class="stories d-flex gap-2 justify-content-between my-3 mx-2">
            <div class="story">
                <img src="https://www.wilsoncenter.org/sites/default/files/media/images/person/james-person-1.jpg" alt="">
            </div>
            <div class="story">
                <img src="https://images.healthshots.com/healthshots/en/uploads/2020/12/08182549/positive-person.jpg"
                    alt="">

            </div>
            <div class="story">
                <img src="https://www.verywellmind.com/thmb/brQcgvNiCkT4O7WhkL8YIDssDSo=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-4660327211-56b5fae93df78c0b13571d1e.jpg"
                    alt="">

            </div>
            <div class="story">
                <img src="https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F352059889%2F28138890009%2F1%2Foriginal.20220911-152029?w=600&auto=format%2Ccompress&q=75&sharp=10&rect=0%2C77%2C680%2C340&s=30a1d3171906fdc8e8c567a434b03542"
                    alt="">
            </div>
            <div class="story">
                <img src="https://contenthub-static.grammarly.com/blog/wp-content/uploads/2016/11/Point-of-View.jpg" alt="">
            </div>
            <div class="story">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1O8H1chF14jAFC7flzk3XOakXSmGUC6QEhg&usqp=CAU"
                    alt="">
            </div>
            <div class="story">
                <img src="https://health.clevelandclinic.org/wp-content/uploads/sites/3/2023/01/Highly-Sensitive-Person-1360038205-770x533-1.jpg"
                    alt="">
            </div>
        </div>
    </div>

    <div class="my_container photo_container mt-3">
        <ul class="list-unstyled row row-cols-12 align-items-center">
            <li v-for="photo in photos">
                <div v-if="photo.visible" class="d-flex flex-column justify-content-center my-2">
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
                            <img @dblclick="like(photo.id)" :src="photo.url" :alt="photo.tilte">
                        </div>
                    </div>
                    <div class="icons mt-2 d-flex gap-2">
                        <div @click="like(photo.id)">
                            <i :id="photo.id + '_heart'" class="fa-solid fa-heart fa-xl"></i>
                        </div>
                        <div data-bs-toggle="modal" data-bs-target="#sendMessageModal">
                            <i class="fa-solid fa-message fa-xl"></i>
                        </div>
                        <div data-bs-toggle="modal" data-bs-target="#sendMessageModal">
                            <i class="fa-solid fa-paper-plane"></i>
                        </div>
                    </div>
                    <div class="liked_by mt-2 d-flex gap-1">
                        <div class="liked_img">
                            <img src="https://picsum.photos/200/300" alt="">
                        </div>
                        <p>Liked by <strong> Vignanello </strong> and <strong> others </strong></p>
                    </div>
                    <span class="mb-1 photo_description"> <strong> description </strong> {{ photo.description }}</span>
                    <span @click="showCategories(photo.id)" id="{{ photo.id }}_click_category"
                        class="show_categories">Mostra
                        tutte le
                        categorie</span>
                    <ul :id="photo.id + '_category'" class="list-unstyled d-none">
                        <li v-for="category in photo.categories">
                            {{ category.name }}
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
    <sendMessage></sendMessage>
</template>


<style lang="scss" scoped></style>