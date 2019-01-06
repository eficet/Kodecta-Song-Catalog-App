INSERT INTO Artist (name, age,country) VALUES
  ('Fayiz', 22, 'Palestine'),
  ('Hamza', 23, 'BiH'),
  ('Ahmed', 42, 'Brazil'),
  ('Furkan', 33, 'Brazil'),
  ('Semso', 37, 'BiH');

  INSERT INTO Artist (name, age,country) VALUES
    ('Admir', 22, 'BiH'),
    ('Samir', 33, 'Palestine');

 INSERT INTO Provider (provider_name) VALUES
    ('Gunit'),
    ('Alibaba'),
    ('Infinity');

 INSERT INTO Song (song_name,genre,artist_id,PROVIDER_ID) VALUES
     ('Hello','pop',3,1),
     ('Habibi','rock',5,2),
     ('Near','rock',3,2),
     ('ComeBack','balad',5,1),
     ('Love','balad',3,3);