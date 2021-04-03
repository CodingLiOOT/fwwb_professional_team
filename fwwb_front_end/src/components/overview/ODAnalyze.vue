<template>
  <div id="OD-board">
    <el-row>
      <el-col >
        <el-switch
          style="float: left"
          v-model="value"
          active-text="OD分析图"
          inactive-text="桑基图">
        </el-switch>
      </el-col>
      <el-col>
        <div id="OD_Analyze" :style="{width: '40rem', height: '20rem'}" v-if="value"></div>
        <div style="width: 40rem;height: 20rem;background-color: white" v-else></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "ODAnalyze",
      data() {
        return {
          value: true,
        }
      },
      methods: {
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
        this.ODChartInit();
      }
    }
</script>

<style lang="less">
  #OD-board {
    margin-top: 1rem;
    margin-left: 3rem;
    width: 70%;
    height: 100%;
    box-shadow: 0 0 3px blue;
    display: flex;
    //flex-direction: column;
    background-color: rgba(6, 30, 93, 0.5);
    border-top: 2px solid rgba(1, 153, 209, .5);
    box-sizing: border-box;
    padding: 0px 20px;

    .ranking-board-title {
      font-weight: bold;
      height: 50px;
      display: flex;
      align-items: center;
      font-size: 20px;
    }
  }
</style>
