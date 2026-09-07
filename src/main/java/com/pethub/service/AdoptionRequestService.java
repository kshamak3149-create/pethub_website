package com.pethub.service;

import java.util.List;

import com.pethub.dao.AdoptionRequestDAO;
import com.pethub.daoimpl.AdoptionRequestDAOImpl;
import com.pethub.model.AdoptionRequest;

public class AdoptionRequestService {

    private AdoptionRequestDAO adoptionRequestDAO;

    public AdoptionRequestService() {
        adoptionRequestDAO = new AdoptionRequestDAOImpl();
    }

    public void addRequest(AdoptionRequest request) {
        adoptionRequestDAO.addRequest(request);
    }

    public AdoptionRequest getRequest(int requestId) {
        return adoptionRequestDAO.getRequest(requestId);
    }

    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestDAO.getAllRequests();
    }

    public void updateRequest(AdoptionRequest request) {
        adoptionRequestDAO.updateRequest(request);
    }

    public void deleteRequest(int requestId) {
        adoptionRequestDAO.deleteRequest(requestId);
    }
}