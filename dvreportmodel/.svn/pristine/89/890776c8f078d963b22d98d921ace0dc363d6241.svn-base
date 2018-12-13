package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Baseline;
import com.ibm.csi.npm.dvreportmodel.BaselineMediator;
import com.ibm.csi.npm.dvreportmodel.Granularity;
import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class BaselineMediatorInitializer {
	public static Mediator initBaselineMediator(String name,
		ArrayList<String> filteredList) {
		BaselineMediator  baselineMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(name+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			baselineMediator = new BaselineMediator();
			
			String stats = ListUtil.getValue(ListUtil.getFilteredList(name+".stats", filteredList).get(0));
			String metric = ListUtil.getValue(ListUtil.getFilteredList(name+".metrics", filteredList).get(0));			
			
			ArrayList<String> baselineProps = ListUtil.getFilteredList(name+".baseline", filteredList);
			Baseline baseline = initBaselineProps(name,baselineProps);
			baselineMediator.setBaseline(baseline);
			
			ArrayList<String> baselineGranularityProps = ListUtil.getFilteredList(name+".Granularity", filteredList);
			Granularity granularity = initBaselineGranularityProps(name,baselineGranularityProps);;
			baselineMediator.setGranularity(granularity);	
		
			baselineMediator.setMetric(metric);
			baselineMediator.setStats(stats);		

		}
		return baselineMediator;
	}

	private static Granularity initBaselineGranularityProps(String name,
			ArrayList<String> baselineGranularityProps) {
		
		Granularity granularity = new Granularity();
		String daily = ListUtil.getValue(ListUtil.getFilteredList(name+".Granularity.daily", baselineGranularityProps).get(0));
		String monthly = ListUtil.getValue(ListUtil.getFilteredList(name+".Granularity.monthly", baselineGranularityProps).get(0));
		String monthly14 = ListUtil.getValue(ListUtil.getFilteredList(name+".Granularity.14months", baselineGranularityProps).get(0));
		String quarterly = ListUtil.getValue(ListUtil.getFilteredList(name+".Granularity.quarterly", baselineGranularityProps).get(0));
		String weekly = ListUtil.getValue(ListUtil.getFilteredList(name+".Granularity.weekly", baselineGranularityProps).get(0));
		String yearly = ListUtil.getValue(ListUtil.getFilteredList(name+".Granularity.yearly", baselineGranularityProps).get(0));
		
		if(daily!=null&&!daily.equals(""))
			granularity.setDaily(daily);
		
		if(monthly!=null&&!monthly.equals(""))
			granularity.setMonthly(monthly);
		
		if(monthly14!=null&&!monthly14.equals(""))
			granularity.setMonths14(monthly14);
		
		if(quarterly!=null&&!quarterly.equals(""))
			granularity.setQuarterly(quarterly);
		
		if(weekly!=null&&!weekly.equals(""))
			granularity.setWeekly(weekly);
		
		if(yearly!=null&&!yearly.equals(""))
			granularity.setYearly(yearly);
		
		return granularity;
	}

	private static Baseline initBaselineProps(String name,
			ArrayList<String> baselineProps) {
		Baseline baseline = new Baseline();
		String periods = ListUtil.getValue(ListUtil.getFilteredList(name+".baseline.periods", baselineProps).get(0));
		if(periods!=null&&!periods.equals(""))
			baseline.setPeriods(periods);
		
		String stats = ListUtil.getValue(ListUtil.getFilteredList(name+".baseline.stats", baselineProps).get(0));
		if(stats!=null&&!stats.equals(""))
			baseline.setStats(stats);
		
		return baseline;
	}

}
