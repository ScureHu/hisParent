//创建数据库语言
CREATE DATABASE hisdb
USE `hisdb`
//病人
CREATE TABLE `zcmu_patient` (
  `uuid` varchar(20) NOT NULL COMMENT 'ID',
  `wardcode` varchar(20) DEFAULT NULL COMMENT '病区',
  `bed_No` varchar(20) DEFAULT NULL COMMENT '床位号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `nursing_Level` varchar(1) DEFAULT NULL COMMENT '护理等级',
  `diagnosis` varchar(50) DEFAULT NULL COMMENT '诊断',
  `attn_Doctor` varchar(20) DEFAULT NULL COMMENT '主治医生',
  `in_Admit_Time` datetime DEFAULT NULL COMMENT '住院时间',
  `out_Admit_Time` datetime DEFAULT NULL COMMENT '出院时间',
  `id_No` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `home_Address` varchar(500) DEFAULT NULL COMMENT '家庭住址',
  `contact_Name` varchar(20) DEFAULT NULL COMMENT '联系人姓名',
  `contact_Phone` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `status` varchar(1) DEFAULT NULL COMMENT '病人状态(出/住)',
  `height` varchar(50) DEFAULT NULL COMMENT '身高',
  `weight` varchar(50) DEFAULT NULL COMMENT '体重',
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
  `bed_no` int(20) DEFAULT NULL COMMENT '床位号',
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
  `drug_name` varchar(10) DEFAULT NULL COMMENT '药物名称',
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
  `split_time` datetime DEFAULT NULL COMMENT '拆分时间',
  `split_name` varchar(20) DEFAULT NULL COMMENT '拆分人',
  `drug_name` varchar(10) DEFAULT NULL COMMENT '药物名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`his_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='原始医嘱表';
//体征普通表
CREATE CREATE TABLE `zcmu_sign_default` (
  `uuid` varchar(20) NOT NULL COMMENT 'ID',
  `parent_id` varchar(100) DEFAULT NULL COMMENT '病人ID',
  `parent_name` varchar(100) DEFAULT NULL COMMENT '病人名称',
  `measure_time` datetime DEFAULT NULL COMMENT '测量时间点',
  `record_time` datetime DEFAULT NULL COMMENT '体征的录入时间',
  `vitalsign_type` VARCHAR(1)  DEFAULT NULL COMMENT '体征类型的代码1->体温 2->脉搏 3->心率',
	`unit` VARCHAR(30)  DEFAULT NULL COMMENT '体征单位',
	`remark` VARCHAR(200)  DEFAULT NULL COMMENT '备注',
	`source` VARCHAR(1)  DEFAULT NULL COMMENT '数据来源1->首页录入，2->批量录入修改',
	`record_nurse_id` varchar(100) DEFAULT NULL COMMENT '记录人代码',
	`record_nurse_name` varchar(100) DEFAULT NULL COMMENT '记录人名称',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='体征普通表';