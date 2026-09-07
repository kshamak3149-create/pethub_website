<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.pethub.model.Pet" %>
<%@ page import="com.pethub.model.User" %>
<%@ page import="java.util.List" %>

<%
    List<Pet> pets = (List<Pet>) request.getAttribute("pets");

    if (pets == null) {
        pets = java.util.Collections.emptyList();
    }

    HttpSession petsSession = request.getSession(false);
    User loggedUser = (petsSession != null)
            ? (User) petsSession.getAttribute("loggedInUser")
            : null;
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Find a pet | PetHub</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/pets.css" />
</head>

<body>

<header class="site-header">

    <a class="brand"
       href="<%= request.getContextPath() %>/index.jsp">

        <span class="brand-mark">P</span>

        <span>
            Pet<span class="brand-accent">Hub</span>
        </span>

    </a>

    <button class="menu-toggle">&#9776;</button>

    <nav class="site-nav">

        <a href="<%= request.getContextPath() %>/index.jsp">
            Home
        </a>

        <a class="active"
           href="<%= request.getContextPath() %>/pets">
            Find a pet
        </a>

        <% if (loggedUser != null) { %>

            <a href="<%= request.getContextPath() %>/users?action=dashboard">
                My dashboard
            </a>

            <a class="nav-button"
               href="<%= request.getContextPath() %>/logout">
                Sign out
            </a>

        <% } else { %>

            <a class="nav-button"
               href="<%= request.getContextPath() %>/login.jsp">
                Sign in
            </a>

        <% } %>

    </nav>

</header>


<main class="page-wrap">

    <section class="page-heading">

        <div>

            <p class="eyebrow">The PetHub collection</p>

            <h1>
                Find your <em>match.</em>
            </h1>

            <p>
                Every profile is a new little possibility. Filter by what feels
                right for your home.
            </p>

        </div>

        <div class="result-count">

            <strong><%= pets.size() %></strong>

            <span>
                pets waiting<br />
                to be met
            </span>

        </div>

    </section>


    <section class="filter-bar">

        <label class="search-field">

            <span>&#9906;</span>

            <input
                id="pet-search"
                type="search"
                placeholder="Search by name or breed" />

        </label>


        <select id="species-filter">

            <option value="">All species</option>

            <option>Dog</option>
            <option>Cat</option>
            <option>Rabbit</option>

        </select>


        <select id="size-filter">

            <option value="">Any size</option>

            <option>Small</option>
            <option>Medium</option>
            <option>Large</option>

        </select>


        <button id="clear-filters">
            Clear
        </button>

    </section>


    <section class="pet-grid" id="pet-grid">

        <% for (Pet pet : pets) { %>

            <%
                String petImageClass =
                    "image-" +
                    (pet.getName() != null
                        ? pet.getName().toLowerCase().replaceAll("\\s+", "")
                        : "default");

                String imageUrl =
                    pet.getImage() != null
                        ? request.getContextPath()
                            + "/images/"
                            + pet.getImage()
                        : null;
            %>


            <article
                class="pet-card"
                data-name="<%= pet.getName() %>"
                data-breed="<%= pet.getBreed() %>"
                data-species="<%= pet.getSpecies() %>"
                data-size="<%= pet.getSize() %>"
            >

                <div class="pet-image <%= petImageClass %>">

                    <% if (imageUrl != null && !imageUrl.isEmpty()) { %>

                        <img
                            src="<%= imageUrl %>"
                            alt="<%= pet.getName() %>"
                            onerror="this.style.display='none'">

                    <% } %>


                    <% if (imageUrl == null || imageUrl.isEmpty()) { %>

                        <span style="font-size: 48px;">🐾</span>

                        <small>image coming soon</small>

                    <% } %>


                    <b>
                        <%= pet.getAdoptionStatus() %>
                    </b>

                </div>


                <div class="pet-card-body">

                    <div class="pet-title">

                        <div>

                            <h2>
                                <%= pet.getName() %>
                            </h2>

                            <p>
                                <%= pet.getBreed() %>
                            </p>

                        </div>

                        <span class="heart-button">
                            &#9825;
                        </span>

                    </div>


                    <div class="pet-meta">

                        <span>
                            <%= pet.getSpecies() %>
                        </span>

                        <span>
                            <%= pet.getAge() %> years
                        </span>

                        <span>
                            <%= pet.getSize() %>
                        </span>

                    </div>


                    <a
                        class="card-link"
                        href="<%= request.getContextPath() %>/pet-details?petId=<%= pet.getPetId() %>"
                    >
                        View <%= pet.getName() %>'s story &rarr;
                    </a>

                </div>

            </article>

        <% } %>

    </section>


    <p class="empty-state" id="empty-state">
        No pets match those filters yet.
    </p>

</main>


<script src="<%= request.getContextPath() %>/js/pets.js"></script>

</body>

</html>