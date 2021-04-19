<template>
  <div id="welcome">
    <el-card class="card">
      <div class="word">欢迎使用</div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Welcome"
}
</script>

<style scoped>
#welcome {
  background: url("../assets/bg/2-2.png") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
.card{
  margin: 10rem auto auto;
  background-color: rgba(54, 78, 147,0.5);
  height: 15rem;
  width: 25rem;
}
.word{
  margin-top: 5rem;
  color: white;
}
</style>
