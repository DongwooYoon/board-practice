package jpa.springboot.ids;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@SuppressWarnings("serial")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Ids implements Serializable {
	
	@Id
	private String tableName;
	private int nextId;
}
