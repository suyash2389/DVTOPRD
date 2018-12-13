package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import com.ibm.csi.npm.dvreportmodel.MatricMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.MatrixMediatorThreshold;
import com.ibm.csi.npm.dvreportmodel.Stats;

import com.ibm.csi.npm.dvreportmodel.TableMediator;
import com.ibm.csi.npm.dvreportmodel.TableMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.TableMediatorGroup;
import com.ibm.csi.npm.dvreportmodel.TableMediatorSort;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class TableMediatorMemberInitializer {
	
	public static TableMediatorSort initTableMediatorSort(ArrayList<String> filteredList)
	{
		TableMediatorSort tableMediatorSort = new TableMediatorSort();
		Iterator<String> sortPropItr = filteredList.iterator();
		while(sortPropItr.hasNext())
		{
			String prop = sortPropItr.next();
			HashMap<String, String>  sortProp = ListUtil.getPropValPair(prop);
			if(sortProp.containsKey("enable"))
			{
				String val = sortProp.get("enable");
				tableMediatorSort.setEnable((val==null)?false:Boolean.parseBoolean(val));
			}
			if(sortProp.containsKey("ascending"))
			{
				String val = sortProp.get("ascending");
				tableMediatorSort.setAscending((val==null)?false:Boolean.parseBoolean(val));
			}
			if(sortProp.containsKey("orderBy"))
			{
				
				tableMediatorSort.setOrderBy(sortProp.get("orderBy"));
			}
			if(sortProp.containsKey("orderByRPTColumn"))
			{
				
				tableMediatorSort.setOrderByRPTColumn(sortProp.get("orderByRPTColumn"));
			}
		}
		
		return tableMediatorSort;
	}
	
	public static ArrayList<TableMediatorFormula> initTableMediatorFormulas(String mediatorName,ArrayList<String> filteredList)
	{
		ArrayList<TableMediatorFormula> formulaList = new ArrayList<TableMediatorFormula>();
		
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
			ArrayList<String> formulaProps = ListUtil.getFilteredList(mediatorName+"."+formulaName, filteredList);
			TableMediatorFormula tableMediatorFormula = initTableMediatorFormula(formulaName,formulaProps);
			if(tableMediatorFormula!=null)
			formulaList.add(tableMediatorFormula);
		}
		return formulaList;
	}
	
	public static ArrayList<MatricMediatorFormula> initMatrixMediatorFormulas(String mediatorName,ArrayList<String> filteredList)
	{
		ArrayList<MatricMediatorFormula> formulaList = new ArrayList<MatricMediatorFormula>();
		
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
			ArrayList<String> formulaProps = ListUtil.getFilteredList(mediatorName+"."+formulaName, filteredList);
			MatricMediatorFormula matricMediatorFormula = initMatrixMediatorFormula(formulaName,formulaProps);
			if(matricMediatorFormula!=null)
				formulaList.add(matricMediatorFormula);
			
		}
		return formulaList;
	}
	
	public static MatricMediatorFormula initMatrixMediatorFormula(String name,ArrayList<String> filteredList)
	{
		MatricMediatorFormula matricMediatorFormula = new MatricMediatorFormula();
		matricMediatorFormula.setFormulaId(name);
		
		Iterator<String> formulaPropItr = filteredList.iterator();
		MatrixMediatorThreshold matrixMediatorThreshold = new MatrixMediatorThreshold();
		while(formulaPropItr.hasNext())
		{
			String prop = formulaPropItr.next();
			HashMap<String, String>  formulaProp = ListUtil.getPropValPair(prop);			
			if(formulaProp.containsKey("metric") && formulaProp.get("metric")!=null)
			{
				
				matricMediatorFormula.setMetric(formulaProp.get("metric").replace("+", ""));	
			}
			if(formulaProp.containsKey("stat") && formulaProp.get("stat")!=null)
			{
				String reporterStats = formulaProp.get("stat");
				String[] reporterStatsArr = reporterStats.split("\\+");
				Stats stats = new Stats();
				for(String stat:reporterStatsArr)
				{
					stats.getStat().add(stat);
					
				}
				matricMediatorFormula.setStats(stats);
				
			}
			if(formulaProp.containsKey("criticalLevelProperty") && formulaProp.get("criticalLevelProperty")!=null)
				matrixMediatorThreshold.setCriticalLevelProperty(formulaProp.get("criticalLevelProperty"));
			
			if(formulaProp.containsKey("default") && formulaProp.get("default")!=null)
				matrixMediatorThreshold.setDefault(formulaProp.get("default"));
			
			if(formulaProp.containsKey("over") && formulaProp.get("over")!=null)
				matrixMediatorThreshold.setOver(Boolean.parseBoolean(formulaProp.get("over")));
			
			if(formulaProp.containsKey("percentOver/Under") && formulaProp.get("percentOver/Under")!=null)
				matrixMediatorThreshold.setPercentOverUnder(Integer.parseInt(formulaProp.get("percentOver/Under")));
			
			if(formulaProp.containsKey("Source/Dest") && formulaProp.get("Source/Dest")!=null)
				matricMediatorFormula.setSourceDestination(formulaProp.get("Source/Dest"));
		}
		matricMediatorFormula.setThreshold(matrixMediatorThreshold);
		if(matricMediatorFormula.getMetric()!=null && !matricMediatorFormula.getMetric().equals(""))
		{
			return matricMediatorFormula;
		}else
		{
			return null;
		}
	}
	
	public static TableMediatorFormula initTableMediatorFormula(String name,ArrayList<String> filteredList)
	{
		TableMediatorFormula tableMediatorFormula = new TableMediatorFormula();
		tableMediatorFormula.setFormulaId(name);
		
		Iterator<String> formulaPropItr = filteredList.iterator();
		while(formulaPropItr.hasNext())
		{
			String prop = formulaPropItr.next();
			HashMap<String, String>  formulaProp = ListUtil.getPropValPair(prop);
			if(formulaProp.containsKey("metrics") && formulaProp.get("metrics")!=null)
			{
				tableMediatorFormula.setMetric(formulaProp.get("metrics").replace("+", ""));	
			}
			if(formulaProp.containsKey("stats") && formulaProp.get("stats")!=null)
			{
				String reporterStats = formulaProp.get("stats");
				String[] reporterStatsArr = reporterStats.split("\\+");
				Stats stats = new Stats();
				for(String stat:reporterStatsArr)
				{
					stats.getStat().add(stat);
					
				}
				tableMediatorFormula.setStats(stats);
				
			}		
		}
		if(tableMediatorFormula.getMetric()!=null && !tableMediatorFormula.getMetric().equals(""))
		{
			return tableMediatorFormula;
		}else
		{
			return null;
		}
	}
	
	public static TableMediatorGroup initTableMediatorGroup(ArrayList<String> filteredList)
	{
		
		Iterator<String> groupPropItr = filteredList.iterator();
		String groupFilter = null;
		while(groupPropItr.hasNext())
		{
			String prop = groupPropItr.next();
			HashMap<String, String>  groupProp = ListUtil.getPropValPair(prop);
			if(groupProp.containsKey("filter"))
			{
				groupFilter = groupProp.get("filter");	
			}
		}
		if(groupFilter!=null && !groupFilter.equals(""))
		{
			TableMediatorGroup tableMediatorGroup = new TableMediatorGroup();
			tableMediatorGroup.setFilter(groupFilter);
			return tableMediatorGroup;
		}else
		{
			return null;
		}
		
	}	

}
