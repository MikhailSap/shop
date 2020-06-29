INSERT INTO usrs (name, password) VALUES ('Vasya', '$2y$12$c.mZFwZDOgHxONNkdrBGZ.rmbDy11VjoVzjRLwlqrEsaiIcts3EYq'),
                                         ('Goga', '$2y$12$PCaSxA3F1lAHYrkjrRCuJOFezLkA.93cRZKtmnXBFDrwfI0PstW.q'),
                                         ('Mike', '$2y$12$csk0uCD8oHcknD5DhFOVNevdPuKh4vm7IJY9H8xGPGbJFpWocQFCi');



INSERT INTO roles (name) VALUES ('ROLE_USER'),
                                ('ROLE_MANAGER'),
                                ('ROLE_ADMIN'),
                                ('ROLE_SUPERADMIN');



INSERT INTO user_role (user_id, role_id) VALUES (1, 1),
                                                (2, 2),
                                                (3, 2);



