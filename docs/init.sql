CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
