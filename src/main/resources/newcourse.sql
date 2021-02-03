/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : newcourse

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2021-02-03 16:28:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `brief` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL COMMENT '0:未审核，1:审核通过，2:课程结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('5', '微积分', '略', '/uploadfiles/0869cc9db3934be38ea7d833cfe415e7.jpg', '1');
INSERT INTO `course` VALUES ('6', '综合英语', '英语听说读写', '/uploadfiles/28465250b47647b49b0950fe12cdfb82.jpg', '1');
INSERT INTO `course` VALUES ('7', 'Java程序语言设计基础', '介绍面向对象的设计思想', '/uploadfiles/65e7e93e9c294f25b3842d6ec50525a7.png', '1');
INSERT INTO `course` VALUES ('8', '大学生心理健康教育', '大学生心理健康教育', '/uploadfiles/a1535915ad6e4a05afd764e1b51695d3.jpg', '0');
INSERT INTO `course` VALUES ('11', '数据结构与算法分析', '数据结构与算法分析简介', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('12', '数据结构与算法分析', '数据结构与算法分析简介', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('13', '数据结构与算法分析', '数据结构与算法分析简介', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('14', '数据结构与算法分析', '数据结构与算法分析简介', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('15', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('16', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('17', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('18', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('19', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('20', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('21', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('22', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('23', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('24', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('25', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('26', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('27', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('28', '高等数学', '高等数学第一册', '/img/hello.jpg', null);
INSERT INTO `course` VALUES ('29', '高等数学', '高等数学第一册', '/img/hello.jpg', null);

-- ----------------------------
-- Table structure for course_user
-- ----------------------------
DROP TABLE IF EXISTS `course_user`;
CREATE TABLE `course_user` (
  `user_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`,`user_id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_user
-- ----------------------------
INSERT INTO `course_user` VALUES ('2', '6');
INSERT INTO `course_user` VALUES ('2', '7');
INSERT INTO `course_user` VALUES ('1', '8');
INSERT INTO `course_user` VALUES ('2', '8');
INSERT INTO `course_user` VALUES ('3', '8');
INSERT INTO `course_user` VALUES ('1', '12');
INSERT INTO `course_user` VALUES ('2', '12');
INSERT INTO `course_user` VALUES ('1', '13');
INSERT INTO `course_user` VALUES ('2', '13');
INSERT INTO `course_user` VALUES ('2', '14');
INSERT INTO `course_user` VALUES ('1', '15');
INSERT INTO `course_user` VALUES ('2', '15');
INSERT INTO `course_user` VALUES ('3', '16');
INSERT INTO `course_user` VALUES ('1', '17');
INSERT INTO `course_user` VALUES ('1', '18');
INSERT INTO `course_user` VALUES ('3', '19');
INSERT INTO `course_user` VALUES ('1', '20');
INSERT INTO `course_user` VALUES ('3', '21');
INSERT INTO `course_user` VALUES ('1', '22');
INSERT INTO `course_user` VALUES ('1', '23');
INSERT INTO `course_user` VALUES ('3', '24');
INSERT INTO `course_user` VALUES ('1', '25');
INSERT INTO `course_user` VALUES ('2', '26');
INSERT INTO `course_user` VALUES ('2', '27');
INSERT INTO `course_user` VALUES ('2', '28');
INSERT INTO `course_user` VALUES ('2', '29');

-- ----------------------------
-- Table structure for c_file
-- ----------------------------
DROP TABLE IF EXISTS `c_file`;
CREATE TABLE `c_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `brief` varchar(255) DEFAULT NULL COMMENT '课程简介',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  `uri` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `course_id` bigint(20) DEFAULT NULL COMMENT '课程id',
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_file
-- ----------------------------
INSERT INTO `c_file` VALUES ('1', '减法运算', '略', '2019-09-13 14:29:54', '/videos/1c5e9aa85220404b9395c6744386022e.mp4', '9', '5', null);
INSERT INTO `c_file` VALUES ('2', '瓦尔登湖讲解', '瓦尔登湖讲解', '2019-12-17 16:31:07', '/videos/0e3143a67b9a4f83929deb1710f39af7.mp4', '9', '6', null);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user:add');
INSERT INTO `permission` VALUES ('2', 'user:get');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `score` int(11) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL COMMENT '0：单选题，1：多选题，2：填空题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('3', null, '1+1=', '9', '1', '1');
INSERT INTO `question` VALUES ('4', null, '1+2=', '9', '1', '1');
INSERT INTO `question` VALUES ('5', null, '1-1=', '9', '2', '1');
INSERT INTO `question` VALUES ('6', null, '2-1=', '9', '1', '1');
INSERT INTO `question` VALUES ('7', null, '1x1=', '9', '1', '1');
INSERT INTO `question` VALUES ('8', null, '1x2=', '9', '1', '1');
INSERT INTO `question` VALUES ('9', null, '1+1=', '9', '10', '2');
INSERT INTO `question` VALUES ('10', null, '1+1=', '9', '2', '2');
INSERT INTO `question` VALUES ('11', '标记', '这是题干信息', null, null, '1');
INSERT INTO `question` VALUES ('12', '标记', '这是题干信息', null, null, '1');
INSERT INTO `question` VALUES ('13', '标记', '这是题干信息', '2', null, '1');
INSERT INTO `question` VALUES ('14', '标记', '这是题干信息', '2', null, '1');
INSERT INTO `question` VALUES ('15', null, '1+1=', '9', '1', '1');
INSERT INTO `question` VALUES ('16', null, '1+1=', '9', '1', '1');

-- ----------------------------
-- Table structure for quiz
-- ----------------------------
DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `course_id` bigint(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quiz
-- ----------------------------
INSERT INTO `quiz` VALUES ('4', '加法运算', '5', '2019-09-13 13:36:00', '2020-08-14 15:40:06');
INSERT INTO `quiz` VALUES ('5', '减法运算', '5', '2019-09-13 13:54:59', '2020-03-12 14:00:07');
INSERT INTO `quiz` VALUES ('6', '乘法运算', '5', '2019-09-13 14:01:51', '2020-06-30 14:00:54');
INSERT INTO `quiz` VALUES ('7', 'adsfads', '5', '2019-09-23 12:59:04', '2020-03-04 10:00:09');
INSERT INTO `quiz` VALUES ('8', 'adwffas', '5', '2019-10-29 17:53:29', '2020-02-12 14:50:00');
INSERT INTO `quiz` VALUES ('9', '课堂小测一', '8', '2020-06-25 15:36:02', '2020-07-20 15:36:02');
INSERT INTO `quiz` VALUES ('10', '课堂小测二', '8', '2020-06-25 15:36:02', '2020-07-25 15:36:02');
INSERT INTO `quiz` VALUES ('11', '课堂小测三', '8', '2020-06-25 15:36:02', '2020-07-25 15:36:02');
INSERT INTO `quiz` VALUES ('13', '课堂小测三', '8', '2020-06-25 15:36:02', '2020-07-25 15:36:02');
INSERT INTO `quiz` VALUES ('14', '课堂小测三', '8', '2020-06-25 15:36:02', '2020-07-25 15:36:02');
INSERT INTO `quiz` VALUES ('15', '课堂小测三', '8', '2020-06-25 15:36:02', '2020-07-25 15:36:02');

-- ----------------------------
-- Table structure for quiz_question
-- ----------------------------
DROP TABLE IF EXISTS `quiz_question`;
CREATE TABLE `quiz_question` (
  `question_id` bigint(20) NOT NULL DEFAULT '0',
  `quiz_id` bigint(20) NOT NULL,
  PRIMARY KEY (`question_id`,`quiz_id`),
  KEY `question_id` (`question_id`),
  KEY `testpaper_id` (`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quiz_question
-- ----------------------------
INSERT INTO `quiz_question` VALUES ('3', '4');
INSERT INTO `quiz_question` VALUES ('3', '5');
INSERT INTO `quiz_question` VALUES ('3', '6');
INSERT INTO `quiz_question` VALUES ('4', '3');
INSERT INTO `quiz_question` VALUES ('4', '4');
INSERT INTO `quiz_question` VALUES ('4', '5');
INSERT INTO `quiz_question` VALUES ('4', '6');
INSERT INTO `quiz_question` VALUES ('5', '4');
INSERT INTO `quiz_question` VALUES ('5', '5');
INSERT INTO `quiz_question` VALUES ('5', '6');
INSERT INTO `quiz_question` VALUES ('6', '4');
INSERT INTO `quiz_question` VALUES ('12', '9');

-- ----------------------------
-- Table structure for quiz_score
-- ----------------------------
DROP TABLE IF EXISTS `quiz_score`;
CREATE TABLE `quiz_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `quiz_id` bigint(20) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `testpaper_id` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quiz_score
-- ----------------------------
INSERT INTO `quiz_score` VALUES ('1', '10', '4', '2', null);
INSERT INTO `quiz_score` VALUES ('2', '10', '5', '3', null);
INSERT INTO `quiz_score` VALUES ('3', '10', '7', '5', null);
INSERT INTO `quiz_score` VALUES ('4', '10', '8', '2', null);

-- ----------------------------
-- Table structure for q_answer
-- ----------------------------
DROP TABLE IF EXISTS `q_answer`;
CREATE TABLE `q_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_answer
-- ----------------------------

-- ----------------------------
-- Table structure for q_choice
-- ----------------------------
DROP TABLE IF EXISTS `q_choice`;
CREATE TABLE `q_choice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_choice
-- ----------------------------
INSERT INTO `q_choice` VALUES ('9', '3', '1');
INSERT INTO `q_choice` VALUES ('10', '3', '2');
INSERT INTO `q_choice` VALUES ('11', '3', '3');
INSERT INTO `q_choice` VALUES ('12', '3', '4');
INSERT INTO `q_choice` VALUES ('13', '4', '1');
INSERT INTO `q_choice` VALUES ('14', '4', '2');
INSERT INTO `q_choice` VALUES ('15', '4', '3');
INSERT INTO `q_choice` VALUES ('16', '4', '4');
INSERT INTO `q_choice` VALUES ('17', '5', '0');
INSERT INTO `q_choice` VALUES ('18', '5', '1');
INSERT INTO `q_choice` VALUES ('19', '5', '2');
INSERT INTO `q_choice` VALUES ('20', '5', '3');
INSERT INTO `q_choice` VALUES ('21', '6', '1');
INSERT INTO `q_choice` VALUES ('22', '6', '3');
INSERT INTO `q_choice` VALUES ('23', '6', '6');
INSERT INTO `q_choice` VALUES ('24', '6', '7');
INSERT INTO `q_choice` VALUES ('25', '7', '1');
INSERT INTO `q_choice` VALUES ('26', '7', '2');
INSERT INTO `q_choice` VALUES ('27', '7', '3');
INSERT INTO `q_choice` VALUES ('28', '7', '4');
INSERT INTO `q_choice` VALUES ('29', '8', '1');
INSERT INTO `q_choice` VALUES ('30', '8', '2');
INSERT INTO `q_choice` VALUES ('31', '8', '3');
INSERT INTO `q_choice` VALUES ('32', '8', '4');
INSERT INTO `q_choice` VALUES ('33', '9', '1');
INSERT INTO `q_choice` VALUES ('34', '9', '2');
INSERT INTO `q_choice` VALUES ('35', '9', '5');
INSERT INTO `q_choice` VALUES ('36', '9', '6');
INSERT INTO `q_choice` VALUES ('37', '9', '7');
INSERT INTO `q_choice` VALUES ('38', '10', '2');
INSERT INTO `q_choice` VALUES ('39', '10', '4');
INSERT INTO `q_choice` VALUES ('40', '10', '5');
INSERT INTO `q_choice` VALUES ('41', '10', '6');
INSERT INTO `q_choice` VALUES ('42', '15', '1');
INSERT INTO `q_choice` VALUES ('43', '15', '2');
INSERT INTO `q_choice` VALUES ('44', '15', '3');
INSERT INTO `q_choice` VALUES ('45', '15', '4');
INSERT INTO `q_choice` VALUES ('46', '16', '1');
INSERT INTO `q_choice` VALUES ('47', '16', '2');
INSERT INTO `q_choice` VALUES ('48', '16', '3');
INSERT INTO `q_choice` VALUES ('49', '16', '4');

-- ----------------------------
-- Table structure for q_img
-- ----------------------------
DROP TABLE IF EXISTS `q_img`;
CREATE TABLE `q_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_img
-- ----------------------------

-- ----------------------------
-- Table structure for q_student_answer
-- ----------------------------
DROP TABLE IF EXISTS `q_student_answer`;
CREATE TABLE `q_student_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `quiz_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`user_id`),
  KEY `testpaper_id` (`quiz_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `q_student_answer_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_student_answer
-- ----------------------------
INSERT INTO `q_student_answer` VALUES ('11', '10', '4', '4', null);
INSERT INTO `q_student_answer` VALUES ('12', '10', '4', '3', null);
INSERT INTO `q_student_answer` VALUES ('13', '10', '5', '6', null);
INSERT INTO `q_student_answer` VALUES ('14', '10', '5', '5', null);
INSERT INTO `q_student_answer` VALUES ('15', '10', '7', '7', null);
INSERT INTO `q_student_answer` VALUES ('16', '10', '7', '5', null);
INSERT INTO `q_student_answer` VALUES ('17', '10', '7', '4', null);
INSERT INTO `q_student_answer` VALUES ('18', '10', '7', '6', null);
INSERT INTO `q_student_answer` VALUES ('19', '10', '8', '3', null);
INSERT INTO `q_student_answer` VALUES ('20', '10', '8', '4', null);
INSERT INTO `q_student_answer` VALUES ('21', '2', '9', '3', '我的答案');
INSERT INTO `q_student_answer` VALUES ('22', '2', '9', '4', '我的答案');
INSERT INTO `q_student_answer` VALUES ('23', '2', '9', '5', '我的答案');
INSERT INTO `q_student_answer` VALUES ('24', '2', '9', '6', '我的答案');
INSERT INTO `q_student_answer` VALUES ('25', '2', '9', '3', '我的答案');
INSERT INTO `q_student_answer` VALUES ('26', '2', '9', '4', '我的答案');
INSERT INTO `q_student_answer` VALUES ('27', '2', '9', '5', '我的答案');
INSERT INTO `q_student_answer` VALUES ('28', '2', '9', '6', '我的答案');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'student');
INSERT INTO `role` VALUES ('3', 'teacher');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  `permission_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yang', '123456', '1198790425@qq.com');
INSERT INTO `user` VALUES ('2', 'peng', '123456', '898474009@qq.com');
INSERT INTO `user` VALUES ('3', 'chen', '123456', '898474009@qq.com');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('1', '3');
INSERT INTO `user_role` VALUES ('3', '3');
