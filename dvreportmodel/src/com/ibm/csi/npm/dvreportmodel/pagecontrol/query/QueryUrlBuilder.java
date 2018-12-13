package com.ibm.csi.npm.dvreportmodel.pagecontrol.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ibm.csi.npm.dvreportmodel.BaselineMediator;
import com.ibm.csi.npm.dvreportmodel.ChartRange;
import com.ibm.csi.npm.dvreportmodel.GstTableMediator;
import com.ibm.csi.npm.dvreportmodel.MatricMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.MatrixMediator;
import com.ibm.csi.npm.dvreportmodel.Property;
import com.ibm.csi.npm.dvreportmodel.RankVariationMediator;
import com.ibm.csi.npm.dvreportmodel.ResDistribMediator;
import com.ibm.csi.npm.dvreportmodel.RptTableMediator;
import com.ibm.csi.npm.dvreportmodel.RstTableMediator;
import com.ibm.csi.npm.dvreportmodel.RttMediator;
import com.ibm.csi.npm.dvreportmodel.TableMediatorFormula;
import com.ibm.csi.npm.dvreportmodel.TimeSeriesMediator;
import com.ibm.csi.npm.dvreportmodel.TopNMediator;
import com.ibm.csi.npm.dvreportmodel.RatioMediator;
import com.ibm.csi.npm.dvreportmodel.util.Constant;

public class QueryUrlBuilder {
	public static String getRSTMediatorQuery(RstTableMediator mediator,String reporterId) {
		String url = Constant.RST_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{removeNullRows}", "true");
		//TODO
		//url = url.replace("{sortByColumn}", mediator..getSort().getOrderBy());
		url = url.replace("{sortByColumn}","");
		url = url.replace("{rank}", mediator.getSort().isAscending()?"ASC":"DESC");
		
		List<TableMediatorFormula> mediatorFormulas = mediator.getFormula();
		Iterator<TableMediatorFormula> mediatorFormulasIt = mediatorFormulas.iterator();
		ArrayList<String> statsNameList = new ArrayList<String>();
		String metricsIds = "";
		while(mediatorFormulasIt.hasNext())
		{
			TableMediatorFormula tmf = mediatorFormulasIt.next();
			metricsIds = metricsIds+((metricsIds.equals(""))?tmf.getMetric():","+tmf.getMetric());
			List<String> statsList = tmf.getStats().getStat();
			String statsString =null;
			for(String stats:statsList)
			{
				statsString =(statsString==null)?stats:statsString+","+stats;
			}
			statsNameList.add(statsString);
		}
		url = url.replace("{metricIDs}", metricsIds);
		Iterator<String> statsListIt = statsNameList.iterator();
		
		int statsCounter = 1;
		String urlStatistics = "";
		while(statsListIt.hasNext())
		{
			String stats = statsListIt.next();
			urlStatistics=urlStatistics+"&statistics"+statsCounter+"="+stats.replace("+", "");
			statsCounter++;
		}
		url = url.replace("{statistics}", urlStatistics);
		if(mediator.getProperties()!=null)
		{
		List<Property> Properties = mediator.getProperties().getProperty();
		String propertyIDs = null;
		for(Property property:Properties)
		{
			propertyIDs = (propertyIDs==null)?""+property.getIdIdx():propertyIDs+","+property.getIdIdx();
		}
		url = url.replace("{propertyIDs}", propertyIDs);
		}else
		{
			url = url.replace("{propertyIDs}", "");
		}
		return url;
	}

	public static String getResDistribMediatorQuery(ResDistribMediator mediator,String reporterId) {
		String url = Constant.RESOURCE_DISTRIB_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricID}", mediator.getMetric());
		url = url.replace("{statistic}", mediator.getStats());
		url = url.replace("{allRanges}",mediator.isProvideAllRanges()?"true":"false");
		List<ChartRange> ranges = mediator.getRanges();
		String urlRanges = "";
		Iterator<ChartRange> rangesIt = ranges.iterator();
		int rangeCounter = 1;
		while(rangesIt.hasNext())
		{
			ChartRange range = rangesIt.next();
			String over = (range.getValueOver()==-1)?"null":""+range.getValueOver();
			String under = (range.getValueUnder()==-1)?"null":""+range.getValueUnder();
			urlRanges=urlRanges+"&range"+rangeCounter+"="+over+","+under;
			rangeCounter++;
		}
		url = url.replace("{ranges}", urlRanges);
		return url;
	}

	public static String getGSTMediatorQuery(GstTableMediator mediator,String reporterId) {
		String url = Constant.GST_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{removeNullRows}", "false");
		url = url.replace("{sortByColumn}", mediator.getSort().getOrderBy());
		url = url.replace("{rank}", mediator.getSort().isAscending()?"ASC":"DESC");
		
		List<TableMediatorFormula> mediatorFormulas = mediator.getFormula();
		Iterator<TableMediatorFormula> mediatorFormulasIt = mediatorFormulas.iterator();
		ArrayList<String> statsNameList = new ArrayList<String>();
		String metricsIds = "";
		while(mediatorFormulasIt.hasNext())
		{
			TableMediatorFormula tmf = mediatorFormulasIt.next();
			metricsIds = metricsIds+((metricsIds.equals(""))?tmf.getMetric():","+tmf.getMetric());
			List<String> statsList = tmf.getStats()!=null?tmf.getStats().getStat():(new ArrayList<String>());
			String statsString =null;
			for(String stats:statsList)
			{
				statsString =(statsString==null)?stats:statsString+","+stats;
			}
			statsNameList.add(statsString);
		}
		url = url.replace("{metricIDs}", metricsIds);
		Iterator<String> statsListIt = statsNameList.iterator();
		
		int statsCounter = 1;
		String urlStatistics = "";
		while(statsListIt.hasNext())
		{
			String stats = statsListIt.next();
			urlStatistics=urlStatistics+"&statistics"+statsCounter+"="+stats.replace("+", "");
			statsCounter++;
		}
		
		url = url.replace("{statistics}", urlStatistics);
		
		return url;
	}
	
	public static String getMatrixMediatorQuery(MatrixMediator mediator,String reporterId) {
		String url = Constant.MATRIX_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{sourceEndpoint}",mediator.getSourceDestination().getSourceProperty());
		url = url.replace("{sourceEndpointLabel}",mediator.getSourceDestination().getSourcePropertyLabel());
		url = url.replace("{destinationEndpoint}",mediator.getSourceDestination().getDestinationProperty());
		url = url.replace("{destinationEndpointLabel}",mediator.getSourceDestination().getDestinationPropertyLabel());
		
		String filterString = mediator.getResourceFilters();		
		List<MatricMediatorFormula> mediatorFormulas = mediator.getFormulas();
		Iterator<MatricMediatorFormula> mediatorFormulasIt = mediatorFormulas.iterator();
		ArrayList<String> statsNameList = new ArrayList<String>();
		String metricsIds = "";
		while(mediatorFormulasIt.hasNext())
		{
			MatricMediatorFormula matricMediatorFormula = mediatorFormulasIt.next();
			metricsIds = metricsIds+((metricsIds.equals(""))?matricMediatorFormula.getMetric():","+matricMediatorFormula.getMetric());
			List<String> statsList = matricMediatorFormula.getStats().getStat();
			String statsString =null;
			for(String stats:statsList)
			{
				statsString =(statsString==null)?stats:statsString+","+stats;
			}
			statsNameList.add(statsString);
		}
		url = url.replace("{metricIDs}", metricsIds);
		Iterator<String> statsListIt = statsNameList.iterator();
		
		int statsCounter = 1;
		String urlStatistics = "";
		while(statsListIt.hasNext())
		{
			String stats = statsListIt.next();
			urlStatistics=urlStatistics+"&statistics"+statsCounter+"="+stats.replace("+", "");
			statsCounter++;
		}
		
		url = url.replace("{statistics}", urlStatistics);
		return url;
	}

	public static String getTimeSeriesMediatorQuery(TimeSeriesMediator mediator,
			String reporterId) {
		String url = Constant.TIME_SERIES_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetric());
		String strStats = null;
		if(mediator.getStats()!=null && (mediator.getStats().getStat().size()>0))
				{
					Iterator<String> statsListIt = mediator.getStats().getStat().iterator();
							while(statsListIt.hasNext())
							{
								if(strStats==null)
									strStats=statsListIt.next();
									else
										strStats=strStats+","+statsListIt.next();
							}
				}
		url = url.replace("{statistics}", strStats!=null?strStats:"");
		url = url.replace("{propertyIDs}", (mediator.getProperties()!=null)?mediator.getProperties():"");
		url = url.replace("{AddNRTtoGTS}", mediator.getGtsNearRealTime().isEnable()?"true":"false");
		url = url.replace("{nrtWait}", ""+mediator.getGtsNearRealTime().getSecondsToWait());
		url = url.replace("{autoSP}", ""+mediator.getReportPeriodMultiplier());
		//TODO
		url = url.replace("{ga}", ""+true);
		
		if(mediator.getBusyHours()!=null && !mediator.getBusyHours().equals(""))
			url = url.replace("{busyHours}","&busyHours="+mediator.getBusyHours().isEnable());
		else
			url = url.replace("{busyHours}","");
		
		url = url.replace("{requireThr}",mediator.getFormula1ViolationType().equalsIgnoreCase("none")?"false":"true");
		return url;
	}
	
	public static String getTopNMediatorQuery(TopNMediator mediator,
			String reporterId) {
		String url = Constant.TOPN_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetrics());
		String strStats = null;
		Iterator<String> statsListIt = mediator.getStats().getStat().iterator();
				while(statsListIt.hasNext())
				{
					if(strStats==null)
						strStats=statsListIt.next();
						else
							strStats=strStats+","+statsListIt.next();
				}
		if(mediator.getProperties()==null || mediator.getProperties().equals(""))
		{
			url = url.replace("{propertyIDs}","");
		}else
		{
			url = url.replace("{propertyIDs}", "&propertyIDs="+mediator.getProperties());
		}
			
		url = url.replace("{statistics}", strStats);
		url = url.replace("{maxResults}", ""+mediator.getNumberOfResults());
		url = url.replace("{metricTopN}", mediator.getMetrics().split(",").length>1?"true":"false");
		url = url.replace("{handleRSE}", ""+mediator.isHandleRSE());
		url = url.replace("{rank}", ""+mediator.isTopRank());
		url = url.replace("{ga}",""+mediator.isGroupTOPN());
		return url;
	}

	public static String getRatioMediatorQuery(RatioMediator mediator,
			String reporterId) {
		String url = Constant.RATIO_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetric());
		url = url.replace("{ga}",""+mediator.isGroupRatio());
		url = url.replace("{ratiotoReport}",""+mediator.getStats());		
		return url;
	}
	public static String getRttMediatorQuery(RttMediator mediator,
			String reporterId) {
		String url = Constant.RTT_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetrics());
		url = url.replace("{statistics}",""+mediator.getStats());
		url = url.replace("{propertyIDs}", (mediator.getProperties()!=null)?mediator.getProperties():"");
		url = url.replace("{rank}",mediator.getSort().isAscending()?"ASC":"DESC");
		url = url.replace("{sortByColumn}",mediator.getSort().getOrderBy());
		return url;
	}

	public static String getRankVariationMediatorQuery(
			RankVariationMediator mediator, String reporterId) {
		String url = Constant.RANK_VARIATION_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetrics());
		url = url.replace("{statistics}",mediator.getStats());
		url = url.replace("{maxResults}",""+mediator.getNumberOfResults());
		url = url.replace("{rank}",""+mediator.isTopRank());
		return url;
	}
	public static String getBaselineMediatorQuery(BaselineMediator mediator,
			String reporterId) {
		String url = Constant.BASELINE_MEDIATOR_QUERY;
		//baselinePeriods,metricIDs,baselineStats,statistics 
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetric());
		url = url.replace("{baselinePeriods}",""+mediator.getBaseline().getPeriods());
		url = url.replace("{baselineStats}",""+mediator.getBaseline().getStats());	
		url = url.replace("{statistics}",""+mediator.getStats());		
		return url;
	}
	
	public static String getRptMediatorQuery(RptTableMediator mediator,
			String reporterId) {
		String url = Constant.RPT_MEDIATOR_QUERY;
		url = url.replace("{reporterID}", reporterId);
		url = url.replace("{metricIDs}", mediator.getMetrics());
		url = url.replace("{statistics}",""+mediator.getStats());
		url = url.replace("{propertyIDs}", (mediator.getProperties()!=null)?mediator.getProperties():"");
		url = url.replace("{rank}",mediator.getSort().isAscending()?"ASC":"DESC");
		url = url.replace("{sortByColumn}",mediator.getSort().getOrderByRPTColumn());
		
		url = url.replace("{plevelDef}",""+mediator.getForecastDate().getUpgradeForcastCondition().getDefault());
		/*url = url.replace("{trendDateLowerLimit}",mediator.getForecastDate());
		url = url.replace("{trendDateUpperLimit}",mediator.getSort().getOrderByRPTColumn());
		url = url.replace("{trendPeriod}",mediator.getSort().getOrderByRPTColumn());
		url = url.replace("{trendType}",mediator.getSort().getOrderByRPTColumn());*/
		

		return url;
	}
}
