INSERT  INTO user_details(id, name, birth_date)
VALUES(1, 'Shaun', current_date()),
      (2, 'Teppei', current_date()),
      (3, 'Taiki', current_date()),
      (4, 'Nguyen', current_date())
;

INSERT INTO post_detail(id, description, user_id)
VALUES (1, 'I want to learn', 2),
       (2, 'Someone go out to eat', 4),
       (3, 'I need to go home', 1),
       (4, 'I dont like him',3)
;