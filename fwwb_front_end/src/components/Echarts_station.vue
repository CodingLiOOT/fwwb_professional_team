<template>
  <div id="app" >
    <el-row :gutter="5">
      <!--      级联选择器-->
      <el-col :span="4">
        <el-cascader
          v-model="valueStation"
          :options="optionsForStation"
          :props="{ expandTrigger: 'hover' }"
          @change="handleChange"></el-cascader>
      </el-col>
      <el-col :span="4">
        <el-select v-model="value" filterable placeholder="请选择">
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
          time:[],
          entranceNum: [],
          outboundNum: [],
          teen: '',
          middle: '',
          old:'',
          underage:'',
        },
        value:'',
        value1: '',
        valueStation:'',
        optionsForStation:[{
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
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} ({d}%)'
          },
          legend: {
            bottom: 10,
            left: 'center',
            data: ['0-17岁', '18-45岁', '46-69岁','70岁以上']
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
      // 点击查询按钮以后
      searchStation(){
        this.$API.p_Station({
          stationName: '47',
          startTime: this.value1[0],
          endTime: this.value1[1],
          granularity: this.value,
        })
          .then(
            res => {
              this.result={};
              for(let item in res.line){
                this.result.time.push(res.stationData[item].time);
                this.result.entranceNum.push(res.stationData[item].entranceNum);
                this.result.outboundNum.push(res.stationData[item].outboundNum);
              }
              this.result.teen=res.age.teen;
              this.result.middle=res.age.middle;
              this.result.old=res.age.old;
              this.result.underage=res.age.underage;
              this.drawChart();
            }
          )
          .catch(err => {

          })
      },
      getAllStationInfor(){
        this.$API.g_getAllStationInfor().then(res =>{
            this.optionsForStation={};
            for (let i in res){
              let temp={
                value:'',
                label:'',
                children: []
              };
              temp.value=res[i].line;
              temp.label=res[i].line;
              for (let j in res[i].station){
                let child={
                  value:'',
                  label:''
                };
                child.value=res[i].station[j];
                child.label=res[i].station[j];
                temp.children.push(child);
              }
              this.optionsForStation.push(temp);
            }
          }
        ).catch(err => {
            console.log("Error");
        })
      },
      handleChange(value) {
        console.log(value);
      }
    },
    mounted() {
      window.vue=this;
      document.querySelector('body').setAttribute('style', 'background-color:#16191D')
      this.drawChart();
      this.getAllStationInfor();
    },
    beforeDestroy() {
      document.querySelector('body').removeAttribute('style')
    },
  };
</script>

<style scoped>
</style>

