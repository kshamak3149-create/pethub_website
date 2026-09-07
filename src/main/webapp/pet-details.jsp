<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pethub.model.User" %>
<%@ page import="com.pethub.model.Pet" %>
<% String contextPath = request.getContextPath(); Pet pet = (Pet) request.getAttribute("pet"); if (pet == null) { response.sendRedirect(contextPath + "/pets"); return; } HttpSession detailSession = request.getSession(false); User loggedUser = (detailSession != null) ? (User) detailSession.getAttribute("loggedInUser") : null; %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title><%= pet.getName() %>'s story | PetHub</title>
		<link rel="stylesheet" href="<%= contextPath %>/css/pet-details.css" />
	</head>
	<body>
		<header class="site-header">
			<a class="brand" href="<%= contextPath %>/index.jsp"><span class="brand-mark">P</span><span>Pet<span class="brand-accent">Hub</span></span></a>
			<button class="menu-toggle" type="button">&#9776;</button>
			<nav class="site-nav">
				<a href="<%= contextPath %>/index.jsp">Home</a>
				<a class="active" href="<%= contextPath %>/pets">Find a pet</a>
				<% if (loggedUser != null) { %>
					<a href="<%= contextPath %>/users?action=dashboard">My dashboard</a>
					<a class="nav-button" href="<%= contextPath %>/logout">Sign out</a>
				<% } else { %>
					<a class="nav-button" href="<%= contextPath %>/login.jsp">Sign in</a>
				<% } %>
			</nav>
		</header>
		<main class="detail-wrap">
			<a class="back-link" href="<%= contextPath %>/pets">&larr; Back to all pets</a>
			<section class="detail-hero">
				<div class="detail-image">
					<% if (pet.getImage() != null && !pet.getImage().isEmpty()) { %>
						<img src="<%= contextPath %>/images/<%= pet.getImage() %>" alt="<%= pet.getName() %>" />
					<% } else { %>
						<span>&#128062;</span><small>your image will appear here</small>
					<% } %>
					<b>Available for adoption</b>
				</div>
				<div class="detail-copy">
					<p class="eyebrow">A gentle soul in need of a home</p>
					<h1><%= pet.getName() %></h1>
					<p class="detail-subtitle"><%= pet.getBreed() %> / <%= pet.getSpecies() %></p>
					<p class="detail-description"><%= pet.getDescription() == null ? "Every rescue pet deserves a patient introduction and a home where they can feel safe, cared for, and loved." : pet.getDescription() %></p>
					<div class="detail-tags"><span><%= pet.getHealthStatus() == null ? "Health information available" : pet.getHealthStatus() %></span><span><%= pet.getAdoptionStatus() %></span><span><%= pet.getAge() %> years / <%= pet.getSize() %></span><span><%= pet.getLocation() %></span></div>
					<a class="button button-primary" href="<%= contextPath %>/adoption-request.jsp?petId=<%= pet.getPetId() %>&petName=<%= java.net.URLEncoder.encode(pet.getName(), "UTF-8") %>&petBreed=<%= java.net.URLEncoder.encode(pet.getBreed(), "UTF-8") %>&petImage=<%= pet.getImage() == null ? "" : pet.getImage() %>">Start an adoption request <span>&rarr;</span></a>
				</div>
			</section>
		</main>
		<script src="<%= contextPath %>/js/pet-details.js"></script>
	</body>
</html>