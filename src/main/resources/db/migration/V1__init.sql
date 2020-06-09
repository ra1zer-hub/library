CREATE TABLE authors
(
  id       serial,
  fullname varchar(100) not null,
  primary key (id)
);

INSERT INTO authors (fullname)
VALUES ('Автор1'),
       ('Автор2'),
       ('Автор3');

CREATE TABLE publishers
(
  id   serial,
  name varchar(50),
  primary key (id)
);

INSERT INTO publishers (name)
VALUES ('Издатель1'),
       ('Издатель2'),
       ('Издатель3');

CREATE TABLE genres
(
  id         serial,
  genre_name varchar(20) UNIQUE,
  primary key (id)
);

INSERT INTO genres (genre_name)
VALUES ('Боевики'),
       ('Ужастики'),
       ('Драма'),
       ('Детектив'),
       ('Приключения'),
       ('Вестерн'),
       ('Фантакстика'),
       ('Классика'),
       ('Бизнес'),
       ('Детские');

CREATE TABLE books
(
  id           serial,
  title        varchar(100) not null,
  content      varchar(100) default null,
  page_count   int          not null,
  genre_id     bigint       not null,
  author_id    bigint       not null,
  publisher_id bigint       not null,
  image        varchar(100) default null,
  description  varchar(500) default null,
  primary key (id),
  CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors (id),
  CONSTRAINT fk_genre FOREIGN KEY (genre_id) REFERENCES genres (id),
  CONSTRAINT fk_publisher FOREIGN KEY (publisher_id) REFERENCES publishers (id)
);

INSERT INTO books (title, image, page_count, genre_id, author_id, publisher_id, content)
VALUES ('Книга 1', '1.jpg', 20, 1, 1, 1, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 2', '2.jpg', 25, 2, 2, 2, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 3', '3.jpg', 30, 3, 3, 3, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 4', '1.jpg', 20, 4, 1, 1, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 5', '2.jpg', 25, 5, 2, 2, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 6', '3.jpg', 30, 6, 3, 3, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 7', '1.jpg', 20, 7, 1, 1, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 8', '2.jpg', 25, 8, 2, 2, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 9', '3.jpg', 30, 1, 3, 3, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 10', '1.jpg', 20, 2, 1, 1, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 11', '2.jpg', 25, 3, 2, 2, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 12', '3.jpg', 30, 3, 3, 3, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 13', '1.jpg', 20, 4, 1, 1, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 14', '2.jpg', 25, 5, 2, 2, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf'),
       ('Книга 15', '3.jpg', 30, 6, 3, 3, 'Dostoevskiyi_F_Selo_Stepanchikovo_I_Ego.pdf');

CREATE TABLE users
(
  id       serial,
  username varchar(30) not null UNIQUE,
  password varchar(80) not null,
  email    varchar(50) not null UNIQUE,
  role_id  bigint      not null,
  primary key (id)
);

INSERT INTO users (username, password, email, role_id)
VALUES ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob@gmail.ru', 2);

CREATE TABLE roles
(
  id   serial,
  name varchar(50) default null UNIQUE,
  primary key (id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

