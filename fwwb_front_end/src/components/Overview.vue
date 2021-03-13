<template>
  <div>
    <el-row :gutter="5">
      <el-col :span="5">
        <h2 class="nowTime">当前时间</h2>
        <div class="nowTime">{{nowTime.date}}</div>
        <div class="nowTime">{{nowTime.time}}</div>
      </el-col>
      <el-col :span="7">
        <div id="weather-v2-plugin-standard"></div>
      </el-col>
      <el-col :span=5>
<!--        人数-->
        <h1 style=color:#FFFFFF;>当前总人次：</h1>
        <dv-digital-flop :config="config" style="width:10rem;height:2.5rem;margin-left: 3rem" /><
      </el-col>
      <el-col :span=5>
<!--        水位图-->
        <dv-percent-pond :config="config" style="width:15rem;height:8rem;margin-top: 3rem;margin-left: 2rem" />
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>当天总人次：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>热力拓扑图：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>线路占比：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>入站站点排行表：</h1>
      </el-col>
    </el-row>
    <el-row :gutter="2">
      <el-col :span="4">
        <div id="dayChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :span="10">
        <!--        热力拓扑图-->
<<<<<<< HEAD
        <!--        以下div仅为占位所用-->
        <div style="width: 25rem;height: 25rem;background-color: #41464e;margin-left: 8rem"></div>
=======
<!--        <div id="map" :style="{width: '25rem', height: '25rem'}"></div>-->
<!--      </el-col>-->
      <div style="width: 25rem;height: 25rem;background-color: #41464e;margin-left: 8rem"></div>
>>>>>>> f6e8a7439a220f04605edfa2bd76e35a50242d84
      </el-col>
      <el-col :span="5">
        <!--        线路占比-->
        <div id="lineProportion" :style="{width: '30rem', height: '25rem'}"></div>

      </el-col>
      <el-col :span="5">
        <!--        前十入站轮播表-->
        <dv-scroll-ranking-board :config="config" style="width:25rem;height:25rem;margin-left: 15rem" />
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>未来七天总人次：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>热力OD图：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>年龄结构：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>出站站点排行表：</h1>
      </el-col>
    </el-row>
    <el-row :gutter="2">
      <el-col :span="4">
        <div id="weekChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :span="10">
        <!--        OD热力图-->
<<<<<<< HEAD
        <!--        以下div仅为占位所用-->
        <div style="width: 25rem;height: 25rem;background-color: #41464e;margin-left: 8rem"></div>
=======
        <div id="OD_Analyze" :style="{width: '55rem', height: '25rem'}"></div>
>>>>>>> f6e8a7439a220f04605edfa2bd76e35a50242d84
      </el-col>
      <el-col :span="5">
        <!--        年龄结构-->
        <div id="ageProportion" :style="{width: '30rem', height: '25rem'}"></div>

      </el-col>
      <el-col :span="5">
        <!--        前十出站轮播表-->
        <dv-scroll-ranking-board :config="config" style="width:25rem;height:25rem;margin-left: 15rem" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
name: "Overview",
  data() {
    return {
      nowTime:{
        date:'',
        time:'',
      },
      //dataV
      config: {
        //数字翻牌器
        number: [10000],
        content: '{nt}',
        textAlign:'right',
        //进度条
        value: 66,
        //排名轮播表
        data: [
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
        ]
      }
    };
  },
  methods: {
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
    dayChartInit(){
      let inChart = this.$echarts.init(document.getElementById('dayChart'))
      inChart.setOption({
        title:{
          //text: '当天总人次',
          bottom:0,
          left:'center'
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
    weekChartInit(){
      let inChart = this.$echarts.init(document.getElementById('weekChart'))
      inChart.setOption({
        title:{
         // text: '当天总人次',
          bottom:0,
          left:'center'
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

    lineProportionInit(){
      let linePropChart = this.$echarts.init(document.getElementById('lineProportion'))
      linePropChart.setOption({
        legend: {
<<<<<<< HEAD
          show:true,
          data: ['1号线', '2号线', '3号线', '4号线', '5号线', '6号线', '7号线', '8号线', '9号线', '10号线', '11号线', '12号线', '13号线'],
          left:'left',
          width:100,
          textStyle:{
            color: '#ffffff'//字体颜色
          },
=======
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
>>>>>>> f6e8a7439a220f04605edfa2bd76e35a50242d84
        },
        series: [
          {
            name: '面积模式',
            type: 'pie',
            radius: [15, 100],
            center: ['50%', '50%'],
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
          }
        ]
      });
    },

    ageProportionInit(){
      let linePropChart = this.$echarts.init(document.getElementById('ageProportion'))
      linePropChart.setOption({
        legend: {
<<<<<<< HEAD
          show:true,
          data: ['少年', '青年', '中年', '老年'],
          left:'left',
          width:100,
          textStyle:{
            color: '#ffffff'//字体颜色
          },
=======
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
>>>>>>> f6e8a7439a220f04605edfa2bd76e35a50242d84
        },
        series: [
          {
            name: '面积模式',
            type: 'pie',
            radius: [15, 100],
            center: ['50%', '50%'],
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

    ODChartInit(){
      let ODChart = this.$echarts.init(document.getElementById('OD_Analyze'))
      ODChart.setOption({
          title: {
            text: "站点OD分析",
            left: "center",
            textStyle: {
              color: '#3fdcdc'
            }
          },
          tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'line',        // 默认为直线，可选为：'line' | 'shadow'
            },
            //     formatter: function(params){return Math.max(params.value,-params.value)}

            formatter: function (params) {
              return params[0].name +
                "<br>进站人数：" + params[0].value +
                "<br>出站人数：" +  -params[1].value;
            }
          },
          legend: {
            data:['进站人数', '出站人数'],
            left: "right",
            textStyle: {
              color: '#3fdcdc'
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          yAxis : [
            {
              type : 'value',
              axisLabel: {
                textStyle: {
                  color: '#3fdcdc'
                }
              }
            }
          ],
          xAxis : [
            {
              type : 'category',
              axisTick : {show: false},
              data : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40'
                ,'41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60','61','62','63','64','65','66','67','68','69','70','71','72','73','74','75','76','77','78','79',
                '80','81','82','83','84','85','86','87','88','89','90','91','92','93','94','95','96','97','98','99','100','101','102','103','104','105','106','107','108','109','110','111','112','113','114','115',
                '116','117','118','119','120','121','122','123','124','125','126','127','128','129','130','131','132','133','134','135','136','137','138','139','140','141','142','143','144','145','146','147','148'
                ,'149','150','151','152','153','154','155','156','157','158','159','160','161','162','163'],
              axisLabel: {
                interval: 5,      // 坐标轴刻度标签的显示间隔
                // rotate: 40,        // 标签倾斜的角度
                textStyle: {
                  color: '#3fdcdc'
                }
              }
            }
          ],
          series : [

            {
              name:'进站人数',
              type:'bar',
              stack: '总人数',
              label: {
                normal: {
                  show: false
                }
              },
              data:[400, 241,360, 320, 302, 341, 374, 390, 450, 420]
            },
            {
              name:'出站人数',
              type:'bar',
              stack: '总人数',
              label: {
                normal: {
                  show: false,
                  formatter: function(params){return -params.value}
                }
              },
              data:[-120, -180,-120, -120, -132, -101, -134, -190, -230, -210]
            }
          ]
        }
      );
    }
  },
  mounted() {
    // 获取当前时间
    this.nowTimes();
    // 天气
    window.WIDGET = {
      CONFIG: {
        "layout": 1,
        "width": "450",
        "height": "150",
        "background": 1,
        "dataColor": "FFFFFF",
        "language": "zh",
        "borderRadius": "10",
        "city": "CN101010100",
        "key": "kAccMmzpUi"
      }
    };
    let script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://apip.weatherdt.com/standard/static/js/weather-standard-common.js?v=2.0';
    document.getElementsByTagName('head')[0].appendChild(script);
    // 画图
    this.dayChartInit();
    this.weekChartInit();
    this.lineProportionInit();
    this.ODChartInit();
    this.ageProportionInit();
    // 设置背景
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
  }

}
</script>

<style scoped>
  .nowTime{
    color: white;
    font-size: 25px;
  }
</style>
