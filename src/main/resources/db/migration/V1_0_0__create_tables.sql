# 用户表
CREATE TABLE `t_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `MOBILE` text NOT NULL,
  `USER_NAME` text NOT NULL,
  `SALT` text NOT NULL,
  `PASSWORD` text NOT NULL,
  `STATUS` text NOT NULL,
  `CLIENT_ID` int(11) NOT NULL,
  `brief` text null,
  `header_src` text null,
  `email` text null,
  `access` int(11) not null default 0,

  `VERSION` int(11) NOT NULL DEFAULT '0',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,

  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# token表
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
  `abstract` text not null ,
  `content` text not null ,
  `raw_file_link` varchar(128) null ,
  `access` int not null default 0,
  `source` varchar(64) not null ,
  `author` text not null ,

  `version` int(11) not null default 0,
  `create_time` timestamp not null default current_timestamp,
  `update_time` timestamp null default null
) engine = InnoDB charset=utf8;

# 用户-文章 关系表
CREATE TABLE IF NOT EXISTS `T_USER_ARTICLE` (
  `id` int(11) primary key auto_increment,
  `user_id` int(11) not null ,
  `article_id` int(11) not null ,

  `version` int(11) not null default 0,
  `create_time` timestamp not null default current_timestamp,
  `update_time` timestamp null default null
) engine = InnoDB charset=utf8;

# 文章分类表
CREATE TABLE IF NOT EXISTS `T_ARTICLE_CLASSIFY` (
  `id` int(11) primary key auto_increment,
  `name` varchar(64) not null ,
  `is_deleted` boolean not null default false ,
  `type` tinyint not null default 0, # 0: 类型 1: 来源

  `version` int(11) not null default 0,
  `create_time` timestamp not null default current_timestamp,
  `update_time` timestamp null default null
) engine = InnoDB charset=utf8;

# 标签表
CREATE TABLE IF NOT EXISTS `T_TAGS` (
  `id` int(11) primary key auto_increment,
  `name` varchar(64) not null ,
  `deleted` boolean not null default false ,
  `type` tinyint null default 0,

  `version` int(11) not null default 0,
  `create_time` timestamp not null default current_timestamp,
  `update_time` timestamp null default null
) engine = InnoDB charset=utf8;

select * from T_ARTICLE LIMIT 100 OFFSET 20;

SELECT id, title, tags, type, abstract as abstractContent, content, raw_file_link as rawFileLink, access, source, author, create_time as createTime
FROM T_ARTICLE
WHERE (1=1) limit 10 offset 1


