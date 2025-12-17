# Gym User Management System

A comprehensive gym management application built with Java Swing, featuring a modern and attractive UI.

## Features

### Core Functionality
- **Dashboard**: Overview of gym statistics with real-time metrics
  - Total members count
  - Daily active members
  - Expiring memberships alerts
  - Revenue tracking

- **Member Management**
  - Add new members with complete details
  - View all members in a searchable table
  - Real-time search functionality
  - Delete members with confirmation
  - Member profile viewer with detailed information

- **Check-In System**
  - Quick member check-in by ID
  - Real-time activity log
  - Timestamp tracking

- **Statistics & Analytics**
  - Visual membership distribution pie chart
  - Monthly revenue trend bar chart
  - Graphical data representation

- **Payment Tracking**
  - Payment history table
  - Revenue summary cards
  - Payment status tracking (Paid/Pending/Overdue)
  - Categorized payments by membership type

## Design Features

- **Modern UI**: Clean, professional interface with teal and green color scheme
- **Responsive Layout**: Adaptable to different screen sizes
- **Smooth Animations**: Hover effects and transitions on interactive elements
- **Visual Hierarchy**: Clear information structure with proper spacing
- **Consistent Styling**: Unified design language throughout the application

## Technology Stack

- **Language**: Java
- **GUI Framework**: Swing
- **Design Pattern**: Singleton (for MemberService)
- **Architecture**: MVC-inspired structure

## Project Structure

```
gym-management/
├── Main.java                 # Application entry point
├── gui/                      # UI components
│   ├── MainFrame.java       # Main application window
│   ├── DashboardPanel.java  # Dashboard view
│   ├── MemberListPanel.java # Member list table
│   ├── AddMemberPanel.java  # Add member form
│   ├── CheckInPanel.java    # Check-in interface
│   ├── StatisticsPanel.java # Charts and analytics
│   ├── PaymentPanel.java    # Payment tracking
│   └── MemberProfilePanel.java # Member profile viewer
├── models/                   # Data models
│   └── Member.java          # Member entity
├── services/                 # Business logic
│   └── MemberService.java   # Member operations
└── utils/                    # Utilities
    └── UIStyles.java        # UI styling constants
```

## Requirements

- Java Development Kit (JDK) 11 or higher
- Java Runtime Environment (JRE) 11 or higher

## How to Run

### Windows

1. Open Command Prompt in the project directory
2. Compile the project:
   ```
   compile.bat
   ```
3. Run the application:
   ```
   run.bat
   ```

Or manually:
```bash
javac -d . Main.java gui/*.java models/*.java services/*.java utils/*.java
java Main
```

### Linux/Mac

1. Open Terminal in the project directory
2. Make scripts executable:
   ```bash
   chmod +x compile.sh run.sh
   ```
3. Compile the project:
   ```bash
   ./compile.sh
   ```
4. Run the application:
   ```bash
   ./run.sh
   ```

Or manually:
```bash
javac -d . Main.java gui/*.java models/*.java services/*.java utils/*.java
java Main
```

## Sample Data

The application comes pre-loaded with 8 sample members to demonstrate all features. You can:
- View them in the Members list
- Search for them by name or email
- Check them in using IDs 1-8
- View their profiles in the Profile section

## Navigation

The application features a sidebar with the following sections:
- 📊 Dashboard - Overview and statistics
- 👥 Members - View and manage all members
- ➕ Add Member - Register new members
- ✅ Check-In - Member check-in system
- 📈 Statistics - Visual analytics and charts
- 💳 Payments - Payment tracking and history
- 👤 Profile - View detailed member profiles

## Key Features by Section

### Dashboard
- Real-time member count
- Active members today
- Membership expiration alerts
- Revenue summary
- Recent activity feed

### Members
- Searchable member list
- Sortable columns
- Delete functionality
- Clean table view

### Add Member
- Form validation
- Multiple membership tiers (Basic, Premium, VIP)
- Success notifications

### Check-In
- ID-based check-in
- Live activity log
- Error handling for invalid IDs

### Statistics
- Interactive pie chart for membership distribution
- Bar chart for revenue trends
- Visual data representation

### Payments
- Comprehensive payment history
- Status indicators (color-coded)
- Revenue summary cards

### Profile
- Detailed member information
- Quick search by ID
- Member status display
- Action buttons (Edit, Renew, Delete)

## Color Scheme

- **Primary**: Teal (#0E7490)
- **Primary Dark**: Dark Teal (#0F4C5C)
- **Secondary**: Green (#22C55E)
- **Accent**: Blue (#3B82F6)
- **Warning**: Amber (#F59E0B)
- **Danger**: Red (#EF4444)
- **Background**: Light Gray (#F9FAFB)

## Future Enhancements

Potential features for future versions:
- Database integration (MySQL/PostgreSQL)
- User authentication and roles
- Workout plan management
- Attendance reports and analytics
- Email notifications
- Equipment inventory tracking
- Trainer scheduling
- Mobile app integration
- Export data to PDF/Excel

## License

This project is open source and available for educational purposes.

## Author

Created as a comprehensive gym management solution with modern UI/UX principles.
