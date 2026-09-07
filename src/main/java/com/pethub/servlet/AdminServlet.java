package com.pethub.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.pethub.model.Admin;
import com.pethub.model.AdoptionRequest;
import com.pethub.model.Pet;
import com.pethub.model.PetHealth;
import com.pethub.model.PetVaccination;
import com.pethub.model.User;
import com.pethub.service.AdminService;
import com.pethub.service.AdoptionRequestService;
import com.pethub.service.PetHealthService;
import com.pethub.service.PetService;
import com.pethub.service.PetVaccinationService;
import com.pethub.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/admins")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class AdminServlet extends HttpServlet {

    private AdminService adminService;
    private PetService petService;
    private UserService userService;
    private AdoptionRequestService adoptionRequestService;
    private PetHealthService petHealthService;
    private PetVaccinationService petVaccinationService;

    public AdminServlet() {
        adminService = new AdminService();
        petService = new PetService();
        userService = new UserService();
        adoptionRequestService = new AdoptionRequestService();
        petHealthService = new PetHealthService();
        petVaccinationService = new PetVaccinationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("dashboard".equals(action) || action == null) {
            if (!isLoggedIn(req)) {
                resp.sendRedirect(req.getContextPath() + "/admin-login.jsp");
                return;
            }
            List<Pet> pets = petService.getAllPets();
            req.setAttribute("pets", pets);
            List<User> users = userService.getAllUsers();
            List<AdoptionRequest> requests = adoptionRequestService.getAllRequests();
            List<PetHealth> healthRecords = petHealthService.getAllPetHealth();
            List<PetVaccination> vaccinations = petVaccinationService.getAllVaccinations();
            req.setAttribute("users", users);
            req.setAttribute("requests", requests);
            req.setAttribute("healthRecords", healthRecords);
            req.setAttribute("vaccinations", vaccinations);
            req.getRequestDispatcher("/admin-dashboard.jsp").forward(req, resp);
            return;
        }

        if ("get".equals(action)) {

            int adminId = Integer.parseInt(req.getParameter("adminId"));

            Admin admin = adminService.getAdmin(adminId);

            req.setAttribute("admin", admin);

            req.getRequestDispatcher("admin-dashboard.jsp")
               .forward(req, resp);

        } else if ("email".equals(action)) {

            String email = req.getParameter("email");

            Admin admin = adminService.getAdminByEmail(email);

            req.setAttribute("admin", admin);

            req.getRequestDispatcher("admin-dashboard.jsp")
               .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action) && "pet".equals(req.getParameter("entity"))) {
            if (!requireAdmin(req, resp)) return;
            Pet pet = readPet(req, 0);
            petService.addPet(pet);
            redirectToDashboard(req, resp, "Pet added successfully.");
            return;
        }

        if ("update".equals(action) && "pet".equals(req.getParameter("entity"))) {
            if (!requireAdmin(req, resp)) return;
            int petId = Integer.parseInt(req.getParameter("petId"));
            Pet existingPet = petService.getPet(petId);
            Pet pet = readPet(req, petId);
            if (pet.getImage() == null && existingPet != null) {
                pet.setImage(existingPet.getImage());
            }
            petService.updatePet(pet);
            redirectToDashboard(req, resp, "Pet updated successfully.");
            return;
        }

        if ("delete".equals(action) && "pet".equals(req.getParameter("entity"))) {
            if (!requireAdmin(req, resp)) return;
            petService.deletePet(Integer.parseInt(req.getParameter("petId")));
            redirectToDashboard(req, resp, "Pet deleted successfully.");
            return;
        }

        if ("login".equals(action)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            String normalizedEmail = email == null ? "" : email.trim();
            String normalizedPassword = password == null ? "" : password.trim();

            if (normalizedEmail.isEmpty() || normalizedPassword.isEmpty()) {
                req.setAttribute("error", "Please enter both email and password.");
                req.getRequestDispatcher("admin-login.jsp").forward(req, resp);
                return;
            }

            Admin admin = adminService.getAdminByEmail(normalizedEmail);
            boolean validLogin = admin != null
                    && admin.getPassword() != null
                    && normalizedPassword.equals(admin.getPassword().trim());

            if (validLogin) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedInAdmin", admin);
                resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");
                return;
            }

            req.setAttribute("error", "Invalid admin email or password.");
            req.getRequestDispatcher("admin-login.jsp").forward(req, resp);
            return;
        }

        if ("add".equals(action)) {

            Admin admin = new Admin();

            admin.setName(req.getParameter("name"));
            admin.setEmail(req.getParameter("email"));
            admin.setPassword(req.getParameter("password"));

            adminService.addAdmin(admin);

            resp.sendRedirect(req.getContextPath() + "/admin-login.jsp");

        } else if ("update".equals(action)) {

            int adminId = Integer.parseInt(req.getParameter("adminId"));

            Admin admin = new Admin();

            admin.setAdminId(adminId);
            admin.setName(req.getParameter("name"));
            admin.setEmail(req.getParameter("email"));
            admin.setPassword(req.getParameter("password"));

            adminService.updateAdmin(admin);

            resp.sendRedirect("admins");

        } else if ("delete".equals(action)) {

            int adminId = Integer.parseInt(req.getParameter("adminId"));

            adminService.deleteAdmin(adminId);

            resp.sendRedirect("admins");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInAdmin") != null;
    }

    private boolean requireAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isLoggedIn(req)) {
            resp.sendRedirect(req.getContextPath() + "/admin-login.jsp");
            return false;
        }
        return true;
    }

    private void redirectToDashboard(HttpServletRequest req, HttpServletResponse resp, String message)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard&message="
                + java.net.URLEncoder.encode(message, java.nio.charset.StandardCharsets.UTF_8));
    }

    private Pet readPet(HttpServletRequest req, int petId) throws IOException, ServletException {
        Pet pet = new Pet();
        pet.setPetId(petId);
        pet.setName(req.getParameter("name"));
        pet.setSpecies(req.getParameter("species"));
        pet.setBreed(req.getParameter("breed"));
        pet.setAge(Integer.parseInt(req.getParameter("age")));
        pet.setGender(req.getParameter("gender"));
        pet.setSize(req.getParameter("size"));
        pet.setDescription(req.getParameter("description"));
        pet.setLocation(req.getParameter("location"));
        pet.setImage(saveUploadedImage(req));
        pet.setHealthStatus(req.getParameter("healthStatus"));
        pet.setVaccinationStatus(req.getParameter("vaccinationStatus"));
        pet.setAdoptionStatus(req.getParameter("adoptionStatus"));
        return pet;
    }

    private String saveUploadedImage(HttpServletRequest req) throws IOException, ServletException {
        Part imagePart = req.getPart("image");
        if (imagePart == null || imagePart.getSize() == 0) {
            return null;
        }
        String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        if (fileName.isBlank()) {
            return null;
        }
        String imageDirectoryPath = getServletContext().getRealPath("/images");
        if (imageDirectoryPath == null) {
            throw new ServletException("The application images directory is not available.");
        }
        Path imageDirectory = Paths.get(imageDirectoryPath);
        Files.createDirectories(imageDirectory);
        try (InputStream input = imagePart.getInputStream()) {
            Files.copy(input, imageDirectory.resolve(fileName), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }
}