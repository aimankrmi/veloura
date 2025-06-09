<%-- 
    Document   : submitSupport
    Created on : May 26, 2025, 3:41:43 PM
    Author     : User
--%>

<%@ page import="com.velouracinema.model.SupportMessage, com.velouracinema.dao.SupportDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String issueType = request.getParameter("issueType");
    String message = request.getParameter("message");

    SupportMessage supportMsg = new SupportMessage();
    supportMsg.setName(name);
    supportMsg.setEmailUsername(email);
    supportMsg.setIssueType(issueType);
    supportMsg.setMessage(message);

    SupportDAO dao = new SupportDAO();
    boolean inserted = dao.insertSupportMessage(supportMsg);

    if (inserted) {
%>
        <script>
            alert("Your support request has been submitted successfully!");
            window.location = "support.jsp";
        </script>
<%
    } else {
%>
        <script>
            alert("Failed to submit. Please try again.");
            window.location = "support.jsp";
        </script>
<%
    }
%>
