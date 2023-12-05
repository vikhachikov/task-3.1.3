INSERT INTO users (age, name, surname, login, password)
VALUES
    (22, 'Vik', 'Garnetov', 'vik', '$2a$12$6LnwbOdMj8xy0VKygy2Vbeel/qxBj7FwcKJDIwuBcbm47OX88sC7i'),
    (27, 'Bella', 'Got', 'bella', '$2a$12$O//mVHEfrF4hjJnwL6H89e.Z.txyuC2awNMjCHtTlH1zjq1gD.ZCm');
INSERT INTO roles (role_name)
values
    ('ROLE_ADMIN'),
    ('ROLE_USER');
INSERT INTO users_roles (user_id, role_id)
values
    (1, 1),
    (1, 2),
    (2,2);