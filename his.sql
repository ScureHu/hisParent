//创建数据库语言
CREATE DATABASE hisdb
USE `hisdb`
//病人
CREATE TABLE `zcmu_patient` (
  `uuid` varchar(20) NOT NULL COMMENT 'ID',
  `wardcode` varchar(20) DEFAULT NULL COMMENT '病区',
  `bed_No` int(20)  DEFAULT NULL COMMENT '床位号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `nursing_Level` varchar(1) DEFAULT NULL COMMENT '护理等级 1-1级 2-2级 3-3级 4-特级',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `attn_Doctor` varchar(20) DEFAULT NULL COMMENT '主治医生',
  `in_Admit_Time` datetime DEFAULT NULL COMMENT '住院时间',
  `out_Admit_Time` datetime DEFAULT NULL COMMENT '出院时间',
  `id_No` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `home_Address` varchar(500) DEFAULT NULL COMMENT '家庭住址',
  `contact_Name` varchar(20) DEFAULT NULL COMMENT '联系人姓名',
  `contact_Phone` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `status` varchar(1) DEFAULT NULL COMMENT '病人状态(出/住)',
  `depet_code` varchar(10) DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者';

//病区
CREATE TABLE `zcmu_ward` (
  `wardcode` VARCHAR(20) NOT NULL COMMENT '病区',
  `ward_name` VARCHAR(20) DEFAULT NULL COMMENT '病区名',
  `bed_sum` int(20) DEFAULT NULL COMMENT '床位总数',
   PRIMARY KEY (`wardcode`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='病区';


//病区床位
CREATE TABLE `zcmu_ward_patient` (
  `id` VARCHAR(20) NOT NULL COMMENT 'id',
  `bed_no` int(20) NOT NULL COMMENT '床位号',
  `ward_name` VARCHAR(20) DEFAULT NULL COMMENT '病区名',
  `patient_Id` VARCHAR(20) DEFAULT NULL COMMENT '病人Id',
  `wardcode` VARCHAR(20) NOT NULL COMMENT '病区',
  `status` VARCHAR(1) DEFAULT NULL COMMENT '床位是否可用',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='病区床位';

//医嘱拆分表
CREATE TABLE `zcmu_order` (
  `plan_id` varchar(20) NOT NULL COMMENT '计划Id',
  `plan_time` datetime DEFAULT NULL COMMENT '计划时间',
  `execute_time` datetime DEFAULT NULL COMMENT '执行时间',
  `finish_time` datetime DEFAULT NULL COMMENT '结束时间',
  `execute_id` varchar(20) DEFAULT NULL COMMENT '执行人Id',
  `execute_name` varchar(20) DEFAULT NULL COMMENT '执行人姓名',
  `patient_id` varchar(20) DEFAULT NULL COMMENT '病人Id',
  `his_order_id` varchar(20) DEFAULT NULL COMMENT '医嘱唯一号',
  `his_group_no` varchar(50) DEFAULT NULL COMMENT '医嘱组号',
  `start_time` datetime DEFAULT NULL COMMENT '医嘱开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '医嘱结束时间',
  `supply_code` varchar(1) DEFAULT NULL COMMENT '给药方式',
  `order_class` varchar(20) DEFAULT NULL COMMENT '医嘱类型',
  `doctor_name`   varchar(20) DEFAULT NULL COMMENT '开嘱医生',
  `dose_unit` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `frequence` varchar(20) DEFAULT NULL COMMENT '频率',
  `status` varchar(1) DEFAULT NULL COMMENT '状态',
  `split_time` datetime DEFAULT NULL COMMENT '拆分时间',
  `split_name` varchar(20) DEFAULT NULL COMMENT '拆分人',
  `drug_name` varchar(20) DEFAULT NULL COMMENT '药物名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医嘱表';
//护士表
CREATE TABLE `zcmu_nurse` (
  `nurse_id` varchar(20) NOT NULL COMMENT '护士Id',
  `nurse_name` varchar(20) DEFAULT NULL COMMENT '护士姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `nurse_code` VARCHAR(50)  DEFAULT NULL COMMENT '护士工号',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `wardcode` VARCHAR(20)  DEFAULT NULL COMMENT '病区'
  PRIMARY KEY (`nurse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='护士表';
//原始医嘱表
CREATE TABLE `zcmu_raw_order` (
  `his_order_id` varchar(20) DEFAULT NULL COMMENT '医嘱唯一号',
  `start_time` datetime DEFAULT NULL COMMENT '医嘱开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '医嘱结束时间',
  `patient_id` varchar(20) DEFAULT NULL COMMENT '病人Id',
  `supply_code` varchar(1) DEFAULT NULL COMMENT '给药方式',
  `order_class` varchar(20) DEFAULT NULL COMMENT '医嘱类型',
  `doctor_name` varchar(20) DEFAULT NULL COMMENT '开嘱医生',
  `dose_unit` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `frequence` varchar(20) DEFAULT NULL COMMENT '频率',
  -- 0->未拆分  1->已拆分 2->已作废
  `status` varchar(1) DEFAULT NULL COMMENT '状态',
  `drug_name` varchar(100) DEFAULT NULL COMMENT '药物名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`his_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='原始医嘱表';
//体征普通表
CREATE TABLE `zcmu_sign` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `patient_id` varchar(20) DEFAULT NULL COMMENT '病人ID',
  `patient_name` varchar(20) DEFAULT NULL COMMENT '病人名称',
  `measure_time` datetime DEFAULT NULL COMMENT '测量时间点',
  `record_time` datetime DEFAULT NULL COMMENT '体征的录入时间',
  `t` double DEFAULT NULL COMMENT '体温',
  `p` double DEFAULT NULL COMMENT '脉搏',
  `r` double DEFAULT NULL COMMENT '心率',
  `bp` double DEFAULT NULL COMMENT '血压',
  `giu` double DEFAULT NULL COMMENT '血糖',
	`remark` VARCHAR(200)  DEFAULT NULL COMMENT '备注',
	`source` VARCHAR(1)  DEFAULT NULL COMMENT '数据来源1->首页录入，2->批量录入修改',
	`record_nurse_id` varchar(20) DEFAULT NULL COMMENT '记录人代码',
	`record_nurse_name` varchar(20) DEFAULT NULL COMMENT '记录人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='体征普通表';
//护理计划模板表
CREATE CREATE TABLE `zcmu_nursing_plan_template`(
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '模板名',
  `is_valid` varchar(1) DEFAULT NULL COMMENT '是否有效',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `target` varchar(2000) DEFAULT NULL COMMENT '预期目标',
  `nursing_measure` varchar(2000) DEFAULT NULL COMMENT '护理措施',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='护理计划模板表';
//护理计划记录表
CREATE CREATE TABLE `zcmu_nursing_plan`(
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `patient_id` varchar(20) DEFAULT NULL COMMENT '病人id',
  `template_id` varchar(20) DEFAULT NULL COMMENT '使用模板',
  `template_name` varchar(20) DEFAULT NULL COMMENT '使用模板名',
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  `recorder_id` varchar(20) DEFAULT NULL COMMENT '记录人Id',
  `recorder_name` varchar(20) DEFAULT NULL COMMENT '记录人姓名',
  `target` varchar(2000) DEFAULT NULL COMMENT '预期目标',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `nursing_measure` varchar(2000) DEFAULT NULL COMMENT '护理措施',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='护理计划记录表';
//健康教育项目
CREATE CREATE TABLE `zcmu_dict_he_item`(
  id,
  item_name,项目名称
  item_content,具体内容
)
//病人评估
CREATE CREATE TABLE `zcmu_HEALTH_ASSESS`(
  `id` varchar(20)  NOT NULL COMMENT 'ID',
  `patient_id` varchar(20) DEFAULT NULL COMMENT '病人id',
  `create_user_id` varchar(20) DEFAULT NULL COMMENT '创建人姓名',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人姓名',,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `caregivers` varchar(100) DEFAULT NULL COMMENT '照顾者',
  `memory_loss` varchar(50) DEFAULT NULL COMMENT '是否失忆',
  `psychological` varchar(50) DEFAULT NULL COMMENT '心里状态',
  `hearing` varchar(50) DEFAULT NULL COMMENT '听力状态',
  `vision` varchar(50) DEFAULT NULL COMMENT '视力状态',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='病人评估表';
//健康教育记录表
CREATE CREATE TABLE `zcmu_HEALTH_plan`(
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `patient_id` varchar(100) DEFAULT NULL COMMENT '病人id',
  `item_name` varchar(20) DEFAULT NULL COMMENT '项目名称',
  `item_id` varchar(100) DEFAULT NULL COMMENT '项目id',
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  `recorder_id` varchar(20) DEFAULT NULL COMMENT '记录人Id',
  `recorder_name` varchar(20) DEFAULT NULL COMMENT '记录人姓名', 
  `health_function` varchar(1) DEFAULT NULL COMMENT '1->口述理解,2->演示',
  `item_content` varchar(2000) DEFAULT NULL COMMENT '项目内容',
  `health_people` varchar(1)  DEFAULT NULL COMMENT '1->家属，2->病人',
  `health_time` datetime DEFAULT NULL COMMENT '教育时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='健康教育记录表';
//患者增加一列出院后由谁照顾,出院去向的字段
ALTER TABLE zcmu_patient  ADD  `care` varchar(100) NOT NULL COMMIT '出院后由谁照顾';
ALTER TABLE zcmu_patient  ADD  `for_address` varchar(100) NOT NULL COMMIT '出院后去向';
