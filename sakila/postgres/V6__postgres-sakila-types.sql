-- This script contains a few jOOQ demo specific tables, that aren't otherwise part of the Sakila database.

/*

-- Cannot activate this yet due to https://github.com/jOOQ/jOOQ/issues/16039
SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;


CREATE TYPE monetary_amount AS (amount numeric, currency CHAR(3));


CREATE SEQUENCE transaction_transaction_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.transaction_transaction_id_seq OWNER TO postgres;

CREATE TABLE transaction (
    transaction_id bigint DEFAULT nextval('transaction_transaction_id_seq'::regclass) NOT NULL,
	value_date timestamp without time zone NOT NULL,
	amount monetary_amount NOT NULL,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
	
	CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id)
);

INSERT INTO transaction (value_date, amount)
VALUES
  (TIMESTAMP '2000-01-01', ROW (10.0, 'USD')::monetary_amount),
  (TIMESTAMP '2000-01-02', ROW (15.0, 'EUR')::monetary_amount);
*/