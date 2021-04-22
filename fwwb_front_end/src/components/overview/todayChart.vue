<template>
  <div id="today">
    <div class="card-item">
      <div id="todayChart" :style="{width: '27rem', height: '27rem'}"></div>
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
          left:'150',
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
            data: ['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
              '18:00', '19:00', '20:00', '21:00', '22:00'],
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
        visualMap: {
          show: false,
          dimension: 0,
          pieces: [{
            lte: 1,
            color: 'green'
          }, {
            gt: 1,
            lte: 2,
            color: 'red'
          }, {
            gt: 2,
            lte: 12,
            color: 'green'
          }, {
            gt: 12,
            lte: 13,
            color: 'red'
          }, {
            gt: 13,
            let:21,
            color: 'green'
          }]
        },
        series: [
          {
            name: '人次',
            type: 'line',
            data: [92, 604, 910, 464, 329, 345, 368, 358, 375, 364, 448, 662,827,485,369,333,119],
            markArea: {
              itemStyle: {
                color: 'rgba(255, 173, 177, 0.4)'
              },
              data: [ [{
                name: '早高峰',
                xAxis: '7:00'
              }, {
                xAxis: '8:00'
              }], [{
                name: '晚高峰',
                xAxis: '18:00'
              }, {
                xAxis: '19:00'
              }] ]
          }},
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
  display: flex;
  justify-content: space-between;


  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    margin-left: 2rem;
    margin-top: 1rem;
    display: flex;
    flex-direction: column;
  }
  .row{
    //margin-top: 3rem;
  }

}

</style>
