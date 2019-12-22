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
  `abv` varchar(11) DEFAULT NULL,
  `beer_country_fk` int(11) DEFAULT NULL,
  `beer_brewery_fk` int(11) DEFAULT NULL,
  `beer_style_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`beer_id`),
  KEY `beer_country__fk` (`beer_country_fk`),
  KEY `beer_brewery__fk` (`beer_brewery_fk`),
  KEY `beer_style__fk` (`beer_style_fk`),
  CONSTRAINT `beer_brewery__fk` FOREIGN KEY (`beer_brewery_fk`) REFERENCES `brewery` (`brewery_id`),
  CONSTRAINT `beer_country__fk` FOREIGN KEY (`beer_country_fk`) REFERENCES `country` (`country_id`),
  CONSTRAINT `beer_style__fk` FOREIGN KEY (`beer_style_fk`) REFERENCES `style` (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table beer.beer: ~12 rows (approximately)
/*!40000 ALTER TABLE `beer` DISABLE KEYS */;
INSERT INTO `beer` (`beer_id`, `beer_picture`, `description`, `name`, `abv`, `beer_country_fk`, `beer_brewery_fk`, `beer_style_fk`) VALUES
	(1, 'Test', 'The design reveals it: This beer comes from Bavaria! The specialty from the house of Egerer is balanced and tasty, with a good and surprisingly high amount of aromatic and bitter hops that give the beer a bittersweet character. The color is golden yellow, with a fine white foam crown. To quote the Brewmaster: "It\'s like sunshine!"', 'Urtyp Hell Edel Bayer', '4.9%', 3, 9, 3),
	(2, 'Test', 'Blend of lambics aged 18 to 20 months and of Bergeron apricots.', 'Fou\' Foune', '5.0%', 5, 1, 4),
	(3, 'Test', 'The heaviest of the Westvleteren beers, the 12 is a quadrupel style beer, which can be recognized by its yellow cap. Like the other Westvleteren beers, the bottle does not have a label, and the cap therefore has all the required information.', 'Trappist Westvleteren 12', '10.2%', 5, 8, 5),
	(4, 'Test', 'Hazelnut Liquor Barrel Aged Imperial Stout - 2018 Edition\r\nCollaboration w. FrauGruber ', 'Black Rabbit', '12.0%', 4, 7, 6),
	(5, 'Test', 'This barley wine is a blend of multiple batches of beer which were aged in different spirits oak barrels. It\'s complex aroma and taste of oak, dark and sweet caramel, vanilla, coconut, cocoa, roast, dark fruits and berries is a product of a complex brewing combined with the long maturation time and in the end of time spent in oak barrels. It will cellar very well, but do not go in to decades of ageing. It is a sipper and perfect to share on a special occasion. ', 'Hagger Blend 1116', '12.1%', 4, 5, 2),
	(6, 'Test', 'Sweet Stout of Mine brings you back to the most blissful memories of your childhood with its taste of crème brûlée and vanilla and then kicks you back to reality with the punch it packs, reminding you that you’re all grown up now, enough that you can enjoy this kind of treat. With vegan lactose, so it can be enjoyed equally by everyone, this is our favourite kind of dessert nowadays. ', 'Sweet Stout of Mine', '10.5%', 1, 3, 6),
	(7, 'Test', 'We named our beer after the psychedelic Tropicalia Samba music from the 70s. We always wanted to include a tropical ingredient in our beer and Hibiscus flowers fit the bill!', 'Tropikalia IPA', '7.0%', 1, 4, 7),
	(8, 'Test', 'Caramel stout aged for almost 6 months in oak barrels previously containing Arkanj brandy (Calvados) made by the Kovilj Monastery. ', '02 Kolaboracija Caramel Stout', '10.0%', 2, 6, 6),
	(9, 'Test', 'Milk Stout, smooth, full mouth feel. Strong body with distinctive flavours of coffee, cocoa, vanilla and tropical notes in the back, a real treat for the connoisseurs. Not for lactose intolerant.', 'Mikkeller Vista Milk Stout', '5.8%', 2, 2, 8),
	(10, 'Test', 'Flying Dogma Galaxy IPA is the first collaboration between two breweries between Serbia and USA! Crispy, fruity and a little bit spicy American style IPA, ideal for this spring days!', 'Flying Dogma Galaxy IPA', '6.6%', 2, 2, 9),
	(11, 'Test', 'Test description.', 'TestBeer', '5.0%', NULL, 2, 3),
	(12, 'Test', 'Test description.', 'TestBeer', '5.0%', NULL, 2, 3);
/*!40000 ALTER TABLE `beer` ENABLE KEYS */;

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

-- Dumping data for table beer.beertag: ~10 rows (approximately)
/*!40000 ALTER TABLE `beertag` DISABLE KEYS */;
INSERT INTO `beertag` (`beertag_id`, `beer_beer_id`, `tag_tag_id`) VALUES
	(8, 1, 3),
	(9, 5, 6),
	(10, 8, 2),
	(11, 5, 5),
	(12, 7, 2),
	(13, 3, 9),
	(14, 1, 1),
	(15, 8, 3),
	(16, 6, 2),
	(17, 5, 1);
/*!40000 ALTER TABLE `beertag` ENABLE KEYS */;

-- Dumping structure for table beer.brewery
CREATE TABLE IF NOT EXISTS `brewery` (
  `name` varchar(80) NOT NULL,
  `brewery_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`brewery_id`),
  UNIQUE KEY `brewery_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table beer.brewery: ~9 rows (approximately)
/*!40000 ALTER TABLE `brewery` DISABLE KEYS */;
INSERT INTO `brewery` (`name`, `brewery_id`) VALUES
	('Bierol', 7),
	('Brasserie Cantillon', 1),
	('Brauhaus Bevog', 5),
	('Brouwerij De Sint-Sixtusabdij van Westvleteren', 8),
	('Dogma Brewery', 2),
	('Kabinet Brewery', 6),
	('Metalhead', 3),
	('Privatbrauerei H. Egerer', 9),
	('White Stork Beer Co.', 4);
/*!40000 ALTER TABLE `brewery` ENABLE KEYS */;

-- Dumping structure for table beer.country
CREATE TABLE IF NOT EXISTS `country` (
  `name` varchar(30) DEFAULT NULL,
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table beer.country: ~5 rows (approximately)
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`name`, `country_id`) VALUES
	('Austria', 4),
	('Belgium', 5),
	('Bulgaria', 1),
	('Germany', 3),
	('Serbia', 2);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;

-- Dumping structure for table beer.drank_beer
CREATE TABLE IF NOT EXISTS `drank_beer` (
  `beer_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_user_id` int(11) DEFAULT NULL,
  `rating_rating_id` int(11) DEFAULT NULL,
  `beer_beer_id` int(11) DEFAULT NULL,
  `column_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`beer_user_id`),
  UNIQUE KEY `drank_beer_rating_rating_id_uindex` (`rating_rating_id`),
  KEY `drank_beer_user_user_id_fk` (`user_user_id`),
  KEY `drank_beer_beer_beer_id_fk` (`beer_beer_id`),
  CONSTRAINT `drank_beer_beer_beer_id_fk` FOREIGN KEY (`beer_beer_id`) REFERENCES `beer` (`beer_id`),
  CONSTRAINT `drank_beer_rating_rating_id_fk` FOREIGN KEY (`rating_rating_id`) REFERENCES `rating` (`rating_id`),
  CONSTRAINT `drank_beer_user_user_id_fk` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table beer.drank_beer: ~0 rows (approximately)
/*!40000 ALTER TABLE `drank_beer` DISABLE KEYS */;
/*!40000 ALTER TABLE `drank_beer` ENABLE KEYS */;

-- Dumping structure for table beer.rating
CREATE TABLE IF NOT EXISTS `rating` (
  `rating_value` int(11) DEFAULT NULL,
  `rating_id` int(11) NOT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table beer.rating: ~0 rows (approximately)
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;

-- Dumping structure for table beer.style
CREATE TABLE IF NOT EXISTS `style` (
  `name` varchar(60) DEFAULT NULL,
  `style_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table beer.style: ~9 rows (approximately)
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` (`name`, `style_id`) VALUES
	('Stout - Imperial / Double', 1),
	('Barleywine ', 2),
	('Lager - Helles', 3),
	('Lambic - Fruit', 4),
	('Belgian Quadrupel', 5),
	('Stout - Imperial / Double Milk', 6),
	('IPA - Red', 7),
	('Stout - Milk / Sweet', 8),
	('IPA - American', 9);
/*!40000 ALTER TABLE `style` ENABLE KEYS */;

-- Dumping structure for table beer.tag
CREATE TABLE IF NOT EXISTS `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table beer.tag: ~10 rows (approximately)
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`tag_id`, `name`) VALUES
	(1, 'Coffee'),
	(2, 'Caramel'),
	(3, 'Light'),
	(4, 'Dark'),
	(5, 'Sweet'),
	(6, 'Intense'),
	(7, 'Fresh'),
	(8, 'Wheat'),
	(9, 'White'),
	(10, 'Brown');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;

-- Dumping structure for table beer.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) DEFAULT NULL,
  `nickname` varchar(40) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `picture` text DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table beer.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table beer.wish_beer
CREATE TABLE IF NOT EXISTS `wish_beer` (
  `beer_user_id` int(11) NOT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  `beer_beer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`beer_user_id`),
  KEY `wish_beer_user_user_id_fk` (`user_user_id`),
  KEY `wish_beer_beer_beer_id_fk` (`beer_beer_id`),
  CONSTRAINT `wish_beer_beer_beer_id_fk` FOREIGN KEY (`beer_beer_id`) REFERENCES `beer` (`beer_id`),
  CONSTRAINT `wish_beer_user_user_id_fk` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table beer.wish_beer: ~0 rows (approximately)
/*!40000 ALTER TABLE `wish_beer` DISABLE KEYS */;
/*!40000 ALTER TABLE `wish_beer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
