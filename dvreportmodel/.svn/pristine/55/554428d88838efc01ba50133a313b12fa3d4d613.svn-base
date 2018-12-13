package com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.RatioMediator;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RatioMediatorInitializer {
	public static Mediator initRatioMediator(String mediatorName,
			ArrayList<String> filteredList) {
		RatioMediator ratioMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			ratioMediator  = new RatioMediator();
			ratioMediator.setMetric(metrics.replace("+", ","));
			
			String metricFromUrl = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".metricFromUrl", filteredList).get(0));
			if(metricFromUrl!=null&&!metricFromUrl.equals(""))
				ratioMediator.setMetricFromUrl(metricFromUrl);
			
			String stats = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".stats", filteredList).get(0));
			if(stats!=null&&!stats.equals(""))
				ratioMediator.setStats(stats.replace("+", ","));
			
			String groupRatio = ListUtil.getValue(ListUtil.getFilteredList(mediatorName+".groupRatio", filteredList).get(0));
			if(groupRatio!=null&&!groupRatio.equals(""))
				ratioMediator.setGroupRatio(Boolean.parseBoolean(groupRatio));
			
		}
		return ratioMediator;
	}

}
