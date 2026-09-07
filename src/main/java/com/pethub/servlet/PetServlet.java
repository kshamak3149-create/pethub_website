package com.pethub.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.pethub.model.Pet;
import com.pethub.service.PetService;

@WebServlet("/pets")
public class PetServlet extends HttpServlet {

    private PetService petService;

    public PetServlet() {
        petService = new PetService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Pet> pets = petService.getAllPets();

        request.setAttribute("pets", pets);

        request.getRequestDispatcher("pets.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInAdmin") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = req.getParameter("action");

        if ("add".equals(action)) {

            Pet pet = new Pet();

            pet.setName(req.getParameter("name"));
            pet.setSpecies(req.getParameter("species"));
            pet.setBreed(req.getParameter("breed"));
            pet.setAge(Integer.parseInt(req.getParameter("age")));
            pet.setGender(req.getParameter("gender"));
            pet.setSize(req.getParameter("size"));
            pet.setDescription(req.getParameter("description"));
            pet.setLocation(req.getParameter("location"));
            pet.setImage(req.getParameter("image"));
            pet.setHealthStatus(req.getParameter("healthStatus"));
            pet.setVaccinationStatus(req.getParameter("vaccinationStatus"));
            pet.setAdoptionStatus(req.getParameter("adoptionStatus"));

            petService.addPet(pet);

            resp.sendRedirect("pets");

        } else if ("update".equals(action)) {

            int petId = Integer.parseInt(req.getParameter("petId"));

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
            pet.setImage(req.getParameter("image"));
            pet.setHealthStatus(req.getParameter("healthStatus"));
            pet.setVaccinationStatus(req.getParameter("vaccinationStatus"));
            pet.setAdoptionStatus(req.getParameter("adoptionStatus"));

            petService.updatePet(pet);

            resp.sendRedirect("pets");

        } else if ("delete".equals(action)) {

            int petId = Integer.parseInt(req.getParameter("petId"));

            petService.deletePet(petId);

            resp.sendRedirect("pets");
        }
    }
}