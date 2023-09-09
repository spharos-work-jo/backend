package com.workjo.pointapp.common.domain.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleSliceDto<T> {

	List<T> content;
	Boolean first;
	Boolean last;


	public static <T> SimpleSliceDto<T> fromSlice(Slice<T> slice) {
		return new SimpleSliceDto<>(slice.getContent(), slice.isFirst(), slice.isLast());
	}


	public static <T> SimpleSliceDto<T> fromSlice(List<T> content, Slice<?> slice) {
		return new SimpleSliceDto<>(content, slice.isFirst(), slice.isLast());
	}

}
