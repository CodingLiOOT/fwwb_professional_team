<template>
  <div id="basic">
    <div id="ranking-board">
      <el-row>
        <el-col>
          <div class="ranking-board-title">入站站点排行表：</div>
        </el-col>
        <el-col>
          <dv-scroll-ranking-board :config="config_in" style="width:20rem;height:20rem;" />
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="ranking-board-title">出站站点排行表：</div>

        </el-col>
        <el-col>
          <dv-scroll-ranking-board :config="config_out" style="width:20rem;height:20rem;" />
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
    export default {
        name: "ranking-board",
      data() {
      return {
        config_in: {
          //排名轮播表
          data: [
            {
              name: '入站1',
              value: 55
            },
            {
              name: '入站2',
              value: 120
            },
            {
              name: '入站3',
              value: 78
            },
            {
              name: '入站4',
              value: 66
            },
            {
              name: '入站5',
              value: 80
            },
            {
              name: '入站6',
              value: 45
            },
            {
              name: '入站7',
              value: 29
            }
          ]
        },
        config_out: {
          //排名轮播表
          data: [
            {
              name: '周口',
              value: 55
            },
            {
              name: '南阳',
              value: 120
            },
            {
              name: '西峡',
              value: 78
            },
            {
              name: '驻马店',
              value: 66
            },
            {
              name: '新乡',
              value: 80
            },
            {
              name: '信阳',
              value: 45
            },
            {
              name: '漯河',
              value: 29
            }
          ]
        }
      };
    },
    }
</script>

<style lang="less">
  #basic {
    display: flex;
    justify-content: space-between;
    height: 45%;
    width: 100%;

    #ranking-board {
      //margin-top: 2.2rem;
      margin-left: 7%;
      //margin-bottom: 2rem;
      //width: 23.1%;
      width: 100%;
      box-shadow: 0 0 3px blue;
      display: flex;
      flex-direction: column;
      background-color: rgba(6, 30, 93, 0.5);
      border-top: 2px solid rgba(1, 153, 209, .5);
      box-sizing: border-box;
      padding: 0px 30px;

      .ranking-board-title {
        font-weight: bold;
        height: 50px;
        display: flex;
        align-items: center;
        font-size: 20px;
        color: white;
      }
    }
  }
</style>
