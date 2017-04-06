-- phpMyAdmin SQL Dump
-- version 4.6.6deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2017-04-06 22:12:34
-- 服务器版本： 5.7.17-0ubuntu1
-- PHP Version: 7.0.15-1ubuntu4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asystem`
--
CREATE DATABASE IF NOT EXISTS `asystem` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `asystem`;

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL COMMENT '键值',
  `name` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `title` varchar(50) DEFAULT NULL COMMENT '课程对应的学科',
  `detail` varchar(10240) DEFAULT NULL COMMENT '课程详细描述',
  `creater` int(11) DEFAULT NULL COMMENT '添加人id',
  `create_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='课程管理表';

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`id`, `name`, `title`, `detail`, `creater`, `create_timestamp`) VALUES
(1, '数学基础', '数学基础知识', '学习数学基础知识', 0, '2017-03-30 13:18:55'),
(2, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(3, '数学基础', '数学基础知识', '学习数学基础知识', 0, '2017-03-30 13:18:55'),
(4, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(5, '数学基础', '数学基础知识', '学习数学基础知识', 0, '2017-03-30 13:18:55'),
(6, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(7, '数学基础', '数学基础知识', '学习数学基础知识', 0, '2017-03-30 13:18:55'),
(8, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(9, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(10, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(11, '数学基础', '数学基础知识', '学习数学基础知识', 0, '2017-03-30 13:18:55'),
(12, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(13, '计算机基础', '计算机', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(35, '物理', '物理现象', '物理存在', 1, '2017-04-04 15:59:16');

-- --------------------------------------------------------

--
-- 表的结构 `score_result`
--

CREATE TABLE `score_result` (
  `id` int(11) NOT NULL COMMENT '主键',
  `student_id` int(11) NOT NULL COMMENT '学生的id',
  `teacher_id` int(11) NOT NULL COMMENT '评分的老师id',
  `test_paper_id` int(11) NOT NULL COMMENT '对应试卷的id',
  `desc` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '学生试卷的得分分布情况，采用json字符串描述'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='存放成绩数据\r\n成绩：得分详细情况，具体是某一学生对某一试卷的得分情况详细描述';

--
-- 转存表中的数据 `score_result`
--

INSERT INTO `score_result` (`id`, `student_id`, `teacher_id`, `test_paper_id`, `desc`) VALUES
(1, 1, 1, 1, '{}');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `number` varchar(40) NOT NULL COMMENT '编号：学生的学号',
  `email` varchar(40) NOT NULL DEFAULT 'a@b.com' COMMENT '联系方式:email',
  `telphone` varchar(20) NOT NULL COMMENT '联系方式:telphone'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表包括老师、学生、及其他';

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `name`, `number`, `email`, `telphone`) VALUES
(1, '小周', '001', 'a@b.com', '18000008888'),
(2, '小张', '002', 'a@b.com', '18000008888'),
(9, '小还', '004', 'z@c.com', '17099999999'),
(8, '小王', '003', 'c@d.com', '18011111111');

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `number` varchar(40) NOT NULL COMMENT '编号：老师的编号',
  `email` varchar(40) NOT NULL DEFAULT 'a@b.com' COMMENT '联系方式:email',
  `telphone` varchar(20) NOT NULL COMMENT '联系方式:telphone'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表包括老师、学生、及其他';

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `number`, `email`, `telphone`) VALUES
(1, '蔡元培', '001', 'a@b.com', '18000008888'),
(2, '周树人', '002', 'a@b.com', '18000008888');

-- --------------------------------------------------------

--
-- 表的结构 `test_paper`
--

CREATE TABLE `test_paper` (
  `id` int(11) NOT NULL COMMENT '试卷对应的ID',
  `name` varchar(100) NOT NULL COMMENT '试卷名称，比如：计算机应用基础，期中考试卷1',
  `desc` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '试卷内容的详细描述',
  `course_id` int(11) NOT NULL COMMENT '试卷对应的课程id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='试卷定义';

--
-- 转存表中的数据 `test_paper`
--

INSERT INTO `test_paper` (`id`, `name`, `desc`, `course_id`) VALUES
(1, '数学基础期中试卷', '{}', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `score_result`
--
ALTER TABLE `score_result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test_paper`
--
ALTER TABLE `test_paper`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '键值', AUTO_INCREMENT=36;
--
-- 使用表AUTO_INCREMENT `score_result`
--
ALTER TABLE `score_result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=2;
--
-- 使用表AUTO_INCREMENT `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=10;
--
-- 使用表AUTO_INCREMENT `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id', AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `test_paper`
--
ALTER TABLE `test_paper`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷对应的ID', AUTO_INCREMENT=2;--
-- Database: `fixedAsset`
--
CREATE DATABASE IF NOT EXISTS `fixedAsset` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `fixedAsset`;

-- --------------------------------------------------------

--
-- 表的结构 `ASSETEVENT`
--

CREATE TABLE `ASSETEVENT` (
  `aetId` varchar(120) NOT NULL DEFAULT '',
  `aetpId` int(11) DEFAULT NULL,
  `atId` varchar(45) DEFAULT NULL,
  `userId` varchar(45) DEFAULT NULL,
  `aeDesc` varchar(100) DEFAULT NULL,
  `aeTime` datetime DEFAULT NULL,
  `operateId` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产事件记录';

-- --------------------------------------------------------

--
-- 表的结构 `ASSETEVENTYPE`
--

CREATE TABLE `ASSETEVENTYPE` (
  `aetId` int(11) NOT NULL,
  `aetName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产事件类型';

--
-- 转存表中的数据 `ASSETEVENTYPE`
--

INSERT INTO `ASSETEVENTYPE` (`aetId`, `aetName`) VALUES
(1, '入库'),
(2, '报废'),
(3, '派发'),
(4, '收回'),
(5, '');

-- --------------------------------------------------------

--
-- 表的结构 `ASSETS`
--

CREATE TABLE `ASSETS` (
  `newId` varchar(45) NOT NULL,
  `oldId` varchar(45) DEFAULT NULL,
  `userId` varchar(45) DEFAULT NULL,
  `departmentId` varchar(45) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `assetName` varchar(50) DEFAULT NULL,
  `assetBelong` varchar(45) DEFAULT NULL,
  `currentStatus` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `specifications` varchar(150) DEFAULT NULL,
  `price` decimal(12,2) DEFAULT NULL,
  `purchaseDate` date DEFAULT NULL,
  `possessDate` date DEFAULT NULL,
  `serviceCode` varchar(45) DEFAULT NULL,
  `mac` varchar(45) DEFAULT NULL,
  `reject` int(11) DEFAULT NULL,
  `rejectDate` date DEFAULT NULL,
  `remark1` varchar(100) DEFAULT NULL,
  `remark2` varchar(50) DEFAULT NULL,
  `qrcode` varchar(400) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所有的资产总表';

-- --------------------------------------------------------

--
-- 表的结构 `ASSETTYPE`
--

CREATE TABLE `ASSETTYPE` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产类型';

--
-- 转存表中的数据 `ASSETTYPE`
--

INSERT INTO `ASSETTYPE` (`typeId`, `typeName`) VALUES
(1, '笔记本'),
(2, '主机'),
(3, '显示器'),
(4, '服务器'),
(6, '移动设备'),
(7, '办公设备'),
(10, '一体机');

-- --------------------------------------------------------

--
-- 表的结构 `AUTHUSER`
--

CREATE TABLE `AUTHUSER` (
  `uid` varchar(50) NOT NULL,
  `pwd` varchar(400) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `uName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

--
-- 转存表中的数据 `AUTHUSER`
--

INSERT INTO `AUTHUSER` (`uid`, `pwd`, `token`, `lastLoginTime`, `uName`) VALUES
('001', '168b805607890fc378ddb6d6f376b2f0ac1a1132c49f90f6a9cb15f04d80db0b4a81d9fac5e3e5a5f3d2410db4212c7d9bf758e3078b2e2ae70215d2fb9c6637', '7a3e6b16cb75f48fb897eff3ae732f3154f6d203b53f33660f01b4c3b6bc2df9', '2016-12-03 20:01:01', 'test'),
('root', '20d0df72c87ddb806887723b25f48bc7ea876bbca90bab52eea2abf121a7e1bbf62986d76ca99312408b55314581c447662706c730560d8afbbdf1a1dc8dcc6a', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', '2016-12-03 20:03:35', 'root');

-- --------------------------------------------------------

--
-- 表的结构 `COMPANY`
--

CREATE TABLE `COMPANY` (
  `companyId` int(11) NOT NULL,
  `companyName` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表';

-- --------------------------------------------------------

--
-- 表的结构 `DEPARTMENT`
--

CREATE TABLE `DEPARTMENT` (
  `departmentId` int(11) NOT NULL,
  `departmentName` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- --------------------------------------------------------

--
-- 表的结构 `GIFT`
--

CREATE TABLE `GIFT` (
  `giftId` varchar(120) NOT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `price` decimal(5,0) DEFAULT NULL,
  `expireDate` varchar(45) DEFAULT NULL,
  `categoryId` varchar(120) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `GIFTCATEGORY`
--

CREATE TABLE `GIFTCATEGORY` (
  `categoryId` varchar(120) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `INVENTORY`
--

CREATE TABLE `INVENTORY` (
  `inventoryId` varchar(120) NOT NULL,
  `giftId` varchar(120) DEFAULT NULL,
  `num` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `LIMITATION`
--

CREATE TABLE `LIMITATION` (
  `giftId` varchar(120) NOT NULL,
  `limitNum` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `PAYMENTTYPE`
--

CREATE TABLE `PAYMENTTYPE` (
  `ptId` varchar(120) NOT NULL,
  `ptName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `REPAIR`
--

CREATE TABLE `REPAIR` (
  `rpId` varchar(120) NOT NULL DEFAULT '',
  `newId` varchar(45) NOT NULL,
  `rptpId` int(11) DEFAULT NULL,
  `atId` varchar(45) DEFAULT NULL,
  `reportTime` datetime DEFAULT NULL,
  `repairTime` varchar(100) DEFAULT NULL,
  `parts` varchar(200) DEFAULT NULL,
  `cost` varchar(45) DEFAULT NULL,
  `model` varchar(45) NOT NULL,
  `reportUser` varchar(45) DEFAULT NULL,
  `deptId` varchar(45) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修记录';

-- --------------------------------------------------------

--
-- 表的结构 `REPAIRTYPE`
--

CREATE TABLE `REPAIRTYPE` (
  `rptpId` int(11) NOT NULL,
  `rptpName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修类型';

-- --------------------------------------------------------

--
-- 表的结构 `STOCKIN`
--

CREATE TABLE `STOCKIN` (
  `siId` varchar(120) NOT NULL,
  `giftId` varchar(120) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `amount` decimal(5,0) DEFAULT NULL,
  `supplier` varchar(120) DEFAULT NULL,
  `siTypeId` varchar(120) DEFAULT NULL,
  `ptId` varchar(120) DEFAULT NULL,
  `siDate` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `other` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 触发器 `STOCKIN`
--
DELIMITER $$
CREATE TRIGGER `TGR_STOCKIN_DELETE` AFTER DELETE ON `STOCKIN` FOR EACH ROW BEGIN
	DECLARE new_si_num INT DEFAULT 0;
	DECLARE new_so_num INT DEFAULT 0;
	DECLARE exist_gift_in_inventory INT;

	SELECT COUNT(1) INTO exist_gift_in_inventory FROM INVENTORY WHERE giftId = OLD.giftId;

	IF exist_gift_in_inventory != 0 THEN		
		BEGIN 
			SELECT SUM(num) INTO new_si_num FROM STOCKIN WHERE giftId = OLD.giftId;
			SELECT SUM(num) INTO new_so_num FROM STOCKOUT WHERE giftId = OLD.giftId;

			IF new_si_num IS NULL THEN
				SET new_si_num = 0;
			END IF;

			IF new_so_num IS NULL THEN 
				SET new_so_num = 0;
			END IF;

			UPDATE INVENTORY SET num = new_si_num - new_so_num WHERE giftId = OLD.giftId;
		END;
	END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TGR_STOCKIN_INSERT` AFTER INSERT ON `STOCKIN` FOR EACH ROW BEGIN
	DECLARE new_si_num INT DEFAULT 0;
	DECLARE new_so_num INT DEFAULT 0;
	DECLARE exist_gift_in_inventory INT;

	SELECT COUNT(1) INTO exist_gift_in_inventory FROM INVENTORY WHERE giftId = NEW.giftId;

	IF exist_gift_in_inventory = 0 THEN		
		INSERT INTO INVENTORY VALUES(UUID(), NEW.giftId, NEW.num);
		
		INSERT INTO LIMITATION VALUES(NEW.giftId, 10);
	ELSE									
		BEGIN 
			SELECT SUM(num) INTO new_si_num FROM STOCKIN WHERE giftId = NEW.giftId;
			SELECT SUM(num) INTO new_so_num FROM STOCKOUT WHERE giftId = NEW.giftId;

			IF new_si_num IS NULL THEN
				SET new_si_num = 0;
			END IF;

			IF new_so_num IS NULL THEN 
				SET new_so_num = 0;
			END IF;

			UPDATE INVENTORY SET num = new_si_num - new_so_num WHERE giftId = NEW.giftId;
		END;
	END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TGR_STOCKIN_UPDATE` AFTER UPDATE ON `STOCKIN` FOR EACH ROW BEGIN
	DECLARE new_si_num INT DEFAULT 0;
	DECLARE new_so_num INT DEFAULT 0;
	DECLARE exist_gift_in_inventory INT;

	SELECT COUNT(1) INTO exist_gift_in_inventory FROM INVENTORY WHERE giftId = NEW.giftId;

	IF exist_gift_in_inventory != 0 THEN		
		BEGIN 
			SELECT SUM(num) INTO new_si_num FROM STOCKIN WHERE giftId = NEW.giftId;
			SELECT SUM(num) INTO new_so_num FROM STOCKOUT WHERE giftId = NEW.giftId;

			IF new_si_num IS NULL THEN
				SET new_si_num = 0;
			END IF;

			IF new_so_num IS NULL THEN 
				SET new_so_num = 0;
			END IF;

			UPDATE INVENTORY SET num = new_si_num - new_so_num WHERE giftId = NEW.giftId;
		END;
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `STOCKINTYPE`
--

CREATE TABLE `STOCKINTYPE` (
  `sitId` varchar(120) NOT NULL,
  `typeName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `STOCKOUT`
--

CREATE TABLE `STOCKOUT` (
  `soId` varchar(120) NOT NULL,
  `giftId` varchar(120) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `amount` decimal(12,0) DEFAULT NULL,
  `applyUserId` varchar(20) DEFAULT NULL,
  `underDept` varchar(120) DEFAULT NULL,
  `ptId` varchar(120) DEFAULT NULL,
  `soDate` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `other` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 触发器 `STOCKOUT`
--
DELIMITER $$
CREATE TRIGGER `TGR_STOCKOUT_INSERT` AFTER INSERT ON `STOCKOUT` FOR EACH ROW BEGIN
	DECLARE new_si_num INT;
	DECLARE new_so_num INT;
	DECLARE exist_gift_in_inventory INT;

	SELECT COUNT(1) INTO exist_gift_in_inventory FROM INVENTORY WHERE giftId = NEW.giftId;

	IF exist_gift_in_inventory != 0 THEN		
		BEGIN 
			SELECT SUM(num) INTO new_si_num FROM STOCKIN WHERE giftId = NEW.giftId;
			SELECT SUM(num) INTO new_so_num FROM STOCKOUT WHERE giftId = NEW.giftId;

			IF new_si_num IS NULL THEN
				SET new_si_num = 0;
			END IF;

			IF new_so_num IS NULL THEN 
				SET new_so_num = 0;
			END IF;

			UPDATE INVENTORY SET num = new_si_num - new_so_num WHERE giftId = NEW.giftId;
		END;
	END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TGR_STOCKOUT_UPDATE` AFTER UPDATE ON `STOCKOUT` FOR EACH ROW BEGIN
	DECLARE new_si_num INT;
	DECLARE new_so_num INT;
	DECLARE exist_gift_in_inventory INT;

	SELECT COUNT(1) INTO exist_gift_in_inventory FROM INVENTORY WHERE giftId = NEW.giftId;

	IF exist_gift_in_inventory != 0 THEN		
		BEGIN 
			SELECT SUM(num) INTO new_si_num FROM STOCKIN WHERE giftId = NEW.giftId;
			SELECT SUM(num) INTO new_so_num FROM STOCKOUT WHERE giftId = NEW.giftId;

			IF new_si_num IS NULL THEN
				SET new_si_num = 0;
			END IF;

			IF new_so_num IS NULL THEN 
				SET new_so_num = 0;
			END IF;

			UPDATE INVENTORY SET num = new_si_num - new_so_num WHERE giftId = NEW.giftId;
		END;
	END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TRG_STOCKOUT_DELETE` AFTER DELETE ON `STOCKOUT` FOR EACH ROW BEGIN
	DECLARE new_si_num INT;
	DECLARE new_so_num INT;
	DECLARE exist_gift_in_inventory INT;

	SELECT COUNT(1) INTO exist_gift_in_inventory FROM INVENTORY WHERE giftId = OLD.giftId;

	IF exist_gift_in_inventory != 0 THEN		
		BEGIN 
			SELECT SUM(num) INTO new_si_num FROM STOCKIN WHERE giftId = OLD.giftId;
			SELECT SUM(num) INTO new_so_num FROM STOCKOUT WHERE giftId = OLD.giftId;

			IF new_si_num IS NULL THEN
				SET new_si_num = 0;
			END IF;

			IF new_so_num IS NULL THEN 
				SET new_so_num = 0;
			END IF;

			UPDATE INVENTORY SET num = new_si_num - new_so_num WHERE giftId = OLD.giftId;
		END;
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `TEMPP`
--

CREATE TABLE `TEMPP` (
  `USERID` varchar(20) DEFAULT NULL,
  `USERNAME` varchar(45) DEFAULT NULL,
  `DEPARTMENT` varchar(100) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `TMP_STOCKIN`
--

CREATE TABLE `TMP_STOCKIN` (
  `tsiDate` varchar(45) DEFAULT NULL,
  `tsiCategory` varchar(45) DEFAULT NULL,
  `tsiBrand` varchar(45) DEFAULT NULL,
  `tsiName` varchar(45) DEFAULT NULL,
  `tsiUnit` varchar(45) DEFAULT NULL,
  `tsiNum` int(11) DEFAULT NULL,
  `tsiPrice` decimal(5,0) DEFAULT NULL,
  `tsiAmount` decimal(5,0) DEFAULT NULL,
  `tsiSupplier` varchar(45) DEFAULT NULL,
  `tsiState` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `USER`
--

CREATE TABLE `USER` (
  `userId` varchar(20) NOT NULL,
  `userName` varchar(30) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `mail` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ASSETEVENT`
--
ALTER TABLE `ASSETEVENT`
  ADD PRIMARY KEY (`aetId`);

--
-- Indexes for table `ASSETEVENTYPE`
--
ALTER TABLE `ASSETEVENTYPE`
  ADD PRIMARY KEY (`aetId`);

--
-- Indexes for table `ASSETS`
--
ALTER TABLE `ASSETS`
  ADD PRIMARY KEY (`newId`);

--
-- Indexes for table `ASSETTYPE`
--
ALTER TABLE `ASSETTYPE`
  ADD PRIMARY KEY (`typeId`);

--
-- Indexes for table `AUTHUSER`
--
ALTER TABLE `AUTHUSER`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `COMPANY`
--
ALTER TABLE `COMPANY`
  ADD PRIMARY KEY (`companyId`);

--
-- Indexes for table `DEPARTMENT`
--
ALTER TABLE `DEPARTMENT`
  ADD PRIMARY KEY (`departmentId`);

--
-- Indexes for table `GIFT`
--
ALTER TABLE `GIFT`
  ADD PRIMARY KEY (`giftId`),
  ADD UNIQUE KEY `giftId_UNIQUE` (`giftId`);

--
-- Indexes for table `GIFTCATEGORY`
--
ALTER TABLE `GIFTCATEGORY`
  ADD PRIMARY KEY (`categoryId`),
  ADD UNIQUE KEY `categoryId_UNIQUE` (`categoryId`);

--
-- Indexes for table `INVENTORY`
--
ALTER TABLE `INVENTORY`
  ADD PRIMARY KEY (`inventoryId`),
  ADD UNIQUE KEY `inventoryId_UNIQUE` (`inventoryId`);

--
-- Indexes for table `LIMITATION`
--
ALTER TABLE `LIMITATION`
  ADD PRIMARY KEY (`giftId`),
  ADD UNIQUE KEY `giftId_UNIQUE` (`giftId`);

--
-- Indexes for table `PAYMENTTYPE`
--
ALTER TABLE `PAYMENTTYPE`
  ADD PRIMARY KEY (`ptId`),
  ADD UNIQUE KEY `ptId_UNIQUE` (`ptId`);

--
-- Indexes for table `REPAIR`
--
ALTER TABLE `REPAIR`
  ADD PRIMARY KEY (`rpId`);

--
-- Indexes for table `REPAIRTYPE`
--
ALTER TABLE `REPAIRTYPE`
  ADD PRIMARY KEY (`rptpId`);

--
-- Indexes for table `STOCKIN`
--
ALTER TABLE `STOCKIN`
  ADD PRIMARY KEY (`siId`),
  ADD UNIQUE KEY `siId_UNIQUE` (`siId`);

--
-- Indexes for table `STOCKINTYPE`
--
ALTER TABLE `STOCKINTYPE`
  ADD PRIMARY KEY (`sitId`),
  ADD UNIQUE KEY `sitId_UNIQUE` (`sitId`);

--
-- Indexes for table `STOCKOUT`
--
ALTER TABLE `STOCKOUT`
  ADD PRIMARY KEY (`soId`),
  ADD UNIQUE KEY `soId_UNIQUE` (`soId`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`userId`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `COMPANY`
--
ALTER TABLE `COMPANY`
  MODIFY `companyId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `DEPARTMENT`
--
ALTER TABLE `DEPARTMENT`
  MODIFY `departmentId` int(11) NOT NULL AUTO_INCREMENT;--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- 表的结构 `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- 表的结构 `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- 表的结构 `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- 表的结构 `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

-- --------------------------------------------------------

--
-- 表的结构 `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

--
-- 转存表中的数据 `pma__export_templates`
--

INSERT INTO `pma__export_templates` (`id`, `username`, `export_type`, `template_name`, `template_data`) VALUES
(1, 'root', 'database', 'template', '{\"quick_or_custom\":\"quick\",\"what\":\"sql\",\"structure_or_data_forced\":\"0\",\"table_select[]\":[\"course\",\"score_result\",\"student\",\"teacher\",\"test_paper\"],\"table_structure[]\":[\"course\",\"score_result\",\"student\",\"teacher\",\"test_paper\"],\"table_data[]\":[\"course\",\"score_result\",\"student\",\"teacher\",\"test_paper\"],\"output_format\":\"sendit\",\"filename_template\":\"@DATABASE@\",\"remember_template\":\"on\",\"charset\":\"utf-8\",\"compression\":\"none\",\"maxsize\":\"\",\"yaml_structure_or_data\":\"data\",\"sql_include_comments\":\"something\",\"sql_header_comment\":\"\",\"sql_compatibility\":\"NONE\",\"sql_structure_or_data\":\"structure_and_data\",\"sql_create_table\":\"something\",\"sql_auto_increment\":\"something\",\"sql_create_view\":\"something\",\"sql_procedure_function\":\"something\",\"sql_create_trigger\":\"something\",\"sql_backquotes\":\"something\",\"sql_type\":\"INSERT\",\"sql_insert_syntax\":\"both\",\"sql_max_query_size\":\"50000\",\"sql_hex_for_binary\":\"something\",\"sql_utc_time\":\"something\",\"phparray_structure_or_data\":\"data\",\"xml_structure_or_data\":\"data\",\"xml_export_events\":\"something\",\"xml_export_functions\":\"something\",\"xml_export_procedures\":\"something\",\"xml_export_tables\":\"something\",\"xml_export_triggers\":\"something\",\"xml_export_views\":\"something\",\"xml_export_contents\":\"something\",\"json_structure_or_data\":\"data\",\"codegen_structure_or_data\":\"data\",\"codegen_format\":\"0\",\"texytext_structure_or_data\":\"structure_and_data\",\"texytext_null\":\"NULL\",\"odt_structure_or_data\":\"structure_and_data\",\"odt_relation\":\"something\",\"odt_comments\":\"something\",\"odt_mime\":\"something\",\"odt_columns\":\"something\",\"odt_null\":\"NULL\",\"csv_separator\":\",\",\"csv_enclosed\":\"\\\"\",\"csv_escaped\":\"\\\"\",\"csv_terminated\":\"AUTO\",\"csv_null\":\"NULL\",\"csv_structure_or_data\":\"data\",\"pdf_report_title\":\"\",\"pdf_structure_or_data\":\"structure_and_data\",\"latex_caption\":\"something\",\"latex_structure_or_data\":\"structure_and_data\",\"latex_structure_caption\":\"@TABLE@ è¡¨çš„ç»“æž„\",\"latex_structure_continued_caption\":\"@TABLE@ è¡¨çš„ç»“æž„ (å»¶ç»­çš„)\",\"latex_structure_label\":\"tab:@TABLE@-structure\",\"latex_relation\":\"something\",\"latex_comments\":\"something\",\"latex_mime\":\"something\",\"latex_columns\":\"something\",\"latex_data_caption\":\"@TABLE@ è¡¨çš„å†…å®¹\",\"latex_data_continued_caption\":\"@TABLE@ è¡¨çš„å†…å®¹ (å»¶ç»­çš„)\",\"latex_data_label\":\"tab:@TABLE@-data\",\"latex_null\":\"\\\\textit{NULL}\",\"htmlword_structure_or_data\":\"structure_and_data\",\"htmlword_null\":\"NULL\",\"excel_null\":\"NULL\",\"excel_edition\":\"win\",\"excel_structure_or_data\":\"data\",\"ods_null\":\"NULL\",\"ods_structure_or_data\":\"data\",\"mediawiki_structure_or_data\":\"structure_and_data\",\"mediawiki_caption\":\"something\",\"mediawiki_headers\":\"something\",\"\":null,\"lock_tables\":null,\"as_separate_files\":null,\"sql_dates\":null,\"sql_relation\":null,\"sql_mime\":null,\"sql_use_transaction\":null,\"sql_disable_fk\":null,\"sql_views_as_tables\":null,\"sql_metadata\":null,\"sql_create_database\":null,\"sql_drop_table\":null,\"sql_if_not_exists\":null,\"sql_truncate\":null,\"sql_delayed\":null,\"sql_ignore\":null,\"json_pretty_print\":null,\"texytext_columns\":null,\"csv_removeCRLF\":null,\"csv_columns\":null,\"htmlword_columns\":null,\"excel_removeCRLF\":null,\"excel_columns\":null,\"ods_columns\":null}');

-- --------------------------------------------------------

--
-- 表的结构 `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- 表的结构 `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- 表的结构 `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- 表的结构 `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- 表的结构 `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- 转存表中的数据 `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"asystem\",\"table\":\"test_paper\"},{\"db\":\"asystem\",\"table\":\"teacher\"},{\"db\":\"asystem\",\"table\":\"student\"},{\"db\":\"asystem\",\"table\":\"score_result\"},{\"db\":\"asystem\",\"table\":\"course\"},{\"db\":\"fixedAsset\",\"table\":\"ASSETEVENTYPE\"},{\"db\":\"fixedAsset\",\"table\":\"USER\"},{\"db\":\"fixedAsset\",\"table\":\"TMP_STOCKIN\"},{\"db\":\"fixedAsset\",\"table\":\"TEMPP\"},{\"db\":\"fixedAsset\",\"table\":\"STOCKOUT\"}]');

-- --------------------------------------------------------

--
-- 表的结构 `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- 表的结构 `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- 表的结构 `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float UNSIGNED NOT NULL DEFAULT '0',
  `y` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- 表的结构 `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- 表的结构 `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- 表的结构 `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- 表的结构 `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- 转存表中的数据 `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2017-03-30 12:57:08', '{\"collation_connection\":\"utf8mb4_unicode_ci\",\"lang\":\"zh_CN\"}');

-- --------------------------------------------------------

--
-- 表的结构 `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- 表的结构 `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indexes for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indexes for table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用表AUTO_INCREMENT `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

-- --------------------------------------------------------

--
-- 表的结构 `runoob_tbl`
--

CREATE TABLE `runoob_tbl` (
  `runoob_id` int(11) NOT NULL,
  `runoob_title` varchar(100) NOT NULL,
  `runoob_author` varchar(40) NOT NULL,
  `submission_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `runoob_tbl`
--

INSERT INTO `runoob_tbl` (`runoob_id`, `runoob_title`, `runoob_author`, `submission_date`) VALUES
(1, 'Learn PHP', 'John Poul', '2016-09-11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `runoob_tbl`
--
ALTER TABLE `runoob_tbl`
  ADD PRIMARY KEY (`runoob_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `runoob_tbl`
--
ALTER TABLE `runoob_tbl`
  MODIFY `runoob_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
