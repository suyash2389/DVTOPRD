package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.ResDistribMediator;
import com.ibm.csi.npm.dvreportmodel.RttMediator;
import com.ibm.csi.npm.dvreportmodel.TopNMediator;
import com.ibm.csi.npm.dvreportmodel.Value;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RttMediatorInitializer {
	public static Mediator initRttMediator(String name,
		ArrayList<String> filteredList) {
		RttMediator  rttMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(name+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			rttMediator = new RttMediator();
			
			String stats = ListUtil.getValue(ListUtil.getFilteredList(name+".stats", filteredList).get(0));
			String pageSize = ListUtil.getValue(ListUtil.getFilteredList(name+".pageSize", filteredList).get(0));			
			String properties = ListUtil.getValue(ListUtil.getFilteredList(name+".properties", filteredList).get(0));
			
			
			rttMediator.setMetrics(metrics.replace("+", ","));
			rttMediator.setStats(stats.replace("+", ","));
			rttMediator.setPageSize(Integer.parseInt(pageSize));
			rttMediator.setProperties(properties!=null?properties.replace("+", ","):null);
			
			ArrayList<String> sortProp = ListUtil.getFilteredList(name+".sort", filteredList);
			rttMediator.setSort(TableMediatorMemberInitializer.initTableMediatorSort(sortProp));	
			
		}
		return rttMediator;
	}

}
