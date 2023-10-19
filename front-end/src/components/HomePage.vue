
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const photos = ref(null);


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
    <ul>
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