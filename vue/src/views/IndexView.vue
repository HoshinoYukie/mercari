<template>
    <el-container>
      <el-header>
        <el-button @click="router.push('/upload')" type="primary" plain>上架商品</el-button>
      </el-header>
      <el-menu mode="horizontal">
        <el-menu-item @click="myUpload()">
          我发布的
        </el-menu-item>
        <el-menu-item>
          我买到的
        </el-menu-item>
        <el-menu-item>
          我卖出的
        </el-menu-item>
      </el-menu>
      <el-main class="el-col-lg-12" style="text-align: center;background-color: dodgerblue" >
        <router-view/>
      </el-main>
    </el-container>
  <div>
    <el-button @click="logout()" type="danger" plain>退出登录</el-button>
  </div>
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
    store.auth.user = null
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

</script>

<style scoped>

</style>