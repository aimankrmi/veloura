<%-- 
    Document   : staffDashboard
    Created on : Jun 2, 2025, 5:21:07 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <title>Staff Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&family=Vibur&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/header.css"/>
        <link rel="stylesheet" href="assets/css/footer.css"/>
    </head>
    <body>
        <jsp:include page="../../../includes/header.jsp" flush="true"/>


        <div class="container py-5">
            <c:if test="${param.error!=null}">
                <div class="alert alert-danger my-2" role="alert">
                    <c:out value="${param.error}"/>
                </div>
            </c:if>
            <c:if test="${param.message!=null}">
                <div class="alert alert-success my-2" role="alert">
                    <c:out value="${param.message}"/>
                </div>
            </c:if>
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

                <!-- View Support Requests -->
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

                <!-- View Profile -->
                <div class="col-md-4">
                    <div class="card text-bg-dark h-100 shadow-lg rounded-4">
                        <div class="card-body text-center">
                            <i class="fas fa-film fa-3x mb-3 text-warning"></i>
                            <h5 class="card-title">Profile</h5>
                            <p class="card-text">View or edit profile.</p>

                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#viewProfileModal">
                                View
                            </button>

                            <!-- Modal -->
                            <form action="${pageContext.request.contextPath}/updateStaff" method="POST" >
                                <input type="hidden" name="id" value="${staff.id}">
                                <div class="modal fade" id="viewProfileModal" tabindex="-1"
                                     aria-labelledby="viewProfileModalLabel" aria-hidden="true">
                                    <div class="modal-dialog model-dialog-centered modal-dialog-scrollable">
                                        <div class="modal-content bg-dark">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5 text-white" id="viewProfileModalLabel ">Your
                                                    Account</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close" style="background-color: var(--color-light-gold);"></button>
                                            </div>
                                            <div class="modal-body text-white">


                                                <!-- staff NAME -->
                                                <div class="mb-3">
                                                    <label for="staffNameFormControlInput" class="form-label">
                                                        Name</label>
                                                    <input type="text" class="form-control bg-dark text-white"
                                                           id="staffNameFormControlInput" name="staffName" value="<c:out value='${staff.name}'/>" required>
                                                </div>

                                                <!-- USERNAME -->
                                                <div class="mb-3">
                                                    <label for="staffUsernameFormControlInput" class="form-label">Username</label>
                                                    <input type="text" class="form-control bg-dark text-white"
                                                           id="staffUsernameFormControlInput" name="staffUsername" value="<c:out value='${staff.username}'/>" required>
                                                </div>

                                                <!-- PASSWORD -->
                                                <div class="mb-3">
                                                    <label for="staffPasswordFormControlInput" class="form-label">Password (only if you want to change)</label>
                                                    <input type="text" class="form-control bg-dark text-white"
                                                           id="staffPasswordFormControlInput" name="staffPassword" value="">
                                                </div>

                                                <!-- EMAIL -->
                                                <div class="mb-3">
                                                    <label for="staffEmailFormControlInput" class="form-label">Email</label>
                                                    <input type="text" class="form-control bg-dark text-white"
                                                           id="staffEmailFormControlInput" name="staffEmail" value="<c:out value='${staff.email}'/>" required>

                                                </div>

                                                <!-- PHONE NO -->
                                                <div class="mb-3">
                                                    <label for="staffPhoneNoFormControlInput" class="form-label">
                                                        Phone No</label>
                                                    <input type="text" class="form-control bg-dark text-white"
                                                           id="staffPhoneNoFormControlInput" name="staffPhoneNo" value="<c:out value='${staff.phoneNo}'/>" required>
                                                </div>



                                            </div>
                                            <div class="modal-footer">
                                                <input type="submit" class="btn btn-primary" value="Save changes">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../../../includes/footer.jsp" flush="true"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
