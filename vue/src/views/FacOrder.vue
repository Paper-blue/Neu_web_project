<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>

    </div>

    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input
          style="width: 20%"
          placeholder="请输入搜寻的状态"
          v-model="search"
          clearable>
      </el-input>
      <el-button type="primary" style="margin-left: 10px" @click="load">查询</el-button>
    </div>
    <!--    展示区域-->
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          fixed
          prop="id"
          label="ID"
          width="50">
      </el-table-column>
      <el-table-column
          prop="orderno"
          label="订单编码"
          width="150">
      </el-table-column>
      <el-table-column
          prop="productid"
          label="产品id"
          width="200">
      </el-table-column>
      <el-table-column
          prop="ordernum"
          label="订购数量"
          width="100">
      </el-table-column>
      <el-table-column
          prop="deaddate"
          label="交付日期"
          width="200">
      </el-table-column>
      <el-table-column
          prop="deliverdate"
          label="投标截止日期"
          width="300">
      </el-table-column>
      <el-table-column
          prop="orderstatus"
          label="订单状态"
          width="100">
      </el-table-column>
      <el-table-column
          prop="factoryid"
          label="中标工厂ID"
          width="100">
      </el-table-column>
      <el-table-column
          prop="receipt"
          label="收据"
          width="100">
      </el-table-column>
      <el-table-column
          prop="userid"
          label="收货人id"
          width="100">
      </el-table-column>
      <el-table-column
          prop="recname"
          label="收货人"
          width="100">
      </el-table-column>
      <el-table-column
          prop="contid"
          label="联系id"
          width="100">
      </el-table-column>
      <el-table-column
          prop="contact"
          label="联系人"
          width="100">
      </el-table-column>



      <el-table-column
          fixed="right"
          label="操作"
          width="200">
        <template slot-scope="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>

<!--          <el-popconfirm-->
<!--              confirm-button-text='好的'-->
<!--              cancel-button-text='不用了'-->
<!--              icon="el-icon-info"-->
<!--              icon-color="red"-->
<!--              title="确定删除吗？"-->
<!--              @confirm="handleDelete(scope.row.id)"-->
<!--          >-->
<!--            <el-button  style="margin-left: 10px" type="danger" size="small" slot="reference" >删除</el-button>-->
<!--          </el-popconfirm>-->
        </template>
      </el-table-column>
    </el-table>

    <!--    最外层-->
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

      <el-dialog
          title="提示"
          :visible.sync="dialogVisible"
          width="30%"
          >
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item style="width: 70%" label="订单编码">
            <el-input v-model="form.orderno"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="产品id">
            <el-input v-model="form.productid"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="订单数量">
            <el-input v-model="form.ordernum"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="交付日期">
            <el-input v-model="form.deaddate"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="投标截止日期">
            <el-input v-model="form.deliverdate"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="订单状态">
            <el-radio v-model="form.orderstatus" label="1">保存</el-radio>
            <el-radio v-model="form.orderstatus" label="2">已发布</el-radio>
            <el-radio v-model="form.orderstatus" label="4">投标结束</el-radio>
            <el-radio v-model="form.orderstatus" label="5">已排产</el-radio>
            <el-radio v-model="form.orderstatus" label="6">已完工</el-radio>
            <el-radio v-model="form.orderstatus" label="7">已发货</el-radio>
            <el-radio v-model="form.orderstatus" label="8">已收货</el-radio>
            <el-radio v-model="form.orderstatus" label="9">已完成</el-radio>
          </el-form-item>
          <el-form-item style="width: 70%" label="中标工厂ID">
            <el-input v-model="form.factoryid"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="收据">
            <el-input v-model="form.receipt"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="收货人id">
            <el-input v-model="form.userid"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="收货人">
            <el-input v-model="form.recname"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="联系人id">
            <el-input v-model="form.contid"></el-input>
          </el-form-item>
          <el-form-item style="width: 70%" label="联系人">
            <el-input v-model="form.contact"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
      </el-dialog>


    </div>

  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name:'order',
  created() {
    this.load()
  },
  methods: {
    add(){
      // 打开弹窗
      this.dialogVisible=true;
      this.form={};
    },
    save(){
      if(this.form.id){
        //更新
        request.put("http://localhost:9090/facorder",this.form).then(res =>{
          console.log(res)
          if(res.code=='0'){
            this.$message({
              type:"success",
              message:"更新成功"
            })//elementUI的自带的组件
          }else {
            this.$message({
              type:"error",
              message:res.msg
            })//elementUI的自带的组件
          }
        })
      }else{
        //新增
        request.post("http://localhost:9090/facorder",this.form).then(res =>{
          console.log(res)
          if(res.code=='0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })//elementUI的自带的组件
          }else {
            this.$message({
              type:"error",
              message:res.msg
            })//elementUI的自带的组件
          }
        })
      }
      this.load()//刷新数据
      this.dialogVisible=false;

    },
    handleClick(row) {
      console.log(row);
    },
    handleEdit(row){
      this.form=JSON.parse(JSON.stringify(row))//深拷贝
      this.dialogVisible=true
    },
    handleDelete(id){
      console.log(id)
      request.delete("http://localhost:9090/facorder/"+id).then(res =>
      {
        if(res.code=='0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else {
          this.$message({
            type:"error",
            message:res.msg
          })
        }
        this.load()
      })
      //获取id，传入controller
    },
    handleSizeChange(pageSize) {//改变每页个数触发
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNumber) {//改变当前页码触发
      this.currentPage4=pageNumber
      this.load()
    },
    load(){
      request.get("http://localhost:9090/facorder",{
        params: {
          pageNum:this.currentPage4,
          pageSize:this.pageSize,
          search:this.search
        }
        }).then(res =>{
          console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    }
    // open() {
    //   this.$prompt('请输入邮箱', '提示', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消',
    //     inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    //     inputErrorMessage: '邮箱格式不正确'
    //   })
    //       .then(({ value }) => {
    //     this.$message({
    //       type: 'success',
    //       message: '你的邮箱是: ' + value
    //     });
    //   }).catch(() => {
    //     this.$message({
    //       type: 'info',
    //       message: '取消输入'
    //     });
    //   });
    // }

  },

  data() {
    return {
      total:20,
      currentPage4: 1,
      pageSize:10,
      search:'',
      tableData:[],
      dialogVisible: false,
      form:{},//是一个对象
      radio: '1'
    }
  }
}
</script>