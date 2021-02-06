<template>
  <div id="app" >
    <el-row :gutter="10">
      <el-col :span="5">
        <el-select v-model="value" filterable placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span=11>
        <el-date-picker
          v-model="value1"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-col>
      <el-col :span=5>
        <el-button @click="searchStation()">查询</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内入站人数：</h1>
      </el-col>
      <el-col :offset="7" :span="8">
        <h1 style=color:#3fdcdc;>年龄比例</h1>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="12">
        <!--进站人数折线图-->
        <div id="inChart" style="width: 40rem;height:25rem;"></div>
      </el-col>
      <el-col :span="8">
        <!--年龄结构图-->
        <div id="ageChart" style="width: 40rem;height:25rem"></div>
<!--        <dv-active-ring-chart :config="config" style="width:200px;height:200px" />-->
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内出站人数：</h1>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="14">
        <!--出站人数折线图-->
        <div id="outChart" style="width: 40rem;height:25rem;"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Echarts_station",
    data() {
      return {
        result:{
          time:'',
          entranceNum: '',
          outboundNum: '',
          teen: '',
          middle: '',
          old:'',
        },
        value:'',
        value1: '',
        config:{
          radius: '80%',
          activeRadius: '85%',
          data: [
            {
              name: '儿童',
              value: 55
            },
            {
              name: '青年',
              value: 120
            },
            {
              name: '老年',
              value: 78
            },
          ],
          digitalFlopStyle: {
            fontSize: 20
          },
          lineWidth: 30
        },
        options: [{
          value: '1',
          label: '小时'
        }, {
          value: '2',
          label: '天'
        },{
          value: '3',
          label: '周'
        }, {
          value: '4',
          label: '月'
        }, ],
      }},
    methods: {
      drawChart() {
        // 基于准备好的dom，初始化echarts实例
        let inChart = this.$echarts.init(document.getElementById("inChart"));
        let outChart = this.$echarts.init(document.getElementById("outChart"));
        let ageChart=this.$echarts.init(document.getElementById("ageChart"));
        //map类型数据
        /* let data = [["2000-06-05",116],["2000-06-06",129],["2000-06-07",135],["2000-06-08",86],["2000-06-09",73],["2000-06-10",85],["2000-06-11",73],["2000-06-12",68],["2000-06-13",92],["2000-06-14",130],["2000-06-15",245],["2000-06-16",139],["2000-06-17",115],["2000-06-18",111],["2000-06-19",309],["2000-06-20",206],["2000-06-21",137],["2000-06-22",128],["2000-06-23",85],["2000-06-24",94],["2000-06-25",71],["2000-06-26",106],["2000-06-27",84],["2000-06-28",93],["2000-06-29",85],["2000-06-30",73],["2000-07-01",83],["2000-07-02",125],["2000-07-03",107],["2000-07-04",82],["2000-07-05",44],["2000-07-06",72],["2000-07-07",106],["2000-07-08",107],["2000-07-09",66],["2000-07-10",91],["2000-07-11",92],["2000-07-12",113],["2000-07-13",107],["2000-07-14",131],["2000-07-15",111],["2000-07-16",64],["2000-07-17",69],["2000-07-18",88],["2000-07-19",77],["2000-07-20",83],["2000-07-21",111],["2000-07-22",57],["2000-07-23",55],["2000-07-24",60]];
        let dateList = data.map(function (item) {
          return item[0];
        });
        let valueList = data.map(function (item) {
          return item[1];
        });*/
        // let dateList = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
        // let inValueList = [150, 230, 224, 218, 135, 147, 260];
        // let outValueList = [350, 230, 224, 218, 135, 147, 260];
        let dateList = this.result.time;
        let inValueList = this.result.entranceNum;
        let outValueList = this.result.outboundNum;
        // 指定图表的配置项和数据
        let optionIn = {
          // title: {
          //   text: '入站人数',
          //   color: '#3fdcdc',
          //   left: 'center'
          // },
          visualMap: [{
            show: false,
            type: 'continuous',
            seriesIndex: 0,
            min: 100,
            max: 300
          }],
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            data: dateList,
            axisLine: {
              lineStyle: {
                type: 'solid',
                color: '#fff',//左边线的颜色
                width:'2'//坐标线的宽度
              }
            },
            axisLabel: {
              textStyle: {
                color: '#fff',//坐标值得具体的颜色
              }
            }
          },
          yAxis: {
            type: 'value',
            axisLine: {
              lineStyle: {
                type: 'solid',
                color: '#fff',//左边线的颜色
                width:'2'//坐标线的宽度
              }
            },
            axisLabel: {
              textStyle: {
                color: '#fff',//坐标值得具体的颜色
              }
            }
          },
          series: [{
            data: inValueList,
            type: 'line'
          }]
        };
        let optionOut = {
          // title: {
          //   text: '出站人数',
          //   left: 'center'
          // },
          visualMap: [{
            show: false,
            type: 'continuous',
            seriesIndex: 0,
            min: 100,
            max: 300
          }],
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            data: dateList,
            axisLine: {
              lineStyle: {
                type: 'solid',
                color: '#fff',//左边线的颜色
                width:'2'//坐标线的宽度
              }
            },
            axisLabel: {
              textStyle: {
                color: '#fff',//坐标值得具体的颜色
              }
            }
          },
          yAxis: {
            type: 'value',
            axisLine: {
              lineStyle: {
                type: 'solid',
                color: '#fff',//左边线的颜色
                width:'2'//坐标线的宽度
              }
            },
            axisLabel: {
              textStyle: {
                color: '#fff',//坐标值得具体的颜色
              }
            }
          },
          series: [{
            data: outValueList,
            type: 'line'
          }]
        };
        let optionAge = {
          // title: {
          //   text: '年龄分布统计',
          //   // subtext: '虚构数据',
          //   left: 'center'
          // },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)'
          },
          legend: {
            bottom: 10,
            left: 'center',
            data: ['青年', '中年', '老年']
          },
          series: [
            {
              type: 'pie',
              radius: '65%',
              center: ['50%', '50%'],
              selectedMode: 'single',

              data: [
                {
                  value: this.result.teen,
                  name: '青年',
                },
                {value: this.result.middle, name: '中年'},
                {value: this.result.old, name: '老年'},
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        // 使用刚指定的配置项和数据显示图表。
        inChart.setOption(optionIn);
        outChart.setOption(optionOut);
        ageChart.setOption(optionAge);
      },
      // 点击查询按钮以后
      searchStation(){
        this.$API.p_Station({
          stationName: '西直门',
          startTime: this.value1[0],
          endTime: this.value1[1],
          granularity: this.value,
        })
          .then(
          res => {
            res.data.forEach((item, index) => {
              this.result.push({
                time: item.time,
                entranceNum: item.entranceNum,
                outboundNum: item.outboundNum,
                teen: item.teen,
                middle: item.middle,
                old: item.old,
              })
            })
          }
        )
          .catch(err => {

          })
      }
    },
    mounted() {
      document.querySelector('body').setAttribute('style', 'background-color:#16191D')
      this.drawChart();
    },
    beforeDestroy() {
      document.querySelector('body').removeAttribute('style')
    },
  };
</script>

<style scoped>
</style>

