<template>
  <div id="overviewIndex">
    <dv-border-box-11 title="客流预测分析">
      <el-container>
        <el-main>
<!--          <div class="bg">-->
            <el-row>
              <el-col span="6">
                <div>
                  <basic-info/>
                </div>
                <div>
                  <ring/>
                </div>
              </el-col>
              <el-col span="18">
                <div class="bigImage"/>
              </el-col>
            </el-row>
<!--          </div>-->
<!--          <div class="bg">-->
            <el-row>
              <el-col span="6">
                <div>
                  <Ranking_board/>
                </div>
              </el-col>
              <el-col span="18">
                <el-row :gutter="400">
                  <el-col span="9">
                    <div>
                      <day-chart/>
                    </div>
                  </el-col>
                  <el-col span="9">
                    <div>
                      <today-chart/>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <div>
                    <ODAnalyze/>
                  </div>
                </el-row>
              </el-col>
            </el-row>
<!--          </div>-->
        </el-main>
      </el-container>
    </dv-border-box-11>
  </div>
</template>

<script>
import Ring from "./Ring";
import BasicInfo from "./BasicInfo";
import dayChart from "./dayChart";
import ODAnalyze from "./ODAnalyze";
import Ranking_board from "./Ranking_board";
import todayChart from "./todayChart";

export default {
  name: "tryIndex",
  components: {
    Ring,
    BasicInfo,
    dayChart,
    ODAnalyze,
    Ranking_board,
    todayChart,
  },
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')

  }
}
</script>

<style scoped>
#overviewIndex{
  background-image: url('img/2-2.png');
}
.bigImage{
  background-color: white;
  width: 60rem;
  height: 50.5rem;
  margin-top: 2.5rem;
  margin-left: 2rem;
  flex: auto;
  display: flex;
  flex-direction: column;
}
.elRow{
  margin-bottom: 2rem;
}
.bg{
  background-color: rgba(5, 27, 85, 0.5);
  border: 2px solid rgba(1, 153, 209, .5);
  margin-top: 2rem;
}
</style>
