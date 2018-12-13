package com.ibm.csi.npm.dvreportmodel.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.ibm.csi.npm.dashjson.ChartIdentityType;
import com.ibm.csi.npm.dvreportinput.MediatorInput;
import com.ibm.csi.npm.dvreportinput.ReporterInput;
import com.ibm.csi.npm.dvreportinput.TitleInput;
import com.ibm.csi.npm.dvreportinput.WidgetConfigInput;
import com.ibm.csi.npm.dvreportinput.WidgetInput;
import com.ibm.csi.npm.dvreportmodel.BaselineMediator;
import com.ibm.csi.npm.dvreportmodel.GstTableMediator;
import com.ibm.csi.npm.dvreportmodel.MatrixMediator;
import com.ibm.csi.npm.dvreportmodel.Mediator;
import com.ibm.csi.npm.dvreportmodel.RankVariationMediator;
import com.ibm.csi.npm.dvreportmodel.RatioMediator;
import com.ibm.csi.npm.dvreportmodel.Reporter;
import com.ibm.csi.npm.dvreportmodel.ResDistribMediator;
import com.ibm.csi.npm.dvreportmodel.RptTableMediator;
import com.ibm.csi.npm.dvreportmodel.RstTableMediator;
import com.ibm.csi.npm.dvreportmodel.RttMediator;
import com.ibm.csi.npm.dvreportmodel.TimeSeriesMediator;
import com.ibm.csi.npm.dvreportmodel.Title;
import com.ibm.csi.npm.dvreportmodel.TopNMediator;
import com.ibm.csi.npm.dvreportmodel.Widget;
import com.ibm.csi.npm.dvreportmodel.WidgetConfig;
import com.ibm.csi.npm.dvreportmodel.db.DbHandler;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators.BaselineMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators.RatioMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators.ResDistribMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.mediators.TimeSeriesMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.AreaChartInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.BarHorizInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.BaselineChartInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.DistributionBarInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.PieChartInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.RankVariationChartWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.StackAreaChartInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.chart.widgets.StackBarHorizInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.common.mediators.RankVariationMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.common.mediators.TopNMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.query.QueryUrlBuilder;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.MatrixTableWidgetsInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators.GstTableMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators.MatrixMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators.RptTableMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators.RstTableMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.mediators.RttMediatorInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.GstTableWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.RankVariationTableWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.RptTableWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.RstTableWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.ThViolationTableWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.pagecontrol.table.widgets.TopNTableWidgetInitializer;
import com.ibm.csi.npm.dvreportmodel.util.Constant;
import com.ibm.csi.npm.dvreportmodel.util.ListUtil;
//*import com.ibm.xylem.instructions.TryCatchInstruction;

public class DashboardBuilder {
	private static final Logger logger = LogManager
			.getLogger(DashboardBuilder.class);
	public static File confFile = null;
	public static HashMap<String, String> chartStyleTypeMap = null;
	public static HashMap<String, String> colourStyleTypeMap = new HashMap<String, String>();
	public static String reporterName = "";
	public static int noifwidgets = 0;
	public static String reporterId = "";
	public static String reporterGrpID = "";
	public static String outputFolderPath = "resources/generated/";
	public static String camelOutputFolderPath = "resources/output/";
	public static String inputFilePath = "resources/PVRsMatrix.xml";
	public static String supportedStylesheets = "'PVLs_AP_8ResTimeSeries_w_Ratio.html','PVLs_AP_8ResTimeSeries.html','PVLs_AP_9ResTimeSeries.html','PVLs_AP_GroupSummary_w_2ResDistrib.html',"
			+ "'PVLs_AP_RankVariation.html','PVLs_AP_ResSummary_w_2Ratio.html','PVLs_AP_ResSummary_w_2ResDistrib.html','PVLs_AP_ResSummary_w_GTS.html',"
			+ "'PVLs_AP_ThresholdViolation.html','PVLs_AP_TopN.html','PVLs_AP_Baseline.html','PVLs_AP_ResTimeSeries_Even.html','PVLs_AP_ResSummary_w_2GTS.html',"
			+ "'PVLsRatio_ResourcePlanningTable.html','PVLs1ChartDrilldown.html','PVLs_AP_Dashboard_5620SAM_Service.html','PVLs_AP_Dashboard_5620SAM_Interface.html',"
			+ "'PVLs_AP_Dashboard_5620SAM_MPLS.html','PVLs_AP_Dashboard_Env.html','PVLs_AP_Dashboard_RV_MIB2.html',"
			+ "'PVRsMatrix.html','PVLs_AP_3ResSummary_w_NoCharts.html','PVLs_AP_ResSummary2_w_2ResDistrib.html','PVLs_AP_Dashboard.html',"
			+ "'PVLs_AP_2GroupSummary_w_4ResDistrib.html','PVLs_AP_GroupSummary_w_2GTS.html','PVLs_AP_2GroupSummary_w_2GTS.html','PVLs_AP_2ResSummary_w_4Ratio.html',"
			//added on 2018/03/13
			+ "'PVLs_AP_ResSummary_w_4GTS.html','PVLs_AP_ResSummary_no_charts.html',"
			//added on 2018/03/14
			+ "'PVLs_AP_GroupSummary_no_charts.html','PVLs_AP_2ResSummary_w_4GTS.html',"
			//added on 2018/03/23
			+ "'PVLs_AP_2GroupSummary_w_4Ratio.html','PVLs_AP_GroupSummary_w_2Ratio.html',"
			//added on 2018/03/26
			+ "'PVLs_AP_ResTimeSeries_Odd.html',"
			//added on 2018/03/29
			+"'PVLs_AP_RankVariation_TopN.html','PVLs_AP_ThresholdViolation.html','PVLs_AP_Dashboard_Health.html',"
			//added on 2018/03/26
			+ "'PVLs_AP_2GroupSummary_w_2Ratio.html','PVLs_AP_Dashboard_Ratio.html',"
			//added on 2018/05/03
			+ "'PVLs3ChartDrilldown.html'," + 
			"'PVLs3DC_Big_1Col_2TopN.html'," + 
			"'PVLs5ChartDrilldown.html'," + 
			"'PVLs6ChartDrilldown_2Ratio.html'," + 
			"'PVLs6ChartDrilldown_2Ratio_6Prop.html'," + 
			"'PVLs6ChartDrilldown_2Ratio_6PropDVDrill.html'," + 
			"'PVLsBaseline.html'," + 
			"'PVLsGroupSummaryTable.html'," + 
			"'PVLsRankVariation.html'," + 
			"'PVLsRatio_GST.html'," + 
			"'PVLsRatio_MLH_RST.html'," + 
			"'PVLsRatio_RST.html'," + 
			"'PVLsRatio_RST_StackedBar.html'," + 
			"'PVLsRes6Chart3Table1Column_RST.html'," + 
			"'PVLsResDistrib_GST.html'," + 
			"'PVLsResDistrib_Ratio_MLH_RST.html'," + 
			"'PVLsResDistrib_RST.html'," + 
			"'PVLsResourceOverThrTable.html'," + 
			"'PVLsResourceSummaryTable.html'," + 
			"'PVLsTopN_6.html'," + 
			"'PVLsTopN_6Chart3Row_avg_ratio.html'," + 
			"'PVLsTopN_Distrib.html'," + 
			"'PVLs_AP_GroupSummary_w_Ratio_odd.html'," + 
			"'PVLs_AP_ResSummary_w_Ratio_odd.html'," + 
			"'PVLs_BSM_1TopN.html'," + 
			"'PVLs_BSM_3Chart_3Table.html'," + 
			"'PVLs_BSM_4TopN_1Table.html'," + 
			"'PVLs_BSM_4TopN_1Table_RST.html'," + 
			"'PVLs_BSM_6Chart_6Table.html'," + 
			"'PVLs_BSM_6Chart_6Table_Drilldown_Titles.html'," + 
			"'PVLs_BSM_6Chart_Small_Drilldown_Titles_LED.html'," + 
			"'PVLs_BSM_9Distrib_1Table.html'," + 
			"'PVLs_Cisco_VOIP_Global.html'," + 
			"'PVLs_RADIUS_8ResTimeSeries_w_Ratio.html'," + 
			"'PVLs_RADIUS_Dashboard.html'," + 
			"'PVLs_RADIUS_GroupSummary_w_2Ratio.html'," + 
			"'PVLs_RADIUS_GroupSummary_w_2Ratio_6GTS.html'," + 
			"'PVLs_RADIUS_ResSummary_w_2Ratio.html'," + 
			"'PVLs_RADIUS_ThresholdViolation.html'," + 
			"'PVRs_VOIP_Dashboard.html',"
			//added on 2018/05/17
			+ "'PVLs6ChartStackedBar_2Ratio_6Prop.html'," + 
			"'PVLs8ChartDrilldown_2Ratio_6PropDVDrill2.html'," + 
			"'PVLsTopN_6_avg_sum_ratio.html'," + 
			"'PVLs_AP_ResSummary_w_ResDistrib_odd.html'";
	
	
	
	// use a latch as signal when to stop Camel
	//private final static CountDownLatch countDownLatch = new CountDownLatch(1);

	public static void main(String[] args) {
		// mode all to export all supported reporter by stylesheet in
		// supportedStylesheets.
		// to run tool for custom mode mode should custom and provide comma
		// seperated list of reporters and stylesheets.
		// stylesheets reporter is optional if not provided tool run in all
		// mode.
		try {
			logger.debug("starting the Tool###################");
			System.out.println("starting the Tool###################");
			confFile = new File("conf/config.ini");
			logger.debug("Reading configuration from file " + confFile);
			System.out.println("Reading configuration from file" + confFile);
			Scanner scnr = new Scanner(confFile);
			System.out.println("Reading configuration from file" + confFile);
			String mode = null;// "custom/all";
			String reporterIDs = null;
			String sylesheets = null; //'PVLs_AP_Dashboard_5620SAM_Service.html'
			
			while(scnr.hasNextLine()){
				 String line = scnr.nextLine();
		           if(line.contains("=")) {
		        	   String temp[]=line.split("=");
		        	   if (temp[0].equalsIgnoreCase("Mode")) {
		        		   	try{mode=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("reporterIDs")) {
							try{reporterIDs=temp[1];}catch(Exception e){}
						}else if (temp[0].equalsIgnoreCase("syleSheets")) {
							try{sylesheets=temp[1];}catch(Exception e){}
						}
		           }
			 	}
			
		long startTime = System.currentTimeMillis();

		int processedReporter = 0;
		int unconfiguredReporter = 0;

		logger.debug("Caching reporter details. ");
		ArrayList<ArrayList<String>> reporterDetailList = getAllReportersDetail(
				mode, reporterIDs, sylesheets);					////Fetch all Report's Report ID, Name , Stylesheet & Type from DB for Reports

		logger.debug("Caching chart styles. ");
		chartStyleTypeMap = getChartStyleTypeMap();

//	For Each report create dvreportmodel Reporter Objects
		for (ArrayList<String> reporterDetail : reporterDetailList) {

			logger.debug("Generating dashboard json for reporter "
					+ reporterDetail.get(Constant.STR_NAME) + "["
					+ reporterDetail.get(Constant.IDX_IND) + "] using stylesheet "
					+ reporterDetail.get(Constant.STR_URL));

			ReporterInput ri = parseInput(reporterDetail);
			reporterId = "" + ri.getReporterId();
			reporterGrpID = "";

//Fetch RAW_PROPERTIES from DB for Reports
			
			logger.debug("Getting raw properties for reporter "
					+ ri.getReporterId());
			ArrayList<String> rawProperties = getReporterRawProperties(ri
					.getReporterId());					

			logger.debug("Initializing reporter object for reporter "
					+ ri.getReporterId());
			Reporter reporter = initReporter(ri, rawProperties);

//Check Report Type
			
			String reportType = reporterDetail.get(Constant.INT_APP_TYPE);
			
			System.out.println("Report type = " + reportType);
			
			if(reportType.equals("1")){
//For Report Type 1	get linked Group			

/*				
 
SELECT
  *
FROM
app_desc where idx_ind like '1400%';

SELECT
  *
FROM
  se_grp_desc; where idx_ind like '14006%';
  
SELECT
  *
FROM
app_desc   
  
select
* 
from
se_grp_app_member where idx_app like '1400%';

select IDX_GROUP from se_grp_app_member WHERE IDX_APP=14006;



select
* 
from PV_GUI.se_grp_group_all ;				
*/				
				ArrayList<String> reporterGroupIDs = getReporterGroup(reporterId)	;
				int size = 0;
				size = reporterGroupIDs.size();
				
				if(size==0) {
				   System.out.println("Report " + reporterId + " not deployed in any group");
				}
				
				
				
				
				
				
			}else {
//For Report Type 0			
				System.out.println("Report " + reporterId + " not deployed in any group");
			}
					
			
//Generate xml for for report objects.			
			if (reporter.getReportWidget().size() > 0) {
				logger.debug("Generating xml for reporter "
						+ reporter.getName() + "(" + reporter.getId() + ")");
				generateReporterXml(reporter);
				logger.debug("XML creation Done! for reporter "
						+ reporter.getName() + "(" + reporter.getId() + ")");
			} else {
				logger.error("No widget configured for reporter "
						+ reporter.getName() + "(" + reporter.getId() + ")");
				unconfiguredReporter++;
			}
			processedReporter++;

		}
		
		//To get sysout in Console
		System.out.println("==================Run Summary==================");
		System.out.println("Total Reporter Proccessed######################## = " + processedReporter);
		System.out.println("total widget configured");
		System.out.println("Total Unconfigured Reporter = "
				+ unconfiguredReporter);
		System.out.println("====================FINISHED====================");
		System.out.println("Proceeding to Build Dashboard JSON ......");
		//To get sysout in Console		
		
		logger.debug("==================Run Summary==================");
		logger.debug("Time Taken =" + (System.currentTimeMillis() - startTime)
				/ 1000 + " Seconds");
		logger.debug("Total Reporter Proccessed = " + processedReporter);
		logger.debug("Total Total Unconfigured Reporter = "
				+ unconfiguredReporter);
		logger.debug("====================FINISHED====================");
		logger.debug("Building Dashboard JSON ......");
		buildDashboardJSON();
		performJsonCorrection();
		PropertiesBuilder.PropertiesBuilder();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Reading File " + confFile);
		}
		System.out.println("=======FINISHED========");
	}

	public static ArrayList<ArrayList<String>> getAllReportersDetail(
			String mode, String reporterIDs, String stylesheets) {
		logger.debug("Tool running "
				+ mode
				+ (mode.equalsIgnoreCase("custom") ? " for reporter ids "
						+ reporterIDs : "")
				+ " stylesheets "
				+ (mode.equalsIgnoreCase("custom") ? stylesheets
						: supportedStylesheets.toString()));
		ArrayList<ArrayList<String>> reporterDetailList = new ArrayList<ArrayList<String>>();
		Connection connection = DbHandler.getConnection();
		Statement statement = null;// connection.prepareStatement("SELECT * FROM APP_DESC WHERE IDX_IND=?");
		ResultSet rs = null;
		try {
			statement = connection.createStatement();

			String sql = "";
			if (mode.equalsIgnoreCase("all")) {
				sql = "SELECT IDX_IND,STR_NAME,STR_URL,INT_APP_TYPE FROM APP_DESC WHERE STR_URL IN ("
						+ supportedStylesheets + ")";
			} else {
				String sqlFilter = null;
				if (reporterIDs != null && !reporterIDs.equalsIgnoreCase(""))
					sqlFilter = "WHERE IDX_IND IN (" + reporterIDs + ")";
				if (stylesheets != null && !stylesheets.equalsIgnoreCase("")) {
					if (sqlFilter == null)
						sqlFilter = "WHERE STR_URL IN (" + stylesheets + ")";

					else
						sqlFilter = sqlFilter + " AND  STR_URL IN ("
								+ stylesheets + ")";
				} else {
					if (sqlFilter == null)
						sqlFilter = "WHERE STR_URL IN (" + supportedStylesheets
								+ ")";

					else
						sqlFilter = sqlFilter + " AND  STR_URL IN ("
								+ supportedStylesheets + ")";

				}
				sql = "SELECT IDX_IND,STR_NAME,STR_URL,INT_APP_TYPE FROM APP_DESC "
						+ sqlFilter;

			}

			logger.debug("SQL fetch to reporters : " + sql);
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				ArrayList<String> reporteDetail = new ArrayList<String>();
				reporteDetail.add(Constant.IDX_IND, rs.getString("IDX_IND"));
				reporteDetail.add(Constant.STR_NAME, rs.getString("STR_NAME"));
				reporteDetail.add(Constant.STR_URL, rs.getString("STR_URL"));
				reporteDetail.add(Constant.INT_APP_TYPE, rs.getString("INT_APP_TYPE"));
				reporterDetailList.add(reporteDetail);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				logger.error(
						"Exception occured while retrieving reporter details",
						e);
			}

		}
		logger.debug("Total reporter retrieve form datable :"
				+ reporterDetailList.size());
		return reporterDetailList;
	}

	public static void buildDashboardJSON() {
		
		// Check Count of xmls to calculate latch for signal to stop Camel
		
		File camelOutputDirectory = new File(outputFolderPath);
		FilenameFilter fileNameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0) {
					// get last index for '.' char
					int lastIndex = name.lastIndexOf('.');

					// get extension
					String str = name.substring(lastIndex);

					// match path name extension
					if (str.equals(".xml")) {
						return true;
					}
				}
				return false;
			}
		};
		File[] fileList = null;
		if (camelOutputDirectory.exists()) {
			fileList = camelOutputDirectory.listFiles(fileNameFilter);
		}
		System.out.println("============================================================");
		System.out.println();
		
		System.out.println("Number of files in "+ camelOutputDirectory.getAbsolutePath() + " is " + fileList.length);
		System.out.println();
		System.out.println("============================================================");
		final CountDownLatch countDownLatch = new CountDownLatch(fileList.length);
		
		//Build Json from xmls
		
		try {
			//final String fileName = reporterName.replaceAll(" ", "_");
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(routePath(countDownLatch));
			context.start();
			countDownLatch.await();
			context.stop();
		} catch (Exception e) {
			logger.error("Exception while xml to json transformation", e);
		}
	}

	private static RouteBuilder routePath(final CountDownLatch countDownLatch) {
		return new RouteBuilder() {
			public void configure() {

				from("file://" + outputFolderPath)
						.to("xslt:file:resources/DV2DashPieJSON.xslt")
						.multicast().to("file://" + camelOutputFolderPath);

				try {
					DataFormat jaxb = new JaxbDataFormat(JAXBContext
							.newInstance("com.ibm.csi.npm.dashjson"));
					from("file://" + camelOutputFolderPath)
							.unmarshal(jaxb)
							.marshal()
							.json(JsonLibrary.Gson)
							.to("file://"
									+ camelOutputFolderPath
									+ "json/?fileName=${file:name.noext}.json")
							.process(new Processor() {
								@Override
								public void process(Exchange exchange)
										throws Exception {

									System.out.println("=======================");
									System.out.println("  Json update/created  ");
									System.out.println("=======================");
									//performJsonCorrection();
									
									countDownLatch.countDown();
									
									
									logger.debug("DONE");
								}
							});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	public static void performJsonCorrection() {
		File camelOutputDirectory = new File(camelOutputFolderPath + "/json");
		
		System.out.println("Starting performJsonCorrection()");
		
		FilenameFilter fileNameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0) {
					// get last index for '.' char
					int lastIndex = name.lastIndexOf('.');

					// get extension
					String str = name.substring(lastIndex);

					// match path name extension
					if (str.equals(".json")) {
						return true;
					}
				}
				return false;
			}
		};
		File[] fileList = null;
		if (camelOutputDirectory.exists()) {
			fileList = camelOutputDirectory.listFiles(fileNameFilter);
		}

		if (fileList != null && fileList.length > 0) {
			for (File f : fileList) {
				try {

					JsonParser parser = new JsonParser();
					JsonReader jr = new JsonReader(new FileReader(f));
					// jr.setLenient(true);
					JsonObject jsonObject = (JsonObject) parser.parse(jr);
					File file = new File(camelOutputFolderPath + "/final/"
							+ f.getName());
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(URLDecoder.decode(jsonObject.toString(),
							"UTF-8").replaceAll("_default", "default")
							.replaceAll("sysdate1", com.ibm.csi.npm.dvreportmodel.util.Constant.SYSDATE1)
							.replaceAll("URL_sEs", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_sEs)
							.replaceAll("URL_subElementGrp", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_subElementGrp)
							.replaceAll("URL_defaultTimeSpan", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_defaultTimeSpan)
							.replaceAll("URL_timeSpancustom", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_timeSpancustom)
							.replaceAll("URL_LastDay", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_LastDay)
							.replaceAll("URL_Today", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_Today)
							.replaceAll("URL_LastMonth", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_LastMonth)
							.replaceAll("URL_LastWeek", com.ibm.csi.npm.dvreportmodel.util.Constant.URL_LastWeek)
							.replaceAll("timeSpancustom","timeSpan=custom")
							.replaceAll("timePeriodLastDay","timePeriod=Last Day")
							.replaceAll("timePeriodToday","timePeriod=Today")
							.replaceAll("timePeriodLastMonth","timePeriod=Last Month")
							.replaceAll("timePeriodLastWeek","timePeriod=Last Week")
							.replaceAll("timePeriodLastYear","timePeriod=Last Year")
							.replaceAll("timePeriodCustom","timePeriod=Custom")
							.replaceAll("clazz", "class")
							.replaceAll("\"dvApiStoreCase\":\"dvApiStoreCase\"", com.ibm.csi.npm.dvreportmodel.util.Constant.DVAPISTORECASE)
							.replaceAll("TEXT_LABEL_STYLE", com.ibm.csi.npm.dvreportmodel.util.Constant.TEXT_LABEL_STYLE)
							.replaceAll("PIE_LABEL_STYLE", com.ibm.csi.npm.dvreportmodel.util.Constant.PIE_LABEL_STYLE)
							.replace("%25AP","$localization.AP")
							.replace("%AP","$localization.AP")
							);
							
					
					fileWriter.flush();
					fileWriter.close();
					f.deleteOnExit();
										
					System.out.println("=======================");
					System.out.println("Corrrected json "+ file.getAbsolutePath() +" Created");
					System.out.println("=======================");
					
				} catch (Exception e) {
					logger.error("Exception while json correction", e);
					System.out.println("Exception while json correction for " + f.getName() + " due to " + e.toString());
				}
			}
		}
		
		System.out.println("End of performJsonCorrection()");
	}

	public static ArrayList<String> getReporterRawProperties(long reporterId) {
		ArrayList<String> rawProperties = new ArrayList<String>();
		Connection connection = DbHandler.getConnection();
		PreparedStatement statement = null;// connection.prepareStatement("SELECT * FROM APP_DESC WHERE IDX_IND=?");
		ResultSet rs = null;
		try {
			statement = connection
					.prepareStatement("SELECT * FROM APP_DESC WHERE IDX_IND=?");

			statement.setLong(1, reporterId);
			rs = statement.executeQuery();
			while (rs.next()) {
				InputStream is = rs.getAsciiStream("RAW_PROPERTIES");
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));

				String line = br.readLine();
				while (line != null) {
					rawProperties.add(line.substring(1, line.length() - 1));
					line = br.readLine();

					// System.out.println(line);
				}
				reporterName = rs.getString("STR_NAME");
			}

		} catch (Exception e) {
			logger.error("Exception while getting raw properties of reporter "
					+ reporterId, e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				logger.error("Exception occured", e);
			}

		}
		return rawProperties;
	}

	public static HashMap<String, String> getChartStyleTypeMap() {
		HashMap<String, String> chartStyleTypeMap = new HashMap<String, String>();
		Connection connection = DbHandler.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement
					.executeQuery("SELECT IDX_IND,STR_NAME FROM APP_STYLE_DESC");
			while (rs.next()) {
				int styleID = rs.getInt("IDX_IND");
				String name = rs.getString("STR_NAME");
				logger.debug("Chart style id: " + styleID + " Chart style name: " + name);
				String type = null;
				if (name.contains("RV Chart")) {
					type = "RV_CHART";
				}
				if (name.contains("Baseline Chart")) {
					type = "BASELINE_CHART";
				}
				if (type == null) {
					String[] nameArray = name.split("-");
					type = (nameArray.length > 1) ? nameArray[1].trim()
							.toUpperCase().replaceAll(" ", "_") : null;
					if (type != null ) {
						if (type.equals("STACK_BAR_HORIZ")) {
							logger.debug("Color scheme: " + nameArray[3]);
							String colour = nameArray[3].trim(); 
							System.out.println("Colour :"+ colour);
							colourStyleTypeMap.put("" + styleID,colour);
							
						}						
					}		
				}
				if (type != null)
					chartStyleTypeMap.put("" + styleID, type);
				logger.debug("PRD Widget style id: " + styleID + " type: " + type);
			}
		} catch (Exception e) {
			logger.error("Exception while getting chart styles ", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				logger.error("Exception occured ", e);
			}

		}
		logger.debug("start style cache " + chartStyleTypeMap.toString());
		return chartStyleTypeMap;
	}
	
	public static ArrayList<String> getReporterGroup(String reporterId) {
		System.out.println("Inside getReporterGroup(String reporterId)");
		ArrayList<String> reporterGroupIDs = new ArrayList<String>();
		Connection connection = DbHandler.getConnection();
		PreparedStatement statement = null;// connection.prepareStatement("SELECT * FROM APP_DESC WHERE IDX_IND=?");
		ResultSet rs = null;
		try {
			String sql = "select " + 
					"IDX_GROUP " + 
					"from " + 
					"se_grp_app_member"
					+ " WHERE IDX_APP=?";
			statement = connection
					.prepareStatement(sql);

			statement.setString(1, reporterId);
			logger.debug("Executing SQL " + sql);
			logger.debug("reporterId " + reporterId);
			rs = statement.executeQuery();
			while (rs.next()) {
				/*InputStream is = rs.getAsciiStream("IDX_GROUP");
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));*/

				String line =  rs.getString("IDX_GROUP");
				logger.debug("IDX_GROUP :" + line);
				reporterGroupIDs.add(line);
			}

		} catch (Exception e) {
			logger.error("Exception while getting group reporter "
					+ reporterId, e);
			
			System.out.println("Exception while getting group reporter "
					+ reporterId);
			//e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				logger.error("Exception occured", e);
			}

		}
		return reporterGroupIDs;
	}

	public static ReporterInput parseInput(ArrayList<String> reporterDetail) {
		ReporterInput reporterInput = null;
		try {
			File file = new File("resources/"
					+ reporterDetail.get(Constant.STR_URL).replace(".html",
							".xml"));
			JAXBContext jaxbContext = JAXBContext
					.newInstance(ReporterInput.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			reporterInput = (ReporterInput) jaxbUnmarshaller.unmarshal(file);
			reporterInput.setReporterId(Integer.parseInt(reporterDetail
					.get(Constant.IDX_IND)));
		} catch (JAXBException e) {
			logger.error("Exception while parsing input xml ", e);
		}
		return reporterInput;

	}

	public static void generateReporterXml(Reporter reporter) {
		try {
			String fileName = reporter.getId()+ ".xml";
			File file = new File(outputFolderPath + fileName);
			if (file.exists()) {
				file = new File(outputFolderPath
						+ reporter.getName().replaceAll(" ", "_")
								.replaceAll("/", "_").replace("\\", "_") + "_"
						+ reporter.getId() + ".xml");
			}
			JAXBContext jaxbContext = JAXBContext.newInstance(Reporter.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(reporter, file);
			// jaxbMarshaller.marshal(reporter, System.out);
			
			System.out.println("=======================");
			System.out.println("New xml "+ file.getAbsolutePath() +" created ");
			System.out.println("=======================");
			
		} catch (JAXBException e) {
			logger.error(
					"Exception while marshaling reporter " + reporter.getName()
							+ "(" + reporter.getId() + ")", e);
		}

	}

	private static Reporter initReporter(ReporterInput ri,
			ArrayList<String> rawProperties) {
		Reporter reporter = new Reporter();
		reporter.setId("DVR-" + ri.getReporterId());
		reporter.setName(reporterName);
		logger.debug("Initializing reporter " + reporterName + "("
				+ ri.getReporterId() + ")");

		if (ri != null && rawProperties != null) {
			List<WidgetConfigInput> controlList = ri.getWidgetConfigInput();
			Iterator<WidgetConfigInput> wciItr = controlList.iterator();
			int widgetCount = 0;
			int widgetWithColSpan = 0;
			int xpos = 0;
			int ypos = 0;
			int rowCount;
			while (wciItr.hasNext()) {
				WidgetConfigInput wci = wciItr.next();
				MediatorInput mediatorInput = wci.getMediator();
				try {
					Mediator mediator = initMediator(mediatorInput,
							ListUtil.getFilteredList(mediatorInput.getName(),
									rawProperties));
					if (mediator != null) {
						String defaultReporterStyle = ListUtil
								.getValue(ListUtil.getFilteredList(
										"reporterStyle", rawProperties).get(0));
						WidgetInput widgetInput = wci.getWidget();
						Widget widget = initWidget(widgetInput,
								ListUtil.getFilteredList(widgetInput.getName(),
										rawProperties), defaultReporterStyle);
						
						if (widget != null) {
							widgetCount++;

							TitleInput titleInput = wci.getTitle();
							Title title = null;
							if (titleInput != null)
								title = initTitle(ListUtil.getFilteredList(
										titleInput.getName(), rawProperties));

							mediator.setStoreName(widgetInput.getName() + "_"
									+ mediatorInput.getName());
							widget.setWidgetId(widgetInput.getName());
							widget.setStoreName(widgetInput.getName() + "_"
									+ mediatorInput.getName());

							ChartIdentityType chartIdentityType = new ChartIdentityType();
							chartIdentityType.setName("chartId"+widget.getWidgetId());
													
							if (wci.getWidget().getType()
									.equalsIgnoreCase("table")
									&& (wci.getWidget()
											.getAdaptor()
											.equalsIgnoreCase(
													Constant.PVLC_GROUP_SUMMARY_TABLE_ADAPTOR) || wci
											.getWidget()
											.getAdaptor()
											.equalsIgnoreCase(
													Constant.PVLC_RESOURCE_SUMMARY_TABLE_ADAPTOR))) {
								widget.setRowspan(10);
								widget.setColspan(60);
								
								//Fix for Blank report on Dash Designer Tool after Json Import.
								if(xpos==0) {
									widget.setYpos(ypos);
								}else {
									ypos=ypos+widget.getRowspan();
									widget.setYpos(ypos);
								}	
								//Fix for Blank report on Dash Designer Tool after Json Import. 
								
								xpos = 0;
								widget.setXpos(xpos);
								
								widgetWithColSpan++;
							} else {
								widget.setRowspan(10);
								widget.setColspan(30);
								if (xpos < 60) {
									widget.setXpos(xpos);
									xpos=xpos+30;
									widget.setYpos(ypos);
								} else {
									xpos = 0;
									ypos=ypos+widget.getRowspan();
									widget.setXpos(xpos);
									xpos=xpos+30;
									widget.setYpos(ypos);
								}
							}

							WidgetConfig widgetConfig = new WidgetConfig();

							if (title != null)
								widgetConfig.setTitle(title);

							widgetConfig.setMediator(mediator);
							widgetConfig.setWidget(widget);

							String dataStoreUrl = generateDataStoreUrl(wci,
									mediator, widget);
							widgetConfig.setStoreUrl(dataStoreUrl);
							widgetConfig.setStoreName(widgetInput.getName()
									+ "_" + mediatorInput.getName());
							reporter.getReportWidget().add(widgetConfig);
						} else {
							logger.debug("Fail to initialize widget "
									+ wci.getWidget().getName());
						}
					} else {
						logger.debug("Fail to initialize mediator "
								+ mediatorInput.getName());
					}

					rowCount = (widgetCount - widgetWithColSpan) % 2 == 0 ? (((widgetCount - widgetWithColSpan) / 2) + widgetWithColSpan)
							: ((widgetCount - widgetWithColSpan) / 2) + 1
									+ widgetWithColSpan;
					reporter.setTotalrows(rowCount*10);
					reporter.setTotalcols(60);
					reporter.setBlockHeight(35);
					reporter.setBootstrapCols(60);
				} catch (Exception e) {
					logger.error("Exception while initializing reporter"
							+ reporterName + "(" + ri.getReporterId() + ")", e);
				}

			}
		}
		return reporter;
	}

	private static Mediator initMediator(MediatorInput mediatorInput,
			ArrayList<String> filteredList) {
		if (filteredList != null && filteredList.size() > 0) {
			String mediatorType = mediatorInput.getType();
			switch (mediatorType) {
			case Constant.GST_MEDIATOR:
				return GstTableMediatorInitializer.initGstTableMediator(
						mediatorInput.getName(), filteredList);

			case Constant.RST_MEDIATOR:
				return RstTableMediatorInitializer.initRstTableMediator(
						mediatorInput.getName(), filteredList);

			case Constant.RESOURCE_DISTRIB_MEDIATOR:
				return ResDistribMediatorInitializer.initResDistribMediator(
						mediatorInput.getName(), filteredList);

			case Constant.TIME_SERIES_MEDIATOR:
				return TimeSeriesMediatorInitializer.initTimeSeriesMediator(
						mediatorInput.getName(), filteredList);

			case Constant.RATIO_MEDIATOR:
				return RatioMediatorInitializer.initRatioMediator(
						mediatorInput.getName(), filteredList);

			case Constant.TOPN_MEDIATOR:
				return TopNMediatorInitializer.initTopNMediator(
						mediatorInput.getName(), filteredList);

			case Constant.RTT_MEDIATOR:
				return RttMediatorInitializer.initRttMediator(
						mediatorInput.getName(), filteredList);

			case Constant.RANK_VARIATION_MEDIATOR:
				return RankVariationMediatorInitializer
						.initRankVariationMediator(mediatorInput.getName(),
								filteredList);

			case Constant.BASELINE_MEDIATOR:
				return BaselineMediatorInitializer.initBaselineMediator(
						mediatorInput.getName(), filteredList);

			case Constant.MATRIX_MEDIATOR:
				return MatrixMediatorInitializer.initMatrixMediator(
						mediatorInput.getName(), filteredList);
			case Constant.RPT_MEDIATOR:
				return RptTableMediatorInitializer.initRptTableMediator(
						mediatorInput.getName(), filteredList);
			default: {
				logger.error("Mediator type " + mediatorType
						+ " not supported.");
				return null;
			}
			}
		} else {
			return null;
		}
	}

	private static Widget initWidget(WidgetInput widgetInput,
			ArrayList<String> filteredList, String defaultReporterStyle) {
		if (filteredList != null && filteredList.size() > 0) {
			String widgetType = widgetInput.getType();
			switch (widgetType) {
			case "table":
				noifwidgets++;
				System.out.println("noifwidgets #######:"+noifwidgets);
				return initTableWidget(widgetInput, filteredList);
				
			case "chart":
				noifwidgets++;
				System.out.println("noifwidgets #######:"+noifwidgets);
				return initChartWidget(widgetInput, filteredList,
						defaultReporterStyle);
			default:
				logger.error("Widget type " + widgetType + " not supported.");
				return null;
			}
		} else {
			return null;
		}
	}

	private static Widget initTableWidget(WidgetInput widgetInput,
			ArrayList<String> filteredList) {
		String adaptor = widgetInput.getAdaptor();
		switch (adaptor) {
		case Constant.PVLC_GROUP_SUMMARY_TABLE_ADAPTOR:
			return GstTableWidgetInitializer.initGstTableWidget(
					widgetInput.getName(), filteredList);
		case Constant.PVLC_RESOURCE_SUMMARY_TABLE_ADAPTOR:
			return RstTableWidgetInitializer.initRstTableWidget(
					widgetInput.getName(), filteredList);
		case Constant.PVLC_SUMMARY_TABLE_ADAPTOR:
			return TopNTableWidgetInitializer.initTopNTableWidget(
					widgetInput.getName(), filteredList);
		case Constant.PVLC_RESOURCE_TH_TABLE_ADAPTOR:
			return ThViolationTableWidgetInitializer
					.initThViolationTableWidget(widgetInput.getName(),
							filteredList);
		case Constant.DV_RANK_VARIATION_TABLE_ADAPTOR:
			return RankVariationTableWidgetInitializer
					.initRankVariationTableWidget(widgetInput.getName(),
							filteredList);
		case Constant.MATRIX_TABLE_ADAPTOR:
			return MatrixTableWidgetsInitializer.initMatrixTableWidget(
					widgetInput.getName(), filteredList);
		case Constant.DV_RESOURCE_PLANNING_TABLE_ADAPTOR:
			return RptTableWidgetInitializer.initRptTableWidget(
					widgetInput.getName(), filteredList);
		default: {
			logger.error("Table adaptor " + adaptor + " not supported.");
			return null;
		}
		}

	}

	private static Widget initChartWidget(WidgetInput widgetInput,
			ArrayList<String> filteredList, String defaultReporterStyle) {
		String chartName = widgetInput.getName();
		String chartStyle = ListUtil.getValue(ListUtil.getFilteredList(
				chartName + ".chartStyle", filteredList).get(0));
		logger.debug("Initializing widget object for chart " + chartName + " using chartStyle ID : "
				+ chartStyle);
		if (chartStyle == null || chartStyle.equalsIgnoreCase("")) {
			logger.error("Chart style not define in widget using reporter default style ");
			chartStyle = defaultReporterStyle;
			return null;
		} else {
			String chartType = chartStyleTypeMap.get(chartStyle.trim());
			
			logger.debug("chartType Name : "	+ chartType);
			
			if (chartType.equals("DISTRIBUTION_BAR_(RED/YELLOW/GREEN)")
					|| chartType.equals("DISTRIBUTION_BAR_(GREEN/YELLOW/RED)")) {
				chartType = "DISTRIBUTION_BAR";
			}
			switch (chartType) {
			case Constant.STACK_BAR_HORIZ:
				return StackBarHorizInitializer.initStackBarHorizWidget(
						chartName, filteredList);
			case Constant.AREA:
				return AreaChartInitializer.initAreaChartWidgetWidget(
						chartName, filteredList);
			case Constant.BAR:
				return AreaChartInitializer.initAreaChartWidgetWidget(
						chartName, filteredList);
			case Constant.PIE:
				return PieChartInitializer.initPieChartWidget(chartName,
						filteredList);
			case Constant.BAR_HORIZ:
				return BarHorizInitializer.initBarHorizWidget(chartName,
						filteredList);
			case Constant.RV_CHART:
				return RankVariationChartWidgetInitializer
						.initRankVariationChartWidget(chartName, filteredList);
			case Constant.BASELINE_CHART:
				return BaselineChartInitializer.initBaselineChartWidget(
						chartName, filteredList);
			case Constant.STACK_AREA:
				return StackAreaChartInitializer
						.initStackAreaChartWidgetWidget(chartName, filteredList);
			case Constant.DISTRIBUTION_BAR:
				return DistributionBarInitializer
						.initDistributionBarChartWidget(chartName, filteredList);
			default: {
				logger.error("Chart type " + chartType + " not supported.");
				return null;
			}
			}
		}

	}

	private static String generateDataStoreUrl(WidgetConfigInput wci,
			Mediator mediator, Widget widget) {
		String mediatorType = wci.getMediator().getType();
		switch (mediatorType) {
		case Constant.GST_MEDIATOR:
			return QueryUrlBuilder.getGSTMediatorQuery(
					(GstTableMediator) mediator, reporterId);
		case Constant.RESOURCE_DISTRIB_MEDIATOR:
			return QueryUrlBuilder.getResDistribMediatorQuery(
					(ResDistribMediator) mediator, reporterId);
		case Constant.RST_MEDIATOR:
			return QueryUrlBuilder.getRSTMediatorQuery(
					(RstTableMediator) mediator, reporterId);
		case Constant.TIME_SERIES_MEDIATOR:
			return QueryUrlBuilder.getTimeSeriesMediatorQuery(
					(TimeSeriesMediator) mediator, reporterId);
		case Constant.TOPN_MEDIATOR:
			return QueryUrlBuilder.getTopNMediatorQuery(
					(TopNMediator) mediator, reporterId);
		case Constant.RATIO_MEDIATOR:
			return QueryUrlBuilder.getRatioMediatorQuery(
					(RatioMediator) mediator, reporterId);
		case Constant.RTT_MEDIATOR:
			return QueryUrlBuilder.getRttMediatorQuery((RttMediator) mediator,
					reporterId);
		case Constant.RANK_VARIATION_MEDIATOR:
			return QueryUrlBuilder.getRankVariationMediatorQuery(
					(RankVariationMediator) mediator, reporterId);
		case Constant.BASELINE_MEDIATOR:
			return QueryUrlBuilder.getBaselineMediatorQuery(
					(BaselineMediator) mediator, reporterId);
		case Constant.MATRIX_MEDIATOR:
			return QueryUrlBuilder.getMatrixMediatorQuery(
					(MatrixMediator) mediator, reporterId);
		case Constant.RPT_MEDIATOR:
			return QueryUrlBuilder.getRptMediatorQuery(
					(RptTableMediator) mediator, reporterId);
		default:
			logger.error("Mediator type " + mediatorType + " not supported.");
			return null;
		}

	}

	private static Title initTitle(ArrayList<String> filteredList) {
		Title title = new Title();
		Iterator<String> titlePropItr = filteredList.iterator();
		while (titlePropItr.hasNext()) {
			String prop = titlePropItr.next();
			HashMap<String, String> titleProp = ListUtil.getPropValPair(prop);
			if (titleProp.containsKey("text")) {
				String val = titleProp.get("text");
				title.setText((val == null) ? "" : val);
			}
			if (titleProp.containsKey("link")) {
				String val = titleProp.get("link");
				title.setLink((val == null) ? "" : val);
			}
		}

		return title;
	}

}
