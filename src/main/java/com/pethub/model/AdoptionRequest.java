package com.pethub.model;

import java.sql.Date;

public class AdoptionRequest {
	
	private int requestId;
	private int userId;
	private int petId;
	private Date requestDate;
	private String reason;
	private String status;
	private String adminRemark;
	
	public AdoptionRequest() {
		// TODO Auto-generated constructor stub
	}

	public AdoptionRequest(int requestId, int userId, int petId, Date requestDate, String reason, String status,
			String adminRemark) {
	
		this.requestId = requestId;
		this.userId = userId;
		this.petId = petId;
		this.requestDate = requestDate;
		this.reason = reason;
		this.status = status;
		this.adminRemark = adminRemark;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	@Override
	public String toString() {
		return "AdoptionRequest [requestId=" + requestId + ", userId=" + userId + ", petId=" + petId + ", requestDate="
				+ requestDate + ", reason=" + reason + ", status=" + status + ", adminRemark=" + adminRemark + "]";
	}
	
	
	
	

}
