INSERT INTO pessoa (nome, id) VALUES
    ('Paulo Vitor', 1L),
    ('Fouquet', 2L),
    ('Leonardo', 3L);

INSERT INTO papel (nome, prioridade) VALUES
    ( 'ADMINISTRADOR', 3L),
    ( 'SCRUM MASTER', 2L),
    ( 'DESENVOLVEDOR', 1L);

INSERT INTO usuario (Id_Pessoa, email, senha) VALUES
    (1L, 'pvlb@gmail.com', '123'),
    (1L, 'pvlb-dev@gmail.com', '123'),
    (2L, 'Fqt@gmail.com', '123'),
    (3L, 'LeonardoDavid@gmail.com', '123');

INSERT INTO usuario_tem_papeis(usuario_id, papel_id) VALUES
    (1L, 2L),
    (1L, 3L),
    (2L, 3L),
    (3L, 3L),
    (4L, 1L);
