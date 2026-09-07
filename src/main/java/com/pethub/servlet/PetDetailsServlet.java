package com.pethub.servlet;

import java.io.IOException;

import com.pethub.model.Pet;
import com.pethub.service.PetService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PetDetailsServlet extends HttpServlet {

    private PetService petService = new PetService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String petIdParameter = request.getParameter("petId");
        try {
            int petId = Integer.parseInt(petIdParameter);
            Pet pet = petService.getPet(petId);
            if (pet == null) {
                response.sendRedirect(request.getContextPath() + "/pets");
                return;
            }
            request.setAttribute("pet", pet);
            request.getRequestDispatcher("/pet-details.jsp").forward(request, response);
        } catch (NumberFormatException exception) {
            response.sendRedirect(request.getContextPath() + "/pets");
        }
    }
}
