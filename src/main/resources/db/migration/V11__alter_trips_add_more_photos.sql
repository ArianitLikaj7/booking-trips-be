ALTER TABLE trips DROP COLUMN IF EXISTS image_url;

CREATE TABLE trip_images (
                             trip_id BIGINT NOT NULL,
                             image_url VARCHAR(255),
                             CONSTRAINT fk_trip_images_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);
