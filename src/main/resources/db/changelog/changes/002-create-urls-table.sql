--changeset hassan:002
CREATE TABLE urls (
                      id  UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
                      original_url TEXT NOT NULL,
                      short_code VARCHAR(10) NOT NULL UNIQUE,
                      created_by UUID NOT NULL REFERENCES users(id),
                      created_at TIMESTAMP NOT NULL,
                      is_active BOOLEAN DEFAULT TRUE,
                      total_clicks BIGINT DEFAULT 0
);