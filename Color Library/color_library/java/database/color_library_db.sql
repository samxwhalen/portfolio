
BEGIN TRANSACTION;

CREATE TABLE users
(
	userId serial,
	userName varchar(100),
	firstName varchar(64) not null,
	lastName varchar(64) not null,
	email varchar(100) not null,
	location_id int,
	artMedium varChar(100)

	constraint pk_users primary key (userId)
);

CREATE TABLE color
(
	hexCode hex not NULL,
	commonName varchar(100),
	htmlCode int,
	rgb_triplet int,
	crayolaName varchar(100),
	pantoneName varchar(100),
	webColorName varchar(100)

	constraint pk_color primary key (hexCode)
);

CREATE TABLE emotion
(
	emotionId serial,
	emotionName varchar(64) not null

	constraint pk_emotion primary key (emotionId)
	
);

CREATE TABLE symbol
(
	symbolId serial,
	symbolName varchar(64) not null

	constraint pk_symbol primary key (symbolId)
	
);

CREATE TABLE location
(
	locationId serial,
	ip_address varchar(100),
	cityName varchar(64) not null,
	countryName varchar(64) not null,
	postalCode varchar(15) not null

	constraint pk_location primary key (locationId)
);


CREATE TABLE personal_assocation
(
	pers_assocId serial,
	emotionId int not Null,
	symbolId int not null,
	memory varchar(300),
	image_url varchar(100)

	constraint pk_personal_assocation primary key (pers_assocId),
	constraint fk_personal_assocation_emotion foreign key (emotionId) references emotion (emotionId),
	constraint fk_personal_assocation_symbol foreign key (symbolId) references symbol (symbolId)
);

CREATE TABLE public_assocation
(
	pub_assocId serial,
	emotionId int not Null,
	symbolId int not null,
	locationId int not null,

	constraint pk_public_assocation primary key (pub_assocId),
	constraint fk_public_assocation_emotion foreign key (emotionId) references emotion (emotionId),
	constraint fk_public_assocation_symbol foreign key (symbolId) references symbol (symbolId), 
	constraint fk_public_assocation_location foreign key (locationId) references location (location)
);


CREATE TABLE emotion_color
(
	emotionId int not NULL,
	color_hexCode hex not NULL

	constraint pk_emotion_color primary key (emotionId, hexCode),
	constraint fk_emotion_color_emotion foreign key (emotionId) references emotion (emotionId),
	constraint fk_emotion_color_color foreign key (color_hexCode) references color (hexCode)
);

CREATE TABLE symbol_color
(
	symbolId int not NULL,
	color_hexCode hex not NULL

	constraint pk_symbol_color primary key (symbolId, hexCode),
	constraint fk_emotion_color_symbol foreign key (symbolId) references symbol (symbolId),
	constraint fk_emotion_color_color foreign key (color_hexCode) references color (hexCode)
);


CREATE TABLE user_personal
(
	userId int not null,
	pers_assocId  int not null

	constraint pk_user_personal primary key (userId, pers_assocId),
	constraint fk_user_personal_users foreign key (userId) references users (userId),
	constraint fk_user_personal_personal_association foreign key (pers_assocId) references personal_assocation (pers_assocId)

);

CREATE TABLE user_public
(
	userId int not null,
	pub_assocId  int not null, 
	locationId int not null

	constraint pk_user_personal primary key (userId, pub_assocId, locationId),
	constraint fk_user_personal_user foreign key (userId) references users (userId),
	constraint fk_user_personal_public_association foreign key (pub_assocId) references public_assocation (pub_assocId), 
	constraint fk_user_personal_location foreign key (locationId) references location (locationId)
);


ROLLBACK;
