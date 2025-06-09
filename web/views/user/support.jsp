<%-- 
    Document   : support
    Created on : May 26, 2025, 3:27:08 PM
    Author     : User
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Support | Cinema Ticket System</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&family=Vibur&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/header.css"/>
        <link rel="stylesheet" href="assets/css/footer.css"/>
    </head>
    <body>
        <jsp:include page="../../includes/header.jsp" flush="true"/>

        <div class="container py-5">
            <h1 class="glow-gold mb-4 display-4" style="text-align: left;">Support Center?</h1>

            <!-- Contact Form -->
            <div class="row">
                <div class="col-md-7">
                    <div class="card shadow-lg p-4 border-0 rounded-4">
                        <h4 class="mb-3">Need help? Send us a message.</h4>
                        <form action="submitSupport.jsp" method="post">
                            <div class="mb-3">
                                <label for="name" class="form-label">Your Name</label>
                                <input type="text" name="name" id="name" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email / Username</label>
                                <input type="text" name="email" id="email" class="form-control" required />
                            </div>
                            <div class="mb-3">
                                <label for="issueType" class="form-label">Issue Type</label>
                                <select name="issueType" id="issueType" class="form-select" required>
                                    <option value="">-- Select an Issue --</option>
                                    <option>Login Problem</option>
                                    <option>Ticket Issue</option>
                                    <option>Payment</option>
                                    <option>Other</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="message" class="form-label">Your Message</label>
                                <textarea name="message" id="message" class="form-control" rows="5" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-warning text-dark rounded-pill px-4">
                                <i class="fas fa-paper-plane me-2"></i>Submit
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Support Info Section -->
                <div class="col-md-5">
                    <div class="card bg-dark text-white shadow-lg p-3 rounded-4">
                        <div class="card-body">
                            <h3 class="text-warning mb-4"><i class="fas fa-headset me-2"></i>Support Information</h3>

                            <p class="mb-3"><i class="fas fa-envelope me-2 text-danger"></i><strong>Email:</strong> support@cinematix.com</p>
                            <p class="mb-3"><i class="fas fa-phone me-2 text-success"></i><strong>Phone:</strong> +60 123-456-789</p>
                            <p class="mb-4"><i class="fas fa-clock me-2 text-warning"></i><strong>Response Time:</strong> We reply within 24 hours</p>

                            <hr class="bg-secondary" />

                            <h5 class="text-info mb-3"><i class="fas fa-question-circle me-2"></i>FAQs</h5>
                            <div class="accordion accordion-flush" id="faqAccordion">
                                <div class="accordion-item bg-transparent border-secondary">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed bg-dark text-white" type="button" data-bs-toggle="collapse" data-bs-target="#faq1">
                                            I didn?t receive my ticket email.
                                        </button>
                                    </h2>
                                    <div id="faq1" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                                        <div class="accordion-body text-secondary">
                                            Please check your spam/junk folder. If it's not there, contact us using the form.
                                        </div>
                                    </div>
                                </div>
                                <div class="accordion-item bg-transparent border-secondary">
                                    <h2 class="accordion-header">
                                        <button class="accordion-button collapsed bg-dark text-white" type="button" data-bs-toggle="collapse" data-bs-target="#faq2">
                                            Can I cancel or change my ticket?
                                        </button>
                                    </h2>
                                    <div id="faq2" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                                        <div class="accordion-body text-secondary">
                                            Tickets are non-refundable, but you can contact support for exceptional cases.
                                        </div>
                                    </div>
                                </div>
                                <!-- You can add more FAQs here -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../../includes/footer.jsp" flush="true"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>