<template>
  <div>
    <el-row :gutter="5">
      <el-col :span="5">
        <h2 class="nowTime">当前时间</h2>
        <div class="nowTime">{{nowTime.date}}</div>
        <div class="nowTime">{{nowTime.time}}</div>
      </el-col>
      <el-col :span="7">
        <div id="weather-v2-plugin-standard"></div>
      </el-col>
      <el-col :span=5>
<!--        人数-->
        <h1 style=color:#3fdcdc;>当前总人次：</h1>
        <dv-digital-flop :config="config" style="width:10rem;height:2.5rem;margin-left: 3rem" /><
      </el-col>
      <el-col :span=5>
<!--        水位图-->
        <dv-percent-pond :config="config" style="width:15rem;height:8rem;margin-top: 3rem;margin-left: 2rem" />
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>当天总人次：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>热力拓扑图：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>线路占比：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>入站站点排行表：</h1>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="5">
        <div id="dayChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :span="5">
        <!--        热力拓扑图-->
        <!--        以下div仅为占位所用-->
        <div style="width: 25rem;height: 25rem;background-color: white;margin-left: 8rem"></div>
      </el-col>
      <el-col :span="5">
        <!--        线路占比-->
        <div id="linePie" :style="{width: '25rem', height: '25rem',marginLeft:'15rem'}"></div>
      </el-col>
      <el-col :span="5">
        <!--        前十入站轮播表-->
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>未来七天总人次：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>热力OD图：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>年龄结构：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>出站站点排行表：</h1>
      </el-col>
    </el-row>
    <el-row :gutter="5">
      <el-col :span="5">
        <div id="weekChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :span="5">
        <!--        OD热力图-->
        <!--        以下div仅为占位所用-->
        <div style="width: 25rem;height: 25rem;background-color: white;margin-left: 8rem"></div>
      </el-col>
      <el-col :span="5">
        <!--        年龄结构-->
        <div id="agePie" :style="{width: '25rem', height: '25rem',marginLeft:'15rem'}"></div>
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
      nowTime:{
        date:'',
        time:'',
      },
      //dataV
      config: {
        //数字翻牌器
        number: [10000],
        content: '{nt}',
        textAlign:'right',
        //进度条
        value: 66,
      }
    };
  },
  methods: {
    timeFormat(timeStamp) {
      let year = new Date(timeStamp).getFullYear();
      let month =new Date(timeStamp).getMonth() + 1 < 10? "0" + (new Date(timeStamp).getMonth() + 1): new Date(timeStamp).getMonth() + 1;
      let date =new Date(timeStamp).getDate() < 10? "0" + new Date(timeStamp).getDate(): new Date(timeStamp).getDate();
      let hh =new Date(timeStamp).getHours() < 10? "0" + new Date(timeStamp).getHours(): new Date(timeStamp).getHours();
      let mm =new Date(timeStamp).getMinutes() < 10? "0" + new Date(timeStamp).getMinutes(): new Date(timeStamp).getMinutes();
      let ss =new Date(timeStamp).getSeconds() < 10? "0" + new Date(timeStamp).getSeconds(): new Date(timeStamp).getSeconds();
      this.nowTime.date = year + "年" + month + "月" + date +"日";
      this.nowTime.time=hh+":"+mm+':'+ss
    },
    nowTimes(){
      this.timeFormat(new Date());
      setInterval(this.nowTimes,1000);
      this.clear()
    },
    clear(){
      clearInterval(this.nowTimes)
      this.nowTimes = null;
    },
    dayChartInit(){
      let inChart = this.$echarts.init(document.getElementById('dayChart'))
      inChart.setOption({
        title:{
          text: '当天总人次',
          bottom:0,
          left:'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        legend: {
          data: ['人次'],
          width:300,
        },
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            // min: 0,
            // max: 250,
            // interval: 50,
            // axisLabel: {
            //   formatter: '{value} ml'
            // }
          }
        ],
        series: [
          {
            name: '人次',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      });
    },
    weekChartInit(){
      let inChart = this.$echarts.init(document.getElementById('weekChart'))
      inChart.setOption({
        title:{
          text: '当天总人次',
          bottom:0,
          left:'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        legend: {
          data: ['人次'],
          width:300,
        },
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            // min: 0,
            // max: 250,
            // interval: 50,
            // axisLabel: {
            //   formatter: '{value} ml'
            // }
          }
        ],
        series: [
          {
            name: '人次',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      });
    },
    linePieInit(){
      let linePie = this.$echarts.init(document.getElementById('linePie'))
      linePie.setOption({
        // title: {
        //   text: '某站点用户访问来源',
        //   subtext: '纯属虚构',
        //   left: 'center'
        // },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          show:true,
          data: ['1号线', '2号线', '3号线', '4号线', '5号线', '6号线', '7号线', '8号线', '9号线', '10号线', '11号线', '12号线', '13号线'],
          left:'left',
          width:100
        },
        series: [
          {
            name: '线路占比',
            type: 'pie',
            radius: '35%',
            center: ['40%', '40%'],
            selectedMode: 'single',
            label:{
              show:false
            },
            data: [
              {value: 1548, name: '1号线',},
              {value: 735, name: '2号线'},
              {value: 510, name: '3号线'},
              {value: 434, name: '4号线'},
              {value: 635, name: '5号线'},
              {value: 135, name: '6号线'},
              {value: 735, name: '7号线'},
              {value: 225, name: '8号线'},
              {value: 895, name: '9号线'},
              {value: 675, name: '10号线'},
              {value: 345, name: '11号线'},
              {value: 315, name: '12号线'},
              {value: 385, name: '13号线'}
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          },
        ]
      })
    },
    agePieInit(){
      let agePie = this.$echarts.init(document.getElementById('agePie'))
      agePie.setOption({
        // title: {
        //   text: '某站点用户访问来源',
        //   subtext: '纯属虚构',
        //   left: 'center'
        // },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          show:true,
          data: ['少年', '青年', '中年', '老年'],
          left:'left',
          width:100
        },
        series: [
          {
            name: '年龄结构',
            type: 'pie',
            radius: '35%',
            center: ['40%', '25%'],
            selectedMode: 'single',
            label:{
              show:false
            },
            data: [
              {value: 1548, name: '少年',},
              {value: 735, name: '青年'},
              {value: 510, name: '中年'},
              {value: 434, name: '老年'},
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          },
        ]
      })
    },
  },
  mounted() {
    // 获取当前时间
    this.nowTimes();
    // 天气
    window.WIDGET = {
      CONFIG: {
        "layout": 1,
        "width": "450",
        "height": "150",
        "background": 1,
        "dataColor": "FFFFFF",
        "language": "zh",
        "borderRadius": "10",
        "city": "CN101010100",
        "key": "kAccMmzpUi"
      }
    };
    let script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://apip.weatherdt.com/standard/static/js/weather-standard-common.js?v=2.0';
    document.getElementsByTagName('head')[0].appendChild(script);
    // 画图
    this.dayChartInit();
    this.weekChartInit();
    this.linePieInit();
    this.agePieInit();
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
  .nowTime{
    color: white;
    font-size: 25px;
  }
</style>
