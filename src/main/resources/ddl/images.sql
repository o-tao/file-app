USE edu;

CREATE OR REPLACE TABLE images 
(
	`no` 		INT 			NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nm1`		VARCHAR(100)	NOT NULL,
	`nm2`		VARCHAR(100)	NOT NULL,
	`extension`	VARCHAR(10)		NOT NULL,
	`path`		VARCHAR(150)	NOT NULL,
	`type` 		VARCHAR(50) 	NOT NULL,
	`del` 		BOOLEAN 		NOT NULL DEFAULT(0)
);

SELECT * FROM images;
