package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.ibm.csi.npm.dvreportmodel.ChartRange;
import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.ResDistribMediator;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class ResDistribMediatorInitializer {

	public static Mediator initResDistribMediator(String name,
			ArrayList<String> filteredList) {
		ResDistribMediator  resDistribMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(name+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			resDistribMediator = new ResDistribMediator();
			
			String stats = ListUtil.getValue(ListUtil.getFilteredList(name+".stats", filteredList).get(0));
			String provideAllRanges = ListUtil.getValue(ListUtil.getFilteredList(name+".provideAllRanges", filteredList).get(0));
			
			resDistribMediator.setMetric(metrics.replace("+", ""));
			resDistribMediator.setStats(stats.replace("+", ""));
			resDistribMediator.setProvideAllRanges((provideAllRanges==null)?false:Boolean.parseBoolean(provideAllRanges));
			
			ArrayList<String> rangesProps = ListUtil.getFilteredList(name+".range", filteredList);
			resDistribMediator.getRanges().addAll(initChartRanges(name,rangesProps));
			
		}
		return resDistribMediator;
	}

	private static ArrayList<ChartRange> initChartRanges(
			String mediatorName, ArrayList<String> rangesProps) {
		ArrayList<ChartRange> rangeList = new ArrayList<ChartRange>();
		//getting all ranges names
		TreeSet<String> rangeSet = new TreeSet<String>();
		Iterator<String> rangePropsItr = rangesProps.iterator();
		while(rangePropsItr.hasNext())
		{
			String prop = rangePropsItr.next();
			String[] propArray = prop.split("\\.");
			rangeSet.add(propArray[1]);
		}
		Iterator<String> rangeSetItr = rangeSet.iterator();
		while(rangeSetItr.hasNext())
		{
			String rangeName = rangeSetItr.next();
			ArrayList<String> rangeProps = ListUtil.getFilteredList(mediatorName+"."+rangeName, rangesProps);
			ChartRange chartRange = initRange(rangeProps);
			if(chartRange!=null)
				rangeList.add(chartRange);
		}
				
		
		return rangeList;
	}

	private static ChartRange initRange(ArrayList<String> rangeProps) {
		ChartRange chartRange = null;
		HashMap<String,String> rangeValueMap = ListUtil.getPropValPair(rangeProps);
		String valueOver = rangeValueMap.get("valueOver");
		String valueUnder = rangeValueMap.get("valueUnder");
		
		if(valueOver!=null ||valueUnder!=null)
		{
			chartRange = new ChartRange();
			chartRange.setValueOver((valueOver==null)?-1:Float.parseFloat(valueOver));
			chartRange.setValueUnder((valueUnder==null)?-1:Float.parseFloat(valueUnder));
		}
			
		return chartRange;
	}

}
