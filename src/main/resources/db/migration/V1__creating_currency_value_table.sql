CREATE TABLE currency_value (
	id bigserial NOT NULL,
	"date" timestamp NULL,
	value numeric(19, 2) NULL,
	CONSTRAINT currency_value_pkey PRIMARY KEY (id)
);