package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.MeticsFormat;
import com.ibm.csi.npm.dvreportmodel.RankVariationTableWidget;
import com.ibm.csi.npm.dvreportmodel.TopNTable;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RankVariationTableWidgetInitializer {
	public static RankVariationTableWidget initRankVariationTableWidget(String name,ArrayList<String> filteredList)
	{
		RankVariationTableWidget rankVariationTableWidget = new RankVariationTableWidget();
		
		ArrayList<String> resourceColumnLabelProps = ListUtil.getFilteredList(name+".resourceColumn.label", filteredList);
		if(resourceColumnLabelProps.size()>0)
			rankVariationTableWidget.setResourceColumnLabel(ListUtil.getValue(resourceColumnLabelProps.get(0)));
		else
			rankVariationTableWidget.setResourceColumnLabel("Name");
		
		ArrayList<String> statsLabelsProps = ListUtil.getFilteredList(name+".stats.labels", filteredList);
		if(statsLabelsProps.size()>0)
			rankVariationTableWidget.setStatsLabels(ListUtil.getValue(statsLabelsProps.get(0).replace("+", ",")));
		
		
		ArrayList<String> metricsLabelsProps = ListUtil.getFilteredList(name+".metrics.labels", filteredList);
		if(metricsLabelsProps.size()>0)
			rankVariationTableWidget.setMetricsLabels(ListUtil.getValue(metricsLabelsProps.get(0).replace("+", ",")));
		
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(name+".drilldown", filteredList);
		rankVariationTableWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		
		
		return rankVariationTableWidget;
	}

}
