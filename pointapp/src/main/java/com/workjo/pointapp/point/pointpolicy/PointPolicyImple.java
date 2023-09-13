package com.workjo.pointapp.point.pointpolicy;

import org.springframework.stereotype.Service;

@Service
public class PointPolicyImple implements IPointPolicy {
    private static final int pointRate = 3;


    @Override
    public int getPoint(int paidPrice) {
        return (paidPrice * pointRate) / 100;
    }


}
