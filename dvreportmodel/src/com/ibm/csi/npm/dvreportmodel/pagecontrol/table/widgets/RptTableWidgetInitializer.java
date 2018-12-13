package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.MeticsFormat;
import com.ibm.csi.npm.dvreportmodel.RptTableWidget;
import com.ibm.csi.npm.dvreportmodel.Setting;
import com.ibm.csi.npm.dvreportmodel.UpgradeConditionLabels;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RptTableWidgetInitializer {
	
	public static RptTableWidget initRptTableWidget(String name,ArrayList<String> filteredList)
	{
		RptTableWidget rptTableWidget = new RptTableWidget();
		
		String drilldownReporterProp = ListUtil.getFilteredList(name+".drilldown.reporter", filteredList).get(0);
		String drilldownReporter = ListUtil.getValue(drilldownReporterProp);
		Drilldown drilldown = new Drilldown();
		drilldown.setReporter(drilldownReporter);
		rptTableWidget.setDrilldown(drilldown);
		
		String headingStats = ListUtil.getFilteredList(name+".headings.stats.display", filteredList).get(0);
		rptTableWidget.setHeadings(headingStats);
		
		String metricDisplay = ListUtil.getFilteredList(name+".metric.displayPrevious", filteredList).get(0);
		Boolean isMetricDisplay = (metricDisplay==null)?false:Boolean.parseBoolean(metricDisplay);
		rptTableWidget.setMetricDisplayPrevious(isMetricDisplay);
		
		ArrayList<String> metricsProps = ListUtil.getFilteredList(name+".metric", filteredList);
		
		MeticsFormat meticsFormat = initMetricFormat(name,metricsProps);
		rptTableWidget.setMetricFormat(meticsFormat);
		
		ArrayList<String> statsLabelsProps = ListUtil.getFilteredList(name+".stat.label", filteredList);
		if(statsLabelsProps.size()>0)
			rptTableWidget.setStatsLabels(ListUtil.getValue(statsLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> propertiesLabelsProps = ListUtil.getFilteredList(name+".properties.labels", filteredList);
		if(propertiesLabelsProps.size()>0)
			rptTableWidget.setPropertiesLabels(ListUtil.getValue(propertiesLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> metricsLabelsProps = ListUtil.getFilteredList(name+".metric.label", filteredList);
		if(metricsLabelsProps.size()>0)
			rptTableWidget.setMetricsLabels(ListUtil.getValue(metricsLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> propertiesResourceLabelsProps = ListUtil.getFilteredList(name+".resource.label", filteredList);
		if(propertiesResourceLabelsProps.size()>0)
			rptTableWidget.setResourceLabels(ListUtil.getValue(propertiesResourceLabelsProps.get(0).replace("+", ",")));
		
		ArrayList<String> upgradeConditionLabelProps = ListUtil.getFilteredList(name+".upgradeCondition", filteredList);
		UpgradeConditionLabels upgradeConditionLabels = initUpgradeLevelFormat(name,upgradeConditionLabelProps);
		rptTableWidget.setUpgradeConditionLabels(upgradeConditionLabels);
		
		return rptTableWidget;
	}
	
	public static MeticsFormat initMetricFormat(String path,
			ArrayList<String> metricsProp) {
		MeticsFormat meticsFormat = new MeticsFormat();
		
		ArrayList<String> labelProp = ListUtil.getFilteredList(path+".metric.label", metricsProp);
		String label =(labelProp.size()>0)?ListUtil.getValue(labelProp.get(0)):"";
		meticsFormat.setLabel(label);
		
		ArrayList<String> formatAsProp = ListUtil.getFilteredList(path+".metric.format.FormatAs", metricsProp);
		String formatAs =ListUtil.getValue(formatAsProp.get(0));
		meticsFormat.setFormatAs(formatAs);
		
		ArrayList<String> formatSettingsProps = ListUtil.getFilteredList(path+".metric.format.setting", metricsProp);
		meticsFormat.setSetting(initMeticsFormatSettings(formatSettingsProps));
		
		
		return meticsFormat;
	}
	
	private static Setting initMeticsFormatSettings(
			ArrayList<String> formatSettingsProps) {	
		Setting setting = new Setting();
		Iterator<String> settingsPropItr = formatSettingsProps.iterator();
		while(settingsPropItr.hasNext())
		{
			String prop = settingsPropItr.next();
			HashMap<String, String>  settingsProp = ListUtil.getPropValPair(prop);
			if(settingsProp.containsKey("nbOfDigits"))
			{
				String val = settingsProp.get("nbOfDigits");
				setting.setNbOfDigits((val==null)?0:Integer.parseInt(val));
			}
			if(settingsProp.containsKey("scalingFactor"))
			{				
				setting.setScalingFactor(settingsProp.get("scalingFactor"));
				
			}
			if(settingsProp.containsKey("useScientificNotationAbove"))
			{
				setting.setUseScientificNotationAbove(settingsProp.get("useScientificNotationAbove"));
				
			}			
		}	
		
		return setting;
	}
	public static UpgradeConditionLabels initUpgradeLevelFormat(String path,
			ArrayList<String> upgradeConditionLabelProps) {
		UpgradeConditionLabels upgradeConditionLabel = new UpgradeConditionLabels();
		
		ArrayList<String> labelProp = ListUtil.getFilteredList(path+".label", upgradeConditionLabelProps);
		String label =(labelProp.size()>0)?ListUtil.getValue(labelProp.get(0)):"";
		upgradeConditionLabel.setLabel(label);
		
		ArrayList<String> levelLabelProp = ListUtil.getFilteredList(path+".levelLabel", upgradeConditionLabelProps);
		String levelLabel =(levelLabelProp.size()>0)?ListUtil.getValue(levelLabelProp.get(0)):"";
		upgradeConditionLabel.setLevelLabel(levelLabel);
		
		ArrayList<String> predictedDateLabelProp = ListUtil.getFilteredList(path+".predictedDateLabel", upgradeConditionLabelProps);
		String predictedDateLabel =(predictedDateLabelProp.size()>0)?ListUtil.getValue(predictedDateLabelProp.get(0)):"";
		upgradeConditionLabel.setPredictedDateLabel(predictedDateLabel);
		
		ArrayList<String> ruleLabelProp = ListUtil.getFilteredList(path+".ruleLabel", upgradeConditionLabelProps);
		String ruleLabel =(ruleLabelProp.size()>0)?ListUtil.getValue(ruleLabelProp.get(0)):"";
		upgradeConditionLabel.setLabel(ruleLabel);
		
		ArrayList<String> trendDirLabelProp = ListUtil.getFilteredList(path+".trendDirLabel", upgradeConditionLabelProps);
		String trendDirLabel =(trendDirLabelProp.size()>0)?ListUtil.getValue(trendDirLabelProp.get(0)):"";
		upgradeConditionLabel.setLabel(trendDirLabel);
		
		
		return upgradeConditionLabel;
	}
}
