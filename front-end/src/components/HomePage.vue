
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import AppHeader from './AppHeader.vue';
const apiUrl = "http://localhost:8080/api/v1.0";
const photos = ref(null);
const search = ref(false);
const searchTerm = ref('');
const searchResults = ref(null);
const message = ref({
  email: '',
  content: ''
})

function sendMessage() {

  axios.post(`${apiUrl}/send`, message.value)
    .then(() => {
      message.value = ref({ ...message });
    }).catch((error) => {
      console.error(error)
    })
}

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
  <AppHeader></AppHeader>
  <div>
    <div>
      <form @submit.prevent="searchPhoto">
        <label for="search">Search a Photo</label>
        <input type="text" v-model="searchTerm">
        <button type="submit">Search</button>
      </form>
    </div>
    <div v-if="search">
      <ul v-if="searchResults.length > 0">
        <li v-for="photo in searchResults">
          <div v-if="photo.visible">
            <h2>{{ photo.title }}</h2>
            <p>{{ photo.description }}</p>
            <img :src="photo.url" :alt="photo.tilte">
            <h4>Categories</h4>
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
    <ul v-if="!search">
      <li v-for="photo in photos">
        <div v-if="photo.visible">
          <h2>{{ photo.title }}</h2>
          <p>{{ photo.description }}</p>
          <img :src="photo.url" :alt="photo.tilte">
          <h4>Categories</h4>
          <ul>
            <li v-for="category in photo.categories">
              {{ category.name }}
            </li>
          </ul>
        </div>
      </li>
    </ul>
    <div class="contact">
      <form @submit.prevent="sendMessage" id="messageForm">
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" name="email" required v-model="message.email">
        </div>
        <div>
          <label for="content">Contenuto:</label>
          <textarea id="content" name="content" rows="4" required v-model="message.content"></textarea>
        </div>
        <button type="submit">Invia Messaggio</button>
      </form>
    </div>
  </div>
</template>


<style lang="scss" scoped></style>