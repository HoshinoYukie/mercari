<template>
  <el-image :src="good.imgPath" fit="contain"></el-image>
  <el-form>
    <div style="margin-top: 20px;">
      <h1>{{ good.name }}</h1>
      <p>{{ good.description }}</p>
      <p>{{ good.price }}</p>
    </div>
  </el-form>
  <div v-if="good.status === 0" class="product-actions">
    <el-button v-if="user.id === good.sellerId" @click="edit(good.id)">编辑</el-button>
    <el-button v-if="user.id !== good.sellerId" type="primary" @click="buy()">购买</el-button>
  </div>
    <el-button v-if="(user.id === good.sellerId) && (good.status === 1)" @click="continueTrade(good.id)">已与买家取得联系</el-button>
    <el-button v-if="(user.id !== good.sellerId) && (good.status === 2)" @click="finishTrade(good.id)" type="primary">确认收货</el-button>
</template>

<script setup>
import {useStore} from "@/stores"
import router from "@/router";
import { get, post } from "@/net"
import {ElMessage} from "element-plus";
import {reactive} from "vue";

const store = useStore()
const good = store.good.info;
const user = store.auth.user;
const form = reactive({
  price: good.price,
  sellerId: good.sellerId,
  buyerId: user.id,
  goodId: good.id
})

console.log(form)

const edit = (id) => {
  get('/api/good/' + id , (message) => {
    store.good.info = message
        router.push('/good/edit')
  })
}

const continueTrade = (id) => {
  get('/api/order/continue/' + id , (message) => {
    ElMessage.success(message),
        router.push('/index/user')
  })
}

const finishTrade = (id) => {
  get('/api/order/finish/' + id , (message) => {
    ElMessage.success(message),
        router.push('/index/user')
  })
}

const buy = () => {
  console.log(form)
  post('/api/order/buy', {
    price: form.price,
    seller_id: form.sellerId,
    buyer_id: form.buyerId,
    good_id: form.goodId
  }, (message) => {
    ElMessage.success(message)
    router.push('/index')
  }, (message) => {
    ElMessage.warning(message)
    router.push('/start')
  })
}

</script>

<style scoped>

</style>