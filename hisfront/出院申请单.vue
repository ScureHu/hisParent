<template>
<div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">

      <el-form-item label="姓名">
        <el-input v-model="form.name" :disabled="true"></el-input>
      </el-form-item>

       <el-form-item label="床位号">
          <el-select v-model="form.bed" placeholder="请选择" :disabled="true">
            <el-option
              v-for="item in beds"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
      </el-form-item>

      <el-form-item label="性别">
        <el-select v-model="form.sex" placeholder="选择性别" :disabled="true">
          <el-option label="男" value="1"/>
          <el-option label="女" value="2"/>
        </el-select>
      </el-form-item>

      <el-form-item label="年龄" >
        <el-input v-model="form.age" :disabled="true"/>
      </el-form-item>

       <el-form-item label="诊断">
        <el-input v-model="form.diagnosis" :disabled="true"/>
      </el-form-item>

      <el-form-item label="主治医生">
        <el-input v-model="form.attnDoctor" :disabled="true"/>
      </el-form-item>

      <el-form-item label="身份证号">
        <el-input v-model="form.idNo" :disabled="true"/>
      </el-form-item>
      
      <el-form-item label="家庭住址">
        <el-input v-model="form.homeAddress"/>
      </el-form-item>

      <el-form-item label="生日">
        <el-date-picker
            v-model="form.birthday"
            type="date"
            placeholder="选择日期"
            :disabled="true"
            >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="入院时间">
        <el-date-picker
            v-model="form.inAdmitTime"
            type="date"
            placeholder="选择日期"
            :disabled="true"
            >
        </el-date-picker>
      </el-form-item>

     <el-form-item label="出院时间">
        <el-date-picker
            v-model="form.inAdmitTime"
            type="date"
            placeholder="选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="护理级别">
        <el-select v-model="form.nursingLevel" placeholder="选择护理级别" :disabled="true">
          <el-option label="一级护理" value="1"/>
          <el-option label="二级护理" value="2"/>
          <el-option label="三级护理" value="3"/>
          <el-option label="特级护理" value="4"/>
        </el-select>
      </el-form-item>

      <el-form-item label="联系人">
        <el-input v-model="form.contactName"/>
      </el-form-item>

      <el-form-item label="联系人电话">
        <el-input v-model="form.contactPhone"/>
      </el-form-item>

      <el-form-item label="出院后由谁照顾">
        <el-radio-group v-model="form.care">
          <el-radio border label="配偶"></el-radio>
          <el-radio border label="父母"></el-radio>
          <el-radio border label="子女"></el-radio>
          <el-radio border label="保姆"></el-radio>
          <el-radio border label="其他"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="出院后去向">
        <el-radio-group v-model="form.forAddress">
          <el-radio border label="家"></el-radio>
          <el-radio border label="养老院"></el-radio>
          <el-radio border label="社区"></el-radio>
          <el-radio border label="敬老院"></el-radio>
          <el-radio border label="其他"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">创建出院评估单</el-button>
      </el-form-item>
    </el-form>
</div>
</template>
<script>
export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        sex:'1',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
        bed:'1',
        nursingLevel:'3'
      },
      beds:[
        {
          value: '1',
          label: '1'
        },
        {
          value: '2',
          label: '2'
        }
      ]
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>
