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
      <el-col :span="16">
        <dv-scroll-ranking-board :config="configRank" v-html="this.configRank.data.value" v-text="this.configRank.data.value"style="width:500px;height:300px" />
      </el-col>
      <el-col :span="8">
        <dv-percent-pond :config="configPercent" style="width:200px;height:100px;" />
      </el-col>
    </el-row>
        <el-row :gutter="10">
      <el-col :span="16">
        <dv-scroll-ranking-board :config="configRank" v-html="this.configRank.data.value" v-text="this.configRank.data.value"style="width:500px;height:300px" />
      </el-col>
      <el-col :span="8">
        <dv-active-ring-chart :config="chart_data" style="width:300px;height:300px" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
  const fullWidth = 300
  const borderGap = 3
  const borderWidth = 3
  const usefulWidth = fullWidth - (borderGap + borderWidth) * 2

  const pieceLength = [0.25, 0.5, 0.25]
  const pieceGap = 3

  const lineDash = pieceLength
    .map(l => [usefulWidth * l, pieceGap])
    .reduce((all, current) => [...all, ...current], [])
    export default {
        name: "dataV",
      data() {
        return {
          config : {
            number: [100],
            content: '{nt}个'
          },
          configRank: {
            data: [
              {
                name: "<div style='color:black'>周口</div>",
                value: "<p style='color:black'>55</p>",
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
              },
            ],
          },
          configPercent:{
            value: 100,
            colors: ['#01c4f9', '#c135ff'],
            // 百分比调为透明色
            textColor:'rgba(1,196,249,0)',
            lineDash
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
          value: '',
          chart_data:{
            /*radius: '40%',
            activeRadius: '45%',*/
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
            /*lineWidth: 30,*/
            digitalFlopStyle: {
              fontSize: 30,
              fill: '#7d7777'
            }
          },
        }
      },
      created() {

      }
    }
</script>

<style scoped>
  .el-row {
    margin-bottom: 60px;
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