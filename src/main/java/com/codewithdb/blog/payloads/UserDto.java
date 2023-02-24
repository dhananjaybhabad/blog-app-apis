package com.codewithdb.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "username must be min of 4 characters !")
	private String name;

	@Email(message = "email address not valid")
	private String email;

	@NotEmpty
	// @Size(min=3,max=10,message = "password must be min of 3 char and max of 10
	// char !")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
	private String password;

	@NotEmpty
	private String about;

}
