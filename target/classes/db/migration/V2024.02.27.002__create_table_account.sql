CREATE TABLE court
(
    id_court character varying(36) NOT NULL,
    tx_name character varying(256) NOT NULL,
    tx_address character varying(256) NOT NULL,
    tx_capacity INTEGER NOT NULL,
    id_sport character varying(256) NOT NULL,
    tx_reserved INTEGER,
    CONSTRAINT pk_court PRIMARY KEY (id_court)
);