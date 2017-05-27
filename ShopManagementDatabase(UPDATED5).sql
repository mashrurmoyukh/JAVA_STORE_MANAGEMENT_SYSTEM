/*
SQLyog Ultimate v8.82 
MySQL - 5.5.5-10.1.8-MariaDB : Database - itemlist
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`itemlist` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `itemlist`;

/*Table structure for table `categorie` */

DROP TABLE IF EXISTS `categorie`;

CREATE TABLE `categorie` (
  `categories` varchar(30) DEFAULT 'Dummy_Category'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `categorie` */

insert  into `categorie`(`categories`) values ('SELECT ONE'),('Electronics'),('Grocery'),('Cosmetics'),('Clothing'),('Stationary'),('Girl-Wear');

/*Table structure for table `history2` */

DROP TABLE IF EXISTS `history2`;

CREATE TABLE `history2` (
  `id` int(11) DEFAULT NULL,
  `quantitySold` varchar(100) DEFAULT NULL,
  `day` varchar(100) DEFAULT NULL,
  `month` varchar(100) DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  `itemprice` varchar(100) DEFAULT NULL,
  `itemname` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `history2` */

insert  into `history2`(`id`,`quantitySold`,`day`,`month`,`year`,`itemprice`,`itemname`) values (4,'2','16','11','2015','2000','Xbox 360 Controller'),(11,'3','16','11','2015','120','Flashlight'),(4,'2','16','11','2015','2000','Xbox 360 Controller'),(5,'3','15','11','2015','20','Potato'),(5,'3','15','11','2015','20','Potato'),(8,'4','12','10','2015','500','Denim - Jens'),(9,'5','12','10','2015','700','Green L/M/S T-Shirt'),(10,'5','16','5','2014','30','Onion'),(10,'5','16','5','2014','30','Onion'),(10,'5','16','5','2014','30','Onion'),(11,'2','2','3','2013','120','Flashlight'),(11,'2','2','3','2013','120','Flashlight'),(4,'1','17','11','2015','2000','Xbox 360 Controller'),(5,'1','17','11','2015','20','Potato'),(8,'7','17','11','2015','500','Denim - Jens'),(8,'2','17','11','2015','500','Denim - Jens'),(8,'6','17','11','2015','500','Denim - Jens'),(4,'1','17','11','2015','2000','Xbox 360 Controller'),(6,'2','17','11','2015','50','Rice - NajirShil'),(5,'3','17','11','2015','20','Potato'),(11,'1','17','11','2015','120','Flashlight'),(5,'2','17','11','2015','20','Potato'),(6,'3','17','12','2015','50','Rice - NajirShil'),(11,'6','17','12','2015','120','Flashlight'),(11,'4','17','12','2015','120','Flashlight'),(11,'6','17','12','2015','120','Flashlight'),(8,'2','17','12','2015','500','Denim - Jens'),(4,'1','17','12','2015','2000','Xbox 360 Controller'),(4,'1','17','12','2015','2000','Xbox 360 Controller'),(4,'1','17','12','2015','2000','Xbox 360 Controller'),(11,'1','17','12','2015','120','Flashlight'),(11,'2','17','12','2015','120','Flashlight'),(11,'3','17','12','2015','120','Flashlight'),(11,'1','17','12','2015','120','Flashlight'),(11,'2','17','12','2015','120','Flashlight'),(11,'2','17','12','2015','120','Flashlight'),(9,'12','17','12','2015','700','Green L/M/S T-Shirt'),(12,'2','18','12','2015','20','Deli-Pencil'),(5,'4','18','12','2015','20','Potato'),(5,'1','18','12','2015','20','Potato'),(5,'1','18','12','2015','20','Potato'),(4,'1','18','12','2015','2000','Xbox 360 Controller'),(4,'1','18','12','2015','2000','Xbox 360 Controller'),(11,'7','18','12','2015','120','Flashlight'),(11,'21','18','12','2015','120','Flashlight'),(11,'21','18','12','2015','120','Flashlight'),(11,'6','18','12','2015','120','Flashlight'),(11,'6','18','12','2015','120','Flashlight'),(11,'3','18','12','2015','120','Flashlight'),(11,'3','18','12','2015','120','Flashlight'),(11,'2','18','12','2015','120','Flashlight'),(11,'1','18','12','2015','120','Flashlight'),(11,'2','18','12','2015','120','Flashlight'),(11,'1','18','12','2015','120','Flashlight'),(12,'18','18','12','2015','20','Deli-Pencil'),(12,'18','18','12','2015','20','Deli-Pencil'),(10,'1','18','12','2015','30','Onion'),(10,'1','18','12','2015','30','Onion'),(10,'1','18','12','2015','30','Onion'),(10,'1','18','12','2015','30','Onion'),(10,'1','18','12','2015','30','Onion'),(10,'1','18','12','2015','30','Onion'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(8,'1','18','12','2015','500','Denim - Jens'),(9,'1','18','12','2015','700','Green L/M/S T-Shirt'),(9,'1','18','12','2015','700','Green L/M/S T-Shirt'),(9,'1','18','12','2015','700','Green L/M/S T-Shirt'),(8,'1','18','12','2015','500','Denim - Jens'),(5,'3','18','12','2015','20','Potato');

/*Table structure for table `itemlist2` */

DROP TABLE IF EXISTS `itemlist2`;

CREATE TABLE `itemlist2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(50) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `itemlist2` */

insert  into `itemlist2`(`id`,`itemname`,`quantity`,`price`,`category`) values (4,'Xbox 360 Controller','56','2200','Electronics'),(5,'Potato','3','20','Grocery'),(6,'Rice - NajirShil','10','50','Grocery'),(8,'Denim - Jens','51','500','Clothing'),(9,'Green L/M/S T-Shirt','84','700','Clothing'),(10,'Onion','5','30','Grocery'),(11,'Flashlight','4','120','Electronics'),(12,'Deli-Pencil','12','20','Stationary');

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `seller` */

insert  into `seller`(`id`,`name`,`password`,`email`) values (1,'irfan','12','gmail');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`email`) values (1,'abc','abc','abc'),(2,'abc2','abc2','abc2'),(3,'admin','admin123','admin'),(4,'user1','user1','@gmail');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
