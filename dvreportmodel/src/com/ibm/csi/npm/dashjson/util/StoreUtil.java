package com.ibm.csi.npm.dashjson.util;

public class StoreUtil {

	
	public static String processQuantile(String chartStyle) {
		
		char[] temp = chartStyle.toCharArray();
		chartStyle="";
		boolean flag = true;
		for (int i = 0; i < temp.length; i++) {
			
			if (temp[i]=='{') { flag=false;}
			
			if (!flag) {
				if (temp[i]=='}') {
					flag=true;
				}
			}
			if (flag) {
				chartStyle=chartStyle+temp[i];
			}		
		}
		
		return chartStyle;
	}
}
