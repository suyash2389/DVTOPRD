package com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ibm.csi.npm.dvreportmodel.GstTableMediator;
import com.ibm.csi.npm.dvreportmodel.Properties;
import com.ibm.csi.npm.dvreportmodel.Property;
import com.ibm.csi.npm.dvreportmodel.RstTableMediator;
import com.ibm.csi.npm.dvreportmodel.TableMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;

public class RstTableMediatorInitializer {
	public static RstTableMediator initRstTableMediator(String name,ArrayList<String> filteredList)
	{
		RstTableMediator rstTableMediator = new RstTableMediator();
		
		ArrayList<String> formulaProps = ListUtil.getFilteredList(name+".formula", filteredList);
		ArrayList<TableMediatorFormula> TableMediatorFormulaList = TableMediatorMemberInitializer.initTableMediatorFormulas(name, formulaProps);
		if(TableMediatorFormulaList!=null && TableMediatorFormulaList.size()>0)
		{
			rstTableMediator.getFormula().addAll(TableMediatorFormulaList);
			
			ArrayList<String> removeEmptyRowsProp = ListUtil.getFilteredList(name+".RemoveEmptyRows", filteredList);
			String removeEmptyRows =ListUtil.getValue(removeEmptyRowsProp.get(0));
			rstTableMediator.setRemoveEmptyRows((removeEmptyRows==null)?true:Boolean.parseBoolean(removeEmptyRows));
			
			ArrayList<String> pageSizeProp = ListUtil.getFilteredList(name+".pageSize", filteredList);
			String pageSize =ListUtil.getValue(pageSizeProp.get(0));
			rstTableMediator.setPageSize((pageSize==null)?50:Integer.parseInt(pageSize));
			
			ArrayList<String> sortProp = ListUtil.getFilteredList(name+".sort", filteredList);
			rstTableMediator.setSort(TableMediatorMemberInitializer.initTableMediatorSort(sortProp));
			
			ArrayList<String> propertiesProps = ListUtil.getFilteredList(name+".PropSel", filteredList);
			Properties properties = initTableMediatorProperties(name,propertiesProps);
			if(properties.getProperty().size()>0)
				rstTableMediator.setProperties(properties);
			
		return rstTableMediator;
		}else
		{
			return null;
		}
	}
	public static Properties initTableMediatorProperties(String medName,ArrayList<String> PropertiesProps)	
	{
		Properties properties = new Properties();
		
		//getting all distinct prop type node names
		Set<String> propGrpSet = new HashSet<String>();
		Iterator<String> propertiesGrpPropItr = PropertiesProps.iterator();
		while(propertiesGrpPropItr.hasNext())
		{
			String prop = propertiesGrpPropItr.next();
			String[] propArray = prop.split("\\.");
			propGrpSet.add(propArray[2]);
		}
		
		Iterator<String> propGrpSetItr = propGrpSet.iterator();
		while(propGrpSetItr.hasNext())
		{
			String propGroupType = propGrpSetItr.next();
			ArrayList<String> filteredProps = ListUtil.getFilteredList(medName+".PropSel."+propGroupType, PropertiesProps);
			
			//getting all distinct properties id type node names
			Set<String> propIdSet = new HashSet<String>();
			Iterator<String> propertiesPropItr = filteredProps.iterator();
			while(propertiesPropItr.hasNext())
			{
				String prop = propertiesPropItr.next();
				String[] propArray = prop.split("\\.");
				String propId = propArray[3];
				if(isInteger(propId))
				{
					propIdSet.add(propId);
				}
			}
			Iterator<String> propIdSetItr = propIdSet.iterator();
			while(propIdSetItr.hasNext())
			{
				String propId = propIdSetItr.next();
				ArrayList<String> propertyProps = ListUtil.getFilteredList(medName+".PropSel."+propGroupType+"."+propId, PropertiesProps);
				Property Property = initProperty(propGroupType,propertyProps); 
				properties.getProperty().add(Property);
			}					
		}					
		return properties;
	}
	
private static Property initProperty(String propGroupType,
			ArrayList<String> propertyProps) {
	Property property = new Property();
	property.setPropGrp(propGroupType);
	HashMap<String,String> propAtribMap = ListUtil.getPropValPair(propertyProps);
	Iterator<String> attribItr = propAtribMap.keySet().iterator();
	while(attribItr.hasNext())
	{
		String attribute = attribItr.next();
		String attribVal = propAtribMap.get(attribute);
		switch(attribute)
		{
		case "attr0":property.setIdIdx(Integer.parseInt(attribVal));
			break;
		case "attr1":property.setName(attribVal);
		    break;
		case "attr2":property.setLabel(attribVal);
	    	break;  
		case "attr3":property.setEdit(attribVal);
    		break;	
		case "attr4":property.setCreate(attribVal);
    		break;
		case "attr5":property.setType(attribVal);
			break;
		case "attr6":property.setDefault(attribVal);
			break;	
		default:break;	
		
		}
	}
	
	
		return property;
	}
public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    return true;
}

}
