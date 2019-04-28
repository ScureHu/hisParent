<template>
<div class="app-container">
    <el-container style="height: 800px; border: 1px solid #eee">
        <el-aside width="250px" style="background-color: rgb(238, 241, 246)">
             <el-col :span="12">
                    <el-autocomplete
                        class="inline-input"
                        v-model="state2"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入内容"
                        :trigger-on-focus="false"
                        @select="handleSelect"
                    ></el-autocomplete>
            </el-col>
            <el-table ref="singleTable" :data="tableData" highlight-current-row @current-change="handleCurrentChange" style="width: 99%">
                <el-table-column property="bed" label="床位号" width="80"></el-table-column>
                <el-table-column property="name" label="姓名"></el-table-column>
            </el-table>

    <div style="margin-top: 20px">
        <el-button @click="setCurrent(tableData[1])">选中第二行</el-button>
        <el-button @click="setCurrent()">取消选择</el-button>
    </div>
        </el-aside>
        <el-container>

            <el-header style="text-align: right; font-size: 12px">
        
            </el-header>
        
            <el-main>
                
            </el-main>

        </el-container>
        
    </el-container>
</div>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
  export default {
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
        currentRow: null,
      }
    },
    methods:{
        setCurrent(row) {
            this.$refs.singleTable.setCurrentRow(row);
        },
        handleCurrentChange(val) {
            this.currentRow = val;
        }
    }
  };
</script>
