package com.pethub.dao;

import java.util.List;
import com.pethub.model.AdoptionRequest;

public interface AdoptionRequestDAO {

    void addRequest(AdoptionRequest request);

    AdoptionRequest getRequest(int requestId);

    List<AdoptionRequest> getAllRequests();

    void updateRequest(AdoptionRequest request);

    void deleteRequest(int requestId);
}