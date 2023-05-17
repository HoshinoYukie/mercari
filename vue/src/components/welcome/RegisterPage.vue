<template>
  <div style="text-align: center;margin: 0 20px">
    <div style="margin-top: 150px;">
      <div style="font-size: 25px">注册新用户</div>
    </div>
    <div style="margin-top: 30px">
      <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" :maxlength="8" type="text" placeholder="用户名">
            <template #prefix>
              <el-icon slot="prefix"><User/></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" placeholder="密码">
            <template #prefix>
              <el-icon slot="prefix"><Unlock/></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password_repeat">
          <el-input v-model="form.password_repeat" type="password" placeholder="确认密码">
            <template #prefix>
              <el-icon slot="prefix"><Unlock/></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="form.email" type="email" placeholder="邮箱地址">
            <template #prefix>
              <el-icon slot="prefix"><Message/></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
            <el-row :gutter="10">
              <el-col :span="17">
                <el-input v-model="form.code" :maxlength="5" type="text" placeholder="请输入验证码">
                  <template #prefix>
                    <el-icon slot="prefix"><EditPen/></el-icon>
                  </template>
                </el-input>
              </el-col>
              <el-col :span="7" style="text-align: right">
                <el-button type="primary" @click="validateEmail"
                           :disabled="!isEmailValid || coldTime > 0">
                  {{coldTime > 0 ? ' ' + coldTime + ' 秒后重试' : '获取验证码'}}
                </el-button>
              </el-col>
            </el-row>
        </el-form-item>
      </el-form>
    </div>
    <div style="margin-top: 20px">
      <el-button @click="register" style="width: 270px" type="primary">立即注册</el-button>
    </div>
    <el-divider>
      <span style="color: grey">已有账号</span>
    </el-divider>
    <div style="margin-top: 20px">
      <el-button @click="router.push('/')" style="width: 270px">前往登录</el-button>
    </div>
  </div>
</template>

<script setup>
import {Unlock, User, Message, EditPen} from "@element-plus/icons-vue"
import {reactive, ref} from "vue";
import router from "../../router";
import {ElMessage} from "element-plus";
import {post} from '@/net'

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: ''
})

const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if(!/^[\u4e00-\u9fa5a-zA-Z0-9_-]+$/.test(value)) {
    callback(new Error('用户名不能以特殊字符开头或结尾'))
  } else {
    callback()
  }
}

const validatePasswordRepeat = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('确认密码不能为空'))
  } else if (value !== form.password) {
    callback(new Error("两次输入不相同"))
  } else {
    callback()
  }
}

const rules = {
  username: [
    { validator: validateUsername, trigger: ['blur', 'change'] },
    { min: 2, max: 10, message: '用户名长度不能少于 2 且不能大于 10', trigger: ['blur', 'change'] },
  ],
  password: [
    { required:true, message: '密码不能为空', trigger: ['blur', 'change'] },
    { min: 6, message: '密码长度不能少于 6', trigger: ['blur', 'change'] },
  ],
  password_repeat: [
    { validator: validatePasswordRepeat, trigger: ['blur', 'change'] },
  ],
  email: [
    { required:true, message: '邮箱地址不能为空', trigger: ['blur', 'change'] },
    {
      type: 'email',
      message: '请输入合法的电子邮件地址',
      trigger: ['blur', 'change']
    },
  ],
  code: [
    { required:true, message: '前往邮箱获取验证码并输入', trigger: ['blur', 'change'] },
  ]
}

const formRef = ref()
const isEmailValid = ref(false);
const coldTime = ref(0)

const onValidate = (prop, isValid) => {
  if(prop === 'email')
    isEmailValid.value = isValid;
}

const register = () => {
  formRef.value.validate((isValid) => {
    if(isValid){
      post('/api/auth/register', {
        username: form.username,
        password: form.password,
        email: form.email,
        code: form.code
      }, (success) => {
        ElMessage.success(success);
        router.push('/');
      })
    } else {
      ElMessage.warning('请完整填写所有内容')
    }
  })
}

const validateEmail = () => {
  coldTime.value = 60
  post('/api/auth/valid-create-email', {
    email:form.email
  }, (message) => {
    ElMessage.success(message)
    setInterval(() => coldTime.value--, 1000)
  }, (message) => {
    ElMessage.warning(message)
    coldTime.value = 0
  })
}

</script>

<style scoped>

</style>