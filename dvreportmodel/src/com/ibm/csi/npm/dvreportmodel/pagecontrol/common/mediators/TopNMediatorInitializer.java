package com.ibm.csi.npm.dvreportmodel.pagecontrol.common.mediators;

import java.util.ArrayList;

import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.ResDistribMediator;
import com.ibm.csi.npm.dvreportmodel.Stats;
import com.ibm.csi.npm.dvreportmodel.TopNMediator;
import com.ibm.csi.npm.dvreportmodel.Value;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class TopNMediatorInitializer {
	public static Mediator initTopNMediator(String name,
		ArrayList<String> filteredList) {
		TopNMediator  topNMediator = null;
		String metrics = ListUtil.getValue(ListUtil.getFilteredList(name+".metrics", filteredList).get(0));
		if(metrics!=null&&!metrics.equals(""))
		{
			topNMediator = new TopNMediator();
			
			String stats= null;
			try {
				
				String prop =ListUtil.getFilteredList(name+".stats", filteredList).get(0);
				stats = ListUtil.getValue(prop);
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String groupTOPN = ListUtil.getValue(ListUtil.getFilteredList(name+".groupTOPN", filteredList).get(0));
			 String handleRSE="false";
			try {
				ArrayList<String> handlerse = ListUtil.getFilteredList(name+".handleRSE", filteredList);
				if(handlerse.isEmpty()) 
					handleRSE = "false";
				else
				handleRSE = ListUtil.getValue(handlerse.get(0));
				
		} catch (IndexOutOfBoundsException e) {
		      
			e.printStackTrace();
		}

			String metricFromUrl = ListUtil.getValue(ListUtil.getFilteredList(name+".metricFromUrl", filteredList).get(0));
			String numberOfResults = ListUtil.getValue(ListUtil.getFilteredList(name+".numberOfResults", filteredList).get(0));
			String properties = ListUtil.getValue(ListUtil.getFilteredList(name+".properties", filteredList).get(0));
			String relation = null;
			try {
				ArrayList<String> rel =ListUtil.getFilteredList(name+".relation", filteredList);
				if(rel.isEmpty())
				 relation = null;
				else 
					relation = ListUtil.getValue(rel.get(0));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String topRank = ListUtil.getValue(ListUtil.getFilteredList(name+".topRank", filteredList).get(0));
			
			topNMediator.setMetrics(metrics.replace("+", ""));
			stats = stats.replace("+", ",");
			String []statsArr = stats.split(",");
			Stats statsObj = new Stats();
			ArrayList<String> statList=new ArrayList<String>();
			for(int i=0;i<statsArr.length;i++)
			{
				statsObj.getStat().add(statsArr[i]);
			}
			topNMediator.setStats(statsObj);
			topNMediator.setGroupTOPN(Boolean.parseBoolean(groupTOPN));
			topNMediator.setHandleRSE(Boolean.parseBoolean(handleRSE));
			topNMediator.setMetricFromUrl(metricFromUrl);
			topNMediator.setNumberOfResults(Integer.parseInt(numberOfResults));
			topNMediator.setProperties(properties!=null?properties.replace("+", ""):"");
			topNMediator.setRelation(relation);
			topNMediator.setTopRank(Boolean.parseBoolean(topRank));
			
			ArrayList<String> valueOverProps = ListUtil.getFilteredList(name+".valueOver", filteredList);
			Value valueOver = new Value();
			valueOver.setDefault(ListUtil.getValue(valueOverProps.get(0)));
			valueOver.setValue(ListUtil.getValue(valueOverProps.get(1)));
			
			topNMediator.setValueOver(valueOver);
			
			ArrayList<String> valueUnderProps = ListUtil.getFilteredList(name+".valueUnder", filteredList);
			Value valueUnder = new Value();
			valueUnder.setDefault(ListUtil.getValue(valueUnderProps.get(0)));
			valueUnder.setValue(ListUtil.getValue(valueUnderProps.get(1)));
			
			topNMediator.setValueUnder(valueUnder);
		}
		return topNMediator;
	}

}
