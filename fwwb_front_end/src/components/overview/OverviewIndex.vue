<template>
  <div id="overviewIndex">
    <dv-border-box-11 title="客流预测分析">
      <el-container>
        <el-main>
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
          <el-row>
            <el-col span="6">
              <div>
                <Ranking_board/>
              </div>
            </el-col>
            <el-col span="18">
              <el-row>
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
  width: 59rem;
  height: 50rem;
  margin-top: 4%;
  margin-left: 5%;
  flex: auto;
  display: flex;
  flex-direction: column;
}
.main-content-row {
  flex: auto;
  display: flex;
  flex-direction: row;
}
</style>
