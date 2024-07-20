
CREATE TABLE labels
(
    id     UUID NOT NULL,
    labels VARCHAR(255)
);

CREATE TABLE query
(
    id   UUID  NOT NULL,
    area JSONB NOT NULL,
    CONSTRAINT pk_query PRIMARY KEY (id)
);

CREATE TABLE results
(
    id       UUID NOT NULL,
    spot_id  UUID,
    location JSON
);

CREATE TABLE tags
(
    id    UUID NOT NULL,
    key   OID,
    value OID
);

ALTER TABLE labels
    ADD CONSTRAINT fk_labels_on_query FOREIGN KEY (id) REFERENCES query (id);

ALTER TABLE results
    ADD CONSTRAINT fk_results_on_query FOREIGN KEY (id) REFERENCES query (id);

ALTER TABLE tags
    ADD CONSTRAINT fk_tags_on_query FOREIGN KEY (id) REFERENCES query (id);