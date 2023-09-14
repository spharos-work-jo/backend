package com.workjo.pointapp.point.common.domain.observable;

import com.workjo.pointapp.point.common.dto.PointEntityDto;

public interface INotUsablePointObservable {
    void observeUnusablePointSaved(PointEntityDto savedPointDto);
}
