CREATE TABLE cljshrink_link (
       id SERIAL PRIMARY KEY,
       sourceurl varchar(255) NOT NULL,
       link varchar(10) NOT NULL,
       created timestamp NOT NULL
       );
