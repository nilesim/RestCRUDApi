CREATE TABLE TED_TALK (
    id  bigserial not null,
    title varchar(255),
    author varchar(255),
    date varchar(255),
    views bigint,
    likes bigint,
    link varchar(255),
    primary key (id)
);

COPY TED_TALK (title,author,date,views,likes,link) FROM '/docker-entrypoint-initdb.d/data.csv' DELIMITER ',' CSV HEADER;