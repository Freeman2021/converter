CREATE TABLE currency_value (
	id bigserial NOT NULL,
	"date" date NULL,
	value numeric(19, 4) NULL,
	CONSTRAINT currency_value_pkey PRIMARY KEY (id)
);