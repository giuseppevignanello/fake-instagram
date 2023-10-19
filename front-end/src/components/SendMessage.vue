
<script setup>
import { ref } from 'vue';
import axios from 'axios';
import SendMessage from './SendMessage.vue';
const apiUrl = "http://localhost:8080/api/v1.0";
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
</script>

<template>
    <div>
        <form @submit.prevent="sendMessage" id="messageForm">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required v-model="message.email">
            </div>
            <div>
                <label for="content">Contenut</label>
                <textarea id="content" name="content" rows="4" required v-model="message.content"></textarea>
            </div>
            <button type="submit">Send Message</button>
        </form>
    </div>
</template>


<style lang="scss" scoped></style>