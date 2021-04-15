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
              <el-col span="18" >
                <div class="tuo"><tuoputu></tuoputu></div>
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
import tuoputu from "./big"
import Tuoputu from "./big";

export default {
  name: "tryIndex",
  components: {
    Tuoputu,
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
  background-size:cover;
  background-attachment: fixed;
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
.tuo{
  background-color: rgba(8, 16, 57, 0.8);
  margin-left: 2rem;
  margin-top: 2.5rem;
  height: 50.5rem;
  width: 60rem;
}
</style>
