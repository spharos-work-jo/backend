package com.workjo.pointapp.user.dto;


import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkGetDto {

	private Integer menu1;


	/**
	 * default bookmark 객체
	 */
	public static BookMarkGetDto defaultBookmark() {
		return BookMarkGetDto.builder()
			.menu1(1)
			.build();
	}

}
