package com.tap.vaccine.dao;

import java.util.List;

import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.entity.RegisterEntity;

public interface MemberDAO {

	boolean saveData(MemberEntity entity);

	boolean isEmailExists(String email);

	boolean isMemberNameExists(String userName);

	List<MemberEntity> getAllMembers(String userEmail);

	int getMemberCount(String userEmail);

	boolean deleteMemberByID(int id);

	int updateMemberCount(String userEmail, int memberCount);

	MemberEntity getEntityById(int memberId);

	boolean updateMemberEntity(MemberEntity entity);

	RegisterEntity getEntityByEmail(String email);

	int decreaseMemberCount(String userEmail, int memberCount);

}
