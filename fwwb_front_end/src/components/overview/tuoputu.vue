<template>
  <div style="position:relative;">
    <canvas
      width="1200"
      height="1200"
      style="position: absolute;top: 0;left: 0;"
      ref="sc"
    ></canvas>
    <canvas
      width="1200"
      height="1200"
      ref="sc2"
      style="
        position: absolute;
        z-index: 900;
        left: 0;
        top: 0;
        left: 0;
      "
    >
    </canvas>
    <canvas
      width="1200"
      height="1200"
      ref="sc1"
      style="
        position: absolute;
        z-index: 500;
        left: 0;
        top: 0;
        left: 0;
      "
    >
    </canvas>
  </div>
</template>

<script>

export default {
  name: "tuoputu",
  mounted() {
    window.vue=this;
    let canvas = this.$refs.sc,
      c = canvas.getContext("2d");

    c.beginPath();
    for (let i in this.siteInfo[1]) {
      c.lineWidth = 5;
      c.strokeStyle = "red";
      if (i == 0) {
        c.moveTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y);
      } else {
        c.lineTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[1]) {
      c.beginPath();
      c.save();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[1][i].x, this.siteInfo[1][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[1][i].x, this.siteInfo[1][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      c.fontSize = "20";
      c.fillText(
        this.siteInfo[1][i].station_name,
        this.siteInfo[1][i].x,
        this.siteInfo[1][i].y
      );
      c.restore();
    }
    c.beginPath();
    for (let i in this.siteInfo[2]) {
      c.lineWidth = 5;
      c.strokeStyle = "#19BC9D";
      if (i == 0) {
        c.moveTo(this.siteInfo[2][i].x, this.siteInfo[2][i].y);
      } else {
        c.lineTo(this.siteInfo[2][i].x, this.siteInfo[2][i].y);
      }
    }
    c.lineTo(888, 428);
    c.stroke();

    for (let i in this.siteInfo[2]) {
      c.save();
      c.beginPath();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[2][i].x, this.siteInfo[2][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[2][i].x, this.siteInfo[2][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[2][i].station_name).width;
      c.fillText(
        this.siteInfo[2][i].station_name,
        this.siteInfo[2][i].x,
        this.siteInfo[2][i].y
      );
      c.restore();
    }

    c.beginPath();
    for (let i in this.siteInfo[3]) {
      c.lineWidth = 5;
      c.strokeStyle = "#3D96D0";
      if (i == 0) {
        c.moveTo(this.siteInfo[3][i].x, this.siteInfo[3][i].y);
      } else {
        c.lineTo(this.siteInfo[3][i].x, this.siteInfo[3][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[3]) {
      c.save();
      c.beginPath();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[3][i].x, this.siteInfo[3][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[3][i].x, this.siteInfo[3][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[3][i].station_name).width;
      c.fillText(
        this.siteInfo[3][i].station_name,
        this.siteInfo[3][i].x,
        this.siteInfo[3][i].y
      );
      c.restore();
    }

    c.beginPath();
    for (let i in this.siteInfo[4]) {
      c.lineWidth = 5;
      c.strokeStyle = "#FDAF05";
      if (i == 0) {
        c.moveTo(this.siteInfo[4][i].x, this.siteInfo[4][i].y);
      } else {
        c.lineTo(this.siteInfo[4][i].x, this.siteInfo[4][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[4]) {
      c.save();
      c.beginPath();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[4][i].x, this.siteInfo[4][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[4][i].x, this.siteInfo[4][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#333";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[4][i].station_name).width;
      c.fillText(
        this.siteInfo[4][i].station_name,
        this.siteInfo[4][i].x,
        this.siteInfo[4][i].y
      );
      c.restore();
    }

    c.beginPath();
    for (let i in this.siteInfo[5]) {
      c.lineWidth = 5;
      c.strokeStyle = "#527292";
      if (i == 0) {
        c.moveTo(this.siteInfo[5][i].x, this.siteInfo[5][i].y);
      } else {
        c.lineTo(this.siteInfo[5][i].x, this.siteInfo[5][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[5]) {
      c.save();
      c.beginPath();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[5][i].x, this.siteInfo[5][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[5][i].x, this.siteInfo[5][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[5][i].station_name).width;
      c.fillText(
        this.siteInfo[5][i].station_name,
        this.siteInfo[5][i].x,
        this.siteInfo[5][i].y
      );
      c.restore();
    }

    c.beginPath();
    for (let i in this.siteInfo[10]) {
      c.lineWidth = 5;
      c.strokeStyle = "#5580d5";
      if (i == 0) {
        c.moveTo(this.siteInfo[10][i].x, this.siteInfo[10][i].y);
      } else {
        c.lineTo(this.siteInfo[10][i].x, this.siteInfo[10][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[10]) {
      c.save();
      c.beginPath();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[10][i].x, this.siteInfo[10][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[10][i].x, this.siteInfo[10][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[10][i].station_name).width;
      c.fillText(
        this.siteInfo[10][i].station_name,
        this.siteInfo[10][i].x,
        this.siteInfo[10][i].y
      );
      c.restore();
    }

    c.beginPath();
    for (let i in this.siteInfo[11]) {
      c.lineWidth = 5;
      c.strokeStyle = "#DE7095";
      if (i == 0) {
        c.moveTo(this.siteInfo[11][i].x, this.siteInfo[11][i].y);
      } else {
        c.lineTo(this.siteInfo[11][i].x, this.siteInfo[11][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[11]) {
      c.beginPath();
      c.save();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[11][i].x, this.siteInfo[11][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[11][i].x, this.siteInfo[11][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[11][i].station_name).width;
      c.fillText(
        this.siteInfo[11][i].station_name,
        this.siteInfo[11][i].x,
        this.siteInfo[11][i].y
      );
      c.restore();
    }

    c.beginPath();
    for (let i in this.siteInfo[12]) {
      c.lineWidth = 5;
      c.strokeStyle = "#F7D7CC";
      if (i == 0) {
        c.moveTo(this.siteInfo[12][i].x, this.siteInfo[12][i].y);
      } else {
        c.lineTo(this.siteInfo[12][i].x, this.siteInfo[12][i].y);
      }
      c.stroke();
    }

    for (let i in this.siteInfo[12]) {
      c.save();
      c.beginPath();
      c.lineWidth = 1;
      c.strokeStyle = "blue";
      c.arc(this.siteInfo[12][i].x, this.siteInfo[12][i].y, 6, 0, 2 * Math.PI);
      c.stroke();
      c.fillStyle = "#EAF1F9";
      c.arc(this.siteInfo[12][i].x, this.siteInfo[12][i].y, 5, 0, 2 * Math.PI);
      c.fill();
      c.fillStyle = "#e7e7e7";
      // console.log(i)
      let strWidth = c.measureText(this.siteInfo[12][i].station_name).width;
      c.fillText(
        this.siteInfo[12][i].station_name,
        this.siteInfo[12][i].x,
        this.siteInfo[12][i].y
      );
      c.restore();
    }

    c.fillStyle = "#EAF1F9";
    c.fillRect(32, 64, 51, 21);
    c.fillStyle = "#333";
    c.fillText("11号线", 40, 77);

    c.fillStyle = "#EAF1F9";
    c.fillRect(293, 48, 51, 21);
    c.fillStyle = "#333";
    c.fillText("10号线", 300, 61);

    c.fillStyle = "#EAF1F9";
    c.fillRect(978, 155, 51, 21);
    c.fillStyle = "#333";
    c.fillText("4号线", 991, 170);

    c.fillStyle = "#EAF1F9";
    c.fillRect(62, 204, 51, 21);
    c.fillStyle = "#333";
    c.fillText("1号线", 76, 218);

    c.fillStyle = "#EAF1F9";
    c.fillRect(248, 503, 51, 21);
    c.fillStyle = "#333";
    c.fillText("12号线", 260, 519);

    c.fillStyle = "#EAF1F9";
    c.fillRect(431, 552, 51, 21);
    c.fillStyle = "#333";
    c.fillText("5号线", 446, 567);

    c.fillStyle = "#EAF1F9";
    c.fillRect(367, 724, 51, 21);
    c.fillStyle = "#333";
    c.fillText("2号线", 382, 739);

    c.fillStyle = "#EAF1F9";
    c.fillRect(896, 730, 51, 21);
    c.fillStyle = "#333";
    c.fillText("3号线", 910, 747);

    this.canvas = this.$refs.sc2,
    this.c = this.canvas.getContext("2d");

    this.canvas.addEventListener("mousemove", this.showInfo);
    this.canvas.addEventListener("click",this.switch);
    this.flying();
  },
  data() {
    return {
      visible: true,
      content: "1212",
      canvas: "",
      c: "",
      x1: 0,
      y1: 0,
      siteInfo: JSON.parse("{\"11\":[{\"station_name\":\"Sta28\",\"sequence\":1,\"district\":\"Dist10\",\"x\":80,\"line_name\":\"11号线\",\"y\":80,\"id\":1131},{\"station_name\":\"Sta124\",\"sequence\":2,\"district\":\"Dist10\",\"x\":109,\"line_name\":\"11号线\",\"y\":74,\"id\":1130},{\"station_name\":\"Sta99\",\"sequence\":3,\"district\":\"Dist10\",\"x\":138,\"line_name\":\"11号线\",\"y\":99,\"id\":1129},{\"station_name\":\"Sta166\",\"sequence\":4,\"district\":\"Dist10\",\"x\":156,\"line_name\":\"11号线\",\"y\":124,\"id\":1128},{\"station_name\":\"Sta36\",\"sequence\":5,\"district\":\"Dist10\",\"x\":162,\"line_name\":\"11号线\",\"y\":135,\"id\":1127},{\"station_name\":\"Sta122\",\"sequence\":6,\"district\":\"Dist10\",\"x\":196,\"line_name\":\"11号线\",\"y\":174,\"id\":1126},{\"station_name\":\"Sta77\",\"sequence\":7,\"district\":\"Dist10\",\"x\":213,\"line_name\":\"11号线\",\"y\":191,\"id\":1125},{\"station_name\":\"Sta140\",\"sequence\":8,\"district\":\"Dist5\",\"x\":228,\"line_name\":\"11号线\",\"y\":217,\"id\":1124},{\"station_name\":\"Sta111\",\"sequence\":9,\"district\":\"Dist5\",\"x\":246,\"line_name\":\"11号线\",\"y\":239,\"id\":1191},{\"station_name\":\"Sta13\",\"sequence\":10,\"district\":\"Dist5\",\"x\":259,\"line_name\":\"11号线\",\"y\":258,\"id\":1122},{\"station_name\":\"Sta70\",\"sequence\":11,\"district\":\"Dist5\",\"x\":277,\"line_name\":\"11号线\",\"y\":274,\"id\":1121},{\"station_name\":\"Sta55\",\"sequence\":12,\"district\":\"Dist5\",\"x\":304,\"line_name\":\"11号线\",\"y\":287,\"id\":1120},{\"station_name\":\"Sta20\",\"sequence\":13,\"district\":\"Dist5\",\"x\":333,\"line_name\":\"11号线\",\"y\":287,\"id\":1119},{\"station_name\":\"Sta23\",\"sequence\":14,\"district\":\"Dist5\",\"x\":365,\"line_name\":\"11号线\",\"y\":288,\"id\":1175},{\"station_name\":\"Sta56\",\"sequence\":15,\"district\":\"Dist5\",\"x\":365,\"line_name\":\"11号线\",\"y\":288,\"id\":1176},{\"station_name\":\"Sta118\",\"sequence\":16,\"district\":\"Dist5\",\"x\":420,\"line_name\":\"11号线\",\"y\":288,\"id\":1118},{\"station_name\":\"Sta115\",\"sequence\":17,\"district\":\"Dist5\",\"x\":452,\"line_name\":\"11号线\",\"y\":288,\"id\":1177},{\"station_name\":\"Sta162\",\"sequence\":18,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"11号线\",\"y\":287,\"id\":1117},{\"station_name\":\"Sta15\",\"sequence\":19,\"district\":\"Dist8\",\"x\":527,\"line_name\":\"11号线\",\"y\":288,\"id\":1115},{\"station_name\":\"Sta86\",\"sequence\":20,\"district\":\"Dist8\",\"x\":567,\"line_name\":\"11号线\",\"y\":288,\"id\":1114},{\"station_name\":\"Sta46\",\"sequence\":21,\"district\":\"Dist8\",\"x\":592,\"line_name\":\"11号线\",\"y\":288,\"id\":1113},{\"station_name\":\"Sta63\",\"sequence\":22,\"district\":\"Dist1\",\"x\":618,\"line_name\":\"11号线\",\"y\":288,\"id\":1178},{\"station_name\":\"Sta3\",\"sequence\":23,\"district\":\"Dist6\",\"x\":644,\"line_name\":\"11号线\",\"y\":288,\"id\":1112},{\"station_name\":\"Sta25\",\"sequence\":24,\"district\":\"Dist6\",\"x\":668,\"line_name\":\"11号线\",\"y\":288,\"id\":1111},{\"station_name\":\"Sta146\",\"sequence\":25,\"district\":\"Dist6\",\"x\":689,\"line_name\":\"11号线\",\"y\":287,\"id\":1110},{\"station_name\":\"Sta130\",\"sequence\":26,\"district\":\"Dist6\",\"x\":713,\"line_name\":\"11号线\",\"y\":304,\"id\":1109},{\"station_name\":\"Sta120\",\"sequence\":27,\"district\":\"Dist6\",\"x\":734,\"line_name\":\"11号线\",\"y\":319,\"id\":1108},{\"station_name\":\"Sta82\",\"sequence\":28,\"district\":\"Dist5\",\"x\":760,\"line_name\":\"11号线\",\"y\":337,\"id\":1132},{\"station_name\":\"Sta164\",\"sequence\":29,\"district\":\"Dist5\",\"x\":780,\"line_name\":\"11号线\",\"y\":351,\"id\":1133},{\"station_name\":\"Sta152\",\"sequence\":30,\"district\":\"Dist1\",\"x\":809,\"line_name\":\"11号线\",\"y\":374,\"id\":1134},{\"station_name\":\"Sta45\",\"sequence\":31,\"district\":\"Dist5\",\"x\":840,\"line_name\":\"11号线\",\"y\":395,\"id\":1135}],\"1\":[{\"station_name\":\"Sta104\",\"sequence\":1,\"district\":\"Dist1\",\"x\":85,\"line_name\":\"1号线\",\"y\":292,\"id\":1170},{\"station_name\":\"Sta65\",\"sequence\":2,\"district\":\"Dist3\",\"x\":96,\"line_name\":\"1号线\",\"y\":337,\"id\":1024},{\"station_name\":\"Sta49\",\"sequence\":3,\"district\":\"Dist3\",\"x\":104,\"line_name\":\"1号线\",\"y\":378,\"id\":1023},{\"station_name\":\"Sta149\",\"sequence\":4,\"district\":\"Dist3\",\"x\":128,\"line_name\":\"1号线\",\"y\":430,\"id\":1022},{\"station_name\":\"Sta74\",\"sequence\":5,\"district\":\"Dist3\",\"x\":168,\"line_name\":\"1号线\",\"y\":430,\"id\":1021},{\"station_name\":\"Sta128\",\"sequence\":6,\"district\":\"Dist3\",\"x\":228,\"line_name\":\"1号线\",\"y\":430,\"id\":1020},{\"station_name\":\"Sta34\",\"sequence\":7,\"district\":\"Dist3\",\"x\":256,\"line_name\":\"1号线\",\"y\":430,\"id\":1019},{\"station_name\":\"Sta106\",\"sequence\":8,\"district\":\"Dist3\",\"x\":302,\"line_name\":\"1号线\",\"y\":430,\"id\":1018},{\"station_name\":\"Sta110\",\"sequence\":9,\"district\":\"Dist3\",\"x\":333,\"line_name\":\"1号线\",\"y\":430,\"id\":1017},{\"station_name\":\"Sta97\",\"sequence\":10,\"district\":\"Dist3\",\"x\":368,\"line_name\":\"1号线\",\"y\":430,\"id\":1016},{\"station_name\":\"Sta80\",\"sequence\":11,\"district\":\"Dist3\",\"x\":415,\"line_name\":\"1号线\",\"y\":430,\"id\":1015},{\"station_name\":\"Sta89\",\"sequence\":12,\"district\":\"Dist3\",\"x\":451,\"line_name\":\"1号线\",\"y\":430,\"id\":1014},{\"station_name\":\"Sta64\",\"sequence\":13,\"district\":\"Dist3\",\"x\":535,\"line_name\":\"1号线\",\"y\":430,\"id\":1013},{\"station_name\":\"Sta150\",\"sequence\":14,\"district\":\"Dist3\",\"x\":589,\"line_name\":\"1号线\",\"y\":430,\"id\":1012},{\"station_name\":\"Sta154\",\"sequence\":15,\"district\":\"Dist3\",\"x\":657,\"line_name\":\"1号线\",\"y\":430,\"id\":1011},{\"station_name\":\"Sta107\",\"sequence\":16,\"district\":\"Dist2\",\"x\":708,\"line_name\":\"1号线\",\"y\":430,\"id\":1010},{\"station_name\":\"Sta83\",\"sequence\":17,\"district\":\"Dist2\",\"x\":752,\"line_name\":\"1号线\",\"y\":430,\"id\":1009},{\"station_name\":\"Sta108\",\"sequence\":18,\"district\":\"Dist1\",\"x\":788,\"line_name\":\"1号线\",\"y\":430,\"id\":1008},{\"station_name\":\"Sta159\",\"sequence\":19,\"district\":\"Dist1\",\"x\":832,\"line_name\":\"1号线\",\"y\":430,\"id\":1006},{\"station_name\":\"Sta1\",\"sequence\":20,\"district\":\"Dist1\",\"x\":893,\"line_name\":\"1号线\",\"y\":430,\"id\":1004}],\"12\":[{\"station_name\":\"Sta14\",\"sequence\":1,\"district\":\"Dist2\",\"x\":292,\"line_name\":\"12号线\",\"y\":508,\"id\":1175},{\"station_name\":\"Sta73\",\"sequence\":2,\"district\":\"Dist2\",\"x\":252,\"line_name\":\"12号线\",\"y\":507,\"id\":1174},{\"station_name\":\"Sta148\",\"sequence\":3,\"district\":\"Dist2\",\"x\":243,\"line_name\":\"12号线\",\"y\":464,\"id\":1173},{\"station_name\":\"Sta60\",\"sequence\":4,\"district\":\"Dist2\",\"x\":228,\"line_name\":\"12号线\",\"y\":380,\"id\":1172},{\"station_name\":\"Sta91\",\"sequence\":5,\"district\":\"Dist6\",\"x\":212,\"line_name\":\"12号线\",\"y\":329,\"id\":1147},{\"station_name\":\"Sta32\",\"sequence\":6,\"district\":\"Dist6\",\"x\":202,\"line_name\":\"12号线\",\"y\":270,\"id\":1146},{\"station_name\":\"Sta116\",\"sequence\":7,\"district\":\"Dist6\",\"x\":290,\"line_name\":\"12号线\",\"y\":222,\"id\":1169},{\"station_name\":\"Sta92\",\"sequence\":8,\"district\":\"Dist6\",\"x\":326,\"line_name\":\"12号线\",\"y\":220,\"id\":1145},{\"station_name\":\"Sta31\",\"sequence\":8,\"district\":\"Dist8\",\"x\":435,\"line_name\":\"12号线\",\"y\":225,\"id\":1165},{\"station_name\":\"Sta93\",\"sequence\":9,\"district\":\"Dist6\",\"x\":637,\"line_name\":\"12号线\",\"y\":221,\"id\":1144},{\"station_name\":\"Sta72\",\"sequence\":10,\"district\":\"Dist6\",\"x\":688,\"line_name\":\"12号线\",\"y\":221,\"id\":1166},{\"station_name\":\"Sta95\",\"sequence\":11,\"district\":\"Dist5\",\"x\":665,\"line_name\":\"12号线\",\"y\":327,\"id\":1143},{\"station_name\":\"Sta90\",\"sequence\":12,\"district\":\"Dist5\",\"x\":652,\"line_name\":\"12号线\",\"y\":369,\"id\":1142},{\"station_name\":\"Sta26\",\"sequence\":13,\"district\":\"Dist5\",\"x\":604,\"line_name\":\"12号线\",\"y\":511,\"id\":1141},{\"station_name\":\"Sta17\",\"sequence\":14,\"district\":\"Dist8\",\"x\":567,\"line_name\":\"12号线\",\"y\":512,\"id\":1140},{\"station_name\":\"Sta101\",\"sequence\":15,\"district\":\"Dist8\",\"x\":533,\"line_name\":\"12号线\",\"y\":507,\"id\":1139},{\"station_name\":\"Sta136\",\"sequence\":16,\"district\":\"Dist3\",\"x\":464,\"line_name\":\"12号线\",\"y\":508,\"id\":1137},{\"station_name\":\"Sta137\",\"sequence\":17,\"district\":\"Dist3\",\"x\":332,\"line_name\":\"12号线\",\"y\":508,\"id\":1138},{\"station_name\":\"Sta14\",\"sequence\":1,\"district\":\"Dist2\",\"x\":292,\"line_name\":\"12号线\",\"y\":508,\"id\":1175}],\"2\":[{\"station_name\":\"Sta51\",\"sequence\":1,\"district\":\"Dist7\",\"x\":342,\"line_name\":\"2号线\",\"y\":739,\"id\":1046},{\"station_name\":\"Sta105\",\"sequence\":2,\"district\":\"Dist4\",\"x\":370,\"line_name\":\"2号线\",\"y\":734,\"id\":1045},{\"station_name\":\"Sta24\",\"sequence\":3,\"district\":\"Dist6\",\"x\":396,\"line_name\":\"2号线\",\"y\":724,\"id\":1044},{\"station_name\":\"Sta139\",\"sequence\":4,\"district\":\"Dist5\",\"x\":418,\"line_name\":\"2号线\",\"y\":708,\"id\":1043},{\"station_name\":\"Sta71\",\"sequence\":5,\"district\":\"Dist4\",\"x\":416,\"line_name\":\"2号线\",\"y\":678,\"id\":1042},{\"station_name\":\"Sta57\",\"sequence\":6,\"district\":\"Dist4\",\"x\":418,\"line_name\":\"2号线\",\"y\":644,\"id\":1041},{\"station_name\":\"Sta76\",\"sequence\":7,\"district\":\"Dist4\",\"x\":417,\"line_name\":\"2号线\",\"y\":611,\"id\":1040},{\"station_name\":\"Sta52\",\"sequence\":8,\"district\":\"Dist4\",\"x\":416,\"line_name\":\"2号线\",\"y\":582,\"id\":1039},{\"station_name\":\"Sta68\",\"sequence\":9,\"district\":\"Dist4\",\"x\":417,\"line_name\":\"2号线\",\"y\":545,\"id\":1038},{\"station_name\":\"Sta151\",\"sequence\":10,\"district\":\"Dist2\",\"x\":416,\"line_name\":\"2号线\",\"y\":515,\"id\":1037},{\"station_name\":\"Sta48\",\"sequence\":11,\"district\":\"Dist2\",\"x\":423,\"line_name\":\"2号线\",\"y\":491,\"id\":1036},{\"station_name\":\"Sta27\",\"sequence\":12,\"district\":\"Dist2\",\"x\":434,\"line_name\":\"2号线\",\"y\":466,\"id\":1035},{\"station_name\":\"Sta81\",\"sequence\":13,\"district\":\"Dist2\",\"x\":464,\"line_name\":\"2号线\",\"y\":403,\"id\":1034},{\"station_name\":\"Sta127\",\"sequence\":14,\"district\":\"Dist2\",\"x\":478,\"line_name\":\"2号线\",\"y\":369,\"id\":1033},{\"station_name\":\"Sta123\",\"sequence\":15,\"district\":\"Dist2\",\"x\":508,\"line_name\":\"2号线\",\"y\":371,\"id\":1032},{\"station_name\":\"Sta47\",\"sequence\":16,\"district\":\"Dist1\",\"x\":530,\"line_name\":\"2号线\",\"y\":372,\"id\":1171},{\"station_name\":\"Sta18\",\"sequence\":17,\"district\":\"Dist1\",\"x\":567,\"line_name\":\"2号线\",\"y\":372,\"id\":1031},{\"station_name\":\"Sta79\",\"sequence\":18,\"district\":\"Dist1\",\"x\":593,\"line_name\":\"2号线\",\"y\":371,\"id\":1030},{\"station_name\":\"Sta78\",\"sequence\":19,\"district\":\"Dist1\",\"x\":620,\"line_name\":\"2号线\",\"y\":369,\"id\":1028},{\"station_name\":\"Sta53\",\"sequence\":20,\"district\":\"Dist1\",\"x\":652,\"line_name\":\"2号线\",\"y\":369,\"id\":1027},{\"station_name\":\"Sta163\",\"sequence\":21,\"district\":\"Dist1\",\"x\":672,\"line_name\":\"2号线\",\"y\":369,\"id\":1026},{\"station_name\":\"Sta9\",\"sequence\":22,\"district\":\"Dist1\",\"x\":686,\"line_name\":\"2号线\",\"y\":390,\"id\":1025},{\"station_name\":\"Sta129\",\"sequence\":23,\"district\":\"Dist1\",\"x\":704,\"line_name\":\"2号线\",\"y\":415,\"id\":1170}],\"3\":[{\"station_name\":\"Sta155\",\"sequence\":1,\"district\":\"Dist7\",\"x\":736,\"line_name\":\"3号线\",\"y\":732,\"id\":1172},{\"station_name\":\"Sta143\",\"sequence\":2,\"district\":\"Dist7\",\"x\":736,\"line_name\":\"3号线\",\"y\":703,\"id\":1048},{\"station_name\":\"Sta156\",\"sequence\":3,\"district\":\"Dist7\",\"x\":736,\"line_name\":\"3号线\",\"y\":682,\"id\":1049},{\"station_name\":\"Sta61\",\"sequence\":4,\"district\":\"Dist7\",\"x\":736,\"line_name\":\"3号线\",\"y\":650,\"id\":1050},{\"station_name\":\"Sta50\",\"sequence\":5,\"district\":\"Dist7\",\"x\":738,\"line_name\":\"3号线\",\"y\":619,\"id\":1051},{\"station_name\":\"Sta119\",\"sequence\":6,\"district\":\"Dist7\",\"x\":728,\"line_name\":\"3号线\",\"y\":596,\"id\":1052},{\"station_name\":\"Sta66\",\"sequence\":7,\"district\":\"Dist7\",\"x\":713,\"line_name\":\"3号线\",\"y\":576,\"id\":1053},{\"station_name\":\"Sta12\",\"sequence\":8,\"district\":\"Dist7\",\"x\":701,\"line_name\":\"3号线\",\"y\":564,\"id\":1054},{\"station_name\":\"Sta161\",\"sequence\":9,\"district\":\"Dist7\",\"x\":686,\"line_name\":\"3号线\",\"y\":540,\"id\":1055},{\"station_name\":\"Sta21\",\"sequence\":10,\"district\":\"Dist7\",\"x\":670,\"line_name\":\"3号线\",\"y\":523,\"id\":1056},{\"station_name\":\"Sta133\",\"sequence\":11,\"district\":\"Dist6\",\"x\":659,\"line_name\":\"3号线\",\"y\":515,\"id\":1057},{\"station_name\":\"Sta22\",\"sequence\":12,\"district\":\"Dist6\",\"x\":650,\"line_name\":\"3号线\",\"y\":505,\"id\":1058},{\"station_name\":\"Sta138\",\"sequence\":13,\"district\":\"Dist6\",\"x\":644,\"line_name\":\"3号线\",\"y\":492,\"id\":1059},{\"station_name\":\"Sta41\",\"sequence\":14,\"district\":\"Dist6\",\"x\":628,\"line_name\":\"3号线\",\"y\":475,\"id\":1060},{\"station_name\":\"Sta30\",\"sequence\":15,\"district\":\"Dist6\",\"x\":615,\"line_name\":\"3号线\",\"y\":461,\"id\":1061},{\"station_name\":\"Sta67\",\"sequence\":16,\"district\":\"Dist6\",\"x\":582,\"line_name\":\"3号线\",\"y\":413,\"id\":1062},{\"station_name\":\"Sta144\",\"sequence\":17,\"district\":\"Dist6\",\"x\":548,\"line_name\":\"3号线\",\"y\":372,\"id\":1063},{\"station_name\":\"Sta5\",\"sequence\":18,\"district\":\"Dist8\",\"x\":535,\"line_name\":\"3号线\",\"y\":351,\"id\":1173},{\"station_name\":\"Sta98\",\"sequence\":19,\"district\":\"Dist8\",\"x\":529,\"line_name\":\"3号线\",\"y\":330,\"id\":1174},{\"station_name\":\"Sta29\",\"sequence\":20,\"district\":\"Dist8\",\"x\":528,\"line_name\":\"3号线\",\"y\":267,\"id\":1064},{\"station_name\":\"Sta126\",\"sequence\":21,\"district\":\"Dist1\",\"x\":524,\"line_name\":\"3号线\",\"y\":231,\"id\":1065},{\"station_name\":\"Sta40\",\"sequence\":22,\"district\":\"Dist5\",\"x\":528,\"line_name\":\"3号线\",\"y\":212,\"id\":1067},{\"station_name\":\"Sta131\",\"sequence\":23,\"district\":\"Dist5\",\"x\":526,\"line_name\":\"3号线\",\"y\":184,\"id\":1068},{\"station_name\":\"Sta39\",\"sequence\":24,\"district\":\"Dist5\",\"x\":525,\"line_name\":\"3号线\",\"y\":143,\"id\":1069},{\"station_name\":\"Sta100\",\"sequence\":25,\"district\":\"Dist5\",\"x\":528,\"line_name\":\"3号线\",\"y\":110,\"id\":1070},{\"station_name\":\"Sta167\",\"sequence\":26,\"district\":\"Dist5\",\"x\":548,\"line_name\":\"3号线\",\"y\":102,\"id\":1072},{\"station_name\":\"Sta113\",\"sequence\":27,\"district\":\"Dist5\",\"x\":566,\"line_name\":\"3号线\",\"y\":94,\"id\":1073},{\"station_name\":\"Sta141\",\"sequence\":28,\"district\":\"Dist5\",\"x\":603,\"line_name\":\"3号线\",\"y\":80,\"id\":1074},{\"station_name\":\"Sta142\",\"sequence\":29,\"district\":\"Dist5\",\"x\":637,\"line_name\":\"3号线\",\"y\":74,\"id\":1075},{\"station_name\":\"Sta158\",\"sequence\":30,\"district\":\"Dist5\",\"x\":677,\"line_name\":\"3号线\",\"y\":61,\"id\":1076},{\"station_name\":\"Sta44\",\"sequence\":31,\"district\":\"Dist5\",\"x\":697,\"line_name\":\"3号线\",\"y\":54,\"id\":1077},{\"station_name\":\"Sta117\",\"sequence\":32,\"district\":\"Dist5\",\"x\":717,\"line_name\":\"3号线\",\"y\":48,\"id\":1078},{\"station_name\":\"Sta147\",\"sequence\":33,\"district\":\"Dist5\",\"x\":739,\"line_name\":\"3号线\",\"y\":39,\"id\":1079},{\"station_name\":\"Sta42\",\"sequence\":34,\"district\":\"Dist5\",\"x\":762,\"line_name\":\"3号线\",\"y\":36,\"id\":1080},{\"station_name\":\"Sta35\",\"sequence\":35,\"district\":\"Dist5\",\"x\":780,\"line_name\":\"3号线\",\"y\":33,\"id\":1081},{\"station_name\":\"Sta109\",\"sequence\":36,\"district\":\"Dist5\",\"x\":797,\"line_name\":\"3号线\",\"y\":40,\"id\":1082},{\"station_name\":\"Sta33\",\"sequence\":37,\"district\":\"Dist5\",\"x\":809,\"line_name\":\"3号线\",\"y\":54,\"id\":1084},{\"station_name\":\"Sta112\",\"sequence\":38,\"district\":\"Dist5\",\"x\":819,\"line_name\":\"3号线\",\"y\":64,\"id\":1085},{\"station_name\":\"Sta153\",\"sequence\":39,\"district\":\"Dist5\",\"x\":832,\"line_name\":\"3号线\",\"y\":76,\"id\":1086},{\"station_name\":\"Sta125\",\"sequence\":40,\"district\":\"Dist5\",\"x\":866,\"line_name\":\"3号线\",\"y\":102,\"id\":1087},{\"station_name\":\"Sta121\",\"sequence\":41,\"district\":\"Dist5\",\"x\":852,\"line_name\":\"3号线\",\"y\":90,\"id\":1088},{\"station_name\":\"Sta125\",\"sequence\":42,\"district\":\"Dist5\",\"x\":866,\"line_name\":\"3号线\",\"y\":102,\"id\":1089},{\"station_name\":\"Sta7722\",\"sequence\":9993,\"district\":\"Dist1244\",\"x\":884,\"line_name\":\"3号线\",\"y\":114,\"id\":9993}],\"4\":[{\"station_name\":\"Sta84\",\"sequence\":1,\"district\":\"Dist5\",\"x\":499,\"line_name\":\"4号线\",\"y\":165,\"id\":1188},{\"station_name\":\"Sta59\",\"sequence\":2,\"district\":\"Dist8\",\"x\":528,\"line_name\":\"4号线\",\"y\":165,\"id\":1092},{\"station_name\":\"Sta19\",\"sequence\":3,\"district\":\"Dist8\",\"x\":574,\"line_name\":\"4号线\",\"y\":165,\"id\":1093},{\"station_name\":\"Sta62\",\"sequence\":4,\"district\":\"Dist8\",\"x\":652,\"line_name\":\"4号线\",\"y\":165,\"id\":1094},{\"station_name\":\"Sta165\",\"sequence\":5,\"district\":\"Dist8\",\"x\":692,\"line_name\":\"4号线\",\"y\":165,\"id\":1095},{\"station_name\":\"Sta58\",\"sequence\":6,\"district\":\"Dist8\",\"x\":734,\"line_name\":\"4号线\",\"y\":165,\"id\":1096},{\"station_name\":\"Sta38\",\"sequence\":7,\"district\":\"Dist8\",\"x\":773,\"line_name\":\"4号线\",\"y\":166,\"id\":1097}],\"5\":[{\"station_name\":\"Sta54\",\"sequence\":1,\"district\":\"Dist5\",\"x\":366,\"line_name\":\"5号线\",\"y\":545,\"id\":1107},{\"station_name\":\"Sta69\",\"sequence\":2,\"district\":\"Dist5\",\"x\":366,\"line_name\":\"5号线\",\"y\":490,\"id\":1104},{\"station_name\":\"Sta16\",\"sequence\":3,\"district\":\"Dist5\",\"x\":364,\"line_name\":\"5号线\",\"y\":373,\"id\":1103},{\"station_name\":\"Sta37\",\"sequence\":4,\"district\":\"Dist5\",\"x\":364,\"line_name\":\"5号线\",\"y\":331,\"id\":1102},{\"station_name\":\"Sta132\",\"sequence\":5,\"district\":\"Dist5\",\"x\":366,\"line_name\":\"5号线\",\"y\":253,\"id\":1101},{\"station_name\":\"Sta96\",\"sequence\":6,\"district\":\"Dist5\",\"x\":380,\"line_name\":\"5号线\",\"y\":205,\"id\":1100},{\"station_name\":\"Sta10\",\"sequence\":7,\"district\":\"Dist5\",\"x\":380,\"line_name\":\"5号线\",\"y\":205,\"id\":1099},{\"station_name\":\"Sta43\",\"sequence\":8,\"district\":\"Dist5\",\"x\":438,\"line_name\":\"5号线\",\"y\":146,\"id\":1098}],\"10\":[{\"station_name\":\"Sta157\",\"sequence\":1,\"district\":\"Dist8\",\"x\":290,\"line_name\":\"10号线\",\"y\":66,\"id\":1149},{\"station_name\":\"Sta114\",\"sequence\":2,\"district\":\"Dist5\",\"x\":348,\"line_name\":\"10号线\",\"y\":68,\"id\":1185},{\"station_name\":\"Sta168\",\"sequence\":3,\"district\":\"Dist5\",\"x\":388,\"line_name\":\"10号线\",\"y\":68,\"id\":1150},{\"station_name\":\"Sta135\",\"sequence\":4,\"district\":\"Dist5\",\"x\":441,\"line_name\":\"10号线\",\"y\":68,\"id\":1186},{\"station_name\":\"Sta134\",\"sequence\":5,\"district\":\"Dist5\",\"x\":480,\"line_name\":\"10号线\",\"y\":68,\"id\":1091},{\"station_name\":\"Sta85\",\"sequence\":6,\"district\":\"Dist5\",\"x\":552,\"line_name\":\"10号线\",\"y\":65,\"id\":1151},{\"station_name\":\"Sta2\",\"sequence\":7,\"district\":\"Dist5\",\"x\":620,\"line_name\":\"10号线\",\"y\":122,\"id\":1152},{\"station_name\":\"Sta4\",\"sequence\":8,\"district\":\"Dist5\",\"x\":607,\"line_name\":\"10号线\",\"y\":200,\"id\":1153},{\"station_name\":\"Sta103\",\"sequence\":9,\"district\":\"Dist5\",\"x\":574,\"line_name\":\"10号线\",\"y\":211,\"id\":1154},{\"station_name\":\"Sta145\",\"sequence\":10,\"district\":\"Dist5\",\"x\":524,\"line_name\":\"10号线\",\"y\":231,\"id\":1155},{\"station_name\":\"Sta88\",\"sequence\":11,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":240,\"id\":1156},{\"station_name\":\"Sta87\",\"sequence\":12,\"district\":\"Dist8\",\"x\":496,\"line_name\":\"10号线\",\"y\":287,\"id\":1187},{\"station_name\":\"Sta94\",\"sequence\":13,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":342,\"id\":1157},{\"station_name\":\"Sta160\",\"sequence\":14,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":429,\"id\":1158},{\"station_name\":\"Sta7\",\"sequence\":15,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":476,\"id\":1159},{\"station_name\":\"Sta6\",\"sequence\":16,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":525,\"id\":1160},{\"station_name\":\"Sta8\",\"sequence\":17,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":561,\"id\":1161},{\"station_name\":\"Sta75\",\"sequence\":18,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":600,\"id\":1136},{\"station_name\":\"Sta102\",\"sequence\":19,\"district\":\"Dist5\",\"x\":496,\"line_name\":\"10号线\",\"y\":666,\"id\":1162}]}\n")
    };
  },
  methods: {
    flying(){
      let can=this.$refs.sc1,
        ctx=can.getContext("2d");

      // 渐变
      function createGradient(ctx,p0,p1){
        let grd = ctx.createLinearGradient(p0.x,p0.y,p1.x,p1.y);
        grd.addColorStop(0,'rgba(0,210,40,0)');
        grd.addColorStop(1,'rgb(250,204,136,1)');
        return grd;
      }
      let p0={'x':100,'y':100};
      let p1={'x':300,'y':100};
      let p2={'x':300,'y':300};

      // 按百分比算点
      function interpolation(P0,P1,t) {
        let Q = {
          x: P0.x * (1 - t) + P1.x * (t),
          y: P0.y * (1 - t) + P1.y * (t),
        };
        return Q;
      }

      let percent = 0.0;
      function auto() {
        let Q01 = interpolation(p0,p1,percent),
          Q11 = interpolation(p1,p2,percent),
          B1 = interpolation(Q01,Q11,percent);
        ctx.beginPath();
        ctx.moveTo(p0.x, p0.y);
        ctx.quadraticCurveTo(Q01.x,Q01.y,B1.x,B1.y);
        ctx.lineCap = 'round';
        ctx.lineWidth =2;
        ctx.strokeStyle = createGradient(ctx,Q01,Q11);
        ctx.stroke();
        ctx.closePath();
        percent += 0.005
        if(percent > 1){
          ctx.beginPath();
          percent = 0.;
        }
        setTimeout(function() {
          auto();
        }, 10);
      }
      auto();

    },
    showInfo(event) {
      // console.log(this.canvas.getBoundingClientRect().left);
      let x = event.clientX - this.canvas.getBoundingClientRect().left,
        y = event.clientY - this.canvas.getBoundingClientRect().top,
        c = this.c


      c.clearRect(0,0, this.canvas.width,this.canvas.width);

      for (let i in this.siteInfo[1]) {
         c.beginPath();
          c.arc(this.siteInfo[1][i].x, this.siteInfo[1][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[1][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[1][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[1][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[1][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[1]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y);
        } else {
          c.lineTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("1号线", x, y+20);
        return;
      }
      c.closePath();



      for (let i in this.siteInfo[2]) {
         c.beginPath();
          c.arc(this.siteInfo[2][i].x, this.siteInfo[2][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[2][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[2][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[2][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[2][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[2]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[2][i].x, this.siteInfo[2][i].y);
        } else {
          c.lineTo(this.siteInfo[2][i].x, this.siteInfo[2][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("2号线", x, y+20);
        return;
      }
      c.closePath();


      for (let i in this.siteInfo[3]) {
         c.beginPath();
          c.arc(this.siteInfo[3][i].x, this.siteInfo[3][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[3][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[3][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[3][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[3][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[3]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[3][i].x, this.siteInfo[3][i].y);
        } else {
          c.lineTo(this.siteInfo[3][i].x, this.siteInfo[3][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("3号线", x, y+20);
        return;
      }
      c.closePath();

      for (let i in this.siteInfo[4]) {
         c.beginPath();
          c.arc(this.siteInfo[4][i].x, this.siteInfo[4][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[4][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[4][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[4][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[4][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[4]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[4][i].x, this.siteInfo[4][i].y);
        } else {
          c.lineTo(this.siteInfo[4][i].x, this.siteInfo[4][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("4号线", x, y+20);
        return;
      }
      c.closePath();

      for (let i in this.siteInfo[5]) {
         c.beginPath();
          c.arc(this.siteInfo[5][i].x, this.siteInfo[5][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[5][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[5][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[5][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[5][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[5]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[5][i].x, this.siteInfo[5][i].y);
        } else {
          c.lineTo(this.siteInfo[5][i].x, this.siteInfo[5][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("5号线", x, y+20);
        return;
      }
      c.closePath();

      for (let i in this.siteInfo[10]) {
         c.beginPath();
          c.arc(this.siteInfo[10][i].x, this.siteInfo[10][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[10][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[10][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[10][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[10][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[10]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[10][i].x, this.siteInfo[10][i].y);
        } else {
          c.lineTo(this.siteInfo[10][i].x, this.siteInfo[10][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("10号线", x, y+20);
        return;
      }
      c.closePath();

      for (let i in this.siteInfo[11]) {
         c.beginPath();
          c.arc(this.siteInfo[11][i].x, this.siteInfo[11][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[11][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[11][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[11][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[11][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[11]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[11][i].x, this.siteInfo[11][i].y);
        } else {
          c.lineTo(this.siteInfo[11][i].x, this.siteInfo[11][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("11号线", x, y+20);
        return;
      }
      c.closePath();

      for (let i in this.siteInfo[12]) {
         c.beginPath();
          c.arc(this.siteInfo[12][i].x, this.siteInfo[12][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          c.fillText('id:'+ this.siteInfo[12][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[12][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[12][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[12][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[12]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[12][i].x, this.siteInfo[12][i].y);
        } else {
          c.lineTo(this.siteInfo[12][i].x, this.siteInfo[12][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("12号线", x, y+20);
        return;
      }
      c.closePath();


    },
    switch(event){
      let x = event.clientX - this.canvas.getBoundingClientRect().left,
        y = event.clientY - this.canvas.getBoundingClientRect().top,
        c = this.c


      c.clearRect(0,0, this.canvas.width,this.canvas.width);

      for (let i in this.siteInfo[1]) {
        c.beginPath();
        c.arc(this.siteInfo[1][i].x, this.siteInfo[1][i].y, 6, 0, 2 * Math.PI);
        if (c.isPointInPath(x, y)) {
          // console.log(c.isPointInPath(x, y)+'----'+i)
          c.clearRect(0,0, this.canvas.width,this.canvas.width);
          c.fillStyle = "yellow"
          c.fillRect(x, y, 240, 82)
          c.fillStyle = "#333";
          c.font = 'bold 24px Arial'
          this.$router.push({
            path: 'echarts',
            query: {
              id: this.siteInfo[1][i].id,
            }
          });
          c.fillText('id:'+ this.siteInfo[1][i].id, x, y+20);
          c.fillText('station_name:'+ this.siteInfo[1][i].station_name, x, y+40);
          c.fillText('line_name:'+ this.siteInfo[1][i].line_name, x, y+60);
          c.fillText('district'+ this.siteInfo[1][i].district, x, y+80);
          return;
        }
        c.closePath();
      }

      c.beginPath();
      for (let i in this.siteInfo[1]) {
        c.lineWidth = 5;
        // c.strokeStyle = "#fff";
        if (i == 0) {
          c.moveTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y);
        } else {
          c.lineTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y);
        }
      }

      if (c.isPointInStroke(x, y)) {
        c.clearRect(0,0, this.canvas.width, this.canvas.width);
        c.fillStyle = "yellow"
        c.fillRect(x, y, 80, 22)
        c.fillStyle = "#333";
        c.font = 'bold 24px Arial'
        c.fillText("1号线", x, y+20);
        return;
      }
      c.closePath();
    },
  },
  components: {},
};
</script>

<style lang="stylus" scoped></style>
