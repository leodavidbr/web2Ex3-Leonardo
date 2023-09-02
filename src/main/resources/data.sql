CREATE TABLE `pessoa`(
    nome VARCHAR(100) NOT NULL,
    id  BIGINT NOT NULL,

    PRIMARY KEY(id)
);

INSERT INTO pessoa (nome, id) VALUES
    ('Paulo', 1),
    ('Fouquet', 2),
    ('Leonardo', 3),
    ('Thyuany', 4),
    ('Esther', 5);