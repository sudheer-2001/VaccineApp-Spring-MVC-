package com.tap.vaccine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.dao.MemberDAO;
import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.AddMemberService;

@Controller
public class AddMemberController {

	private AddMemberService memberService;
	private MemberDAO memberDao;

	public AddMemberController() {
		System.out.println("Object Created in AddMemberController..");
	}

	@Autowired
	public AddMemberController(AddMemberService memberService, MemberDAO memberDao) {
		super();
		this.memberService = memberService;
		this.memberDao = memberDao;
	}

	@RequestMapping(value = "/getAddMemberPage")
	public String getMemberPage() {
		return "/WEB-INF/pages/AddMember.jsp";
	}

	@RequestMapping(value = "/addMember")
	public String addMemberToDB(@RequestParam String memberName, @RequestParam String gender,
			@RequestParam String memberDob, @RequestParam String idProof, @RequestParam String proofNumber,
			@RequestParam String vaccineType, @RequestParam int dose, @RequestParam String memberEmail, Model model,
			HttpSession session) {
		String userEmail = (String) session.getAttribute("userEmail");
		try {
			boolean validateData = memberService.validateData(memberName, gender, memberDob, idProof, proofNumber,
					vaccineType, dose, memberEmail, userEmail);
			System.out.println("validateData " + validateData);
			boolean saveUserData = memberService.saveUserData(memberName, gender, memberDob, idProof, proofNumber,
					vaccineType, dose, memberEmail, userEmail);
			System.out.println("saveUserData " + saveUserData);
			model.addAttribute("responseMessage", "Successfully Added.");

		} catch (InvalidException e) {
			System.out.println("");
			model.addAttribute("responseMessage", e.getMessage());
		}
		return "/getAllMember";
	}

	@RequestMapping(value = "/getAllMember")
	public String getAllMembers(Model model, HttpSession session) {
		String userEmail = (String) session.getAttribute("userEmail");
		List<MemberEntity> members = memberService.viewAllMember(userEmail);
		if (members != null) {
			model.addAttribute("viewMembers", members);
		} else {
			model.addAttribute("updateMessage", "No Member Found..!");
		}
		return "/WEB-INF/pages/AddMember.jsp";
	}

	@RequestMapping(value = "/gotoHome")
	public String goToHome(Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("Invocked into goToHome()...");
		String userEmail = (String) session.getAttribute("userEmail");
		System.out.println(userEmail);
		int memCount = memberService.getMemberCount(userEmail);
		model.addAttribute("userEmail", userEmail);
		RegisterEntity entity = memberDao.getEntityByEmail(userEmail);
		if (entity != null)
			model.addAttribute("userName", entity.getUserName().toUpperCase());
		else
			model.addAttribute("userName", "Employee Name");

		System.out.println(memCount);
		model.addAttribute("memCount", memCount);
		return "/WEB-INF/pages/WelcomeUser.jsp";
	}

	@RequestMapping(value = "/editMembers/{id}")
	public String editMember(@PathVariable int id, Model model) {
		System.out.println("Edit Entity");
		MemberEntity entity = memberService.getEntityById(id);
		model.addAttribute("memberId", entity.getMemberId());
		model.addAttribute("memberName", entity.getMemberName());
		model.addAttribute("gender", entity.getGender());
		model.addAttribute("dob", entity.getDob());
		model.addAttribute("idProof", entity.getIdProof());
		model.addAttribute("idProofNumber", entity.getIdProofNumber());
		model.addAttribute("vaccineType", entity.getVaccineType());
		model.addAttribute("dose", entity.getDose());
		model.addAttribute("memberEmail", entity.getMemberEmail());
		return "/WEB-INF/pages/EditMember.jsp";
	}

	@RequestMapping(value = "/updateDetails", method = RequestMethod.POST)
	public String updateMemberDetails(@RequestParam int memberId, @RequestParam("memberName") String name,
			@RequestParam("gender") String gender, @RequestParam("dob") String dob,
			@RequestParam("idProof") String idProof, @RequestParam("proofNumber") String idProofNumber,
			@RequestParam("vaccineType") String vaccineType, @RequestParam("dose") int dose,
			@RequestParam("memberEmail") String memberEmail, Model model, HttpSession session) {
		System.out.println("Updating ");
		String userEmail = (String) session.getAttribute("userEmail");
		boolean memberDetails = memberService.updateMemberEntity(memberId, name, gender, dob, idProof, idProofNumber,
				vaccineType, dose, memberEmail, userEmail);
		System.out.println(name + " " + gender + " " + dob + " " + idProof + " " + idProofNumber + " " + vaccineType
				+ " " + dose + " " + memberEmail);
		System.out.println(memberDetails);
		if (memberDetails) {
			model.addAttribute("updateMessage", "Id number " + memberId + " Successfully Updated");
			System.out.println("Edit Success");
			return "/getAllMember";
		} else {
			model.addAttribute("updateMessage", "Something Went Wrong While Updating");
			System.out.println("Edit not success");
		}
		return "/WEB-INF/pages/EditMember.jsp";
	}

	@RequestMapping(value = "/deleteMember/{id}")
	public String deleteMember(HttpServletRequest request, @PathVariable int id, Model model) {
		System.out.println("Invoked deleteAddMemberRequestHandler()");
		System.out.println("delid : " + id);
		boolean deleteById = memberService.deleteMemberEntityById(id);
		System.out.println("Deleted: " + deleteById);
		if (deleteById) {
			HttpSession session = request.getSession(true);
			String email = (String) session.getAttribute("userEmail");
			System.out.println(email);
			int count = memberService.getMemberCount(email);
			System.out.println("Count " + count);
			memberService.decreaseMemberCount(email, count);
			System.out.println("Deleted Successfull");
			model.addAttribute("updateMessage", "Deleted Successful.");
			return "redirect:/getAllMember";
		} else {
			model.addAttribute("updateMessage", "Something Went Wrong while deleting..!");
			System.out.println("Something Went Wrong while deleting...!");
		}
		return "/WEB-INF/pages/AddMember.jsp";
	}
}
