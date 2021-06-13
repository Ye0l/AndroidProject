CREATE TABLE `comments_board_1` (
  `board_id` int(10) NOT NULL,
  `post_id` int(10) NOT NULL,
  `accounts_id` varchar(20) NOT NULL,
  `contents` varchar(200) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `post_board_1` (
  `board_id` int(10) NOT NULL,
  `accounts_id` varchar(20) NOT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` varchar(50) NOT NULL,
  `contents` varchar(1000) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
