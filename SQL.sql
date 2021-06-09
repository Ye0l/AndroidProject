-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 21-06-08 11:20
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- 데이터베이스: `android_project`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `since` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(30) NOT NULL DEFAULT ' ',
  `age` int(3) NOT NULL DEFAULT '0',
  `phone` varchar(20) NOT NULL DEFAULT ' ',
  `introduce` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `accounts`
--

INSERT INTO `accounts` (`id`, `password`, `nickname`, `since`, `name`, `age`, `phone`, `introduce`) VALUES
('admin', '12341234', 'ADMIN', '2021-05-31 23:00:53', '', 0, '', 'introduce test !!'),
('testid', '12341234', 'testname', '2021-05-31 23:06:22', 'testname', 0, '', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `alert`
--

CREATE TABLE IF NOT EXISTS `alert` (
  `id` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `contents` int(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `alert`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `alert_list`
--

CREATE TABLE IF NOT EXISTS `alert_list` (
  `target_id` varchar(20) NOT NULL,
  `alert_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `alert_list`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `board`
--

CREATE TABLE IF NOT EXISTS `board` (
  `group_id` int(10) NOT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 테이블의 덤프 데이터 `board`
--

INSERT INTO `board` (`group_id`, `id`, `name`) VALUES
(1, 1, 'firstBoard');

-- --------------------------------------------------------

--
-- 테이블 구조 `calender`
--

CREATE TABLE IF NOT EXISTS `calender` (
  `id` int(10) NOT NULL,
  `accounts_id` varchar(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contents` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `calender`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `comments_board_1`
--

CREATE TABLE IF NOT EXISTS `comments_board_1` (
  `board_id` int(10) NOT NULL,
  `post_id` int(10) NOT NULL,
  `accounts_id` int(10) NOT NULL,
  `contents` varchar(200) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `comments_board_1`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `introduce` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 테이블의 덤프 데이터 `group`
--

INSERT INTO `group` (`id`, `name`, `introduce`) VALUES
(1, 'groupTest', 'test group introduce!');

-- --------------------------------------------------------

--
-- 테이블 구조 `group_member`
--

CREATE TABLE IF NOT EXISTS `group_member` (
  `accounts_id` varchar(20) NOT NULL,
  `group_id` int(10) NOT NULL,
  `auth` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `group_member`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `post_board_1`
--

CREATE TABLE IF NOT EXISTS `post_board_1` (
  `board_id` int(10) NOT NULL,
  `id` int(10) NOT NULL DEFAULT '0',
  `title` varchar(50) NOT NULL,
  `contents` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `post_board_1`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `accounts_id` varchar(20) NOT NULL,
  `id` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `period` int(3) NOT NULL,
  `desc` varchar(200) NOT NULL,
  `groups_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `vote`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `vote_item`
--

CREATE TABLE IF NOT EXISTS `vote_item` (
  `vote_id` int(10) NOT NULL,
  `item` varchar(20) NOT NULL,
  `votes` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `vote_item`
--
