-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 16. Mai 2015 um 15:53
-- Server Version: 5.5.41
-- PHP-Version: 5.4.39-0+deb7u2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `spapp_db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bewertungen`
--

CREATE TABLE IF NOT EXISTS `bewertungen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) NOT NULL,
  `speiseId` int(11) NOT NULL,
  `action` enum('like','dislike') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userIdSpeiseId` (`userId`,`speiseId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=50 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dataface__modules`
--

CREATE TABLE IF NOT EXISTS `dataface__modules` (
  `module_name` varchar(255) NOT NULL,
  `module_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`module_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dataface__mtimes`
--

CREATE TABLE IF NOT EXISTS `dataface__mtimes` (
  `name` varchar(255) NOT NULL,
  `mtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `dataface__mtimes`
--

INSERT INTO `dataface__mtimes` (`name`, `mtime`) VALUES
('bewertungen', 1431342821),
('dataface__modules', 1431342821),
('dataface__mtimes', 1431342827),
('dataface__version', 1431342821),
('speisen', 1431342821),
('termine', 1431342821),
('zusatzstoffe', 1431342821),
('zusatzstoffelink', 1431342821);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dataface__version`
--

CREATE TABLE IF NOT EXISTS `dataface__version` (
  `version` int(5) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `dataface__version`
--

INSERT INTO `dataface__version` (`version`) VALUES
(0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `speisen`
--

CREATE TABLE IF NOT EXISTS `speisen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `art` enum('Vorspeise','Leichte Vollkost','Dessert','Gemüseteller','Vegetarisch','Vollkost','Beilagen') NOT NULL,
  `beachte` varchar(255) DEFAULT NULL,
  `kcal` int(11) DEFAULT NULL,
  `eiweisse` int(11) DEFAULT NULL,
  `fette` int(11) DEFAULT NULL,
  `kohlenhydrate` int(11) DEFAULT NULL,
  `beschreibung` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_art` (`name`,`art`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Daten für Tabelle `speisen`
--

INSERT INTO `speisen` (`id`, `name`, `art`, `beachte`, `kcal`, `eiweisse`, `fette`, `kohlenhydrate`, `beschreibung`) VALUES
(1, 'Möhrensalat', 'Vorspeise', 'vegan', NULL, NULL, NULL, NULL, ''),
(2, 'Buletten mit Kürbis und Kartoffelpüree', 'Vollkost', 'Schwein', 534, 24, 26, 38, ''),
(3, 'Kürbis, Schmorkohl', 'Gemüseteller', 'vegan', NULL, NULL, NULL, NULL, ''),
(4, 'gelbe Fruchtgrütze', 'Dessert', 'vegetarisch', NULL, NULL, NULL, NULL, ''),
(5, 'Vegetarische Pizza', 'Vegetarisch', 'Vegetarisch', NULL, NULL, NULL, NULL, NULL),
(6, 'Tofu', 'Leichte Vollkost', 'ES IST TOFU', 0, 0, 0, 0, 'ACHTUNG TOFU'),
(7, 'Banane', 'Beilagen', 'Gelb', NULL, NULL, NULL, NULL, NULL),
(8, 'Gurkensalat', 'Vorspeise', 'kann Spuren von Gurken beinhalten', 0, 0, 0, 0, 'Gurken'),
(9, 'Eis', 'Dessert', 'Eiskalt', 0, 0, 0, 0, 'Vorsicht kalt'),
(10, 'Schnitzel mit Kartoffeln und Soße', 'Vollkost', 'Schnitzel kann Spuren von Schwein beinhalten', 0, 0, 0, 0, 'Schnitzel ist krass');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `termine`
--

CREATE TABLE IF NOT EXISTS `termine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  `speise` int(11) NOT NULL,
  `preis` decimal(6,2) NOT NULL,
  `isDiaet` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=173 ;

--
-- Daten für Tabelle `termine`
--

INSERT INTO `termine` (`id`, `datum`, `speise`, `preis`, `isDiaet`) VALUES
(129, '2015-04-06', 1, 0.00, 0),
(130, '2015-04-06', 5, 0.00, 0),
(131, '2015-04-06', 2, 0.00, 0),
(132, '2015-04-06', 7, 0.00, 0),
(133, '2015-04-06', 6, 0.00, 1),
(134, '2015-04-07', 8, 0.00, 0),
(135, '2015-04-07', 5, 0.00, 0),
(136, '2015-04-07', 2, 0.00, 0),
(137, '2015-04-07', 7, 0.00, 0),
(138, '2015-04-07', 9, 0.00, 0),
(139, '2015-04-08', 8, 0.00, 0),
(140, '2015-04-08', 5, 0.00, 0),
(141, '2015-04-08', 2, 0.00, 0),
(142, '2015-04-08', 7, 0.00, 0),
(143, '2015-04-08', 9, 0.00, 0),
(144, '2015-04-09', 1, 0.00, 0),
(145, '2015-04-09', 5, 0.00, 0),
(146, '2015-04-09', 2, 0.00, 0),
(147, '2015-04-09', 7, 0.00, 0),
(148, '2015-04-09', 4, 0.00, 0),
(149, '2015-04-10', 1, 0.00, 0),
(150, '2015-04-10', 5, 0.00, 0),
(151, '2015-04-10', 2, 0.00, 0),
(152, '2015-04-10', 7, 0.00, 0),
(153, '2015-04-10', 4, 0.00, 0),
(154, '2015-04-13', 8, 0.00, 0),
(155, '2015-04-13', 5, 0.00, 0),
(156, '2015-04-13', 3, 0.00, 0),
(157, '2015-04-13', 2, 0.00, 0),
(158, '2015-04-13', 4, 0.00, 0),
(159, '2015-04-14', 1, 0.00, 0),
(160, '2015-04-14', 2, 0.00, 0),
(161, '2015-04-14', 7, 0.00, 0),
(162, '2015-04-15', 8, 0.00, 0),
(163, '2015-04-15', 5, 0.00, 0),
(164, '2015-04-15', 8, 0.00, 0),
(165, '2015-04-15', 9, 0.00, 0),
(166, '2015-04-16', 5, 0.00, 0),
(167, '2015-04-16', 2, 0.00, 0),
(168, '2015-04-16', 9, 0.00, 0),
(169, '2015-04-17', 1, 0.00, 0),
(170, '2015-04-17', 10, 0.00, 0),
(171, '2015-04-17', 7, 0.00, 0),
(172, '2015-04-17', 9, 0.00, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zusatzstoffe`
--

CREATE TABLE IF NOT EXISTS `zusatzstoffe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `nummer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `zusatzstoffe`
--

INSERT INTO `zusatzstoffe` (`id`, `name`, `nummer`) VALUES
(1, 'Koffein', 2),
(2, 'Nitrate', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zusatzstoffelink`
--

CREATE TABLE IF NOT EXISTS `zusatzstoffelink` (
  `speise` int(11) NOT NULL DEFAULT '0',
  `zusatzstoff` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`speise`,`zusatzstoff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `zusatzstoffelink`
--

INSERT INTO `zusatzstoffelink` (`speise`, `zusatzstoff`) VALUES
(1, 2),
(2, 1),
(2, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
