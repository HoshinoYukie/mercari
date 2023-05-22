<template>
<el-row style="margin: 10px 10px;display:flex;flex-wrap: wrap;">
  <el-col :md="4">
    <div>

    </div>
  </el-col>

  <el-col :md="16" :sm="24" style="background-color: wheat" >
    <el-menu mode="horizontal" style="text-align: center;justify-content: center;">
      <el-menu-item @click="router.push('/')">
        搜索商品
      </el-menu-item>
      <el-menu-item @click="router.push('/upload')">
        上架商品
      </el-menu-item>
      <el-menu-item @click="router.push('/user')">
        个人中心
      </el-menu-item>
    </el-menu>
  </el-col>

  <el-col :md="4">
    <div>

    </div>
  </el-col>
</el-row>
  <router-view/>
</template>

<script setup>

import {reactive} from "vue";
import {useStore} from "@/stores"
import router from "@/router"

const store = useStore()

const searchInfo = reactive({
  searchKey: '',
  searchResult: []
})

</script>

<style scoped>

</style>