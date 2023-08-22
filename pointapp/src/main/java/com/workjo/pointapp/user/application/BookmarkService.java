package com.workjo.pointapp.user.application;


import com.workjo.pointapp.user.dto.BookMarkGetDto;


public interface BookmarkService {

	BookMarkGetDto getUserBookmark(String uuid);

}
