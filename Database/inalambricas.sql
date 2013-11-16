-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 16, 2013 at 04:14 PM
-- Server version: 5.5.31
-- PHP Version: 5.4.4-14+deb7u5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `inalambricas`
--

-- --------------------------------------------------------

--
-- Table structure for table `Devices`
--

CREATE TABLE IF NOT EXISTS `Devices` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(25) NOT NULL,
  `HARD_ADDRESS` varchar(25) NOT NULL,
  `LEDA` varchar(2) NOT NULL,
  `LEDB` varchar(2) NOT NULL,
  `LEDC` varchar(2) NOT NULL,
  `TEMPERATURE` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `Devices`
--

INSERT INTO `Devices` (`ID`, `NAME`, `HARD_ADDRESS`, `LEDA`, `LEDB`, `LEDC`, `TEMPERATURE`) VALUES
(1, 'Arduino 1', '013200a48556', '0', '1', '1', '21.0'),
(2, 'Arduino 2', '032165478963', '1', '0', '0', '22.0');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
