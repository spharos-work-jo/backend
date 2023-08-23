package com.workjo.pointapp.point.application.pointpolicy;

import org.springframework.stereotype.Service;

@Service
public class FixedRatePointPolicy implements IPointPolicy {
    private static final int pointRate = 3;

    @Override
    public int getPoint(int paidPrice) {
        return (paidPrice * pointRate) / 100;
    }
}
