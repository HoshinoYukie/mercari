<template>
  <div style="text-align: center;margin: 0 20px">
    <div style="margin: 30px 30px;">
      <div style="font-size: 25px">发布商品</div>
    </div>
    <div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="name">
          <el-input v-model="form.name" type="text" placeholder="商品名" required>
          </el-input>
        </el-form-item>
        <el-form-item prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="商品描述">
          </el-input>
        </el-form-item>
        <el-form-item prop="price">
          <el-input v-model="form.price" type="text" placeholder="商品价格" required>
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

import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {useStore} from "@/stores"
import {post} from "@/net"
import router from "@/router";

const store = useStore()
const formRef = ref()

const form = reactive({
  name: '',
  description: '',
  price: 0,
  seller_id: store.auth.user.id
})

const validatePrice = (rule, value, callback) => {
  if (value === ''){
    callback(new Error('请输入价格'))
  } else if(!/^[0-9]+(\.[0-9]{1,2})?$/.test(value)){
    callback(new Error('请输入一个小数位在两位之内的数字'))
  } else {
    callback()
  }
}

const rules = {
  name: [
    { required:true, message: '请输入商品名', trigger: ['blur', 'change'] }
  ],
  price: [
    { validator: validatePrice, trigger: ['blur', 'change'] }
  ]
}

const uploadGood = () => {
  formRef.value.validate((isValid) => {{
    if(isValid){
      post('/api/good/upload-good', {
        name: form.name,
        description: form.description,
        price: form.price,
        seller_id: form.seller_id
      },(message) => {
        ElMessage.success(message)
        router.push('/index')
      })
    } else {
      ElMessage.warning('请正确填写所有内容')
      }
    }
  })
}

</script>

<style scoped>

</style>