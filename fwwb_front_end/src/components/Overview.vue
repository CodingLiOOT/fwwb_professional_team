<template>
  <div>
    <el-row :gutter="5">
      <el-col :span="5">
        <div class="fs-28">{{ date.hh + ':'+ date.MM + ':'+ date.ss }}</div>
        <div class="fs-14">{{ date.yyyy + '-'+ date.mm + '-'+ date.dd }}</div>
        <div class="left fs-18 lh-42">星期{{ date.week }}</div>
      </el-col>
      <el-col :span="7">
<!--        天气插件-->
      </el-col>
      <el-col :span=5>
<!--        人数-->
      </el-col>
      <el-col :span=5>
<!--        水位图-->
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="5">
<!--        当天折线图-->
      </el-col>
      <el-col :span="5">
        <!--        热力拓扑图-->
      </el-col>
      <el-col :span="5">
        <!--        线路占比-->
      </el-col>
      <el-col :span="5">
        <!--        前十入站轮播表-->
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="5">
        <!--        未来七天折线图-->
      </el-col>
      <el-col :span="5">
        <!--        OD热力图-->
      </el-col>
      <el-col :span="5">
        <!--        年龄结构-->
      </el-col>
      <el-col :span="5">
        <!--        前十出站轮播表-->
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
name: "Overview",
  data() {
    return {
      date:{
        yyyy:'',
        mm:'',
        dd:'',
        hh:'',
        MM:'',
        ss:'',
        week:'',
      }
    };
  },
  methods: {
    getClientTime() {
      const date = new Date()
      const weeks = ['天', '一', '二', '三', '四', '五', '六']
      const that = this
      this.date = {
        yyyy: date.getFullYear(),
        mm: date.getMonth() + 1,
        dd: date.getDate(),
        hh: date.getHours(),
        MM: date.getMinutes(),
        ss: date.getSeconds(),
        week: weeks[date.getDay()]
      }
      if (this.date.mm < 10) {
        this.date.mm = '0' + this.date.mm
      }
      if (this.date.dd < 10) {
        this.date.dd = '0' + this.date.dd
      }
      if (this.date.hh < 10) {
        this.date.hh = '0' + this.date.hh
      }
      if (this.date.MM < 10) {
        this.date.MM = '0' + this.date.MM
      }
      if (this.date.ss < 10) {
        this.date.ss = '0' + this.date.ss
      }
    },
  },
  mounted() {
    // 获取当前时间
    setTimeout(() => {
      this.getClientTime()
    }, 1000)
    // 设置背景
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
  }

}
</script>

<style scoped>

</style>
