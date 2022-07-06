package jpa.springboot.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jpa.springboot.entity.Member;
import jpa.springboot.form.JoinForm;
import jpa.springboot.repository.MemberRepository;
import jpa.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
// 회원가입	
	@GetMapping("/join")
	public String joinForm(Model model) {
		model.addAttribute("joinForm", new JoinForm());
		return "join";
	}
	
	@PostMapping("/join")
	public String join(@Valid JoinForm joinForm, BindingResult result, Model model) {
		log.debug("/join start");
		
		if(result.hasErrors()) {
			return "join";
		}
		Member member = new Member();
		member.setNickname(joinForm.getNickname());
		member.setPassword(joinForm.getPassword());
		try {
			memberService.join(member);
		}catch(IllegalStateException e) {
			log.info(">>>>>>>>>>>>>>>>>> 회원가입 에러: " + e.getMessage() + " <<<<<<<<<<<<<<<<<<<<");
			model.addAttribute("message", e.getMessage());
			return "join";
		}

		return "redirect:/login";
	}
	
// 로그인
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
    public String login(Member member, HttpSession session) {
		if(memberService.login(member)) {
			String nickname = member.getNickname();
			int member_id = memberRepository.findByNickname(nickname).get(0).getId();
			session.setAttribute("member_id", member_id);
			session.setAttribute("nickname", nickname);
			return "redirect:/board/lists/leagueoflegends";
		}else {
			return "login";
		}
    }
	
// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
