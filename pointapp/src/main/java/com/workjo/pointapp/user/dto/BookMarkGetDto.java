package com.workjo.pointapp.user.dto;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkGetDto {

	static final Integer defaultValue = -1;

	private Integer menu1;
	private Integer menu2;
	private Integer menu3;
	private Integer menu4;
	private Integer menu5;
	private Integer menu6;


	/**
	 * default bookmark 리스트
	 */
	public static List<Integer> defaultBookmark() {
		return List.of(new Integer[] { 1, 2, 3, -1, -1, -1 });
	}


	public List<Integer> toIdList() {
		List<Integer> list = new ArrayList<>();
		list.add(Objects.nonNull(menu1) ? menu1 : defaultValue);
		list.add(Objects.nonNull(menu2) ? menu2 : defaultValue);
		list.add(Objects.nonNull(menu3) ? menu3 : defaultValue);
		list.add(Objects.nonNull(menu4) ? menu4 : defaultValue);
		list.add(Objects.nonNull(menu5) ? menu5 : defaultValue);
		list.add(Objects.nonNull(menu6) ? menu6 : defaultValue);
		return list;
	}

}
