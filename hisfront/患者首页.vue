<template>
<div class="app-container">
  <br>
  <el-form :inline="true" class="demo-form-inline" label-width="80px">   
        <el-form-item label="护理等级">
             <el-select v-model="select" placeholder="请选择">
                <el-option label="一级护理" value="1"></el-option>
                <el-option label="二级护理" value="2"></el-option>
                <el-option label="三级护理" value="3"></el-option>
                <el-option label="特级护理" value="4"></el-option>
            </el-select>
        </el-form-item>
        <el-button type="primary" @click="fetchData()">查询</el-button>
    </el-form>
    <el-col :span="4" v-for="o in 18" :key="o">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
            <span>病人姓名</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible = true">录入体温</el-button>
        </div>
          <div v-for="o in 2" :key="o" class="text item">
          {{'列表内容 ' + o }}
          </div>
          <el-popover
              placement="top-start"
              title="详细信息"
              width="180"
              trigger="hover"
              >
                    {{'床号： ' + o }}
                    <br>
                     {{'姓名： ' + "mmomomo" }}
                    <br>
                    {{'年龄： ' + o }} 
                    <br>
                    {{'性别： ' + o }}
                    <br>
                    {{'身份证号： ' + o }}
                    <br>
                    {{'联系电话： ' + o }}
                    <br>
                    {{'住院时间： ' + o }}
                    <br>
                    {{'诊断： ' + o }}
                    <br>
                    <br>
                    {{'临床科室： ' + o }}
                    <br>
                    {{'主治医生： ' + o }}
                    <br>
                    {{'护理级别： ' + o }}
              <el-button slot="reference">详细信息</el-button>
          </el-popover>
        </el-card>
    </el-col>
    <div align="center" class="paginat"> 
      <el-pagination
          background
          layout="prev, pager, next"
          :total="1000">
      </el-pagination>
    </div>
    <el-dialog  title="生命体征录入" :visible.sync="dialogFormVisible" >
      <el-form label-width="100px">

        <el-form-item label="时间点" >  
          <el-select v-model="from.date" placeholder="请选择测量时间点">
            <el-option label="5:00" value="1"></el-option>
            <el-option label="10:00" value="2"></el-option>
            <el-option label="15:00" value="3"></el-option>
            <el-option label="22:00" value="4"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="体温(℃)">
            <el-input placeholder="体温" v-model="from.t">
              <template slot="append">℃</template>
            </el-input>
        </el-form-item>
        <el-form-item label="心率(分钟/次)">
            <el-input  placeholder="心率(分钟/次)" v-model="from.t"></el-input>
        </el-form-item>
        <el-form-item label="脉搏(次/分)">
            <el-input  placeholder="脉搏(次/分)" v-model="from.p"></el-input>
        </el-form-item>
        <el-form-item label="血压(mmHg)">
            <el-input placeholder="格式为mm/Hg" v-model="from.bp"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" >保存</el-button>
          <el-button @click="dialogFormVisible = false" >关闭</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data () {
    return {
      select: '',
      animalHeat:'',
      dialogFormVisible: false,
      from:{}
    }
  }
}
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 180px;
    margin-bottom: 18px;
    
  }
  .paginat{
    float: right ;
    margin-bottom: 35px
  }
</style>
