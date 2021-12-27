CREATE TABLE conversion_history (
	id bigserial NOT NULL,
	"date" timestamp NULL,
	source_currency_amount numeric(19, 2) NULL,
	target_currency_amount numeric(19, 2) NULL,
	source_currency_id int8 NULL,
	target_currency_id int8 NULL,
	CONSTRAINT conversion_history_pkey PRIMARY KEY (id)
);

ALTER TABLE conversion_history ADD CONSTRAINT fk4acp8fv9bd0irkpeg91wxtewk FOREIGN KEY (source_currency_id) REFERENCES currency(id);
ALTER TABLE conversion_history ADD CONSTRAINT fksovk2oismyuve1x5i6fdtxaoe FOREIGN KEY (target_currency_id) REFERENCES currency(id)