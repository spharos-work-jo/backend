package com.workjo.pointapp.point.infrastructure;

import com.workjo.pointapp.point.domain.PointEarn;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointEarnRepository {
    boolean savePointEarn(PointEarn pointEarn);
}
