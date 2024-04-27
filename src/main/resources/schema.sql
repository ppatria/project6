DROP TABLE IF EXISTS book;
CREATE TABLE job (
                      id                BIGSERIAL PRIMARY KEY NOT NULL,
                      jobid             varchar(255) NOT NULL,
                      title             varchar(255) UNIQUE NOT NULL,
                      description       varchar(255) NOT NULL,
                      companyname       varchar(255) NOT NULL,
                      skill1            varchar(255) NOT NULL,
                      skill2            varchar(255) NOT NULL,
                      version           integer NOT NULL
);
