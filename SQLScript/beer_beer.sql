INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (1, 'Test', 'The design reveals it: This beer comes from Bavaria! The specialty from the house of Egerer is balanced and tasty, with a good and surprisingly high amount of aromatic and bitter hops that give the beer a bittersweet character. The color is golden yellow, with a fine white foam crown. To quote the Brewmaster: "It''s like sunshine!"', 'Urtyp Hell Edel Bayer', '4.9%', 3, 9, 3);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (2, 'Test', 'Blend of lambics aged 18 to 20 months and of Bergeron apricots.', 'Fou'' Foune', '5.0%', 5, 1, 4);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (3, 'Test', 'The heaviest of the Westvleteren beers, the 12 is a quadrupel style beer, which can be recognized by its yellow cap. Like the other Westvleteren beers, the bottle does not have a label, and the cap therefore has all the required information.', 'Trappist Westvleteren 12', '10.2%', 5, 8, 5);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (4, 'Test', 'Hazelnut Liquor Barrel Aged Imperial Stout - 2018 Edition
Collaboration w. FrauGruber ', 'Black Rabbit', '12.0%', 4, 7, 6);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (5, 'Test', 'This barley wine is a blend of multiple batches of beer which were aged in different spirits oak barrels. It''s complex aroma and taste of oak, dark and sweet caramel, vanilla, coconut, cocoa, roast, dark fruits and berries is a product of a complex brewing combined with the long maturation time and in the end of time spent in oak barrels. It will cellar very well, but do not go in to decades of ageing. It is a sipper and perfect to share on a special occasion. ', 'Hagger Blend 1116', '12.1%', 4, 5, 2);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (6, 'Test', 'Sweet Stout of Mine brings you back to the most blissful memories of your childhood with its taste of crème brûlée and vanilla and then kicks you back to reality with the punch it packs, reminding you that you’re all grown up now, enough that you can enjoy this kind of treat. With vegan lactose, so it can be enjoyed equally by everyone, this is our favourite kind of dessert nowadays. ', 'Sweet Stout of Mine', '10.5%', 1, 3, 6);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (7, 'Test', 'We named our beer after the psychedelic Tropicalia Samba music from the 70s. We always wanted to include a tropical ingredient in our beer and Hibiscus flowers fit the bill!', 'Tropikalia IPA', '7.0%', 1, 4, 7);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (8, 'Test', 'Caramel stout aged for almost 6 months in oak barrels previously containing Arkanj brandy (Calvados) made by the Kovilj Monastery. ', '02 Kolaboracija Caramel Stout', '10.0%', 2, 6, 6);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (9, 'Test', 'Milk Stout, smooth, full mouth feel. Strong body with distinctive flavours of coffee, cocoa, vanilla and tropical notes in the back, a real treat for the connoisseurs. Not for lactose intolerant.', 'Mikkeller Vista Milk Stout', '5.8%', 2, 2, 8);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (10, 'Test', 'Flying Dogma Galaxy IPA is the first collaboration between two breweries between Serbia and USA! Crispy, fruity and a little bit spicy American style IPA, ideal for this spring days!', 'Flying Dogma Galaxy IPA', '6.6%', 2, 2, 9);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (11, 'Test', 'Test description.', 'TestBeer', '5.0%', null, 2, 3);
INSERT INTO beer.beer (beer_id, beer_picture, description, name, abv, beer_country_fk, beer_brewery_fk, beer_style_fk) VALUES (12, 'Test', 'Test description.', 'TestBeer', '5.0%', null, 2, 3);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (8, 1, 3);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (9, 5, 6);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (10, 8, 2);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (11, 5, 5);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (12, 7, 2);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (13, 3, 9);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (14, 1, 1);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (15, 8, 3);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (16, 6, 2);
INSERT INTO beer.beertag (beertag_id, beer_beer_id, tag_tag_id) VALUES (17, 5, 1);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Bierol', 7);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Brasserie Cantillon', 1);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Brauhaus Bevog', 5);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Brouwerij De Sint-Sixtusabdij van Westvleteren', 8);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Dogma Brewery', 2);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Kabinet Brewery', 6);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Metalhead', 3);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('Privatbrauerei H. Egerer', 9);
INSERT INTO beer.brewery (name, brewery_id) VALUES ('White Stork Beer Co.', 4);
INSERT INTO beer.country (name, country_id) VALUES ('Austria', 4);
INSERT INTO beer.country (name, country_id) VALUES ('Belgium', 5);
INSERT INTO beer.country (name, country_id) VALUES ('Bulgaria', 1);
INSERT INTO beer.country (name, country_id) VALUES ('Germany', 3);
INSERT INTO beer.country (name, country_id) VALUES ('Serbia', 2);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (1, 1, null, 1, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (2, 2, 3, 1, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (3, 1, null, 2, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (4, 1, null, 3, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (5, 1, null, 4, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (6, 2, null, 3, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (7, 3, null, 7, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (8, 4, null, 8, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (9, 5, null, 9, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (10, 2, null, 10, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (11, 1, null, 11, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (12, 2, null, 11, null);
INSERT INTO beer.drank_beer (beer_user_id, user_user_id, rating_rating_id, beer_beer_id, column_5) VALUES (13, 1, null, 12, null);
INSERT INTO beer.rating (rating_value, rating_id) VALUES (1, 1);
INSERT INTO beer.rating (rating_value, rating_id) VALUES (2, 2);
INSERT INTO beer.rating (rating_value, rating_id) VALUES (3, 3);
INSERT INTO beer.rating (rating_value, rating_id) VALUES (4, 4);
INSERT INTO beer.rating (rating_value, rating_id) VALUES (5, 5);
INSERT INTO beer.style (name, style_id) VALUES ('Stout - Imperial / Double', 1);
INSERT INTO beer.style (name, style_id) VALUES ('Barleywine ', 2);
INSERT INTO beer.style (name, style_id) VALUES ('Lager - Helles', 3);
INSERT INTO beer.style (name, style_id) VALUES ('Lambic - Fruit', 4);
INSERT INTO beer.style (name, style_id) VALUES ('Belgian Quadrupel', 5);
INSERT INTO beer.style (name, style_id) VALUES ('Stout - Imperial / Double Milk', 6);
INSERT INTO beer.style (name, style_id) VALUES ('IPA - Red', 7);
INSERT INTO beer.style (name, style_id) VALUES ('Stout - Milk / Sweet', 8);
INSERT INTO beer.style (name, style_id) VALUES ('IPA - American', 9);
INSERT INTO beer.tag (tag_id, name) VALUES (1, 'Coffee');
INSERT INTO beer.tag (tag_id, name) VALUES (2, 'Caramel');
INSERT INTO beer.tag (tag_id, name) VALUES (3, 'Light');
INSERT INTO beer.tag (tag_id, name) VALUES (4, 'Dark');
INSERT INTO beer.tag (tag_id, name) VALUES (5, 'Sweet');
INSERT INTO beer.tag (tag_id, name) VALUES (6, 'Intense');
INSERT INTO beer.tag (tag_id, name) VALUES (7, 'Fresh');
INSERT INTO beer.tag (tag_id, name) VALUES (8, 'Wheat');
INSERT INTO beer.tag (tag_id, name) VALUES (9, 'White');
INSERT INTO beer.tag (tag_id, name) VALUES (10, 'Brown');
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (1, 'Petar', 'Petrov', 'p3sho_93', 'pesho@gmail.com', null);
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (2, 'Jonh', '', 'Jony_123', 'Jony@gmail.com', null);
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (3, '', '', 'beer_master', 'master_beer@yahoo.com', null);
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (4, 'Maria', 'Spasova', 'Maria_db', 'marcheto@gmail.com', null);
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (5, '', 'Petrov', 'noFear', 'igracha@gmail.com', null);
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (6, 'Allah', 'Mehmedov', 'bai_mangal_jelezoto', 'ciganina_bate@gmail.com', null);
INSERT INTO beer.user (user_id, first_name, last_name, nickname, email, picture) VALUES (7, 'Petar', 'Ivanov', 'drinkMaster', 'qko_piem_bace@abv.bg', null);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (1, 1, 2);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (2, 3, 4);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (3, 1, 1);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (4, 5, 2);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (5, 1, 11);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (6, 4, 8);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (7, 1, 8);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (8, 5, 3);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (9, 2, 2);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (10, 1, 10);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (11, 2, 10);
INSERT INTO beer.wish_beer (beer_user_id, user_user_id, beer_beer_id) VALUES (12, 3, 10);