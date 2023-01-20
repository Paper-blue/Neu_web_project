<template>
  <div style="width: 100%; height: 100vh;background-color: darkslateblue; overflow: hidden">
    <div style="width: 400px; margin: 100px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">注册</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="loginname">
          <el-input prefix-icon="el-icon-user-solid" v-model="form.loginname"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input prefix-icon="el-icon-lock" v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user-solid" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="mobile">
          <el-input prefix-icon="el-icon-mobile-phone" v-model="form.mobile"></el-input>
        </el-form-item>
        <el-form-item prop="roleid">
          <!--          <el-radio v-model="form.role" :label="1" style="color: #eee">管理员</el-radio>-->
          <!--          <el-radio v-model="form.role" :label="2" style="color: #eee">普通用户</el-radio>-->
          <el-radio v-model="form.roleid" label="2">云工厂管理员</el-radio>
          <el-radio v-model="form.roleid" label="3">经销商</el-radio>
          <!--            <el-input v-model="form.roleid"></el-input>-->
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注 册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {role: 1},
      rules: {
        loginname: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        confirm: [
          {required: true, message: '请输入确认密码', trigger: 'blur'},
        ],
        username:[
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        mobile:[
          {required: true, message: '选择输入联系方式', trigger: 'blur'},
        ],
        roleid:[
          {required: true, message: '选择职责', trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    sessionStorage.removeItem("user")
  },
  rules: {
    loginname: [
      {required: true, message: '请输入用户名', trigger: 'blur'},
    ],
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
    ],
    confirm: [
      {required: true, message: '请确认密码', trigger: 'blur'},
    ],
    username:[
      {required: true, message: '请输入姓名', trigger: 'blur'},
    ],
    mobile:[
      {required: true, message: '选择输入联系方式', trigger: 'blur'},
    ],
    roleid:[
      {required: true, message: '选择职责', trigger: 'blur'},
    ],
  },
  methods: {
    register() {
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: "密码不一致！"
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("http://localhost:9090/user/register", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "注册成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
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