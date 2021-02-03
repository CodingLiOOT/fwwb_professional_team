<template>
  <div id="app">
    <div id="main" style="width: 600px;height:400px;"></div>
  </div>
</template>

<script>
  export default {
    name: "Echarts_station",
    methods: {
      drawChart() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById("main"),'dark');
        let base = +new Date(1988, 9, 3);
        let oneDay = 24 * 3600 * 1000;

        let data = [[base, Math.random() * 300]];

        for (let i = 1; i < 20000; i++) {
          let now = new Date(base += oneDay);
          data.push([
            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
            Math.round((Math.random() - 0.5) * 20 + data[i - 1][1])
          ]);
        }
        // 指定图表的配置项和数据
        let option = {
          tooltip: {
            trigger: 'axis',
            position: function (pt) {
              return [pt[0], '10%'];
            }
          },
          title: {
            left: 'center',
            text: '大数据量面积图',
          },
          toolbox: {
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              restore: {},
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'time',
            boundaryGap: false
          },
          yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
          },
          dataZoom: [{
            type: 'inside',
            start: 0,
            end: 20
          }, {
            start: 0,
            end: 20
          }],
          series: [
            {
              name: '模拟数据',
              type: 'line',
              smooth: true,
              symbol: 'none',
              areaStyle: {},
              data: data
            }
          ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      }
    },
    mounted() {
      this.drawChart();
    }
  };
</script>

<style scoped>

</style>
