--tables

--generic value
CREATE DOMAIN GV as varchar(500);

CREATE TABLE Username (
    username        GV NOT null unique,
    password        GV NOT null,
    email_address   GV,
    nickname        GV,
    first_name      GV,
    last_name       GV,
    year_of_birth   INTEGER,
    postal_address  GV,
    cc_number       INTEGER,
    banned          BOOLEAN,
    admin           BOOLEAN,
    primary key (username)
);

CREATE TABLE Auction (
    id                  serial,
    title               GV,
    author              GV REFERENCES Username(username) NOT null,
    category            GV,
    picture             GV,
    description         GV,
    postage_details     GV,
    reserve_price       FLOAT,
    start_price         FLOAT,
    bidding_increments  FLOAT,
    end_of_auction      TIMESTAMP,
    halt                BOOLEAN,
    finished            BOOLEAN,
    primary key (id)
);

CREATE TABLE Bidding (
    id          serial,
    author      GV REFERENCES Username(username) NOT null,
    auction     INTEGER REFERENCES Auction(id) NOT null,
    price       FLOAT,
    bid_date    TIMESTAMP,
    primary key (id)
);

CREATE TABLE WinningAuction (
    id          serial,
    auction     INTEGER REFERENCES Auction(id) NOT null,
    bid         INTEGER REFERENCES Bidding(id) NOT null,
    primary key (id)
);

CREATE TABLE Alert (
    id          serial,
    author      GV REFERENCES Username(username) NOT null,
    auction     INTEGER REFERENCES Auction(id),
    message     GV,
    primary key (id)
);
