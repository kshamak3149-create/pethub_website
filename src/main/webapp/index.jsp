<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.pethub.model.User" %>

<%
String contextPath = request.getContextPath();

HttpSession indexSession = request.getSession(false);

User loggedUser = (indexSession != null)
        ? (User) indexSession.getAttribute("loggedInUser")
        : null;
%>

<!doctype html>

<html lang="en">

<head>

    <meta charset="UTF-8" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>PetHub | Find your new best friend</title>

    <link rel="stylesheet"
          href="<%= contextPath %>/css/index.css" />

</head>

<body>

    <header class="site-header">

        <a class="brand"
           href="<%= contextPath %>/index.jsp">

            <span class="brand-mark">P</span>

            <span>Pet<span class="brand-accent">Hub</span></span>

        </a>

        <button class="menu-toggle">&#9776;</button>

        <nav class="site-nav">

            <a class="active"
               href="<%= contextPath %>/index.jsp">
                Home
            </a>

            <a href="<%= contextPath %>/pets">
                Find a pet
            </a>

            <% if (loggedUser != null) { %>

                <a href="<%= contextPath %>/users?action=dashboard">
                    My dashboard
                </a>

                <a class="nav-button"
                   href="<%= contextPath %>/logout">
                    Sign out
                </a>

            <% } else { %>

               <a href="<%= contextPath %>/login.jsp">Sign in</a>

            <% } %>

            <a href="<%= contextPath %>/admin-login.jsp">
                Admin login
            </a>

            <% if (loggedUser == null) { %>

                <a href="<%= contextPath %>/register.jsp">
                    Join PetHub
                </a>

            <% } %>

        </nav>

    </header>

    <main>

        <section class="hero-shell">

            <div class="hero-copy">

                <p class="eyebrow">
                    A little more love, every day
                </p>

                <h1>
                    Meet the one<br />
                    <em>who gets you.</em>
                </h1>

                <p class="hero-text">
                    PetHub brings caring people and wonderful rescue pets together. Take
                    a look around and start your adoption story.
                </p>

                <div class="hero-actions">

                    <a class="button button-primary"
                       href="<%= contextPath %>/pets">
                        Explore pets <span>&rarr;</span>
                    </a>

                    <a class="text-link"
                       href="<%= contextPath %>/register.jsp">
                        Create an account
                    </a>

                </div>

                <div class="hero-note">

                    <span class="note-icon">&#10003;</span>

                    <span>
                        <strong>Every pet deserves a home.</strong><br />
                        Every home deserves a little more joy.
                    </span>

                </div>

            </div>

            <div class="hero-art">

                <div class="sun-disc"></div>

                <div class="pet-placeholder large-placeholder">

                    <span>&#128062;</span>
                    <small>your next best friend</small>

                </div>

                <div class="floating-card card-one">

                    <strong>Pets</strong>
                    <span>waiting to be met</span>

                </div>

                <div class="floating-card card-two">

                    <span class="tiny-heart">&#9829;</span>
                    <span>Adopt, don't shop</span>

                </div>

            </div>

        </section>

        <section class="intro-section">

            <div>

                <p class="eyebrow">
                    Why PetHub
                </p>

                <h2>
                    Good matches<br />
                    start here.
                </h2>

            </div>

            <p class="section-lead">
                From the first curious hello to the day they finally come home, PetHub
                makes adoption feel simple, personal, and full of possibility.
            </p>

        </section>

        <section class="feature-grid">

            <article class="feature-card">

                <span class="feature-number">01</span>

                <h3>Browse with heart</h3>

                <p>
                    Get to know pets by personality, needs, and the kind of home they
                    are looking for.
                </p>

                <a href="<%= contextPath %>/pets">
                    Meet the pets &rarr;
                </a>

            </article>

            <article class="feature-card featured">

                <span class="feature-number">02</span>

                <h3>Share your story</h3>

                <p>
                    Tell us about yourself and the home you can offer. The right match
                    is worth taking time for.
                </p>

                <a href="<%= contextPath %>/register.jsp">
                    Get started &rarr;
                </a>

            </article>

            <article class="feature-card">

                <span class="feature-number">03</span>

                <h3>Stay in the loop</h3>

                <p>
                    Track your adoption requests and keep every important update in one
                    calm, clear place.
                </p>

                <a href="<%= contextPath %>/login.jsp">
                    View your dashboard &rarr;
                </a>

            </article>

        </section>

    </main>

    <footer class="site-footer">

        <span>PetHub</span>

        <span>
            Made for people who believe every pet has a story.
        </span>

        <span>2024</span>

    </footer>

    <script src="<%= contextPath %>/js/index.js"></script>

</body>

</html>