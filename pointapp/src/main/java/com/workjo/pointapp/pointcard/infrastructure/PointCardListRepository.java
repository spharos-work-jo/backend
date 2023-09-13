package com.workjo.pointapp.pointcard.infrastructure;


import com.workjo.pointapp.pointcard.domain.PointCardList;
import com.workjo.pointapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PointCardListRepository extends JpaRepository<PointCardList, Long> {

	Optional<PointCardList> findByUser(User user);

}
