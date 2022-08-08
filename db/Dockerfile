FROM postgres:alpine
COPY init-db.sql /docker-entrypoint-initdb.d/
ADD init-db.sql /docker-entrypoint-initdb.d
COPY data.csv /docker-entrypoint-initdb.d/
ADD data.csv /docker-entrypoint-initdb.d
RUN chmod a+r /docker-entrypoint-initdb.d/*
RUN chmod a+r /docker-entrypoint-initdb.d/data.csv
EXPOSE 5432