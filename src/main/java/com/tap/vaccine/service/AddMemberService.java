package com.tap.vaccine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.dao.MemberDAO;
import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.exception.InvalidException;

@Component
public class AddMemberService {

	private MemberDAO memberDao;

	@Autowired
	public AddMemberService(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	public AddMemberService() {
		System.out.println("Object Created in AddMemberService..");
	}

	public boolean validateData(String memberName, String gender, String dob, String idProof, String proofNumber,
			String vaccineType, Integer dose, String memberEmail, String userEmail) throws InvalidException {

		boolean isEmailExists = memberDao.isEmailExists(memberEmail);
		boolean isMemberNameExists = memberDao.isMemberNameExists(memberName);

		boolean flag = true;
		if (memberName == null || memberName.isEmpty() || memberName.isBlank()) {
			throw new InvalidException("Member Name is Invalid..");
		}
		if (isMemberNameExists) {
			throw new InvalidException("Member is already exists.. Try with different Member Name");
		}

		if (gender == null || gender.isEmpty() || gender.isBlank()) {
			throw new InvalidException("Gender is mandatory...");
		}

		if (dob == null || dob.isEmpty() || dob.isBlank()) {
			throw new InvalidException("Providing the date of birth is a must and should...");
		}

		if (idProof.isEmpty() || idProof.isBlank() || idProof == null) {
			throw new InvalidException("Please select proofNumber...");
		}

		if (proofNumber == null || proofNumber.isEmpty() || proofNumber.isBlank()) {
			throw new InvalidException("Proof Number required...");
		}
		if (vaccineType == null || vaccineType.isEmpty() || vaccineType.isBlank()) {
			throw new InvalidException("Please select vaccineType...");
		}
		if (dose == null) {
			throw new InvalidException("Please select which Dose upto done!..");
		}
		if (memberEmail == null || memberEmail.isEmpty() || memberEmail.isBlank()) {
			throw new InvalidException("Member Email cannot be empty...");
		} else {
			if (isEmailExists) {
				throw new InvalidException("MemberEmail already exists.. Try with different mail");
			} else {
				System.out.println("New Member Email registering...");
			}
		}
		return flag;
	}

	public boolean saveUserData(String memberName, String gender, String dob, String idProof, String proofNumber,
			String vaccineType, int dose, String memberEmail, String userEmail) throws InvalidException {
		int memberCount = memberDao.getMemberCount(userEmail);
		if (memberCount < 4) {
			MemberEntity entity = new MemberEntity(memberName, gender, dob, idProof, proofNumber, vaccineType, dose,
					memberEmail, userEmail);
			boolean saved = memberDao.saveData(entity);
			if (saved) {
				int memCount = memberDao.updateMemberCount(userEmail, memberCount);
				return true;
			} else {
				throw new InvalidException("Not Saved. & Check data once.");
			}
		} else {
			throw new InvalidException("Member registration Limit Exceeded.");
		}
	}

	public List<MemberEntity> viewAllMember(String userEmail) {
		System.out.println("Invoked viewAllMember() in service..");
		List<MemberEntity> allMembers = memberDao.getAllMembers(userEmail);
		return allMembers;
	}

	public int getMemberCount(String email) {
		return memberDao.getMemberCount(email);
	}

	public boolean checkMemberCount(String userEmail) {
		final int maxMembers = 4;
		int memberCount = memberDao.getMemberCount(userEmail);
		return memberCount < maxMembers;
	}

	public int updateMemberCount(String email, int memberCount) {
		return memberDao.updateMemberCount(email, memberCount);
	}

	public MemberEntity getEntityById(int id) {
		return memberDao.getEntityById(id);
	}

	public boolean updateMemberEntity(int memberId, String name, String gender, String dob, String idProof,
			String idProofNumber, String vaccineType, int dose, String userEmail, String memberEmail) {
		MemberEntity entity = new MemberEntity(memberId, name, gender, dob, idProof, idProofNumber, vaccineType, dose,
				userEmail, memberEmail);
		return memberDao.updateMemberEntity(entity);
	}

	public boolean deleteMemberEntityById(int id) {
		return memberDao.deleteMemberByID(id);
	}

	public int decreaseMemberCount(String email, int memberCount) {
		return memberDao.decreaseMemberCount(email, memberCount);
	}
}
