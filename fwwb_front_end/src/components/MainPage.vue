<template>
  <el-container style="width: 100%;height: 100%; top: 0; bottom: 0;" direction="vertical">
    <el-header class="myHeader" style="padding: 0">
      <el-row>
        <el-col :span="8"><img class="myImg" src="../assets/logo.png"></el-col>
        <el-col :span="8" align="middle">
          <div class="myTitle">
            <strong style = "color:#ffffff">专业团队</strong>
          </div>
        </el-col>
        <el-col :span="8" class="username"></el-col>
      </el-row>
    </el-header>
    <el-container direction="horizontal" style=" height: 100%;width: 100%; top: 0; bottom: 0;">
      <el-aside width="200px">
        <el-row class="tac">
          <el-col :span="12">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              router="true"
              @open="handleOpen"
              @close="handleClose">
              <el-menu-item index="/mainPage/data_V">
                <i class="el-icon-menu"></i>
                <template slot="title">dataV</template>
              </el-menu-item>
              <el-menu-item index="/mainPage/baiduMap">
                <i class="el-icon-document"></i>
                <span slot="title">baidu-map</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>


  export default {
    name: "MainPage",

    data() {
      return {
        fold: 'el-icon-s-unfold',
        unfold: 'el-icon-s-fold',
        myIcon: 'el-icon-s-fold',
        isFold: true,
      }
    },

    mounted: function () {
      this.load();
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }

    }
  }
</script>
<style>
  .tac {
    margin: 0;
    height: 100%;
  }

  html, body {
    margin: 0;
    top: 0;
    bottom: 0;
    height: 100%;
  }

  .myHeader {
    background-color: #60b4c9;
  }

  .myImg {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    margin: 5px;
  }

  .myTitle {
    font-family: SimSun;
    font-size: 30px;
    height: 60px;
    line-height: 60px;
  }

  .el-menu-mainBar i {
    color: #ffffff;
  }
  .username{
    margin-top: 20px;
    font-size: 20px;
    color: white;
  }


</style>
