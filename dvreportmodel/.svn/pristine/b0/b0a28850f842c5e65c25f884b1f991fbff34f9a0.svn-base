package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators;

import java.util.ArrayList;
import java.util.Iterator;

import com.ibm.csi.npm.dvreportmodel.GstTableMediator;
import com.ibm.csi.npm.dvreportmodel.TableFormulaWithoutTH;
import com.ibm.csi.npm.dvreportmodel.TableMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.TableMediatorGroup;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;


public class GstTableMediatorInitializer {
	
	public static GstTableMediator initGstTableMediator(String name,ArrayList<String> filteredList)
	{
		GstTableMediator gstTableMediator = null;
		
		ArrayList<String> formulaProps = ListUtil.getFilteredList(name+".formula", filteredList);
		ArrayList<TableMediatorFormula> TableMediatorFormulaList = TableMediatorMemberInitializer.initTableMediatorFormulas(name, formulaProps);
		if(TableMediatorFormulaList!=null && TableMediatorFormulaList.size()>0)
		{
			gstTableMediator = new GstTableMediator();
			gstTableMediator.getFormula().addAll(TableMediatorFormulaList);
			
			ArrayList<String> removeEmptyRowsProp = ListUtil.getFilteredList(name+".RemoveEmptyRows", filteredList);
			String removeEmptyRows =ListUtil.getValue(removeEmptyRowsProp.get(0));
			gstTableMediator.setRemoveEmptyRows((removeEmptyRows==null)?true:Boolean.parseBoolean(removeEmptyRows));
			
			ArrayList<String> pageSizeProp = ListUtil.getFilteredList(name+".pageSize", filteredList);
			String pageSize =ListUtil.getValue(pageSizeProp.get(0));
			gstTableMediator.setPageSize((pageSize==null)?50:Integer.parseInt(pageSize));
			
			ArrayList<String> sortProp = ListUtil.getFilteredList(name+".sort", filteredList);
			gstTableMediator.setSort(TableMediatorMemberInitializer.initTableMediatorSort(sortProp));
			
			ArrayList<String> tableGroupProps = ListUtil.getFilteredList(name+".group", filteredList);
			TableMediatorGroup tmg = TableMediatorMemberInitializer.initTableMediatorGroup(tableGroupProps);
			if(tmg!=null)
			gstTableMediator.setGroup(tmg);
		}
		
		return gstTableMediator;
		
		
	}

}
