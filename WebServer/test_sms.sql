-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2015 at 11:07 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test_sms`
--

-- --------------------------------------------------------

--
-- Table structure for table `blocks`
--

CREATE TABLE IF NOT EXISTS `blocks` (
  `block_id` int(10) NOT NULL AUTO_INCREMENT,
  `block_name` varchar(100) NOT NULL,
  `block_alias` varchar(100) NOT NULL,
  `number_rooms` int(100) NOT NULL,
  `logo` varchar(10000) NOT NULL,
  `rep_history` varchar(10000) NOT NULL,
  PRIMARY KEY (`block_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `blocks`
--

INSERT INTO `blocks` (`block_id`, `block_name`, `block_alias`, `number_rooms`, `logo`, `rep_history`) VALUES
(1, 'X', 'Excellence', 22, '', ''),
(2, 'A', 'Aye', 40, '', ''),
(3, 'B', 'Runci', 40, '', ''),
(4, 'C', 'Che', 40, '', ''),
(5, 'D', 'Dynamite', 40, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE IF NOT EXISTS `countries` (
  `country_id` int(10) NOT NULL AUTO_INCREMENT,
  `country` varchar(100) NOT NULL,
  `nationalityCountry` varchar(100) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=193 ;

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`country_id`, `country`, `nationalityCountry`) VALUES
(1, 'Afghanistan', 'Afghan'),
(2, 'Albania', 'Albanian'),
(3, 'Algeria', 'Algerian'),
(4, 'Andorra', 'Andorran'),
(5, 'Angola', 'Angolan'),
(6, 'Antigua and Barbuda', 'Antiguan/Barbudan'),
(7, 'Argentina', 'Argentinean'),
(8, 'Armenia', 'Armenian'),
(9, 'Australia', 'Australian'),
(10, 'Austria', 'Austrian'),
(11, 'Azerbaijan', 'Azerbaijani'),
(12, 'The Bahamas', 'Bahamian'),
(13, 'Bahrain', 'Bahraini'),
(14, 'Bangladesh', 'Bangladeshi'),
(15, 'Barbados', 'Barbadian'),
(16, 'Belarus', 'Belarusian'),
(17, 'Belgium', 'Belgian'),
(18, 'Belize', 'Belizean'),
(19, 'Benin', 'Beninese'),
(20, 'Bhutan', 'Bhutanese'),
(21, 'Bolivia', 'Bolivian'),
(22, 'Bosnia and Herzegovina', 'Bosnian'),
(23, 'Botswana', 'Motswana'),
(24, 'Brazil', 'Brazilian'),
(25, 'Brunei', 'Bruneian'),
(26, 'Bulgaria', 'Bulgarian'),
(27, 'Burkina Faso', 'Burkinabe'),
(28, 'Burundi', 'Burundian'),
(29, 'Cambodia', 'Cambodian'),
(30, 'Cameroon', 'Cameroonian'),
(31, 'Canada', 'Canadian'),
(32, 'Cape Verde', 'Cape Verdian'),
(33, 'Central African Republic', 'Central African'),
(34, 'Chad', 'Chadian'),
(35, 'Chile', 'Chilean'),
(36, 'China', 'Chinese'),
(37, 'Colombia', 'Colombian'),
(38, 'Comoros', 'Comoran'),
(39, 'Congo, Republic of the', 'Congolese'),
(40, 'Congo, Democratic Republic of the', 'Congolese'),
(41, 'Costa Rica', 'Costa Rican'),
(42, 'Cote d''Ivoire', 'Ivorian'),
(43, 'Croatia', 'Croatian'),
(44, 'Cuba', 'Cuban'),
(45, 'Cyprus', 'Cypriot'),
(46, 'Czech Republic', 'Czech'),
(47, 'Denmark', 'Danish'),
(48, 'Djibouti', 'Djibouti'),
(49, 'Dominica', 'Dominican'),
(50, 'Dominican Republic', 'Dominican'),
(51, 'East Timor', 'East Timorese'),
(52, 'Ecuador', 'Ecuadorean'),
(53, 'Egypt', 'Egyptian'),
(54, 'El Salvador', 'Salvadoran'),
(55, 'Equatorial Guinea', 'Equatorial Guinean'),
(56, 'Eritrea', 'Eritrean'),
(57, 'Estonia', 'Estonian'),
(58, 'Ethiopia', 'Ethiopian'),
(59, 'Fiji', 'Fijian'),
(60, 'Finland', 'Finnish'),
(61, 'France', 'French'),
(62, 'Gabon', 'Gabonese'),
(63, 'The Gambia', 'Gambian'),
(64, 'Georgia', 'Georgian'),
(65, 'Germany', 'German'),
(66, 'Ghana', 'Ghanaian'),
(67, 'Greece', 'Greek'),
(68, 'Grenada', 'Grenadian'),
(69, 'Guatemala', 'Guatemalan'),
(70, 'Guinea', 'Guinean'),
(71, 'Guinea-Bissau', 'Guinea-Bissauan'),
(72, 'Guyana', 'Guyanese'),
(73, 'Haiti', 'Haitian'),
(74, 'Honduras', 'Honduran'),
(75, 'Hungary', 'Hungarian'),
(76, 'Iceland', 'Icelander'),
(77, 'India', 'Indian'),
(78, 'Indonesia', 'Indonesian'),
(79, 'Iran', 'Iranian'),
(80, 'Iraq', 'Iraqi'),
(81, 'Ireland', 'Irish'),
(82, 'Israel', 'Israeli'),
(83, 'Italy', 'Italian'),
(84, 'Jamaica', 'Jamaican'),
(85, 'Japan', 'Japanese'),
(86, 'Jordan', 'Jordanian'),
(87, 'Kazakhstan', 'Kazakhstani'),
(88, 'Kenya', 'Kenyan'),
(89, 'Kiribati', 'I-Kiribati'),
(90, 'Korea, North', 'North Korean'),
(91, 'Korea, South', 'South Korean'),
(92, 'Kuwait', 'Kuwaiti'),
(93, 'Kyrgyz Republic', 'Kirghiz'),
(94, 'Laos', 'Laotian'),
(95, 'Latvia', 'Latvian'),
(96, 'Lebanon', 'Lebanese'),
(97, 'Lesotho', 'Mosotho'),
(98, 'Liberia', 'Liberian'),
(99, 'Libya', 'Libyan'),
(100, 'Liechtenstein', 'Liechtensteiner'),
(101, 'Lithuania', 'Lithuanian'),
(102, 'Luxembourg', 'Luxembourger'),
(103, 'Macedonia', 'Macedonian'),
(104, 'Madagascar', 'Malagasy'),
(105, 'Malawi', 'Malawian'),
(106, 'Malaysia', 'Malaysian'),
(107, 'Maldives', 'Maldivan'),
(108, 'Mali', 'Malian'),
(109, 'Malta', 'Maltese'),
(110, 'Marshall Islands', 'Marshallese'),
(111, 'Mauritania', 'Mauritanian'),
(112, 'Mauritius', 'Mauritian'),
(113, 'Mexico', 'Mexican'),
(114, 'Federated States of Micronesia', 'Micronesian'),
(115, 'Moldova', 'Moldovan'),
(116, 'Monaco', 'Monegasque'),
(117, 'Mongolia', 'Mongolian'),
(118, 'Morocco', 'Moroccan'),
(119, 'Mozambique', 'Mozambican'),
(120, 'Myanmar (Burma);', 'Burmese'),
(121, 'Namibia', 'Namibian'),
(122, 'Nauru', 'Nauruan'),
(123, 'Nepal', 'Nepalese'),
(124, 'Netherlands', 'Dutch'),
(125, 'New Zealand', 'New Zealander'),
(126, 'Nicaragua', 'Nicaraguan'),
(127, 'Niger', 'Nigerien'),
(128, 'Nigeria', 'Nigerian'),
(129, 'Norway', 'Norwegian'),
(130, 'Oman', 'Omani'),
(131, 'Pakistan', 'Pakistani'),
(132, 'Palau', 'Palauan'),
(133, 'Panama', 'Panamanian'),
(134, 'Papua New Guinea', 'Papua New Guinean'),
(135, 'Paraguay', 'Paraguayan'),
(136, 'Peru', 'Peruvian'),
(137, 'Philippines', 'Filipino'),
(138, 'Poland', 'Polish'),
(139, 'Portugal', 'Portuguese'),
(140, 'Qatar', 'Qatari'),
(141, 'Romania', 'Romanian'),
(142, 'Russia', 'Russian'),
(143, 'Rwanda', 'Rwandan'),
(144, 'Saint Kitts and Nevis', 'Kittian and Nevisian'),
(145, 'Saint Lucia', 'Saint Lucian'),
(146, 'Samoa', 'Samoan'),
(147, 'San Marino', 'Sammarinese'),
(148, 'Sao Tome and Principe', 'Sao Tomean'),
(149, 'Saudi Arabia', 'Saudi Arabian'),
(150, 'Senegal', 'Senegalese'),
(151, 'Serbia and Montenegro', 'Serbian'),
(152, 'Seychelles', 'Seychellois'),
(153, 'Sierra Leone', 'Sierra Leonean'),
(154, 'Singapore', 'Singaporean'),
(155, 'Slovakia', 'Slovak'),
(156, 'Slovenia', 'Slovene'),
(157, 'Solomon Islands', 'Solomon Islander'),
(158, 'Somalia', 'Somali'),
(159, 'South Africa', 'South African'),
(160, 'Spain', 'Spanish'),
(161, 'Sri Lanka', 'Sri Lankan'),
(162, 'Sudan', 'Sudanese'),
(163, 'Suriname', 'Surinamer'),
(164, 'Swaziland', 'Swazi'),
(165, 'Sweden', 'Swedish'),
(166, 'Switzerland', 'Swiss'),
(167, 'Syria', 'Syrian'),
(168, 'Taiwan', 'Taiwanese'),
(169, 'Tajikistan', 'Tadzhik'),
(170, 'Tanzania', 'Tanzanian'),
(171, 'Thailand', 'Thai'),
(172, 'Togo', 'Togolese'),
(173, 'Tonga', 'Tongan'),
(174, 'Trinidad and Tobago', 'Trinidadian'),
(175, 'Tunisia', 'Tunisian'),
(176, 'Turkey', 'Turkish'),
(177, 'Turkmenistan', 'Turkmen'),
(178, 'Tuvalu', 'Tuvaluan'),
(179, 'Uganda', 'Ugandan'),
(180, 'Ukraine', 'Ukrainian'),
(181, 'United Arab Emirates', 'Emirian'),
(182, 'United Kingdom', 'British'),
(183, 'United States', 'American'),
(184, 'Uruguay', 'Uruguayan'),
(185, 'Uzbekistan', 'Uzbekistani'),
(186, 'Vanuatu', 'Ni-Vanuatu'),
(187, 'Vatican City (Holy See)', 'none'),
(188, 'Venezuela', 'Venezuelan'),
(189, 'Vietnam', 'Vietnamese'),
(190, 'Yemen', 'Yemeni'),
(191, 'Zambia', 'Zambian'),
(192, 'Zimbabwe', 'Zimbabwean');

-- --------------------------------------------------------

--
-- Table structure for table `faculties`
--

CREATE TABLE IF NOT EXISTS `faculties` (
  `faculty_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`faculty_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `faculties`
--

INSERT INTO `faculties` (`faculty_id`, `name`) VALUES
(1, 'Humanities & Education'),
(2, 'Law'),
(3, 'Medical Sciences'),
(4, 'Science & Technology'),
(5, 'Social Sciences'),
(6, 'None'),
(7, 'None');

-- --------------------------------------------------------

--
-- Table structure for table `halls`
--

CREATE TABLE IF NOT EXISTS `halls` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `halls`
--

INSERT INTO `halls` (`id`, `name`) VALUES
(1, 'A.B.C. Hall'),
(2, 'Aston Preston Hall'),
(3, 'Chancellor Hall'),
(4, 'Elsa Leo-Rhynie Hall'),
(5, 'Irvine Hall'),
(6, 'Marlene Hamilton Hall'),
(7, 'Mary Seacole Hall'),
(8, 'New Hall of Residence'),
(9, 'Rex Nettleford Hall'),
(10, 'Taylor Hall');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `room_id` int(100) NOT NULL AUTO_INCREMENT,
  `number` varchar(10) NOT NULL,
  `block` int(10) NOT NULL,
  `contents` varchar(10000) NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=83 ;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_id`, `number`, `block`, `contents`) VALUES
(1, 'A1', 2, ''),
(2, 'A2', 2, ''),
(3, 'A3', 2, ''),
(4, 'A4', 2, ''),
(5, 'A5', 2, ''),
(6, 'A6', 2, ''),
(7, 'A7', 2, ''),
(8, 'A8', 2, ''),
(9, 'A9', 2, ''),
(10, 'A10', 2, ''),
(11, 'A11', 2, ''),
(12, 'A12', 2, ''),
(13, 'A13', 2, ''),
(14, 'A14', 2, ''),
(15, 'A15', 2, ''),
(16, 'A16', 2, ''),
(17, 'A17', 2, ''),
(18, 'A18', 2, ''),
(19, 'A19', 2, ''),
(20, 'A20', 2, ''),
(21, 'A21', 2, ''),
(22, 'A22', 2, ''),
(23, 'A23', 2, ''),
(24, 'A24', 2, ''),
(25, 'A25', 2, ''),
(26, 'A26', 2, ''),
(27, 'A27', 2, ''),
(28, 'A28', 2, ''),
(29, 'A29', 2, ''),
(30, 'A30', 2, ''),
(31, 'A31', 2, ''),
(32, 'A32', 2, ''),
(33, 'A33', 2, ''),
(34, 'A34', 2, ''),
(35, 'A35', 2, ''),
(36, 'A36', 2, ''),
(37, 'A37', 2, ''),
(38, 'A38', 2, ''),
(39, 'A39', 2, ''),
(40, 'A40', 2, ''),
(41, 'B1', 3, ''),
(42, 'B2', 3, ''),
(43, 'B3', 3, ''),
(44, 'B4', 3, ''),
(45, 'B5', 3, ''),
(46, 'B6', 3, ''),
(47, 'B7', 3, ''),
(48, 'B8', 3, ''),
(49, 'B9', 3, ''),
(50, 'B10', 3, ''),
(51, 'B11', 3, ''),
(52, 'B12', 3, ''),
(53, 'B13', 3, ''),
(54, 'B14', 3, ''),
(55, 'B15', 3, ''),
(56, 'B16', 3, ''),
(57, 'B17', 3, ''),
(58, 'B18', 3, ''),
(59, 'B19', 3, ''),
(60, 'B20', 3, ''),
(61, 'B21', 3, ''),
(62, 'B22', 3, ''),
(63, 'B23', 3, ''),
(64, 'B24', 3, ''),
(65, 'B25', 3, ''),
(66, 'B26', 3, ''),
(67, 'B27', 3, ''),
(68, 'B28', 3, ''),
(69, 'B29', 3, ''),
(70, 'B30', 3, ''),
(71, 'B31', 3, ''),
(72, 'B32', 3, ''),
(73, 'B33', 3, ''),
(74, 'B34', 3, ''),
(75, 'B35', 3, ''),
(76, 'B36', 3, ''),
(77, 'B37', 3, ''),
(78, 'B38', 3, ''),
(79, 'B39', 3, ''),
(80, 'B40', 3, ''),
(81, 'X3B', 1, ''),
(82, 'X13A', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `secondary_schools`
--

CREATE TABLE IF NOT EXISTS `secondary_schools` (
  `id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_version`
--

CREATE TABLE IF NOT EXISTS `student_version` (
  `id_changed` varchar(100) NOT NULL,
  `version` int(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `student_version`
--

INSERT INTO `student_version` (`id_changed`, `version`) VALUES
('620063216', 1);

-- --------------------------------------------------------

--
-- Table structure for table `test_stdns`
--

CREATE TABLE IF NOT EXISTS `test_stdns` (
  `academic_status` tinyint(1) NOT NULL,
  `achievements` varchar(1000) NOT NULL,
  `behaviour_history` varchar(1000) NOT NULL,
  `block` int(10) NOT NULL,
  `cell_phone` varchar(100) NOT NULL,
  `family_history` varchar(10000) DEFAULT NULL,
  `community_group` varchar(10000) NOT NULL,
  `co_curricular` varchar(10000) NOT NULL,
  `day_joined` date NOT NULL,
  `dob` date NOT NULL DEFAULT '0000-00-00',
  `faculty` int(10) NOT NULL,
  `father_first_name` varchar(100) NOT NULL,
  `father_last_name` varchar(100) NOT NULL,
  `father_phone` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `hall_history` varchar(1000) NOT NULL,
  `home_address1` varchar(100) NOT NULL,
  `home_address2` varchar(1000) NOT NULL,
  `home_city` varchar(100) NOT NULL,
  `home_province` varchar(100) NOT NULL,
  `id_number` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `middle_name` varchar(100) NOT NULL,
  `mother_first_name` varchar(100) NOT NULL,
  `mother_last_name` varchar(100) NOT NULL,
  `mother_phone` varchar(100) NOT NULL,
  `nationalityCountry` int(10) NOT NULL,
  `participation_level` tinyint(100) NOT NULL,
  `picture` tinyint(1) NOT NULL,
  `previous_secondary_school` varchar(100) NOT NULL,
  `reason_residing` text NOT NULL,
  `resident_country` int(10) NOT NULL,
  `room` int(10) NOT NULL,
  `tertiary_level` tinyint(1) NOT NULL,
  `will_participate` tinyint(1) NOT NULL,
  `email` varchar(1000) NOT NULL,
  `attached_documents` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_stdns`
--

INSERT INTO `test_stdns` (`academic_status`, `achievements`, `behaviour_history`, `block`, `cell_phone`, `family_history`, `community_group`, `co_curricular`, `day_joined`, `dob`, `faculty`, `father_first_name`, `father_last_name`, `father_phone`, `first_name`, `hall_history`, `home_address1`, `home_address2`, `home_city`, `home_province`, `id_number`, `last_name`, `middle_name`, `mother_first_name`, `mother_last_name`, `mother_phone`, `nationalityCountry`, `participation_level`, `picture`, `previous_secondary_school`, `reason_residing`, `resident_country`, `room`, `tertiary_level`, `will_participate`, `email`, `attached_documents`) VALUES
(1, '', '', 1, '', '', '', '', '2015-08-12', '1996-09-15', 4, '', '', '', 'Yerodin', '', '', '0', '', '', '620063216', 'Richards', 'Fitzgerald', '', '', '', 84, 0, 0, 'Munro College', '', 84, 82, 0, 1, 'yero_din@hotmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(10000) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `permission` int(10) NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `first_name`, `last_name`, `permission`, `status`) VALUES
(1, 'asap', '188828425086e7b07b3033a28a694966d1181a2d', 'Yerodin', 'Richards', 100, '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
