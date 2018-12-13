package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.ibm.csi.npm.dvreportmodel.Drilldown;
import com.ibm.csi.npm.dvreportmodel.GstTableWidget;
import com.ibm.csi.npm.dvreportmodel.RSTTableResource;
import com.ibm.csi.npm.dvreportmodel.RstTableWidget;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RstTableWidgetInitializer {
	
	public static RstTableWidget initRstTableWidget(String name,ArrayList<String> filteredList)
	{
		RstTableWidget rstTableWidget = new RstTableWidget();
		
		ArrayList<String> SumRowProps = ListUtil.getFilteredList(name+".SumRow", filteredList);
		rstTableWidget.setSumRow(TableWidgetMemberInitializer.initSumRow(name,SumRowProps));
		
		ArrayList<String> formulaProps = ListUtil.getFilteredList(name+".formula", filteredList);
		
		rstTableWidget.getFormula().addAll(TableWidgetMemberInitializer.initTableFormulasWithoutTh(name, formulaProps));
		
		ArrayList<String> resourceProps = ListUtil.getFilteredList(name+".resource", filteredList);
		rstTableWidget.setResource(initRSTTableResource(resourceProps));
		
		String headingStatsProp = ListUtil.getFilteredList(name+".headings.stats.display", filteredList).get(0);
		String headingStatsVal = ListUtil.getValue(headingStatsProp);
		Boolean isIeadingStats = (headingStatsVal==null)?false:Boolean.parseBoolean(headingStatsVal);
		rstTableWidget.setHeadingStatsDisplay(isIeadingStats);		
		
		ArrayList<String> drilldownReporterProps = ListUtil.getFilteredList(name+".drilldown", filteredList);
		rstTableWidget.setDrilldown(TableWidgetMemberInitializer.initDrilldown(drilldownReporterProps));
			
		
		return rstTableWidget;
	}

	private static RSTTableResource initRSTTableResource(
			ArrayList<String> resourceProps) {
		RSTTableResource rSTTableResource = new RSTTableResource();
		HashMap<String,String> map = ListUtil.getPropValPair(resourceProps);
		
		Iterator<String> mapItr = map.keySet().iterator();
		while(mapItr.hasNext())
		{
			String attribute = mapItr.next();
			String attribVal = map.get(attribute);			
			switch(attribute)
			{
			case "editName":rSTTableResource.setEditName(Boolean.parseBoolean(attribVal));
				break;
			case "label":rSTTableResource.setLabel(attribVal!=null?attribVal:"Name");
			    break;	
			default:break;	
			
			}
		}		
			
		return rSTTableResource;
	}

}
