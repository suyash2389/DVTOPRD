package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.ForcastDate;
import com.ibm.csi.npm.dvreportmodel.RptTableMediator;
import com.ibm.csi.npm.dvreportmodel.UpgradeForcastCondition;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RptTableMediatorInitializer {
	public static RptTableMediator initRptTableMediator(String name,ArrayList<String> filteredList)
	{
		RptTableMediator rptTableMediator = new RptTableMediator();
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(name+".metric", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			
			rptTableMediator.setMetrics(metrics.replace("+", ","));
			
			
			String pageSize = ListUtil.getValue(ListUtil.getFilteredList(name+".pageSize", filteredList).get(0));			
			rptTableMediator.setPagesize(Integer.parseInt(pageSize));
			
			String properties = ListUtil.getValue(ListUtil.getFilteredList(name+".properties", filteredList).get(0));
			rptTableMediator.setProperties(properties!=null?properties.replace("+", ","):null);
			
			String handleRse = ListUtil.getValue(ListUtil.getFilteredList(name+".handleRSE", filteredList).get(0));
			Boolean isHandleRse = (handleRse==null)?false:Boolean.parseBoolean(handleRse);
			rptTableMediator.setHandleRSE(isHandleRse);
			
			String filter = ListUtil.getValue(ListUtil.getFilteredList(name+".filter", filteredList).get(0));
			rptTableMediator.setFilter(filter);
			
			String stats = ListUtil.getValue(ListUtil.getFilteredList(name+".stat", filteredList).get(0));
			rptTableMediator.setStats(stats.replace("+", ","));
			
			ArrayList<String> sortProp = ListUtil.getFilteredList(name+".sort", filteredList);
			rptTableMediator.setSort(TableMediatorMemberInitializer.initTableMediatorSort(sortProp));	
						
			ArrayList<String> forcastDateProps = ListUtil.getFilteredList(name+".ForecastDate", filteredList);
			ForcastDate forcastDate = initForcastDate(name,forcastDateProps);
			rptTableMediator.setForecastDate(forcastDate);
			
		}
		return rptTableMediator;
	}
	
	public static ForcastDate initForcastDate(String path,
			ArrayList<String> forcastDateProps) {
		ForcastDate forcastDateLabelProps = new ForcastDate();
		
		ArrayList<String> historicalTrendViewLimitProp = ListUtil.getFilteredList(path+".ForecastDate.HistoricalTrendViewLimit", forcastDateProps);
		String historicalTrendViewLimit =(historicalTrendViewLimitProp.size()>0)?ListUtil.getValue(historicalTrendViewLimitProp.get(0)):"";
		forcastDateLabelProps.setHistoricalTrendViewLimit(historicalTrendViewLimit);
		
		ArrayList<String> trendingPeriodSettingsProp = ListUtil.getFilteredList(path+".ForecastDate.TrendingPeriodSettings", forcastDateProps);
		String trendingPeriodSettings =(trendingPeriodSettingsProp.size()>0)?ListUtil.getValue(trendingPeriodSettingsProp.get(0)):"";
		forcastDateLabelProps.setTrendingPeriodSettings(trendingPeriodSettings);
		
		UpgradeForcastCondition forcastDateSubProps = initForcastDateSubProps(path+".ForecastDate.UpgradeCondition", forcastDateProps);		
		forcastDateLabelProps.setUpgradeForcastCondition(forcastDateSubProps);
		
		return forcastDateLabelProps;
	}

	private static UpgradeForcastCondition initForcastDateSubProps(String string,
			ArrayList<String> forcastDateProps) {
		UpgradeForcastCondition upgradeForcastCondition = new UpgradeForcastCondition();
		
		ArrayList<String> basePropertyProp = ListUtil.getFilteredList(string+".BaseProperty", forcastDateProps);
		String baseProperty =(basePropertyProp.size()>0)?ListUtil.getValue(basePropertyProp.get(0)):"";
		if(baseProperty != null) {
			upgradeForcastCondition.setBaseProperty(Double.parseDouble(baseProperty));
		}
		ArrayList<String> multiplierPropertyprop = ListUtil.getFilteredList(string+".MultiplierProperty", forcastDateProps);
		String multiplierProperty =(multiplierPropertyprop.size()>0)?ListUtil.getValue(multiplierPropertyprop.get(0)):"";
		if(multiplierProperty != null) {
			upgradeForcastCondition.setMultiplierProperty(Double.parseDouble(multiplierProperty));
		}
		ArrayList<String> defaultProp = ListUtil.getFilteredList(string+".default", forcastDateProps);
		String defaultProperty =(defaultProp.size()>0)?ListUtil.getValue(defaultProp.get(0)):"";
		if(defaultProperty != null) {
			upgradeForcastCondition.setBaseProperty(Double.parseDouble(defaultProperty));
		}
		return upgradeForcastCondition;
	}
}
