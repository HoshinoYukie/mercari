<template>
  <el-row>
    <el-col :span="12" v-for="(good) in goods" :key="good.id">
      <div style="margin: 10px 10px 10px 10px">
        <el-card shadow="hover">
          <img style="background-color: darkgrey" class="image">
          <div style="padding: 14px">
            <h4>{{good.name}}</h4>
            <p>{{good.price}}</p>
            <div class="bottom clearfix">
              <el-button type="link" @click="goodInfo(good.id)" class="button">查看</el-button>
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

const goods = store.user.soldGoods

const goodInfo = (id) => {
  get('/api/good/' + id , (message) => {
    store.good.info = message
        router.push('/good')
  })
}
</script>

<style scoped>

</style>