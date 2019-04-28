<template>
<div class="app-container">
    <el-container style="height: 800px; border: 1px solid #eee">
        <el-aside width="250px" style="background-color: rgb(238, 241, 246)">
            
            <el-input
                placeholder="输入姓名进行过滤"
                v-model="filterText"
                style="width: 99%">
            </el-input>

            <el-table ref="singleTable" 
                :data="patients.filter(data => !filterText || data.name.toLowerCase().includes(filterText.toLowerCase()))"
                highlight-current-row 
                @current-change="handleCurrentChange" 
                style="width: 99%">
                <el-table-column property="bed" label="床位号" width="80"></el-table-column>
                <el-table-column property="name" label="姓名"></el-table-column>
            </el-table>

        </el-aside>
        <el-container>

            <el-header style="text-align: right; font-size: 12px;">
                <el-button-group>
                    <el-button type="primary" v-if="menuManager_btn_add" icon="plus" @click="handlerAdd">新增</el-button>
                </el-button-group>
            </el-header>
        
            <el-main>
                <el-table :data="list" border style="width: 100%">
                    <el-table-column type="selection" width="55"> </el-table-column>
                    <el-table-column  prop="name"  label="序号"  width="107"></el-table-column>
                    <el-table-column  prop="healthTime"  label="教育时间"  width="120"></el-table-column>
                    <el-table-column  prop="healthPople"  label="教育对象"  width="120"></el-table-column>
                    <el-table-column  prop="itemName"  label="教育项目"  width="200"></el-table-column>
                    <el-table-column  prop="healthFunction"  label="教育方法"  width="120"></el-table-column>
                    <el-table-column  prop="recorderName"  label="教育者"  width="120"></el-table-column>
                    <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                        <template slot-scope="scope">
                            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                            <el-button type="text" size="small">编辑</el-button>
                        </template>
                    </el-table-column>
                 </el-table>
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
import patientApi from '@/api/patient'
export default {
    data() {
      return {
        patients: [],
        currentRow: null,
        filterText: '',
        menuManager_btn_add: false,
        menuManager_btn_edit: false,
        menuManager_btn_del: false
      }
    },
    methods:{
        setCurrent(row) {
            this.$refs.singleTable.setCurrentRow(row);
        },
        handleCurrentChange(val) {
            this.currentRow = val;
            this.menuManager_btn_add = true;
            this.menuManager_btn_edit = true;
            this.menuManager_btn_del = true;
        },
        fetchData(){
            patientApi.findAll().then(response => {
                this.patients = response.data
            });
        }   
    },
    created(){
      this.fetchData();
    },
  };
</script>
