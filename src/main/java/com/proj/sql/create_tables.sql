

-- User table
CREATE TABLE User (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
);

-- Venue table
CREATE TABLE Venue (
  id INT PRIMARY KEY AUTO_INCREMENT, 
  name VARCHAR(50) NOT NULL,
  location VARCHAR(100) NOT NULL,
  seatingCapacity INT NOT NULL
);

-- Event table
CREATE TABLE Event (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  location VARCHAR(100) NOT NULL, 
  date DATE NOT NULL,
  venueId INT NOT NULL,
  FOREIGN KEY (venueId) REFERENCES Venue(id)
);

-- Seat table
CREATE TABLE Seat (
  id INT PRIMARY KEY AUTO_INCREMENT,
  seatNumber VARCHAR(10) NOT NULL,
  venueId INT NOT NULL,
  FOREIGN KEY (venueId) REFERENCES Venue(id)
);

-- Ticket table
CREATE TABLE Ticket (
  id INT PRIMARY KEY AUTO_INCREMENT,
  eventId INT NOT NULL,
  userId INT NOT NULL,
  seatId INT NOT NULL,
  FOREIGN KEY (eventId) REFERENCES Event(id),
  FOREIGN KEY (userId) REFERENCES User(id),
  FOREIGN KEY (seatId) REFERENCES Seat(id)
);

-- Order table
CREATE TABLE `Order` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  userId INT NOT NULL,
  ticketId INT NOT NULL,
  orderDate DATE NOT NULL,
  FOREIGN KEY (userId) REFERENCES User(id),
  FOREIGN KEY (ticketId) REFERENCES Ticket(id)
);