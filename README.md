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

| Name                                  | Role & Responsibilities                          |
|---------------------------------------|--------------------------------------------------|
| Muhamad Noraiman Karami Bin Rahim     | Backend Developer – JSP, Servlets, Movie module  |
| Siti Fikriyah Binti I.R. Abdul Khawi  | Frontend Developer – HTML/CSS/JS, UI Design      |
| Nur Alieya Natasya Binti Azha         | Database Admin – MySQL, ERD, SQL scripts         |

---

## ⚙️ Technologies Used

- Java (JDK 17)
- JSP (JavaServer Pages)
- Servlet
- HTML, CSS, JavaScript
- MySQL
- Apache Tomcat 9

---

## 🛠️ How to Run the Project Locally

1. **Clone the repository:**
   ```bash
   git clone put here
   ```

2. **Open the project in NetBeans.**
   - File → Open Project → Select ``veloura``

3. **Import Dependencies**
   Ensure the following libraries are added to your project:
     ```
     - JSTL.jar
     - mysql-connector-j-9.2.0.jar
      ```
4. **Configure MySQL database:**
   - Create a database: ``cinema_db``
   - Run the SQL dump: filename.sql db/filename.sql to create tables and sample data.
  
    Update the DBUtil.java file with your actual MySQL credentials:
   ```
    - jdbc.url=jdbc:mysql://localhost:3306/veloura
    - jdbc.username=databaseuser
    - jdbc.password=yourpassword
   ```
      
6. **Deploy & Run the project using Apache Tomcat**
     Open your browser and go to:
     ```
      http://localhost:8080/Veloura
     ```
---

## 🧪 Features

- 🎬 View list of all movies with posters
- 📅 Select available show dates and times
- 🪑 View available seats per screening
- 📝 Book tickets 
- 🔐 Admin login to manage movie listings

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
│   │   │       └── admin-dashboard.jsp, manage-member.jsp, manage-staff.jsp
│   │   │   ├── booking/
│   │   │       └── edit-booking.jsp, success-edit.jsp
│   │   │   ├── member/
│   │   │       └── booking-history.jsp, member-dashboard.jsp
│   │   │   ├── payment/
│   │   │       └── payment.jsp, review-payment.jsp, seccuess-payment.jsp
│   │   │   ├── staff/
│   │   │       └── manage-movies.jsp, staff-dashboard.jsp
│   │   │   ├── user/
│   │   │       └── login.jsp, register.jsp
│   │   │   ├── booking/
│   │   │       └── booking.jsp
│   │   │   ├── error/
│   │   │       └── 409.jsp, error.jsp
│   ├── assets/
│   │   ├── css/
│   │       └── booking.css, footer.css, header.css, movie.css, payment.css, styles.css
│   │   ├── icon/
│   │       └── 
│   │   ├── images/
│   │       └──
│   │   ├── js/
│   │       └── booking.js, edit-booking.js, scripts.js
│   ├── includes/
│       └── footer.jsp, header.jsp, seat-legend.jsp, seat-row.jsp
│   │     
├── Source Packages/
│   ├── com.velouracinema.controller.booking/
│   │   └── BookingServlet.java, EditBookingServlet.java, ProcessEditBookingServlet.java, ReviewPaymentServlet.java
│   │   
│   ├── com.velouracinema.controller.home/
│   │   └── HomeServlet.java
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
│   │   └── UserDAO.java
│   │   
│   ├── com.velouracinema.listener/
│   │   └── BookingCleanupTaskListener.java
│   │   
│   ├── com.velouracinema.model/
│   │   └── Booking.java, Movie.java, Payment.java, Seat.java, Showtime.java, SupportMessage.java, TopMovie.java, User.java
│   │   
│   ├── com.velouracinema.util/
│   │   └── DBUtil.java, Utils.java
│   │   
├── db/
│   └── cinema_db.sql
│   
└── README.md
```

---

## 🌐 Live Demo 

> If deployed: [https://velouracinema-demo.vercel.app](https://velouracinema-demo.vercel.app)  
> *(Replace with actual link if deployed)*

---

## 📃 License

This project is for academic purposes only. No license.

---
