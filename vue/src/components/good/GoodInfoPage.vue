<template>
  <el-image :src="good.imgPath" fit="contain"></el-image>
  <div style="margin-top: 20px;">
    <h1>{{ good.name }}</h1>
    <p>{{ good.description }}</p>
    <p>{{ good.price }}</p>
  </div>
  <div class="product-actions">
    <el-button v-if="user.id === good.sellerId" @click="edit(good.id)">编辑</el-button>
    <el-button v-if="user.id !== good.sellerId" type="primary" @click="buy">购买</el-button>
  </div>
</template>

<script setup>
import {useStore} from "@/stores"
import router from "@/router";
import {get} from "@/net"

const store = useStore()

const good = store.good.info;

const user = store.auth.user;

const edit = (id) => {
  get('/api/good/' + id , (message) => {
    store.good.info = message
        router.push('/good/edit')
  })
}
</script>

<style scoped>

</style>