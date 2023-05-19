<template>
  <div style="text-align: center;margin: 0 20px">
    <div style="margin: 30px 30px;">
      <div style="font-size: 25px">发布商品</div>
    </div>
    <div>
      <el-form :model="form">
        <el-form-item prop="name">
          <el-input v-model="form.name" type="text" placeholder="商品名" required>
          </el-input>
        </el-form-item>
        <el-form-item prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="商品描述">
          </el-input>
        </el-form-item>
        <el-form-item prop="price">
          <el-input v-model="form.price" type="text" placeholder="商品价格">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="text-align: center" type="primary" @click="uploadGood">发布商品</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>

import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {useStore} from "@/stores"
import {post} from "@/net"
import router from "@/router";

const store = useStore()

const form = reactive({
  name: '',
  description: '',
  price: 0,
  seller_id: store.auth.user.id
})

const uploadGood = () => {
  if(!form.name || !form.price){
    ElMessage.warning("请填写商品的名称或价格")
  } else {
    post('/api/good/upload-good', {
      name: form.name,
      description: form.description,
      price: form.price,
      seller_id: form.seller_id
    },(message) => {
      ElMessage.success(message)
      post('/api/good/my-upload',{
        seller_id: store.auth.user.id,
      },(message) => {
        store.user.uploadGoods = message;
        router.push('/index/my-upload')
      })
    })
  }
}
</script>

<style scoped>

</style>