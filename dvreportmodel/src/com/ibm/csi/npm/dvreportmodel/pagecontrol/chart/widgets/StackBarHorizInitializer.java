package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.csi.npm.dvreportmodel.ChartMetricLabel;
import com.ibm.csi.npm.dvreportmodel.ChartOtherLabel;
import com.ibm.csi.npm.dvreportmodel.ChartPropertiesLabel;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.RealTimeParameter;
import com.ibm.csi.npm.dvreportmodel.RealTimeParameters;
import com.ibm.csi.npm.dvreportmodel.StackBarHorizWidget;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class StackBarHorizInitializer {
	private static final Logger logger = LogManager.getLogger(StackBarHorizInitializer.class);
	public static StackBarHorizWidget initStackBarHorizWidget(String chartName,
			ArrayList<String> filteredList) {
		StackBarHorizWidget stackBarHorizWidget = new StackBarHorizWidget();
		try{
		ArrayList<String> autoResizeEnableProp =  ListUtil.getFilteredList(chartName+".autoResize.enable", filteredList);
		String autoResizeEnable = (autoResizeEnableProp.size()>0)?ListUtil.getValue(autoResizeEnableProp.get(0)):null;
		stackBarHorizWidget.setAutoResize((autoResizeEnable==null)?false:Boolean.parseBoolean(autoResizeEnable));
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		stackBarHorizWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		stackBarHorizWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		stackBarHorizWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		stackBarHorizWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
		
				
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		stackBarHorizWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		
		
		ArrayList<String> customLabelsProps = ListUtil.getFilteredList(chartName+".customLabels", filteredList);
		CustomLabels customLabel = ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps);
		if(customLabel!=null)
			stackBarHorizWidget.setCustomLabels(ChartWidgetMemberInitializer.initCustomLabels(chartName,customLabelsProps));
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		stackBarHorizWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
		
		stackBarHorizWidget.setColorRangeId(ListUtil.genrateColorRangeIdChartStyle(chartStyle));
		}catch(Exception e){
			logger.error("Exception occured", e);
		}
		return stackBarHorizWidget;
		
	}

}
