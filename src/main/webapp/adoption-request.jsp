<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pethub.model.User" %>
<%@ page import="java.sql.Date"%>
<%
    String contextPath = request.getContextPath();
    HttpSession requestSession = request.getSession(false);
    User loggedUser = (requestSession != null) ? (User) requestSession.getAttribute("loggedInUser") : null;
    if (loggedUser == null) {
        response.sendRedirect(contextPath + "/login.jsp");
        return;
    }
    String petId = request.getParameter("petId") == null ? "" : request.getParameter("petId");
    String petName = request.getParameter("petName") == null ? "Your chosen pet" : request.getParameter("petName");
    String petBreed = request.getParameter("petBreed") == null ? "" : request.getParameter("petBreed");
    String petImage = request.getParameter("petImage") == null ? "" : request.getParameter("petImage");
    String userId = String.valueOf(loggedUser.getUserId());
%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Adoption request | PetHub</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/adoption-request.css" />
  </head>
  <body>
    <header class="site-header">
      <a class="brand" href="<%= contextPath %>/index.jsp"><span class="brand-mark">P</span><span>Pet<span class="brand-accent">Hub</span></span></a>
      <nav class="site-nav">
        <a href="<%= contextPath %>/pets">Back to pets</a>
        <a class="nav-button" href="<%= contextPath %>/users?action=dashboard">My dashboard</a>
      </nav>
    </header>
    <main class="request-wrap">
      <div class="request-intro">
        <p class="eyebrow">The first hello</p>
        <h1>Tell us about<br /><em>your home.</em></h1>
        <p>The more we know, the better we can help make a thoughtful match.</p>
        <div class="selected-pet">
          <% if (!petImage.isEmpty()) { %><img src="<%= contextPath %>/images/<%= petImage %>" alt="<%= petName %>" /><% } else { %><span>&#128062;</span><% } %>
          <div><small>Applying to adopt</small><strong><%= petName %></strong><small><%= petBreed %> &middot; Pet ID #<%= petId %></small></div>
        </div>
      </div>
      <form id="adoption-form" class="form-card" method="post" action="<%= contextPath %>/adoptionrequests">
        <input type="hidden" name="action" value="add" />
        <input type="hidden" name="userId" value="<%= userId %>" />
        <input type="hidden" name="petId" value="<%= petId %>" />
        <input type="hidden" name="requestDate" value="<%= Date.valueOf(java.time.LocalDate.now()) %>" />
        <label>Why would you like to adopt <%= petName %>?<textarea name="reason" rows="7" required></textarea></label>
        <label class="check-label"><input type="checkbox" required /> I understand that submitting a request does not guarantee adoption.</label>
        <button class="button button-primary" type="submit">Send adoption request <span>&rarr;</span></button>
      </form>
    </main>
    <script src="<%= contextPath %>/js/adoption-request.js"></script>
  </body>
</html>