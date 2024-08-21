INSERT INTO users(users_id,email,name,nick_name,password)
    values (10001,'test@naver.com','test','testtest','test1234');

INSERT INTO my_reviews(my_reviwes_id,exhibitions_location, exhibitions_title, grade, main_image_url, visited_date, users_id)
values (1,'서울미술관','김민형 사진전','4.5','../assets/images/carousel4.jpg','2023.12.18',10001);

INSERT INTO my_reviews_contents(create_date,update_date,my_reviews_contents_id,art_title, artist, note, number, my_reviews_id)
values (NOW(),NOW(),1,'뉴욕거리1','김민형','좋다. 멋있고 짱이다.',1,1),
       (NOW(),NOW(),2,'뉴욕거리2','김민형','굿굿.',2,1);

INSERT INTO my_exhibition_images(my_reviews_contents_id,create_date,update_date,my_exhibition_images_url, my_reviews_contents_id)
values (1,NOW(),NOW(),'../assets/images/carousel4.jpg',1),
       (2,NOW(),NOW(),'../assets/images/carousel5.jpg',2);

INSERT INTO communications(communications_id,create_date, update_date, content, date, gallery, name, rate,users_id)
values (1,NOW(),NOW(),'많은 것을 배울 수 있었다.','2023.12.18','서울미술관','뉴욕거리1','1',10001);

INSERT INTO communications_keyword(communications_keyword_id, create_date, update_date, keyword, communications_id)
values (1,NOW(),NOW(),'흥미로운',1);

INSERT INTO communications_images(communications_images_id, create_date, update_date, communications_images_url, communications_id)
values (1,NOW(),NOW(),'../assets/images/carousel4.jpg',1);