package jpa.springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpa.springboot.entity.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final EntityManager em;			// @RequiredArgsConstructor(By lombok)에 의해 생성자가 만들어지고, 자동으로 객체주입

	// 등록	
	public void save(Member member) {
		em.persist(member);
	}
	
	// 전체 조회
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
	// id로 조회
	public Member findById(int id) {
		return em.find(Member.class, id);
	}
	
	// nickname으로 조회
	public List<Member> findByNickname(String nickname){
		return em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
				.setParameter("nickname", nickname)
				.getResultList();
	}
}
