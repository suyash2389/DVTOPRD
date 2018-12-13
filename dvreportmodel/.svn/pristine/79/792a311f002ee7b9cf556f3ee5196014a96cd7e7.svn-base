package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.MeticsFormat;
import com.ibm.csi.npm.dvreportmodel.ThViolationTableWidget;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class ThViolationTableWidgetInitializer {
	public static ThViolationTableWidget initThViolationTableWidget(String name,ArrayList<String> filteredList)
	{
		ThViolationTableWidget thViolationTableWidget = new ThViolationTableWidget();
		
		///ArrayList<String> SumRowProps = ListUtil.getFilteredList(name+".SumRow", filteredList);
		//thViolationTableWidget.setSumRow(TableWidgetMemberInitializer.initSumRow(name,SumRowProps));
		
		ArrayList<String> metricsProps = ListUtil.getFilteredList(name+".metrics", filteredList);
		
		MeticsFormat meticsFormat = TableWidgetMemberInitializer.initMetricFormat(name,metricsProps);
		thViolationTableWidget.setMetricFormat(meticsFormat);
		
		
		
		ArrayList<String> statsLabelsProps = ListUtil.getFilteredList(name+".stats.labels", filteredList);
		if(statsLabelsProps.size()>0)
			thViolationTableWidget.setStatsLabels(ListUtil.getValue(statsLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> propertiesLabelsProps = ListUtil.getFilteredList(name+".properties.labels", filteredList);
		if(propertiesLabelsProps.size()>0)
			thViolationTableWidget.setPropertiesLabels(ListUtil.getValue(propertiesLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> metricsLabelsProps = ListUtil.getFilteredList(name+".metrics.labels", filteredList);
		if(metricsLabelsProps.size()>0)
			thViolationTableWidget.setMetricsLabels(ListUtil.getValue(metricsLabelsProps.get(0).replace("+", ",")));
		
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(name+".drilldown", filteredList);
		thViolationTableWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		
		return thViolationTableWidget;
	}

}
