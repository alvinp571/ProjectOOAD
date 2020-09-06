-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 05, 2020 at 09:26 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cobasweebookdao`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` char(36) NOT NULL,
  `genre_id` char(36) NOT NULL,
  `title` varchar(255) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `genre_id`, `title`, `isbn`, `quantity`) VALUES
('54111b9e-020a-4848-b829-3c3dce37836a', '049acce0-e2ac-427d-93d9-2b0c8a2752b7', 'Example Comic Book', '555931333135', 1);

-- --------------------------------------------------------

--
-- Table structure for table `borrows`
--

CREATE TABLE `borrows` (
  `id` char(36) NOT NULL,
  `member_id` char(36) NOT NULL,
  `status` varchar(50) NOT NULL,
  `borrow_timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `borrow_items`
--

CREATE TABLE `borrow_items` (
  `borrow_id` char(36) NOT NULL,
  `book_id` char(36) NOT NULL,
  `return_timestamp` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `user_id` char(36) NOT NULL,
  `salary` bigint(20) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`user_id`, `salary`, `status`) VALUES
('b209320f-943e-46ff-8da8-de5cbfdcd1af', 812887, 'Active'),
('455c6b51-53d8-4df1-a9d1-d16602bf7cb3', 31000, 'Active'),
('48c30104-8d2d-4fef-8317-c23dacc6db5e', 20000, 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `id` char(36) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`id`, `type`) VALUES
('2ee18ab5-2d6f-48ff-ab10-83d3ccb18f24', 'Action and Adventure'),
('683b87f5-e918-4786-a1f7-9d2adffc46e6', 'Biographies and Autobiographies'),
('049acce0-e2ac-427d-93d9-2b0c8a2752b7', 'Comic'),
('a12c77a2-82d8-428f-8224-c9bca655f802', 'Cookbooks'),
('812ad8e7-8b2a-487f-a9de-9167feda37d9', 'Horror');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` char(36) NOT NULL,
  `address` text NOT NULL,
  `member_since` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`user_id`, `address`, `member_since`) VALUES
('ccf8f721-a92a-4aad-a1d5-4824565fc11c', 'Dummy Address', '2020-07-27 06:15:06'),
('06e8d45e-bc83-4bab-990d-edabe7b75bf1', 'hallooo', '2020-09-01 08:49:10'),
('bbebbc8f-069b-41e1-9467-a919fa38954c', 'managerH', '2020-09-01 11:30:41'),
('e774f917-62a8-45d2-ac3c-c1be0b2d81e1', 'ily', '2020-09-05 06:47:55'),
('c3339494-15c8-485b-94ef-1e07ad42ee50', 'oke', '2020-09-05 06:51:15');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` char(36) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
('0acac7fa-59b7-4555-8201-34d1a03f9fb8', 'Administrator'),
('d9390fe5-de7a-428e-b0e6-a1c08e39b913', 'Human Capital'),
('64da7812-7198-4543-8449-863bbf5bd374', 'Manager'),
('e90dd78e-5ace-4fb4-afef-58d950997757', 'Membership'),
('5a7634a6-c065-4482-9359-f24bbb10627c', 'Purchasing');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` char(36) NOT NULL,
  `role_id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `role_id`, `name`, `username`, `password`, `gender`) VALUES
('b209320f-943e-46ff-8da8-de5cbfdcd1af', '64da7812-7198-4543-8449-863bbf5bd374', 'Dummy Manager', 'manager', '1a8565a9dc72048ba03b4156be3e569f22771f23', 'Male'),
('ccf8f721-a92a-4aad-a1d5-4824565fc11c', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'Dummy Member', 'member', '6467baa3b187373e3931422e2a8ef22f3e447d77', 'Male'),
('4151c0ff-6a68-41a8-bda1-109a74647726', 'c6b2f775-7bfe-4fb4-89f6-e0239eca212f', 'hai', 'alvin', '59d97cb9530a12325b70e648432cc8de75741c2c', 'male'),
('bbebbc8f-069b-41e1-9467-a919fa38954c', '64da7812-7198-4543-8449-863bbf5bd374', 'mr.manager', 'manager1', 'a5c297c15e40ac3881db51277613aea3731b673a', 'male'),
('678ed4e5-3e6c-474f-9a8b-77d2563a51b1', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'emp1', 'emp1', 'a2666dd2ad1ee965f72b957a96d97e3ff302b910', 'male'),
('48c30104-8d2d-4fef-8317-c23dacc6db5e', '5a7634a6-c065-4482-9359-f24bbb10627c', 'Karyawan', 'karyawan1', 'dc81dffaab0d6632eef2e0032d8beb79b72f383e', 'male'),
('e774f917-62a8-45d2-ac3c-c1be0b2d81e1', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'ily', 'ily', '5843debd6525d895151b80f8f0d7bf1415d7fa40', 'female'),
('c3339494-15c8-485b-94ef-1e07ad42ee50', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'oke', 'oke', '6d73d34e71cd212d35f709b9dff6a52b2aa582ec', 'male');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
