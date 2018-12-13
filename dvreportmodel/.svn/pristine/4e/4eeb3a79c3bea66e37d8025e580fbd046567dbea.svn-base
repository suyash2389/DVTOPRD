package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ibm.csi.npm.dvreportmodel.Display;
import com.ibm.csi.npm.dvreportmodel.GstTableWidget;
import com.ibm.csi.npm.dvreportmodel.SumRow;
import com.ibm.csi.npm.dvreportmodel.TableGroup;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class GstTableWidgetInitializer {
	public static GstTableWidget initGstTableWidget(String name,ArrayList<String> filteredList)
	{
		GstTableWidget gstTableWidget = new GstTableWidget();
		
		ArrayList<String> chartErrorThresholdPercentageProp = ListUtil.getFilteredList(name+".chartErrorThresholdPercentage", filteredList);
		String chartErrorThresholdPercentage =ListUtil.getValue(chartErrorThresholdPercentageProp.get(0));
		if(chartErrorThresholdPercentage!=null && !chartErrorThresholdPercentage.equals(""))
			gstTableWidget.setChartErrorThresholdPercentage(Integer.parseInt(chartErrorThresholdPercentage));
		
		ArrayList<String> chartWarningThresholdPercentageProp = ListUtil.getFilteredList(name+".chartWarningThresholdPercentage", filteredList);
		String chartWarningThresholdPercentage =ListUtil.getValue(chartWarningThresholdPercentageProp.get(0));
		if(chartWarningThresholdPercentage!=null && !chartWarningThresholdPercentage.equals(""))
			gstTableWidget.setChartErrorThresholdPercentage(Integer.parseInt(chartWarningThresholdPercentage));
		
		ArrayList<String> groupProps = ListUtil.getFilteredList(name+".group", filteredList);
		gstTableWidget.setGroup(TableWidgetMemberInitializer.initGroup(name,groupProps));
		
		ArrayList<String> SumRowProps = ListUtil.getFilteredList(name+".SumRow", filteredList);
		gstTableWidget.setSumRow(TableWidgetMemberInitializer.initSumRow(name,SumRowProps));
		
		ArrayList<String> formulaProps = ListUtil.getFilteredList(name+".formula", filteredList);
		
		gstTableWidget.getFormula().addAll(TableWidgetMemberInitializer.initTableFormulasWithTh(name, formulaProps));
		
		return gstTableWidget;
	}

	
	

}
