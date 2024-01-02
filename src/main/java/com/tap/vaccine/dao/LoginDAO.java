package com.tap.vaccine.dao;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;

public interface LoginDAO {
	RegisterEntity getRegisterEntityByEmail(String email) throws EntityNotFoundException;

	boolean updateRegisterEntity(String email);
}
