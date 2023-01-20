<template>
<!--  超级管理员的导航栏-->
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="user"
        router
        class="el-menu-vertical-demo"
    >

<!--      default-openeds="1-1,2-1,3-1,4-1,5-1,6-1"-->
      <el-submenu index="1-1" v-if="this.id=='1'">
        <template #title>系统设置</template>
        <el-menu-item index="user" >用户管理</el-menu-item>
      </el-submenu >
      <el-submenu index="2-1" v-if="this.id=='1'|| this.id=='2'">
        <template #title>云工厂</template>
        <el-menu-item index="/factory">云工厂信息</el-menu-item>
      </el-submenu >
      <el-submenu index="3-1" v-if="this.id=='1' || this.id=='2'" >
        <template #title>产品管理</template>
        <el-menu-item index="/producttype">产品类别管理</el-menu-item>
        <el-menu-item index="/product">产品信息管理</el-menu-item>
      </el-submenu >
      <el-submenu index="4-1" v-if="this.id=='1'||this.id=='2'">
        <template #title>产能中心</template>
        <el-menu-item index="/devicetype">设备类型管理管理</el-menu-item>
        <el-menu-item index="/device">设备管理</el-menu-item>
      </el-submenu >
      <el-submenu index="6-1" v-if="this.id=='1'||this.id=='3'">
        <template #title>订单管理</template>
        <el-menu-item index="/facorder">订单基本信息</el-menu-item>
      </el-submenu >

    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      path: this.$route.path,   // 设置默认高亮的菜单
      id:sessionStorage.getItem("roleid")
    }
  },
  created() {
    console.log(this.id);
  }
}
</script>

<style scoped>


</style>