<template>
  <div id="cards">
    <div class="card-item">
       <router-link  class="link" :to="{ path: '/Mainpage/data_v' }">查看线路详情</router-link>
     <div id="lineProportion" :style="{width: '15rem', height: '30rem',}">
      </div>
    </div>
  </div>

</template>

<script>
import data_V from "../data_V";

export default {
  name: "Ring",
  data(){
    return{
      ageStructure:{
        teen:'',
        middle:'',
        old:'',
        underage:'',
      },
      LineRatio:{
        line:[],
        total:[],
      },
    }
  },

  methods:{
    getInfo(){
      let date=new Date();
      let datetime=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDay()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
      this.$API.p_getAgeLine({
        date:datetime,
      })
      .then(
        res=>{
          this.ageStructure={
            teen:'',
            middle:'',
            old:'',
            underage:'',
          };
          this.LineRatio={
            line:[],
            total:[],
          };
          this.ageStructure.underage=res.ageStructure.underage;
          this.ageStructure.teen=res.ageStructure.teen;
          this.ageStructure.old=res.ageStructure.old;
          this.ageStructure.middle=res.ageStructure.middle;
          for(let item in res.LineRatio){
            this.LineRatio.line.push(res.LineRatio[item].line);
            this.LineRatio.total.push(res.LineRatio[item].total);
          }
        }
      )
      .catch(err=>{

      })
    },
    lineProportionInit(){
      let linePropChart = this.$echarts.init(document.getElementById('lineProportion'))
      linePropChart.setOption({
        title:[{
          text:'线路占比',
          left:'center',
          top:'47%',
          textStyle:{
            color:'white',
          }
        },
          {
            text:'年龄结构',
            left:'center',
            bottom:'2%',
            textStyle:{
              color:'white',
            }
          }],
        series: [
          {
            name: '面积模式',
            type: 'pie',
            radius: [7,50],
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
  },
  mounted() {
    // this.getInfo();
    this.lineProportionInit();
    this.ageProportionInit();
  }
}
</script>
<style lang="less">
#cards {
  display: flex;
  justify-content: space-between;
  height: 30%;
  width:100%;

  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    //border-right: 2px solid rgba(1, 153, 209, .5);
    margin-left: 7%;
    //margin-top:14%;
    align-items: center;
    //width: 23%;
    width: 100%;
    display: flex;
    flex-direction: column;
  }
  .link{
    color:white;
  }




}
</style>
