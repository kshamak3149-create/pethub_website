package com.pethub.service;

import com.pethub.dao.AdminDAO;
import com.pethub.daoimpl.AdminDAOImpl;
import com.pethub.model.Admin;

public class AdminService {
	
	private AdminDAO adminDAO;
	
	public AdminService() {
		 adminDAO = new AdminDAOImpl();
	}
	
	public void addAdmin(Admin admin) {
		adminDAO.addAdmin(admin);
	}

	  public Admin getAdmin(int adminId) {
	    	return adminDAO.getAdmin(adminId);
	    }

	  public  Admin getAdminByEmail(String email) {
		  return adminDAO.getAdminByEmail(email);
	  }

	   public void updateAdmin(Admin admin) {
		    adminDAO.updateAdmin(admin);
	   }

	   public void deleteAdmin(int adminId) {
	    	adminDAO.deleteAdmin(adminId);
	    }
}
