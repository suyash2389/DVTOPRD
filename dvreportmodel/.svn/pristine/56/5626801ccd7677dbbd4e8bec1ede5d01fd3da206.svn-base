package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.AreaChartWidget;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.StackAreaChartWidget;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class StackAreaChartInitializer {
	public static StackAreaChartWidget initStackAreaChartWidgetWidget(String chartName,
			ArrayList<String> filteredList) {
		
		StackAreaChartWidget stackAreaChartWidget = new StackAreaChartWidget();
		
		ArrayList<String> autoResizeProp = ListUtil.getFilteredList(chartName+".autoResize.enable", filteredList);
		String autoResizeEnable = autoResizeProp.size()>0?ListUtil.getValue(autoResizeProp.get(0)):null;
		stackAreaChartWidget.setAutoResize((autoResizeEnable==null)?false:Boolean.parseBoolean(autoResizeEnable));
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		stackAreaChartWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		stackAreaChartWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		stackAreaChartWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		stackAreaChartWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
		
		
		ArrayList<String> customLabelsProps = ListUtil.getFilteredList(chartName+".customLabels", filteredList);
		CustomLabels customLabel = ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps);
		if(customLabel!=null)
			stackAreaChartWidget.setCustomLabels(ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps));
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		stackAreaChartWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		stackAreaChartWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
	
		return stackAreaChartWidget;
		
	}

}
