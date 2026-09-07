<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><% String contextPath = request.getContextPath(); %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign in | PetHub</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/login.css" />
  </head>
  <body>
    <main class="auth-layout">
      <a class="brand" href="<%= contextPath %>/index.jsp"
        ><span class="brand-mark">P</span
        ><span>Pet<span class="brand-accent">Hub</span></span></a
      >
      <section class="auth-panel">
        <p class="eyebrow">Welcome back</p>
        <h1>Good to see<br /><em>you again.</em></h1>
        <p class="auth-intro">Sign in to follow your adoption journey.</p>
        <form id="login-form" class="form-card" method="post" action="<%= contextPath %>/users">
          <input type="hidden" name="action" value="login" />
          <label
            >Email address<input type="email" name="email" required /></label
          ><label
            >Password<input type="password" name="password" required /></label
          ><button class="button button-primary" type="submit">
            Sign in <span>&rarr;</span>
          </button>
          <p class="form-message" id="form-message"><% String loginError = (String) request.getAttribute("error"); if (loginError != null) { out.print(loginError); } %></p>
          <p class="auth-switch">
            New to PetHub?
            <a href="<%= contextPath %>/register.jsp">Create an account</a>
          </p>
        </form>
      </section>
      <div class="auth-art">
        <span>&#128062;</span>
        <p>Find the connection<br />that feels like home.</p>
      </div>
    </main>
    <script src="<%= contextPath %>/js/login.js"></script>
  </body>
</html>
