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
    mounted() {
      document.querySelector('body').setAttribute('style', 'background-color:#16191D')
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
</style>
