<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.pethub.model.User" %>
<%@ page import="com.pethub.model.AdoptionRequest" %>
<%@ page import="java.util.List" %>
<%
String contextPath = request.getContextPath();
HttpSession dashboardSession = request.getSession(false);
User loggedUser = (dashboardSession != null) ? (User) dashboardSession.getAttribute("loggedInUser") : null;
if (loggedUser == null) {
    response.sendRedirect(contextPath + "/login.jsp");
    return;
}
List<AdoptionRequest> userRequests = (List<AdoptionRequest>) request.getAttribute("userRequests");
if (userRequests == null) {
  response.sendRedirect(contextPath + "/users?action=dashboard");
  return;
}
%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My dashboard | PetHub</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/user-dashboard.css" />
  </head>
  <body>
    <header class="site-header">
      <a class="brand" href="<%= contextPath %>/index.jsp"
        ><span class="brand-mark">P</span
        ><span>Pet<span class="brand-accent">Hub</span></span></a
      >
      <nav class="site-nav">
        <a href="<%= contextPath %>/pets">Find a pet</a
        ><a class="active" href="<%= contextPath %>/users?action=dashboard"
          >My dashboard</a
        ><a class="nav-button" href="<%= contextPath %>/logout">Sign out</a>
      </nav>
    </header>
    <main class="dashboard-wrap">
      <div class="welcome-row">
        <div>
          <p class="eyebrow">Your PetHub space</p>
          <h1>Welcome back,<br /><em><%= loggedUser.getName() %></em></h1>
          <p>Manage your requests and profile details here.</p>
        </div>
      </div>
      <section class="dashboard-grid">
        <div class="panel">
          <div class="panel-heading">
            <div>
              <p class="eyebrow">Your activity</p>
              <h2>Adoption requests</h2>
            </div>
            <a href="<%= contextPath %>/pets">Find another pet &rarr;</a>
          </div>
          <% if (userRequests == null || userRequests.isEmpty()) { %>
            <p>No adoption requests to show yet.</p>
          <% } else { %>
            <div class="request-list">
              <% for (AdoptionRequest adoptionRequest : userRequests) { %>
                <div class="request-item">
                  <div class="request-main">
                    <div class="request-heading">
                      <strong>Pet ID: <%= adoptionRequest.getPetId() %></strong>
                      <span class="request-date"><%= adoptionRequest.getRequestDate() %></span>
                    </div>
                    <p><%= adoptionRequest.getReason() %></p>
                    <% if (adoptionRequest.getAdminRemark() != null
                            && !adoptionRequest.getAdminRemark().isBlank()) { %>
                      <small class="admin-note">Admin note: <%= adoptionRequest.getAdminRemark() %></small>
                    <% } %>
                  </div>
                  <span class="request-status <%= adoptionRequest.getStatus() == null
                      ? "pending" : adoptionRequest.getStatus().toLowerCase() %>">
                    <%= adoptionRequest.getStatus() == null ? "Pending" : adoptionRequest.getStatus() %>
                  </span>
                </div>
                <% if ("Approved".equalsIgnoreCase(adoptionRequest.getStatus())) { %>
                  <div class="approval-notice">
                    <h3>&#9989; Your adoption request has been approved!</h3>
                    <p>Our team will contact you soon regarding the next steps.</p>
                    <div class="contact-details">
                      <span><strong>Contact:</strong> +91 XXXXX XXXXX</span>
                      <span><strong>Email:</strong>
                        <a href="mailto:pethub@gmail.com">pethub@gmail.com</a>
                      </span>
                    </div>
                  </div>
                <% } %>
              <% } %>
            </div>
          <% } %>
        </div>
      </section>
    </main>
    <script src="<%= contextPath %>/js/user-dashboard.js"></script>
  </body>
</html>
