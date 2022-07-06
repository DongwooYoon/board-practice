package jpa.springboot.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class JoinForm {

	@NotNull(message = "nickname는 필수 값입니다.")
	private String nickname;
	
	@NotNull(message = "password는 필수 값입니다.")
	private String password;

}
