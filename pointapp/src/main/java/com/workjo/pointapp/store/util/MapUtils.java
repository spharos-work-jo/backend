package com.workjo.pointapp.store.util;


public class MapUtils {

	public static String boundToPolygonString(double bottomLat, double topLat, double leftLng, double rightLng) {
		StringBuilder sb = new StringBuilder()
			.append("Polygon((")
			.append(leftLng).append(" ").append(bottomLat)
			.append(",")
			.append(leftLng).append(" ").append(topLat)
			.append(",")
			.append(rightLng).append(" ").append(topLat)
			.append(",")
			.append(rightLng).append(" ").append(bottomLat)
			.append(",")
			.append(leftLng).append(" ").append(bottomLat)
			.append("))");
		return sb.toString();
	}

}
