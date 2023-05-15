<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;background-color: dodgerblue">
      <el-image style="width: 100%;height: 100%" fit="cover"
                src="https://ts1.cn.mm.bing.net/th/id/R-C.80936e1e713a73518bf7abcfa4ae1a1e?rik=yS230r9Hoc2Oxg&riu=http%3a%2f%2fpic.bizhi360.com%2fbbpic%2f50%2f950.jpg&ehk=h4r8dA44aTPn6Dkaf%2fJBQ4GVBhAMxYwtQp8EuotGFb4%3d&risl=&pid=ImgRaw&r=0"/>
    </div>
    <div class="welcome-title">
      <div>二手交易平台</div>
    </div>
    <div style="width: 400px;background-color: white;z-index: 1">
      <router-view v-slot="{ Component }">
        <el-collapse-transition>
          <component :is="Component" />
        </el-collapse-transition>
      </router-view>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.welcome-title {
  position: absolute;
  bottom: 30px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;
}
</style>