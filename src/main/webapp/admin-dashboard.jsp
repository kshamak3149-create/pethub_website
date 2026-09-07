<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.pethub.model.Admin" %>
<%@ page import="com.pethub.model.Pet" %>
<%@ page import="com.pethub.model.User" %>
<%@ page import="com.pethub.model.AdoptionRequest" %>
<%@ page import="com.pethub.model.PetHealth" %>
<%@ page import="com.pethub.model.PetVaccination" %>
<%@ page import="java.util.List" %>
<%
String contextPath = request.getContextPath();
HttpSession adminSession = request.getSession(false);
Admin loggedAdmin = (adminSession != null) ? (Admin) adminSession.getAttribute("loggedInAdmin") : null;
if (loggedAdmin == null) {
    response.sendRedirect(contextPath + "/admin-login.jsp");
    return;
}
  List<Pet> pets = (List<Pet>) request.getAttribute("pets");
  List<User> users = (List<User>) request.getAttribute("users");
  List<AdoptionRequest> requests = (List<AdoptionRequest>) request.getAttribute("requests");
  List<PetHealth> healthRecords = (List<PetHealth>) request.getAttribute("healthRecords");
  List<PetVaccination> vaccinations = (List<PetVaccination>) request.getAttribute("vaccinations");
  if (pets == null || users == null || requests == null || healthRecords == null || vaccinations == null) {
      response.sendRedirect(contextPath + "/admins?action=dashboard");
      return;
  }
%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin dashboard | PetHub</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/admin-dashboard.css" />
  </head>
  <body>
    <aside class="admin-sidebar">
      <div class="admin-brand">
        <span class="brand-mark">P</span>
        <div><strong>PetHub</strong><small>Admin console</small></div>
      </div>
      <nav class="admin-nav">
        <a class="active" href="#overview">&#9632; Overview</a>
        <a href="#pets">&#128062; Manage pets</a>
        <a href="#requests">&#9825; Adoption requests</a>
        <a href="#users">&#9679; Users</a>
        <a href="#health">&#10010; Pet health</a>
        <a href="#vaccinations">&#10003; Vaccinations</a>
      </nav>
      <a class="sidebar-logout" href="<%= contextPath %>/logout"
        >&#8592; Sign out</a
      >
    </aside>
    <main class="admin-main">
      <header class="admin-topbar">
        <div>
          <p class="eyebrow">Rescue operations</p>
          <h1>Welcome, <%= loggedAdmin.getName() %></h1>
        </div>
        <a class="view-site" href="<%= contextPath %>/index.jsp">View public site &rarr;</a>
      </header>
      <% String message = request.getParameter("message"); if (message != null) { %>
        <p class="admin-message"><%= message %></p>
      <% } %>
      <section id="overview" class="metric-grid">
        <a class="metric-card" href="#pets"><span class="metric-icon coral">&#128062;</span><strong><%= pets.size() %></strong><span>Total pets</span></a>
        <a class="metric-card" href="#pets"><span class="metric-icon green">&#10003;</span><strong><% int availablePets = 0; for (Pet pet : pets) if ("Available".equalsIgnoreCase(pet.getAdoptionStatus())) availablePets++; %><%= availablePets %></strong><span>Available pets</span></a>
        <a class="metric-card" href="#pets"><span class="metric-icon yellow">&#9825;</span><strong><% int adoptedPets = 0; for (Pet pet : pets) if ("Adopted".equalsIgnoreCase(pet.getAdoptionStatus())) adoptedPets++; %><%= adoptedPets %></strong><span>Adopted pets</span></a>
        <a class="metric-card" href="#pets"><span class="metric-icon blue">&#9679;</span><strong><%= pets.size() %></strong><span>All pets</span></a>
      </section>
      <section id="pets" class="admin-section">
        <div class="section-heading"><div><p class="eyebrow">Pet directory</p><h2>Manage pets</h2></div><a class="button button-primary" href="#add-pet">+ Add pet</a></div>
        <div id="add-pet" class="admin-panel form-panel">
          <h3>Add a pet</h3>
          <form class="admin-form" method="post" action="<%= contextPath %>/admins" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add" />
            <input type="hidden" name="entity" value="pet" />
            <label>Name<input name="name" required /></label><label>Species<input name="species" required /></label><label>Breed<input name="breed" required /></label><label>Age<input name="age" type="number" min="0" required /></label><label>Gender<input name="gender" required /></label><label>Size<select name="size"><option>Small</option><option>Medium</option><option>Large</option></select></label><label>Location<input name="location" required /></label><label>Pet image<input name="image" type="file" accept="image/*" /></label><label>Health status<input name="healthStatus" value="Healthy" /></label><label>Vaccination status<input name="vaccinationStatus" value="Up to date" /></label><label>Adoption status<select name="adoptionStatus"><option>Available</option><option>Pending</option><option>Adopted</option></select></label><label class="wide">Description<textarea name="description" rows="2"></textarea></label>
            <button class="button button-primary" type="submit">Add pet</button>
          </form>
        </div>
        <div class="admin-panel table-panel"><div class="panel-title"><h3>Current pets</h3><a href="<%= contextPath %>/pets">Open public listing &rarr;</a></div><div class="table-wrap"><table><thead><tr><th>Pet image</th><th>Name</th><th>Species</th><th>Breed</th><th>Age</th><th>Size</th><th>Status</th><th>Actions</th></tr></thead><tbody>
          <% for (Pet pet : pets) { %><tr><td><% if (pet.getImage() != null && !pet.getImage().isBlank()) { %><img class="pet-thumb" src="<%= request.getContextPath() %>/images/<%= pet.getImage() %>" alt="<%= pet.getName() %>" /><% } else { %><span class="pet-thumb empty-thumb">&#128062;</span><% } %></td><td><strong><%= pet.getName() %></strong><small>ID #<%= pet.getPetId() %></small></td><td><%= pet.getSpecies() %></td><td><%= pet.getBreed() %></td><td><%= pet.getAge() %></td><td><%= pet.getSize() %></td><td><span class="status"><%= pet.getAdoptionStatus() %></span></td><td class="actions"><details class="edit-details"><summary>Edit</summary><form class="edit-form" method="post" action="<%= contextPath %>/admins" enctype="multipart/form-data"><input type="hidden" name="action" value="update" /><input type="hidden" name="entity" value="pet" /><input type="hidden" name="petId" value="<%= pet.getPetId() %>" /><input name="name" value="<%= pet.getName() %>" required /><input name="species" value="<%= pet.getSpecies() %>" required /><input name="breed" value="<%= pet.getBreed() %>" required /><input name="age" type="number" min="0" value="<%= pet.getAge() %>" required /><input name="gender" value="<%= pet.getGender() %>" required /><input name="size" value="<%= pet.getSize() %>" required /><input name="location" value="<%= pet.getLocation() %>" required /><input name="image" type="file" accept="image/*" /><input name="healthStatus" value="<%= pet.getHealthStatus() == null ? "" : pet.getHealthStatus() %>" /><input name="vaccinationStatus" value="<%= pet.getVaccinationStatus() == null ? "" : pet.getVaccinationStatus() %>" /><input name="adoptionStatus" value="<%= pet.getAdoptionStatus() %>" required /><textarea name="description" rows="2"><%= pet.getDescription() == null ? "" : pet.getDescription() %></textarea><button type="submit">Save changes</button></form></details><form method="post" action="<%= contextPath %>/admins" onsubmit="return confirm('Delete this pet?');"><input type="hidden" name="action" value="delete" /><input type="hidden" name="entity" value="pet" /><input type="hidden" name="petId" value="<%= pet.getPetId() %>" /><button type="submit">Delete</button></form></td></tr><% } %>
        </tbody></table></div></div>
      </section>
      <section id="requests" class="admin-section split-section"><div class="admin-panel table-panel full-panel"><div class="section-heading"><div><p class="eyebrow">Incoming interest</p><h2>Adoption requests</h2></div></div><div class="table-wrap"><table><thead><tr><th>Request</th><th>Pet / User</th><th>Reason</th><th>Review</th></tr></thead><tbody><% for (AdoptionRequest item : requests) { %><tr><td><strong>#<%= item.getRequestId() %></strong><small><%= item.getRequestDate() %></small></td><td>Pet #<%= item.getPetId() %><small>User #<%= item.getUserId() %></small></td><td><%= item.getReason() %></td><td><form class="inline-form" method="post" action="<%= contextPath %>/adoptionrequests"><input type="hidden" name="action" value="update" /><input type="hidden" name="requestId" value="<%= item.getRequestId() %>" /><input type="hidden" name="userId" value="<%= item.getUserId() %>" /><input type="hidden" name="petId" value="<%= item.getPetId() %>" /><input type="hidden" name="requestDate" value="<%= item.getRequestDate() %>" /><input type="hidden" name="reason" value="<%= item.getReason() %>" /><select name="status"><option <%= "Pending".equalsIgnoreCase(item.getStatus()) ? "selected" : "" %>>Pending</option><option <%= "Approved".equalsIgnoreCase(item.getStatus()) ? "selected" : "" %>>Approved</option><option <%= "Rejected".equalsIgnoreCase(item.getStatus()) ? "selected" : "" %>>Rejected</option></select><input name="adminRemark" value="<%= item.getAdminRemark() == null ? "" : item.getAdminRemark() %>" placeholder="Remark" /><button type="submit">Save</button></form></td></tr><% } %></tbody></table></div></div></section>
      <section id="users" class="admin-section split-section"><div class="admin-panel table-panel full-panel"><div class="section-heading"><div><p class="eyebrow">Community</p><h2>Registered users</h2></div></div><div class="table-wrap"><table><thead><tr><th>User</th><th>Contact</th><th>Address</th></tr></thead><tbody><% for (User user : users) { %><tr><td><strong><%= user.getName() %></strong><small>User #<%= user.getUserId() %></small></td><td><%= user.getEmail() %><small><%= user.getPhone() == null ? "No phone" : user.getPhone() %></small></td><td><%= user.getAddress() == null ? "Not provided" : user.getAddress() %></td></tr><% } %></tbody></table></div></div></section>
      <section id="health" class="admin-section split-section"><div class="admin-panel table-panel"><div class="section-heading"><div><p class="eyebrow">Care records</p><h2>Pet health</h2></div></div><form class="compact-form" method="post" action="<%= contextPath %>/pethealth"><input type="hidden" name="action" value="add" /><input name="petId" type="number" placeholder="Pet ID" required /><input name="medicalCondition" placeholder="Condition" required /><input name="treatment" placeholder="Treatment" required /><input name="lastCheckup" type="date" required /><input name="veterinarian" placeholder="Veterinarian" required /><button type="submit">Add record</button></form><div class="record-list"><% for (PetHealth record : healthRecords) { %><div><strong>Pet #<%= record.getPetId() %></strong><span><%= record.getMedicalCondition() %> / <%= record.getLastCheckup() %></span></div><% } %></div></div></section>
      <section id="vaccinations" class="admin-section split-section"><div class="admin-panel table-panel"><div class="section-heading"><div><p class="eyebrow">Preventive care</p><h2>Vaccinations</h2></div></div><form class="compact-form" method="post" action="<%= contextPath %>/petvaccination"><input type="hidden" name="action" value="add" /><input name="petId" type="number" placeholder="Pet ID" required /><input name="vaccineName" placeholder="Vaccine name" required /><input name="vaccinationDate" type="date" required /><input name="nextDueDate" type="date" required /><button type="submit">Add record</button></form><div class="record-list"><% for (PetVaccination record : vaccinations) { %><div><strong>Pet #<%= record.getPetId() %></strong><span><%= record.getVaccineName() %> / due <%= record.getNextDueDate() %></span></div><% } %></div></div></section>
    </main>
    <script src="<%= contextPath %>/js/admin-dashboard.js"></script>
  </body>
</html>
