<template>
  <div id="datav">
    <dv-border-box-11 title="客流预测分析">
      <div class="main-content">
        <div class="block-left-right-content">
      <el-row style="margin-top: 5rem">
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
          <dv-digital-flop style="width:200px;height:50px;" />
        </el-col>
      </el-row>

      <el-row :gutter="6">
        <div id="subLine" :style="{width: '100rem', height: '10rem',align:'center'}"></div>
      </el-row>
      <el-row :gutter="6">
        <el-col :offset="0" :span="7">
          <div class="lineChartBoard">
            <el-row>
              <el-col>
                <div class="board-title">时间段内进站人数：</div>
              </el-col>
              <el-col>
                <div id="inChart" :style="{width: '20rem', height: '25rem'}"></div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :offset="0" :span="7">
          <div class="lineChartBoard1">
            <el-row>
              <el-col>
                <div class="board-title">时间段内出站人数：</div>
              </el-col>
              <el-col>
                <div id="outChart" :style="{width: '20rem', height: '25rem'}"></div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :offset="0" :span="4">
          <div class="ratio">
            <el-row>
              <el-col>
                <div class="board-title">人数占比：</div>
              </el-col>
              <el-col>
                <div id="inPie" :style="{width: '15rem', height: '25rem'}"></div>
              </el-col>
            </el-row>
          </div>
        </el-col>
        <el-col :offset="0" :span="5">
          <div class="pieChartBoard">
            <el-row>
              <el-col>
                <div class="board-title">年龄占比：</div>
              </el-col>
              <el-col>
                <div id="ageLine" :style="{width: '20rem', height: '25rem'}"></div>
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
        {
          value:3,
          label:'3号线'
        },
        {
          value:4,
          label:'4号线'
        },
        {
          value:5,
          label:'5号线'
        },
        {
          value:10,
          label:'10号线'
        },
        {
          value:11,
          label:'11号线'
        },
        {
          value:12,
          label:'12号线'
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
        time: ['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'],
        timePro:[0,0,0,0,1,0,0],
        entranceNum: [327,336,340,333,216,198,328],
        morning:[40,42,40,42,25,24,39],
        evening:[37,39,41,39,24,24,37],
      },
      outboundData:{
        time: ['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'],
        timePro:[0,0,0,0,1,0,0],
        outboundNum: [322,335,335,331,210,194,332],
        morning:[39,42,40,42,25,24,40],
        evening:[36,39,40,39,23,23,38],
      },
      ageStructure:{
        time:['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'],
        underage: [5, 6, 17, 4, 5, 2, 8],
        teen:  [617, 613, 587, 389, 345, 593, 581],
        middle: [43, 53, 60, 32, 38, 59, 65],
        old:  [0, 0, 0,0, 0, 0, 0],
      },
      lineRatio:{
        line:["Sta43","Sta10","Sta96","Sta132","Sta37","Sta16","Sta69","Sta54"],
        entranceNum:[831,17,1226,311,270,110,498,1633],
        outboundNum:[829,20,1236,300,259,108,490,1643],
      }
    }
  },
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')
    // 以下方法供开发使用，
    //this.selectedValue=[];
    //this.selectedValue.push("5号线");
    this.selectedValue="5号线"
    this.granularity="天";
    this.timeValue=[];

    let now=new Date();
    now.setDate(now.getDate()-1);
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
    this.inChartInit();
    this.outChartInit();
    this.pieInit();
    this.ageLineInit();
    this.initPie();

    // this.ODInit();
    // 获取线路信息
    this.getLineStation();
    // 获取线路拓扑图
    this.subwayLineInit();


  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  methods:{
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
        this.entranceData.entranceNum=[2,29,41,18,8,13,6,5,4,10,8,22,41,14,18,8,4];
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
        this.entranceData.entranceNum=[327,336,340,333,216,198,328];
        // this.entranceData.entranceNum=[19,20,20,20,13,12,19];
        this.entranceData.morning=[40,42,40,42,25,24,39];
        this.entranceData.evening=[37,39,41,39,24,24,37];

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
          }
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
        this.outboundData.outboundNum=[1,13,57,19,6,10,5,5,10,5,8,9,37,19,16,13,5];
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
        this.outboundData.outboundNum=[322,335,335,331,210,194,332];
        this.outboundData.morning=[39,42,40,42,25,24,40];
        this.outboundData.evening=[36,39,40,39,23,23,38];

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
          }
        ],
        series:seriesData,
      },true);
    },
    ageLineInit(){
      //时间粒度为小时
      if(this.granularity==1){
        this.ageStructure.time=['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
          '18:00', '19:00', '20:00', '21:00', '22:00'];
        this.ageStructure.underage=[0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0,0, 0, 0, 0,0,0,0];
        this.ageStructure.teen=[1, 39, 95, 26, 11, 17, 10,9,13,12,14,28,75,30,31,19,9];
        this.ageStructure.middle= [2, 3, 3, 1, 2, 4, 1,1,1,3,2,3,3,3,3,2,0];
        this.ageStructure.old=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0,0,0];
      }
      else if(this.granularity==2||this.granularity=='天'){
        this.ageStructure.time=['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'];
        this.ageStructure.underage=[5, 6, 17, 4, 5, 2, 8];
        this.ageStructure.teen=[617, 613, 587, 389, 345, 593, 581];
        this.ageStructure.middle= [43, 53, 60, 32, 38, 59, 65];
        this.ageStructure.old=[0, 0, 0,0, 0, 0, 0];
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
      console.log(this.ageStructure.underage[0])
      console.log(this.ageStructure.underage[1])
       let option = {
        legend: {
          textStyle:{
            color: '#ffffff',//字体颜色

          },
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
    pieInit(){
      let inPie = this.$echarts.init(document.getElementById('inPie'))
      let myLegend=[];
      for(let item in this.lineRatio.line){
        let temp=this.lineRatio.line[item];
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
          left: '25%',
          bottom:190,
          textStyle:{
            color:'white',
          }
        },
          {
            subtext: '出站',
            left: '25%',
            bottom:20,
            textStyle:{
              color:'white',
            }
          },
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c}人次 ({d}%)'
        },
        legend: {
          show:true,
          data: ["Sta43","Sta10","Sta96","Sta132","Sta37","Sta16","Sta69","Sta54"],
          left:'left',
          width:250,
          textStyle:{
            color: '#ffffff',//字体颜色
          },
        },
        series: [
          {
            type: 'pie',
            radius: '40%',
            center: ['35%', '30%'],
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
            radius: '40%',
            center: ['35%', '75%'],
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
    initPie(){
      //时间粒度为小时
      if(this.granularity==1){
        this.ageStructure.time=['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00',
          '18:00', '19:00', '20:00', '21:00', '22:00'];
        this.ageStructure.underage=[0, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0,0, 0, 0, 0,0,0,0];
        this.ageStructure.teen=[1, 39, 95, 26, 11, 17, 10,9,13,12,14,28,75,30,31,19,9];
        this.ageStructure.middle= [2, 3, 3, 1, 2, 4, 1,1,1,3,2,3,3,3,3,2,0];
        this.ageStructure.old=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0,0,0];
      }
      else if(this.granularity==2||this.granularity=='天'){
        this.ageStructure.time=['4月20日','4月21日','4月22日','4月23日','4月24日','4月25日','4月26日'];
        this.ageStructure.underage=[5, 6, 17, 4, 5, 2, 8];
        this.ageStructure.teen=[617, 613, 587, 389, 345, 593, 581];
        this.ageStructure.middle= [43, 53, 60, 32, 38, 59, 65];
        this.ageStructure.old=[0, 0, 0,0, 0, 0, 0];
      }
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
          text: '线路人流热力图',
          left: 15,
          textStyle:{
            color:'white',
          }
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
            },
              {
                name: '8',
                x: 350,
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
              {
                source: '7',
                target: '8',
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
      this.pieInit();
      this.outChartInit();
      this.ageLineInit();
      this.initPie();


      this.subwayLineInit();

      //获取进，出和年龄结构
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
       //this.inChartInit();
       //this.outChartInit();
       //this.ageLineInit();
      // // this.pieInit();
    },
  },
}
</script>

<style lang="less">
#datav{
  background-color: #030409;
  color: #fff;
  background-image: url('overview/img/2-2.png');
.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
}
}
.el-range-input{
  background-color: rgba(26, 41, 118, 0.7);
}
.el-input__inner .el-range-editor {
  background-color: rgba(26, 41, 118, 0.7);
}
.el-input__inner {
  background-color: rgba(26, 41, 118, 0.7);
}
.el-button{
  background-color: rgba(26, 41, 118, 0.7);
}
.el-select-dropdown__list{
  background-color: rgba(26, 41, 118, 0.7);
}
.el-date-range-picker__time-header{
  background-color: rgba(26, 41, 118, 0.7);
  color: white;
}
.el-picker-panel__footer {
  background-color: rgba(26, 41, 118, 0.7);
  color: white;
}
.el-date-range-picker__content.is-left {
  background-color: rgba(26, 41, 118, 0.7);
  color: white;
}
.el-date-range-picker__content.is-right {
  background-color: rgba(26, 41, 118, 0.7);
  color: white;
}
.el-button.is-disabled.is-plain{
  background-color: rgba(26, 41, 118, 0.7);
  .span{
    color: white;
  }
}
.el-cascader-menu__list{
  background-color: rgba(26, 41, 118, 0.7);
  color: white;
}
.el-cascader-node__label{
  color: white;
}
.th{
  color: white;
}
.lineChartBoard {
  margin-top: 2rem;
  margin-left: 1rem;
  margin-bottom: 1rem;
  width: 24rem;
  box-shadow: 0 0 3px blue;
  display: flex;
  background-color: rgba(6, 30, 93, 0.5);
  border-top: 2px solid rgba(1, 153, 209, .5);
  border-right: 2px solid rgba(1, 153, 209, .5);
  box-sizing: border-box;
  padding: 0 2rem;

  .board-title {
    font-weight: bold;
    height: 50px;
    display: flex;
    align-items: center;
    font-size: 20px;
  }
}

.lineChartBoard1 {
  margin-top: 2rem;
  margin-left: 0.3rem;
  margin-bottom: 1rem;
  width: 24rem;
  box-shadow: 0 0 3px blue;
  display: flex;
  background-color: rgba(6, 30, 93, 0.5);
  border-top: 2px solid rgba(1, 153, 209, .5);
  border-right: 2px solid rgba(1, 153, 209, .5);
  box-sizing: border-box;
  padding: 0 2rem;

  .board-title {
    font-weight: bold;
    height: 50px;
    display: flex;
    align-items: center;
    font-size: 20px;
  }
}

.ratio{
  margin-top: 2rem;
  margin-left: 0.1rem;
  margin-bottom: 1rem;
  width: 16rem;
  box-shadow: 0 0 3px blue;
  display: flex;
  background-color: rgba(6, 30, 93, 0.5);
  border-top: 2px solid rgba(1, 153, 209, .5);
  border-right: 2px solid rgba(1, 153, 209, .5);
  box-sizing: border-box;
  padding: 0px 23px;

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
  margin-left: 1rem;
  margin-bottom: 1rem;
  //width: 115%;
  width: 20rem;
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
