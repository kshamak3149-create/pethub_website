package com.pethub.servlet;

import java.io.IOException;
import java.util.List;

import com.pethub.model.User;
import com.pethub.model.AdoptionRequest;
import com.pethub.service.AdoptionRequestService;
import com.pethub.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService;
    private AdoptionRequestService adoptionRequestService;

    public UserServlet() {
        userService = new UserService();
        adoptionRequestService = new AdoptionRequestService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if ("dashboard".equals(req.getParameter("action"))) {
            HttpSession session = req.getSession(false);
            User loggedUser = session == null ? null : (User) session.getAttribute("loggedInUser");
            if (loggedUser == null) {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
            List<AdoptionRequest> requests = adoptionRequestService.getAllRequests();
            requests.removeIf(item -> item.getUserId() != loggedUser.getUserId());
            req.setAttribute("userRequests", requests);
            req.getRequestDispatcher("/user-dashboard.jsp").forward(req, resp);
            return;
        }

        if (!isAdmin(req)) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        List<User> users = userService.getAllUsers();

        req.setAttribute("users", users);

        req.getRequestDispatcher("user-dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("login".equals(action)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            String normalizedEmail = email == null ? "" : email.trim();
            String normalizedPassword = password == null ? "" : password.trim();

            if (normalizedEmail.isEmpty() || normalizedPassword.isEmpty()) {
                req.setAttribute("error", "Please enter both email and password.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }

            User user = userService.getUserByEmail(normalizedEmail);
            boolean validLogin = user != null && normalizedPassword.equals(user.getPassword().trim());

            if (validLogin) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedInUser", user);
                resp.sendRedirect(req.getContextPath() + "/users?action=dashboard");
                return;
            }

            req.setAttribute("error", "Invalid email or password.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        if ("add".equals(action)) {

            User user = new User();

            user.setName(req.getParameter("name"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            user.setPhone(req.getParameter("phone"));
            user.setAddress(req.getParameter("address"));

            userService.addUser(user);

            req.setAttribute("success", "Registration successful! You can now sign in.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;

        } else if ("update".equals(action)) {

            if (!isAdmin(req)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            int userId = Integer.parseInt(req.getParameter("userId"));

            User user = new User();

            user.setUserId(userId);
            user.setName(req.getParameter("name"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            user.setPhone(req.getParameter("phone"));
            user.setAddress(req.getParameter("address"));

            userService.updateUser(user);

            resp.sendRedirect("users");

        } else if ("delete".equals(action)) {

            if (!isAdmin(req)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            int userId = Integer.parseInt(req.getParameter("userId"));

            userService.deleteUser(userId);

            resp.sendRedirect("users");
        }
    }

    private boolean isAdmin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInAdmin") != null;
    }
}