package com.ibm.csi.npm.dvreportmodel.pagecontrol.common.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.RankVariationMediator;
import com.ibm.csi.npm.dvreportmodel.ResDistribMediator;
import com.ibm.csi.npm.dvreportmodel.TopNMediator;
import com.ibm.csi.npm.dvreportmodel.Value;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RankVariationMediatorInitializer {
	public static RankVariationMediator initRankVariationMediator(String name,
		ArrayList<String> filteredList) {
		RankVariationMediator  rankVariationMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(name+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			rankVariationMediator = new RankVariationMediator();
			
			String stats = ListUtil.getValue(ListUtil.getFilteredList(name+".stats", filteredList).get(0));
			String numberOfResults = ListUtil.getValue(ListUtil.getFilteredList(name+".numberOfResults", filteredList).get(0));
			String topRank = ListUtil.getValue(ListUtil.getFilteredList(name+".topRank", filteredList).get(0));
			
			rankVariationMediator.setMetrics(metrics.replace("+", ""));
			rankVariationMediator.setStats(stats.replace("+", ""));			
			rankVariationMediator.setNumberOfResults(Integer.parseInt(numberOfResults));			
			rankVariationMediator.setTopRank(Boolean.parseBoolean(topRank));
			
			ArrayList<String> valueOverProps = ListUtil.getFilteredList(name+".valueOver", filteredList);
			Value valueOver = new Value();
			valueOver.setDefault(ListUtil.getValue(valueOverProps.get(0)));
			valueOver.setValue(ListUtil.getValue(valueOverProps.get(1)));
			
			rankVariationMediator.setValueOver(valueOver);
			
			ArrayList<String> valueUnderProps = ListUtil.getFilteredList(name+".valueUnder", filteredList);
			Value valueUnder = new Value();
			valueUnder.setDefault(ListUtil.getValue(valueUnderProps.get(0)));
			valueUnder.setValue(ListUtil.getValue(valueUnderProps.get(1)));
			
			rankVariationMediator.setValueUnder(valueUnder);
		}
		return rankVariationMediator;
	}

}
