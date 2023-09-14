package com.workjo.pointapp.point.observable;

import com.workjo.pointapp.point.common.dto.PointEntityDto;

public interface INotUsablePointObservable {
    void observeUnusablePointSaved(PointEntityDto savedPointDto);
}
