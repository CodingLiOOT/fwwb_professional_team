import Vue from 'vue'

import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
  state:{
    user:undefined,
    token:undefined
  },
  mutations:{
    login(state,payload){
      state.user=JSON.stringify(payload.user);
      state.token=payload.token;
      sessionStorage.token = payload.token;
      sessionStorage.user=JSON.stringify(payload.user);
    },
    logout(state){
      state.user=undefined;
      state.token=undefined;
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('user');
    },
    setToken(state,payload){
      state.token=payload;
    },
    removeToken(state){
      state.token='';
    }
  },
  actions:{
    login(context,payload){
      context.commit('login',payload)
    },
    logout(context,payload){
      context.commit('logout',payload)
    },
    setToken(context,payload){
      context.commit('login',payload);
    },
    removeToken(context,payload){
      context.commit('logout',payload);
    }
  },
  getters:{
    getUser:state => {
      return JSON.parse(state.user)
    },
    getUserAttribute:(state, getters) =>(name)=> {
      return getters.getUser[name];
    },
    getToken:state => {
      return state.token
    }
  }
})

export default store
