<template>
  <div class="homepage-hero-module">
    <div :style="fixStyle" class="filter"></div>
    <video :style="fixStyle" autoplay loop class="fillWidth" src="../components/overview/img/bg.mp4" v-on:canplay="canplay" muted="muted">
    </video>
    <img class="title" src="../components/overview/img/title.jpg" alt="">
<div class="loginPanel" >
  <div style="width: 300px;margin-left: 47px">
    <b style="color: white;font-size: 25px;float: left;margin-left: 18px">登录</b>
    <el-input
      placeholder="请输入账户"
      prefix-icon="el-icon-user-solid"
      size="small"
      v-model="account">
    </el-input>
    <el-input
      placeholder="请输入密码"
      prefix-icon="el-icon-s-cooperation"
      v-model="psw"
      size="small"
      show-password>
    </el-input>
    <el-button type="primary" @click="signIn">登录</el-button>
<div>
  <b style="color: white;font-size: 14px;display: inline-block">服务器状态：</b><b style="color: #2de37f;font-size: 14px;display: inline-block">正常</b>
  <b style="color: white;font-size: 14px;display: inline-block;margin-left: 100px">延迟：</b><b style="color: white;font-size: 14px;display: inline-block">5ms</b>
</div>
  </div>
  </div>
    <b class="slogn" style="color: white;font-size: 24px;">生活就像海洋，只有意志坚强的人才能到达彼岸——鲁迅</b>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data() {
      return {
        account:'',
        psw:''
      }
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('年龄不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      }
      return {
        vedioCanPlay: false,
        fixStyle: '',
        activeName: 'first',
        ruleForm: {
          name:'',
          pass: '',
          checkPass: '',
          age: ''
        },
        form:{
          name:'',
          pass:'',
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          age: [
            { validator: checkAge, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      canplay() {
        this.vedioCanPlay = true
      },
      signIn(){
        this.$router.push('/MainPage/overviewIndex');
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    // mounted: function() {
    //   window.onresize = () => {
    //     const windowWidth = document.body.clientWidth
    //     const windowHeight = document.body.clientHeight
    //     const windowAspectRatio = windowHeight / windowWidth
    //     let videoWidth
    //     let videoHeight
    //     if (windowAspectRatio < 0.5625) {
    //       videoWidth = windowWidth
    //       videoHeight = videoWidth * 0.5625
    //       this.fixStyle = {
    //         height: windowWidth * 0.5625 + 'px',
    //         width: windowWidth + 'px',
    //         'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
    //         'margin-left': 'initial'
    //       }
    //     } else {
    //       videoHeight = windowHeight
    //       videoWidth = videoHeight / 0.5625
    //       this.fixStyle = {
    //         height: windowHeight + 'px',
    //         width: windowHeight / 0.5625 + 'px',
    //         'margin-left': (windowWidth - videoWidth) / 2 + 'px',
    //         'margin-bottom': 'initial'
    //       }
    //     }
    //   }
    //   window.onresize()
    // }
  }
</script>


<style scoped>
  .homepage-hero-module,


  .video-container .poster img,
  .video-container video {
    z-index: 0;
    position: absolute;
    height: 100%;
    width : 100%;
    top: 0;
    left: 0;

  }

  .filter {
    z-index: 1;
    position: absolute;
    background: rgba(0, 0, 0, 0.4);
    height: 100%;
    width : 100%;
  }

  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    z-index: 10;
    width: 480px;
    position: absolute;
    top: 40%;
    left: 35%;
    font-size: 50px;
  }
  .loginPanel{
    z-index: 10;
    width: 400px;
    position: absolute;
    top: 32%;
    left: 36.5%;
    font-size: 50px;
    /* @Key 透明度 R G B Alpha（不透明度为50%） */
    background-color: rgba(4, 12, 40, 0.7);
    border: 2px solid rgba(114, 130, 208, 0.5);

    /* @Key 圆角矩形 */
    /*-moz-border-radius: 1em;*/
    /*-webkit-border-radius: 1em;*/
    border-radius: 0.2em;
    padding-bottom: 35px;
    padding-top: 40px;
  }
  .title{
    z-index: 5;
    position: absolute;
    top: 12%;
    left: 26.5%;
    width: 700px;
  }
  .demo-ruleForm{
    font-size: large;
  }
  .slogn{
    z-index: 5;
    position: absolute;
    top: 86%;
    left: 30.5%;
  }

</style>
