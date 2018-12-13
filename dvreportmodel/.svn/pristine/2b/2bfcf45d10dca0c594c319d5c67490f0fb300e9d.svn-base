package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.csi.npm.dvreportmodel.MatricMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.MatrixMediator;
import com.ibm.csi.npm.dvreportmodel.SourceDestination;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class MatrixMediatorInitializer {
	public static MatrixMediator initMatrixMediator(String name,ArrayList<String> filteredList)
	{
		MatrixMediator matrixMediator = null;
		
		ArrayList<String> formulaProps = ListUtil.getFilteredList(name+".formula", filteredList);
		ArrayList<MatricMediatorFormula> TableMediatorFormulaList = TableMediatorMemberInitializer.initMatrixMediatorFormulas(name, formulaProps);
				
		if(TableMediatorFormulaList!=null && TableMediatorFormulaList.size()>0)
		{
			matrixMediator = new MatrixMediator();
			matrixMediator.getFormulas().addAll(TableMediatorFormulaList);
			
			ArrayList<String> sourceDestProps = ListUtil.getFilteredList(name+".Source/Dest", filteredList);
			HashMap<String, String>  sourceDestPropsMap = ListUtil.getPropValPair(sourceDestProps);
			SourceDestination sourceDestination = new SourceDestination();
			sourceDestination.setDestinationProperty(sourceDestPropsMap.containsKey("destinationProperty")?sourceDestPropsMap.get("destinationProperty"):null);
			sourceDestination.setDestinationPropertyLabel(sourceDestPropsMap.containsKey("destinationPropertyLabel")?sourceDestPropsMap.get("destinationPropertyLabel"):null);
			sourceDestination.setSourceProperty(sourceDestPropsMap.containsKey("sourceProperty")?sourceDestPropsMap.get("sourceProperty"):null);
			sourceDestination.setSourcePropertyLabel(sourceDestPropsMap.containsKey("sourcePropertyLabel")?sourceDestPropsMap.get("sourcePropertyLabel"):null);
			matrixMediator.setSourceDestination(sourceDestination);
			
			ArrayList<String> matrixTypeProps = ListUtil.getFilteredList(name+".matrixType", filteredList);
			String matrixType =ListUtil.getValue(matrixTypeProps.get(0));
			matrixMediator.setMatrixType(matrixType);
			
			ArrayList<String> resourceFiltersProps = ListUtil.getFilteredList(name+".resourceFilters", filteredList);
			String resourceFilters =ListUtil.getValue(resourceFiltersProps.get(0));
			matrixMediator.setResourceFilters(resourceFilters);
				
			
		}
		return matrixMediator;
	
	}

}
