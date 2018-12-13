package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.csi.npm.dvreportmodel.BarHorizWidget;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.StackBarHorizWidget;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class BarHorizInitializer {
	private static final Logger logger = LogManager.getLogger(BarHorizInitializer.class);
	public static BarHorizWidget initBarHorizWidget(String chartName,
			ArrayList<String> filteredList) {
		BarHorizWidget barHorizWidget = new BarHorizWidget();
		try{
		ArrayList<String> autoResizeEnableProp =  ListUtil.getFilteredList(chartName+".autoResize.enable", filteredList);
		String autoResizeEnable = (autoResizeEnableProp.size()>0)?ListUtil.getValue(autoResizeEnableProp.get(0)):null;
		barHorizWidget.setAutoResize((autoResizeEnable==null)?false:Boolean.parseBoolean(autoResizeEnable));
		
		String title = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Title", filteredList).get(0));
		barHorizWidget.setTitle(title);
		
		String footer = ListUtil.getValue(ListUtil.getFilteredList(chartName+".Footer", filteredList).get(0));
		barHorizWidget.setFooter(footer);
		
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(chartName+".chartStyle", filteredList).get(0));
		barHorizWidget.setChartStyle(Integer.parseInt(chartStyle));
		
		String realTimeSupport = ListUtil.getValue(ListUtil.getFilteredList(chartName+".realTimeSupport", filteredList).get(0));
		barHorizWidget.setRealTimeSupport(Boolean.parseBoolean(realTimeSupport));
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(chartName+".drilldown", filteredList);
		barHorizWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		
		
		
		ArrayList<String> realTimeParametersProps = ListUtil.getFilteredList(chartName+".realTimeParameters", filteredList);
		barHorizWidget.setRealTimeParameters(ChartWidgetMemberInitializer.initRealTimeParameters(realTimeParametersProps));
		}catch(Exception e){
			logger.error("Exception occured", e);
		}
		return barHorizWidget;
		
	}

}
