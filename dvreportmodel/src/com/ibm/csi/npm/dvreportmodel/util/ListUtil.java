package com.ibm.csi.npm.dvreportmodel.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ListUtil {
	public static ArrayList<String> getFilteredList(String startString,
			ArrayList<String> props) {
		ArrayList<String> filteredProps = new ArrayList<String>();
		if (props.size() > 0) {
			Iterator<String> it = props.iterator();
			while (it.hasNext()) {
				String prop = it.next();
				if (prop.startsWith(startString))
					filteredProps.add(prop);
			}
		}
		return filteredProps;
	}

	public static String getValue(String prop) {
		String value = null;
		String[] propValueArr = prop.split("=");
		if (propValueArr.length > 1)
			value = propValueArr[1];
		return value;
	}

	public static HashMap<String, String> getPropValPair(String prop) {
		HashMap<String, String> propValPair = new HashMap<String, String>();
		String value = null;
		String property = null;
		String[] propValueArr = prop.split("=");
		String propString = propValueArr[0];
		String[] propStringArr = propString.split("\\.");
		property = propStringArr[propStringArr.length - 1];
		if (propValueArr.length > 1)
			value = propValueArr[1];
		propValPair.put(property, value);
		return propValPair;
	}
	
	public static HashMap<String, String> getPropValPair(ArrayList<String> props) {
		HashMap<String, String> propValPair = new HashMap<String, String>();
		
		propValPair.clear();
		Iterator<String> propIdSetItr = props.iterator();
		while(propIdSetItr.hasNext())
		{
			String value = null;
			String property = null;
			String prop = propIdSetItr.next();
			String[] propValueArr = prop.split("=");
			String propString = propValueArr[0];
			String[] propStringArr = propString.split("\\.");
			property = propStringArr[propStringArr.length - 1];
			
			if (propValueArr.length > 1)
				value = propValueArr[1];
		
			propValPair.put(property, value);
		}
		return propValPair;
	}

	public static ArrayList<String> getPageControlsByType(String type,
			ArrayList<String> props) {
		HashSet<String> pageControls = new HashSet<String>();
		Iterator<String> it = props.iterator();
		while (it.hasNext()) {
			String prop = it.next();
			String[] propHirachyArray = prop.split(".");
			if (propHirachyArray[0].contains(type))
				pageControls.add(propHirachyArray[0]);
		}
		return new ArrayList<String>(pageControls);
	}

	public static String genrateColorRangeIdChartStyle(String chartStyle) {
		String style = com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.colourStyleTypeMap.get(chartStyle);
		System.out.println("Style =" + style);
		
		switch (style) {
		case "Red Org Green":
			System.out.println("Setting range3kpiRev");
			return "range3kpiRev";
			
		
		case "Green Org Red":
			System.out.println("Setting range3kpi");
			return "range3kpi";
			
		case "Red Org Yellow Green":
			System.out.println("Setting range5kpi");
			return "range5kpi";	
			
		case "Green Yellow Org Red":
			System.out.println("Setting range5kpiRev");
			return "range5kpiRev";	
			
		default:
			System.out.println("Setting tnpm7");
			return "tnpm7";
		}
	}
}
