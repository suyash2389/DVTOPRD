package com.ibm.csi.npm.dvreportmodel.util;

public class Constant {
	public static final String RESOURCE_DISTRIB_MEDIATOR ="resourceDistributionMediator";
	public static final String GST_MEDIATOR ="gstMediator";
	public static final String RST_MEDIATOR ="rstMediator";
	public static final String TIME_SERIES_MEDIATOR="timeSeriesMediator";
	public static final String RATIO_MEDIATOR="ratioMediator";
	public static final String TOPN_MEDIATOR="topnMediator";
	public static final String RTT_MEDIATOR="rttMediator";
	public static final String RANK_VARIATION_MEDIATOR="rankVariationMediator";
	public static final String BASELINE_MEDIATOR="baselineMediator";
	//special case here we map class name instead of tag name
	public static final String MATRIX_MEDIATOR="MatrixMediator";
	public static final String RPT_MEDIATOR ="rptMediator";
	
	public static final String PVLC_GROUP_SUMMARY_TABLE_ADAPTOR ="PVLcGroupSummaryTableAdaptor";
	public static final String PVLC_RESOURCE_SUMMARY_TABLE_ADAPTOR ="PVLcResourceSummaryTableAdaptor";
	public static final String PVLC_SUMMARY_TABLE_ADAPTOR ="PVLcSummaryTableAdaptor";
	public static final String PVLC_RESOURCE_TH_TABLE_ADAPTOR ="PVLcResourceOverThrTableAdaptor";
	public static final String DV_RANK_VARIATION_TABLE_ADAPTOR ="DVRankVariationTableAdaptor";
	public static final String MATRIX_TABLE_ADAPTOR ="MatrixAdaptor";
	public static final String DV_RESOURCE_PLANNING_TABLE_ADAPTOR ="DVResourcePlanningTableAdaptor";
	
	public static final int IDX_IND = 0;
	public static final int STR_NAME = 1;
	public static final int STR_URL = 2;
	public static final int INT_APP_TYPE = 3;
	
	public static final String PIE = "PIE";
	public static final String AREA = "AREA";
	public static final String STACK_BAR = "STACK_BAR";
	public static final String BAR = "BAR";
	public static final String STACK_BAR_HORIZ = "STACK_BAR_HORIZ";
	public static final String STACK_BAR_HORIZ_RYG = "STACK_BAR_HORIZ_RYG";
	public static final String STACK_BAR_HORIZ_GYR = "STACK_BAR_HORIZ_GYR";
	public static final String STACK_BAR_HORIZ_ROYGG = "STACK_BAR_HORIZ_ROYGG";
	public static final String BAR_HORIZ = "BAR_HORIZ";
	public static final String RV_CHART = "RV_CHART";
	public static final String BASELINE_CHART = "BASELINE_CHART";
	public static final String DISTRIBUTION_BAR = "DISTRIBUTION_BAR";
	public static final String STACK_AREA = "STACK_AREA";
	
	
	// Constants required to perform performJsonCorrection
	public static final String SYSDATE1 = "sysdate+1";
	//public static final String URL_sEs = "/{contextRoot}/PROVISO/DataView/Query.json?subElmtID=0&subElmtGrpID={subElmtGrpID}&relativePeriod=timeparam&reporterID=10353&startTime={fancyTimeFrom}&user=NOC&gr=true&gdm=true&tz=GMT{timeZone}{endTimeSelector}&convertTime=true&queryType=DVRSTProperty&resLabelID=2&metricIDs=11119&statistics1=avg&propertyIDs=&removeNullRows=false";
	//public static final String URL_sEs = "/{contextRoot}/PROVISO/DataView/Query.json?user=NOC&subElmtID=null&subElmtGrpIDs={subElmtGrpID}&queryType=DVAssocSubElmtGrp&endTime={endTime}&startTime={startTime}";
	public static final String URL_sEs = "/{contextRoot}/PROVISO/DataView/Query.json?user=NOC&subElmtID=null&subElmtGrpIDs=50503&queryType=DVAssocSubGrpGrp&endTime={endTime}&startTime={startTime}";
	public static final String URL_subElementGrp = "/{contextRoot}/PROVISO/DataView/Query.json?user=NOC&subElmtID=null&subElmtGrpIDs=50503&queryType=DVAssocSubGrpGrp&endTime={endTime}&startTime={startTime}";
	public static final String URL_defaultTimeSpan= "/{contextRoot}/PROVISO/DataView/Query.json?user=NOC&queryType=DVAggOffsetDesc&subElmtGrpIDs={subElmtGrpID}&timeSpan={timeSpan}&startTime={tzStartTime}";
	public static final String URL_timeSpancustom= "/{contextRoot}/PROVISO/DataView/Query.json?user=NOC&queryType=DVAggOffsetDesc&subElmtGrpIDs={subElmtGrpID}&endTime={tzEndTime}&startTime={tzStartTime}";
	public static final String URL_LastDay= "select(yr||'.'||mon||'.'||da||'.00.00.00') as value from(select case when length(mon) <2 then 0||mon else mon end as mon, case when length(da) <2 then 0||da else da end as da, yr from(select to_char(value,'yyyy') as yr, to_char(value,'MM') as mon, to_char(value,'dd') as da from(select(getdate()-1) as value from dual))) /* Last day */";
	public static final String URL_Today= "select(yr||'.'||mon||'.'||da||'.00.00.00') as value from(select case when length(mon) <2 then 0||mon else mon end as mon, case when length(da) <2 then 0||da else da end as da, yr from (select to_char(value,'yyyy') as yr, to_char(value,'MM') as mon, to_char(value,'dd') as da from (select (getdate()) as value from dual))) /* Today */";
	public static final String URL_LastMonth= "select(yr||'.'||mon||'.'||'01.00.00.00') as value from (select yr from (select to_char(dateadd('month',-1,getdate()),'yyyy') as yr from dual)) as yr, (select mon from (select case when length(mon) <2 then 0||mon else mon end as mon from (select to_char(value,'MM') as mon from (select (dateadd('month',-1,getdate())) as value from dual))) as mon) /*Last Month*/";
	public static final String URL_LastWeek= "select(yr||'.'||mon||'.'||da||'.00.00.00') as value from (select case when length(mon) <2 then 0||mon else mon end as mon, case when length(da) <2 then 0||da else da end as da, yr from (select to_char(value,'yyyy') as yr, to_char(value,'MM') as mon, to_char(value,'dd') as da from ((select case when col < 2 then dateadd('day',-(col-2),dateadd('week',-2,getdate())) when col > 2 then dateadd('day',-(col-2),dateadd('week',-1,getdate())) when col=2 then dateadd('week',-1,getdate()) END as value from (select dayofweek(dateadd('week',-1,getdate())) as col)))))/*Last Week*/";
	public static final String DVAPISTORECASE= "\"cases\":[{\"timePeriod=Today\":{\"timespan\": \"day\"}},{\"default\":{\"endTimeSelector\":\"&timeSpan={timespan}\"}},{\"timePeriod=Last Week\":{\"timespan\": \"week\"}},{\"timePeriod=Custom\": {\"endTimeSelector\": \"&endTime={tzEndTime}\"}},{\"timePeriod=Last Year\":{\"timespan\":\"year\"}},{\"timePeriod=Last Day\":{\"timespan\":\"day\"}},{\"timePeriod=Last Month\":{\"timespan\":\"month\"}}]";
	public static final String TEXT_LABEL_STYLE = "width:100%;float:left;height:100%;font-size:13px;color:rgb(102,102,102) !important;text-align:left;background-color:#ffffff;border-bottom:2px solid #b7c1d1;padding:10px 0 0 0;";
	public static final String PIE_LABEL_STYLE ="width:100%;margin-left:3%;margin-bottom:2.6%;";
	//Derby DB SQLs
	public static final String URL_infraHiddenDBTimeFilterStore=	"select cast((year(current_date)/1000)as char)|| cast((year(current_date)/100)-(10*(year(current_date)/1000)) as char)|| cast((year(current_date)/10)-(100*(year(current_date)/1000))- (10 * ((year(current_date)/100)-(10*(year(current_date)/1000)))) as char) || cast((year(current_date) - (1000*(year(current_date)/1000)) - (100 * ((year(current_date)/100)-(10*(year(current_date)/1000)))) - (10 * ((year(current_date)/10)-(100*(year(current_date)/1000))- (10 * ((year(current_date)/100)-(10*(year(current_date)/1000)))))) ) as char)|| '.' || cast ((month(current_date)/10) as char ) || cast( (month(current_date)-(10 * (month(current_date)/10)) ) as char ) || '.' || cast ((day(current_date)/10) as char ) || cast( (day(current_date)-(10 * (day(current_date)/10)) ) as char ) || '.00.00.00'  as startTime,  cast((year(current_date)/1000)as char)|| cast((year(current_date)/100)-(10*(year(current_date)/1000)) as char)|| cast((year(current_date)/10)-(100*(year(current_date)/1000))- (10 * ((year(current_date)/100)-(10*(year(current_date)/1000)))) as char) ||cast((year(current_date) - (1000*(year(current_date)/1000)) - (100 * ((year(current_date)/100)-(10*(year(current_date)/1000)))) - (10 * ((year(current_date)/10)-(100*(year(current_date)/1000))- (10 * ((year(current_date)/100)-(10*(year(current_date)/1000)))))) ) as char)|| '.' || cast ((month(current_date)/10) as char ) || cast( (month(current_date)-(10 * (month(current_date)/10)) ) as char ) || '.' || cast ((day(current_date)/10) as char ) ||cast( (day(current_date)-(10 * (day(current_date)/10)) +1) as char ) || '.00.00.00' as endTime from sysibm.sysdummy1";
	
	// Constants required to perform performJsonCorrection
	
	public static final String BASE_URL = "/PV/PROVISO/DataView/Query.json?subElmtID={subElmtID}"
			+ "&subElmtGrpID={subElmtGrpID}&timeSpan={timeSpan}&relativePeriod=timeparam&reporterID={reporterID}&startTime={fancyTimeFrom}"
			+ "&user=NOC&gr=true&gdm=true&tz=GMT{timeZone}{endTimeSelector}&convertTime=true"; 
	
	public static final String TS_BASE_URL = "/PV/PROVISO/DataView/Query.json?subElmtIDs={subElmtID}"
			+ "&subElmtGrpID={subElmtGrpID}&timeSpan={timeSpan}&relativePeriod=timeparam&reporterID={reporterID}&startTime={fancyTimeFrom}"
			+ "&user=NOC&gr=true&gdm=true&tz=GMT{timeZone}{endTimeSelector}&convertTime=true"; 
	
	public static final String RESOURCE_DISTRIB_MEDIATOR_QUERY=BASE_URL+"&queryType=DVResDistribution&resLabelID=2&"
			+ "metricID={metricID}&statistic={statistic}&allRanges={allRanges}{ranges}";
	
	public static final String GST_MEDIATOR_QUERY=BASE_URL+"&queryType=DVGST&resLabelID=2&"
			+ "metricIDs={metricIDs}{statistics}&violationType=true&Burst=true&Period=true&Risk=true&Baseline=true&removeNullRows={removeNullRows}"
			+ "&sortByColumn={sortByColumn}&rank={rank}";
	
	public static final String RST_MEDIATOR_QUERY=BASE_URL+"&queryType=DVRSTProperty&resLabelID=2&"
			+ "metricIDs={metricIDs}{statistics}&propertyIDs={propertyIDs}&removeNullRows={removeNullRows}"
			+ "{sortByColumn}&rank={rank}";
	
	public static final String TIME_SERIES_MEDIATOR_QUERY=TS_BASE_URL+"&queryType=DVTSProperty&resLabelID=1&"
			+ "metricIDs={metricIDs}&propertyIDs={propertyIDs}&statistics={statistics}&requireThr={requireThr}&ga={ga}&AddNRTtoGTS={AddNRTtoGTS}"
			+ "&nrtWait={nrtWait}&autoSP={autoSP}{busyHours}";
	
	public static final String TOPN_MEDIATOR_QUERY=BASE_URL+"&queryType=DVTopNProperty&resLabelID=2&metricIDs={metricIDs}{propertyIDs}"
			+ "&statistics={statistics}&maxResults={maxResults}&ga=false&metricTopN={metricTopN}&rank={rank}&handleRSE={handleRSE}";
	
	public static final String RATIO_MEDIATOR_QUERY=BASE_URL+"&queryType=DVRatioMetric&resLabelID=2&metricIDs={metricIDs}&ratiotoReport={ratiotoReport}&ga={ga}";
	
	public static final String RTT_MEDIATOR_QUERY=BASE_URL+"&queryType=DVRTTBurst&resLabelID=2&metricIDs={metricIDs}&statistics={statistics}&propertyIDs={propertyIDs}"
			+ "&sortByColumn={sortByColumn}&rank={rank}";
		
	public static final String RANK_VARIATION_MEDIATOR_QUERY=BASE_URL+"&queryType=DVTopNVariation&resLabelID=2&metricIDs={metricIDs}&statistic={statistics}"
			+ "&maxResults={maxResults}&rank={rank}";
	
	public static final String BASELINE_MEDIATOR_QUERY=BASE_URL
			+"&queryType=DVBaseline&resLabelID=1771&baselinePeriods={baselinePeriods}&metricIDs=={metricIDs}&baselineStats={baselineStats}&statistics={statistics}";
	
	public static final String MATRIX_MEDIATOR_QUERY = BASE_URL+"&queryType=DVMetricMatrixSummary&resLabelID=1771&propConstraints={propConstraints}"
			+ "&sourceEndpoint={sourceEndpoint}&destinationEndpoint={destinationEndpoint}&sourceEndpointLabel={sourceEndpointLabel}"
			+ "&destinationEndpointLabel={destinationEndpointLabel}&metricIDs={metricIDs}{statistics}";
	
	public static final String RPT_MEDIATOR_QUERY=BASE_URL+"&queryType=DVRPT&resLabelID=1771&metricIDs={metricIDs}&statistics={statistics}&propertyIDs={propertyIDs}"
			+ "&sortByColumn={sortByColumn}&rank={rank}&plevelDef={plevelDef}&trendDateLowerLimit={trendDateLowerLimit}&trendDateUpperLimit={trendDateUpperLimit}"
			+ "&trendPeriod={trendPeriod}&trendType={trendType}";
}
