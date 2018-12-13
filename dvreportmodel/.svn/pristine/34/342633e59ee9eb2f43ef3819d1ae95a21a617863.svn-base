package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ibm.csi.npm.dvreportmodel.Display;
import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.LeafDrilldown;
import com.ibm.csi.npm.dvreportmodel.MeticsFormat;
import com.ibm.csi.npm.dvreportmodel.MetricFormatWithTH;
import com.ibm.csi.npm.dvreportmodel.Setting;
import com.ibm.csi.npm.dvreportmodel.SumRow;
import com.ibm.csi.npm.dvreportmodel.TableFormulaWithTh;
import com.ibm.csi.npm.dvreportmodel.TableFormulaWithoutTH;
import com.ibm.csi.npm.dvreportmodel.TableGroup;
import com.ibm.csi.npm.dvreportmodel.ThValue;
import com.ibm.csi.npm.dvreportmodel.Threshold;
import com.ibm.csi.npm.dvreportmodel.ViolationType;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class TableWidgetMemberInitializer {
	
	public static ArrayList<TableFormulaWithTh> initTableFormulasWithTh(String tableName,ArrayList<String> filteredList)
	{
		ArrayList<TableFormulaWithTh> formulaList = new ArrayList<TableFormulaWithTh>();
		
		//getting all distict formula names
		Set<String> formulaNameSet = new HashSet<String>();
		Iterator<String> formulaPropItr = filteredList.iterator();
		while(formulaPropItr.hasNext())
		{
			String prop = formulaPropItr.next();
			String[] propArray = prop.split("\\.");
			formulaNameSet.add(propArray[1]);
		}
		Iterator<String> formulaNameSetItr = formulaNameSet.iterator();
		while(formulaNameSetItr.hasNext())
		{
			String formulaName = formulaNameSetItr.next();
			ArrayList<String> formulaProps = ListUtil.getFilteredList(tableName+"."+formulaName, filteredList);
			TableFormulaWithTh tableFormula = initTableFormulaWithTh(tableName,formulaName,formulaProps);			
			formulaList.add(tableFormula);
		}
		return formulaList;
	}
	
	public static ArrayList<TableFormulaWithoutTH> initTableFormulasWithoutTh(String tableName,ArrayList<String> filteredList)
	{
		ArrayList<TableFormulaWithoutTH> formulaList = new ArrayList<TableFormulaWithoutTH>();
		
		//getting all distict formula names
		Set<String> formulaNameSet = new HashSet<String>();
		Iterator<String> formulaPropItr = filteredList.iterator();
		while(formulaPropItr.hasNext())
		{
			String prop = formulaPropItr.next();
			String[] propArray = prop.split("\\.");
			formulaNameSet.add(propArray[1]);
		}
		Iterator<String> formulaNameSetItr = formulaNameSet.iterator();
		while(formulaNameSetItr.hasNext())
		{
			String formulaName = formulaNameSetItr.next();
			ArrayList<String> formulaProps = ListUtil.getFilteredList(tableName+"."+formulaName, filteredList);
			TableFormulaWithoutTH tableFormula = initTableFormulaWithoutTh(tableName,formulaName,formulaProps);			
			formulaList.add(tableFormula);
		}
		return formulaList;
	}
	
	public static TableFormulaWithoutTH initTableFormulaWithoutTh(String tableName,String formulaName,ArrayList<String> filteredList)
	{
		TableFormulaWithoutTH tableFormula = new TableFormulaWithoutTH();
		tableFormula.setFormulaId(formulaName);
		
		ArrayList<String> statsProp = ListUtil.getFilteredList(tableName+"."+formulaName+".stats.labels", filteredList);
		String stats =statsProp.size()>0?ListUtil.getValue(statsProp.get(0)):null;
		tableFormula.setStatsLabel((stats==null)?null:stats.replace("+", ""));
		
		ArrayList<String> metricsProp = ListUtil.getFilteredList(tableName+"."+formulaName, filteredList);
		tableFormula.setFormula(initMetricFormat(tableName+"."+formulaName,metricsProp));
		return tableFormula;
	}
	
	public static TableFormulaWithTh initTableFormulaWithTh(String tableName,String formulaName,ArrayList<String> filteredList)
	{
		TableFormulaWithTh tableFormula = new TableFormulaWithTh();
		tableFormula.setFormulaId(formulaName);
		
		ArrayList<String> statsProp = ListUtil.getFilteredList(tableName+"."+formulaName+".stats.labels", filteredList);
		String stats =ListUtil.getValue(statsProp.get(0));
		if(stats!=null)
		{
			tableFormula.setStatsLabel(stats.replace("+", ""));
		}else
		{
			tableFormula.setStatsLabel("");
		}
		
		
		ArrayList<String> metricsProp = ListUtil.getFilteredList(tableName+"."+formulaName, filteredList);
		tableFormula.setMetricFormat(initMetricFormatWithTH(tableName+"."+formulaName,metricsProp));
		
		ArrayList<String> drilldownReporterProp = ListUtil.getFilteredList(tableName+"."+formulaName+".drilldown", filteredList);
		tableFormula.setDrilldown(initDrilldown(drilldownReporterProp));
		return tableFormula;
	}

	public static MetricFormatWithTH initMetricFormatWithTH(String path,
			ArrayList<String> metricsProp) {
		MetricFormatWithTH metricFormatWithTH = new MetricFormatWithTH();
		
		ArrayList<String> labelProp = ListUtil.getFilteredList(path+".metrics.labels", metricsProp);
		String stats =ListUtil.getValue(labelProp.get(0));
		metricFormatWithTH.setLabel(stats);
		
		ArrayList<String> formatAsProp = ListUtil.getFilteredList(path+".metrics.format.FormatAs", metricsProp);
		String formatAs =ListUtil.getValue(formatAsProp.get(0));
		metricFormatWithTH.setFormatAs(formatAs);
		
		ArrayList<String> formatSettingsProps = ListUtil.getFilteredList(path+".metrics.format.setting", metricsProp);
		metricFormatWithTH.setSetting(initMeticsFormatSettings(formatSettingsProps));
		
		ArrayList<String> formatThresholdProps = ListUtil.getFilteredList(path+".thresholds", metricsProp);
		
		Threshold th = initThreshold(path+".thresholds",formatThresholdProps);
		if(th!=null)
		metricFormatWithTH.setThreshold(th);
		
		return metricFormatWithTH;
	}
	
	public static MeticsFormat initMetricFormat(String path,
			ArrayList<String> metricsProp) {
		MeticsFormat meticsFormat = new MeticsFormat();
		
		ArrayList<String> labelProp = ListUtil.getFilteredList(path+".metrics.labels", metricsProp);
		String label =(labelProp.size()>0)?ListUtil.getValue(labelProp.get(0)):"";
		meticsFormat.setLabel(label);
		
		ArrayList<String> formatAsProp = ListUtil.getFilteredList(path+".metrics.format.FormatAs", metricsProp);
		String formatAs =ListUtil.getValue(formatAsProp.get(0));
		meticsFormat.setFormatAs(formatAs);
		
		ArrayList<String> formatSettingsProps = ListUtil.getFilteredList(path+".metrics.format.setting", metricsProp);
		meticsFormat.setSetting(initMeticsFormatSettings(formatSettingsProps));
		
		
		return meticsFormat;
	}


	private static Threshold initThreshold(String path,
			ArrayList<String> formatThresholdProps) {
		
		ArrayList<String> statsProp = ListUtil.getFilteredList(path+".stats", formatThresholdProps);
		String stats =ListUtil.getValue(statsProp.get(0));
		
		if(stats!=null && !stats.equals(""))
		{
		Threshold threshold = new Threshold();
		threshold.setStats(stats.replace("+", ""));
		
		ArrayList<String> groupBasedProp = ListUtil.getFilteredList(path+".GroupBased", formatThresholdProps);
		String groupBased =ListUtil.getValue(groupBasedProp.get(0));
		threshold.setGroupBased((groupBased==null)?false:Boolean.parseBoolean(groupBased));
		
		ArrayList<String> overProp = ListUtil.getFilteredList(path+".over", formatThresholdProps);
		String over =ListUtil.getValue(overProp.get(0));
		threshold.setOver((over==null)?false:Boolean.parseBoolean(over));
		
		//ArrayList<String> statsProp = ListUtil.getFilteredList(path+".stats", formatThresholdProps);
		//String stats =ListUtil.getValue(statsProp.get(0));
		//threshold.setStats((stats==null)?"":stats);
		
		ArrayList<String> timeSpanProp = ListUtil.getFilteredList(path+".timeSpan", formatThresholdProps);
		String timeSpan =ListUtil.getValue(timeSpanProp.get(0));
		threshold.setTimeSpan((timeSpan==null)?"":timeSpan);
		
		ArrayList<String> thValueProps = ListUtil.getFilteredList(path+".values", formatThresholdProps);
		threshold.setValue(initThValues(thValueProps));
		
		ArrayList<String> violationTypeProps = ListUtil.getFilteredList(path+".violationType", formatThresholdProps);
		threshold.setViolationType(initViolationType(violationTypeProps));
		
		ArrayList<String> drilldownReporterProp = ListUtil.getFilteredList(path+".drilldown", formatThresholdProps);	
		threshold.setDrilldown(initDrilldown(drilldownReporterProp));
		
		//threshold.setStats(stats);
		
		return threshold;
		}else
		{
			return null;
		}
	}
	

	public static Drilldown initDrilldown(
			ArrayList<String> drilldownReporterProp) {
		Drilldown drilldown = null;
		if(drilldownReporterProp!=null&&drilldownReporterProp.size()>0)
		{
			String reporter =ListUtil.getValue(drilldownReporterProp.get(0));		
			String leafReporter =drilldownReporterProp.size()>1?ListUtil.getValue(drilldownReporterProp.get(1)):null;
			LeafDrilldown leafDrilldown= new LeafDrilldown();
			if(leafReporter!=null&&!leafReporter.equalsIgnoreCase("null"))
			leafDrilldown.setReporter("DVR-"+leafReporter);
			drilldown = new Drilldown();
			if(reporter!=null&&!reporter.equalsIgnoreCase("null"))
			drilldown.setReporter("DVR-"+reporter);
			drilldown.setLeaf(leafDrilldown);
	      }
		return drilldown;
		
	}

	private static ViolationType initViolationType(
			ArrayList<String> violationTypeProps) {
		ViolationType violationType = new ViolationType();
		
		Iterator<String> violationTypePropsItr = violationTypeProps.iterator();
		while(violationTypePropsItr.hasNext())
		{
			String prop = violationTypePropsItr.next();
			HashMap<String, String>  violationTypePropMap = ListUtil.getPropValPair(prop);
			if(violationTypePropMap.containsKey("Baseline"))
			{
				String val = violationTypePropMap.get("Baseline");
				violationType.setBaseline((val==null)?false:Boolean.parseBoolean(val));
			}
			if(violationTypePropMap.containsKey("Burst"))
			{	
				String val = violationTypePropMap.get("Burst");
				violationType.setBurst((val==null)?false:Boolean.parseBoolean(val));
				
			}
			if(violationTypePropMap.containsKey("Period"))
			{
				String val = violationTypePropMap.get("Period");
				violationType.setPeriod((val==null)?false:Boolean.parseBoolean(val));
				
			}	
			if(violationTypePropMap.containsKey("Risk"))
			{
				String val = violationTypePropMap.get("Risk");
				violationType.setRisk((val==null)?false:Boolean.parseBoolean(val));
				
			}		
		}		
		
		return violationType;
	}

	private static ThValue initThValues(ArrayList<String> thValueProps) {
		ThValue thValue = new ThValue();
		Iterator<String> thValuePropItr = thValueProps.iterator();
		while(thValuePropItr.hasNext())
		{
			String prop = thValuePropItr.next();
			HashMap<String, String>  thValuePropMap = ListUtil.getPropValPair(prop);
			if(thValuePropMap.containsKey("critical"))
			{
				String val = thValuePropMap.get("critical");
				thValue.setCritical((val==null)?0:Integer.parseInt(val));			
			}
			if(thValuePropMap.containsKey("warning"))
			{				
				String val = thValuePropMap.get("warning");
				thValue.setCritical((val==null)?0:Integer.parseInt(val));		
			}
			
		}
		return thValue;
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
	
	public static SumRow initSumRow(String name, ArrayList<String> sumRowProps) {
		SumRow sumRow = new SumRow();
		
		ArrayList<String> SumRowProps = ListUtil.getFilteredList(name+".SumRow.display", sumRowProps);
		sumRow.setDisplay(initDisplay(SumRowProps));
		
		ArrayList<String> SumRowLabelProps = ListUtil.getFilteredList(name+".SumRow.label", sumRowProps);
		String val = ListUtil.getValue(SumRowLabelProps.get(0));
		sumRow.setLabel((val==null)?"":val);
		
		
		ArrayList<String> SumRowNoValueTagProps = ListUtil.getFilteredList(name+".SumRow.NoValueTag", sumRowProps);
		sumRow.setNoValueTag(ListUtil.getValue(SumRowNoValueTagProps.get(0)));
		
		return sumRow;
	}


	private static Display initDisplay(ArrayList<String> sumRowProps) {
		
		Display display = new Display();
		Iterator<String> displayPropsItr = sumRowProps.iterator();
		while(displayPropsItr.hasNext())
		{
			String prop = displayPropsItr.next();
			HashMap<String, String>  displayPropMap = ListUtil.getPropValPair(prop);
			if(displayPropMap.containsKey("bottom"))
			{
				String val = displayPropMap.get("bottom");
				display.setBottom((val==null)?false:Boolean.parseBoolean(val));
			}
			if(displayPropMap.containsKey("top"))
			{				
				String val = displayPropMap.get("top");
				display.setTop((val==null)?false:Boolean.parseBoolean(val));	
			}
			
		}
		
		return display;
	}


	public static TableGroup initGroup(String name,
			ArrayList<String> groupProps) {
		TableGroup tableGroup = new TableGroup();
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(name+".group.drilldown", groupProps);
		tableGroup.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
		
		ArrayList<String> lableProp = ListUtil.getFilteredList(name+".group.label", groupProps);
		tableGroup.setLabel(ListUtil.getValue(lableProp.get(0)));
		
		ArrayList<String> showResourceCountProp = ListUtil.getFilteredList(name+".group.showResourceCount", groupProps);
		String val = ListUtil.getValue(showResourceCountProp.get(0));
		tableGroup.setShowResourceCount((val==null)?false:Boolean.parseBoolean(val));
		
		return tableGroup;
	}
}
