package jpa.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter	@Setter
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "member_id")
	private int id;
	
	@Column(unique = true)
	private String nickname;
	
	@Column(nullable = false)
	private String password;
	
//	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)		// cascade = CascadeType.ALL : 레코드 변화를 주인 테이블에 동시에 반영
//	private List<Board> articles = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)		// cascade = CascadeType.ALL : 레코드 변화를 주인 테이블에 동시에 반영
//	private List<Comment> comments = new ArrayList<>();
}
