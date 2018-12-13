package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.PieChartWidget;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;


public class PieChartInitializer {
	public static PieChartWidget initPieChartWidget(String chartName,
			ArrayList<String> filteredList) {
		PieChartWidget pieChartWidget = new PieChartWidget();
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		pieChartWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		pieChartWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		pieChartWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		pieChartWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
		
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		pieChartWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		pieChartWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
	
		return pieChartWidget;
		
		
	}
}
