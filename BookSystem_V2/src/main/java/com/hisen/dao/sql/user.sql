SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 NOT NULL,
  `user_address` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_number` varchar(11) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`user_id`,`user_number`),
  UNIQUE KEY `user_number` (`user_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
