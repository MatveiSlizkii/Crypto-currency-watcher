SET client_encoding = 'UTF8';

CREATE SCHEMA app;

ALTER SCHEMA app OWNER TO postgres;

CREATE TABLE app.crypto (
    id bigint NOT NULL,
    symbol character varying NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL
);

ALTER TABLE app.crypto OWNER TO postgres;

CREATE TABLE app.crypto_price (
    id bigint NOT NULL,
    price_usd double precision,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL
);

ALTER TABLE app.crypto_price OWNER TO postgres;

CREATE TABLE app.user_notification (
    id uuid NOT NULL,
    username character varying NOT NULL,
    symbol character varying NOT NULL,
    start_price double precision NOT NULL,
    enable boolean DEFAULT true NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL
);

ALTER TABLE app.user_notification OWNER TO postgres;

INSERT INTO app.crypto VALUES (48543, 'SOL', '2022-07-15 04:09:09.089', '2022-07-15 04:09:09.089');
INSERT INTO app.crypto VALUES (90, 'BTC', '2022-07-15 04:09:24.195', '2022-07-15 04:09:24.195');
INSERT INTO app.crypto VALUES (80, 'ETH', '2022-07-15 04:09:32.954', '2022-07-15 04:09:32.954');

INSERT INTO app.crypto_price VALUES (48543, 37.14, '2022-07-15 04:09:09.089', '2022-07-15 04:49:42.097');
INSERT INTO app.crypto_price VALUES (90, 20459.61, '2022-07-15 04:09:24.195', '2022-07-15 04:49:42.097');
INSERT INTO app.crypto_price VALUES (80, 1188.7, '2022-07-15 04:09:32.954', '2022-07-15 04:49:42.097');

ALTER TABLE ONLY app.crypto
    ADD CONSTRAINT crypto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.crypto_price
    ADD CONSTRAINT crypto_value_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.user_notification
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.crypto_price
    ADD CONSTRAINT id_fk FOREIGN KEY (id) REFERENCES app.crypto(id) NOT VALID;

