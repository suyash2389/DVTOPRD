package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.BusyHours;
import com.ibm.csi.npm.dvreportmodel.GTSNearRealTime;
import com.ibm.csi.npm.dvreportmodel.Granularity;
import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.Stats;
import com.ibm.csi.npm.dvreportmodel.TimeSeriesMediator;
import com.ibm.csi.npm.dvreportmodel.Trending;
import com.ibm.csi.npm.dvreportmodel.UpgradeCondition;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class TimeSeriesMediatorInitializer {

	public static Mediator initTimeSeriesMediator(String mediatorName,
			ArrayList<String> filteredList) {
		TimeSeriesMediator timeSeriesMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			timeSeriesMediator = new TimeSeriesMediator();
			timeSeriesMediator.setMetric(metrics.replace("+", ","));
			
			String advGrpTimeSeries = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".AdvGrpTimeSeries", filteredList).get(0));
			if(advGrpTimeSeries!=null&&!advGrpTimeSeries.equals(""))
				timeSeriesMediator.setAdvGrpTimeSeries(advGrpTimeSeries);
			
			String multiResources = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".MultiResources", filteredList).get(0));
			if(multiResources!=null && !multiResources.equals(""))
				timeSeriesMediator.setMultiResources(multiResources);
			
			String formula1ViolationType = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".formula1.violationType", filteredList).get(0));
			if(formula1ViolationType!=null&&!formula1ViolationType.equals(""))
				timeSeriesMediator.setFormula1ViolationType(formula1ViolationType);
			
			String metricFromUrl = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".metricFromUrl", filteredList).get(0));
			if(metricFromUrl!=null&&!metricFromUrl.equals(""))
				timeSeriesMediator.setMetricFromUrl(metricFromUrl);
			
			String properties = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".properties", filteredList).get(0));
			if(properties!=null&&!properties.equals(""))
				timeSeriesMediator.setProperties(properties);
			
			String reportPeriod = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".reportPeriod", filteredList).get(0));
			if(reportPeriod!=null&&!reportPeriod.equals(""))
				timeSeriesMediator.setReportPeriod(reportPeriod);
			
			String reportPeriodOffset = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".reportPeriodOffset", filteredList).get(0));
			if(reportPeriodOffset!=null&&!reportPeriodOffset.equals(""))
				timeSeriesMediator.setReportPeriodOffset(Integer.parseInt(reportPeriodOffset));
			
			String reportPeriodMultiplier = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".reportPeriodMultiplier", filteredList).get(0));
			if(reportPeriodMultiplier!=null&&!reportPeriodMultiplier.equals(""))
				timeSeriesMediator.setReportPeriodMultiplier(Integer.parseInt(reportPeriodMultiplier));
			
			String reportPeriodSliding = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".reportPeriodSliding.enable", filteredList).get(0));
			if(reportPeriodSliding!=null&&!reportPeriodSliding.equals(""))
				timeSeriesMediator.setReportPeriodSliding(Boolean.parseBoolean(reportPeriodSliding));
			
			String statFromURL = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".statFromURL", filteredList).get(0));
			if(statFromURL!=null&&!statFromURL.equals(""))
				timeSeriesMediator.setStatFromURL(statFromURL);
			
			String statsString = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".stats", filteredList).get(0));
			Stats stats= null;
			if(statsString!=null&&!statsString.equals("")){
				
				statsString = statsString.replace("+", ",");
				String[] statsArr = statsString.replace("+",",").split(",");
				ArrayList<String> statList = new ArrayList<String>();
				for(String stat:statsArr) 
				{
					statList.add(stat);
				}
				stats = new Stats();
				stats.getStat().addAll(statList);
			}
			timeSeriesMediator.setStats(stats);
				timeSeriesMediator.setStats(stats);
			
			ArrayList<String> busyHoursProps = ListUtil.getFilteredList(mediatorName+".busyHours", filteredList);
			BusyHours busyHours = initBusyHours(mediatorName,busyHoursProps);
			if(busyHours!=null)
				timeSeriesMediator.setBusyHours(busyHours);
			
			ArrayList<String> trendingProps = ListUtil.getFilteredList(mediatorName+".Trending", filteredList);
			Trending trending = initTrending(mediatorName,trendingProps);
			if(trending!=null)
				timeSeriesMediator.setTrending(trending);
			
			ArrayList<String> granularityProps = ListUtil.getFilteredList(mediatorName+".Granularity", filteredList);
			Granularity granularity = initGranularity(mediatorName,granularityProps);
			if(granularity!=null)
				timeSeriesMediator.setGranularity(granularity);
			
			ArrayList<String> gTSNearRealTimeProps = ListUtil.getFilteredList(mediatorName+".GTSNearRealTime", filteredList);
			GTSNearRealTime gTSNearRealTime = initGTSNearRealTime(mediatorName,gTSNearRealTimeProps);
			if(gTSNearRealTime!=null)
				timeSeriesMediator.setGtsNearRealTime(gTSNearRealTime);	
		}
		
		return timeSeriesMediator;
		
		
	}

	private static GTSNearRealTime initGTSNearRealTime(String mediatorName,
			ArrayList<String> gTSNearRealTimeProps) {
		GTSNearRealTime gTSNearRealTime = new GTSNearRealTime();
		
		String refreshRate = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".GTSNearRealTime.RefreshRate", gTSNearRealTimeProps).get(0));
		if(refreshRate!=null&&!refreshRate.equals(""))
			gTSNearRealTime.setRefreshRate(Integer.parseInt(refreshRate));
		
		String secondsToWait = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".GTSNearRealTime.secondsToWait", gTSNearRealTimeProps).get(0));
		if(secondsToWait!=null&&!secondsToWait.equals(""))
			gTSNearRealTime.setSecondsToWait(Integer.parseInt(secondsToWait));
		
		String enable = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".GTSNearRealTime.enable", gTSNearRealTimeProps).get(0));
		if(enable!=null&&!enable.equals(""))
			gTSNearRealTime.setEnable(Boolean.parseBoolean(enable));
		
		return gTSNearRealTime;
	}

	private static Granularity initGranularity(
			String mediatorName, ArrayList<String> granularityProps) {
		Granularity granularity = new Granularity();
		
		String months14 = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Granularity.14months", granularityProps).get(0));
		if(months14!=null&&!months14.equals(""))
			granularity.setMonths14(months14);
		
		String monthly = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Granularity.monthly", granularityProps).get(0));
		if(monthly!=null&&!monthly.equals(""))
			granularity.setMonthly(monthly);
		
		String daily = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Granularity.daily", granularityProps).get(0));
		if(daily!=null&&!daily.equals(""))
			granularity.setDaily(daily);
		
		String quarterly = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Granularity.quarterly", granularityProps).get(0));
		if(quarterly!=null&&!quarterly.equals(""))
			granularity.setQuarterly(quarterly);
		
		String weekly = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Granularity.weekly", granularityProps).get(0));
		if(weekly!=null&&!weekly.equals(""))
			granularity.setWeekly(weekly);
		
		String yearly = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Granularity.yearly", granularityProps).get(0));
		if(yearly!=null&&!yearly.equals(""))
			granularity.setYearly(yearly);
		
		return granularity;
	}

	private static Trending initTrending(String mediatorName,
			ArrayList<String> trendingProps) {
		Trending trending =new Trending();
		
		String trendingPeriodFromUrl = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.TrendingPeriodFromUrl", trendingProps).get(0));
		if(trendingPeriodFromUrl!=null&&!trendingPeriodFromUrl.equals(""))
			trending.setTrendingPeriodFromUrl(trendingPeriodFromUrl);
		
		String trendingPeriodSettings = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.TrendingPeriodSettings", trendingProps).get(0));
		if(trendingPeriodSettings!=null&&!trendingPeriodSettings.equals(""))
			trending.setTrendingPeriodSettings(trendingPeriodSettings);
		
		String displayTrend = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.displayTrend", trendingProps).get(0));
		if(displayTrend!=null&&!displayTrend.equals(""))
			trending.setDisplayTrend(displayTrend);
		
		ArrayList<String> upgradeConditionProps = ListUtil.getFilteredList(mediatorName+".Trending.UpgradeCondition", trendingProps);
		UpgradeCondition upgradeCondition = initUpgradeCondition(mediatorName,trendingProps);
		if(upgradeCondition!=null)
			trending.setUpgradeCondition(upgradeCondition);	
		
		return trending;
	}

	private static UpgradeCondition initUpgradeCondition(String mediatorName,
			ArrayList<String> trendingProps) {
		UpgradeCondition upgradeCondition = new UpgradeCondition();
		
		String baseProperty = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.UpgradeCondition.BaseProperty", trendingProps).get(0));
		if(baseProperty!=null&&!baseProperty.equals(""))
			upgradeCondition.setBaseProperty(baseProperty);
		
		String multiplierProperty = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.UpgradeCondition.MultiplierProperty", trendingProps).get(0));
		if(multiplierProperty!=null&&!multiplierProperty.equals(""))
			upgradeCondition.setMultiplierProperty(multiplierProperty);
		
		String defaults = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.UpgradeCondition.default", trendingProps).get(0));
		if(defaults!=null&&!defaults.equals(""))
			upgradeCondition.setDefault(defaults);
		
		String ucFromURL = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".Trending.UpgradeCondition.ucFromURL", trendingProps).get(0));
		if(ucFromURL!=null&&!ucFromURL.equals(""))
			upgradeCondition.setUcFromURL(ucFromURL);
		if(baseProperty!=null||multiplierProperty!=null||defaults!=null||ucFromURL!=null)
			return upgradeCondition;
		else
			return null;
	}

	private static BusyHours initBusyHours(String mediatorName,
			ArrayList<String> busyHoursProps) {
		BusyHours busyHours = new BusyHours();
		
		String enable = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".busyHours.enable", busyHoursProps).get(0));
		if(enable!=null&&!enable.equals(""))
			busyHours.setEnable(Boolean.parseBoolean(enable));
		
		String stats = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".busyHours.stats", busyHoursProps).get(0));
		if(stats!=null&&!stats.equals(""))
			busyHours.setStats(stats);
			
		return busyHours;
	}

}
