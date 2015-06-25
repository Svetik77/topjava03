DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);


-- new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
-- new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
-- new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
-- new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
-- new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
-- new UserMeal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
INSERT INTO meals (description, calories, datetime, user_id)
VALUES ('Завтрак', 500, TIMESTAMP '2015-05-30 10:00:00',100000);
INSERT INTO meals (description, calories, datetime, user_id)
VALUES ('Обед', 1000, TIMESTAMP '2015-05-30 13:00:00',100000);
INSERT INTO meals (description, calories, datetime, user_id)
VALUES ('Ужин', 500, TIMESTAMP '2015-05-30 20:00:00',100000);
INSERT INTO meals (description, calories, datetime, user_id)
VALUES ('Завтрак', 500, TIMESTAMP '2015-05-31 10:00:00',100000);
INSERT INTO meals (description, calories, datetime, user_id)
VALUES ('Обед', 1000, TIMESTAMP '2015-05-31 13:00:00',100000);
INSERT INTO meals (description, calories, datetime, user_id)
VALUES ('Ужин', 510, TIMESTAMP '2015-05-31 20:00:00',100000);


