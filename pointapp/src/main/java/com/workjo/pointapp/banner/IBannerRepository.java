package com.workjo.pointapp.banner;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IBannerRepository extends JpaRepository<Banner, Long> {

	List<Banner> findAllByOrderByOrderNumAsc();

}
