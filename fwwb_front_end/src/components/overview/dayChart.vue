<template>
  <div id="day">
    <div class="card-item">
      <div id="dayChart" :style="{width: '27rem', height: '27rem'}"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dayChart",
  methods:{
    dayChartInit(){
      let inChart = this.$echarts.init(document.getElementById('dayChart'))
      inChart.setOption({
        title:{
          text: '未来7天总人次',
          bottom:0,
          left:'center',
          textStyle:{
            color:'white',
          }
        },
        grid:{
          x:60,
          y:60,
          width:300,
          height:300,
          x2:60,
          y2:60,
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
            data: ['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'],
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
            min:5000,
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
            data: [7458,7412,7504, 7711, 6375, 6172, 7612, 7471]
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
#day {
  display: flex;
  justify-content: space-between;


  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    margin-left: 0.5rem;
    margin-top: 1rem;
    margin-right: 1rem;
    display: flex;
    flex-direction: column;
  }
  .row{
    //margin-top: 3rem;
  }

}

</style>
