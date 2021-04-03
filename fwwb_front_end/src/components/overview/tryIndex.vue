<template>
  <el-container>
    <el-aside width="20rem">
<!--      <div style="width: 15rem;height: 100rem;background-color: white"></div>-->
      <BasicInfo />
      <Ring />
<!--      <Ranking_board/>-->
    </el-aside>
    <el-main>
      <div class="bigImage"></div>
    </el-main>
  </el-container>
</template>

<script>
import Ring from "./Ring";
import BasicInfo from "./BasicInfo";
import dayChart from "./dayChart";
import ODAnalyze from "./ODAnalyze";
import Ranking_board from "./Ranking_board";

export default {
  name: "tryIndex",
  components: {
    Ring,
    BasicInfo,
    dayChart,
    ODAnalyze,
    Ranking_board,
  },
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')

  }
}
</script>

<style scoped>
.bigImage{
  background-color: white;
  width: 50rem;
  height: 50rem;
  flex: auto;
  display: flex;
  flex-direction: column;
}
</style>
