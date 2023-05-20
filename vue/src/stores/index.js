import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useStore = defineStore('store', () => {
  const auth = reactive({
    user: null
  })
  const user = reactive({
    uploadGoods: [],
    boughtGoods: [],
    soldGoods: [],
  })
  const good = reactive({
    info: null
  })
  return { auth, user, good}
})
