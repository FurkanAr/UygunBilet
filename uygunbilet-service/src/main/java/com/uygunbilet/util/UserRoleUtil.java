package com.uygunbilet.util;

import com.uygunbilet.model.User;
import com.uygunbilet.model.enums.UserRole;

/*
 * If user role is equal to admin this class return true
 */
public class UserRoleUtil {
	
	private UserRoleUtil() {
		
	}
	
	public static boolean validateUserRole(User user) {
		return UserRole.ADMIN.equals(user.getRole());
	}

}
