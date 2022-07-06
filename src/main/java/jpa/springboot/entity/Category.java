package jpa.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {
	
	@Id
	@Column(name = "category_en_name")
	private String enName;
	
	private String koName;
}
