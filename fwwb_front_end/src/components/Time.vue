<template>
<div>
  <el-row>
    <el-col>
      <h1>工作日和周末</h1>
    </el-col>
  </el-row>
  <el-row class="select" gutter="20">
    <el-col :span="6">
<!--      选择总览还是几号线-->
      <el-select v-model="value" placeholder="请选择查询线路" style="width: 15rem">
        <el-option
          v-for="item in optionsForStation"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-col>
    <el-col span="4">
      <el-button>查询</el-button>
    </el-col>
  </el-row>
  <el-row gutter="20">
    <el-col :span="12">
      <div id="week" style="width: 40rem;height:25rem"></div>
    </el-col>
    <el-col :span="6">
      <div id="workRing" style="width: 20rem;height:25rem"></div>
    </el-col>
    <el-col :span="6">
      <div id="weekendRing"style="width: 20rem;height:25rem"></div>
    </el-col>
  </el-row>
  <el-row>
    <el-col>
      <h1>早晚高峰</h1>
    </el-col>
  </el-row>
  <el-row class="select" gutter="20">
    <el-col :span="6">
      <!--      选择总览还是几号线-->
      <el-select v-model="value" placeholder="请选择查询线路" style="width: 15rem">
        <el-option
          v-for="item in optionsForStation"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-col>
    <el-col span="4">
      <el-button>查询</el-button>
    </el-col>
  </el-row>
  <el-row gutter="20">
    <el-col :span="12">
      <div id="day" style="width: 40rem;height:25rem"></div>
    </el-col>
    <el-col :span="6">
      <div id="peakRing" style="width: 20rem;height:25rem"></div>
    </el-col>
    <el-col :span="6">
      <div id="normalRing"style="width: 20rem;height:25rem"></div>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "Time",
  data(){
    return {
      valueStation: '',
      optionsForStation:[],
      weekData:{
        list:[],
        workday:[],
        weekend:[],
      },
      workRingData:{
        list:[],
        values:[],
      },
      weekendRingData:{
        list:[],
        values:[],
      }
    }
  },
  methods:{
    drawChart(){
      let chartWeek = this.$echarts.init(document.getElementById("week"));
      let workRing = this.$echarts.init(document.getElementById("workRing"));
      let weekendRing = this.$echarts.init(document.getElementById("weekendRing"));

      let optionWeek;
      let optionWork;
      let optionWeekend;

      //数据
      let weekendList=this.weekData.list;
      let workday=this.weekData.workday;
      let weekend=this.weekData.weekend;

      let workRingList=this.workRingData.list;
      let workValues=this.workRingData.values;

      let weekendRingList=this.weekendRingData.list;
      let weekendValues=this.weekendRingData.values;

      optionWeek = {
        title: {
          text: '线路人流量图',
          left: 'left'
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
          data: ['工作日', '周末']
        },
        xAxis: [
          {
            type: 'category',
            data: weekendList,
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: {
          type: 'value',
          name: '人数',
        },
        series: [
          {
            name: '工作日',
            type: 'bar',
            data: workday
          },
          {
            name: '周末',
            type: 'bar',
            data: weekend,
          },
        ]
      };
      optionWork = {
        title: {
          text: '工作日',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['1号线','2号线'],
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',

            data: [
              {name:'1号线',value:'23'},
              {name:'2号线',value:'53'},

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
      for(let i in workValues){
        optionWork.series.data.push({
          value: workValues[i],
          name: workRingList[i],
        })
      };
      optionWeekend={
        title: {
          text: '周末',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: weekendRingList,
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',

            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      for(let i in weekendValues){
        optionWeekend.series.data.push({
          value: weekendValues[i],
          name: weekendRingList[i],
        })
      };

      chartWeek.setOption(optionWeek);
      workRing.setOption(optionWork);
      weekendRing.setOption(optionWeekend);
    },
    drawPeakChart(){
      let chartDay = this.$echarts.init(document.getElementById("day"));
      let peakRing = this.$echarts.init(document.getElementById("peakRing"));
      let normalRing = this.$echarts.init(document.getElementById("normalRing"));

      let optionDay;
      let optionPeak;
      let optionNormal;

      optionDay = {
        title: {
          text: '线路人流量图',
          left: 'left'
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
          data: ['工作日', '周末']
        },
        xAxis: [
          {
            type: 'category',
            data: [],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: {
          type: 'value',
          name: '人数',
        },
        series: [
          {
            name: '工作日',
            type: 'bar',
            data: []
          },
          {
            name: '周末',
            type: 'bar',
            data: [],
          },
        ]
      };
      optionPeak = {
        title: {
          text: '高峰期',
          left: 'center'
        },
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

            data: [],
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
      optionNormal={
        title: {
          text: '非高峰',
          left: 'center'
        },
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

            data: [],
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

      chartDay.setOption(optionDay);
      peakRing.setOption(optionPeak);
      normalRing.setOption(optionNormal);

    },
    getAllStationInfo() {
      this.$API.g_getAllStationInfo().then(res => {
          this.optionsForStation = [];
          this.optionsForStation.push({
            value:'0',
            label:'总览',
          })
          for (let i in res) {
            let temp = {
              value: '',
              label: '',
            };
            temp.value = res[i].line;
            temp.label = res[i].line + "号线";
            this.optionsForStation.push(temp);
          }
          //alert(JSON.stringify(this.optionsForStation));
        }
      ).catch(err => {
        console.log("Error");
      })
    },
  },
  mounted() {
    window.vue = this;
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
    this.drawChart();
    this.drawPeakChart();
    this.getAllStationInfo();
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
}
</script>

<style scoped>
h1{
  color: #3fdcdc;
  margin-bottom: 2rem;
}
.select{
  margin-bottom: 2rem;
}
</style>
