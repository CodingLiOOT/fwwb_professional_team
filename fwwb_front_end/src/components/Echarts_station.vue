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
            <el-row :gutter="6">
              <el-col :span="12">
                <div class="pieChartBoard">
                  <el-row>
                    <el-col>
                      <div class="board-title">年龄占比：</div>
                    </el-col>
                    <el-col>
                      <div id="ageLine" :style="{width: '40rem', height: '40rem'}"></div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="lineChartBoard">
                  <el-row>
                    <el-col>
                      <div class="board-title">时间段内入站人数：</div>
                    </el-col>
                    <el-col >
                      <div id="inChart" :style="{width: '40rem', height: '17.5rem'}"></div>
                    </el-col>
                  </el-row>
                </div>
                <div class="lineChartBoard">
                  <el-row>
                    <el-col>
                      <div class="board-title">时间段内出站人数：</div>
                    </el-col>
                    <el-col>
                      <div id="outChart" :style="{width: '40rem', height: '17.5rem'}"></div>
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
      selectedValue:'',
      granularity: '',
      timeValue: '',
      entranceData:{
        time: ['4月18日','4月19日','4月20日','4月21日','4月22日','4月23日','4月24日'],
        timePro:[1,0,0,0,0,0,1],
        entranceNum: [12,23,12,45,23,21,45],
        morning:[12,23,12,45,23,21,11],
        evening:[19,33,22,35,20,11,19],
      },
      outboundData:{
        time:['4月18日','4月19日','4月20日','4月21日','4月22日','4月23日','4月24日'],
        timePro:[1,0,0,0,0,0,1],
        outboundNum: [21,34,25,13,23,16,21],
        morning:[12,32,12,22,14,25,12],
        evening:[23,21,13,14,15,16,21],
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
      let inChart = this.$echarts.init(document.getElementById('inChart'))
      let myLengend=[];
      let seriesData=[];
      let f0=0,f1=0,f2=0,f3=0,f4=0,f5=0,f6=0;
      let m1=[],m2=[],e1=[],e2=[];
      // 时间粒度为小时
      if(this.granularity==1){
        // 初始化图例
        myLengend.push('总人次');
        // 初始化早晚高峰范围
        for(let i in this.entranceData.timePro){
          if(this.entranceData.timePro[i]===31){
            m1.push(this.entranceData.time[i]);
          }
          else if(this.entranceData.timePro[i]===32){
            m2.push(this.entranceData.time[i]);
          }
          else if(this.entranceData.timePro[i]===41){
            e1.push(this.entranceData.time[i]);
          }
          else if(this.entranceData.timePro[i]===42){
            e2.push(this.entranceData.time[i]);
          }
        }
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
              data:scope,
            }
          }
        ];

      }
      // 时间粒度为天
      else if(this.granularity==2){
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
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
              textStyle: {
                color: '#ffffff'
              }
            },

          }
        ],
        series:seriesData,
      },true);
    },
    outChartInit(){
      let outChart = this.$echarts.init(document.getElementById('outChart'))
      let myLengend=[];
      let seriesData=[];
      let f0=0,f1=0,f2=0,f3=0,f4=0,f5=0,f6=0;
      let m1=[],m2=[],e1=[],e2=[];
      // 时间粒度为小时
      if(this.granularity==1){
        // 初始化图例
        myLengend.push('总人次');
        // 初始化早晚高峰范围
        for(let i in this.entranceData.timePro){
          if(this.entranceData.timePro[i]===31){
            m1.push(this.entranceData.time[i]);
          }
          else if(this.entranceData.timePro[i]===32){
            m2.push(this.entranceData.time[i]);
          }
          else if(this.entranceData.timePro[i]===41){
            e1.push(this.entranceData.time[i]);
          }
          else if(this.entranceData.timePro[i]===42){
            e2.push(this.entranceData.time[i]);
          }
        }
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
              data:scope,
            }
          }
        ];

      }
      // 时间粒度为天
      else if(this.granularity==2){
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
          }
        },
        legend: {
          textStyle:{
            color: '#ffffff'//字体颜色
          },
          data: myLengend,
          left:'left',
          width:300,
        },
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
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
              // formatter: '{value} ml',
              textStyle: {
                color: '#ffffff'
              }
            }
          }
        ],
        series: seriesData
      });
    },
    ageLineInit(){
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
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
          {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
          {
            type: 'pie',
            id: 'pie',
            radius: '30%',
            center: ['50%', '25%'],
            emphasis: {focus: 'data'},
            label: {
              formatter: '{b}:\n {@2012} ({d}%)'
            },
            encode: {
              itemName: 'time',
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
    // 点击查询按钮以后
    searchStation() {
      // 之后删掉！！
      this.inChartInit();
      this.outChartInit();
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
    // 以下3个方法供开发使用，之后删掉！
    this.inChartInit();
    this.outChartInit();
    this.ageLineInit();
    this.initPie();
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
  .lineChartBoard {
    margin-top: 2rem;
    margin-left: 1rem;
    margin-bottom: 1rem;
    width:40rem;
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
    margin-top: 2rem;
    margin-left: 3rem;
    margin-bottom: 1rem;
    width: 40rem;
    box-shadow: 0 0 3px blue;
    display: flex;
    //flex-direction: column;
    background-color: rgba(6, 30, 93, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    border-right: 2px solid rgba(1, 153, 209, .5);
    box-sizing: border-box;
    padding: 0px 8px;

    .board-title {
      font-weight: bold;
      height: 50px;
      display: flex;
      align-items: center;
      font-size: 20px;
    }
  }
</style>

