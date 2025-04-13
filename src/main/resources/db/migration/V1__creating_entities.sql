
CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       username VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       phone_number VARCHAR(255),
                       image_url_of_user VARCHAR(255)
);

CREATE TABLE trips (
                       id UUID PRIMARY KEY,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       created_by UUID,
                       origin VARCHAR(255),
                       destination VARCHAR(255),
                       available_seats INTEGER,
                       total_seats INTEGER,
                       route VARCHAR(255),
                       price DECIMAL,
                       title VARCHAR,
                       description VARCHAR,
                       type_of_trip VARCHAR,
                       company_name VARCHAR(255),
                       local_date_time TIMESTAMP,
                       CONSTRAINT fk_trip_creator FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE reservations (
                              id UUID PRIMARY KEY,
                              created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                              user_id UUID,
                              trip_id UUID,
                              seat_number INTEGER,
                              reserved_for VARCHAR(255),
                              CONSTRAINT fk_res_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                              CONSTRAINT fk_res_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);

CREATE TABLE favorites (
                           id UUID PRIMARY KEY,
                           user_id UUID NOT NULL,
                           trip_id UUID NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           UNIQUE(user_id, trip_id),
                           CONSTRAINT fk_fav_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           CONSTRAINT fk_fav_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);

CREATE TABLE trip_images (
                             trip_id UUID NOT NULL,
                             image_url VARCHAR(255),
                             CONSTRAINT fk_trip_images_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);
