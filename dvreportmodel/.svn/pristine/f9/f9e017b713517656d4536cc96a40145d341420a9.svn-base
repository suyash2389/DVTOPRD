package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.MeticsFormat;
import com.ibm.csi.npm.dvreportmodel.TopNTable;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class TopNTableWidgetInitializer {
	public static TopNTable initTopNTableWidget(String name,ArrayList<String> filteredList)
	{
		TopNTable topNTable = new TopNTable();
		
		ArrayList<String> SumRowProps = ListUtil.getFilteredList(name+".SumRow", filteredList);
		topNTable.setSumRow(TableWidgetMemberInitializer.initSumRow(name,SumRowProps));
		
		ArrayList<String> metricsProps = ListUtil.getFilteredList(name+".metrics", filteredList);
		
		MeticsFormat meticsFormat = TableWidgetMemberInitializer.initMetricFormat(name,metricsProps);
		topNTable.setMetricFormat(meticsFormat);
		
		ArrayList<String> mainColumnLabelProps = ListUtil.getFilteredList(name+".mainColumn.label", filteredList);
		if(mainColumnLabelProps.size()>0)
			topNTable.setMainColumnLabel(ListUtil.getValue(mainColumnLabelProps.get(0)));
		
		ArrayList<String> statsLabelsProps = ListUtil.getFilteredList(name+".stats.labels", filteredList);
		if(statsLabelsProps.size()>0)
			topNTable.setStatsLabels(ListUtil.getValue(statsLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> propertiesLabelsProps = ListUtil.getFilteredList(name+".properties.labels", filteredList);
		if(propertiesLabelsProps.size()>0)
			topNTable.setPropertiesLabels(ListUtil.getValue(propertiesLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> metricsLabelsProps = ListUtil.getFilteredList(name+".metrics.labels", filteredList);
		if(metricsLabelsProps.size()>0)
			topNTable.setMetricsLabels(ListUtil.getValue(metricsLabelsProps.get(0).replace("+", ",")));
		
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(name+".drilldown", filteredList);
		topNTable.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		
		return topNTable;
	}

}
