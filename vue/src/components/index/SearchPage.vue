<template>
  <div class="search-page">
    <div class="search-wrapper">
      <el-input v-model.trim="searchInfo.searchKey"
                clearable
                @clear="clearSearch"
                @keydown.enter.native="search"
                autofocus
      >
        <template #prefix>
          <el-icon slot="prefix"><Search/></el-icon>
        </template>
      </el-input>
      <el-button type="primary" @click="search">搜索</el-button>
    </div>
    <div class="search-result" v-if="searchInfo.searchResult.length">
      <div>
        <el-row>
          <el-col :span="12" v-for="result in searchInfo.searchResult" :key="result.id">
          <el-card @click="goodInfo(result.id)">
            <img :src="result.imgPath" class="goods-img" />
              <div class="goods-info">
                <div class="goods-name">{{ result.name }}</div>
                <div class="goods-price">{{ result.price }}</div>
                <div class="goods-desc">{{ result.description }}</div>
              </div>
          </el-card>
        </el-col>
      </el-row>
      </div>
    </div>
    <div v-else>
      <p>暂无搜索结果</p>
    </div>
  </div>
</template>

<script setup>
import { Search } from "@element-plus/icons-vue"
import { get, post } from "@/net"
import {reactive} from "vue";
import router from "@/router";
import {useStore} from "@/stores";

const store = useStore()

const searchInfo = reactive({
  searchKey: '',
  searchResult: []
})

const search = () => {
  if(searchInfo.searchKey === '' || searchInfo.searchKey === null){
    get('/api/good/search',
        (message) => {
          searchInfo.searchResult = message
        })
  } else {
    get('/api/good/search/' + searchInfo.searchKey,
        (message) => {
          searchInfo.searchResult = message
        })
  }
}

if(searchInfo.searchResult === [])
  search()

const clearSearch = () => {
  searchInfo.searchKey = '';
  searchInfo.searchResult = [];
}

const goodInfo = (id) => {
  get('/api/good/' + id , (message) => {
    store.good.info = message
    router.push('/good')
  })
}
</script>

<style scoped>
.search-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.search-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
}

.search-wrapper {
  width: 300px;
  margin-right: 10px;
}
</style>