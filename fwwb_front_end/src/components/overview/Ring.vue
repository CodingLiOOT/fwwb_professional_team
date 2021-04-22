<template>
  <div id="cards">
    <div class="card-item">
       <router-link  class="link" :to="{ path: '/Mainpage/data_v' }">查看线路详情</router-link>
     <div id="lineProportion" :style="{width: '15rem', height: '22.5rem',}">
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
          top:'46%',
          textStyle:{
            color:'white',
          }
        },
          {
            text:'年龄结构',
            left:'center',
            bottom:'5%',
            textStyle:{
              color:'white',
            }
          }],
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        series: [
          {
            name: '线路占比',
            type: 'pie',
            radius: [7,50],
            center: ['50%', '24%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              {value: 2417, name: 'Line 1'},
              {value: 2036, name: 'Line 2'},
              {value: 3585	, name: 'Line 3'},
              {value: 158, name: 'Line 4'},
              {value: 489, name: 'Line 5'},
              {value: 1351, name: 'Line 10'},
              {value: 3424, name: 'Line 11'},
              {value: 1066, name: 'Line 12'}
            ]
          },
          {
            name: '年龄结构',
            type: 'pie',
            radius: [7, 50],
            center: ['50%', '73%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              {value: 6880, name: '青年'},
              {value: 67, name: '未成年'},
              {value: 603, name: '中年'},
              {value: 7, name: '老年'},
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

  .card-item {
    background-color: rgba(6, 28, 87, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    margin-left: 1rem;
    margin-bottom: 1.5rem;
    align-items: center;
    width:25rem;
    display: flex;
    flex-direction: column;
  }
  .link{
    color:white;
  }




}
</style>
