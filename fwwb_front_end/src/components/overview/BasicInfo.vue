<template>
  <div id="basic">
    <div class="card-item">

      <el-row class="row">
        <el-col :span="9">
          <h2 class="nowTime">当前时间:</h2>
        </el-col>
        <el-col :span="11">
          <div class="nowTime">{{nowTime.date}}</div>
          <div class="nowTime">{{nowTime.time}}</div>
        </el-col>
      </el-row>
      <div class="water-level-chart-details">
        今日预计人流量<span>{{this.number}}</span>人次
      </div>
      <div class="chart-container">
        <dv-water-level-pond :config="config" />
      </div>
    </div>

  </div>

</template>
<script>
export default {
  name: "BasicInfo",
  data(){
    return{
      nowTime:{
        date:'',
        time:'',
      },
      config: {
        data: [45],
        shape: 'round',
        waveHeight: 25,
        waveNum: 2
      },
      // congig_wop:{
      //   //水位图
      //   data: [66],
      // },
      number:'300',
    }
  },
  methods:{
    timeFormat(timeStamp) {
      let year = new Date(timeStamp).getFullYear();
      let month =new Date(timeStamp).getMonth() + 1 < 10? "0" + (new Date(timeStamp).getMonth() + 1): new Date(timeStamp).getMonth() + 1;
      let date =new Date(timeStamp).getDate() < 10? "0" + new Date(timeStamp).getDate(): new Date(timeStamp).getDate();
      let hh =new Date(timeStamp).getHours() < 10? "0" + new Date(timeStamp).getHours(): new Date(timeStamp).getHours();
      let mm =new Date(timeStamp).getMinutes() < 10? "0" + new Date(timeStamp).getMinutes(): new Date(timeStamp).getMinutes();
      let ss =new Date(timeStamp).getSeconds() < 10? "0" + new Date(timeStamp).getSeconds(): new Date(timeStamp).getSeconds();
      this.nowTime.date = year + "年" + month + "月" + date +"日";
      this.nowTime.time=hh+":"+mm+':'+ss
    },
    nowTimes(){
      this.timeFormat(new Date());
      setInterval(this.nowTimes,1000);
      this.clear()
    },
    clear(){
      clearInterval(this.nowTimes)
      this.nowTimes = null;
    },
  },
  mounted() {
    // 获取当前时间
    this.nowTimes();
    // 天气
    window.WIDGET = {
      CONFIG: {
        "layout": 1,
        "width": 450,
        "height": 150,
        "background": 1,
        "dataColor": "FFFFFF",
        "borderRadius": 5,
        "key": "9XTAGJuJaL"
      }
    };
    let script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://apip.weatherdt.com/standard/static/js/weather-standard-common.js?v=2.0';
    // document.getElementsByTagName('head')[0].appendChild(script);
    document.body.appendChild(script);
  }
}
</script>

<style lang="less">
#basic {
  display: flex;
  justify-content: space-between;
  height:90%;
  width:30%;


  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    border-right: 2px solid rgba(1, 153, 209, .5);
    margin-left: 5%;
    margin-top:4%;
    width: 100%;
    height:100%;
    display: flex;
    flex-direction: column;
  }
  .water-level-chart-details {
    height: 15%;
    display: flex;
    justify-content: center;
    font-size: 17px;
    align-items: flex-end;
    margin-top: 4%;

    span {
      font-size: 35px;
      font-weight: bold;
      color: #58a1ff;
      margin: 0 5px;
      margin-bottom: -5px;
    }
  }

  .chart-container {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 4%;
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
  .nowTime{
    color: white;
    font-size: 25px;
  }
  .row{
    margin-top: 3rem;
  }

}

</style>
