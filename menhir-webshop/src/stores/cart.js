import { defineStore } from 'pinia'

export const useCartStore = defineStore('cart', {
  state: () => ({
    itemsById: {},
  }),

  getters: {
    items(state) {
      return Object.values(state.itemsById)
    },
    totalCount(state) {
      return Object.values(state.itemsById).reduce((sum, item) => sum + item.qty, 0)
    },
  },

  actions: {
    add(product) {
      if (!product || product.id == null) return

      const existing = this.itemsById[product.id]
      if (existing) {
        existing.qty += 1
      } else {
        this.itemsById[product.id] = {
          id: product.id,
          title: product.title,
          image: product.image,
          value: product.value ?? 0,
          qty: 1,
        }
      }
    },

    inc(id) {
      const item = this.itemsById[id]
      if (!item) return
      item.qty += 1
    },

    dec(id) {
      const item = this.itemsById[id]
      if (!item) return
      item.qty -= 1
      if (item.qty <= 0) delete this.itemsById[id]
    },

    clear() {
      this.itemsById = {}
    },
  },
})
