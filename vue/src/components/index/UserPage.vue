<template>
    <el-container style="min-height: 80vh;display: flex">
      <el-main>
        <el-menu mode="horizontal" style="text-align: center;justify-content: center;">
          <el-menu-item @click="myUpload()">
            我发布的
          </el-menu-item>
          <el-menu-item @click="myBought()">
            我买到的
          </el-menu-item>
          <el-menu-item @click="mySold()">
            我卖出的
          </el-menu-item>
          <el-menu-item @click="myTrading()">
            交易中的
          </el-menu-item>
        </el-menu>
        <el-row justify="center">
          <el-col :xs="24" :sm="16" :md="12">
            <router-view/>
          </el-col>
        </el-row>
      </el-main>
      <el-footer>
        <div style="text-align: center">
          <el-button @click="logout()" type="danger">退出登录</el-button>
        </div>
      </el-footer>
    </el-container>
</template>

<script setup>
import {post, get} from '@/net';
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";

const store = useStore()

const logout = () => {
  get('/api/auth/logout', (message) => {
    ElMessage.success(message)
    store.auth.user = null,
    store.user.boughtGoods = [],
    store.user.uploadGoods = [],
    store.user.soldGoods = []
    router.push('/')
  })
}

const myUpload = () => {
  post('/api/good/my-upload',{
    seller_id: store.auth.user.id,
  },(message) => {
    store.user.uploadGoods = message;
    router.push('/user/my-upload')
  })
}

const myBought = () => {
  post('/api/good/my-bought',{
    buyer_id: store.auth.user.id,
  },(message) => {
    store.user.boughtGoods = message;
    router.push('/user/my-bought')
  })
}

const mySold = () => {
  post('/api/good/my-sold',{
    seller_id: store.auth.user.id,
  },(message) => {
    store.user.soldGoods = message;
    router.push('/user/my-sold')
  })
}

const myTrading = () => {
  get('/api/order/my-trading/' + store.auth.user.id,(message) => {
    store.user.tradingOrder = message;
    router.push('/user/my-trading')
  })
}

</script>

<style scoped>

</style>