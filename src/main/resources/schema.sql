CREATE TABLE IF NOT EXISTS utilizatori (
    id SERIAL PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    parola VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL CHECK (rol IN ('STUDENT', 'INSTRUCTOR')),
    specializare VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS cursuri (
    id SERIAL PRIMARY KEY,
    titlu VARCHAR(200) NOT NULL,
    descriere TEXT,
    instructor_id INT NOT NULL REFERENCES utilizatori(id) ON DELETE CASCADE,
    categorie VARCHAR(100),
    pret NUMERIC(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS module (
    id SERIAL PRIMARY KEY,
    titlu VARCHAR(200) NOT NULL,
    continut TEXT,
    durata_minute INT NOT NULL DEFAULT 0,
    curs_id INT NOT NULL REFERENCES cursuri(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS quizuri (
    id SERIAL PRIMARY KEY,
    curs_id INT NOT NULL REFERENCES cursuri(id) ON DELETE CASCADE,
    punctaj_maxim INT NOT NULL DEFAULT 10
);

CREATE TABLE IF NOT EXISTS intrebari (
    id SERIAL PRIMARY KEY,
    text VARCHAR(500) NOT NULL,
    variante_raspuns TEXT NOT NULL,
    index_raspuns_corect INT NOT NULL,
    quiz_id INT NOT NULL REFERENCES quizuri(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS inscrieri (
    id SERIAL PRIMARY KEY,
    student_id INT NOT NULL REFERENCES utilizatori(id) ON DELETE CASCADE,
    curs_id INT NOT NULL REFERENCES cursuri(id) ON DELETE CASCADE,
    data_inscriere DATE NOT NULL DEFAULT CURRENT_DATE,
    progres NUMERIC(5,2) NOT NULL DEFAULT 0,
    nota_finala NUMERIC(5,2) NOT NULL DEFAULT -1,
    scor_quiz NUMERIC(5,2) NOT NULL DEFAULT -1,
    UNIQUE (student_id, curs_id)
);
ALTER TABLE inscrieri ADD COLUMN IF NOT EXISTS scor_quiz NUMERIC(5,2) NOT NULL DEFAULT -1;
