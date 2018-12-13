package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.BaselineChartWidget;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class BaselineChartInitializer {
	public static BaselineChartWidget initBaselineChartWidget(String chartName,
			ArrayList<String> filteredList) {
		
		BaselineChartWidget baselineChartWidget = new BaselineChartWidget();
		
		ArrayList<String> autoResizeProp = ListUtil.getFilteredList(chartName+".autoResize.enable", filteredList);
		String autoResizeEnable = autoResizeProp.size()>0?ListUtil.getValue(autoResizeProp.get(0)):null;
		baselineChartWidget.setAutoResize((autoResizeEnable==null)?false:Boolean.parseBoolean(autoResizeEnable));
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		baselineChartWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		baselineChartWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		baselineChartWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		baselineChartWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
				
		ArrayList<String> customLabelsProps = ListUtil.getFilteredList(chartName+".customLabels", filteredList);
		CustomLabels customLabel = ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps);
		if(customLabel!=null)
			baselineChartWidget.setCustomLabels(ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps));
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		baselineChartWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		baselineChartWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
	
		return baselineChartWidget;
		
	}

}
