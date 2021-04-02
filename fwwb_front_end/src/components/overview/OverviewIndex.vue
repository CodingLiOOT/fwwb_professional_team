<template>

  <div id="data-view">
<!--    <dv-full-screen-container style="margin-top: 5rem;">-->
<!--      <Top-header />-->
      <dv-border-box-11 title="客流预测分析">

        <div class="main-content-col">
          <div class="main-content-row">
            <div class="main-content-col">
              <BasicInfo />
              <Ring />
            </div>
            <div style="background-color: white;width: 50rem;height: 50rem"></div>
          </div>
          <div class="main-content-row">
            <Ranking_board/>
            <day-chart/>
            <ODAnalyze/>
          </div>
        </div>

      </dv-border-box-11>
<!--    </dv-full-screen-container>-->
  </div>
</template>

<script>

import Ring from "./Ring"
import BasicInfo from "./BasicInfo";
import dayChart from "./dayChart";
import ODAnalyze from "./ODAnalyze";
import Ranking_board from "./Ranking_board"

export default {
  name: 'DataView',
  components: {
    Ring,
    BasicInfo,
    dayChart,
    ODAnalyze,
    Ranking_board,
  },
  data () {
    return {}
  },
  methods: {},
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#16191D')

  }
}
</script>

<style lang="less">
#data-view {
  width: 100%;
  height: 100%;
  background-color: #030409;
  color: #fff;

  #dv-full-screen-container {
    //background-image: url('img/2-2.png');
    background-size: 100% 100%;
    box-shadow: 0 0 3px blue;
    display: flex;
    flex-direction: column;
  }

  .main-content-col {
    flex: 1;
    display: flex;
    flex-direction: column;
  }

  .main-content-row {
    flex: 1;
    display: flex;
    flex-direction: row;
  }

  .block-top-center-content{
    flex:1;
    display:flex;
    marigin-top:20rem;
    marigin-left:30rem;
  }

  .block-bottom-content{
    flex:1;
    display:flex;
    marigin-top:20rem;
    marigin-left:30rem;
  }

  .block-left-right-content {
    flex: 1;
    display: flex;
    flex-direction: column;
  }

  .block-top-bottom-content {
    flex: 1;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    padding-left: 20px;
  }

  .block-top-content {
    height: 55%;
    display: flex;
    flex-grow: 0;
    box-sizing: border-box;
    padding-bottom: 30px;
  }
}
</style>
