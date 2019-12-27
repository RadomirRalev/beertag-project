-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.10-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for beer
CREATE DATABASE IF NOT EXISTS `beer` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `beer`;

-- Dumping structure for table beer.beer
CREATE TABLE IF NOT EXISTS `beer` (
  `beer_id` int(11) NOT NULL AUTO_INCREMENT,
  `beer_picture` varchar(30) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `abv` double DEFAULT NULL,
  `beer_country_fk` int(11) DEFAULT NULL,
  `beer_brewery_fk` int(11) DEFAULT NULL,
  `beer_style_fk` int(11) DEFAULT NULL,
  `avg_rating` double DEFAULT 0,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`beer_id`),
  KEY `beer_country__fk` (`beer_country_fk`),
  KEY `beer_brewery__fk` (`beer_brewery_fk`),
  KEY `beer_style__fk` (`beer_style_fk`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `beer_brewery__fk` FOREIGN KEY (`beer_brewery_fk`) REFERENCES `brewery` (`brewery_id`),
  CONSTRAINT `beer_country__fk` FOREIGN KEY (`beer_country_fk`) REFERENCES `country` (`country_id`),
  CONSTRAINT `beer_style__fk` FOREIGN KEY (`beer_style_fk`) REFERENCES `style` (`style_id`),
  CONSTRAINT `created_by` FOREIGN KEY (`created_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.beertag
CREATE TABLE IF NOT EXISTS `beertag` (
  `beertag_id` int(11) NOT NULL AUTO_INCREMENT,
  `beer_beer_id` int(11) DEFAULT NULL,
  `tag_tag_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`beertag_id`),
  KEY `beertag_tag_tag_id_fk` (`tag_tag_id`),
  KEY `beertag_beer_beer_id_fk` (`beer_beer_id`),
  CONSTRAINT `beertag_beer_beer_id_fk` FOREIGN KEY (`beer_beer_id`) REFERENCES `beer` (`beer_id`),
  CONSTRAINT `beertag_tag_tag_id_fk` FOREIGN KEY (`tag_tag_id`) REFERENCES `tag` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.brewery
CREATE TABLE IF NOT EXISTS `brewery` (
  `name` varchar(80) NOT NULL,
  `brewery_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`brewery_id`),
  UNIQUE KEY `brewery_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.country
CREATE TABLE IF NOT EXISTS `country` (
  `name` varchar(30) DEFAULT NULL,
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.drank_beer
CREATE TABLE IF NOT EXISTS `drank_beer` (
  `drank_beer_id` int(11) NOT NULL AUTO_INCREMENT,
  `drank_beer_user_id` int(11) DEFAULT NULL,
  `drank_beer_beer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`drank_beer_id`),
  UNIQUE KEY `drank_beer_rating_unique` (`drank_beer_user_id`,`drank_beer_beer_id`),
  KEY `drank_beer_user_user_id_fk` (`drank_beer_user_id`),
  KEY `drank_beer_beer_beer_id_fk` (`drank_beer_beer_id`),
  CONSTRAINT `drank_beer_beer_beer_id_fk` FOREIGN KEY (`drank_beer_beer_id`) REFERENCES `beer` (`beer_id`),
  CONSTRAINT `drank_beer_user_user_id_fk` FOREIGN KEY (`drank_beer_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.rating
CREATE TABLE IF NOT EXISTS `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) NOT NULL,
  `drank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rating_id`),
  UNIQUE KEY `rating_drank_id_uindex` (`drank_id`),
  CONSTRAINT `rating_drank_beer__fk` FOREIGN KEY (`drank_id`) REFERENCES `drank_beer` (`drank_beer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.style
CREATE TABLE IF NOT EXISTS `style` (
  `name` varchar(60) DEFAULT NULL,
  `style_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.tag
CREATE TABLE IF NOT EXISTS `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) DEFAULT NULL,
  `nickname` varchar(40) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `picture` text DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK_user_roles_roles` (`role_id`),
  KEY `FK_user_roles_user` (`user_id`),
  CONSTRAINT `FK_user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FK_user_roles_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table beer.wish_beer
CREATE TABLE IF NOT EXISTS `wish_beer` (
  `beer_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_user_id` int(11) NOT NULL,
  `beer_beer_id` int(11) NOT NULL,
  PRIMARY KEY (`beer_user_id`),
  KEY `wish_beer_user_user_id_fk` (`user_user_id`),
  KEY `wish_beer_beer_beer_id_fk` (`beer_beer_id`),
  CONSTRAINT `wish_beer_beer_beer_id_fk` FOREIGN KEY (`beer_beer_id`) REFERENCES `beer` (`beer_id`),
  CONSTRAINT `wish_beer_user_user_id_fk` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
