/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.18-log : Database - studentstatus
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentstatus` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `studentstatus`;

/*Table structure for table `auth` */

DROP TABLE IF EXISTS `auth`;

CREATE TABLE `auth` (
  `authId` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(20) DEFAULT NULL,
  `authPath` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `authDescription` varchar(200) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `iconCls` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`authId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `auth` */

insert  into `auth`(`authId`,`authName`,`authPath`,`parentId`,`authDescription`,`state`,`iconCls`) values (1,'某系统','',-1,NULL,'closed','icon-home'),(2,'权限管理','',1,NULL,'closed','icon-permission'),(3,'学生管理','',1,NULL,'closed','icon-student'),(5,'成绩管理','cjgl.html',3,NULL,'open','icon-item'),(6,'学生信息管理','xxjbxx.html',3,NULL,'open','icon-item'),(7,'学籍管理','xjgl.html',3,NULL,'open','icon-item'),(8,'奖惩管理','jcgl.html',3,NULL,'open','icon-item'),(12,'用户管理','userManage.html',2,NULL,'open','icon-userManage'),(13,'角色管理','roleManage.html',2,NULL,'open','icon-roleManage'),(14,'菜单管理','menuManage.html',2,NULL,'open','icon-menuManage'),(15,'修改密码','',1,NULL,'open','icon-modifyPassword'),(16,'安全退出','',1,NULL,'open','icon-exit'),(23,'B','B1B',11,'B','open','icon-item');

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `deptNo` char(255) NOT NULL,
  `deptName` char(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`id`,`deptNo`,`deptName`) values (1,'01','数学系'),(2,'02','英语系');

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `studentNo` varchar(255) NOT NULL,
  `subjectId` int(10) NOT NULL,
  `score` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

insert  into `grade`(`id`,`studentNo`,`subjectId`,`score`) values (5,'222',1,99),(6,'222',2,78),(8,'222',3,68),(9,'222',4,100),(15,'333',1,98),(16,'333',2,89),(17,'333',3,89),(18,'333',4,89),(23,'555',1,67),(24,'555',2,77),(25,'555',3,77),(26,'555',4,77),(27,'666',1,66),(28,'666',2,66),(29,'666',3,66),(30,'666',4,66),(31,'777',1,77),(32,'777',2,77),(33,'777',3,77),(34,'777',4,77),(35,'888',1,88),(36,'888',2,88),(37,'888',3,88),(38,'888',4,88),(47,'999',1,99),(48,'999',2,89),(49,'999',3,56),(50,'999',4,89),(55,'1010',1,89),(56,'1010',2,98),(57,'1010',3,89),(58,'1010',4,78),(59,'1111',1,90),(60,'1111',2,91),(61,'1111',3,92),(62,'1111',4,93),(63,'77777',1,60),(64,'77777',2,60),(65,'77777',3,60),(66,'77777',4,61);

/*Table structure for table `reward` */

DROP TABLE IF EXISTS `reward`;

CREATE TABLE `reward` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `studentNo` varchar(255) NOT NULL,
  `deptNo` varchar(255) NOT NULL,
  `rewardInfo` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `reward` */

insert  into `reward`(`id`,`studentNo`,`deptNo`,`rewardInfo`) values (1,'2018601201','01','9988'),(2,'222','02','2222'),(4,'333','02','777777'),(6,'555','02',NULL),(7,'666','02','666'),(8,'777','01',NULL),(9,'888','02',NULL),(13,'999','02',NULL),(15,'1010','02','1010'),(16,'网','01',NULL),(17,'1111','01',NULL),(18,'77777','01',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(10) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `authIds` varchar(255) DEFAULT NULL,
  `roleDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`,`authIds`,`roleDescription`) values (1,'超级管理员','1,2,12,13,14,3,5,6,7,8,15,16,31','具有最高权限'),(2,'宿舍管理员','1,3,5,6,7,8,16','宿管'),(3,'班主任','3,5','班主任是你能随便得罪的?');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `studentNo` varchar(255) NOT NULL,
  `studentName` varchar(255) NOT NULL,
  `sex` varchar(25) NOT NULL,
  `deptNo` varchar(10) NOT NULL,
  `IdCard` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postcode` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`studentNo`,`studentName`,`sex`,`deptNo`,`IdCard`,`address`,`postcode`,`state`) values (2,'222','222','222','02','222','22','2222','2'),(4,'333','333','333','02','333','333','333','2'),(6,'555','555','55','02','55','55','55','1'),(7,'666','666','66','02','66','66','66','1'),(8,'777','777','7','01','77','777','777','2'),(9,'888','888','男','02','888','888','888','1'),(13,'999','999','999','02','999','999','999','1'),(15,'1010','1010','女','02','1010','1010','10101','2'),(16,'1111','张三2','男2','02','2324324322','ccc2','2332','2'),(17,'77777','ss','1','01','3242','234','432','2');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `subject` */

insert  into `subject`(`id`,`subjectName`) values (1,'高数'),(2,'大学英语'),(3,'体育'),(4,'思想政治理论');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL,
  `userType` int(1) NOT NULL,
  `roleId` int(10) NOT NULL,
  PRIMARY KEY (`id`,`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`password`,`userType`,`roleId`) values (1,'javalz','12',1,1),(2,'marry','123456',2,2),(3,'zhangsan','12',2,3),(4,'cc','cc',2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
