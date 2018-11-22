CREATE TABLE `T_USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `MOBILE` text NOT NULL,
  `USER_NAME` text NOT NULL,
  `SALT` text NOT NULL,
  `PASSWORD` text NOT NULL,
  `STATUS` text NOT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  `CLIENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `T_TOKEN` (
  `id` int(11) primary key auto_increment,
  `token` varchar(64) NOT NULL ,
  `client_id` int(11) NOT NULL ,
  `expire_time` timestamp NOT NULL ,
  `user_id` int(11) not null ,

  `version` int(11) not null default 0,
  `create_time` timestamp not null default current_timestamp,
  `update_time` timestamp null default null
) engine = InnoDB charset=utf8;

# 文章信息表
CREATE TABLE IF NOT EXISTS `T_ARTICLE` (
  `id` int(11) primary key auto_increment,
  `title` varchar(64) not null ,
  `tags` varchar(128) not null ,
  `type` varchar(64) not null ,
  `abstract` varchar(128) not null ,
  `content` text not null ,
  `raw_file_link` varchar(128) null ,

  `version` int(11) not null default 0,
  `create_time` timestamp not null default current_timestamp,
  `update_time` timestamp null default null
) engine = InnoDB charset=utf8;




# CREATE TABLE `T_CUSTOMER` (
#   `id` int(11) primary key auto_increment,
#   `name` varchar(32) not null ,
#   `contacts` varchar(32) ,
#   `mobile` varchar(12),
#   `address` varchar(256),
#   `comment` varchar(1024),
#
#   `version` int(11) not null default 0,
#   `create_time` timestamp not null default current_timestamp,
#   `update_time` timestamp null default null
# ) engine = InnoDB charset=utf8;


