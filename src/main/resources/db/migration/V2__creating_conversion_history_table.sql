CREATE TABLE conversion_history (
	id int8 NOT NULL,
	"date" timestamp NULL,
	source_currency varchar(255) NULL,
	source_currency_amount numeric(19, 2) NULL,
	target_currency varchar(255) NULL,
	target_currency_amount numeric(19, 2) NULL,
	CONSTRAINT conversion_history_pkey PRIMARY KEY (id)
);