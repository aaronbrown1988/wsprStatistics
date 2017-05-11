import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Distance from '@/components/Distance'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/distance',
      name: 'Distance',
      component: Distance
    }
  ]
})
