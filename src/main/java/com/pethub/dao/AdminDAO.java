package com.pethub.dao;

import com.pethub.model.Admin;

public interface AdminDAO {

    void addAdmin(Admin admin);

    Admin getAdmin(int adminId);

    Admin getAdminByEmail(String email);

    void updateAdmin(Admin admin);

    void deleteAdmin(int adminId);
}