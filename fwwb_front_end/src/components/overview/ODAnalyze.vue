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
        <div id="OD_Analyze" :style="{width: '58rem', height: '23rem'}" v-if="value"></div>
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
          RECORDS: [
            {
              "in_station": "1",
              "num": "31"
            },
            {
              "in_station": "2",
              "num": "14"
            },
            {
              "in_station": "3",
              "num": "24"
            },
            {
              "in_station": "4",
              "num": "22"
            },
            {
              "in_station": "6",
              "num": "4"
            },
            {
              "in_station": "7",
              "num": "10"
            },
            {
              "in_station": "8",
              "num": "13"
            },
            {
              "in_station": "9",
              "num": "138"
            },
            {
              "in_station": "10",
              "num": "3"
            },
            {
              "in_station": "11",
              "num": "8"
            },
            {
              "in_station": "12",
              "num": "21"
            },
            {
              "in_station": "13",
              "num": "10"
            },
            {
              "in_station": "15",
              "num": "49"
            },
            {
              "in_station": "16",
              "num": "14"
            },
            {
              "in_station": "17",
              "num": "63"
            },
            {
              "in_station": "18",
              "num": "12"
            },
            {
              "in_station": "19",
              "num": "6"
            },
            {
              "in_station": "20",
              "num": "148"
            },
            {
              "in_station": "21",
              "num": "17"
            },
            {
              "in_station": "22",
              "num": "46"
            },
            {
              "in_station": "23",
              "num": "85"
            },
            {
              "in_station": "24",
              "num": "0"
            },
            {
              "in_station": "25",
              "num": "47"
            },
            {
              "in_station": "26",
              "num": "29"
            },
            {
              "in_station": "27",
              "num": "40"
            },
            {
              "in_station": "28",
              "num": "30"
            },
            {
              "in_station": "29",
              "num": "53"
            },
            {
              "in_station": "30",
              "num": "138"
            },
            {
              "in_station": "31",
              "num": "61"
            },
            {
              "in_station": "32",
              "num": "26"
            },
            {
              "in_station": "33",
              "num": "5"
            },
            {
              "in_station": "34",
              "num": "30"
            },
            {
              "in_station": "35",
              "num": "32"
            },
            {
              "in_station": "36",
              "num": "17"
            },
            {
              "in_station": "37",
              "num": "32"
            },
            {
              "in_station": "38",
              "num": "9"
            },
            {
              "in_station": "39",
              "num": "25"
            },
            {
              "in_station": "40",
              "num": "154"
            },
            {
              "in_station": "41",
              "num": "71"
            },
            {
              "in_station": "42",
              "num": "51"
            },
            {
              "in_station": "43",
              "num": "12"
            },
            {
              "in_station": "44",
              "num": "28"
            },
            {
              "in_station": "45",
              "num": "1"
            },
            {
              "in_station": "46",
              "num": "45"
            },
            {
              "in_station": "47",
              "num": "115"
            },
            {
              "in_station": "48",
              "num": "24"
            },
            {
              "in_station": "49",
              "num": "101"
            },
            {
              "in_station": "50",
              "num": "8"
            },
            {
              "in_station": "51",
              "num": "12"
            },
            {
              "in_station": "52",
              "num": "15"
            },
            {
              "in_station": "53",
              "num": "19"
            },
            {
              "in_station": "54",
              "num": "77"
            },
            {
              "in_station": "55",
              "num": "50"
            },
            {
              "in_station": "56",
              "num": "64"
            },
            {
              "in_station": "57",
              "num": "11"
            },
            {
              "in_station": "58",
              "num": "2"
            },
            {
              "in_station": "59",
              "num": "18"
            },
            {
              "in_station": "60",
              "num": "22"
            },
            {
              "in_station": "61",
              "num": "41"
            },
            {
              "in_station": "62",
              "num": "6"
            },
            {
              "in_station": "63",
              "num": "319"
            },
            {
              "in_station": "64",
              "num": "40"
            },
            {
              "in_station": "65",
              "num": "64"
            },
            {
              "in_station": "66",
              "num": "10"
            },
            {
              "in_station": "67",
              "num": "110"
            },
            {
              "in_station": "68",
              "num": "46"
            },
            {
              "in_station": "69",
              "num": "58"
            },
            {
              "in_station": "70",
              "num": "32"
            },
            {
              "in_station": "71",
              "num": "14"
            },
            {
              "in_station": "72",
              "num": "30"
            },
            {
              "in_station": "73",
              "num": "7"
            },
            {
              "in_station": "74",
              "num": "57"
            },
            {
              "in_station": "75",
              "num": "5"
            },
            {
              "in_station": "76",
              "num": "18"
            },
            {
              "in_station": "77",
              "num": "5"
            },
            {
              "in_station": "78",
              "num": "34"
            },
            {
              "in_station": "79",
              "num": "66"
            },
            {
              "in_station": "80",
              "num": "26"
            },
            {
              "in_station": "81",
              "num": "91"
            },
            {
              "in_station": "82",
              "num": "27"
            },
            {
              "in_station": "83",
              "num": "53"
            },
            {
              "in_station": "84",
              "num": "25"
            },
            {
              "in_station": "85",
              "num": "28"
            },
            {
              "in_station": "86",
              "num": "65"
            },
            {
              "in_station": "87",
              "num": "82"
            },
            {
              "in_station": "88",
              "num": "72"
            },
            {
              "in_station": "89",
              "num": "325"
            },
            {
              "in_station": "90",
              "num": "56"
            },
            {
              "in_station": "91",
              "num": "19"
            },
            {
              "in_station": "92",
              "num": "28"
            },
            {
              "in_station": "93",
              "num": "19"
            },
            {
              "in_station": "94",
              "num": "15"
            },
            {
              "in_station": "95",
              "num": "64"
            },
            {
              "in_station": "96",
              "num": "17"
            },
            {
              "in_station": "97",
              "num": "19"
            },
            {
              "in_station": "99",
              "num": "53"
            },
            {
              "in_station": "100",
              "num": "73"
            },
            {
              "in_station": "101",
              "num": "13"
            },
            {
              "in_station": "102",
              "num": "2"
            },
            {
              "in_station": "103",
              "num": "11"
            },
            {
              "in_station": "105",
              "num": "8"
            },
            {
              "in_station": "106",
              "num": "10"
            },
            {
              "in_station": "107",
              "num": "145"
            },
            {
              "in_station": "108",
              "num": "120"
            },
            {
              "in_station": "109",
              "num": "18"
            },
            {
              "in_station": "110",
              "num": "74"
            },
            {
              "in_station": "111",
              "num": "12"
            },
            {
              "in_station": "112",
              "num": "26"
            },
            {
              "in_station": "113",
              "num": "23"
            },
            {
              "in_station": "114",
              "num": "63"
            },
            {
              "in_station": "115",
              "num": "181"
            },
            {
              "in_station": "116",
              "num": "0"
            },
            {
              "in_station": "117",
              "num": "16"
            },
            {
              "in_station": "118",
              "num": "80"
            },
            {
              "in_station": "119",
              "num": "12"
            },
            {
              "in_station": "120",
              "num": "39"
            },
            {
              "in_station": "121",
              "num": "34"
            },
            {
              "in_station": "122",
              "num": "49"
            },
            {
              "in_station": "123",
              "num": "50"
            },
            {
              "in_station": "124",
              "num": "16"
            },
            {
              "in_station": "125",
              "num": "11"
            },
            {
              "in_station": "126",
              "num": "342"
            },
            {
              "in_station": "127",
              "num": "93"
            },
            {
              "in_station": "128",
              "num": "17"
            },
            {
              "in_station": "129",
              "num": "167"
            },
            {
              "in_station": "130",
              "num": "35"
            },
            {
              "in_station": "131",
              "num": "57"
            },
            {
              "in_station": "132",
              "num": "38"
            },
            {
              "in_station": "133",
              "num": "17"
            },
            {
              "in_station": "134",
              "num": "241"
            },
            {
              "in_station": "135",
              "num": "45"
            },
            {
              "in_station": "136",
              "num": "50"
            },
            {
              "in_station": "137",
              "num": "38"
            },
            {
              "in_station": "138",
              "num": "54"
            },
            {
              "in_station": "139",
              "num": "10"
            },
            {
              "in_station": "140",
              "num": "10"
            },
            {
              "in_station": "141",
              "num": "61"
            },
            {
              "in_station": "142",
              "num": "58"
            },
            {
              "in_station": "143",
              "num": "6"
            },
            {
              "in_station": "144",
              "num": "31"
            },
            {
              "in_station": "145",
              "num": "4"
            },
            {
              "in_station": "146",
              "num": "67"
            },
            {
              "in_station": "147",
              "num": "9"
            },
            {
              "in_station": "148",
              "num": "20"
            },
            {
              "in_station": "149",
              "num": "23"
            },
            {
              "in_station": "150",
              "num": "9"
            },
            {
              "in_station": "151",
              "num": "19"
            },
            {
              "in_station": "152",
              "num": "7"
            },
            {
              "in_station": "153",
              "num": "15"
            },
            {
              "in_station": "154",
              "num": "34"
            },
            {
              "in_station": "156",
              "num": "29"
            },
            {
              "in_station": "157",
              "num": "30"
            },
            {
              "in_station": "158",
              "num": "42"
            },
            {
              "in_station": "159",
              "num": "25"
            },
            {
              "in_station": "160",
              "num": "43"
            },
            {
              "in_station": "161",
              "num": "5"
            },
            {
              "in_station": "162",
              "num": "84"
            },
            {
              "in_station": "163",
              "num": "17"
            },
            {
              "in_station": "164",
              "num": "0"
            },
            {
              "in_station": "165",
              "num": "3"
            },
            {
              "in_station": "166",
              "num": "4"
            },
            {
              "in_station": "167",
              "num": "35"
            },
            {
              "in_station": "168",
              "num": "32"
            }
          ],
          OUT: [
            {
              "out_station": "1",
              "num": "45"
            },
            {
              "out_station": "2",
              "num": "14"
            },
            {
              "out_station": "3",
              "num": "27"
            },
            {
              "out_station": "4",
              "num": "22"
            },
            {
              "out_station": "6",
              "num": "6"
            },
            {
              "out_station": "7",
              "num": "8"
            },
            {
              "out_station": "8",
              "num": "7"
            },
            {
              "out_station": "9",
              "num": "148"
            },
            {
              "out_station": "10",
              "num": "1"
            },
            {
              "out_station": "11",
              "num": "8"
            },
            {
              "out_station": "12",
              "num": "20"
            },
            {
              "out_station": "13",
              "num": "10"
            },
            {
              "out_station": "15",
              "num": "47"
            },
            {
              "out_station": "16",
              "num": "12"
            },
            {
              "out_station": "17",
              "num": "62"
            },
            {
              "out_station": "18",
              "num": "20"
            },
            {
              "out_station": "19",
              "num": "4"
            },
            {
              "out_station": "20",
              "num": "158"
            },
            {
              "out_station": "21",
              "num": "14"
            },
            {
              "out_station": "22",
              "num": "41"
            },
            {
              "out_station": "23",
              "num": "95"
            },
            {
              "out_station": "24",
              "num": "2"
            },
            {
              "out_station": "25",
              "num": "35"
            },
            {
              "out_station": "26",
              "num": "27"
            },
            {
              "out_station": "27",
              "num": "31"
            },
            {
              "out_station": "28",
              "num": "31"
            },
            {
              "out_station": "29",
              "num": "44"
            },
            {
              "out_station": "30",
              "num": "148"
            },
            {
              "out_station": "31",
              "num": "43"
            },
            {
              "out_station": "32",
              "num": "23"
            },
            {
              "out_station": "33",
              "num": "5"
            },
            {
              "out_station": "34",
              "num": "22"
            },
            {
              "out_station": "35",
              "num": "33"
            },
            {
              "out_station": "36",
              "num": "24"
            },
            {
              "out_station": "37",
              "num": "33"
            },
            {
              "out_station": "38",
              "num": "8"
            },
            {
              "out_station": "39",
              "num": "27"
            },
            {
              "out_station": "40",
              "num": "131"
            },
            {
              "out_station": "41",
              "num": "65"
            },
            {
              "out_station": "42",
              "num": "58"
            },
            {
              "out_station": "43",
              "num": "8"
            },
            {
              "out_station": "44",
              "num": "33"
            },
            {
              "out_station": "45",
              "num": "1"
            },
            {
              "out_station": "46",
              "num": "57"
            },
            {
              "out_station": "47",
              "num": "105"
            },
            {
              "out_station": "48",
              "num": "16"
            },
            {
              "out_station": "49",
              "num": "98"
            },
            {
              "out_station": "50",
              "num": "6"
            },
            {
              "out_station": "51",
              "num": "8"
            },
            {
              "out_station": "52",
              "num": "14"
            },
            {
              "out_station": "53",
              "num": "19"
            },
            {
              "out_station": "54",
              "num": "75"
            },
            {
              "out_station": "55",
              "num": "39"
            },
            {
              "out_station": "56",
              "num": "67"
            },
            {
              "out_station": "57",
              "num": "9"
            },
            {
              "out_station": "58",
              "num": "3"
            },
            {
              "out_station": "59",
              "num": "23"
            },
            {
              "out_station": "60",
              "num": "12"
            },
            {
              "out_station": "61",
              "num": "35"
            },
            {
              "out_station": "62",
              "num": "12"
            },
            {
              "out_station": "63",
              "num": "397"
            },
            {
              "out_station": "64",
              "num": "35"
            },
            {
              "out_station": "65",
              "num": "62"
            },
            {
              "out_station": "66",
              "num": "7"
            },
            {
              "out_station": "67",
              "num": "106"
            },
            {
              "out_station": "68",
              "num": "30"
            },
            {
              "out_station": "69",
              "num": "61"
            },
            {
              "out_station": "70",
              "num": "33"
            },
            {
              "out_station": "71",
              "num": "8"
            },
            {
              "out_station": "72",
              "num": "40"
            },
            {
              "out_station": "73",
              "num": "7"
            },
            {
              "out_station": "74",
              "num": "59"
            },
            {
              "out_station": "75",
              "num": "7"
            },
            {
              "out_station": "76",
              "num": "24"
            },
            {
              "out_station": "77",
              "num": "4"
            },
            {
              "out_station": "78",
              "num": "38"
            },
            {
              "out_station": "79",
              "num": "67"
            },
            {
              "out_station": "80",
              "num": "28"
            },
            {
              "out_station": "81",
              "num": "99"
            },
            {
              "out_station": "82",
              "num": "35"
            },
            {
              "out_station": "83",
              "num": "54"
            },
            {
              "out_station": "84",
              "num": "37"
            },
            {
              "out_station": "85",
              "num": "26"
            },
            {
              "out_station": "86",
              "num": "82"
            },
            {
              "out_station": "87",
              "num": "47"
            },
            {
              "out_station": "88",
              "num": "62"
            },
            {
              "out_station": "89",
              "num": "312"
            },
            {
              "out_station": "90",
              "num": "53"
            },
            {
              "out_station": "91",
              "num": "20"
            },
            {
              "out_station": "92",
              "num": "26"
            },
            {
              "out_station": "93",
              "num": "17"
            },
            {
              "out_station": "94",
              "num": "20"
            },
            {
              "out_station": "95",
              "num": "53"
            },
            {
              "out_station": "96",
              "num": "12"
            },
            {
              "out_station": "97",
              "num": "22"
            },
            {
              "out_station": "99",
              "num": "43"
            },
            {
              "out_station": "100",
              "num": "68"
            },
            {
              "out_station": "101",
              "num": "19"
            },
            {
              "out_station": "102",
              "num": "6"
            },
            {
              "out_station": "103",
              "num": "9"
            },
            {
              "out_station": "105",
              "num": "7"
            },
            {
              "out_station": "106",
              "num": "6"
            },
            {
              "out_station": "107",
              "num": "135"
            },
            {
              "out_station": "108",
              "num": "139"
            },
            {
              "out_station": "109",
              "num": "25"
            },
            {
              "out_station": "110",
              "num": "69"
            },
            {
              "out_station": "111",
              "num": "14"
            },
            {
              "out_station": "112",
              "num": "21"
            },
            {
              "out_station": "113",
              "num": "26"
            },
            {
              "out_station": "114",
              "num": "71"
            },
            {
              "out_station": "115",
              "num": "193"
            },
            {
              "out_station": "116",
              "num": "0"
            },
            {
              "out_station": "117",
              "num": "10"
            },
            {
              "out_station": "118",
              "num": "82"
            },
            {
              "out_station": "119",
              "num": "11"
            },
            {
              "out_station": "120",
              "num": "43"
            },
            {
              "out_station": "121",
              "num": "40"
            },
            {
              "out_station": "122",
              "num": "53"
            },
            {
              "out_station": "123",
              "num": "46"
            },
            {
              "out_station": "124",
              "num": "12"
            },
            {
              "out_station": "125",
              "num": "14"
            },
            {
              "out_station": "126",
              "num": "399"
            },
            {
              "out_station": "127",
              "num": "107"
            },
            {
              "out_station": "128",
              "num": "16"
            },
            {
              "out_station": "129",
              "num": "165"
            },
            {
              "out_station": "130",
              "num": "34"
            },
            {
              "out_station": "131",
              "num": "57"
            },
            {
              "out_station": "132",
              "num": "36"
            },
            {
              "out_station": "133",
              "num": "17"
            },
            {
              "out_station": "134",
              "num": "156"
            },
            {
              "out_station": "135",
              "num": "53"
            },
            {
              "out_station": "136",
              "num": "64"
            },
            {
              "out_station": "137",
              "num": "35"
            },
            {
              "out_station": "138",
              "num": "44"
            },
            {
              "out_station": "139",
              "num": "16"
            },
            {
              "out_station": "140",
              "num": "10"
            },
            {
              "out_station": "141",
              "num": "52"
            },
            {
              "out_station": "142",
              "num": "71"
            },
            {
              "out_station": "143",
              "num": "11"
            },
            {
              "out_station": "144",
              "num": "31"
            },
            {
              "out_station": "145",
              "num": "6"
            },
            {
              "out_station": "146",
              "num": "52"
            },
            {
              "out_station": "147",
              "num": "3"
            },
            {
              "out_station": "148",
              "num": "20"
            },
            {
              "out_station": "149",
              "num": "28"
            },
            {
              "out_station": "150",
              "num": "10"
            },
            {
              "out_station": "151",
              "num": "18"
            },
            {
              "out_station": "152",
              "num": "6"
            },
            {
              "out_station": "153",
              "num": "7"
            },
            {
              "out_station": "154",
              "num": "44"
            },
            {
              "out_station": "156",
              "num": "28"
            },
            {
              "out_station": "157",
              "num": "25"
            },
            {
              "out_station": "158",
              "num": "39"
            },
            {
              "out_station": "159",
              "num": "30"
            },
            {
              "out_station": "160",
              "num": "34"
            },
            {
              "out_station": "161",
              "num": "3"
            },
            {
              "out_station": "162",
              "num": "81"
            },
            {
              "out_station": "163",
              "num": "20"
            },
            {
              "out_station": "164",
              "num": "1"
            },
            {
              "out_station": "165",
              "num": "2"
            },
            {
              "out_station": "166",
              "num": "3"
            },
            {
              "out_station": "167",
              "num": "35"
            },
            {
              "out_station": "168",
              "num": "36"
            }
          ]
        }
      },
      methods: {
        ODChartInit(){
          let ODChart = this.$echarts.init(document.getElementById('OD_Analyze'))
          let x=[];
          let positive=[];
          let negative=[];
          for(let i=0;i<this.RECORDS.length;i++){
            x.push(this.RECORDS[i].in_station);
            positive.push(this.RECORDS[i].num);
            negative.push(-this.OUT[i].num);
          }
          ODChart.setOption({
              title: {
                text: "站点进出分析",
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
            grid:{
              x:60,
              y:60,
              //width:1000,
              //height:250,
              x2:60,
              y2:60,
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
                  data : x,
                  axisLabel: {
                    interval: 5,      // 坐标轴刻度标签的显示间隔
                    rotate: 40,        // 标签倾斜的角度
                    textStyle: {
                      color: '#3fdcdc'
                    }
                  },
                  showMinLabel: true,
                  showMaxLabel: false
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
                  data:positive
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
                  data:negative,
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
    margin-left: 2rem;
    width:60rem;
    box-shadow: 0 0 3px blue;
    display: flex;
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
