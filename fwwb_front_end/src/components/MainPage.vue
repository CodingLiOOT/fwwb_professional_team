<template>
  <div class="mainPage">
    <el-container style="width: 100%;height: 100%; top: 0; bottom: 0;" direction="vertical">
<!--      <el-header class="myHeader" style="padding: 0">-->
        <el-row>
          <el-col>
<!--            <el-menu-->
<!--              default-active="2"-->
<!--              class="el-menu-vertical-demo"-->
<!--              mode="horizontal"-->
<!--              background-color="#545c64"-->
<!--              text-color="#fff"-->
<!--              active-text-color="#ffd04b"-->
<!--              router-->
<!--              @open="handleOpen"-->
<!--              @close="handleClose">-->
<!--              <el-menu-item index="/mainPage/overviewIndex">-->
<!--                <i class="el-icon-menu"></i>-->
<!--                <template slot="title">总览</template>-->
<!--              </el-menu-item>-->
<!--              <el-menu-item index="/mainPage/data_V">-->
<!--                <i class="el-icon-menu"></i>-->
<!--                <template slot="title">线</template>-->
<!--              </el-menu-item>-->
<!--              <el-menu-item index="/mainPage/echarts">-->
<!--                <i class="el-icon-document"></i>-->
<!--                <span slot="title">站点</span>-->

<!--              </el-menu-item>-->
<!--            </el-menu>-->
          </el-col>
        </el-row>
<!--      </el-header>-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>

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
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }

  },
  mounted() {
    window.Vue=this;
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
.mainPage{
  /*background: url("../assets/bg/2-2.png") no-repeat center;*/
  /*height: 100%;*/
  /*width: 100%;*/
  /*background-size: cover;*/
  /*position: fixed;*/
}


</style>
