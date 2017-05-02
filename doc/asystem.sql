-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2017-05-02 23:00:04
-- 服务器版本： 5.7.18-0ubuntu0.17.04.1
-- PHP Version: 7.0.15-1ubuntu4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

--
-- Database: `asystem`
--

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
(1, '数学', '数学基础知识', '学习数学基础知识', 2, '2017-04-11 14:29:22'),
(2, '大学计算机基础', '计算机基础', '计算机基础知识介绍', 1, '2017-03-30 13:19:13'),
(15, '语文', '文学基础', '文学基础', 1, '2017-04-16 10:26:50'),
(14, '物理', '自然规律', '物理存在', 1, '2017-04-09 03:18:09');

-- --------------------------------------------------------

--
-- 表的结构 `score_result`
--

CREATE TABLE `score_result` (
  `id` int(11) NOT NULL COMMENT '主键',
  `student_id` int(11) NOT NULL COMMENT '学生的id',
  `teacher_id` int(11) NOT NULL COMMENT '评分的老师id',
  `test_paper_id` int(11) NOT NULL COMMENT '对应试卷的id',
  `detail` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '学生试卷的得分分布情况，采用json字符串描述'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='存放成绩数据\r\n成绩：得分详细情况，具体是某一学生对某一试卷的得分情况详细描述';

--
-- 转存表中的数据 `score_result`
--

INSERT INTO `score_result` (`id`, `student_id`, `teacher_id`, `test_paper_id`, `detail`) VALUES
(13, 2, 2, 2, '{\"student_id\":\"2\",\"teacher_id\":\"2\",\"paper_id\":\"2\",\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"20\",\"score_real\":\"10\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题1\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"20\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"}]}'),
(12, 1, 1, 1, '{\"student_id\":\"1\",\"teacher_id\":\"1\",\"paper_id\":\"1\",\"paper_total_score\":\"120\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题3\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题4\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"15\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题5\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题2\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"}]}'),
(14, 2, 2, 2, '{\"student_id\":\"2\",\"teacher_id\":\"2\",\"paper_id\":\"2\",\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"20\",\"score_real\":\"10\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题1\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"20\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"}]}'),
(15, 2, 2, 2, '{\"student_id\":\"2\",\"teacher_id\":\"2\",\"paper_id\":\"2\",\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"20\",\"score_real\":\"10\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题1\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"20\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"}]}'),
(16, 2, 2, 2, '{\"student_id\":\"2\",\"teacher_id\":\"2\",\"paper_id\":\"2\",\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"20\",\"score_real\":\"10\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题1\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"20\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"}]}'),
(17, 2, 2, 2, '{\"student_id\":\"2\",\"teacher_id\":\"2\",\"paper_id\":\"2\",\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"20\",\"score_real\":\"10\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题1\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"20\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"}]}'),
(18, 2, 2, 2, '{\"student_id\":\"2\",\"teacher_id\":\"2\",\"paper_id\":\"2\",\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"20\",\"score_real\":\"10\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题1\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"20\",\"score_real\":\"20\",\"comments\":\"\"},{\"type\":\"计算题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"30\",\"score_real\":\"20\",\"comments\":\"\"}]}'),
(19, 1, 1, 1, '{\"student_id\":\"1\",\"teacher_id\":\"1\",\"paper_id\":\"1\",\"paper_total_score\":\"120\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题3\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题4\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"15\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题5\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题2\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"}]}'),
(20, 1, 1, 1, '{\"student_id\":\"1\",\"teacher_id\":\"1\",\"paper_id\":\"1\",\"paper_total_score\":\"120\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题3\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题4\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"15\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题5\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题2\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"}]}'),
(21, 1, 1, 1, '{\"student_id\":\"1\",\"teacher_id\":\"1\",\"paper_id\":\"1\",\"paper_total_score\":\"120\",\"data\":[{\"type\":\"选择题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题2\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题3\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"5\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题4\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"15\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"选择题5\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"填空题2\",\"level_str\":\"中等难度\",\"level_index\":\"2\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题1\",\"level_str\":\"基础题目\",\"level_index\":\"1\",\"score_total\":\"10\",\"score_real\":\"1\",\"comments\":\"\"},{\"type\":\"简答题2\",\"level_str\":\"高难度题目\",\"level_index\":\"3\",\"score_total\":\"20\",\"score_real\":\"1\",\"comments\":\"\"}]}');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(1024) DEFAULT NULL COMMENT '密码',
  `number` varchar(40) NOT NULL COMMENT '编号：学生的学号',
  `email` varchar(40) NOT NULL DEFAULT 'a@b.com' COMMENT '联系方式:email',
  `telphone` varchar(20) NOT NULL COMMENT '联系方式:telphone'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表包括老师、学生、及其他';

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `name`, `password`, `number`, `email`, `telphone`) VALUES
(1, '呜呜', 'password', '20170901', '1@s.com', '19000000001'),
(2, '小张', 'password', '20170902', '2@s.com', '19000000002'),
(9, '小还', 'password', '20170903', '3@s.com', '19000000003'),
(8, '小王', 'password', '20170904', '4@s.com', '19000000004');

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(1024) DEFAULT NULL COMMENT '密码',
  `number` varchar(40) DEFAULT NULL COMMENT '编号：老师的编号',
  `email` varchar(40) DEFAULT 'a@b.com' COMMENT '联系方式:email',
  `telphone` varchar(20) DEFAULT NULL COMMENT '联系方式:telphone'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表包括老师、学生、及其他';

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `password`, `number`, `email`, `telphone`) VALUES
(1, '蔡元培', 'password', '20170301', '1@sch.edu', '1800000001'),
(2, '周树人', 'password', '20170302', '2@sch.edu', '1800000002'),
(3, '王老师', 'password', '20170303', '3@sch.edu', '1800000003'),
(4, '李老师', 'password', '20170304', '4@sch.edu', '1800000004');

-- --------------------------------------------------------

--
-- 表的结构 `test_paper`
--

CREATE TABLE `test_paper` (
  `id` int(11) NOT NULL COMMENT '试卷对应的ID',
  `name` varchar(100) NOT NULL COMMENT '试卷名称，比如：计算机应用基础，期中考试卷1',
  `detail` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '试卷内容的详细描述',
  `course_id` int(11) NOT NULL COMMENT '试卷对应的课程id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='试卷定义';

--
-- 转存表中的数据 `test_paper`
--

INSERT INTO `test_paper` (`id`, `name`, `detail`, `course_id`) VALUES
(2, '2017物理期末试卷', '{\"paper_name\":\"2017物理期末试卷\",\"paper_total_score\":\"100\",\"items\":[{\"type\":\"选择题\",\"data\":[{\"name\":1,\"level_index\":\"1\",\"level_str\":\"基础题目\",\"score\":\"20\"},{\"name\":1,\"level_index\":\"3\",\"level_str\":\"高难度题目\",\"score\":\"30\"}]},{\"type\":\"计算题\",\"data\":[{\"name\":1,\"level_index\":\"2\",\"level_str\":\"中等难度\",\"score\":\"20\"},{\"name\":1,\"level_index\":\"3\",\"level_str\":\"高难度题目\",\"score\":\"30\"}]}]}', 14),
(1, '2017数学期末考试', '{\"paper_name\":\"2017数学期末考试\",\"paper_total_score\":\"120\",\"items\":[{\"type\":\"选择题\",\"data\":[{\"name\":1,\"level_index\":\"1\",\"level_str\":\"基础题目\",\"score\":\"5\"},{\"name\":1,\"level_index\":\"1\",\"level_str\":\"基础题目\",\"score\":\"5\"},{\"name\":1,\"level_index\":\"1\",\"level_str\":\"基础题目\",\"score\":\"5\"},{\"name\":1,\"level_index\":\"2\",\"level_str\":\"中等难度\",\"score\":\"15\"},{\"name\":1,\"level_index\":\"3\",\"level_str\":\"高难度题目\",\"score\":\"20\"}]},{\"type\":\"填空题\",\"data\":[{\"name\":1,\"level_index\":\"1\",\"level_str\":\"基础题目\",\"score\":\"10\"},{\"name\":1,\"level_index\":\"2\",\"level_str\":\"中等难度\",\"score\":\"10\"}]},{\"type\":\"简答题\",\"data\":[{\"name\":1,\"level_index\":\"1\",\"level_str\":\"基础题目\",\"score\":\"10\"},{\"name\":1,\"level_index\":\"3\",\"level_str\":\"高难度题目\",\"score\":\"20\"}]}]}', 1);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '键值', AUTO_INCREMENT=16;
--
-- 使用表AUTO_INCREMENT `score_result`
--
ALTER TABLE `score_result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=22;
--
-- 使用表AUTO_INCREMENT `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=13;
--
-- 使用表AUTO_INCREMENT `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id', AUTO_INCREMENT=9;
--
-- 使用表AUTO_INCREMENT `test_paper`
--
ALTER TABLE `test_paper`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷对应的ID', AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
