
<script setup>
import { ref } from 'vue';
import axios from 'axios';
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
    <!-- Modal -->
    <div class="modal fade" id="sendMessageModal" tabindex="-1" aria-labelledby="sendMessageModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Send a message</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <form @submit.prevent="sendMessage" id="messageForm">
                        <div>
                            <label class="form-label" for="email">Email:</label>
                            <input class="form-control" type="email" id="email" name="email" required
                                v-model="message.email">
                        </div>
                        <div>
                            <label class="form-label" for="content">Contenut</label>
                            <textarea class="form-control" id="content" name="content" rows="4" required
                                v-model="message.content"></textarea>
                            <button type="submit" class="mt-2 btn btn-primary">Send Message</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</template>


<style lang="scss" scoped></style>