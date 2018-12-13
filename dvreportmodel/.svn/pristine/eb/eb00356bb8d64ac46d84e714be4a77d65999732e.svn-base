package com.ibm.csi.npm.dvreportmodel.pagecontrol.table;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.csi.npm.dvreportmodel.MatrixDrilldown;
import com.ibm.csi.npm.dvreportmodel.MatrixTableWidget;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TableWidgetMemberInitializer;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;


public class MatrixTableWidgetsInitializer {
	public static MatrixTableWidget initMatrixTableWidget(String name,ArrayList<String> filteredList)
	{
		MatrixTableWidget matrixTableWidget = new MatrixTableWidget();
		
		ArrayList<String> drilldownProps = ListUtil.getFilteredList(name+".drilldown", filteredList);
		HashMap<String, String>  drilldownPropsMap = ListUtil.getPropValPair(drilldownProps);		
		MatrixDrilldown matrixDrilldown = new MatrixDrilldown();
		matrixDrilldown.setFromCells(drilldownPropsMap.containsKey("fromCells")?Integer.parseInt(drilldownPropsMap.get("fromCells")):null);
		matrixDrilldown.setFromColumnLabels(drilldownPropsMap.containsKey("fromColumnLabels")?Integer.parseInt(drilldownPropsMap.get("fromColumnLabels")):null);
		matrixDrilldown.setFromRowLabels(drilldownPropsMap.containsKey("fromRowLabels")?Integer.parseInt(drilldownPropsMap.get("fromRowLabels")):null);
		matrixTableWidget.setDrilldown(matrixDrilldown);
		
		ArrayList<String> cellFormatProps = ListUtil.getFilteredList(name+".cellFormat", filteredList);
		String cellFormat =ListUtil.getValue(cellFormatProps.get(0));
		matrixTableWidget.setCellFormat(cellFormat);
		
		ArrayList<String> displayLegendProps = ListUtil.getFilteredList(name+".displayLegend", filteredList);
		String displayLegend =ListUtil.getValue(displayLegendProps.get(0));
		matrixTableWidget.setDisplayLegend(displayLegend);
		
		ArrayList<String> firstCellLabelsProps = ListUtil.getFilteredList(name+".labels.firstCell", filteredList);
		String firstCellLabels =ListUtil.getValue(firstCellLabelsProps.get(0));
		matrixTableWidget.setFirstCellLabels(firstCellLabels);
		
		ArrayList<String> rowColumnsLabelsProps = ListUtil.getFilteredList(name+".labels.row/Columns", filteredList);
		String rowColumnsLabels =ListUtil.getValue(rowColumnsLabelsProps.get(0));
		matrixTableWidget.setRowColumnsLabels(rowColumnsLabels);
		
		ArrayList<String> rotateProps = ListUtil.getFilteredList(name+".rotate", filteredList);
		String rotate =ListUtil.getValue(rotateProps.get(0));
		matrixTableWidget.setRotate(rotate!=null?Boolean.parseBoolean(rotate):false);
		
		
		ArrayList<String> formulaProps = ListUtil.getFilteredList(name+".formula", filteredList);
		
		matrixTableWidget.getFormulas().addAll(TableWidgetMemberInitializer.initTableFormulasWithoutTh(name, formulaProps));
		
		
		return matrixTableWidget;
	}

}
