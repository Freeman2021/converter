CREATE TABLE currencies (
	id int8 NOT NULL,
	char_code varchar(255) NULL,
	name varchar(255) NULL,
	nominal int4 NULL,
	num_code varchar(255) NULL,
	value numeric(19, 2) NULL,
	CONSTRAINT currencies_pkey PRIMARY KEY (id)
);