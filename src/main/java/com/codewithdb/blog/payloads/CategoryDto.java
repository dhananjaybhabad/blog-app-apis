package com.codewithdb.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;

	@NotBlank
	@Size(min = 4 , message = "Min size is 4 char")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10 ,message = "Min size is 10 char")
	private String categoryDescription;
}
