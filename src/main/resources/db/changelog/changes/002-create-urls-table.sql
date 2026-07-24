--changeset hassan:002
CREATE TABLE urls (
                      id BIGSERIAL PRIMARY KEY,
                      original_url TEXT NOT NULL,
                      short_code VARCHAR(10) NOT NULL UNIQUE,
                      created_by BIGINT NOT NULL REFERENCES users(id),
                      created_at TIMESTAMP NOT NULL,
                      active BOOLEAN DEFAULT TRUE,
                      total_clicks BIGINT DEFAULT 0
);