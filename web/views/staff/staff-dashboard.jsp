<%-- 
    Document   : staffDashboard
    Created on : Jun 2, 2025, 5:21:07 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
       
        <meta charset="UTF-8">
        <title>Staff Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;700&family=Vibur&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/header.css"/>
        <link rel="stylesheet" href="assets/css/footer.css"/>
    </head>
    <body>
        <jsp:include page="../../includes/header.jsp" flush="true"/>

         
        <div class="container py-5">
            <h1 class="glow-gold display-4 mb-4" style="text-align: left;">Staff Dashboard</h1>
            <p class="lead">Welcome, <strong>Staff</strong>! Here's your workspace.</p>

            <div class="row g-4 mt-4">
                <!-- Card: Review Payments -->
                <div class="col-md-4">
                    <div class="card text-bg-dark h-100 shadow-lg rounded-4">
                        <div class="card-body text-center">
                            <i class="fas fa-money-check-alt fa-3x mb-3 text-warning"></i>
                            <h5 class="card-title">Review Payments</h5>
                            <p class="card-text">View and verify customer payments.</p>
                            <a href="${pageContext.request.contextPath}/reviewPayment" class="btn btn-warning text-dark rounded-pill mt-3 px-4">Go</a>
                        </div>
                    </div>
                </div>

                <!-- Card: Manage Movies -->
                <div class="col-md-4">
                    <div class="card text-bg-dark h-100 shadow-lg rounded-4">
                        <div class="card-body text-center">
                            <i class="fas fa-film fa-3x mb-3 text-warning"></i>
                            <h5 class="card-title">Manage Movies</h5>
                            <p class="card-text">Add, edit or remove movie listings.</p>
                            <a href="${pageContext.request.contextPath}/viewMovies" class="btn btn-warning text-dark rounded-pill mt-3 px-4">Go</a>
                        </div>
                    </div>
                </div>

                <!-- Optional Card: View Support Requests -->
                <div class="col-md-4">
                    <div class="card text-bg-dark h-100 shadow-lg rounded-4">
                        <div class="card-body text-center">
                            <i class="fas fa-headset fa-3x mb-3 text-warning"></i>
                            <h5 class="card-title">Support Requests</h5>
                            <p class="card-text">Check tickets submitted by users.</p>
                            <a href="viewSupport.jsp" class="btn btn-warning text-dark rounded-pill mt-3 px-4">Go</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../../includes/footer.jsp" flush="true"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
