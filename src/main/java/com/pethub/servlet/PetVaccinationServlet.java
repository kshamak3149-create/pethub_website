package com.pethub.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.pethub.model.PetVaccination;
import com.pethub.service.PetVaccinationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/petvaccination")
public class PetVaccinationServlet extends HttpServlet {

    private PetVaccinationService petVaccinationService;

    public PetVaccinationServlet() {
        petVaccinationService = new PetVaccinationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

                if (!isAdmin(req)) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                }

        List<PetVaccination> vaccinations =
                petVaccinationService.getAllVaccinations();

        req.setAttribute("vaccinations", vaccinations);

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

            PetVaccination vaccination = new PetVaccination();

            vaccination.setPetId(
                    Integer.parseInt(req.getParameter("petId"))
            );

            vaccination.setVaccineName(
                    req.getParameter("vaccineName")
            );

            vaccination.setVaccinationDate(
                    Date.valueOf(req.getParameter("vaccinationDate"))
            );

            vaccination.setNextDueDate(
                    Date.valueOf(req.getParameter("nextDueDate"))
            );

            petVaccinationService.addVaccination(vaccination);

            resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");

        } else if ("update".equals(action)) {

            int vaccinationId =
                    Integer.parseInt(req.getParameter("vaccinationId"));

            PetVaccination vaccination = new PetVaccination();

            vaccination.setVaccinationId(vaccinationId);

            vaccination.setPetId(
                    Integer.parseInt(req.getParameter("petId"))
            );

            vaccination.setVaccineName(
                    req.getParameter("vaccineName")
            );

            vaccination.setVaccinationDate(
                    Date.valueOf(req.getParameter("vaccinationDate"))
            );

            vaccination.setNextDueDate(
                    Date.valueOf(req.getParameter("nextDueDate"))
            );

            petVaccinationService.updateVaccination(vaccination);

            resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");

        } else if ("delete".equals(action)) {

            int vaccinationId =
                    Integer.parseInt(req.getParameter("vaccinationId"));

            petVaccinationService.deleteVaccination(vaccinationId);

                        resp.sendRedirect(req.getContextPath() + "/admins?action=dashboard");
        }
    }

        private boolean isAdmin(HttpServletRequest req) {
                HttpSession session = req.getSession(false);
                return session != null && session.getAttribute("loggedInAdmin") != null;
        }
}