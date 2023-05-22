<template>
  <el-row>
    <el-col :span="12" v-for="(good) in goods" :key="good.id">
      <div>
        <el-card shadow="hover" style="margin: 10px 10px 10px 10px">
          <img style="background-color: darkgrey" class="image">
          <div style="padding: 14px">
            <h4>{{good.name}}</h4>
            <p>{{good.price}}</p>
            <div>
              <el-button type="link" @click="goodInfo(good.id)">查看</el-button>
              <el-button type="link" @click="deleteGood(good.id)">下架</el-button>
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
import {get, post} from "@/net"
import {ElMessage} from "element-plus";

const store = useStore()
const user = store.auth.user
const goods = store.user.uploadGoods

const goodInfo = (id) => {
  get('/api/good/' + id , (message) => {
    store.good.info = message
        router.push('/good')
  })
}

const deleteGood = (id) => {
  get('/api/good/delete/' + id , (message) => {
    ElMessage.success(message),
        router.push('/index')
  }, (message) => {
    ElMessage.warning(message)
  })
}

</script>

<style scoped>

</style>