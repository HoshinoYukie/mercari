<script setup>
import {Unlock, User} from "@element-plus/icons-vue"
</script>

<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;background-color: dodgerblue">
      <el-image style="width: 100%;height: 100%" fit="cover"
          src="https://ts1.cn.mm.bing.net/th/id/R-C.80936e1e713a73518bf7abcfa4ae1a1e?rik=yS230r9Hoc2Oxg&riu=http%3a%2f%2fpic.bizhi360.com%2fbbpic%2f50%2f950.jpg&ehk=h4r8dA44aTPn6Dkaf%2fJBQ4GVBhAMxYwtQp8EuotGFb4%3d&risl=&pid=ImgRaw&r=0"/>
    </div>
    <div style="width: 400px;background-color: white">
        <div style="text-align: center;margin: 0 20px">
          <div style="margin-top: 150px;">
            <div style="font-size: 25px">登录</div>
            <div style="margin-top: 10px;font-size: 14px;color:grey">在进入系统之前请先输入用户名和密码进行登录</div>
          </div>
          <div style="margin-top: 30px">
            <el-input type="text" placeholder="用户名">
              <template #prefix>
                <el-icon slot="prefix"><User/></el-icon>
              </template>
            </el-input>
            <el-input type="password" style="margin-top: 15px" placeholder="密码">
              <template #prefix>
                <el-icon slot="prefix"><Unlock/></el-icon>
              </template>
            </el-input>
          </div>
          <div style="margin-top: 10px;text-align: left">
            <el-checkbox v-model="checked1">记住我</el-checkbox>
          </div>
          <div style="margin-top: 20px">
            <el-button style="width: 270px" type="primary">登录</el-button>
          </div>
          <el-divider>
            <span style="color: grey">没有账号</span>
          </el-divider>
          <div style="margin-top: 20px">
            <el-button style="width: 270px">注册</el-button>
          </div>
        </div>
      </div>
  </div>
</template>

<style scoped>

</style>
