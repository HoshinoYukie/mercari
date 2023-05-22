<template>
  <el-row>
    <el-col :span="12" v-for="(order) in orders" :key="order.id">
      <div style="margin: 10px 10px 10px 10px">
        <el-card shadow="hover">
          <img style="background-color: darkgrey" class="image">
          <div style="padding: 14px">
            <h4>{{order.name}}</h4>
            <p>{{order.price}}</p>
            <p>{{order.date}}</p>
            <div class="bottom clearfix">
              <el-button type="link" @click="goodInfo(order.goodId)" class="button">查看</el-button>
            </div>
          </div>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<script setup>

import {useStore} from "@/stores";
import router from "@/router";
import {get} from "@/net"

const store = useStore()

const orders = store.user.tradingOrder

const goodInfo = (id) => {
  get('/api/good/' + id , (message) => {
    store.good.info = message
    router.push('/good')
  })
}

</script>

<style scoped>

</style>