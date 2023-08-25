package com.workjo.pointapp.user.infrastructure;


import com.workjo.pointapp.user.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	Optional<Bookmark> getBookmarkByUserUUID(UUID uuid);

}
