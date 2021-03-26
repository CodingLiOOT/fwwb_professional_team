<template>

  <div id="data-view">
    <dv-full-screen-container>
<!--      <Top-header />-->
      <dv-border-box-11 title="客流预测分析">
        <div class="main-content">
          <div class="block-left-right-content">
            <Ring />


          <!--          <ranking-board />-->

          <!--          <div class="block-top-bottom-content">-->
          <!--            <div class="block-top-content">-->
          <!--              <rose-chart />-->

          <!--              <water-level-chart />-->

          <!--              <scroll-board />-->
          <!--            </div>-->

          <!--            <cards />-->
          <!--          </div>-->
          </div>
        </div>

      </dv-border-box-11>
    </dv-full-screen-container>
  </div>
</template>

<script>

import Ring from "./Ring"

export default {
  name: 'DataView',
  components: {
    Ring,
  },
  data () {
    return {}
  },
  methods: {}
}
</script>

<style lang="less">
#data-view {
  width: 100%;
  height: 100%;
  background-color: #030409;
  color: #fff;

  #dv-full-screen-container {
    background-image: url('img/2-2.png');
    background-size: 100% 100%;
    box-shadow: 0 0 3px blue;
    display: flex;
    flex-direction: column;
  }

  .main-content {
    flex: 1;
    display: flex;
    flex-direction: column;
  }

  .block-left-right-content {
    flex: 1;
    display: flex;
    margin-top: 20px;
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
    padding-bottom: 20px;
  }
}
</style>
