<template>
  <div style="width: 100%; height: 100vh;background-color: darkslateblue; overflow: hidden">
    <div style="width: 400px; margin: 100px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="loginname">
          <el-input prefix-icon="el-icon-user-solid" v-model="form.loginname"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <!--          <el-radio v-model="form.role" :label="1" style="color: #eee">管理员</el-radio>-->
          <!--          <el-radio v-model="form.role" :label="2" style="color: #eee">普通用户</el-radio>-->
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
        <router-link to='/register'>
          <el-button style="width: 100%" type="primary" >点此注册</el-button>
        </router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      form: {
        loginname:'',
        password:''
      },
      rules: {
        loginname: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      }
    }
  },
  created() {
    sessionStorage.removeItem("user")
  },
  methods: {
    login() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("http://localhost:9090/user/login", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登录成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息

              console.log(res)
              sessionStorage.setItem("roleid",res.data.roleid)
              console.log("------------------")
              console.log(sessionStorage.getItem('roleid'))
              console.log(sessionStorage.getItem('user'))
              this.$router.push("/")  //登录成功之后进行页面的跳转，跳转到主页
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>