-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2017-04-09 12:19:54
-- 服务器版本： 5.7.17-0ubuntu1
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
(14, '物理', '物理现象', '物理存在', 1, '2017-04-09 03:18:09');

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
(1, '小天', '001', 'a@b.com', '18000008888'),
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
  `detail` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '试卷内容的详细描述',
  `course_id` int(11) NOT NULL COMMENT '试卷对应的课程id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='试卷定义';

--
-- 转存表中的数据 `test_paper`
--

INSERT INTO `test_paper` (`id`, `name`, `detail`, `course_id`) VALUES
(1, '数学基础期中试卷', '{}', 1),
(2, '数学基础期末试卷', '{}', 1);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '键值', AUTO_INCREMENT=15;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷对应的ID', AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
