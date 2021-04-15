<template>
  <div>
    <canvas width="1200" height="900" ref="sc"></canvas>
  </div>
</template>

<script>
export default {
  name: 'tuoputu',
  mounted() {
    const c = this.$refs.sc.getContext('2d')
    c.onclick = function () {
      console.log('test')
    }
    let r = 0

    c.beginPath()
    for (let i in this.siteInfo[1]) {
      c.lineWidth = 5
      c.strokeStyle = 'red'
      if (i == 0) {
        c.moveTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y)
      } else {
        c.lineTo(this.siteInfo[1][i].x, this.siteInfo[1][i].y)
      }
      c.stroke()
    }
    for (let i in this.siteInfo[1]) {
      c.beginPath()
      c.save()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[1][i].x, this.siteInfo[1][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[1][i].x, this.siteInfo[1][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      c.fontSize = '20'
      c.fillText(this.siteInfo[1][i].Station_name, this.siteInfo[1][i].x, this.siteInfo[1][i].y)
      c.restore()
    }
    c.beginPath()
    for (let i in this.siteInfo[2]) {
      c.lineWidth = 5
      c.strokeStyle = '#19BC9D'
      if (i == 0) {
        c.moveTo(this.siteInfo[2][i].x, this.siteInfo[2][i].y)
      } else {
        c.lineTo(this.siteInfo[2][i].x, this.siteInfo[2][i].y)
      }
    }
    c.lineTo(888, 428)
    c.stroke()

    for (let i in this.siteInfo[2]) {
      c.save()
      c.beginPath()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[2][i].x, this.siteInfo[2][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[2][i].x, this.siteInfo[2][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f8f5f5'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[2][i].Station_name).width
      c.fillText(this.siteInfo[2][i].Station_name, this.siteInfo[2][i].x, this.siteInfo[2][i].y)
      c.restore()
    }

    c.beginPath()
    for (let i in this.siteInfo[3]) {
      c.lineWidth = 5
      c.strokeStyle = '#3D96D0'
      if (i == 0) {
        c.moveTo(this.siteInfo[3][i].x, this.siteInfo[3][i].y)
      } else {
        c.lineTo(this.siteInfo[3][i].x, this.siteInfo[3][i].y)
      }
      c.stroke()
    }

    for (let i in this.siteInfo[3]) {
      c.save()
      c.beginPath()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[3][i].x, this.siteInfo[3][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[3][i].x, this.siteInfo[3][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[3][i].Station_name).width
      c.fillText(this.siteInfo[3][i].Station_name, this.siteInfo[3][i].x, this.siteInfo[3][i].y)
      c.restore()
    }

    c.beginPath()
    for (let i in this.siteInfo[4]) {
      c.lineWidth = 5
      c.strokeStyle = '#FDAF05'
      if (i == 0) {
        c.moveTo(this.siteInfo[4][i].x, this.siteInfo[4][i].y)
      } else {
        c.lineTo(this.siteInfo[4][i].x, this.siteInfo[4][i].y)
      }
      c.stroke()
    }

    for (let i in this.siteInfo[4]) {
      c.save()
      c.beginPath()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[4][i].x, this.siteInfo[4][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[4][i].x, this.siteInfo[4][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[4][i].Station_name).width
      c.fillText(this.siteInfo[4][i].Station_name, this.siteInfo[4][i].x, this.siteInfo[4][i].y)
      c.restore()
    }

    c.beginPath()
    for (let i in this.siteInfo[5]) {
      c.lineWidth = 5
      c.strokeStyle = '#527292'
      if (i == 0) {
        c.moveTo(this.siteInfo[5][i].x, this.siteInfo[5][i].y)
      } else {
        c.lineTo(this.siteInfo[5][i].x, this.siteInfo[5][i].y)
      }
      c.stroke()
    }

    for (let i in this.siteInfo[5]) {
      c.save()
      c.beginPath()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[5][i].x, this.siteInfo[5][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[5][i].x, this.siteInfo[5][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[5][i].Station_name).width
      c.fillText(this.siteInfo[5][i].Station_name, this.siteInfo[5][i].x, this.siteInfo[5][i].y)
      c.restore()
    }

    c.beginPath()
    for (let i in this.siteInfo[10]) {
      c.lineWidth = 5
      c.strokeStyle = '#2b6dee'
      if (i == 0) {
        c.moveTo(this.siteInfo[10][i].x, this.siteInfo[10][i].y)
      } else {
        c.lineTo(this.siteInfo[10][i].x, this.siteInfo[10][i].y)
      }
      c.stroke()
    }

    for (let i in this.siteInfo[10]) {
      c.save()
      c.beginPath()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[10][i].x, this.siteInfo[10][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[10][i].x, this.siteInfo[10][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[10][i].Station_name).width
      c.fillText(this.siteInfo[10][i].Station_name, this.siteInfo[10][i].x, this.siteInfo[10][i].y)
      c.restore()
    }

    c.beginPath()
    for (let i in this.siteInfo[11]) {
      c.lineWidth = 5
      c.strokeStyle = '#DE7095'
      if (i == 0) {
        c.moveTo(this.siteInfo[11][i].x, this.siteInfo[11][i].y)
      } else {
        c.lineTo(this.siteInfo[11][i].x, this.siteInfo[11][i].y)
      }
      c.stroke()
    }

    for (let i in this.siteInfo[11]) {
      c.beginPath()
      c.save()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[11][i].x, this.siteInfo[11][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[11][i].x, this.siteInfo[11][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[11][i].Station_name).width
      c.fillText(this.siteInfo[11][i].Station_name, this.siteInfo[11][i].x, this.siteInfo[11][i].y)
      c.restore()
    }

    c.beginPath()
    for (let i in this.siteInfo[12]) {
      c.lineWidth = 5
      c.strokeStyle = '#F7D7CC'
      if (i == 0) {
        c.moveTo(this.siteInfo[12][i].x, this.siteInfo[12][i].y)
      } else {
        c.lineTo(this.siteInfo[12][i].x, this.siteInfo[12][i].y)
      }
      c.stroke()
    }

    for (let i in this.siteInfo[12]) {
      c.save()
      c.beginPath()
      c.lineWidth = 1
      c.strokeStyle = 'blue'
      c.arc(this.siteInfo[12][i].x, this.siteInfo[12][i].y, 6, 0, 2 * Math.PI)
      c.stroke()
      c.fillStyle = '#EAF1F9'
      c.arc(this.siteInfo[12][i].x, this.siteInfo[12][i].y, 5, 0, 2 * Math.PI)
      c.fill()
      c.fillStyle = '#f3eded'
      console.log(i)
      let strWidth = c.measureText(this.siteInfo[12][i].Station_name).width
      c.fillText(this.siteInfo[12][i].Station_name, this.siteInfo[12][i].x, this.siteInfo[12][i].y)
      c.restore()
    }

    c.fillStyle = '#EAF1F9'
    c.fillRect(32, 64, 51, 21)
    c.fillStyle = '#333'
    c.fillText('11号线', 40, 77)

    c.fillStyle = '#EAF1F9'
    c.fillRect(293, 48, 51, 21)
    c.fillStyle = '#333'
    c.fillText('10号线', 300, 61)

    c.fillStyle = '#EAF1F9'
    c.fillRect(978, 155, 51, 21)
    c.fillStyle = '#333'
    c.fillText('4号线', 991, 170)

    c.fillStyle = '#EAF1F9'
    c.fillRect(62, 204, 51, 21)
    c.fillStyle = '#333'
    c.fillText('1号线', 76, 218)

    c.fillStyle = '#EAF1F9'
    c.fillRect(248, 503, 51, 21)
    c.fillStyle = '#333'
    c.fillText('12号线', 260, 519)

    c.fillStyle = '#EAF1F9'
    c.fillRect(431, 552, 51, 21)
    c.fillStyle = '#333'
    c.fillText('5号线', 446, 567)

    c.fillStyle = '#EAF1F9'
    c.fillRect(367, 724, 51, 21)
    c.fillStyle = '#333'
    c.fillText('2号线', 382, 739)

    c.fillStyle = '#EAF1F9'
    c.fillRect(896, 730, 51, 21)
    c.fillStyle = '#333'
    c.fillText('3号线', 910, 747)

  },
  data() {
    return {
      siteInfo: {
        1: [
          {
            id: 1170,
            Station_name: '  Sta104',
            line_name: '1号线',
            district: 'Dist1',
            sequence: 1,
            x: 107,
            y: 292,
          },
          {
            id: 1024,
            Station_name: '  Sta65',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 2,
            x: 120,
            y: 337,
          },
          {
            id: 1023,
            Station_name: '  Sta49',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 3,
            x: 130,
            y: 378,
          },
          {
            id: 1022,
            Station_name: '  Sta149',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 4,
            x: 161,
            y: 430,
          },
          {
            id: 1021,
            Station_name: '  Sta74',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 5,
            x: 210,
            y: 430,
          },
          {
            id: 1020,
            Station_name: '  Sta128',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 6,
            x: 285,
            y: 430,
          },
          {
            id: 1019,
            Station_name: '  Sta34',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 7,
            x: 320,
            y: 430,
          },
          {
            id: 1018,
            Station_name: '  Sta106',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 8,
            x: 378,
            y: 430,
          },
          {
            id: 1017,
            Station_name: '  Sta110',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 9,
            x: 417,
            y: 430,
          },
          {
            id: 1016,
            Station_name: '  Sta97',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 10,
            x: 461,
            y: 430,
          },
          {
            id: 1015,
            Station_name: '  Sta80',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 11,
            x: 519,
            y: 430,
          },
          {
            id: 1014,
            Station_name: '  Sta89',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 12,
            x: 564,
            y: 430,
          },
          {
            id: 1013,
            Station_name: '  Sta64',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 13,
            x: 669,
            y: 430,//429应该没有64 1号
          },
          {
            id: 1012,
            Station_name: '  Sta150',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 14,
            x: 737,
            y: 430,
          },
          {
            id: 1011,
            Station_name: '  Sta154',
            line_name: '1号线',
            district: 'Dist3',
            sequence: 15,
            x: 822,
            y: 430,
          },
          {
            id: 1010,
            Station_name: '  Sta107',
            line_name: '1号线',
            district: 'Dist2',
            sequence: 16,
            x: 885,
            y: 430,
          },
          {
            id: 1009,
            Station_name: '  Sta83',
            line_name: '1号线',
            district: 'Dist2',
            sequence: 17,
            x: 941,
            y: 430,
          },
          {
            id: 1008,
            Station_name: '  Sta108',
            line_name: '1号线',
            district: 'Dist1',
            sequence: 18,
            x: 986,
            y: 430,
          },
          {
            id: 1006,
            Station_name: '  Sta159',
            line_name: '1号线',
            district: 'Dist1',
            sequence: 19,
            x: 1041,
            y: 430,
          },
          {
            id: 1004,
            Station_name: '  Sta1',
            line_name: '1号线',
            district: 'Dist1',
            sequence: 20,
            x: 1117,
            y: 430,
          },
        ],
        2: [
          {
            id: 1046,
            Station_name: '  Sta51',
            line_name: '2号线',
            district: 'Dist7',
            sequence: 1,
            x: 428,
            y: 739,
          },
          {
            id: 1045,
            Station_name: '  Sta105',
            line_name: '2号线',
            district: 'Dist4',
            sequence: 2,
            x: 463,
            y: 734,
          },
          {
            id: 1044,
            Station_name: '  Sta24',
            line_name: '2号线',
            district: 'Dist6',
            sequence: 3,
            x: 496,
            y: 724,
          },
          {
            id: 1043,
            Station_name: '  Sta139',
            line_name: '2号线',
            district: 'Dist5',
            sequence: 4,
            x: 523,
            y: 708,
          },
          {
            id: 1042,
            Station_name: '  Sta71',
            line_name: '2号线',
            district: 'Dist4',
            sequence: 5,
            x: 521,
            y: 678,
          },
          {
            id: 1041,
            Station_name: '  Sta57',
            line_name: '2号线',
            district: 'Dist4',
            sequence: 6,
            x: 523,
            y: 644,
          },
          {
            id: 1040,
            Station_name: '  Sta76',
            line_name: '2号线',
            district: 'Dist4',
            sequence: 7,
            x: 522,
            y: 611,
          },
          {
            id: 1039,
            Station_name: '  Sta52',
            line_name: '2号线',
            district: 'Dist4',
            sequence: 8,
            x: 521,
            y: 582,
          },
          {
            id: 1038,
            Station_name: '  Sta68',
            line_name: '2号线',
            district: 'Dist4',
            sequence: 9,
            x: 522,
            y: 545,
          },
          {
            id: 1037,
            Station_name: '  Sta151',
            line_name: '2号线',
            district: 'Dist2',
            sequence: 10,
            x: 521,
            y: 515,
          },
          {
            id: 1036,
            Station_name: '  Sta48',
            line_name: '2号线',
            district: 'Dist2',
            sequence: 11,
            x: 529,
            y: 491,
          },
          {
            id: 1035,
            Station_name: '  Sta27',
            line_name: '2号线',
            district: 'Dist2',
            sequence: 12,
            x: 543,
            y: 466,
          },
          {
            id: 1034,
            Station_name: '  Sta81',
            line_name: '2号线',
            district: 'Dist2',
            sequence: 13,
            x: 581,
            y: 403,
          },
          {
            id: 1033,
            Station_name: '  Sta127',
            line_name: '2号线',
            district: 'Dist2',
            sequence: 14,
            x: 598,
            y: 369,
          },
          {
            id: 1032,
            Station_name: '  Sta123',
            line_name: '2号线',
            district: 'Dist2',
            sequence: 15,
            x: 635,
            y: 371,
          },
          {
            id: 1171,
            Station_name: '  Sta47',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 16,
            x: 663,
            y: 372,
          },
          {
            id: 1031,
            Station_name: '  Sta18',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 17,
            x: 709,
            y: 372,
          },
          {
            id: 1030,
            Station_name: '  Sta79',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 18,
            x: 742,
            y: 371,
          },
          {
            id: 1028,
            Station_name: '  Sta78',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 19,
            x: 775,
            y: 369,
          },
          {
            id: 1027,
            Station_name: '  Sta53',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 20,
            x: 816,
            y: 369,
          },
          {
            id: 1026,
            Station_name: '  Sta163',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 21,
            x: 841,
            y: 369,
          },
          {
            id: 1025,
            Station_name: '  Sta9',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 22,
            x: 858,
            y: 390,
          },
          {
            id: 1170,
            Station_name: '  Sta129',
            line_name: '2号线',
            district: 'Dist1',
            sequence: 23,
            x: 880,
            y: 415,
          },
        ],
        3: [
          {
            id: 1172,
            Station_name: '  Sta155',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 1,
            x: 921,
            y: 732,
          },
          {
            id: 1048,
            Station_name: '  Sta143',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 2,
            x: 920,
            y: 703,
          },
          {
            id: 1049,
            Station_name: '  Sta156',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 3,
            x: 920,
            y: 682,
          },
          {
            id: 1050,
            Station_name: '  Sta61',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 4,
            x: 920,
            y: 650,
          },
          {
            id: 1051,
            Station_name: '  Sta50',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 5,
            x: 923,
            y: 619,
          },
          {
            id: 1052,
            Station_name: '  Sta119',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 6,
            x: 911,
            y: 596,
          },
          {
            id: 1053,
            Station_name: '  Sta66',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 7,
            x: 892,
            y: 576,
          },
          {
            id: 1054,
            Station_name: '  Sta12',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 8,
            x: 877,
            y: 564,
          },
          {
            id: 1055,
            Station_name: '  Sta161',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 9,
            x: 858,
            y: 540,
          },
          {
            id: 1056,
            Station_name: '  Sta21',
            line_name: '3号线',
            district: 'Dist7',
            sequence: 10,
            x: 838,
            y: 523,
          },
          {
            id: 1057,
            Station_name: '  Sta133',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 11,
            x: 824,
            y: 515,
          },
          {
            id: 1058,
            Station_name: '  Sta22',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 12,
            x: 813,
            y: 505,
          },
          {
            id: 1059,
            Station_name: '  Sta138',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 13,
            x: 806,
            y: 492,
          },
          {
            id: 1060,
            Station_name: '  Sta41',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 14,
            x: 785,
            y: 475,
          },
          {
            id: 1061,
            Station_name: '  Sta30',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 15,
            x: 769,
            y: 461,
          },
          {
            id: 1062,
            Station_name: '  Sta67',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 16,
            x: 728,
            y: 413,
          },
          {
            id: 1063,
            Station_name: '  Sta144',
            line_name: '3号线',
            district: 'Dist6',
            sequence: 17,
            x: 686,
            y: 372,
          },
          {
            id: 1173,
            Station_name: '  Sta5',
            line_name: '3号线',
            district: 'Dist8',
            sequence: 18,
            x: 669,
            y: 351,
          },
          {
            id: 1174,
            Station_name: '  Sta98',
            line_name: '3号线',
            district: 'Dist8',
            sequence: 19,
            x: 662,
            y: 330,
          },
          {
            id: 1064,
            Station_name: '  Sta29',
            line_name: '3号线',
            district: 'Dist8',
            sequence: 20,
            x: 660,
            y: 267,
          },
          {
            id: 1065,
            Station_name: '  Sta126',
            line_name: '3号线',
            district: 'Dist1',
            sequence: 21,
            x: 656,
            y: 231,
          },
          {
            id: 1067,
            Station_name: '  Sta40',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 22,
            x: 660,
            y: 212,
          },
          {
            id: 1068,
            Station_name: '  Sta131',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 23,
            x: 658,
            y: 184,
          },
          {
            id: 1069,
            Station_name: '  Sta39',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 24,
            x: 657,
            y: 143,
          },
          {
            id: 1070,
            Station_name: '  Sta100',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 25,
            x: 661,
            y: 110,
          },
          {
            id: 1072,
            Station_name: '  Sta167',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 26,
            x: 686,
            y: 102,
          },
          {
            id: 1073,
            Station_name: '  Sta113',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 27,
            x: 708,
            y: 94,
          },
          {
            id: 1074,
            Station_name: '  Sta141',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 28,
            x: 754,
            y: 80,
          },
          {
            id: 1075,
            Station_name: '  Sta142',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 29,
            x: 797,
            y: 74,
          },
          {
            id: 1076,
            Station_name: '  Sta158',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 30,
            x: 847,
            y: 61,
          },
          {
            id: 1077,
            Station_name: '  Sta44',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 31,
            x: 872,
            y: 54,
          },
          {
            id: 1078,
            Station_name: '  Sta117',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 32,
            x: 897,
            y: 48,
          },
          {
            id: 1079,
            Station_name: '  Sta147',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 33,
            x: 924,
            y: 39,
          },
          {
            id: 1080,
            Station_name: '  Sta42',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 34,
            x: 953,
            y: 36,
          },
          {
            id: 1081,
            Station_name: '  Sta35',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 35,
            x: 976,
            y: 33,
          },
          {
            id: 1082,
            Station_name: '  Sta109',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 36,
            x: 997,
            y: 40,
          },
          {
            id: 1084,
            Station_name: '  Sta33',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 37,
            x: 1012,
            y: 54,
          },
          {
            id: 1085,
            Station_name: '  Sta112',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 38,
            x: 1024,
            y: 64,
          },
          {
            id: 1086,
            Station_name: '  Sta153',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 39,
            x: 1040,
            y: 76,
          },
          {
            id: 1087,
            Station_name: '  Sta125',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 40,
            x: 1083,
            y: 102,
          },
          {
            id: 1088,
            Station_name: '  Sta121',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 41,
            x: 1066,
            y: 90,
          },
          {
            id: 1089,
            Station_name: '  Sta125',
            line_name: '3号线',
            district: 'Dist5',
            sequence: 42,
            x: 1083,
            y: 102,
          },
          {
            id: 9993,
            Station_name: '  Sta7722',
            line_name: '3号线',
            district: 'Dist1244',
            sequence: 9993,
            x: 1106,
            y: 114,
          },
        ],
        4: [
          {
            id: 1188,
            Station_name: '  Sta84',
            line_name: '4号线',
            district: 'Dist5',
            sequence: 1,
            x: 624,
            y: 165,
          },
          {
            id: 1092,
            Station_name: '  Sta59',
            line_name: '4号线',
            district: 'Dist8',
            sequence: 2,
            x: 660,
            y: 165,
          },
          {
            id: 1093,
            Station_name: '  Sta19',
            line_name: '4号线',
            district: 'Dist8',
            sequence: 3,
            x: 718,
            y: 165,
          },
          {
            id: 1094,
            Station_name: '  Sta62',
            line_name: '4号线',
            district: 'Dist8',
            sequence: 4,
            x: 815,
            y: 165,
          },
          {
            id: 1095,
            Station_name: '  Sta165',
            line_name: '4号线',
            district: 'Dist8',
            sequence: 5,
            x: 865,
            y: 165,
          },
          {
            id: 1096,
            Station_name: '  Sta58',
            line_name: '4号线',
            district: 'Dist8',
            sequence: 6,
            x: 918,
            y: 165,
          },
          {
            id: 1097,
            Station_name: '  Sta38',
            line_name: '4号线',
            district: 'Dist8',
            sequence: 7,
            x: 967,
            y: 166,
          },
        ],
        5: [
          {
            id: 1107,
            Station_name: '  Sta54',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 1,
            x: 458,
            y: 545,
          },
          {
            id: 1104,
            Station_name: '  Sta69',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 2,
            x: 458,
            y: 490,
          },
          {
            id: 1103,
            Station_name: '  Sta16',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 3,
            x: 456,
            y: 373,
          },
          {
            id: 1102,
            Station_name: '  Sta37',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 4,
            x: 456,
            y: 331,
          },
          {
            id: 1101,
            Station_name: '  Sta132',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 5,
            x: 458,
            y: 253,
          },
          {
            id: 1100,
            Station_name: '  Sta96',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 6,
            x: 475,
            y: 205,
          },
          {
            id: 1099,
            Station_name: '  Sta10',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 7,
            x: 475,
            y: 205,
          },
          {
            id: 1098,
            Station_name: '  Sta43',
            line_name: '5号线',
            district: 'Dist5',
            sequence: 8,
            x: 548,
            y: 146,
          },
        ],
        10: [
          {
            id: 1149,
            Station_name: '  Sta157',
            line_name: '10号线',
            district: 'Dist8',
            sequence: 1,
            x: 363,
            y: 66,
          },
          {
            id: 1185,
            Station_name: '  Sta114',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 2,
            x: 435,
            y: 68,
          },
          {
            id: 1150,
            Station_name: '  Sta168',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 3,
            x: 486,
            y: 68,
          },
          {
            id: 1186,
            Station_name: '  Sta135',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 4,
            x: 552,
            y: 68,
          },
          {
            id: 1091,
            Station_name: '  Sta134',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 5,
            x: 600,
            y: 68,
          },
          {
            id: 1151,
            Station_name: '  Sta85',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 6,
            x: 690,
            y: 65,
          },
          {
            id: 1152,
            Station_name: '  Sta2',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 7,
            x: 775,
            y: 122,
          },
          {
            id: 1153,
            Station_name: '  Sta4',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 8,
            x: 759,
            y: 200,
          },
          {
            id: 1154,
            Station_name: '  Sta103',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 9,
            x: 718,
            y: 211,
          },
          {
            id: 1155,
            Station_name: '  Sta145',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 10,
            x: 656,
            y: 231,
          },
          {
            id: 1156,
            Station_name: '  Sta88',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 11,
            x: 620,
            y: 240,
          },
          {
            id: 1187,
            Station_name: '  Sta87',
            line_name: '10号线',
            district: 'Dist8',
            sequence: 12,
            x: 621,
            y: 287,
          },
          {
            id: 1157,
            Station_name: '  Sta94',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 13,
            x: 621,
            y: 342,
          },
          {
            id: 1158,
            Station_name: '  Sta160',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 14,
            x: 621,
            y: 429,
          },
          {
            id: 1159,
            Station_name: '  Sta7',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 15,
            x: 620,
            y: 476,
          },
          {
            id: 1160,
            Station_name: '  Sta6',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 16,
            x: 621,
            y: 525,
          },
          {
            id: 1161,
            Station_name: '  Sta8',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 17,
            x: 620,
            y: 561,
          },
          {
            id: 1136,
            Station_name: '  Sta75',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 18,
            x: 620,
            y: 600,
          },
          {
            id: 1162,
            Station_name: '  Sta102',
            line_name: '10号线',
            district: 'Dist5',
            sequence: 19,
            x: 621,
            y: 666,
          },
        ],
        11: [
          {
            id: 1131,
            Station_name: '  Sta28',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 1,
            x: 100,
            y: 80,
          },
          {
            id: 1130,
            Station_name: '  Sta124',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 2,
            x: 137,
            y: 74,
          },
          {
            id: 1129,
            Station_name: '  Sta99',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 3,
            x: 173,
            y: 99,
          },
          {
            id: 1128,
            Station_name: '  Sta166',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 4,
            x: 196,
            y: 124,
          },
          {
            id: 1127,
            Station_name: '  Sta36',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 5,
            x: 203,
            y: 135,
          },
          {
            id: 1126,
            Station_name: '  Sta122',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 6,
            x: 246,
            y: 174,
          },
          {
            id: 1125,
            Station_name: '  Sta77',
            line_name: '11号线',
            district: 'Dist10',
            sequence: 7,
            x: 267,
            y: 191,
          },
          {
            id: 1124,
            Station_name: '  Sta140',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 8,
            x: 285,
            y: 217,
          },
          {
            id: 1191,
            Station_name: '  Sta111',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 9,
            x: 308,
            y: 239,
          },
          {
            id: 1122,
            Station_name: '  Sta13',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 10,
            x: 324,
            y: 258,
          },
          {
            id: 1121,
            Station_name: '  Sta70',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 11,
            x: 347,
            y: 274,
          },
          {
            id: 1120,
            Station_name: '  Sta55',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 12,
            x: 380,
            y: 287,
          },
          {
            id: 1119,
            Station_name: '  Sta20',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 13,
            x: 417,
            y: 287,
          },
          {
            id: 1175,
            Station_name: '  Sta23',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 14,
            x: 457,
            y: 288,
          },
          {
            id: 1176,
            Station_name: '  Sta56',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 15,
            x: 457,
            y: 288,
          },
          {
            id: 1118,
            Station_name: '  Sta118',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 16,
            x: 526,
            y: 288,
          },
          {
            id: 1177,
            Station_name: '  Sta115',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 17,
            x: 565,
            y: 288,
          },
          {
            id: 1117,
            Station_name: '  Sta162',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 18,
            x: 621,
            y: 287,
          },
          {
            id: 1115,
            Station_name: '  Sta15',
            line_name: '11号线',
            district: 'Dist8',
            sequence: 19,
            x: 659,
            y: 288,
          },
          {
            id: 1114,
            Station_name: '  Sta86',
            line_name: '11号线',
            district: 'Dist8',
            sequence: 20,
            x: 709,
            y: 288,
          },
          {
            id: 1113,
            Station_name: '  Sta46',
            line_name: '11号线',
            district: 'Dist8',
            sequence: 21,
            x: 741,
            y: 288,
          },
          {
            id: 1178,
            Station_name: '  Sta63',
            line_name: '11号线',
            district: 'Dist1',
            sequence: 22,
            x: 773,
            y: 288,
          },
          {
            id: 1112,
            Station_name: '  Sta3',
            line_name: '11号线',
            district: 'Dist6',
            sequence: 23,
            x: 806,
            y: 288,
          },
          {
            id: 1111,
            Station_name: '  Sta25',
            line_name: '11号线',
            district: 'Dist6',
            sequence: 24,
            x: 835,
            y: 288,
          },
          {
            id: 1110,
            Station_name: '  Sta146',
            line_name: '11号线',
            district: 'Dist6',
            sequence: 25,
            x: 862,
            y: 287,
          },
          {
            id: 1109,
            Station_name: '  Sta130',
            line_name: '11号线',
            district: 'Dist6',
            sequence: 26,
            x: 892,
            y: 304,
          },
          {
            id: 1108,
            Station_name: '  Sta120',
            line_name: '11号线',
            district: 'Dist6',
            sequence: 27,
            x: 918,
            y: 319,
          },
          {
            id: 1132,
            Station_name: '  Sta82',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 28,
            x: 950,
            y: 337,
          },
          {
            id: 1133,
            Station_name: '  Sta164',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 29,
            x: 976,
            y: 351,
          },
          {
            id: 1134,
            Station_name: '  Sta152',
            line_name: '11号线',
            district: 'Dist1',
            sequence: 30,
            x: 1012,
            y: 374,
          },
          {
            id: 1135,
            Station_name: '  Sta45',
            line_name: '11号线',
            district: 'Dist5',
            sequence: 31,
            x: 1050,
            y: 395,
          },
        ],
        12: [
          {
            id: 1175,
            Station_name: '  Sta14',
            line_name: '12号线',
            district: 'Dist2',
            sequence: 1,
            x: 366,
            y: 508,
          },
          {
            id: 1174,
            Station_name: '  Sta73',
            line_name: '12号线',
            district: 'Dist2',
            sequence: 2,
            x: 315,
            y: 507,
          },
          {
            id: 1173,
            Station_name: '  Sta148',
            line_name: '12号线',
            district: 'Dist2',
            sequence: 3,
            x: 304,
            y: 464,
          },
          {
            id: 1172,
            Station_name: '  Sta60',
            line_name: '12号线',
            district: 'Dist2',
            sequence: 4,
            x: 285,
            y: 380,
          },
          {
            id: 1147,
            Station_name: '  Sta91',
            line_name: '12号线',
            district: 'Dist6',
            sequence: 5,
            x: 266,
            y: 329,
          },
          {
            id: 1146,
            Station_name: '  Sta32',
            line_name: '12号线',
            district: 'Dist6',
            sequence: 6,
            x: 253,
            y: 270,
          },
          {
            id: 1169,
            Station_name: '  Sta116',
            line_name: '12号线',
            district: 'Dist6',
            sequence: 7,
            x: 363,
            y: 222,
          },
          {
            id: 1145,
            Station_name: '  Sta92',
            line_name: '12号线',
            district: 'Dist6',
            sequence: 8,
            x: 408,
            y: 220,
          },
          {
            id: 1165,
            Station_name: '  Sta31',
            line_name: '12号线',
            district: 'Dist8',
            sequence: 8,
            x: 544,
            y: 225,
          },
          {
            id: 1144,
            Station_name: '  Sta93',
            line_name: '12号线',
            district: 'Dist6',
            sequence: 9,
            x: 797,
            y: 221,
          },
          {
            id: 1166,
            Station_name: '  Sta72',
            line_name: '12号线',
            district: 'Dist6',
            sequence: 10,
            x: 861,
            y: 221,
          },
          {
            id: 1143,
            Station_name: '  Sta95',
            line_name: '12号线',
            district: 'Dist5',
            sequence: 11,
            x: 832,
            y: 327,
          },
          {
            id: 1142,
            Station_name: '  Sta90',
            line_name: '12号线',
            district: 'Dist5',
            sequence: 12,
            x: 816,
            y: 369,
          },
          {
            id: 1141,
            Station_name: '  Sta26',
            line_name: '12号线',
            district: 'Dist5',
            sequence: 13,
            x: 755,
            y: 511,
          },
          {
            id: 1140,
            Station_name: '  Sta17',
            line_name: '12号线',
            district: 'Dist8',
            sequence: 14,
            x: 709,
            y: 512,
          },
          {
            id: 1139,
            Station_name: '  Sta101',
            line_name: '12号线',
            district: 'Dist8',
            sequence: 15,
            x: 667,
            y: 507,
          },
          {
            id: 1137,
            Station_name: '  Sta136',
            line_name: '12号线',
            district: 'Dist3',
            sequence: 16,
            x: 581,
            y: 508,
          },
          {
            id: 1138,
            Station_name: '  Sta137',
            line_name: '12号线',
            district: 'Dist3',
            sequence: 17,
            x: 416,
            y: 508,
          },
          {
            id: 1175,
            Station_name: '  Sta14',
            line_name: '12号线',
            district: 'Dist2',
            sequence: 1,
            x: 366,
            y: 508,
          },
        ],
      },
    }
  },
  methods: {
    drawImage(c, e) {
      let ev = e || event
      console.log(ev)

    }
  },
  components: {},
}
</script>

<style lang="stylus" scoped>

</style>
