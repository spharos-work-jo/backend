package com.workjo.pointapp.pointcard.infrastructure;


import com.workjo.pointapp.pointcard.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointCardRepository extends JpaRepository<PointCard, Long> {

}
