<template>
  <div id="cards">
    <div class="card-item">
      <div id="lineProportion" :style="{width: '15rem', height: '25rem'}"></div>
<!--      <div id="ageProportion" :style="{width: '30rem', height: '25rem'}"></div>-->
    </div>







  </div>

</template>

<script>
export default {
  name: "Ring",
  data(){

  },
  methods:{
    lineProportionInit(){
      let linePropChart = this.$echarts.init(document.getElementById('lineProportion'))
      linePropChart.setOption({
        title:[{
          text:'线路占比',
          right:'15%',
          top:'12%',
          textStyle:{
            color:'white',
          }
        },
          {
            text:'年龄结构',
            left:'15%',
            bottom:'28%',
            textStyle:{
              color:'white',
            }
          }],
        // legend:[
        //   {
        //     right:'0',
        //     top:'25%',
        //     width:'50%',
        //     textStyle : {
        //       color : "#B8B6B8"
        //     },
        //     data:['Line 1','Line 2','Line 3','Line 4','Line 5','Line 6','Line 7','Line 8','Line 9'],
        //   },
        //   {
        //     left:'0',
        //     bottom:'12%',
        //     width:'50%',
        //     textStyle : {
        //       color : "#B8B6B8"
        //     },
        //     data : [ '青年', '未成年','中年','老年' ],
        //     x : 'right'
        //   }
        // ],
        // legend: {
        //   right:'0',
        //   top:'25%',
        //   width:'50%',
        //   textStyle: {
        //     color: '#3fdcdc'
        //   },
        // },
        series: [
          {
            name: '面积模式',
            type: 'pie',
            radius: [7, 50],
            center: ['50%', '25%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              {value: 20, name: 'Line 1'},
              {value: 36, name: 'Line 2'},
              {value: 30, name: 'Line 3'},
              {value: 28, name: 'Line 4'},
              {value: 26, name: 'Line 5'},
              {value: 22, name: 'Line 5'},
              {value: 16, name: 'Line 7'},
              {value: 16, name: 'Line 8'},
              {value: 16, name: 'Line 9'}
            ]
          },
          {
            name: '面积模式',
            type: 'pie',
            radius: [7, 50],
            center: ['50%', '75%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              {value: 40, name: '青年'},
              {value: 30, name: '未成年'},
              {value: 20, name: '中年'},
              {value: 10, name: '老年'},
            ]
          }
        ]
      });
    },
    ageProportionInit(){
      let linePropChart = this.$echarts.init(document.getElementById('ageProportion'))
      linePropChart.setOption({
        legend: {
          top: 'bottom',
          textStyle: {
            color: '#3fdcdc'
          }
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: '面积模式',
            type: 'pie',
            radius: [7, 50],
            center: ['75%', '75%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              {value: 40, name: '青年'},
              {value: 30, name: '未成年'},
              {value: 20, name: '中年'},
              {value: 10, name: '老年'},
            ]
          }
        ]
      });
    },
  },
  mounted() {
    this.lineProportionInit();
    this.ageProportionInit();
  }
}
</script>
<style lang="less">
#cards {
  display: flex;
  justify-content: space-between;
  height: 45%;

  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    border-right: 2px solid rgba(1, 153, 209, .5);
    margin-left: 5%;
    margin-top:5%;
    width: 100%;
    display: flex;
    flex-direction: column;
  }

  .card-header {
    display: flex;
    height: 20%;
    align-items: center;
    justify-content: space-between;

    .card-header-left {
      font-size: 18px;
      font-weight: bold;
      padding-left: 20px;
    }

    .card-header-right {
      padding-right: 20px;
      font-size: 40px;
      color: #03d3ec;
    }
  }

  .ring-charts {
    height: 55%;
  }

  .card-footer {
    height: 25%;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }

  .card-footer-item {
    padding: 5px 10px 0px 10px;
    box-sizing: border-box;
    width: 40%;
    background-color: rgba(6, 30, 93, 0.7);
    border-radius: 3px;

    .footer-title {
      font-size: 15px;
      margin-bottom: 5px;
    }

    .footer-detail {
      font-size: 20px;
      color: #1294fb;
      display: flex;
      font-size: 18px;
      align-items: center;

      .dv-digital-flop {
        margin-right: 5px;
      }
    }
  }
}
</style>
