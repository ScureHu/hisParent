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

//病区床位
CREATE TABLE `zcmu_ward` (
  `wardcode` varchar(20) NOT NULL COMMENT '病区',
  `bed_No` varchar(20) DEFAULT NULL COMMENT '床位号',
  `wardName` varchar(20) DEFAULT NULL COMMENT '病区名',
  `parent_Id` varchar(20) DEFAULT NULL COMMENT '病人Id',
  `status` varchar(1) DEFAULT NULL COMMENT '床位是否可用',
  PRIMARY KEY (`wardcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='病区床位'
