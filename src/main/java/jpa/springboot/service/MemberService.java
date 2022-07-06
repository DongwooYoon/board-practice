package jpa.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpa.springboot.entity.Member;
import jpa.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;		// @RequiredArgsConstructor(By lombok)에 의해 생성자가 만들어지고, 자동으로 객체주입 
	
// 가입
	@Transactional
	public int join(Member member) {
		validateDuplicateMember(member);	// 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
// 로그인
	public boolean login(Member member) {
		List<Member> findMember = memberRepository.findByNickname(member.getNickname());
		if(findMember.isEmpty()) {
			return false;
		} else {
			return true;
		}	
	}
	
	
	
	
	
	
	
/*	
	// 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    // id 조회
    public Member findMember(int memberId) {
        return memberRepository.findById(memberId);
    }
*/   
}
