package com.workjo.pointapp.common.domain.dto;

import java.lang.reflect.Constructor;

public record ConvertInfo<T>(Class<T> type, Constructor<T> constructor) {
}
