-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 13, 2017 at 02:54 PM
-- Server version: 5.7.18-0ubuntu0.17.04.1
-- PHP Version: 7.0.18-0ubuntu0.17.04.1

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
-- Table structure for table `course`
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
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `name`, `title`, `detail`, `creater`, `create_timestamp`) VALUES
(1, '计算机原理', '了解计算机的基本知识', '通过学习本课程，对计算机有一定的了解', 1, '2017-05-11 01:08:40'),
(2, '海警信息指挥', '信息指挥', '熟悉并掌握海警信息指挥的方式方法', 1, '2017-05-11 01:08:46');

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(1024) DEFAULT NULL COMMENT '密码',
  `number` varchar(40) NOT NULL COMMENT '编号：管理员的编号',
  `email` varchar(40) NOT NULL DEFAULT 'a@b.com' COMMENT '联系方式:email',
  `telphone` varchar(20) NOT NULL COMMENT '联系方式:telphone'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='管理员对应的表';

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `name`, `password`, `number`, `email`, `telphone`) VALUES
(1, 'admin', 'password', '001', 'a@b.com', '12300000000');

-- --------------------------------------------------------

--
-- Table structure for table `score_result`
--

CREATE TABLE `score_result` (
  `id` int(11) NOT NULL COMMENT '主键',
  `student_id` int(11) NOT NULL COMMENT '学生的id',
  `teacher_id` int(11) NOT NULL COMMENT '评分的老师id',
  `test_paper_id` int(11) NOT NULL COMMENT '对应试卷的id',
  `detail` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '学生试卷的得分分布情况，采用json字符串描述'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='存放成绩数据\r\n成绩：得分详细情况，具体是某一学生对某一试卷的得分情况详细描述';

-- --------------------------------------------------------

--
-- Table structure for table `student`
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
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `password`, `number`, `email`, `telphone`) VALUES
(18, '胡洋', '123456', '03742013018', '649944769@qq.com', '18968215241'),
(6, '陈喆', '123456', '03742013006', '1170516536@qq.com', '18654691562'),
(25, '李昂', '123456', '03742013025', '1731155093@qq.com', '17706467363'),
(3, '陈昊翔', '123456', '03742013003', '931660893@qq.com', '18368403117'),
(35, '齐立超', '123456', '03742013035', '616041869@qq.com', '19508749921'),
(20, '姜珂', '123456', '03742013020', '151952561@qq.com', '18368403157'),
(11, '范燚', '123456', '03742013011', '821220425@qq.com', '18961033169'),
(33, '莫誉夸', '123456', '03742013033', '171749394@qq.com', '18017440321'),
(12, '冯楚帆', '123456', '03742013012', '1519523561@qq.com', '18698414933'),
(30, '李晏超', '123456', '03742013030', '294563501@qq.com', '13885098744'),
(13, '付运达', '123456', '03742013013', '844610848@qq.com', '18067528086'),
(10, '段凌涵', '123456', '03742013010', '1570785859@qq.com', '18074222128'),
(1, '包基权', '123456', '03742013001', '294575201@qq.com', '13777125468'),
(2, '包钰麒', '123456', '03742013002', '844610425@qq.com', '18067525473'),
(5, '陈泽夫', '123456', '03742013005', '649512769@qq.com', '13777465056'),
(4, '陈聿铭', '123456', '03742013004', '821330425@qq.com', '18961022169'),
(7, '崔平', '123456', '03742013007', '854120425@qq.com', '18949322678'),
(8, '邓杰', '123456', '03742013008', '645489769@qq.com', '13846328744'),
(9, '杜殿杰', '123456', '03742013009', '844659874@qq.com', '13777521646'),
(14, '高飞', '123456', '03742013014', '649945629@qq.com', '13777278956'),
(15, '高铭阳', '123456', '03742013015', '821510225@qq.com', '13885012344'),
(16, '葛佳奇', '123456', '03742013016', '294842351@qq.com', '18067123586'),
(17, '胡嘉捷', '123456', '03742013017', '844651238@qq.com', '18961518169'),
(19, '黄华华', '123456', '03742013019', '649948519@qq.com', '13820318744'),
(21, '蒋涵韬', '123456', '03742013021', '844665318@qq.com', '18968939358'),
(22, '金志豪', '123456', '03742013022', '649512769@qq.com', '18986622678'),
(23, '靳奇', '123456', '03742013023', '821226543@qq.com', '18968848478'),
(24, '康思博', '123456', '03742013024', '821252015@qq.com', '18961032158'),
(26, '李承遥', '123456', '03742013026', '649948429@qq.com', '18074333128'),
(27, '李东蒲', '123456', '03742013027', '294842501@qq.com', '18084128086'),
(28, '李木燃', '123456', '03742013028', '649945869@qq.com', '1399898744'),
(31, '廖漳', '123456', '03742013031', '648855769@qq.com', '13788113056'),
(32, '林炫', '123456', '03742013032', '649518529@qq.com', '18963013169'),
(34, '潘志斌', '123456', '03742013034', '649521089@qq.com', '18968525678'),
(36, '冉阳', '123456', '03742013036', '649512510@qq.com', '18961035201'),
(37, '任越', '123456', '03742013037', '821225205@qq.com', '18968845678'),
(38, '剡国庆', '123456', '03742013038', '644455769@qq.com', '18067512086'),
(39, '石昊', '123456', '03742013039', '649512639@qq.com', '18067528986'),
(40, '孙琪', '123456', '03742013040', '649512845@qq.com', '18962362678'),
(41, '陈涛', '123456', '03742012046', '649512521@qq.com', '13785213056');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
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
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `password`, `number`, `email`, `telphone`) VALUES
(1, '应忠于', '123', '1', 'yingzhouyu@sina.com', '18968262678'),
(3, '曹迎槐', '123', '3', '649945769@qq.com', '13777213056'),
(2, '李伟春', '123', '2', 'liweichun@163.com', '767075620');

-- --------------------------------------------------------

--
-- Table structure for table `test_paper`
--

CREATE TABLE `test_paper` (
  `id` int(11) NOT NULL COMMENT '试卷对应的ID',
  `name` varchar(100) NOT NULL COMMENT '试卷名称，比如：计算机应用基础，期中考试卷1',
  `detail` varchar(10240) NOT NULL DEFAULT '{}' COMMENT '试卷内容的详细描述',
  `course_id` int(11) NOT NULL COMMENT '试卷对应的课程id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='试卷定义';

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
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

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
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '键值', AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `score_result`
--
ALTER TABLE `score_result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id', AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `test_paper`
--
ALTER TABLE `test_paper`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷对应的ID', AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
