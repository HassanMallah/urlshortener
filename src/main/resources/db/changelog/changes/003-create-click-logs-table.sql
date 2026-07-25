--changeset hassan:003
CREATE TABLE click_logs (
                            id  UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
                            url_id UUID NOT NULL REFERENCES urls(id),
                            ip_address VARCHAR(50),
                            country VARCHAR(100),
                            device VARCHAR(50),
                            browser VARCHAR(50),
                            os VARCHAR(50),
                            clicked_at TIMESTAMP NOT NULL
);