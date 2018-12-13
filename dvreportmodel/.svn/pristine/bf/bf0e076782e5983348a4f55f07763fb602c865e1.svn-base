package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.AreaChartWidget;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.RankVariationChartWidget;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RankVariationChartWidgetInitializer {
	public static RankVariationChartWidget initRankVariationChartWidget(String chartName,
			ArrayList<String> filteredList) {
		
		RankVariationChartWidget rankVariationChartWidget = new RankVariationChartWidget();
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		rankVariationChartWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		rankVariationChartWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		rankVariationChartWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		rankVariationChartWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		rankVariationChartWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		rankVariationChartWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
	
		return rankVariationChartWidget;
		
	}

}
