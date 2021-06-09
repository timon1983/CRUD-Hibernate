CREATE TABLE IF NOT EXISTS users(
                    user_id serial not null,
                    primary key(user_id),
                    firstName varchar(45) not null,
                    lastName varchar(45) not null,
                    region_id int not null);
CREATE TABLE IF NOT EXISTS regions(
                    region_id serial not null,
                    primary key(region_id),
                    regionName varchar(45) not null);
CREATE TABLE IF NOT EXISTS posts(
                    post_id serial not null,
                    primary key(post_id),
                    user_id int references users(user_id),
                    content varchar(45) not null,
                    created int not null,
                    updated int not null);