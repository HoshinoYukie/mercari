<template>
  <div>
    <div style="margin: 70px 30px">
      <el-steps :active="active" finish-status="success" align-center>
        <el-step title="验证邮箱" />
        <el-step title="重置密码" />
      </el-steps>
    </div>
    <transition name="el-fade-in-linear" mode="out-in">
      <div style="text-align: center;margin: 0 20px;height: 100%" v-if="active === 0">
        <div style="margin-top: 80px;">
          <div style="font-size: 25px">验证邮箱</div>
        </div>
        <div style="margin-top: 30px">
          <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
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
          <el-button @click="startReset" style="width: 270px" type="primary">前往设置新密码</el-button>
        </div>
      </div>
    </transition>
    <transition name="el-fade-in-linear" mode="out-in">
      <div style="text-align: center;margin: 0 20px;height: 100%" v-if="active === 1">
      <div style="margin-top: 80px;">
        <div style="font-size: 25px">设置新密码</div>
      </div>
      <div style="margin-top: 30px">
        <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
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
        </el-form>
      </div>
      <div style="margin-top: 20px">
        <el-button @click="doReset" style="width: 270px" type="primary">重置密码</el-button>
      </div>
    </div>
    </transition>
  </div>
</template>

<script setup>

import {Message, EditPen} from '@element-plus/icons-vue'
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import router from "../../router";
import {post} from '@/net';

const formRef = ref()
const isEmailValid = ref(false);
const coldTime = ref(0)
const active = ref(0)

const form = reactive({
  email: '',
  code: '',
  password: '',
  password_repeat: '',
})

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
  email: [
    {required: true, message: '邮箱地址不能为空', trigger: ['blur', 'change']},
    {
      type: 'email',
      message: '请输入合法的电子邮件地址',
      trigger: ['blur', 'change']
    },
  ],
  code: [
    { required:true, message: '前往邮箱获取验证码并输入', trigger: ['blur', 'change'] },
  ],
  password: [
    { required:true, message: '密码不能为空', trigger: ['blur', 'change'] },
    { min: 6, message: '密码长度不能少于 6', trigger: ['blur', 'change'] },
  ],
  password_repeat: [
    { validator: validatePasswordRepeat, trigger: ['blur', 'change'] },
  ]
}

const onValidate = (prop, isValid) => {
  if(prop === 'email')
    isEmailValid.value = isValid;
}

const validateEmail = () => {
  coldTime.value = 60
  post('/api/auth/valid-reset-email', {
    email:form.email
  }, (message) => {
    ElMessage.success(message)
    setInterval(() => coldTime.value--, 1000)
  }, (message) => {
    ElMessage.warning(message)
    coldTime.value = 0
  })
}

const startReset = () => {
  formRef.value.validate((isValid) => {
    if(isValid) {
      post('/api/auth/start-reset', {
        email: form.email,
        code: form.code
      }, () => {
        active.value++
      })
    } else {
      ElMessage.warning('请填写电子邮件地址和验证码')
    }
  })
}

const doReset = () => {
  formRef.value.validate((isValid) => {
    if(isValid) {
      post('/api/auth/do-reset', {
        password: form.password
      }, (message) => {
        ElMessage.success(message)
        router.push('/')
      })
    } else {
      ElMessage.warning('请填写新的密码')
    }
  })
}

</script>

<style scoped>

</style>