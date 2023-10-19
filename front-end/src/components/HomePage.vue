
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const photos = ref(null);
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
  </div>
</template>


<style lang="scss" scoped></style>