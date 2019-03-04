CREATE DATABASE IF NOT EXISTS flagchecker DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_bin;

CREATE TABLE `continent` (
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL UNIQUE,
  id bigint auto_increment primary key
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE INDEX IDX_continent ON flagchecker.continent (name);

CREATE TABLE `country` (
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL UNIQUE,
  `continent_id` bigint COLLATE utf8mb4_bin NOT NULL,
  `flag_img` LONGBLOB,
  `flag_url` varchar(100) COLLATE utf8mb4_bin,
  id bigint auto_increment primary key,
  foreign key (continent_id) references flagchecker.continent (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE INDEX IDX_country ON flagchecker.country (name);
