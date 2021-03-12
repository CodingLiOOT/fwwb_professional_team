<template>
  <div>
    <el-row :gutter="5">
      <el-col :span="4">
        <el-select v-model="value" filterable placeholder="请选择线路">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="4">

        <el-select v-model="value" filterable placeholder="请选择时间粒度">
          <el-option
            v-for="item in optionsForGranularity"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span=10>
        <el-date-picker
          v-model="value1"
          type="datetimerange"
          format="yyyy-MM-dd HH:mm:ss"
          value-format="yyyy-MM-dd HH:mm:ss"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['12:00:00']">
        </el-date-picker>
      </el-col>
      <el-col :span=5>
        <el-button @click="searchStation()">查询</el-button>
      </el-col>
      <el-col :span="8">
        <dv-digital-flop :config="config" style="width:200px;height:50px;" />
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="8">
        <h1 style=color:#3fdcdc;>时间段内入站人数：</h1>
      </el-col>
      <el-col :offset="0" :span="8">
        <h1 style=color:#3fdcdc;>时间段内出站人数：</h1>
      </el-col>
      <el-col :offset="0" :span="8">
        <h1 style=color:#3fdcdc;>年龄占比</h1>
      </el-col>
    </el-row>

    <el-row :gutter="6">
      <el-col :offset="0" :span="8">
        <div id="inChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :offset="0" :span="8">
        <div id="outChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :offset="0" :span="8">
        <div id="ageLine" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  name: "Echarts_station",
  data() {
    return {
      stationValue: {},
      result: {
        time: [],
        entranceNum: [],
        outboundNum: [],
        teen: '',
        middle: '',
        old: '',
        underage: '',
      },
      value: '',
      value1: '',
      valueStation: '',
      optionsForStation: [{
        value: 'zhinan',
        label: '指南',
        children: [{
          value: 'shejiyuanze',
          label: '设计原则',
        }, {
          value: 'daohang',
          label: '导航',
        }]
      },]
      ,
      optionsForGranularity: [{
        value: '1',
        label: '小时'
      }, {
        value: '2',
        label: '天'
      }, {
        value: '3',
        label: '周'
      }, {
        value: '4',
        label: '月'
      },],
    }
  },
  methods: {
    // 虽然这个函数没用了，但可以参考里面的接口写法
    drawChart() {
      // 基于准备好的dom，初始化echarts实例
      let inChart = this.$echarts.init(document.getElementById("inChart"));
      let outChart = this.$echarts.init(document.getElementById("outChart"));
      let ageChart = this.$echarts.init(document.getElementById("ageChart"));
      let dateList = this.result.time;
      let inValueList = this.result.entranceNum;
      let outValueList = this.result.outboundNum;
      // 指定图表的配置项和数据
      let optionIn = {
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
              width: '2'//坐标线的宽度
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
              width: '2'//坐标线的宽度
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
              width: '2'//坐标线的宽度
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
              width: '2'//坐标线的宽度
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
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['0-17岁', '18-45岁', '46-69岁', '70岁以上']
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
                name: '18-45岁',
              },
              {value: this.result.middle, name: '46-69岁'},
              {value: this.result.old, name: '70岁以上'},
              {value: this.result.underage, name: '0-17岁'},
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
    inChartInit(){
      let inChart = this.$echarts.init(document.getElementById('inChart'))
      inChart.setOption({
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
          data: ['蒸发量', '降水量', '平均温度','节假日','周末'],
          left:'left',
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
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
              formatter: '{value} ml'
            }
          }
        ],
        series: [
          {
            name: '蒸发量',
            type: 'bar',
            data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
          },
          {
            name: '降水量',
            type: 'bar',
            data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
          },
          {
            name: '节假日',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          },
          {
            name: '平均温度',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          },
          {
            name: '周末',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      });
    },
    outChartInit(){
      let outChart = this.$echarts.init(document.getElementById('outChart'))
      outChart.setOption({
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
          data: ['蒸发量', '降水量', '平均温度','节假日','周末'],
          left:'left',
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
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
              formatter: '{value} ml'
            }
          }
        ],
        series: [
          {
            name: '蒸发量',
            type: 'bar',
            data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
          },
          {
            name: '降水量',
            type: 'bar',
            data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
          },
          {
            name: '节假日',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          },
          {
            name: '平均温度',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          },
          {
            name: '周末',
            type: 'line',
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      });
    },
    ageLineInit(){
      let inPie = this.$echarts.init(document.getElementById('ageLine'))
      inPie.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['邮件营销', '联盟广告', '视频广告', '直接访问']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '邮件营销',
            type: 'line',
            stack: '总量',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '联盟广告',
            type: 'line',
            stack: '总量',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '视频广告',
            type: 'line',
            stack: '总量',
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: '直接访问',
            type: 'line',
            stack: '总量',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
        ]
      })
    },
    // 点击查询按钮以后
    searchStation() {
      this.$API.p_Station({
        stationName: this.stationValue,
        startTime: this.value1[0],
        endTime: this.value1[1],
        granularity: this.value,
      })
        .then(
          res => {
            this.result = {
              time: [],
              entranceNum: [],
              outboundNum: [],
              teen: '',
              middle: '',
              old: '',
              underage: '',
            };
            for (let item in res.stationData) {
              this.result.time.push(res.stationData[item].time);
              this.result.entranceNum.push(res.stationData[item].entranceNum);
              this.result.outboundNum.push(res.stationData[item].outboundNum);
            }
            this.result.teen = res.age.teen;
            this.result.middle = res.age.middle;
            this.result.old = res.age.old;
            this.result.underage = res.age.underage;
            this.drawChart();
          }
        )
        .catch(err => {
        })
    },
    getAllStationInfo() {
      this.$API.g_getAllStationInfo().then(res => {
          this.optionsForStation = [];
          for (let i in res) {
            let temp = {
              value: '',
              label: '',
              children: []
            };
            temp.value = res[i].line;
            temp.label = res[i].line + "号线";
            for (let j in res[i].station) {
              let child = {
                value: '',
                label: ''
              };
              child.value = res[i].station[j];
              child.label = res[i].station[j] + "站";
              temp.children.push(child);
            }
            this.optionsForStation.push(temp);
          }
          //alert(JSON.stringify(this.optionsForStation));
        }
      ).catch(err => {
        console.log("Error");
      })
    },
    handleChange(value) {
      this.stationValue = value[1];
      console.log(value[1]);
    }
  },
  mounted() {
    window.vue = this;
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
    //this.drawChart();
    this.inChartInit();
    this.outChartInit();
    this.ageLineInit();
    this.getAllStationInfo();
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
};
</script>

<style scoped>
</style>
