Use [master]
go
create database [Assignment]
go
USE [Assignment]
GO
CREATE TABLE account (
    uid INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    accountType NVARCHAR(50) DEFAULT 'reader'
);
GO
INSERT INTO account (username, password, email, accountType) VALUES
('user1', 't', 'user1@example.com', 'uploader'),
('user2', 't', 'user2@example.com', 'reader'),
('user3', 't', 'user3@example.com', 'reader'),
('user4', 't', 'user4@example.com', 'reader'),
('user5', 't', 'user5@example.com', 'reader'),
('user6', 't', 'user6@example.com', 'uploader'),
('user7', 't', 'user7@example.com', 'uploader'),
('user8', 't', 'user8@example.com', 'uploader'),
('user9', 't', 'user9@example.com', 'uploader'),
('user10', 't', 'user10@example.com', 'uploader'),
('user11', 't', 'user11@example.com', 'admin'),
('user12', 't', 'user12@example.com', 'admin'),
('user13', 't', 'user13@example.com', 'admin'),
('user14', 't', 'user14@example.com', 'reader'),
('user15', 't', 'user15@example.com', 'reader'),
('user16', 't', 'user16@example.com', 'uploader'),
('user17', 't', 'user17@example.com', 'uploader'),
('user18', 't', 'user18@example.com', 'reader'),
('user19', 't', 'user19@example.com', 'admin'),
('admin', 't', 'user20@example.com', 'admin');
GO

CREATE TABLE manga (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    imageUrl VARCHAR(255) NOT NULL,
    author NVARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    status NVARCHAR(50) NOT NULL,
);
Go
INSERT INTO manga (name, imageUrl, author, date, status) VALUES
('Attack on Titan', 'https://mangadex.org/covers/304ceac3-8cdb-4fe7-acf7-2b6ff7a60613/29f82b1d-b37f-455a-b630-e42bccb1422a.jpg', 'Hajime Isayama', '2009-09-09', N'Đang cập nhật'),
('My Hero Academia', 'https://mangadex.org/covers/4f3bcae4-2d96-4c9d-932c-90181d9c873e/24b433fa-8b8c-4570-9b55-918ad60a6170.jpg', 'Kohei Horikoshi', '2014-07-07', N'Đang cập nhật'),
('One Piece', 'https://mangadex.org/covers/a1c7c817-4e59-43b7-9365-09675a149a6f/2f4aca53-64c7-46ac-ae85-3bc9b3169890.png', 'Eiichiro Oda', '1997-07-22', N'Đang cập nhật'),
('Demon Slayer', 'https://mangadex.org/covers/789642f8-ca89-4e4e-8f7b-eee4d17ea08b/6fa2282f-282b-41cf-8ab7-fbdbeb822bd5.jpg', 'Koyoharu Gotouge', '2016-02-15', N'Hoàn thành'),
('Naruto', 'https://mangadex.org/covers/6b1eb93e-473a-4ab3-9922-1a66d2a29a4a/c5a3090c-4ca0-40a2-9102-e0ee0c6dac15.jpg', 'Masashi Kishimoto', '1999-09-21', N'Hoàn thành'),
('Tokyo Ghoul', 'https://mangadex.org/covers/6a1d1cb1-ecd5-40d9-89ff-9d88e40b136b/040e8ae9-4ddd-49d2-8986-56782b391714.jpg', 'Sui Ishida', '2011-09-08', N'Hoàn thành'),
('Fullmetal Alchemist', 'https://mangadex.org/covers/dd8a907a-3850-4f95-ba03-ba201a8399e3/a9cd0207-1b86-4738-a2b5-3575c32d5315.jpg', 'Hiromu Arakawa', '2001-07-12', N'Hoàn thành'),
('Death Note', 'https://mangadex.org/covers/75ee72ab-c6bf-4b87-badd-de839156934c/8f45c074-5512-4679-a14b-cac837c87171.jpg', 'Tsugumi Ohba', '2003-04-02', N'Hoàn thành'),
('Sword Art Online', 'https://mangadex.org/covers/3dd0b814-23f4-4342-b75b-f206598534f6/b9293d5d-9b05-4505-813e-f16efb42606a.jpg', 'Reki Kawahara', '2009-04-10', N'Hoàn thành'),
('Bleach', 'https://mangadex.org/covers/239d6260-d71f-43b0-afff-074e3619e3de/032cc781-43eb-4281-956c-7cf991dd0a6a.jpg', 'Tite Kubo', '2001-08-07', N'Hoàn thành'),
('Hunter x Hunter', 'https://mangadex.org/covers/db692d58-4b13-4174-ae8c-30c515c0689c/aa112927-f1e5-4fe4-a4db-7fd4a1536e3c.jpg', 'Yoshihiro Togashi', '1998-03-03', N'Đang cập nhật'),
('JoJo’s Bizarre Adventure', 'https://mangadex.org/covers/5a547d1d-576b-477f-8cb3-70a3b4187f8a/5336b1e8-48be-4ea9-bd66-d52b8b8112c0.jpg', 'Hirohiko Araki', '1987-01-01', N'Đang cập nhật'),
('Fairy Tail', 'https://mangadex.org/covers/227e3f72-863f-46f9-bafe-c43104ca29ee/a74a86c4-0585-453a-8027-d6732d742dd6.jpg', 'Hiro Mashima', '2006-08-02', N'Hoàn thành'),
('Black Clover', 'https://mangadex.org/covers/e7eabe96-aa17-476f-b431-2497d5e9d060/a8ce3755-86ae-428a-af1b-9f283ab3ae83.jpg', 'Yūki Tabata', '2015-02-16', N'Đang cập nhật'),
('One Punch Man', 'https://mangadex.org/covers/d8a959f7-648e-4c8d-8f23-f1f3f8e129f3/dfc14954-f855-47a3-9401-4abe2a78621a.jpg', 'Yusuke Murata', '2012-06-14', N'Đang cập nhật'),
('Mob Psycho 100', 'https://mangadex.org/covers/736a2bf0-f875-4b52-a7b4-e8c40505b68a/4ca68630-7cf6-42b4-a628-065f55eb1f6f.jpg', 'ONE', '2012-08-18', N'Hoàn thành'),
('The Promised Neverland', 'https://mangadex.org/covers/46e9cae5-4407-4576-9b9e-4c517ae9298e/97b244ef-5179-4e21-bbba-099c5f129bda.jpg', 'Kaiu Shirai', '2016-08-01', N'Hoàn thành'),
('Your Name', 'https://mangadex.org/covers/c071276e-abd4-4711-8b14-544431eb152a/ad37bcec-aed0-451c-b9a7-e8c5ed3c6762.jpg', 'Makoto Shinkai', '2016-08-24', N'Hoàn thành'),
('Kaguya-sama: Love Is War', 'https://mangadex.org/covers/37f5cce0-8070-4ada-96e5-fa24b1bd4ff9/24d3c21f-c05d-403e-8640-192c972f04b2.jpg', 'Aka Akasaka', '2015-05-19', N'Đang cập nhật'),
('Haikyuu!!', 'https://mangadex.org/covers/8f8b7cb0-7109-46e8-b12c-0448a6453dfa/9078041a-7c7b-4cf1-ab7b-e683beb501c7.jpg', 'Haruichi Furudate', '2012-04-06', N'Hoàn thành'),
('Yona of the Dawn', 'https://mangadex.org/covers/3bb0279f-a01d-4aa4-93e4-305800f4b83e/72db86cc-76b0-4280-963b-0ea6fe30f125.jpg', 'Mizuho Kusanagi', '2009-08-05', N'Đang cập nhật'),
('Noragami', 'https://mangadex.org/covers/e5ce88e2-8c46-482d-8acf-5c6d5a64a585/eb7e8831-89fc-4ae1-938d-92edd86419b7.jpg', 'Adachitoka', '2010-01-17', N'Đang cập nhật'),
('Toradora!', 'https://mangadex.org/covers/e9dc373b-314c-44ff-9428-ae63319ae86b/b57e18e9-a3d7-49cb-8df1-8377376d5d3e.jpg', 'Yuyuko Tokemiya', '2006-12-25', N'Hoàn thành'),
('Assassination Classroom', 'https://mangadex.org/covers/333f4d22-7753-4e3b-b0da-0a69b2cdce4f/da6a849e-1645-46ae-9ada-8f13356595f0.jpg', 'Yūsei Matsui', '2012-07-02', N'Hoàn thành'),
('March Comes in Like a Lion', 'https://mangadex.org/covers/0ca1627e-95dd-4118-892a-f144adf02256/eab815ea-903c-41a4-a6de-685503e5a36f.jpg', 'Chica Umino', '04-07-2007', N'Đang cập nhật'),
('The Ancient Magus’ Bride', 'https://mangadex.org/covers/195023bf-cf9a-4772-94ef-09dd6eddea84/85062c54-6c2a-4046-8ab2-ca8f09f9df85.jpg', 'Kore Yamazaki', '2013-11-10', N'Đang cập nhật'),
('Gintama', 'https://mangadex.org/covers/f65444dc-3694-4e31-a166-8afb2938ed55/a77b9a0f-e9e6-4b95-959a-c3ddf7cf7f12.jpg', 'Hideaki Sorachi', '2004-12-08', N'Hoàn thành'),
('Berserk', 'https://mangadex.org/covers/801513ba-a712-498c-8f57-cae55b38cc92/bae91cd3-2847-4c19-8777-9da5db0ed2d1.jpg', 'Kentaro Miura', '1989-08-25', N'Hoàn thành'),
('Dandadan', 'https://mangadex.org/covers/68112dc1-2b80-4f20-beb8-2f2a8716a430/b2be43ff-4b60-4ad0-8ec9-a50e21320fd5.jpg', 'Tatsu Yukinobu', '2021-04-06', N'Đang cập nhật'),
('Doraemon', 'https://mangadex.org/covers/e36da8b0-feca-46dd-9120-d5dc2f3feae8/63db63d4-5f3c-427c-8f91-87d94570a0c1.jpg', 'Fujiko F. Fujio', '1969-12-01', N'Hoàn thành');
go
CREATE TABLE user_follow (
    user_id INT,
    manga_id INT,
    PRIMARY KEY (user_id, manga_id),
    FOREIGN KEY (user_id) REFERENCES account(uid),
    FOREIGN KEY (manga_id) REFERENCES manga(id)
);
go
INSERT INTO user_follow (user_id, manga_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 4),
(2, 5),
(3, 2),
(3, 3),
(3, 6),
(4, 7),
(4, 8),
(5, 9),
(5, 10),
(6, 11),
(6, 12);
CREATE TABLE user_owner (
    user_id INT,
    manga_id INT,
    PRIMARY KEY (user_id, manga_id),
    FOREIGN KEY (user_id) REFERENCES account(uid),
    FOREIGN KEY (manga_id) REFERENCES manga(id)
);
go
INSERT INTO user_owner (user_id, manga_id) VALUES
(1,29), (1,30),
(6, 1), (6, 2), (6, 3), (6, 4), (6, 5),
(7, 6), (7, 7), (7, 8), (7, 9), (7, 10),
(8, 11), (8, 12), (8, 13), (8, 14), (8, 15),
(9, 16), (9, 17), (9, 18), (9, 19), (9, 20),
(10, 21), (10, 22), (10, 23), (10, 24), (10, 25),
(16, 26), (16, 27), (17, 28);
go
CREATE TABLE type (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL
);
go
INSERT INTO type (name) VALUES 
(N'Hành động'),
(N'Phiêu lưu'),
(N'Trinh thám'),
(N'Kinh dị'),
(N'Lãng mạn'),
(N'Tâm lý học'),
(N'Hài hước'),
(N'Chính kịch'),
(N'Đời thường'),
(N'Thể thao'),
(N'Lịch sử'),
(N'Khoa học viễn tưởng'),
(N'Siêu nhiên'),
(N'Giả tưởng'),
(N'Bí ẩn');
GO

CREATE TABLE manga_type (
    manga_id INT,
    type_id INT,
    PRIMARY KEY (manga_id, type_id),
    FOREIGN KEY (manga_id) REFERENCES manga(id),
    FOREIGN KEY (type_id) REFERENCES type(id)
);
go
INSERT INTO manga_type (manga_id, type_id) VALUES
(1, 1), (1, 4), (1, 11), -- Attack on Titan: Hành động, Kinh dị, Kì ảo
(2, 1), (2, 2), (2, 7), -- My Hero Academia: Hành động, Phiêu lưu, Hài hước
(3, 1), (3, 2), (3, 11), -- One Piece: Hành động, Phiêu lưu, Kì ảo
(4, 1), (4, 4), (4, 10), -- Demon Slayer: Hành động, Kinh dị, Siêu nhiên
(5, 1), (5, 8), (5, 10), -- Naruto: Hành động, Kịch tính, Siêu nhiên
(6, 4), (6, 11), (6, 12), -- Tokyo Ghoul: Kinh dị, Kì ảo, Huyền bí
(7, 1), (7, 2), (7, 11), -- Fullmetal Alchemist: Hành động, Phiêu lưu, Kì ảo
(8, 12), (8, 3), (8, 13), -- Death Note: Huyền bí, Trinh thám, Siêu nhiên
(9, 1), (9, 2), (9, 6), -- Sword Art Online: Hành động, Phiêu lưu, Tâm lý
(10, 1), (10, 2), (10, 11), -- Bleach: Hành động, Phiêu lưu, Kì ảo
(11, 1), (11, 2), (11, 10), -- Hunter x Hunter: Hành động, Phiêu lưu, Siêu nhiên
(12, 1), (12, 7), (12, 11), -- JoJo’s Bizarre Adventure: Hành động, Hài hước, Kì ảo
(13, 1), (13, 2), (13, 11), -- Fairy Tail: Hành động, Phiêu lưu, Kì ảo
(14, 1), (14, 2), (14, 6), -- Black Clover: Hành động, Phiêu lưu, Tâm lý
(15, 1), (15, 7), (15, 11), -- One Punch Man: Hành động, Hài hước, Kì ảo
(16, 1), (16, 6), (16, 12), -- Mob Psycho 100: Hành động, Tâm lý, Huyền bí
(17, 2), (17, 8), (17, 12), -- The Promised Neverland: Phiêu lưu, Kịch tính, Huyền bí
(18, 5), (18, 6), -- Your Name: Tình cảm, Tâm lý
(19, 5), (19, 7), -- Kaguya-sama: Love Is War: Tình cảm, Hài hước
(20, 2), (20, 9), (20, 6), -- Haikyuu!!: Phiêu lưu, Thể thao, Tâm lý
(21, 2), (21, 5), (21, 6), -- Yona of the Dawn: Phiêu lưu, Tình cảm, Tâm lý
(22, 2), (22, 11), (22, 12), -- Noragami: Phiêu lưu, Kì ảo, Huyền bí
(23, 5), (23, 8), -- Toradora!: Tình cảm, Kịch tính
(24, 1), (24, 7), (24, 12), -- Assassination Classroom: Hành động, Hài hước, Huyền bí
(25, 6), (25, 9), -- March Comes in Like a Lion: Tâm lý, Đời thường
(26, 5), (26, 11), (26, 12), -- The Ancient Magus’ Bride: Tình cảm, Kì ảo, Huyền bí
(27, 1), (27, 7), (27, 11), -- Gintama: Hành động, Hài hước, Kì ảo
(28, 1), (28, 11), (28, 12), -- Berserk: Hành động, Kì ảo, Huyền bí
(29,1),(29,7),(29,13),
(30,2),(30,7),(30,12),(30,9);
go

CREATE TABLE chap (
    id INT IDENTITY(1,1) PRIMARY KEY,
    manga_id INT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    link NVARCHAR(255) NOT NULL,
    FOREIGN KEY (manga_id) REFERENCES manga(id)
);
go