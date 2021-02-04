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
        <el-time-picker
          v-model="value1"
          :picker-options="{
      selectableRange: '18:30:00 - 20:30:00'
    }"
          placeholder="任意时间点">
        </el-time-picker>
        <el-time-picker
          arrow-control
          v-model="value2"
          :picker-options="{
      selectableRange: '18:30:00 - 20:30:00'
    }"
          placeholder="任意时间点">
        </el-time-picker>
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
      <el-col :offset="2" :span="14">
      <!--进站人数折线图-->
        <div id="inChart" style="width: 600px;height:400px;"></div>
      </el-col>
      <el-col :span="8">
        <!--年龄结构图-->
        <dv-active-ring-chart :config="config" style="width:200px;height:200px" />
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内出站人数：</h1>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="2" :span="14">
        <!--出站人数折线图-->
        <div id="outChart" style="width: 600px;height:400px;"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Echarts_station",
    data() {
      return {
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
          value: '选项1',
          label: '一天内'
        }, {
          value: '选项2',
          label: '一周内'
        }],
      }},
    methods: {
      drawChart() {
        // 基于准备好的dom，初始化echarts实例
        let inChart = this.$echarts.init(document.getElementById("inChart"));
        let outChart = this.$echarts.init(document.getElementById("outChart"));
       //map类型数据
        /* let data = [["2000-06-05",116],["2000-06-06",129],["2000-06-07",135],["2000-06-08",86],["2000-06-09",73],["2000-06-10",85],["2000-06-11",73],["2000-06-12",68],["2000-06-13",92],["2000-06-14",130],["2000-06-15",245],["2000-06-16",139],["2000-06-17",115],["2000-06-18",111],["2000-06-19",309],["2000-06-20",206],["2000-06-21",137],["2000-06-22",128],["2000-06-23",85],["2000-06-24",94],["2000-06-25",71],["2000-06-26",106],["2000-06-27",84],["2000-06-28",93],["2000-06-29",85],["2000-06-30",73],["2000-07-01",83],["2000-07-02",125],["2000-07-03",107],["2000-07-04",82],["2000-07-05",44],["2000-07-06",72],["2000-07-07",106],["2000-07-08",107],["2000-07-09",66],["2000-07-10",91],["2000-07-11",92],["2000-07-12",113],["2000-07-13",107],["2000-07-14",131],["2000-07-15",111],["2000-07-16",64],["2000-07-17",69],["2000-07-18",88],["2000-07-19",77],["2000-07-20",83],["2000-07-21",111],["2000-07-22",57],["2000-07-23",55],["2000-07-24",60]];
        let dateList = data.map(function (item) {
          return item[0];
        });
        let valueList = data.map(function (item) {
          return item[1];
        });*/
        let dateList = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
        let inValueList = [150, 230, 224, 218, 135, 147, 260];
        let outValueList = [350, 230, 224, 218, 135, 147, 260];

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
        // 使用刚指定的配置项和数据显示图表。
        inChart.setOption(optionIn);
        outChart.setOption(optionOut);
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

