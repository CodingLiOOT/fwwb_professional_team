<template>
  <div>
    <h1>主页面</h1>
    欢迎你!
    <el-button type="primary" @click="test()">test</el-button>
    <span v-if="token"> {{ user.name }}
            <el-button type="warning" @click="logout">注销</el-button>
        </span>
    <el-button v-else type="success" @click="login">点击登录</el-button>
  </div>
</template>

<script>
export default {
  name: 'index',
  data() {
    return {

    }
  },
  methods: {
    login() {
      this.$router.replace('/login')
    },
    logout() {
      this.$store.dispatch('logout').then(() => {
        this.$router.replace('/login')
      })
    },
    test(){
      //alert(this.$store.getters.getUserAttribute('userName'))
      this.$API.g_getAllUser()
        .then(
          data=>{
            alert(data[0].userName);
          }
        )
        .catch(
          error=>{

          }
        )
    }
  },
  computed: {
    token() {
      return this.$store.getters.getToken
    },
    user(){
      return this.$store.getters.getUser
    }
  }
}
</script>
