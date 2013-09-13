--tables

--generic value
CREATE DOMAIN GV as varchar(100);

CREATE TABLE User (
    username        GV NOT null unique,
    password        GV NOT null,
    email_address   GV,
    nickname        GV,
    first_name      GV,
    last_name       GV,
    year_of_birth   INTEGER,
    postal_address  GV,
    cc_number       INTEGER,
    primary key (username)
);

CREATE TABLE Auction (
    id                  serial,
    title               GV,
    author              GV REFERENCES User(username) NOT null,
    category            GV,
    picture             GV,
    description         GV,
    postage_details     GV,
    reserve_price       FLOAT,
    start_price         FLOAT,
    bidding_increments  FLOAT,
    end_of_auction      DATE,    --maybe datetime?
    primary key (id)
);

CREATE TABLE Biddings (
    id          serial,
    author      GV REFERENCES User(username) NOT null,
    auction     INTEGER REFERENCES Auction(id) NOT Null,
    price       FLOAT,
    bid_date    DATE,
    primary key (id)
);
