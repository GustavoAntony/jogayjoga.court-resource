CREATE TABLE court
(
    id_court character varying(36) NOT NULL,
    tx_name character varying(256) NOT NULL,
    tx_address character varying(256) NOT NULL,
    tx_capacity character INTEGER NOT NULL,
    id_sport character INTEGER NOT NULL,
    CONSTRAINT pk_court PRIMARY KEY (id_court)
);