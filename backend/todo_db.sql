CREATE TYPE TaskStatus AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED');

CREATE TYPE NotificationStatus AS ENUM ('READ', 'UNREAD');

CREATE TABLE Board (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE "User" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE TaskList (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    board_id INT REFERENCES Board(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Task (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status TaskStatus,
    board_id INT REFERENCES Board(id),
    user_id INT REFERENCES "User"(id),
    task_list_id INT REFERENCES TaskList(id),  -- Relacionamento com TaskList
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Favorite (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES "User"(id),
    task_id INT REFERENCES Task(id),
    UNIQUE(user_id, task_id)
);

CREATE TABLE Notification (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES "User"(id),
    message TEXT NOT NULL,
    status NotificationStatus DEFAULT 'UNREAD',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Category_Task (
    category_id INT REFERENCES Category(id),
    task_id INT REFERENCES Task(id),
    PRIMARY KEY(category_id, task_id)
);

CREATE INDEX idx_user_id ON Task(user_id);
CREATE INDEX idx_task_status ON Task(status);
CREATE INDEX idx_task_due_date ON Task(due_date);
CREATE INDEX idx_task_list_board_id ON TaskList(board_id);
