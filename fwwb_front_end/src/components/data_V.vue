<template>
  <div>
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
      <el-col :span="8">
        <dv-digital-flop :config="config" style="width:200px;height:50px;" />
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内入站人数：</h1>
      </el-col>
      <el-col :offset="7" :span="8">
        <h1 style=color:#3fdcdc;>进度池</h1>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :offset="2" :span="14">
        <dv-scroll-ranking-board :config="config_rank" style="width:750px;height:400px" />
      </el-col>
      <el-col :span="8">
        <dv-percent-pond :config="config" style="width:200px;height:100px;" />
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内出站人数：</h1>
      </el-col>
      <el-col :offset="7" :span="8">
        <h1 style=color:#3fdcdc;>人员比例</h1>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="2" :span="14">
        <dv-scroll-ranking-board :config="config_rank" style="width:750px;height:400px" />
      </el-col>
      <el-col :span="8">
        <dv-active-ring-chart :config="config" style="width:200px;height:200px" />
      </el-col>
    </el-row>
    <span class="spanTitle">OD分析</span>
    <div id="OD_Analyze" style="width:100%;height:400px"></div>
  </div>

</template>

<script>
export default {
  name: "dataV",
  data() {
    return {
      config : {
        number: [100],
        content: '{nt}个'
      },
      config_rank:{
        carousel: "single",
        rowNum: 6,
        waitTime: 10000,
        data: [
          {
            name: '周口',
            value: 55123
          },
          {
            name: '南阳',
            value: 12022
          },
          {
            name: '西峡',
            value: 78932
          },
          {
            name: '驻马店',
            value: 63411
          },
          {
            name: '新乡',
            value: 44231
          },
          {
            name: '周口',
            value: 55
          },
          {
            name: '南阳',
            value: 120
          },
          {
            name: '西峡',
            value: 78
          },
          {
            name: '驻马店',
            value: 66
          },
          {
            name: '新乡',
            value: 80
          },
          {
            name: '信阳',
            value: 45
          },
          {
            name: '漯河',
            value: 29
          }
        ],
        unit: '人',
        valueFormatter ({ value }) {
          console.warn(arguments)
          const reverseNumber = (value + '').split('').reverse()
          let valueStr = ''
          while (reverseNumber.length) {
            const seg = reverseNumber.splice(0, 3).join('')
            valueStr += seg
            if (seg.length === 3) valueStr += ','
          }
          return valueStr.split('').reverse().join('')
        }
      },
      value1: new Date(2016, 9, 10, 18, 40),
      value2: new Date(2016, 9, 10, 18, 40),
      options: [{
        value: '选项1',
        label: '1号线'
      }, {
        value: '选项2',
        label: '2号线'
      }, {
        value: '选项3',
        label: '4号线'
      }, {
        value: '选项4',
        label: '6号线'
      }, {
        value: '选项5',
        label: '9号线'
      }],
      value: ''
    }
  },
  methods:{
    softwareRangeInit(){
      let barChart = this.$echarts.init(document.getElementById("OD_Analyze"));
      let xdata=['12a', '1a', '2a', '3a', '4a', '5a', '6a', '7a', '8a', '9a','10a','11a', '12p', '1p', '2p', '3p', '4p', '5p', '6p', '7p', '8p', '9p', '10p', '11p'];
      let ydata=['Saturday', 'Friday', 'Thursday', 'Wednesday', 'Tuesday', 'Monday', 'Sunday'];
      let zdata=[[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];

    let barOption = {
        tooltip: {},
        visualMap: {   //颜色图例
          max: 100,    //允许显示的最大值
          inRange: {
            color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
          }
        },
        xAxis3D: {
          type: 'category',
          name:'进站站点',
          data: xdata,
        },
        yAxis3D: {
          type: 'category',
          name:'出站站点',
          data: ydata,
        },
        zAxis3D: {
          type: 'value',
          name:'人次',
          axisLabel: {
            show: true,
            interval: 'auto',
            formatter: '{value}%'
          },
        },
        grid3D: {
          boxWidth: 300,
          boxDepth: 150,
          viewControl: {
            // beta:0,   //调整倾斜角度
            //  projection: 'orthographic'
          },
          light: {
            main: {
              intensity: 1.2,
              shadow: true
            },
            ambient: {
              intensity: 0.3
            }
          }
        },
        series: [{
          type: 'bar3D',
          name:'OD分析图',
          data: zdata.map(function (item) {
            return {
              value: [item[0], item[1], item[2]],
            }
          }),
          shading: 'lambert',
          label: {
            textStyle: {
              fontSize: 16,
              borderWidth: 1
            }
          },
          emphasis: {
            label: {
              textStyle: {
                fontSize: 20,
                color: '#900'
              }
            },
            itemStyle: {
              color: '#900'
            }
          }
        }]
      }
      barChart.setOption(barOption);
    }
  },
  mounted() {
    window.vue=this;
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
    this.softwareRangeInit();
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
}
</script>
<style scoped>
el-row {
  margin-bottom: 20px;
  /*&:last-child {*/
  /*   margin-bottom: 0;*/
  /* }*/
}
el-col {
  border-radius: 4px;
}
.configRank>>>name{
  color:black
}
</style>
