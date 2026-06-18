-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2022 at 02:51 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_registration_list`
--

-- --------------------------------------------------------

--
-- Table structure for table `std_credentials`
--

CREATE TABLE `std_credentials` (
  `StudentNumber` varchar(20) NOT NULL,
  `School Diploma` varchar(10) NOT NULL,
  `Form 137` varchar(10) NOT NULL,
  `Form 138` varchar(10) NOT NULL,
  `School PSA` varchar(10) NOT NULL,
  `School Good Moral` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `std_credentials`
--

INSERT INTO `std_credentials` (`StudentNumber`, `School Diploma`, `Form 137`, `Form 138`, `School PSA`, `School Good Moral`) VALUES
('2013 - 849715', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2013 - 928743', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2014 - 381064', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2014 - 462581', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2015 - 321574', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2015 - 945138', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2016 - 345678', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2016 - 596728', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2016 - 817329', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2017 - 239486', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2018 - 248967', 'INCOMPLETE', 'COMPLETE', 'INCOMPLETE', 'COMPLETE', 'COMPLETE'),
('2018 - 478536', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2018 - 573481', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2018 - 947864', 'COMPLETE', 'INCOMPLETE', 'COMPLETE', 'INCOMPLETE', 'COMPLETE'),
('2019 - 046753', 'COMPLETE', 'COMPLETE', 'INCOMPLETE', 'INCOMPLETE', 'INCOMPLETE'),
('2019 - 141678', 'INCOMPLETE', 'INCOMPLETE', 'COMPLETE', 'COMPLETE', 'INCOMPLETE'),
('2019 - 406335', 'COMPLETE', 'COMPLETE', 'INCOMPLETE', 'COMPLETE', 'INCOMPLETE'),
('2019 - 677072', 'INCOMPLETE', 'INCOMPLETE', 'INCOMPLETE', 'INCOMPLETE', 'INCOMPLETE'),
('2019 - 735461', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2019 - 958162', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2020 - 674895', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE'),
('2020 - 762845', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE', 'COMPLETE');

-- --------------------------------------------------------

--
-- Table structure for table `std_data`
--

CREATE TABLE `std_data` (
  `StudentNumber` varchar(20) NOT NULL,
  `First Name` text NOT NULL,
  `Last Name` text NOT NULL,
  `Status` text NOT NULL,
  `Course Program` text NOT NULL,
  `Year Level` varchar(15) NOT NULL,
  `School Year` varchar(15) NOT NULL,
  `Semester` varchar(15) NOT NULL,
  `Total Units` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `std_data`
--

INSERT INTO `std_data` (`StudentNumber`, `First Name`, `Last Name`, `Status`, `Course Program`, `Year Level`, `School Year`, `Semester`, `Total Units`) VALUES
('2013 - 849715', 'Christopher', 'Ong', 'GRADUATE', 'Bachelor of Science in Information Technology', '', '', '', 178),
('2013 - 928743', 'Wonwoo', 'Jeon', 'GRADUATE', 'Bachelor of Science in Information Technology', '', '', '', 178),
('2014 - 381064', 'Maureen Joyce', 'Magtira', 'GRADUATE', 'Bachelor of Science in Information Technology', '', '', '', 178),
('2014 - 462581', 'Lia Jane', 'Baguio', 'GRADUATE', 'Bachelor of Science in Information Technology', '', '', '', 178),
('2015 - 321574', 'Joe', 'Goldberg', 'GRADUATE', 'Bachelor of Science in Information Technology', '', '', '', 178),
('2015 - 945138', 'Phil', 'Dunphy', 'GRADUATE', 'Bachelor of Science in Information Technology', '', '', '', 181),
('2016 - 345678', 'Luke', 'Cagampang', 'GRADUATE', 'Bachelor of Science in Computer Engineering', '', '', '', 181),
('2016 - 596728', 'Jake Sim', 'Chua', 'GRADUATE', 'Bachelor of Science in Architecture', '', '', '', 226),
('2016 - 817329', 'Heesung Lee', 'Batumbakal', 'GRADUATE', 'Bachelor of Science in Computer Engineering', '', '', '', 181),
('2017 - 239486', 'Jennie Lay', 'Nacua', 'GRADUATE', 'Bachelor of Science in Architecture', '', '', '', 226),
('2017 - 657281', 'Beatrice', 'Besabella', 'GRADUATE', 'Bachelor of Science in Architecture', '', '', '', 226),
('2018 - 248967', 'Hannah', 'Ponce', 'ENROLLED', 'Bachelor of Science in Architecture', '2nd Year', '2022-2023', '1st Sem', 10),
('2018 - 478536', 'Gloria', 'Berces', 'GRADUATE', 'Bachelor of Science in Civil Engineering', '', '', '', 187),
('2018 - 573481', 'Joshua Hong', 'Diamante', 'GRADUATE', 'Bachelor of Science in Civil Engineering', '', '', '', 187),
('2018 - 947864', 'Dawson', 'Bautista', 'ENROLLED', 'Bachelor of Science in Astronomy', '2nd Year', '2022-2023', '1st Sem', 10),
('2019 - 046753', 'Joshua', 'Cunanan', 'ENROLLED', 'Bachelor of Science in Accountancy', '2nd Year', '2022-2023', '1st Sem', 10),
('2019 - 100567', 'Darlene Mae', 'Leonardo', 'INACTIVE', 'Bachelor of Science in Architecture', '', '', '', 0),
('2019 - 102106', 'Andrei Miguel', 'Samonte', 'INACTIVE', 'Bachelor of Science in Business Administration ', '', '', '', 0),
('2019 - 141678', 'Tracy Ley', 'Fajardo', 'INACTIVE', 'Bachelor of Science in Architecture', '', '', '', 0),
('2019 - 146834', 'Corrine', 'Lee', 'ENROLLED', 'Bachelor of Science in Accountancy', '2nd Year', '2022-2023', '1st Sem', 10),
('2019 - 194258', 'Olivia Reign', 'Baybin', 'INACTIVE', 'Bachelor of Science in Information Technology', '', '', '', 0),
('2019 - 406335', 'Danny', 'Acosta', 'ENROLLED', 'Bachelor of Science in Civil Engineering', '2nd Year', '2022-2023', '1st Sem', 10),
('2019 - 677072', 'Sammy', 'Padilla', 'ENROLLED', 'Bachelor of Science in Computer Engineering', '2nd Year', '2022-2023', '1st Sem', 10),
('2019 - 735461', 'Mary Grace', 'Camacho', 'GRADUATE', 'Bachelor of Science in Accountancy', '', '', '', 197),
('2019 - 958162', 'Ian Jay', 'Palad', 'GRADUATE', 'Bachelor of Science in Accountancy', '', '', '', 197),
('2020 - 164789', 'Mary Ann', 'Bautista', 'ENROLLED', 'Bachelor of Science in Information Technology', '2nd Year', '2022-2023', '1st Sem', 10),
('2020 - 448116', 'John Rey', 'Santos', 'ENROLLED', 'Bachelor of Science in Astronomy', '2nd Year', '2022-2023', '1st Sem', 10),
('2020 - 674895', 'Christian Louie', 'Tejoso', 'GRADUATE', 'Bachelor of Science in Business Administration', '', '', '', 195),
('2020 - 762845', 'Arnel Dave', 'Quiban', 'GRADUATE', 'Bachelor of Science in Business Administration', '', '', '', 195),
('2020 - 877761', 'John Michael', 'Alejandro', 'ENROLLED', 'Bachelor of Science in Industrial Technology', '2nd Year', '2022-2023', '1st Sem', 10),
('2020 - 890277', 'Althea Marie', 'Dela Cruz', 'ENROLLED', 'Bachelor of Science in Architecture', '2nd Year', '2022-2023', '1st Sem', 10),
('2020-  154527', 'Samantha Ysabel', 'Castro', 'ENROLLED', 'Bachelor of Science in Mechanical Engineering', '2nd Year', '2022-2023', '1st Sem', 10),
('2021 - 124567', 'Christian James', 'Abad', 'INACTIVE', 'Bachelor of Science in Architecture', '', '', '', 0),
('2021 - 124894', 'Adrian', 'Sagun', 'ENROLLED', 'Bachelor of Science in Information Technology', '2nd Year', '2022-2023', '1st Sem', 10),
('2021 - 201478', 'Jessie Rose', 'Barette', 'INACTIVE', 'Bachelor of Science in Mechanical Engineering', '', '', '', 0),
('2021 - 211953', 'John Lloyd', 'Masanque', 'ENROLLED', 'Bachelor of Science in Business Administration', '2nd Year', '2022-2023', '1st Sem', 10),
('2021 - 222432', 'Gabriel Sean', 'Padua', 'INACTIVE', 'Bachelor of Science in Computer Engineering', '', '', '', 0),
('2021 - 262267', 'John Carlo', 'Yumul', 'ENROLLED', 'Bachelor of Science in Architecture', '2nd Year', '2022-2023', '1st Sem', 10),
('2021 - 265793', 'Michelle', 'Abdon', 'INACTIVE', 'Bachelor of Science in Information Technology', '', '', '', 0),
('2021 - 278954', 'Patricia Gel', 'Zoleta', 'INACTIVE', 'Bachelor of Science in Accountancy', '', '', '', 0),
('2021 - 290765', 'Matthew', 'Benedicto', 'INACTIVE', 'Bachelor of Science in Computer Engineering', '', '', '', 0),
('2021 - 298756', 'Eunice Claire', 'Gaston', 'INACTIVE', 'Bachelor of Science in Mechanical Engineering', '', '', '', 0),
('2022 - 017547', 'Christian', 'Laxamana', 'ENROLLED', 'Bachelor of Science in Electrical Engineering', '2nd Year', '2022-2023', '1st Sem', 10),
('2022 - 102457', 'Angelo', 'Patingo', 'ENROLLED', 'Bachelor of Science in Computer Engineering', '2nd Year', '2022-2023', '1st Sem', 10),
('2022 - 164896', 'Rose Ann', 'Reyes', 'ENROLLED', 'Bachelor of Science in Entreprenuership', '2nd Year', '2022-2023', '1st Sem', 10),
('2022 - 204190', 'Mary Joy', 'Aguilar', 'ENROLLED', 'Bachelor of Science in Information Technology', '2nd Year', '2022-2023', '1st Sem', 10),
('2022 - 232122', 'Sharjah', 'Bautista', 'INACTIVE', 'Bachelor of Science in Architecture', '', '', '', 0),
('2022 - 300156', 'Shaun Serim', 'Saga', 'INACTIVE', 'Bachelor of Science in Civil Engineering', '', '', '', 0),
('2022 - 300985', 'Ashley', 'Aboc', 'INACTIVE', 'Bachelor of Science in Computer Engineering', '', '', '', 0),
('2022 - 302345', 'Peter', 'Espinosa', 'INACTIVE', 'Bachelor of Science in Civil Engineering', '', '', '', 0),
('2022 - 306521', 'Cyrus John', 'Gonzaga', 'INACTIVE', 'Bachelor of Science in Accountancy', '', '', '', 0),
('2022 - 365364', 'Christine', 'Revilla', 'INACTIVE', 'Bachelor of Science in Entreprenuership', '', '', '', 0),
('2022 - 378921', 'Ranz William', 'Chua', 'INACTIVE', 'Bachelor of Science in Business Administration ', '', '', '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `std_credentials`
--
ALTER TABLE `std_credentials`
  ADD UNIQUE KEY `StudentNumber_2` (`StudentNumber`),
  ADD KEY `StudentNumber` (`StudentNumber`);

--
-- Indexes for table `std_data`
--
ALTER TABLE `std_data`
  ADD PRIMARY KEY (`StudentNumber`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `std_credentials`
--
ALTER TABLE `std_credentials`
  ADD CONSTRAINT `std_credentials_ibfk_1` FOREIGN KEY (`StudentNumber`) REFERENCES `std_data` (`StudentNumber`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
