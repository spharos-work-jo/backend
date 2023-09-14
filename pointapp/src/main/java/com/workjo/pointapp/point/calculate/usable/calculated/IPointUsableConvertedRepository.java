package com.workjo.pointapp.point.calculate.usable.calculated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPointUsableConvertedRepository extends JpaRepository<PointUsableConverted, Long> {

}
