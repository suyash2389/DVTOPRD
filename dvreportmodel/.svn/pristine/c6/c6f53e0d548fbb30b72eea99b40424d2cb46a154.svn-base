package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ibm.csi.npm.dvreportmodel.ChartMetricLabel;
import com.ibm.csi.npm.dvreportmodel.ChartOtherLabel;
import com.ibm.csi.npm.dvreportmodel.ChartPropertiesLabel;
import com.ibm.csi.npm.dvreportmodel.CustomLabels;
import com.ibm.csi.npm.dvreportmodel.RealTimeParameter;
import com.ibm.csi.npm.dvreportmodel.RealTimeParameters;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class ChartWidgetMemberInitializer {
	

	public static RealTimeParameters initRealTimeParameters(
			ArrayList<String> realTimeParametersProps) {
		RealTimeParameters realTimeParameters = new RealTimeParameters();
		HashMap<String,String> chartRealTimeParametersMap =  ListUtil.getPropValPair(realTimeParametersProps);
		
		
		Iterator<String> chartRealTimeParametersMapIt = chartRealTimeParametersMap.keySet().iterator();
		while(chartRealTimeParametersMapIt.hasNext())
		{
			String realTimeParamName = chartRealTimeParametersMapIt.next();
			String realTimeParamValue = chartRealTimeParametersMap.get(realTimeParamName);
			if(realTimeParamValue!=null && !realTimeParamValue.equals(""))
			{
				RealTimeParameter realTimeParameter = new RealTimeParameter();
				realTimeParameter.setName(realTimeParamName);
				realTimeParameter.setValue(realTimeParamValue);
				realTimeParameters.getRealTimeParameter().add(realTimeParameter);
			}
		}
		return realTimeParameters;
	}

	public static CustomLabels initCustomLabels(String chartName,
			ArrayList<String> customLabelProps) {
		CustomLabels customLabels = new CustomLabels();
		
		ArrayList<String> metricLabelsProps = ListUtil.getFilteredList(chartName+".customLabels.metrics", customLabelProps);
		ArrayList<ChartMetricLabel> metricsLabels =initChartMetricsLabels(metricLabelsProps);
		
		if(metricsLabels.size()>0)
			customLabels.getMetricsLabels().addAll(metricsLabels);
		
		ArrayList<String> otherLabelsProps = ListUtil.getFilteredList(chartName+".customLabels.other", customLabelProps);
		ArrayList<ChartOtherLabel> otherlabels = initChartOtherLabels(otherLabelsProps);
		if(otherlabels.size()>0)
			customLabels.getOtherLabels().addAll(otherlabels);
		
		ArrayList<String> propertiesLabelsProps = ListUtil.getFilteredList(chartName+".customLabels.properties", customLabelProps);
		ArrayList<ChartPropertiesLabel> propertiesLabels =initChartPropertiesLabels(propertiesLabelsProps);
		if(propertiesLabels.size()>0)
		{
			customLabels.getPropertiesLabels().addAll(propertiesLabels);
		}
		if(metricsLabels.size()>0||otherlabels.size()>0||propertiesLabels.size()>0)
			return customLabels;
		else
			return null;
	}

	public static ArrayList<ChartPropertiesLabel> initChartPropertiesLabels(
			ArrayList<String> propertiesLabelsProps) {
		ArrayList<ChartPropertiesLabel> chartPropertiesLabels = new ArrayList<ChartPropertiesLabel>();
		HashMap<String,String> chartOtherLabelMap =  ListUtil.getPropValPair(propertiesLabelsProps);
		Iterator<String> chartOtherLabelMapIt = chartOtherLabelMap.keySet().iterator();
		while(chartOtherLabelMapIt.hasNext())
		{
			String propertyId = chartOtherLabelMapIt.next();
			String label = chartOtherLabelMap.get(propertyId);
			if(label!=null && !label.equals(""))
			{
				ChartPropertiesLabel chartPropertiesLabel = new ChartPropertiesLabel();
				chartPropertiesLabel.setPropertiesId(propertyId);
				chartPropertiesLabel.setLabel((label==null)?"":label);
				chartPropertiesLabels.add(chartPropertiesLabel);
			}
		}
		return chartPropertiesLabels;
	}

	public static ArrayList<ChartOtherLabel> initChartOtherLabels(
			ArrayList<String> otherLabelsProps) {
		ArrayList<ChartOtherLabel> chartOtherLabelList = new ArrayList<ChartOtherLabel>();
		HashMap<String,String> chartOtherLabelMap =  ListUtil.getPropValPair(otherLabelsProps);
		Iterator<String> chartOtherLabelMapIt = chartOtherLabelMap.keySet().iterator();
		while(chartOtherLabelMapIt.hasNext())
		{
			String name = chartOtherLabelMapIt.next();
			String label = chartOtherLabelMap.get(name);
			if(label!=null && !label.equals(""))
			{
				ChartOtherLabel chartOtherLabel = new ChartOtherLabel();
				chartOtherLabel.setName(name);
				chartOtherLabel.setLabel(label);
				chartOtherLabelList.add(chartOtherLabel);
			}
		}
		
		return chartOtherLabelList;
	}

	public static ArrayList<ChartMetricLabel> initChartMetricsLabels(
			ArrayList<String> metricLabelsProps) {
		ArrayList<ChartMetricLabel> meticsLabels =new ArrayList<ChartMetricLabel>();
		HashMap<String,String> formulaLabelMap =  ListUtil.getPropValPair(metricLabelsProps);
		Iterator<String> formulaNameSetIt = formulaLabelMap.keySet().iterator();
		while(formulaNameSetIt.hasNext())
		{
			String formulaName = formulaNameSetIt.next();
			String label = formulaLabelMap.get(formulaName);
			if(label!=null && !label.equals(""))
			{
				ChartMetricLabel chartMetricLabel = new ChartMetricLabel();
				chartMetricLabel.setFormulaId(formulaName);
				chartMetricLabel.setLabel((label==null)?"":label);
				meticsLabels.add(chartMetricLabel);
			}
		}	
		return meticsLabels;
	}		

}
