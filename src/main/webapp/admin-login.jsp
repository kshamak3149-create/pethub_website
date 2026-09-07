<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><% String contextPath = request.getContextPath(); %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin sign in | PetHub</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/admin-login.css" />
  </head>
  <body>
    <main class="admin-auth">
      <div class="admin-brand">
        <span class="brand-mark">P</span>
        <div><strong>PetHub</strong><small>Rescue operations</small></div>
      </div>
      <div class="admin-card">
        <p class="eyebrow">Team access</p>
        <h1>Welcome to<br /><em>the back office.</em></h1>
        <p>
          Sign in to manage pets, requests, and the people who care about them.
        </p>
        <form id="admin-login-form" method="post" action="<%= contextPath %>/admins">
          <input type="hidden" name="action" value="login" />
          <label>Admin email<input type="email" name="email" required /></label
          ><label
            >Password<input type="password" name="password" required /></label
          ><button class="button button-dark" type="submit">
            Open dashboard <span>&rarr;</span>
          </button>
          <p class="form-message" id="form-message"><% String adminLoginError = (String) request.getAttribute("error"); if (adminLoginError != null) { out.print(adminLoginError); } %></p>
        </form>
        <a class="return-link" href="<%= contextPath %>/index.jsp"
          >&larr; Return to PetHub</a
        >
      </div>
    </main>
    <script src="<%= contextPath %>/js/admin-login.js"></script>
  </body>
</html>
