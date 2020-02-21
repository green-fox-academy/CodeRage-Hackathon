INSERT INTO users (full_name, daily_wage, password, qualification, role, username)
VALUES ('admin', 999, '$2a$10$AUDofg41ZNClzkARuRkiq.uzE/33ACdXTsoK9Bka382fU9owY1A0W', 'admin',
        'admin', 'admin'),
       ('John Smith', 444, 'sadfkjkfdas', 'carpenter', 'Employee', 'Johny33'),
       ('Eric Smith', 222, 'sadfkjkfdas', 'carpenter', 'Employee', 'Ericc'),
       ('Will Smith', 11, 'sadfkjkfdas', 'constructionWorker, carpenter', 'Employee', 'Willy'),
       ('John Doe', 33, 'sadfkjkfdas', 'carpenter', 'Employee', 'Doe'),
       ('Some More', 231, 'sadfkjkfdas', 'carpenter', 'Employee', 'Summer'),
       ('Hanz', 144, 'sadfkjkfdas', 'carpenter', 'Employee', 'Schnitzel'),
       ('Henrik', 144, 'sadfkjkfdas', 'carpenter', 'Customer', 'Heni'),
       ('Gabor', 666, 'sadfkjkfdas', 'carpenter', 'Employee', 'Snoozy');

INSERT INTO tools (daily_price, name)
VALUES (43, 'saw'),
       (41, 'lesserSaw'),
       (88, 'hammer'),
       (66, 'axe'),
       (999, 'rotacioskapa'),
       (1, 'mop'),
       (99999, 'tractor');
