<template>
  <div id="echarts">
      <dv-border-box-11 title="客流预测分析">
        <div class="main-content">
          <div class="block-left-right-content">
            <el-row :gutter="5" style="margin-top: 5rem; margin-left: 2rem">
              <el-col :span="4">
                <el-cascader
                  v-model="selectedValue"
                  :options="optionsForStation"
                  :props="{ expandTrigger: 'hover' }"
                  @change="handleChange"></el-cascader>
              </el-col>
              <el-col :span="4">
                <el-select v-model="granularity" filterable placeholder="请选择时间粒度">
                  <el-option
                    v-for="item in optionsForGranularity"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span=10>
                <el-date-picker
                  v-model="timeValue"
                  type="datetimerange"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  :default-time="['12:00:00']">
                </el-date-picker>
              </el-col>
              <el-col :span=5>
                <el-button @click="searchStation()">查询</el-button>
              </el-col>
            </el-row>

            <el-row>
              <div id="od" :style="{width:'95rem',height:'20rem',marginLeft:'1rem'}"></div>
            </el-row>
            <el-row>
              <el-col :span="7">
                <div class="pieChartBoard">
                  <el-row>
                    <el-col>
                      <div class="board-title">年龄占比：</div>
                    </el-col>
                    <el-col>
                      <div id="ageLine" :style="{width: '27rem', height: '20rem'}"></div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="8">
                <div class="lineChartBoard">
                  <el-row>
                    <el-col>
                      <div class="board-title">时间段内入站人数：</div>
                    </el-col>
                    <el-col >
                      <div id="inChart" :style="{width: '27rem', height: '20rem'}"></div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="8">
                <div class="lineChartBoard">
                  <el-row>
                    <el-col>
                      <div class="board-title">时间段内出站人数：</div>
                    </el-col>
                    <el-col>
                      <div id="outChart" :style="{width: '27rem', height: '20rem'}"></div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </dv-border-box-11>
  </div>


</template>

<script>
export default {
  name: "Echarts_station",
  data() {
    return {
      RECORDS: [
        {
          "out_station": "9",
          "num": "3"
        },
        {
          "out_station": "12",
          "num": "1"
        },
        {
          "out_station": "15",
          "num": "3"
        },
        {
          "out_station": "27",
          "num": "1"
        },
        {
          "out_station": "30",
          "num": "1"
        },
        {
          "out_station": "33",
          "num": "2"
        },
        {
          "out_station": "34",
          "num": "1"
        },
        {
          "out_station": "40",
          "num": "1"
        },
        {
          "out_station": "41",
          "num": "2"
        },
        {
          "out_station": "42",
          "num": "2"
        },
        {
          "out_station": "47",
          "num": "3"
        },
        {
          "out_station": "63",
          "num": "7"
        },
        {
          "out_station": "64",
          "num": "3"
        },
        {
          "out_station": "65",
          "num": "1"
        },
        {
          "out_station": "74",
          "num": "4"
        },
        {
          "out_station": "80",
          "num": "1"
        },
        {
          "out_station": "81",
          "num": "1"
        },
        {
          "out_station": "88",
          "num": "1"
        },
        {
          "out_station": "89",
          "num": "25"
        },
        {
          "out_station": "107",
          "num": "4"
        },
        {
          "out_station": "108",
          "num": "3"
        },
        {
          "out_station": "110",
          "num": "3"
        },
        {
          "out_station": "115",
          "num": "2"
        },
        {
          "out_station": "126",
          "num": "2"
        },
        {
          "out_station": "128",
          "num": "3"
        },
        {
          "out_station": "129",
          "num": "1"
        },
        {
          "out_station": "131",
          "num": "1"
        },
        {
          "out_station": "133",
          "num": "1"
        },
        {
          "out_station": "141",
          "num": "1"
        },
        {
          "out_station": "142",
          "num": "1"
        },
        {
          "out_station": "144",
          "num": "1"
        },
        {
          "out_station": "149",
          "num": "1"
        },
        {
          "out_station": "150",
          "num": "1"
        },
        {
          "out_station": "154",
          "num": "1"
        },
        {
          "out_station": "159",
          "num": "1"
        },
        {
          "out_station": "162",
          "num": "1"
        },
        {
          "out_station": "167",
          "num": "1"
        }
      ],
      val:'',
      selectedValue:'',
      granularity: '',
      timeValue: '',
      entranceData:{
        time: ['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'],
        timePro:[0,0,0,0,1,0,0],
        entranceNum: [374,347,379,388,390,395,391],
        morning:[46,44,45,49,46,47,46],
        evening:[42,40,46,45,43,47,44],
      },
      outboundData:{
        time: ['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'],
        timePro:[0,0,0,0,1,0,0],
        entranceNum: [318,291,320,332,336,352,358],
        morning:[39,37,38,42,39,42,42],
        evening:[36,34,38,39,37,42,40],
      },
      ageStructure:{
        time: ['4月18日','4月19日','4月20日','4月21日','4月22日','4月23日','4月24日'],
        teen: [120, 132, 101, 134, 90, 230, 210],
        middle:  [220, 182, 191, 234, 290, 330, 310],
        old: [150, 232, 201, 154, 190, 330, 410],
        underage:  [320, 332, 301, 334, 390, 330, 320],
      },
      optionsForStation: [{
        value: '1号线',
        label: '1号线',
        children: [{
          value: '1号站',
          label: '1号站',
        },
          {
            value: '2号站',
            label: '2号站',
          },
          {
            value: '3号站',
            label: '3号站',
          },
          {
            value: '4号站',
            label: '4号站',
          },],
      }],
      optionsForGranularity: [{
        value: '1',
        label: '小时'
      }, {
        value: '2',
        label: '天'
      }, {
        value: '3',
        label: '周'
      }, {
        value: '4',
        label: '月'
      },],
    }
  },
  methods: {
    initPie(){
      let ageLine = this.$echarts.init(document.getElementById('ageLine'));
      let t=['time'];
      for(let item in this.ageStructure.time){
        t.push(this.ageStructure.time[item]);
      }
      let under=['0-17岁'];
      for(let item in this.ageStructure.underage){
        under.push(this.ageStructure.underage[item]);
      }
      let y=['18-45岁'];
      for(let item in this.ageStructure.teen){
        y.push(this.ageStructure.teen[item]);
      }
      let mid=['46-69岁'];
      for(let item in this.ageStructure.middle){
        mid.push(this.ageStructure.middle[item]);
      }
      let o=['70岁以上'];
      for(let item in this.ageStructure.old){
        o.push(this.ageStructure.old[item]);
      }
      ageLine.setOption({
        dataset: {
          source: [
            t,under,y,mid,o
          ]
        },
        series: {
          id: 'pie',
          label: {
            formatter: '{b}:\n {@[' + 1 + ']} ({d}%)'
          },
          encode: {
            value: 1,
            tooltip:1,
          }
        }
      });
    },
    inChartInit(){
      // this.subwayLineInit();
      let inChart = this.$echarts.init(document.getElementById('inChart'))
      let myLengend=[];
      let seriesData=[];
      let f0=0,f1=0,f2=0,f3=0,f4=0,f5=0,f6=0;
      let m1=[],m2=[],e1=[],e2=[];
      let visual;
      // 时间粒度为小时
      if(this.granularity==1){
        this.entranceData.time=['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
          '18:00', '19:00', '20:00', '21:00', '22:00'];
        // this.entranceData.timePro=[0,0,0,0,1,0,0];
        this.entranceData.entranceNum=[3,8,9,5,6,6,4,4,3,5,5,5,7,8,4,6];
        // 初始化图例
        myLengend.push('总人次');
        // 初始化早晚高峰范围
        // for(let i in this.entranceData.timePro){
        //   if(this.entranceData.timePro[i]===31){
        //     m1.push(this.entranceData.time[i]);
        //   }
        //   else if(this.entranceData.timePro[i]===32){
        //     m2.push(this.entranceData.time[i]);
        //   }
        //   else if(this.entranceData.timePro[i]===41){
        //     e1.push(this.entranceData.time[i]);
        //   }
        //   else if(this.entranceData.timePro[i]===42){
        //     e2.push(this.entranceData.time[i]);
        //   }
        // }
        // 初始化数据
        let scope=[];
        for(let item in m1){
          let tempM1={
            name:'早高峰',
            xAxis:'',
          }
          let tempM2={
            xAxis:'',
          }
          tempM1.xAxis=m1[item];
          tempM2.xAxis=m2[item];
          let m=[];
          m.push(tempM1);
          m.push(tempM2);
          scope.push(m);
        }
        for(let item in e1){
          let tempE1={
            name:'晚高峰',
            xAxis:'',
          }
          let tempE2= {
            xAxis: '',
          }
          tempE1.xAxis=e1[item];
          tempE2.xAxis=e2[item];
          let e=[];
          e.push(tempE1);
          e.push(tempE2);
          scope.push(e);
        }
        visual= {
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
          // scope= [ [{
          //   name: '早高峰',
          //   xAxis: '7'
          // }, {
          //   name:'早高峰',
          //   xAxis: '9'
          // }], [{
          //   name: '晚高峰',
          //   xAxis: '17'
          // }, {
          //   xAxis: '19'
          // }], [{
          //   name: '早高峰',
          //   xAxis: '71'
          // }, {
          //   name:'早高峰',
          //   xAxis: '91'
          // }], ]
          // scope= [ [{
          //   name: '晚高峰',
          //   xAxis: '17'
          // }, {
          //   xAxis: '19'
          // }]]
          // alert("scope.size "+scope.length);
          // alert("scope[0] "+scope[0]);
          // alert("scope[0][0] "+scope[0][0]);
          // alert("scope[0][0].xAxis "+scope[0][0].xAxis);
          // alert("scope[0][1].xAxis "+scope[0][1].xAxis);
          seriesData= [
            {
              name: '总人次',
              type: 'line',
              smooth: true,
              data: this.entranceData.entranceNum,
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
              }
              // markArea: {
              //   itemStyle: {
              //     color: 'rgba(255, 173, 177, 0.4)'
              //   },
              //   data:scope,
              // }
            }
          ];

      }
      // 时间粒度为天
      else if(this.granularity==2||this.granularity=='天'){
        this.entranceData.time=['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'];
        this.entranceData.timePro=[0,0,0,0,1,0,0];
        this.entranceData.entranceNum=[374,347,379,388,390,395,391];
        this.entranceData.morning=[46,44,45,49,46,47,46];
        this.entranceData.evening=[42,40,46,45,43,47,44];

        // 初始化图例
        for(let item in this.entranceData.timePro){
          if(this.entranceData.timePro[item]===0&&f0===0){
            myLengend.push('工作日');
            f0=1;
          }
          else if(this.entranceData.timePro[item]===1&&f1===0){
            myLengend.push('周末');
            f1=1;
          }
          else if(this.entranceData.timePro[item]===2&&f2===0){
            myLengend.push('节假日');
            f2=1;
          }
        }
        myLengend.push('早高峰');
        myLengend.push('晚高峰');
        // 初始化数据
        for(let item in myLengend){
          let temp={
            name:'',
            type:'',
            stack:'',
            data:[],
          };
          if(myLengend[item]==="工作日"){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            temp.itemStyle={
              normal:{
                color:'rgb(30,115,190)'
              }
            }
            for(let i in this.entranceData.entranceNum){
              if(this.entranceData.timePro[i]===0){
                temp.data.push(this.entranceData.entranceNum[i]);
              }
              else{
                temp.data.push(0);
              }
            }
            seriesData.push(temp);
          }
          else if(myLengend[item]==='周末'){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            temp.itemStyle={
              normal:{
                color:'rgb(96,159,168)'
              }
            }
            for(let i in this.entranceData.entranceNum){
              if(this.entranceData.timePro[i]===1){
                temp.data.push(this.entranceData.entranceNum[i]);
              }
              else{
                temp.data.push(0);
              }
            }
            seriesData.push(temp);
          }
          else if(myLengend[item]==='节假日'){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            for(let i in this.entranceData.entranceNum){
              if(this.entranceData.timePro[i]===2){
                temp.data.push(this.entranceData.entranceNum[i]);
              }
              else{
                temp.data.push(0);
              }
            }
            seriesData.push(temp);
          }
          else if(myLengend[item]==='早高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.yAxisIndex='1';
            temp.data=this.entranceData.morning;
            seriesData.push(temp);
          }
          else if(myLengend[item]==='晚高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.yAxisIndex='1';
            temp.itemStyle={
              normal:{
                color:'rgb(206,166,47)'
              }
            }
            temp.data=this.entranceData.evening;
            seriesData.push(temp);
          }
        }
      }
      else{
        // 初始化图例
        myLengend.push('总人次');
        myLengend.push('早高峰');
        myLengend.push('晚高峰');
        // 初始化数据
        for(let item in myLengend){
          let temp={
            name:'',
            type:'',
            stack:'',
            data:[],
          };
          if(myLengend[item]==='总人次'){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            temp.data=this.entranceData.entranceNum;
            seriesData.push(temp);
          }
          else if(myLengend[item]==='早高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.data=this.entranceData.morning;
            seriesData.push(temp);
          }
          else if(myLengend[item]==='晚高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.data=this.entranceData.evening;
            seriesData.push(temp);
          }
        }
      }
      inChart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          },
          formatter: function (params) {
            let res=params[0].name+'</p></div>'
            for(let i=0;i<params.length;i++){
              if(params[i].data!==0){
                res+='<p>'+params[i].seriesName+': '+params[i].data+'</p>'
              }
            }
            return res;
          },
        },
        legend: {
          textStyle:{
            color: '#ffffff'//字体颜色
          },
          data: myLengend,
          left:'left',
          width:300,
        },
        visualMap:visual,
        xAxis: [
          {
            type: 'category',
            data: this.entranceData.time,
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
            axisLabel: {
              textStyle: {
                color: '#ffffff'
              }
            },
          },
          {
            type: 'value',
            axisLabel: {
              textStyle: {
                color: '#ffffff'
              }
            },
          },
        ],
        series:seriesData,
      },true);
    },
    outChartInit(){
      // this.subwayLineInit();
      let outChart = this.$echarts.init(document.getElementById('outChart'))
      let myLengend=[];
      let seriesData=[];
      let f0=0,f1=0,f2=0,f3=0,f4=0,f5=0,f6=0;
      let m1=[],m2=[],e1=[],e2=[];
      let visual;
      // 时间粒度为小时
      if(this.granularity==1){
        this.outboundData.time=['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
          '18:00', '19:00', '20:00', '21:00', '22:00'];
        // this.entranceData.timePro=[0,0,0,0,1,0,0];
        this.outboundData.outboundNum=[4,9,8,7,5,6,4,4,4,3,5,5,7,9,4,6];
        // 初始化图例
        myLengend.push('总人次');
        // 初始化早晚高峰范围
        // for(let i in this.entranceData.timePro){
        //   if(this.entranceData.timePro[i]===31){
        //     m1.push(this.entranceData.time[i]);
        //   }
        //   else if(this.entranceData.timePro[i]===32){
        //     m2.push(this.entranceData.time[i]);
        //   }
        //   else if(this.entranceData.timePro[i]===41){
        //     e1.push(this.entranceData.time[i]);
        //   }
        //   else if(this.entranceData.timePro[i]===42){
        //     e2.push(this.entranceData.time[i]);
        //   }
        // }
        // 初始化数据
        let scope=[];
        for(let item in m1){
          let tempM1={
            name:'早高峰',
            xAxis:'',
          }
          let tempM2={
            xAxis:'',
          }
          tempM1.xAxis=m1[item];
          tempM2.xAxis=m2[item];
          let m=[];
          m.push(tempM1);
          m.push(tempM2);
          scope.push(m);
        }
        for(let item in e1){
          let tempE1={
            name:'晚高峰',
            xAxis:'',
          }
          let tempE2= {
            xAxis: '',
          }
          tempE1.xAxis=e1[item];
          tempE2.xAxis=e2[item];
          let e=[];
          e.push(tempE1);
          e.push(tempE2);
          scope.push(e);
        }
        visual= {
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
          // scope= [ [{
          //   name: '早高峰',
          //   xAxis: '7'
          // }, {
          //   name:'早高峰',
          //   xAxis: '9'
          // }], [{
          //   name: '晚高峰',
          //   xAxis: '17'
          // }, {
          //   xAxis: '19'
          // }], [{
          //   name: '早高峰',
          //   xAxis: '71'
          // }, {
          //   name:'早高峰',
          //   xAxis: '91'
          // }], ]
          // scope= [ [{
          //   name: '晚高峰',
          //   xAxis: '17'
          // }, {
          //   xAxis: '19'
          // }]]
          // alert("scope.size "+scope.length);
          // alert("scope[0] "+scope[0]);
          // alert("scope[0][0] "+scope[0][0]);
          // alert("scope[0][0].xAxis "+scope[0][0].xAxis);
          // alert("scope[0][1].xAxis "+scope[0][1].xAxis);
          seriesData= [
            {
              name: '总人次',
              type: 'line',
              smooth: true,
              data: this.outboundData.outboundNum,
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
              }
              // markArea: {
              //   itemStyle: {
              //     color: 'rgba(255, 173, 177, 0.4)'
              //   },
              //   data:scope,
              // }
            }
          ];

      }
      // 时间粒度为天
      else if(this.granularity==2||this.granularity=='天'){
        this.outboundData.time=['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'];
        this.outboundData.timePro=[0,0,0,0,1,0,0];
        this.outboundData.outboundNum=[318,291,320,332,336,352,358];
        this.outboundData.morning=[39,37,38,42,39,42,42];
        this.outboundData.evening=[36,34,38,39,37,42,40];

        // 初始化图例
        for(let item in this.outboundData.timePro){
          if(this.outboundData.timePro[item]===0&&f0===0){
            myLengend.push('工作日');
            f0=1;
          }
          else if(this.outboundData.timePro[item]===1&&f1===0){
            myLengend.push('周末');
            f1=1;
          }
          else if(this.outboundData.timePro[item]===2&&f2===0){
            myLengend.push('节假日');
            f2=1;
          }
        }
        myLengend.push('早高峰');
        myLengend.push('晚高峰');
        // 初始化数据
        for(let item in myLengend){
          let temp={
            name:'',
            type:'',
            stack:'',
            data:[],
          };
          if(myLengend[item]==="工作日"){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            temp.itemStyle={
              normal:{
                color:'rgb(30,115,190)'
              }
            }
            for(let i in this.outboundData.outboundNum){
              if(this.outboundData.timePro[i]===0){
                temp.data.push(this.outboundData.outboundNum[i]);
              }
              else{
                temp.data.push(0);
              }
            }
            seriesData.push(temp);
          }
          else if(myLengend[item]==='周末'){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            temp.itemStyle={
              normal:{
                color:'rgb(96,159,168)'
              }
            }
            for(let i in this.outboundData.outboundNum){
              if(this.outboundData.timePro[i]===1){
                temp.data.push(this.outboundData.outboundNum[i]);
              }
              else{
                temp.data.push(0);
              }
            }
            seriesData.push(temp);
          }
          else if(myLengend[item]==='节假日'){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            for(let i in this.outboundData.outboundNum){
              if(this.outboundData.timePro[i]===2){
                temp.data.push(this.outboundData.outboundNum[i]);
              }
              else{
                temp.data.push(0);
              }
            }
            seriesData.push(temp);
          }
          else if(myLengend[item]==='早高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.yAxisIndex='1';
            temp.data=this.outboundData.morning;
            seriesData.push(temp);
          }
          else if(myLengend[item]==='晚高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.yAxisIndex='1';
            temp.itemStyle={
              normal:{
                color:'rgb(206,166,47)'
              }
            }
            temp.data=this.outboundData.evening;
            seriesData.push(temp);
          }
        }
      }
      else{
        // 初始化图例
        myLengend.push('总人次');
        myLengend.push('早高峰');
        myLengend.push('晚高峰');
        // 初始化数据
        for(let item in myLengend){
          let temp={
            name:'',
            type:'',
            stack:'',
            data:[],
          };
          if(myLengend[item]==='总人次'){
            temp.name=myLengend[item];
            temp.type='bar';
            temp.stack='one';
            temp.data=this.outboundData.outboundNum;
            seriesData.push(temp);
          }
          else if(myLengend[item]==='早高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.data=this.outboundData.morning;
            seriesData.push(temp);
          }
          else if(myLengend[item]==='晚高峰'){
            temp.name=myLengend[item];
            temp.type='line';
            temp.stack='';
            temp.data=this.outboundData.evening;
            seriesData.push(temp);
          }
        }
      }
      outChart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          },
          formatter: function (params) {
            let res=params[0].name+'</p></div>'
            for(let i=0;i<params.length;i++){
              if(params[i].data!==0){
                res+='<p>'+params[i].seriesName+': '+params[i].data+'</p>'
              }
            }
            return res;
          },
        },
        legend: {
          textStyle:{
            color: '#ffffff'//字体颜色
          },
          data: myLengend,
          left:'left',
          width:300,
        },
        visualMap:visual,
        xAxis: [
          {
            type: 'category',
            data: this.outboundData.time,
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
            axisLabel: {
              textStyle: {
                color: '#ffffff'
              }
            },
          },
          {
            type: 'value',
            axisLabel: {
              textStyle: {
                color: '#ffffff'
              }
            },
          },
        ],
        series:seriesData,
      },true);
    },
    ageLineInit(){
      //时间粒度为小时
      if(this.granularity==1){
        this.ageStructure.time=['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
          '18:00', '19:00', '20:00', '21:00', '22:00'];
        this.ageStructure.underage=[0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0,0, 0, 0, 0,0,0,0];
        this.ageStructure.teen=[1, 41, 82, 38, 14, 17, 12,9,8,16,10,32,78,37,30,18,13];
        this.ageStructure.middle= [1, 41, 82, 38, 15, 19, 13,9,8,16,10,32,78,37,30,18,13];
        this.ageStructure.old=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0,0,0];
      }
      else if(this.granularity==2||this.granularity=='天'){
        this.ageStructure.time=['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'];
        this.ageStructure.underage=[19, 6, 7, 7, 16, 15, 7];
        this.ageStructure.teen=[578, 669, 660, 675, 708, 696, 661];
        this.ageStructure.middle= [40, 23, 52, 41, 22, 28, 25];
        this.ageStructure.old=[0, 0, 0,0, 0, 6, 6];
      }
      let ageLine = this.$echarts.init(document.getElementById('ageLine'))
      let t=['time'];
      for(let item in this.ageStructure.time){
        t.push(this.ageStructure.time[item]);
      }
      let under=['0-17岁'];
      for(let item in this.ageStructure.underage){
        under.push(this.ageStructure.underage[item]);
      }
      let y=['18-45岁'];
      for(let item in this.ageStructure.teen){
        y.push(this.ageStructure.teen[item]);
      }
      let mid=['46-69岁'];
      for(let item in this.ageStructure.middle){
        mid.push(this.ageStructure.middle[item]);
      }
      let o=['70岁以上'];
      for(let item in this.ageStructure.old){
        o.push(this.ageStructure.old[item]);
      }

      let option = {
        legend: {
          textStyle:{
            color: '#ffffff'//字体颜色
          },
          // data:['0-17岁','18-45岁','46-69岁','70岁以上']
        },
        dataset: {
          source: [
            t,under,y,mid,o
          ]
        },
        tooltip: {
          trigger: 'axis',
          showContent: false,
          crossStyle: {
            color: '#999'
          }
        },
        xAxis: {
          type: 'category',
          axisLabel:{
            show: true,
            textStyle: {
              color: '#ffffff'
            }
          },},
        yAxis: {
          gridIndex: 0,
          axisLabel:{
            show: true,
            textStyle: {
              color: '#ffffff'
            }
          },},
        grid: {top: '55%'},
        series: [
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'},color:'#e3e26e'},
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'},color:'#609fa8'},
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'},color:'#bca19a'},
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'},color: '#ac1969'},
          {
            type: 'pie',
            id: 'pie',
            radius: '30%',
            center: ['50%', '35%'],
            color:['#e3e26e','#609fa8','#bca19a','#ac1969'],
            emphasis: {focus: 'data'},
            label: {
              formatter: '{b}:\n {@2012} ({d}%)'
            },
            encode: {
              itemName: 'time',
              // value: '2012',
              // tooltip: '2012'
            }
          }
        ]
      };

      ageLine.on('updateAxisPointer', function (event) {
        let xAxisInfo = event.axesInfo[0];
        if (xAxisInfo) {
          let dimension = xAxisInfo.value + 1;
          ageLine.setOption({
            series: {
              id: 'pie',
              label: {
                formatter: '{b}:\n {@[' + dimension + ']} ({d}%)'
              },
              encode: {
                value: dimension,
                tooltip: dimension
              }
            }
          });
        }
      });

      ageLine.setOption(option);
    },
    odInit(){
      let chartDom = document.getElementById('od');
      let myChart = this.$echarts.init(chartDom);
      let option;

      let dataAxis = [];
      let data = [];
      for(let i=0;i<this.RECORDS.length;i++){
        dataAxis.push(this.RECORDS[i].out_station);
        data.push(this.RECORDS[i].num);
      }
      let yMax = 500;
      let dataShadow = [];
      for (let i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
      }
      myChart.setOption( {
        title: {
          text: 'OD分析图',
          textStyle:{
            color:'white',
          }
        },
        xAxis: {
          data: dataAxis,
          axisLabel: {
            inside: true,
            textStyle: {
              color: '#fff'
            }
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          z: 10
        },
        yAxis: {
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: '#999'
            }
          }
        },
        dataZoom: [
          {
            type: 'inside'
          }
        ],
        series: [
          {
            type: 'bar',
            showBackground: true,
            itemStyle: {
              color: new this.$echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  {offset: 0, color: '#83bff6'},
                  {offset: 0.5, color: '#188df0'},
                  {offset: 1, color: '#188df0'}
                ]
              )
            },
            emphasis: {
              itemStyle: {
                color: new this.$echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#2378f7'},
                    {offset: 0.7, color: '#2378f7'},
                    {offset: 1, color: '#83bff6'}
                  ]
                )
              }
            },
            data: data
          }
        ]
      })
//    Enable data zoom when user click bar.
      let zoomSize = 6;
      myChart.on('click', function (params) {
        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart.dispatchAction({
          type: 'dataZoom',
          startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        });
      });
      // option && myChart.setOption(option);

    },
    // 点击查询按钮以后
    searchStation() {
      // 之后删掉！！
      this.inChartInit();
      this.outChartInit();
      this.ageLineInit();
      this.initPie();
      this.odInit();
      // 前后端交互
      this.$API.p_Station({
        line: this.selectedValue[0],
        station:this.selectedValue[1],
        startTime: this.timeValue[0],
        endTime: this.timeValue[1],
        granularity: this.granularity,
      })
        .then(
          res => {
            this.entranceData={
                time: [],
                timePro:[],
                entranceNum: [],
                morning:[],
                evening:[],
            };
            this.outboundData={
                time: [],
                timePro:[],
                outboundNum: [],
                morning:[],
                evening:[],
            };
            this.ageStructure={
                time: [],
                teen: [],
                middle: [],
                old: [],
                underage: [],
            };
            for (let item in res.entranceData) {
              this.entranceData.time.push(res.entranceData[item].time);
              this.entranceData.timePro.push(res.entranceData[item].timePro);
              this.entranceData.entranceNum.push(res.entranceData[item].entranceNum);
              this.entranceData.morning.push(res.entranceData[item].morning);
              this.entranceData.evening.push(res.entranceData[item].evening);
            }
            for (let item in res.outboundData) {
              this.outboundData.time.push(res.outboundData[item].time);
              this.outboundData.timePro.push(res.outboundData[item].timePro);
              this.outboundData.entranceNum.push(res.outboundData[item].entranceNum);
              this.outboundData.morning.push(res.outboundData[item].morning);
              this.outboundData.evening.push(res.outboundData[item].evening);
            }
            for (let item in res.ageStructure) {
              this.ageStructure.time.push(res.ageStructure[item].time);
              this.ageStructure.teen.push(res.ageStructure[item].teen);
              this.ageStructure.middle.push(res.ageStructure[item].middle);
              this.ageStructure.old.push(res.ageStructure[item].old);
              this.ageStructure.underage.push(res.ageStructure[item].underage);
            }
            // 更新图
            this.inChartInit();
            this.outChartInit();
            this.ageLineInit();
          }
        )
        .catch(err => {
        })
    },
    getAllStationInfo() {
      this.$API.g_getAllStationInfo().then(res => {
          this.optionsForStation = [];
          for (let i in res) {
            let temp = {
              value: '',
              label: '',
              children: []
            };
            temp.value = res[i].line;
            temp.label = res[i].line + "号线";
            for (let j in res[i].station) {
              let child = {
                value: '',
                label: ''
              };
              child.value = res[i].station[j];
              child.label = res[i].station[j] + "站";
              temp.children.push(child);
            }
            this.optionsForStation.push(temp);
          }
          //alert(JSON.stringify(this.optionsForStation));
        }
      ).catch(err => {
        console.log("Error");
      })
    },
    handleChange(value) {
      this.stationValue = value[1];
      console.log(value[1]);
    }
  },
  mounted() {
    window.vue = this;
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
    this.getAllStationInfo();
    // 以下5个方法供开发使用，之后删掉！
    this.inChartInit();
    this.outChartInit();
    this.ageLineInit();
    this.initPie();
    this.odInit();
  },
  created() {
    this.selectedValue= [];
    this.selectedValue.push("1号线");
    this.selectedValue.push("3号站");
    this.granularity="天";
    this.timeValue=[];

    let now=new Date();
    // now.setDate(now.getDate()-1);
    let y = now.getFullYear();
    let m = now.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    let d = now.getDate();
    d = d < 10 ? ('0' + d) : d;
    let h = now.getHours();
    h = h < 10 ? ('0' + h) : h;
    let minute = now.getMinutes();
    let second = now.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    let n= y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;

    let date2 = new Date(now);
    date2.setDate(now.getDate()+6);
    let y2 = date2.getFullYear();
    let m2 = date2.getMonth() + 1;
    m2 = m2 < 10 ? ('0' + m2) : m2;
    let d2 = date2.getDate();
    d2 = d2 < 10 ? ('0' + d2) : d2;
    let h2 = date2.getHours();
    h2 = h2 < 10 ? ('0' + h2) : h2;
    let minute2 = date2.getMinutes();
    let second2 = date2.getSeconds();
    minute2 = minute2 < 10 ? ('0' + minute2) : minute2;
    second2 = second2 < 10 ? ('0' + second2) : second2;
    let n2= y2 + '-' + m2 + '-' + d2+' '+h2+':'+minute2+':'+second2;
    this.timeValue.push(n);
    this.timeValue.push(n2);
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
};
</script>

<style lang="less">
  #echarts{
    background-color: #030409;
    color: #fff;
    background-image: url('overview/img/2-2.png');
    .main-content {
      flex: 1;
      display: flex;
      flex-direction: column;
    }
  }
  .el-select-dropdown__list {
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }
  .el-date-table td.in-range div, .el-date-table td.in-range div:hover, .el-date-table.is-week-mode .el-date-table__row.current div, .el-date-table.is-week-mode .el-date-table__row:hover div {
    background-color: #676f79;
    color: #f0f2f5;
  }
  .el-range-input{
    background-color: rgba(178, 179, 186, 0.3);
    color: #f0f2f5;
  }
  .el-input__inner .el-range-editor {
    background-color: rgba(178, 179, 186, 0.3);
    //background-color: rgba(26, 41, 118, 0.7);
  }
  .el-input__inner {
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }
  .el-date-range-picker__time-header {
    background-color: rgb(52 59 67);
    color: #f0f2f5;
  }
  .el-button{
    background-color: rgba(178, 179, 186, 0.3);
    //background-color: rgba(26, 41, 118, 0.7);
    color: #f0f2f5;
  }

  .el-select-dropdown__item{
    color: #f0f2f5;
  }

  .el-date-editor .el-range-input{
    color: #f0f2f5;
  }

  .el-date-range-picker__content.is-left {
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }

  .el-date-range-picker__content.is-right {
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }
  .el-picker-panel__footer {
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }
  .el-button.is-disabled.is-plain{
    background-color: rgba(178, 179, 186, 0.3);
    //background-color: rgba(26, 41, 118, 0.7);
    .span{
      color: #f0f2f5;
    }
  }
  .el-select-dropdown__item.hover, .el-select-dropdown__item:hover {
    background-color: #F5F7FA;
  }
  .el-select-dropdown__item.hover, .el-select-dropdown__item:hover {
    background-color: #B5BCC9;
  }
  .el-cascader-node:not(.is-disabled):focus, .el-cascader-node:not(.is-disabled):hover {
    background: #b6bdca;
  }
  .el-cascader-menu__list {
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }
  .el-range-editor .el-range-input {
    line-height: 1;
    background-color: rgb(57 69 84);
  }
  .el-time-panel{
    background-color: rgb(57 69 84);
    color: #f0f2f5;
  }
  .el-time-spinner__item{
    color: #f0f2f5;
  }
  .el-time-spinner__item.active:not(.disabled){
    color: #ffffff;
  }
  .el-time-panel__btn{
    color: #ffffff;
  }

  .lineChartBoard {
    margin-top: 2rem;
    margin-left: 3rem;
    margin-bottom: 1rem;
    width:27rem;
    box-shadow: 0 0 3px blue;
    display: flex;
    background-color: rgba(6, 30, 93, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    border-right: 2px solid rgba(1, 153, 209, .5);
    box-sizing: border-box;
    padding: 0 1rem;

    .board-title {
      font-weight: bold;
      height: 50px;
      display: flex;
      align-items: center;
      font-size: 20px;
    }
  }

  .pieChartBoard {
    margin: 2rem 2rem 1rem 1rem;
    width: 27rem;
    box-shadow: 0 0 3px blue;
    display: flex;
    //flex-direction: column;
    background-color: rgba(6, 30, 93, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    border-right: 2px solid rgba(1, 153, 209, .5);
    box-sizing: border-box;
    padding: 0px 1rem;

    .board-title {
      font-weight: bold;
      height: 50px;
      display: flex;
      align-items: center;
      font-size: 20px;
    }
  }
</style>

