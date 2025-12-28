# Movie Ticket System

A comprehensive desktop application for managing movie ticket bookings, built with JavaFX. This system provides a user-friendly GUI for both customers and administrators to manage movies, shows, reservations, and payments.

## Features

### User Features
- **Browse Movies**: View available movies with details, ratings, and categories
- **Seat Selection**: Interactive seat map for selecting preferred seats
- **Reservation Management**: Create and manage movie reservations
- **Payment Processing**: Secure payment processing for ticket purchases
- **Ticket Generation**: Automatic PDF ticket generation after successful payment
- **User Profile**: Manage personal information and view booking history
- **Cinema Information**: View cinema details and hall information

### Admin Features
- **Movie Management**: Add and remove movies from the system
- **Show Management**: Create and manage movie shows with time slots
- **Hall Management**: Configure cinema halls and seating arrangements
- **Reports**: Generate reports on shows, reservations, and sales
- **System Administration**: Full control over the movie ticket system

## Technology Stack

- **Java**: 21
- **JavaFX**: 21.0.9 (GUI Framework)
- **Maven**: Build and dependency management
- **Gson**: 2.10.1 (JSON data handling)
- **iTextPDF**: 5.5.13.3 (PDF ticket generation)

## Project Structure

```
MovieTicketSystem/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── movietickets/
│       │           ├── Main.java                 # Console demo application
│       │           ├── model/                     # Data models
│       │           │   ├── Admin.java
│       │           │   ├── Cinema.java
│       │           │   ├── Hall.java
│       │           │   ├── Movie.java
│       │           │   ├── Payment.java
│       │           │   ├── Person.java
│       │           │   ├── Reservation.java
│       │           │   ├── Seat.java
│       │           │   ├── Show.java
│       │           │   ├── Ticket.java
│       │           │   └── User.java
│       │           ├── repository/                # Data access layer
│       │           │   ├── AdminRepository.java
│       │           │   ├── HallRepository.java
│       │           │   ├── MovieRepository.java
│       │           │   ├── PaymentRepository.java
│       │           │   ├── ReservationRepository.java
│       │           │   ├── SeatRepository.java
│       │           │   ├── ShowRepository.java
│       │           │   ├── TicketRepository.java
│       │           │   └── UserRepository.java
│       │           ├── ui/                        # User interface
│       │           │   ├── MainApp.java          # JavaFX application entry point
│       │           │   └── Controller/            # FXML controllers
│       │           └── util/
│       │               └── JsonHandler.java       # JSON file operations
│       └── resources/
│           └── com/
│               └── movietickets/
│                   └── resources/
│                       ├── *.fxml                # FXML UI layouts
│                       ├── photos/                # Image resources
│                       └── files/                 # Additional resources
├── data/                                          # JSON data storage
│   ├── admins.json
│   ├── halls.json
│   ├── movies.json
│   ├── payments.json
│   ├── reservations.json
│   ├── seats.json
│   ├── shows.json
│   ├── tickets.json
│   └── users.json
└── pom.xml                                        # Maven configuration
```

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 21 or higher
- **Maven**: Version 3.6.0 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (optional but recommended)

## Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd MovieTicketSystem
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   
   Using Maven:
   ```bash
   mvn javafx:run
   ```
   
   Or run the main class directly:
   ```bash
   mvn exec:java -Dexec.mainClass="com.movietickets.ui.MainApp"
   ```

## Usage

### Starting the Application

1. Run the application using Maven or your IDE
2. The login screen will appear
3. Login as a user or admin to access respective features

### User Workflow

1. **Browse Movies**: Navigate through available movies
2. **Select Show**: Choose a show time for your preferred movie
3. **Select Seats**: Pick your seats from the interactive seat map
4. **Make Reservation**: Confirm your seat selection
5. **Process Payment**: Complete the payment for your tickets
6. **Download Ticket**: Receive a PDF ticket after successful payment

### Admin Workflow

1. **Login as Admin**: Use admin credentials to access admin panel
2. **Manage Movies**: Add new movies or remove existing ones
3. **Manage Shows**: Create shows for movies with specific time slots
4. **View Reports**: Generate and view system reports

## Data Storage

The application uses JSON files for data persistence. All data is stored in the `data/` directory:

- `users.json`: User accounts and information
- `admins.json`: Admin accounts
- `movies.json`: Movie catalog
- `halls.json`: Cinema hall configurations
- `seats.json`: Seat information
- `shows.json`: Show schedules
- `reservations.json`: Booking reservations
- `payments.json`: Payment records
- `tickets.json`: Generated tickets

## Acknowledgments

- JavaFX community for excellent documentation
- Maven for dependency management
- Google Gson for JSON processing
- iTextPDF for PDF generation capabilities

## Future Enhancements

- [ ] Online payment gateway integration
- [ ] Email notifications for bookings
- [ ] Mobile application version
- [ ] Advanced reporting and analytics
- [ ] Multi-cinema support
- [ ] Loyalty program integration


