<template>
  <div class="datav">
    <el-row :gutter="5">
      <el-col :span="4">
        <el-select v-model="selectedValue" filterable placeholder="请选择线路">
          <el-option
            v-for="item in optionsForLine"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
        <el-button @click="searchLine()">查询</el-button>
      </el-col>
      <el-col :span="8">
        <dv-digital-flop :config="config" style="width:200px;height:50px;" />
      </el-col>
    </el-row>

<!--    <el-row :gutter="6">-->
<!--      <div id="subLine" :style="{width: '100rem', height: '25rem'}"></div>-->
<!--    </el-row>-->

    <el-row :gutter="10">
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内入站人数：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>时间段内出站人数：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>人数占比：</h1>
      </el-col>
      <el-col :offset="0" :span="6">
        <h1 style=color:#3fdcdc;>年龄占比：</h1>
      </el-col>
    </el-row>

    <el-row :gutter="6">
      <el-col :offset="0" :span="7">
        <div id="inChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :offset="0" :span="7">
        <div id="outChart" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :offset="0" :span="5">
        <div id="inPie" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
      <el-col :offset="0" :span="5">
        <div id="ageLine" :style="{width: '25rem', height: '25rem'}"></div>
      </el-col>
    </el-row>

    <template>
      <span class="spanTitle">OD分析</span>
      <div id="OD_Analyze" :style="{width: '155rem', height:'100rem'}"></div>
    </template>
  </div>

</template>

<script>
export default {
  data() {
    return {
      selectedValue:'',
      granularity:'',
      timeValue:'',
      optionsForLine:[
        {
          value:1,
          label:'1号线'
        },
        {
          value:2,
          label:'2号线'
        },
      ],
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
      entranceData:{
        time: [1,2,3,4,5,6,7],
        timePro:[0,0,0,0,0,1,1],
        entranceNum: [12,23,12,45,23,21,45],
        morning:[12,23,12,45,23,21,11],
        evening:[19,33,22,35,20,11,19],
      },
      outboundData:{
        time: [1,2,3,4,5,6,7],
        timePro:[0,0,0,0,0,1,1],
        outboundNum: [21,34,25,13,23,16,21],
        morning:[12,32,12,22,14,25,12],
        evening:[23,21,13,14,15,16,21],
      },
      ageStructure:{
        time: ['1','2','3','4','5','6','7'],
        teen: [120, 132, 101, 134, 90, 230, 210],
        middle:  [220, 182, 191, 234, 290, 330, 310],
        old: [150, 232, 201, 154, 190, 330, 410],
        underage:  [320, 332, 301, 334, 390, 330, 320],
      },
      lineRatio:{
        line:[1,2,3],
        entranceNum:[12,23,34],
        outboundNum:[24,54,12],
      }
    }
  },
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
    // 获取线路信息
    this.getLineStation();
    // 获取线路拓扑图
    this.subwayLineInit();
    // 以下方法供开发使用，之后删掉
    this.inChartInit();
    this.outChartInit();
    this.pieInit();
    this.ageLineInit();
    // this.ODInit();

  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  methods:{
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
        toolbox: {
          feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
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
        toolbox: {
          feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
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
              formatter: '{b}: {@2012} ({d}%)'
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
                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
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
    pieInit(){
      let inPie = this.$echarts.init(document.getElementById('inPie'))
      let myLegend=[];
      for(let item in this.lineRatio.line){
        let temp=this.lineRatio.line[item]+'号线';
        myLegend.push(temp);
      }
      let inData=[];
      for(let item in this.lineRatio.line){
        let temp={
          value:'',
          name:'',
        }
        temp.value=this.lineRatio.entranceNum[item];
        temp.name=this.lineRatio.line[item];
        inData.push(temp);
      }
      let outData=[];
      for(let item in this.lineRatio.line){
        let temp={
          value:'',
          name:'',
        }
        temp.value=this.lineRatio.outboundNum[item];
        temp.name=this.lineRatio.line[item];
        outData.push(temp);
      }
      inPie.setOption({
        title: [{
          subtext: '入站',
          left: 'center',
          bottom:200
        },
          {
            subtext: '出站',
            left: 'center',
            bottom:0
          },
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b}号线 : {c}人次 ({d}%)'
        },
        legend: {
          show:true,
          data: this.lineRatio.line,
          left:'left',
          width:200,
          textStyle:{
            color: '#ffffff'//字体颜色
          },
        },
        series: [
          {
            type: 'pie',
            radius: '32.5%',
            center: ['50%', '25%'],
            selectedMode: 'single',
            label:{
              textStyle: {
                color: '#ffffff'
              },
              show:false,
            },
            data: inData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          },
          {
            type: 'pie',
            radius: '32.5%',
            center: ['50%', '75%'],
            selectedMode: 'single',
            label:{
              show:false
            },
            data: outData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })
    },

    getLineStation(){
      this.$API.g_getAllStationInfo().then(res => {
          this.optionsForLine = [];
          for (let i in res) {
            let temp = {
              value: '',
              label: '',
            };
            temp.value = res[i].line;
            temp.label = res[i].line + "号线";
            this.optionsForLine.push(temp);
          }
        }
      ).catch(err => {
        console.log("Error");
      })
    },

    subwayLineInit(){
      let r = 0, g = 0,val = 10;
      let one = 500 / 60
      if (val < 30)//第一个三等分
      {
        r = Math.round(one * val);
        g = 255;
      }
      else if (val >= 30 && val < 60)//第二个三等分
      {
        r = 255;
        g = Math.round(255 - (val - 30) * one);//val减最大取值的三分之一
      }
      else { r = 255; }

      let subwayLineInit = this.$echarts.init(document.getElementById('subLine'))
      subwayLineInit.setOption({
        title: {
          text: '线路人流热力图'
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            type: 'graph',
            layout: 'none',
            symbolSize: 30,//节点大小为17
            roam: true,
            itemStyle: {//给所有节点的类型一个默认样式，特殊的可在其节点下单独修改样式
              normal: {
                color: "#FFFFFF",//颜色默认白色
                borderColor: "#009900"//边框默认绿色
              }
            },
            label: {//给所有的节点字体一个默认样式
              normal: {
                show: true,//显示
                position: "bottom",//下方显示
                color: "#C9C9C9",//默认颜色灰色
                fontSize: 12//字体样式
              }
            },
            lineStyle: {//给所有连线一个默认样式
              normal: {
                width: 10,
                color: "#009900"
              }
            },
            data: [{//节点，name不可重复
              name: '1',
              x: 0,
              y: 0,
              itemStyle: {//给特殊的节点设置不同的样式
                normal: {
                  width: 30,
                  color: "#ffffff",
                }
              },
              label: {
                normal: {
                  color:"#ffffff"
                }
              }
            }, {
              name: '人数',//由于一个节点只能设置一个label，所以添加一个重合节点显示数据和热力，位置一致，label显示在上方
              x: 0,
              y: 0,

              symbolSize: 30,//后期调整为根据热力显示
              itemStyle: {
                normal: {
                  color: 'rgb(r,g,0,0.3)',//后期调整为蓝色程度根据热力显示
                }
              },
              label: {
                normal: {
                  position: "top",
                  color: "#000000"
                }
              }
            },
              {
              name: '2',
              x: 50,
              y: 0,
              itemStyle: {
                normal: {
                  color: "#07F236",
                }
              },
            }, {
              name: '3',
              x: 100,
              y: 0
            }, {
              name: '4',
              x: 150,
              y: 0
            }, {
              name: '5',
              x: 200,
              y: 0
            }, {
              name: '6',
              x: 250,
              y: 0,
              itemStyle: {
                normal: {
                  color: "#FFFFFF",
                  borderColor: "#FF0000"
                }
              }
            }, {
              name: '7',
              x: 300,
              y: 0,
              itemStyle: {
                normal: {
                  color: "#FFFFFF",
                  borderColor: "#FF0000"
                }
              }
            }],
            links: [{//连线的source和target可以选择index，也可选择name，这里方便查看理解我用了name
              source: '1',
              target: '2',
            }, {
              source: '2',
              target: '3',
            }, {
              source: '3',
              target: '4'
            }, {
              source: '4',
              target: '5'
            }, {
              source: '5',
              target: '6'
            }, {
              source: '6',
              target: '7',
              lineStyle: {//特色的连线设置特殊的样式
                normal: {
                  color: "#FF0000"
                }
              }
            },
            ],
          }
        ]
      });
    },

    searchLine() {
      // 之后删掉！！
      this.inChartInit();
      this.outChartInit();
      // 获取进，出和年龄结构
      this.$API.p_Line({
        line: this.selectedValue,
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
            // this.drawChart();
            this.inChartInit();
            this.outChartInit();
            this.ageLineInit();
          }
        )
        .catch(err => {
        })
      // 获取人数占比
      this.$API.p_LineRatio({
        startTime: this.timeValue[0],
        endTime: this.timeValue[1],
      })
        .then(
          res => {
             this.lineRatio={
              line:[],
              entranceNum:[],
              outboundNum:[],
            }
            for (let item in res.ratio) {
              this.lineRatio.line.push(res.ratio[item].line);
              this.lineRatio.entranceNum.push(res.ratio[item].entranceNum);
              this.lineRatio.outboundNum.push(res.ratio[item].outboundNum);
            }
          }
        )
        .catch(err => {
        })
      // 更新四个图
      this.inChartInit();
      this.outChartInit();
      this.ageLineInit();
      this.pieInit();
    },
    // ODInit(){
    //   let barChart = this.$echarts.init(document.getElementById("OD_Analyze"));
    //   let dataY = ['12a', '1a', '2a', '3a', '4a', '5a', '6a', '7a', '8a', '9a','10a','11a', '12p', '1p', '2p', '3p', '4p', '5p', '6p', '7p', '8p', '9p', '10p', '11p'];
    //   let dataX = ['Saturday', 'Friday', 'Thursday', 'Wednesday', 'Tuesday', 'Monday', 'Sunday'];
    //   let dataZ = [[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];
    //   barChart.setOption({
    //     tooltip: {},
    //     visualMap: {   //颜色图例
    //       max: 100,    //允许显示的最大值
    //       inRange: {
    //         color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
    //       }
    //     },
    //     xAxis3D: {
    //       type: 'category',
    //       name:'进站站点',
    //       data: dataX
    //     },
    //     yAxis3D: {
    //       type: 'category',
    //       name:'出站站点',
    //       data: dataY
    //     },
    //     zAxis3D: {
    //       type: 'value',
    //       name:'人次',
    //       axisLabel: {
    //         show: true,
    //         interval: 'auto',
    //         formatter: '{value}%'
    //       },
    //     },
    //     grid3D: {
    //       boxWidth: 300,
    //       boxDepth: 150,
    //       viewControl: {
    //         // beta:0,   //调整倾斜角度
    //         //  projection: 'orthographic'
    //       },
    //       light: {
    //         main: {
    //           intensity: 1.2,
    //           shadow: true
    //         },
    //         ambient: {
    //           intensity: 0.3
    //         }
    //       }
    //     },
    //     series: [{
    //       type: 'bar3D',
    //       name:'OD分析图',
    //       data: dataZ.map(function (item) {
    //         return {
    //           value: [item[0], item[1], item[2]],
    //         }
    //       }),
    //       shading: 'lambert',
    //       label: {
    //         textStyle: {
    //           fontSize: 16,
    //           borderWidth: 1
    //         }
    //       },
    //       emphasis: {
    //         label: {
    //           textStyle: {
    //             fontSize: 20,
    //             color: '#900'
    //           }
    //         },
    //         itemStyle: {
    //           color: '#900'
    //         }
    //       }
    //     }]
    //   });
    // }
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
.configRank>>>name{
  color:black
}
.datav{
  background: url("../assets/bg/2-2.png") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
</style>
