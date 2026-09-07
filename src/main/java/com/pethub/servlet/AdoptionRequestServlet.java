package com.pethub.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.pethub.model.AdoptionRequest;
import com.pethub.model.User;
import com.pethub.service.AdoptionRequestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adoptionrequests")
public class AdoptionRequestServlet extends HttpServlet {

    private AdoptionRequestService adoptionRequestService;

    public AdoptionRequestServlet() {
        adoptionRequestService = new AdoptionRequestService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

                if (!isAdmin(req)) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                }

        List<AdoptionRequest> requests =
                adoptionRequestService.getAllRequests();

        req.setAttribute("requests", requests);

        req.getRequestDispatcher("adoption-request.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {

            HttpSession session = req.getSession(false);
            User loggedUser = (session != null) ? (User) session.getAttribute("loggedInUser") : null;
                        if (loggedUser == null) {
                                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                                return;
                        }
                        int userId = loggedUser.getUserId();

            AdoptionRequest request = new AdoptionRequest();
            request.setUserId(userId);
            request.setPetId(Integer.parseInt(req.getParameter("petId")));
            request.setRequestDate(Date.valueOf(req.getParameter("requestDate")));
            request.setReason(req.getParameter("reason"));
            request.setStatus("Pending");
            request.setAdminRemark("");

            adoptionRequestService.addRequest(request);

            resp.sendRedirect(req.getContextPath() + "/user-dashboard.jsp");

        } else if ("update".equals(action)) {

                        if (!isAdmin(req)) {
                                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                                return;
                        }

            int requestId =
                    Integer.parseInt(req.getParameter("requestId"));

            AdoptionRequest request = new AdoptionRequest();

            request.setRequestId(requestId);

            request.setUserId(
                    Integer.parseInt(req.getParameter("userId"))
            );

            request.setPetId(
                    Integer.parseInt(req.getParameter("petId"))
            );

            request.setRequestDate(
                    Date.valueOf(req.getParameter("requestDate"))
            );

            request.setReason(
                    req.getParameter("reason")
            );

            request.setStatus(
                    req.getParameter("status")
            );

            request.setAdminRemark(
                    req.getParameter("adminRemark")
            );

            adoptionRequestService.updateRequest(request);

            resp.sendRedirect("adoptionrequests");

        } else if ("delete".equals(action)) {

                        if (!isAdmin(req)) {
                                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                                return;
                        }

            int requestId =
                    Integer.parseInt(req.getParameter("requestId"));

            adoptionRequestService.deleteRequest(requestId);

            resp.sendRedirect("adoptionrequests");
        }
    }

        private boolean isAdmin(HttpServletRequest req) {
                HttpSession session = req.getSession(false);
                return session != null && session.getAttribute("loggedInAdmin") != null;
        }
}