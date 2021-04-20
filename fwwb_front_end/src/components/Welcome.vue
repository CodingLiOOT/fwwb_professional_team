<template>
  <div id="welcome">
    <div class="control">
      <div class="item">
        <div class="active">Sign in</div><div>Sign up</div>
      </div>
      <div class="content">
        <form action="http://localhost:8080/login" method="post" id="login_form">
          <table>
            <div style="display: block;">
              <p>Account</p>
              <input type="text" id="login_username_input" name="login_username_input" placeholder="username" style="width: 20rem"/>
              <span id="login_username_error" class="error"></span>
              <p>Password</p>
              <input type="password" id="login_password_input" name="login_password_input" placeholder="password" />
              <span id="login_password_error" class="error"></span>
              <br/><br/>
              <label><input type="checkbox" id="remeberyhm">记住密码</label>
              <br/>
              <input id="login_submit" type="submit" value="Login" />
            </div>
          </table>
        </form>

        <form action="http://localhost:8080/register" method="post" id="logon_form">
          <div>
            <p>Account</p>
            <input type="text" id="logon_username_input" name="logon_username_input" placeholder="username" />
            <span id="logon_username_error" class="error"></span>
            <p>Password</p>
            <input type="password" id="logon_password_input" name="logon_password_input" placeholder="password" />
            <span id="logon_password_error" class="error"></span>
            <br/>
            <input id="logon_submit" type="submit" value="Logon" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Welcome",
  mounted(){
    //选项卡
    var item = document.getElementsByClassName("item");
    var it = item[0].getElementsByTagName("div");

    var content = document.getElementsByClassName("content");
    var con = content[0].getElementsByTagName("div");

    for(let i=0;i<it.length;i++){
      it[i].onclick = function(){
        for(let j=0;j<it.length;j++){
          it[j].className = '';
          con[j].style.display = "none";
        }
        this.className = "active";
        it[i].index=i;
        con[i].style.display = "block";
      };
    }

    if (isPostBack == "False") {
      GetLastUser();
    }

    //若已有cookie，自动跳转
    var userNameValue=getCookieVal("name");
    var userPassValue=getCookieVal("value")
    if (userNameValue!=null&&userNameValue!=""&&userPassValue!=null&&userPassValue!=""){
      window.location.href="http://jwc.bjtu.edu.cn";
    }

    document.getElementById("login_username_input").onblur=checkAndFillPwd;
    document.getElementById("login_password_input").onblur=checkLoginPassword;
    document.getElementById("logon_username_input").onblur=checkLogonUsername;
    document.getElementById("logon_password_input").onblur=checkLogonPassword;
    document.getElementById("login_submit").onfocus=login;
    document.getElementById("logon_submit").onfocus=logon;
  },
  methods:{
          logon() {
        var obj={
          username: $("#logon_username_input").val(),
          password: $("#logon_password_input").val(),
        };
        if ((checkLogonUsername()&&checkLogonPassword())){

          //发送请求




        }else if(checkLogonPassword()){
          alert("用户名格式错误");
          window.location.href="http://localhost:8080/";
        }else if (checkLogonUsername()){
          alert("密码格式错误");
          window.location.href="http://localhost:8080/";
        }else {
          alert("用户名与密码格式错误");
          window.location.href="http://localhost:8080/";
        }
      },
          login(){
        //set cookie
        SetPwdAndChk();
        var obj={
          username: $("#login_username_input").val(),
          password: $("#login_password_input").val(),
        };
        if ((checkLoginUsername() && checkLoginPassword())) {
       //发送请求

        }else if (checkLoginPassword()) {
          alert("用户名格式错误");
          window.location.href = "http://localhost:8080/";
        } else if (checkLoginUsername()) {
          alert("密码格式错误");
          window.location.href = "http://localhost:8080/";
        } else {
          alert("用户名与密码格式错误");
          window.location.href = "http://localhost:8080/";
        }
      },

      checkAndFillPwd() {
        //检查格式
        checkLoginUsername();

        //cookie —— fill in password
        var usr = document.getElementById('login_username_input').value;
        var pwd = GetCookie(usr);
        if (pwd != null) {
          document.getElementById('remeberyhm').checked = true;
          document.getElementById('login_password_input').value = pwd;
        } else {
          document.getElementById('remeberyhm').checked = false;
          document.getElementById('login_password_input').value = "";
        }

      },

      // 校验用户名
      checkLoginUsername(){
        // 1.获取用户名的值
        var username = document.getElementById("login_username_input").value;
        // 2.定义正则表达式
        var reg_username = /^\w{6,12}$/;
        // 3.判断值是否符合正则的规则
        var flag = reg_username.test(username);

        // 4.提示信息
        var s_username= document.getElementById("login_username_error");
        if(flag){
          // 提示绿色对勾
          s_username.innerHTML = "<img width='20' height='20' src='images/gouhui.png'/>";
        }else{
          // 提示红色用户名有误
          s_username.innerHTML = "用户名格式有误:长度为6～12位";
          document.getElementById("login_username_input").focus();
        }

        return flag;
      },

      // 校验密码
      checkLoginPassword() {
        // 1.获取用户名的值
        var password = document.getElementById("login_password_input").value;
        // 2.定义正则表达式
        var reg_password = /^\w{6,12}$/;
        // 3.判断值是否符合正则的规则
        var flag = reg_password.test(password);
        // 4.提示信息
        var s_password = document.getElementById("login_password_error");
        if (flag) {
          // 提示绿色对勾
          s_password.innerHTML = "<img width='20' height='20' src='images/gouhui.png'/>";
        } else {
          // 提示红色用户名有误
          s_password.innerHTML = "密码格式有误:长度为6～12位";
          document.getElementById("login_password_input").focus();
        }

        return flag;
      },

      checkLogonUsername(){
        // 1.获取用户名的值
        var username = document.getElementById("logon_username_input").value;
        // 2.定义正则表达式
        var reg_username = /^\w{6,12}$/;
        // 3.判断值是否符合正则的规则
        var flag = reg_username.test(username);

        // 4.提示信息
        var s_username= document.getElementById("logon_username_error");
        if(flag){
          // 提示绿色对勾
          s_username.innerHTML = "<img width='20' height='20' src='images/gouhui.png'/>";
        }else{
          // 提示红色用户名有误
          s_username.innerHTML = "用户名格式有误:长度为6～12位";
          document.getElementById("logon_username_input").focus();
        }
        return flag;
      },

      checkLogonPassword() {
        // 1.获取用户名的值
        var password = document.getElementById("logon_password_input").value;
        // 2.定义正则表达式
        var reg_password = /^\w{6,12}$/;
        // 3.判断值是否符合正则的规则
        var flag = reg_password.test(password);
        // 4.提示信息
        var s_password = document.getElementById("logon_password_error");
        if (flag) {
          // 提示绿色对勾
          s_password.innerHTML = "<img width='20' height='20' src='images/gouhui.png'/>";
        } else {
          // 提示红色用户名有误
          s_password.innerHTML = "密码格式有误:长度为6～12位";
          document.getElementById("logon_password_input").focus();
        }
        return flag;
      },

}
}

</script>

<style scoped>
#welcome {
  background: url("../assets/bg/2-2.png") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
.card{
  margin: 10rem auto auto;
  background-color: rgba(54, 78, 147,0.5);
  height: 15rem;
  width: 25rem;
}
.word{
  margin-top: 5rem;
  color: white;
}
* {
  margin: 0;
  padding: 0;
}
.control{
  width: 24rem;
  background: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  border-radius: 5px;
}
.item{
  width: 24rem;
  height: 60px;
  background: #eeeeee;
}
.item div{
  width: 12rem;
  height: 60px;
  display: inline-block;
  color: black;
  font-size: 18px;
  text-align: center;
  line-height: 60px;
  cursor: pointer;
}
.content{
  width: 100%;
}
.content div{
  margin: 20px 30px;
  display: none;
  text-align: left;
}
p{
  color: #4a4a4a;
  margin-top: 30px;
  margin-bottom: 6px;
  font-size: 15px;
}
.content input[type="text"], .content input[type="password"]{
  width: 100%;
  height: 40px;
  border-radius: 3px;
  border: 1px solid #adadad;
  padding: 0 10px;
  box-sizing: border-box;
}
.content input[type="submit"]{
  margin-top: 40px;
  width: 100%;
  height: 40px;
  border-radius: 5px;
  color: white;
  border: 1px solid #f30606;
  background: #f30606;
  cursor: pointer;
  letter-spacing: 4px;
  margin-bottom: 40px;
}
.active{
  background: white;
}
.item div:hover{
  background: #f6f6f6;
}

.button {
  border-color: cornsilk;
  background-color: rgba(100, 149, 237, .7);
  color: aliceblue;
  border-style: hidden;
  border-radius: 5px;
  width: 100px;
  height: 31px;
  font-size: 16px;
}
.error{
  color: #f30606;
}
</style>
