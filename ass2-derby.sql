--for derby
--CONNECT 'jdbc:derby://localhost:1527/ass2;create=true;user=root;password=root';
--CONNECT 'jdbc:derby://localhost:1527/ass2;user=root;password=root';
--java -jar derbyrun.jar ij
--

CREATE SCHEMA root;

CREATE TABLE Username (
    username        varchar(500) NOT null,
    password        varchar(500) NOT null,
    email_address   varchar(500),
    nickname        varchar(500),
    first_name      varchar(500),
    last_name       varchar(500),
    year_of_birth   INTEGER,
    postal_address  varchar(500),
    cc_number       INTEGER,
    banned          BOOLEAN,
    admin           BOOLEAN,
    verified        BOOLEAN,
    hash            INTEGER,
    primary key (username)
);


CREATE TABLE Auction (
    id                  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title               varchar(500),
    author              varchar(500) REFERENCES Username(username) NOT null,
    category            varchar(500),
    picture             varchar(500),
    description         varchar(500),
    postage_details     varchar(500),
    reserve_price       FLOAT,
    start_price         FLOAT,
    bidding_increments  FLOAT,
    end_of_auction      TIMESTAMP,
    halt                BOOLEAN,
    finished            BOOLEAN,
    primary key (id)
);

CREATE TABLE Bidding (
    id          INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    author      varchar(500) REFERENCES Username(username) NOT null,
    auction     INTEGER REFERENCES Auction(id) NOT null,
    price       FLOAT,
    bid_date    TIMESTAMP,
    primary key (id)
);

CREATE TABLE WinningAuction (
    id          INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    auction     INTEGER REFERENCES Auction(id) NOT null,
    bid         INTEGER REFERENCES Bidding(id) NOT null,
    primary key (id)
);

CREATE TABLE Alert (
    id          INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    author      varchar(500) REFERENCES Username(username) NOT null,
    auction     INTEGER REFERENCES Auction(id),
    message     varchar(500),
    primary key (id)
);
