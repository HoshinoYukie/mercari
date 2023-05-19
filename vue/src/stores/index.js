import {ref, computed, reactive} from 'vue'
import { defineStore } from 'pinia'

export const useStore = defineStore('store', () => {
  const auth = reactive({
    user: null
  })
  const user = reactive({
    uploadGoods: []
  })
  return { auth , user}
})
