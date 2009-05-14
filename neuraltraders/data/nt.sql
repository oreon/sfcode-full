/*
SQLyog Community Edition- MySQL GUI v7.15 
MySQL - 5.0.67-community-nt : Database - neural
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`neural` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `neural`;

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `name` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `price` int(10) NOT NULL,
  `venue` varchar(60) NOT NULL,
  `maxseat` int(10) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `description` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `event` */

insert  into `event`(`name`,`date`,`time`,`price`,`venue`,`maxseat`,`id`,`description`) values ('jagdeep','2008-08-02','19:00:00',23,'165 John Tabor',200,1,' About currencies'),('jagdeep','2008-08-02','19:00:00',23,'165 John Tabor',200,2,' About currencies'),('jagdeep','2008-08-02','19:00:00',23,'165 John Tabor',200,3,' About currencies'),('options','2009-07-02','19:00:00',23,'165 John Tabor',2,4,' About currencies'),('Currency Trading','2009-06-02','19:00:00',13,'165 John Tabor',5,5,' About currencies'),('Forex Seminar','2009-08-02','19:00:00',13,'165 John Tabor',5,6,' About currencies');

/*Table structure for table `eventregistration` */

DROP TABLE IF EXISTS `eventregistration`;

CREATE TABLE `eventregistration` (
  `userid` int(45) NOT NULL,
  `eventid` int(45) default NULL,
  `id` int(11) NOT NULL auto_increment,
  `status` varchar(45) NOT NULL default 'OK',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `eventregistration` */

insert  into `eventregistration`(`userid`,`eventid`,`id`,`status`) values (3,4,1,'cancel'),(3,6,2,'cancel'),(4,4,3,'OK'),(4,5,4,'OK'),(4,4,5,'OK'),(4,4,6,'OK'),(4,4,7,'OK'),(5,4,8,'cancel'),(5,5,9,'cancel'),(5,4,10,'cancel'),(5,5,11,'cancel');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL default 'REGULAR',
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`firstname`,`lastname`,`email`,`phone`,`username`,`password`,`type`,`id`) values ('','','sdf','4162378901','','','REGULAR',1),('','','habcd','`nqjfecbdc','','ncdwdfj','REGULAR',2),('harp','harp','har@gmail.com','4162841965','','dumbo','REGULAR',3),('harp','harp','har@gmail.com','4162841965','','duknpoi','REGULAR',4),('harp','harp','har@gmail.com','4162841965','harp','dumbo','REGULAR',5),('jag','mohan','singhjess@gmail.com','4162841965','harp','mohali','REGULAR',6),('sf','ss','singhjess@gmail.com','4162841965','','bikder','REGULAR',7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
