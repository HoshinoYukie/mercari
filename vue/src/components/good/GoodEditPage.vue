<template><div>
  <el-form ref="form" :model="good" label-width="80px">
    <el-form-item label="商品名">
      <el-input v-model="good.name" placeholder="请输入商品名字" />
    </el-form-item>
    <el-form-item label="商品描述">
      <el-input type="textarea" v-model="good.description" placeholder="请输入商品描述" />
    </el-form-item>
    <el-form-item label="商品价格">
      <el-input v-model="good.price" placeholder="请输入商品价格" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitEdit">提交</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script setup>

import {useStore} from "@/stores"
import {post} from "@/net"
import {ElMessage} from "element-plus";
import router from "@/router";

const store = useStore()
const good = store.good.info;
const user = store.auth.user

const submitEdit = () => {
  post("/api/good/good-edit", {
    name: good.name,
    description: good.description,
    price: good.price,
    id: good.id
  }, (message) => {
    ElMessage.success(message)
    router.push('/index')
  }, (message) => {
    ElMessage.warning(message)
  })
}

</script>

<style scoped>

</style>