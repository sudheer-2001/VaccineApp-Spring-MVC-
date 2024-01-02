package com.tap.vaccine.dao;

import com.tap.vaccine.exception.InvalidException;

public interface ResetPasswordDAO {

	boolean resetPasswordByEmail(String email, String newPassword) throws InvalidException;

}
