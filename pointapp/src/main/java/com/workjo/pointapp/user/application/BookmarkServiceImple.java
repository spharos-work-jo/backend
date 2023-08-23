package com.workjo.pointapp.user.application;


import com.workjo.pointapp.config.ModelMapperBean;
import com.workjo.pointapp.user.domain.Bookmark;
import com.workjo.pointapp.user.dto.BookMarkGetDto;
import com.workjo.pointapp.user.infrastructure.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@ToString
@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkServiceImple implements BookmarkService {

	private final ModelMapperBean modelMapperBean;
	private final BookmarkRepository bookmarkRepository;


	@Override
	public List<Integer> getUserBookmark(String uuid) {
		List<Integer> bookmarkIdlist;
		Optional<Bookmark> bookmark = bookmarkRepository.getBookmarkByUUID(uuid);
		if (bookmark.isEmpty()) {
			bookmarkIdlist = BookMarkGetDto.defaultBookmark();
		} else {
			BookMarkGetDto bookMarkGetDto = modelMapperBean.modelMapper().map(bookmark, BookMarkGetDto.class);
			bookmarkIdlist = bookMarkGetDto.toIdList();
		}
		log.debug(bookmarkIdlist.toString());
		return bookmarkIdlist;
	}

}
