package com.pethub.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.pethub.model.PetHealth;
import com.pethub.service.PetHealthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/pethealth")
public class PetHealthServlet extends HttpServlet {

    private PetHealthService petHealthService;

    public PetHealthServlet() {
        petHealthService = new PetHealthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        List<PetHealth> pethealth = petHealthService.getAllPetHealth();

        req.setAttribute("pethealth", pethealth);

        req.getRequestDispatcher("pet-details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!isAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = req.getParameter("action");

        if ("add".equals(action)) {

            PetHealth pethealth = new PetHealth();

            pethealth.setPetId(Integer.parseInt(req.getParameter("petId")));
            pethealth.setMedicalCondition(req.getParameter("medicalCondition"));
            pethealth.setTreatment(req.getParameter("treatment"));

            pethealth.setLastCheckup(
                    Date.valueOf(req.getParameter("lastCheckup"))
            );

            pethealth.setVeterinarian(req.getParameter("veterinarian"));

            petHealthService.addPetHealth(pethealth);

            resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");

        } else if ("update".equals(action)) {

            int healthId = Integer.parseInt(req.getParameter("healthId"));

            PetHealth pethealth = new PetHealth();

            pethealth.setHealthId(healthId);
            pethealth.setPetId(Integer.parseInt(req.getParameter("petId")));
            pethealth.setMedicalCondition(req.getParameter("medicalCondition"));
            pethealth.setTreatment(req.getParameter("treatment"));

            pethealth.setLastCheckup(
                    Date.valueOf(req.getParameter("lastCheckup"))
            );

            pethealth.setVeterinarian(req.getParameter("veterinarian"));

            petHealthService.updatePetHealth(pethealth);

            resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");

        } else if ("delete".equals(action)) {

            int healthId = Integer.parseInt(req.getParameter("healthId"));

            petHealthService.deletePetHealth(healthId);

            resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");
        }
    }

    private boolean isAdmin(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInAdmin") != null;
    }
}