# 🎟️ Veloura Cinema Ticket System – JSP & Servlets

Veloura Cinema Ticket is a web-based movie ticket booking system developed using JSP, Servlets, and MySQL. The system allows users to view available movies, select showtimes, and make bookings in a streamlined, user-friendly interface. It is designed for cinema staff and customers to manage movie information and seat reservations efficiently.

---

## 📖 Project Overview


**Veloura Cinema Ticket** is a web-based Cinema Ticket Management System developed to enhance and simplify the ticketing experience for both customers and cinema staff. The system resolves common issues in traditional ticketing methods, such as long queues, double bookings, and outdated seat availability displays.

This digital solution provides:
- 🎞️ An intuitive interface for browsing movies and showtimes  
- 🪑 Real-time seat selection and booking  
- 💳 Secure and flexible payment handling  
- 👤 User account functionality for easier ticket management  
- 🛠️ Admin tools for cinema staff to manage movie listings and schedules


### 🎯 Purpose of the System

- ✅ Allow users to **modify their bookings after payment**, offering flexibility for last-minute changes without needing to cancel and rebook.
- ⏳ Eliminate pressure from **time-limited payment sessions**, making the system more accessible — especially for older users or those unfamiliar with online payments.
- 🧾 Reduce long queues at cinema counters by encouraging **smooth, reliable online booking**, improving customer satisfaction and easing staff workload.


Overall, the system bridges the gap between traditional cinema experiences and modern technology, creating a faster, smarter, and more enjoyable movie-going experience.

---

## 👥 Group Members & Responsibilities

| Name                                  | Modules Responsibility                            | Role & Responsibility                            |
|---------------------------------------|---------------------------------------------------|--------------------------------------------------|
| Muhamad Noraiman Karami Bin Rahim     | Booking Management Module                         | Backend Developer – JSP, Servlets, Movie module  |
| Siti Fikriyah Binti I.R. Abdul Khawi  | Movies Management Module                          | Frontend Developer – HTML/CSS/JS, UI Design      |
| Nur Alieya Natasya Binti Azha         | User Management Module, Support Management Module | Database Admin – MySQL, ERD, SQL scripts         |

---

## ⚙️ Technologies Used

- Java (JDK 17)
- JSP (JavaServer Pages)
- Servlet
- HTML, CSS, JavaScript
- MySQL
- Apache Tomcat 9

---

## ✨ Features

### 🛡️ Admin
- 🔐 Secure login with admin credentials
- 👥 Add and delete staff accounts (user management)
- 🎬 Manage movie listings (add/edit/delete)  
- 📅 Configure showtimes and assign screens  

### 🧑‍💼 Staff
- 🗓️ Update movie details  
- 📥 Assist with pay at counter ticket bookings  

### 🎟️ Customer
- 🎥 Browse movie catalog with posters and descriptions
- ⏰ View available showtimes and durations
- 🪑 Select and reserve seats in real-time
- 💳 Book tickets securely 
- 🔄 Edit seat bookings after payment (only if more than 3 hours before showtime)
- ✉️ View confirmation details (optional email or on-screen)

---

## 🛠️ How to Run the Project Locally

1. **Clone the repository on Netbeans:**
   ```
    https://github.com/aimankrmi/veloura.git
   ```

2. **Open the project in NetBeans.**
   - File → Open Project → Select ``veloura``

3. **Import Dependencies**
 Ensure the following libraries are added to your project:

- `JSTL.jar`
- [`mysql-connector-j-9.2.0.jar`](https://dev.mysql.com/downloads/file/?id=538917)
- [`javax.mail.jar`](https://github.com/javaee/javamail/releases/download/JAVAMAIL-1_6_2/javax.mail.jar)
- [`activation.jar`](https://repo1.maven.org/maven2/javax/activation/activation/1.1/activation-1.1.jar)

4. **Configure MySQL database:**
   - Run the SQL dump: veloura_db.sql (db/veloura_db.sql) to create database cinema_db tables and sample data.
  
    Update the DBUtil.java file with your actual MySQL credentials:
   ```
    - String url = mysql://localhost:3306/veloura
    - String username=databaseuser
    - String password=yourpassword
   ```
      
6. **Deploy & Run the project using Apache Tomcat**
     Open your browser and go to:
     ```
      http://localhost:8080/veloura
     ```
---

## 📂 Project Structure

```plaintext
VelouraCinemaTicket/
├── Web Pages/
│   ├── index.jsp
│   ├── META-INF/
│   │   └── context.xml
│   ├── WEB-INF/
│   │   └── web.xml
│   │   ├── views/
│   │   │   ├── admin/
│   │   │   │   └── admin-dashboard.jsp, manage-member.jsp, manage-staff.jsp
│   │   │   ├── booking/
│   │   │   │   └── edit-booking.jsp, success-edit.jsp
│   │   │   ├── member/
│   │   │   │   └── booking-history.jsp, member-dashboard.jsp
│   │   │   ├── payment/
│   │   │   │   └── payment.jsp, review-payment.jsp, seccuess-payment.jsp
│   │   │   ├── staff/
│   │   │   │   └── manage-movies.jsp, staff-dashboard.jsp, manage-support.jsp
│   │   │   ├── user/
│   │   │   │   └── login.jsp, register.jsp, support.jsp
│   │   │   ├── booking/
│   │   │   │   └── booking.jsp
│   │   │   └── error/
│   │   │       └── 409.jsp, error.jsp
│   │   └── includes/
│   │       └── footer.jsp, header.jsp, seat-legend.jsp, seat-row.jsp
│   ├── assets/
│   │   ├── css/
│   │   │   └── booking.css, footer.css, header.css, movie.css, payment.css, styles.css
│   │   ├── icon/
│   │   │   └── 
│   │   ├── images/
│   │   │   └──
│   │   └── js/
│   │       └── booking.js, edit-booking.js, scripts.js
│   │     
├── Source Packages/
│   ├── com.velouracinema.controller.booking/
│   │   └── BookingServlet.java, EditBookingServlet.java, ProcessEditBookingServlet.java, ReviewPaymentServlet.java
│   │   
│   ├── com.velouracinema.controller.home/
│   │   └── HomeServlet.java
│   │
│   ├── com.velouracinema.controller.support/
│   │   └── SupportServlet.java
│   │   
│   ├── com.velouracinema.controller.movie/
│   │   └── ManageMovieServlet.java
│   │   
│   ├── com.velouracinema.controller.payment/
│   │   └── PaymentProcessServlet.java, PaymentServlet.java, UpdatePaymentServlet.java
│   │   
│   ├── com.velouracinema.controller.user/
│   │   └── AdminServlet.java, LoginServlet.java, LogoutServlet.java, ManageMemberServlet.java, ManageStaffServlet.java, MemberServlet.java, RegisterServlet.java, StaffServlet.java
│   │   
│   ├── com.velouracinema.dao.booking/
│   │   └── BookingDAO.java, seatDAO.java, ShowtimeDAO.java
│   │   
│   ├── com.velouracinema.dao.movie/
│   │   └── MovieDAO.java, TopMovieDAO.java
│   │   
│   ├── com.velouracinema.dao.payment/
│   │   └── PaymentDAO.java
│   │   
│   ├── com.velouracinema.dao.user/
│   │   └── UserDAO.java, SupportDAO.java
│   │   
│   ├── com.velouracinema.listener/
│   │   └── BookingCleanupTaskListener.java
│   │   
│   ├── com.velouracinema.model/
│   │   └── Booking.java, Movie.java, Payment.java, Seat.java, Showtime.java, SupportMessage.java, TopMovie.java, User.java, SupportRequest.java
│   │   
│   ├── com.velouracinema.util/
│   │   └── DBUtil.java, Utils.java, EmailUtils.java
│   │   
├── db/
│   └── veloura_db.sql
│   
└── README.md
```

---

## 🔒 Security Notes

- Passwords are hashed using SHA-256 before storage.
- Auto-expiry protect against unauthorized access.
- Admin-only operations are protected from frontend access.

--- 

## 🌐 Live Demo 

> Deployed at: [https://velouracinema-demo.vercel.app](https://velouracinema-demo.vercel.app)  (Not Available Yet)

---

## 📃 License

This project is for academic purposes only. No license.

---
