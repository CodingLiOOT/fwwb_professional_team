<template>
  <div id="basic">
    <div id="ranking-board">
      <el-row>
        <el-col>
          <div class="ranking-board-title">入站站点排行表：</div>
        </el-col>
        <el-col>
          <dv-scroll-ranking-board :config="config_in" style="width:15rem;height:22rem;" />
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="ranking-board-title">出站站点排行表：</div>

        </el-col>
        <el-col>
          <dv-scroll-ranking-board :config="config_out" style="width:15rem;height:22rem;" />
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
              name: 'Sta126',
              value: 337
            },
            {
              name: 'Sta89',
              value: 320
            },
            {
              name: 'Sta63',
              value: 315
            },
            {
              name: 'Sta134',
              value: 238
            },
            {
              name: 'Sta115',
              value: 179
            },
            {
              name: 'Sta129',
              value: 165
            },
            {
              name: 'Sta40',
              value: 151
            },
            {
              name: 'Sta20',
              value: 146
            },
            {
              name: 'Sta107',
              value: 143
            },
            {
              name: 'Sta9',
              value: 136
            }
          ],
          rowNum:5,
        },
        config_out: {
          rowNum:5,
          //排名轮播表
          data: [
            {
              name: 'Sta126',
              value: 394
            },
            {
              name: 'Sta63',
              value: 392
            },
            {
              name: 'Sta89',
              value: 308
            },
            {
              name: 'Sta115',
              value: 190
            },
            {
              name: 'Sta129',
              value: 163
            },
            {
              name: 'Sta20',
              value: 156
            },
            {
              name: 'Sta134',
              value: 154
            },
            {
              name: 'Sta9',
              value: 146
            },
            {
              name: 'Sta30',
              value: 146
            },
            {
              name: 'Sta5',
              value: 143
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

    #ranking-board {
      margin-left: 1rem;
      margin-top: 1rem;
      width: 25rem;
      box-shadow: 0 0 3px blue;
      display: flex;
      flex-direction: column;
      background-color: rgba(6, 30, 93, 0.5);
      border-top: 2px solid rgba(1, 153, 209, .5);
      box-sizing: border-box;
      padding: 0px 30px;

      .ranking-board-title {
        font-weight: bold;
        align-items: center;
        margin-top: 2rem;
        margin-bottom: 1rem;
        color: white;
      }
    }
  }
</style>
