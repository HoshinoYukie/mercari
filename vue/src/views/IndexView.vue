<template>
    <el-container>
      <el-header style="margin: 10px">
        <el-row>
          <el-col>
            <div>
            <el-button @click="router.push('/upload')" type="primary">上架商品</el-button>
            </div>
          </el-col>
          <el-col>
            <div>
              <el-button @click="logout()" type="danger" plain>退出登录</el-button>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main flex="flex">
        <el-menu mode="horizontal" style="text-align: center;">
          <el-menu-item @click="myUpload()">
            我发布的
          </el-menu-item>
          <el-menu-item @click="myBought()">
            我买到的
          </el-menu-item>
          <el-menu-item @click="mySold()">
            我卖出的
          </el-menu-item>
        </el-menu>
        <el-row justify="center">
          <el-col :xs="24" :sm="16" :md="12">
            <router-view/>
          </el-col>
        </el-row>
      </el-main>
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
    router.push('/index/my-upload')
  })
}

const myBought = () => {
  post('/api/good/my-bought',{
    buyer_id: store.auth.user.id,
  },(message) => {
    store.user.boughtGoods = message;
    router.push('/index/my-bought')
  })
}

const mySold = () => {
  post('/api/good/my-sold',{
    seller_id: store.auth.user.id,
  },(message) => {
    store.user.soldGoods = message;
    router.push('/index/my-sold')
  })
}


</script>

<style scoped>

</style>