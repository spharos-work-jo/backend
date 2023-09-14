package com.workjo.pointapp.point.common.domain.observable;

import com.workjo.pointapp.point.common.dto.PointEntityDto;

public interface IUsablePointObservable {
    void observeUsablePointIncreased(PointEntityDto usablePoint);
    void observePointUsed(PointEntityDto usedPoint);
}
