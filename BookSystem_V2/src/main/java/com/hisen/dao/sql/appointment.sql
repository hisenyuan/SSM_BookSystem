SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `book_id` int(20) NOT NULL,
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_number` int(11) NOT NULL,
  `appointment_time` datetime NOT NULL,
  `expect_return_time` datetime NOT NULL,
  `real_return_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
