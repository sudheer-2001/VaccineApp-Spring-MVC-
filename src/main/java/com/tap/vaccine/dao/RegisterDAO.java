package com.tap.vaccine.dao;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.InvalidException;

public interface RegisterDAO {

	boolean saveData(RegisterEntity entity) throws InvalidException;

	public boolean isEmailExists(String email);

	public boolean isUserNameExists(String userName);
}
