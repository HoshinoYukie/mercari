<template>
<el-row>
  <el-col :span="6" v-for="(item) in goods" :key="item.id">
    <div style="margin: 10px 10px 10px 10px">
    <el-card shadow="hover" >
      <img style="background-color: darkgrey" class="image">
      <div style="padding: 14px">
        <span>{{item.name}}</span><br/>
        <span>{{item.description}}</span><br/>
        <span>{{item.price}}</span>
        <div class="bottom clearfix">
          <el-button type="text" class="button">查看</el-button>
        </div>
      </div>
    </el-card>
    </div>
  </el-col>
</el-row>
</template>

<script setup>

import {useStore} from "@/stores";

const store = useStore()

const goods = store.user.uploadGoods

</script>

<style scoped>

</style>