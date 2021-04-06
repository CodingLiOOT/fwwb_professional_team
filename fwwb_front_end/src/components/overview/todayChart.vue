<template>
  <div id="today">
    <div class="card-item">
      <div id="todayChart" :style="{width: '25rem', height: '25rem'}"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dayChart",
  methods:{
    dayChartInit(){
      let inChart = this.$echarts.init(document.getElementById('todayChart'))
      inChart.setOption({
        title:{
          text: '当天总人次',
          bottom:0,
          left:'center',
          textStyle:{
            color:'white',
          }
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
          textStyle:{
            color: '#ffffff'//字体颜色
          },
        },
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisPointer: {
              type: 'shadow'
            },
            axisLabel:{
              show: true,
              textStyle: {
                color: '#ffffff'
              }
            },
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
            axisLabel: {
              textStyle: {
                color: '#ffffff'
              }
            },
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
  },
  mounted() {
    this.dayChartInit();
  }
}
</script>

<style lang="less">
#today {
  //display: flex;
  justify-content: space-between;
  height:90%;
  width:40%;


  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    border-right: 2px solid rgba(1, 153, 209, .5);
    //margin-left: 18%;
    //margin-top:4%;
    margin:auto;
    width: 100%;
    height:100%;
    //display: flex;
    //flex-direction: column;
  }

}

</style>
