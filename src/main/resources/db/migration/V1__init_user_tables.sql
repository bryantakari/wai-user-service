-- Table
CREATE TABLE account (
  id               integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username         varchar(50)  NOT NULL,
  email            varchar(50)  NOT NULL,
  phone            varchar(20),
  fullname         varchar(255) NOT NULL,
  password         varchar(255) NOT NULL,
  created_at       timestamptz  NOT NULL DEFAULT now(),
  updated_at       timestamptz  NOT NULL DEFAULT now(),
  last_login_at    timestamptz
);

-- Recommended constraints (optional but typical)
ALTER TABLE account ADD CONSTRAINT account_username_uk UNIQUE (username);
ALTER TABLE account ADD CONSTRAINT account_email_uk    UNIQUE (email);

-- Auto-update updated_at on UPDATE
CREATE OR REPLACE FUNCTION account_set_updated_at()
RETURNS trigger AS $$
BEGIN
  NEW.updated_at := now();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_account_set_updated_at
BEFORE UPDATE ON account
FOR EACH ROW EXECUTE FUNCTION account_set_updated_at();