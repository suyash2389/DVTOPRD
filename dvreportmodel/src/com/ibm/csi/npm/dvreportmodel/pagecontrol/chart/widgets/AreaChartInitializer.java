package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.csi.npm.dvreportmodel.AreaChartWidget;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class AreaChartInitializer {
	private static final Logger logger = LogManager.getLogger(AreaChartInitializer.class);
	public static AreaChartWidget initAreaChartWidgetWidget(String chartName,
			ArrayList<String> filteredList) {
		
		AreaChartWidget areaChartWidget = new AreaChartWidget();
		try{
		ArrayList<String> autoResizeProp = ListUtil.getFilteredList(chartName+".autoResize.enable", filteredList);
		String autoResizeEnable = autoResizeProp.size()>0?ListUtil.getValue(autoResizeProp.get(0)):null;
		areaChartWidget.setAutoResize((autoResizeEnable==null)?false:Boolean.parseBoolean(autoResizeEnable));
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		areaChartWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		areaChartWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		areaChartWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		areaChartWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
		
		
		ArrayList<String> customLabelsProps = ListUtil.getFilteredList(chartName+".customLabels", filteredList);
		CustomLabels customLabel = ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps);
		if(customLabel!=null)
			areaChartWidget.setCustomLabels(ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps));
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		areaChartWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		areaChartWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
		}catch(Exception e){
			logger.error("Exception occured", e);
		}
		return areaChartWidget;
		
	}

}
