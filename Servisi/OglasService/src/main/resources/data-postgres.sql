insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka1','Model1','Klasa1','Gorivo1','Menjac1','100km','4',true, 2);
insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka2','Model2','Klasa2','Gorivo2','Menjac2','200km','2',true, 3);
insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka3','Model3','Klasa3','Gorivo3','Menjac3','300km','2',true, 2);

insert into oglas ( user_id, vozilo_id, mesto, cena, popust, cenaspopust) values ( 2, 1, 'Ugljevik', 10, 10, 10 );
insert into oglas ( user_id, vozilo_id, mesto, cena, popust, cenaspopust) values ( 3, 2, 'Novi Sad', 20, 20, 20 );

insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka4','Model4','Klasa4','Gorivo4','Menjac4','400km','4',true, 3);
insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka5','Model5','Klasa5','Gorivo5','Menjac5','500km','2',true, 2);
insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka6','Model6','Klasa6','Gorivo6','Menjac6','600km','4',true, 3);

insert into oglas ( user_id, vozilo_id, mesto, cena, popust, cenaspopust) values ( 3, 4, 'Bijeljina', 10, 10, 10 );
insert into oglas ( user_id, vozilo_id, mesto, cena, popust, cenaspopust) values ( 2, 5, 'Beograd', 20, 20, 20 );

insert into vozilo ( markavozila_id, modelvozila_id, klasavozila_id, tipgoriva_id, vrstamenjaca_id, predjenikm, brsedistadeca, cdw, user_id) values ( 'Marka7','Model7','Klasa7','Gorivo7','Menjac7','700km','4',true, 2);
insert into oglas ( user_id, vozilo_id, mesto, cena, popust, cenaspopust) values ( 2, 7, 'Sarajevo', 20, 20, 20 );

insert into izvestaj (predjeniKm, komentar, vozilo_id) values ('125', 'Vozilo vraceno na vrijeme', 7);