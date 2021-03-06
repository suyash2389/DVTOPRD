package com.ibm.csi.npm.dvreportmodel.main;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;

/*
 * ====================================================================
 *
 *
 */


import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.lookup.Interpolator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.csi.npm.dashjson.AxisType;
import com.ibm.csi.npm.dashjson.CaseList;
import com.ibm.csi.npm.dashjson.ChartIdentityType;
import com.ibm.csi.npm.dashjson.ColumnType;
import com.ibm.csi.npm.dashjson.DashboardType;
import com.ibm.csi.npm.dashjson.FilterByList;
import com.ibm.csi.npm.dashjson.FilterbarType;
import com.ibm.csi.npm.dashjson.IconList;
import com.ibm.csi.npm.dashjson.LegendType;
import com.ibm.csi.npm.dashjson.PlotType;
import com.ibm.csi.npm.dashjson.PropertiesType;
import com.ibm.csi.npm.dashjson.SeriesType;
import com.ibm.csi.npm.dashjson.StoreDefaultList;
import com.ibm.csi.npm.dashjson.StoreType;
import com.ibm.csi.npm.dashjson.WidgetType;
import com.ibm.csi.npm.dashjson.WidgettoolbarType;
import com.ibm.csi.npm.dashjson.util.StoreUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;


/**
 * An example of HttpClient can be customized to authenticate
 * preemptively using BASIC scheme.
 * <b>
 * Generally, preemptive authentication can be considered less
 * secure than a response to an authentication challenge
 * and therefore discouraged.
 */
public class GrafanaReportCollector {
	private static final Logger logger = LogManager.getLogger(DashboardBuilder.class);
	public static HashMap<String, JsonObject> reportConfs = new HashMap<String, JsonObject>();
	public static HashMap<String, DashboardType> dashboards = new HashMap<String,DashboardType>();
	public static HashMap<String, JsonObject> prdDashboards = new HashMap<String, JsonObject>();
	public static HashMap<String, String> dataSources = new HashMap<String,String>();
	public static Scanner sc=new Scanner(System.in); 
    public static String reportID = "";
    public static int totalRows=0;
    public static boolean isPrometheus= false;
	
	public static void main(String[] args) throws IOException  {
    	ArrayList<String> reportIds= new ArrayList<String>(); 	
       
    	String host = "" ,context="" ,user = "" , password = "";
    	int port = 3000;
    	boolean check = false;
    	
    	System.out.println("\n====================================================================");
    	
    	//getting host/IP from user
    	do {
			System.out.println("Please Enter Hostname/IP Address of Server installed with Grafana :");
			host=com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();			
		} while (host.equals(""));
    	
    	//getting port from user
    	check = true;
    	do {
    		System.out.println("On this Server are you using default port for Grafana i.e 3000 ? (y/n)" );
			String temp =com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();
			if (temp.equalsIgnoreCase("y")) {
				check=false;
			} else if (temp.equalsIgnoreCase("n")) {
				do {
					port=0;
				System.out.println("Enter port for Grafana");
				port = sc.nextInt();
				} while(port==3000 || port==0);
				check=false;
			} else {
				System.out.println("Invalid input");
			}
		} while (check);
    	
    	do {
			System.out.println("Do you use context to host application? (y/n)");
			String temp =com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();
			if (temp.equalsIgnoreCase("y")) {
				System.out.println("Enter context for Grafana");
				context=com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();
			} else if (temp.equalsIgnoreCase("n")) {
				break;
			} else {
				System.out.println("Invalid input");
			}			
			
		} while (context.equals(""));
    	
    	if (context.equals("") || context.equals("")) {
    		
    	}else { context = "/" + context; } 
    	
    	//getting username from user
    	do {
			System.out.println("Please Enter username of Grafana :");
			user=com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();			
		} while (host.equals(""));
    	
    	
    	//getting password from user
    	do {
			System.out.println("Please Enter password of Grafana :");
			password=com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();			
		} while (host.equals(""));
    	
    	HttpHost target = new HttpHost(host, port, "http");
        
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(user, password));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider).build();
        JsonParser parser = new JsonParser(); 
        try {

            // Create AuthCache instance
            AuthCache authCache = new BasicAuthCache();
            // Generate BASIC scheme object and add it to the local
            // auth cache
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);

            // Add AuthCache to the execution context
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);
            
            
            //fetch datasources
            
            HttpGet httpgetDatasource = new HttpGet("http://" + host + ":"+ port + "/api/datasources/");
            
            CloseableHttpResponse responseDatasource = httpclient.execute(target, httpgetDatasource, localContext);
            try {
            	
            	String tempResponse = EntityUtils.toString(responseDatasource.getEntity());
                JsonArray allReports = (JsonArray) parser.parse(tempResponse);
                System.out.println("list of all datasource : ");
                
                for (JsonElement jsonElement : allReports) {
             	   JsonObject jsonObject = (JsonObject) jsonElement;
             	   String name = jsonObject.get("name").getAsString();
             	   String type = jsonObject.get("type").getAsString();
             	  dataSources.put(name, type);
             	   System.out.println("Datasource name : "+ name + " Datasource type: " + type );    
                }
     
            	 
            }
            catch (NullPointerException e) {
            	System.out.println("Ex = " + e.toString());
			}
            catch(Exception e) {
            	System.out.println("Ex = " + e.toString());
            }
            finally {
                responseDatasource.close();
            }
            
            	
            	
   
            
          //fetch Report UIDs
            
            HttpGet httpget = new HttpGet("http://" + host + ":"+ port + "/api/search/");

            System.out.println("Executing request " + httpget.getRequestLine() + " to target " + target);
         
                CloseableHttpResponse response = httpclient.execute(target, httpget, localContext);
                try {
                    
	                   // System.out.println(response.getStatusLine());
	                   String tempResponse = EntityUtils.toString(response.getEntity());
	                   JsonArray allReports = (JsonArray) parser.parse(tempResponse);
	
	                   //Read more: http://www.java67.com/2016/10/3-ways-to-convert-string-to-json-object-in-java.html#ixzz5DVEON3Lf
	                   
	                   System.out.println("all Reports array: ");
	                   
		                   for (JsonElement jsonElement : allReports) {
		                	   JsonObject jsonObject = (JsonObject) jsonElement;
		                	   System.out.println("UID : "+ jsonObject.get("uid") + " DashBoard Name: " + jsonObject.get("title"));    
		                   }
	            
	                   System.out.println("all Reports count: " + allReports.size());
	                   
	                   System.out.println("\n====================================================================");
	               	 
	                   check = true;
	               	do {
	               		System.out.println("Generate JSONs for all reports or specific report? (all/specific)" );
	           			String temp =com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.sc.next();
	           			if (temp.equalsIgnoreCase("all")) {
	           				check=false;
	           			} else if (temp.equalsIgnoreCase("specific")) {
	           				do {
	           					port=0;
	           				System.out.println("Enter dashboard uids for Grafana");
	           				reportID = sc.nextLine();
	           				
	           				System.out.println(reportID);
	           				} while(reportID.equals(""));
	           				check=false;
	           			} else {
	           				System.out.println("Invalid input");
	           			}
	           		} while (check);
	                   
	                   for (JsonElement jsonElement : allReports) {
	                	   System.out.println("Report json details :" + jsonElement.toString());
	                	   JsonObject tempObject = (JsonObject) jsonElement;
	                	   JsonElement tempElement = tempObject.get("uid");
	                	   String uid = tempElement.getAsString();
	                	   if (reportID.equals("")) {
	                		   reportIds.add(uid);
	                		   System.out.println("Added " + uid);
						}else {
							if (reportID.contains(uid)){
								reportIds.add(uid);
								System.out.println("Added " + uid);
							}else {
								System.out.println("Ignoring " + uid);
							}
						}
	                	   
	                	   
	                   }
	                   
	                   System.out.println("All Available Report IDs : \n" + reportIds.toString());
	                   
		            }
                catch (NullPointerException e) {
                	System.out.println("Ex = " + e.toString());
				}
                catch(Exception e) {
                	System.out.println("Ex = " + e.toString());
                }
                finally {
                    response.close();
                }
                
                
                for (String uid : reportIds) {
                	try {
                		//fetch Report Config
                		httpget = new HttpGet("http://" + host + ":"+ port + "/api/dashboards/uid/"+uid);
                		response = httpclient.execute(target, httpget, localContext);
                        
	                      String tempResponse = EntityUtils.toString(response.getEntity());
	 	                  JsonObject reportConfig= (JsonObject) parser.parse(tempResponse);
	 	                   
	 	                  System.out.println("Data on uid : "+ uid +"\n" + reportConfig.toString());
	 	                   
	 	                  JsonElement tempElement = reportConfig.get("dashboard");
	                       
	 	                  reportConfig = (JsonObject) tempElement;
	 	                  System.out.println("Dashboard on uid : "+ uid +"\n" + reportConfig.toString());
	 	                  
	 	                  reportConfs.put(uid,reportConfig);
 	                   
    				} catch (Exception e) {
    					System.out.println("Ex = " + e.toString());
    				}finally {
                        response.close();
                    }	
				}
                
                System.out.println("All Dashboard confs : \n" + reportConfs.toString());
        
        } finally {
            httpclient.close();
        }
        
        
      //Extract required information
        generateDashFromReportConf();
        
        
       
      //Generate PRD JSON Object xml File
        
       for (String key : dashboards.keySet()) {
			
        	DashboardType dashboard = dashboards.get(key);
        	System.out.println("Dasboard : \n" + dashboard);
        	//System.out.println("Dasboard : \n" + (objectToJSONObject(dashboard)).toString());
        	
        	generatePRDxmlFile(dashboard);
		}
        
        

        // Build JSON File from xml      
       		buildJSONfromXml();
       
        
        
       /* for (String key : prdDashboards.keySet()) {
			try {
				File file = new File(
		        		com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath + "/final/"
						+ key + ".json");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(prdDashboards.get(key).toString());
				fileWriter.flush();
				fileWriter.close();
				System.out.println(com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath + "/final/"
						+ key + ".json created");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/
       		
       		
       		//	Perform JSON Correctoion
       		com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.performJsonCorrection();
			
        
        System.out.println("====== End of Execution ======");
    }
	
	
	//generateDashFromReportConf - start
	private static void generateDashFromReportConf() {
		for (String key : reportConfs.keySet()) {
			
			isPrometheus= false;
        	DashboardType tempDash = new DashboardType();
        	JsonObject tempReport = reportConfs.get(key);
        	JsonArray tempPanels = tempReport.getAsJsonArray("panels");
        	JsonArray tempRows = tempReport.getAsJsonArray("rows");
        	        	
        	try {
        		
        		if(tempPanels==null) {
        			System.out.println("Panels is null or not found. Creating new Panel");
        			tempPanels = new JsonArray();	
        		}
        		
        		if (tempRows == null ) 
        		System.out.println("Rows is null or not found.");
        		
        		if (tempRows != null ) {
        			System.out.println("Rows is found. checking for panels in it.");
            		for (JsonElement rowElement : tempRows) {
    					JsonObject rowObject = (JsonObject) rowElement;
    					
    					JsonArray tempRowPanels = rowObject.getAsJsonArray("panels");
    					for (JsonElement rowPanel : tempRowPanels) {
    						System.out.println(rowPanel.toString());
    						System.out.println("Adding rowPanel to tempPanels");
    						tempPanels.add(rowPanel);
    					}
    				}
        		}
    		}catch (Exception e) {
    			System.out.println("Failed to load tempPanels due to" + e.toString());
    		} finally {
    			System.out.println("Pannels : "+ tempPanels.toString());
    		}
        	
        	System.out.print("Working on Dashboard ");
        	totalRows=0;
        	
        	//id
        		tempDash.setId((String) (tempReport.get("uid")).getAsString());
        		System.out.print("with Id: " + tempDash.getId());
        	
        	//title
        		tempDash.setTitle((String) (tempReport.get("title")).getAsString());
        		System.out.println(" & Title: \"" + tempDash.getTitle() + "\"");
        	
        	
        	//description
        		tempDash.setDescription("Report " + ((String) (tempReport.get("uid")).getAsString()) + " version " + ((String) (tempReport.get("version")).getAsString()) + " generated by Grafana to PRD Migration Tool");
        	
        	//store
        	try {
				
			
        	ArrayList<StoreType> tempStores = buildStoresFromPanels(key,tempPanels); 
        	for (StoreType e : tempStores) {
        		tempDash.getStores().add(e);
			}
        	
        	//filter
        	
        	//for prometheus dashboard
        	if (isPrometheus) {
				FilterbarType tempFilterBar = new FilterbarType();
				tempFilterBar.setDrilldown("true");
				tempFilterBar.setDashboardId(key);
				if ((tempFilterBar.getNames())==null) {
					tempFilterBar.setNames("timePeriod");
				}else {
					tempFilterBar.setNames((tempFilterBar.getNames())+",timePeriod");
				}
				if ((tempFilterBar.getDisplaynames())==null) {
					tempFilterBar.setDisplaynames("Time Period");
				}else {
					tempFilterBar.setDisplaynames((tempFilterBar.getDisplaynames())+",Time Period");
				}
				if (tempFilterBar.getIcons()==null) {
					IconList iconList = new IconList();
					FilterByList timePeriod = new FilterByList();
					timePeriod.setValues("Last5minutes,Last15minutes,Last30minutes,Last1hour,Last3hours,Last6hours,Last12hours,Last24hours");
					timePeriod.setDisplayValues("Last 5 minutes,Last 15 minutes,Last 30 minutes,Last 1 hour,Last 3 hours,Last 6 hours,Last 12 hours,Last 24 hours");
					timePeriod.setDefaultValue("Last5minutes");
					timePeriod.setHidden("false");
					iconList.setTimePeriod(timePeriod);
					tempFilterBar.setIcons(iconList);
				}else {
					try {
						FilterByList timePeriod = (tempFilterBar.getIcons()).getTimePeriod();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				tempDash.setFilterbar(tempFilterBar);
			}
        	//to be planned
        	
        	//widgets
        	ArrayList<WidgetType> tempWidgets = buildWidgetsFromPanels(key,tempPanels); 
        	for (WidgetType e : tempWidgets) {
        		tempDash.getWidgets().add(e);
			}
        	        	
        	//blockHeight
        	//kept default to 35
        	tempDash.setBlockHeight("35");
        	
        	//bootstrapCols
        	//kept default to 60
        	tempDash.setBootstrapCols("60");
        	
        	//totalRows & totalCols
        	//to be calculated
        	tempDash.setTotalcols("24");
        	System.out.println("Total Columns as " + tempDash.getTotalcols());
        	tempDash.setTotalrows((String.valueOf(totalRows)));
        	System.out.println("Total Rows as "+ tempDash.getTotalrows());
        	
        	
        	//localized
        	tempDash.setLocalized("false"); 
        	
        	//Add temp object in ArrayList
        	
        	dashboards.put(key, tempDash);
        	
        	System.out.println("\n Dashboard extracted Succesfully");
        	
        	} catch (Exception e) {
				System.out.println("\n Failed with error:" + e.toString());
				
			}
        	System.out.println();
        	System.out.println("==================================================");
        	System.out.println();
        	
		}
	}
	//generateDashFromReportConf - end
	
	
	//generatePRDjsonFile - start
	private static void generatePRDxmlFile(DashboardType dashboard) {
		try {
			String fileName = dashboard.getId()+ ".xml";
			File file = new File( com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath + fileName);
			if (file.exists()) {
				file = new File( com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath
						+ dashboard.getTitle().replaceAll(" ", "_")
								.replaceAll("/", "_").replace("\\", "_") + "_"
						+ dashboard.getId() + ".xml");
			}
			
			JAXBContext jaxbContext = JAXBContext.newInstance(DashboardType.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			  // Set the Marshaller media type to JSON or XML
			
			//jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE,"application/json");
			
			// Set it to true if you need to include the JSON root element in the JSON output
			
			//jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
			
			// Set it to true if you need the JSON output to formatted
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Marshal the employee object to JSON and print the output to console
			jaxbMarshaller.marshal(dashboard, file);
			//jaxbMarshaller.marshal(dashboard, System.out);
			
			System.out.println("=======================");
			System.out.println("New file "+ file.getAbsolutePath() +" created ");
			System.out.println("=======================");
			
		} catch (JAXBException e) {
			System.out.println(
					"Exception while marshaling reporter \"" + dashboard.getTitle()
							+ "\" (" + dashboard.getId() + ")" + e.toString());
		}

	}
	//generatePRDjsonFile - end
	
	//buildJSONfromXml - start
	public static void buildJSONfromXml(){
		
		File camelOutputDirectory = new File(com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath);
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
		
		try {
			//final String fileName = reporterName.replaceAll(" ", "_");
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(new RouteBuilder() {
				public void configure() {

					try {
						DataFormat jaxb = new JaxbDataFormat(JAXBContext
								.newInstance("com.ibm.csi.npm.dashjson"));
						from("file://" + com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath)
								.unmarshal(jaxb)
								.marshal()
								.json(JsonLibrary.Gson)
								.to("file://"
										+ com.ibm.csi.npm.dvreportmodel.main.DashboardBuilder.camelOutputFolderPath
										+ "json/?fileName=${file:name.noext}.json")
								.process(new Processor() {
									@Override
									public void process(Exchange exchange)
											throws Exception {

										System.out.println("=======================");
										System.out.println("Json update/created");
										System.out.println("=======================");
											
										countDownLatch.countDown();
										
									}
								});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			context.start();
			countDownLatch.await();
			context.stop();
		} catch (Exception e) {
			System.out.println("Exception while xml to json transformation" + e.toString());
		}
	}
	//buildJSONfromXml - end
	
	
	protected static ArrayList<StoreType> buildStoresFromPanels(String key,JsonArray tempPanels) {
		ArrayList<StoreType> tempStores = new ArrayList<StoreType> ();
		for (JsonElement panel : tempPanels) {
			JsonObject panelObject = (JsonObject) panel;
			JsonArray temptargets = (JsonArray) panelObject.get("targets");
			String title = ((panelObject.get("title")).getAsString().replace(" ", ""));
			String dataSource="";
			String dataSourceType ="";
			try {
		
			  dataSource = (panelObject.get("datasource")).getAsString();
			  Iterator<Entry<String,String>> it = dataSources.entrySet().iterator();
			  while(it.hasNext())
			  {
				  Map.Entry<String, String> data = (Map.Entry<String, String>) it.next();
				  String name = data.getKey();
				  if(name.equalsIgnoreCase(dataSource))
					  dataSourceType = data.getValue();
			  }
			
		     System.out.println("dataSourceType : " + dataSourceType);
				
			
			}catch (Exception e) {
				System.out.println("Fail to attach dataSource due to " + e.toString());
			}
			
			try {
				if(!temptargets.equals(null)){
					for (JsonElement target : temptargets) {
						JsonObject targetObject = (JsonObject) target;
						StoreType tempStore = null;
						
						System.out.println("Building Store for "+title);
						
						try {
							tempStore = buildStoreFromTarget(key, title, targetObject, dataSourceType); // store without datasource;
						}catch (Exception e) {
							e.printStackTrace();
						}
						
						tempStore.setDatasource(dataSource);
						tempStores.add(tempStore);
					}
				}	
			} catch (Exception e) {
				System.out.println("Fail to add stores due to " + e.toString());
			}
		}
		return tempStores;
	}

	private static StoreType buildStoreFromTarget(String key, String title, JsonObject targetObject, String dataSourceType) {
		StoreType tempStore = new StoreType();
		tempStore.setName("store_"+key+ (targetObject.get("refId")).getAsString() +"_"+ title );
		
		switch (dataSourceType) {
		
		case "mysql":
			String rawSql="";
			try {
				rawSql = (targetObject.get("rawSql")).getAsString();
			}catch (Exception e) {
				System.out.println("Error in fetching rawSql due to "+ e.toString());
			}finally {
				System.out.println("Setting "+rawSql+" in query");
				tempStore.setQuery(rawSql);
			}			
		break;
		
		case "prometheus":
			String prometheusURL="";
			String expr="";
			int interval=1;
			int step=1;
			int intervalFactor=1;
			isPrometheus=true;
			try {
				expr = (targetObject.get("expr")).getAsString();
			}catch (Exception e) {
				System.out.println("Error in fetching expr due to "+ e.toString());
			}try {
				interval = (targetObject.get("interval")).getAsInt();
			}catch (Exception e) {
				System.out.println("Error in fetching interval due to "+ e.toString());
			}try {
				step = (targetObject.get("step")).getAsInt();
			}catch (Exception e) {
				System.out.println("Error in fetching step due to "+ e.toString());
			}try {
				intervalFactor = (targetObject.get("intervalFactor")).getAsInt();
			}catch (Exception e) {
				System.out.println("Error in fetching intervalFactor due to "+ e.toString());
			}finally {
				
				try {
					if (interval==0) interval=1;
					if (step==0) step =1;
					if (intervalFactor==0) intervalFactor=1;
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
				
				//prometheusURL = "/api/prometheus/query4?expr=" + expr + "&startTime=1535612095&endTime=1535612995&steps=" + (60/(step*intervalFactor*interval)) ;
				
				if(expr.contains("{") && expr.contains("}")) {
					expr = StoreUtil.processQuantile(expr);
				} 
				
				
				try {
					prometheusURL = "/api/prometheus/query25?expr=" + URLEncoder.encode(expr, "UTF-8") + "&timePeriod={timePeriod}&steps=" + (60/(step*intervalFactor*interval)) ;
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2.toString());
					e2.printStackTrace();
				}
				

				try {
					URL url= new URL(prometheusURL);
					URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
					prometheusURL = uri.toASCIIString(); 
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				System.out.println("Setting "+prometheusURL+" in query");
				tempStore.setQuery(prometheusURL);
			}
		break;
		
		case "elasticsearch":
			String url="";
			try {
				url = (targetObject.get("erl")).getAsString();
			}catch (Exception e) {
				System.out.println("Error in fetching rawSql due to "+ e.toString());
			}finally {
				System.out.println("Setting "+url+" in query");
				tempStore.setQuery(url);
			}			
		break;
			
		case "grafana-simple-json-datasource":
			
		break;
		
		case "ibm-apm-datasource":
		
		break;
		
		case "kentik-ds":
		break;
		
		default:
			System.out.println("Data Source " +dataSourceType+ " type is not compatible with tool yet.");
			System.out.println("Setting null in query.");
			tempStore.setQuery("");
			System.out.println("User will need to set query manually.");
		break;
		
		}
		
		StoreDefaultList deflt = new StoreDefaultList();
		CaseList caseList = new CaseList();
		caseList.setDefault(deflt);
		tempStore.getCases().add(caseList);
		return tempStore;
	}
	
	private static StoreType buildStoreFromTarget(String key, String title, JsonObject targetObject) {
		StoreType tempStore = new StoreType();
		tempStore.setName("store_"+key+ (targetObject.get("refId")).getAsString() +"_"+ title );
		StoreDefaultList deflt = new StoreDefaultList();
		CaseList caseList = new CaseList();
		caseList.setDefault(deflt);
		tempStore.getCases().add(caseList);
		return tempStore;
	}

	protected static ArrayList<WidgetType> buildWidgetsFromPanels(String key,JsonArray tempPanels) {
		
		ArrayList<WidgetType> tempWidgets = new ArrayList<WidgetType> ();
		int index=1;
		String tempStoreName=null;
		
		for (JsonElement panel : tempPanels) {
			JsonObject panelObject = (JsonObject) panel;
			WidgetType tempWidget = new WidgetType();
			
			
			// Set widget id & Title
			tempWidget.setId("grafanaPanel_"+key+(String) (panelObject.get("id")).getAsString());
			tempWidget.setTitle((String) (panelObject.get("title")).getAsString());
			logger.debug("Widget id: " + tempWidget.getId() + " Title : " + tempWidget.getTitle());;
			System.out.println("Widget id: " + tempWidget.getId() + " Title : " + tempWidget.getTitle());;
			
			//Type & Mode
			String mode="";
			String type="";
		
			
			//Set Widget Type, Panel Type & Mode
			type=panelObject.get("type").getAsString();
			
			logger.debug("Panel Type: " + panelObject.get("type").getAsString());
			System.out.println("Panel Type: " + panelObject.get("type").getAsString());
			
			if(type.equalsIgnoreCase("graph")) {
				/*if(panelObject.get("bars").getAsBoolean()) {
					tempWidget.setType("Bar");
					}
				else if(panelObject.get("lines").getAsBoolean()) {
					tempWidget.setType("Line");
					}
				else if(panelObject.get("points").getAsBoolean()) {
					tempWidget.setType("Line");
					}
				else if(panelObject.get("stack").getAsBoolean()) {
					tempWidget.setType("Bar");
					}
				else if(panelObject.get("steppedLine").getAsBoolean()) {
					tempWidget.setType("Line");
					}
				else if(panelObject.get("dashes").getAsBoolean()) {
					tempWidget.setType("Line");
					}
				else if(panelObject.get("percentage").getAsBoolean()) {
					tempWidget.setType("Line");
					}
				else{
					tempWidget.setType("Line");
					}*/
				
				JsonObject xAxis =  panelObject.get("xaxis").getAsJsonObject();
				mode = xAxis.get("mode").getAsString();
				
				if (mode.equalsIgnoreCase("time")) 	
					tempWidget.setType("Line");
			   if (mode.equalsIgnoreCase("series"))
					tempWidget.setType("Column");
			   else if(mode.equalsIgnoreCase("histogram"))
				   tempWidget.setType("Column");

			}
			else if (type.equalsIgnoreCase("table")) {
				tempWidget.setType("grid");
				mode="table";
			}
			else if (type.equalsIgnoreCase("heatmap")) {
				tempWidget.setType("Line");
				mode="heatmap";
			}
			else if(type.equalsIgnoreCase("grafana-piechart-panel") )
			{
				tempWidget.setType("pie");
				mode =panelObject.get("pieType").getAsString();
				if(mode.equalsIgnoreCase("pie")) {
					tempWidget.setType("pie");
				}
				else if(mode.equalsIgnoreCase("donut"))
				{
					tempWidget.setType("donut");
				}
			}
			else if (type.equalsIgnoreCase("text")) {
				tempWidget.setType("Badge");
				mode = panelObject.get("mode").getAsString();
				System.out.println("Mode of widget is " + mode);
			}
			else if (type.equalsIgnoreCase("row")) {
				tempWidget.setType("Badge");
				mode = null;
				System.out.println("Mode of widget is " + mode);
			}
		/*	else if(type.equalsIgnoreCase("singlestat")) {
				JsonElement tempSingleStat = panelObject.get("guage");
				
				JsonObject tempSingleStatObject = tempSingleStat.getAsJsonObject();
				String ifGauge = tempSingleStatObject.get("show").getAsString();
				ifGauge.toLowerCase();
				logger.debug(tempWidget.getTitle() + " is of singlestat type having gauge.show = " + ifGauge);
				  if(ifGauge.contains("true"))
				  {
					  tempWidget.setType("guage");
					  mode="guage";
				  }
			}*/
			
			else if (type.equalsIgnoreCase("singlestat")) {
				
				System.out.println("Single stat");
				
				JsonObject tempGaugeObject = (panelObject.get("gauge")).getAsJsonObject();
				
				System.out.println("tempGaugeObject = " + tempGaugeObject );
				
				String ifGauge = (tempGaugeObject.get("show")).getAsString();
				
				System.out.println("ifGauge" + ifGauge);
				
				if(ifGauge.contains("true")) {
				tempWidget.setType("gauge");
				mode = "gauge";
				System.out.println("mode = \"gauge\"");
				}
			}
			else {
				tempWidget.setType("unsupported");
			}
			
			logger.debug("Widget Type: " + tempWidget.getType());
			System.out.println("Widget Type: " + tempWidget.getType());
			
			//set rowspan,colspan,xpos,ypos
			try {
				JsonObject gridPos = (JsonObject) panelObject.getAsJsonObject("gridPos");
				try {
					tempWidget.setRowspan(gridPos.get("h").getAsString());
					tempWidget.setColspan(gridPos.get("w").getAsString());
				} catch (Exception e) {
					System.out.println("Error setting span for Widget : " + e.toString());
				}
				
				try {
					tempWidget.setXpos(gridPos.get("x").getAsString());
					tempWidget.setYpos(gridPos.get("y").getAsString());
				} catch (Exception e) {
					System.out.println("Error setting Position for Widget : " + e.toString());
				}
			} catch (Exception e) {
				System.out.println("Error getting gridPos from Pannel : " + e.toString());
			}
			
			logger.debug("Widget span are " + tempWidget.getRowspan() + " & " + tempWidget.getColspan());
			logger.debug("Widget position is " + tempWidget.getXpos() + " on x-axis & " + tempWidget.getYpos() + "on y-axis");
			
			
			
			//set properties as per widget type
			
			switch (type) {
			case "table":
				
				List<ColumnType> columns =  new ArrayList<ColumnType> ();
				
				JsonArray styles = (JsonArray) panelObject.get("styles");
				

				
				//set toolbar
				WidgettoolbarType tempToolbar = new WidgettoolbarType();
				tempToolbar.setIsMaster("false");
				try {
					tempToolbar.setTitle(tempWidget.getTitle());
					logger.debug("Toolbar Title set to " + tempWidget.getTitle());
					System.out.println("Toolbar Title set to " + tempWidget.getTitle());
				} catch (Exception e) {
					System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
				}
				tempWidget.setToolbar(tempToolbar);
				
				
				//set Style
				tempWidget.setStyle("height: 250px;");
				logger.debug("Toolbar style set.");
				System.out.println("Toolbar style set.");
				
				
				//Get Columns from Styles of Pannel
				for (JsonElement jsonElement : styles) {
					
					try {
						JsonObject style = (JsonObject) jsonElement;
						ColumnType tempColumn = new ColumnType();
						tempColumn.setName((String) style.get("alias").getAsString());
						tempColumn.setId((String) style.get("pattern").getAsString());
						tempColumn.setField((String) style.get("pattern").getAsString());
						tempColumn.setLabeltype((String) style.get("type").getAsString());
						
						if(!(tempColumn.getName()).equals("")) {
							columns.add(tempColumn);
						}
					} catch (Exception e) {
						System.out.println("Eror getting columns for widget: " + e.toString());;
					}
					
				}
				

				//add Property to Grid Widget
				JsonArray temptargets = (JsonArray) panelObject.get("targets");
				String title = ((panelObject.get("title")).getAsString().replace(" ", ""));
				for (JsonElement target : temptargets) {
					try {
						JsonObject targetObject = (JsonObject) target;
						StoreType tempStore = buildStoreFromTarget(key, title, targetObject); // store without datasource;
						tempStoreName = tempStore.getName();
						PropertiesType tempProperty = new PropertiesType();
						tempProperty.setDataStore(tempStoreName);
						tempWidget.setProperties(tempProperty);
					} catch (Exception e) {
						System.out.println("Error getting store name for widget: " + e.toString());
					}
				}
								
				
				//add Columns to Grid Widget
				for (ColumnType e : columns) {
					tempWidget.getColumns().add(e);
				}
				
				//get & set tooltip for Widget
				tempWidget.setTooltip("true");
				//set chartidentity
				ChartIdentityType chartIdentityGridType = new ChartIdentityType();
				chartIdentityGridType.setName(tempWidget.getId()+tempWidget.getType());
				
				
				tempWidget.getChartidentity().add(chartIdentityGridType);
				
				break;
				
			case "graph":
				tempStoreName=null;
				if(mode.equalsIgnoreCase("time")) {
				//set style
				tempWidget.setStyle("height:230px;");
				
				//dynamicseries
				tempWidget.setDynamicseries("true");
				
				//set toolbar
				WidgettoolbarType tempToolbarLine = new WidgettoolbarType();
				tempToolbarLine.setIsMaster("false");
				try {
					tempToolbarLine.setChartFamily("graphical_time"); 
					tempToolbarLine.setTitle(tempWidget.getTitle());
					logger.debug("Toolbar Title set to " + tempWidget.getTitle());
					System.out.println("Toolbar Title set to " + tempWidget.getTitle());
				} catch (Exception e) {
					System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
				}
				tempWidget.setToolbar(tempToolbarLine);
				
				//colorRangeId
				tempWidget.setColorRangeId("tnpm1");
				
				//plots
				PlotType plotLineType =  new PlotType();
				//plotLineType.setmarkerSize("0");
				plotLineType.setMinBarSize("3");
				plotLineType.setGap("3");
				plotLineType.setName("default");
				plotLineType.setType("Lines");
				plotLineType.setMarkers("false");
				plotLineType.setMaxBarSize("20");
				
				tempWidget.getPlots().add(plotLineType);
				
				
				
				
				
				
				//series
					
					JsonArray tempLinetargets = (JsonArray) panelObject.get("targets");
					String titleLineSeries = ((panelObject.get("title")).getAsString().replace(" ", ""));
					for (JsonElement target : tempLinetargets) {
						try {
							JsonObject targetObject = (JsonObject) target;
							StoreType tempStore = buildStoreFromTarget(key, titleLineSeries, targetObject); // store without datasource;
							tempStoreName = tempStore.getName();
							SeriesType seriesTime = new SeriesType();
							seriesTime.setPlot("default");
							seriesTime.setName("series_"+tempStoreName);
							seriesTime.setSortorder("asc");
							seriesTime.setStore(tempStoreName);
							seriesTime.setSeriesColumn("item.metric");
							seriesTime.setLabelColumn("item.time_sec");
							seriesTime.setValue("item.value");
							seriesTime.setReplacevalueparam("value");
							seriesTime.setSortparam("time_sec");
							seriesTime.setLabelType("date and time");
							seriesTime.setLabelFormat("MMM d, yyyy");
							seriesTime.setLabelUnit("seconds");
							
							tempWidget.getSeries().add(seriesTime);
						} catch (Exception e) {
							System.out.println("Error getting store name for widget: " + e.toString());
						}
					}
				
					//axes
					
					AxisType axis = new AxisType();
					AxisType axes = new AxisType();
					
					//axisX
					//axis"majorLabels": "true",
					axis.setMaxLabelCharCount("false");//"minorLabels": "false",
					axis.setMinorTicks("false");//"minorTicks": "false",
					//"dropLabels": "false",
					axis.setRotation("0");
					axis.setName("x");
					axis.setTitleOrientation("away");//"titleOrientation": "away",
					axis.setMaxLabelCharCount("15");
					//"majorTickStep": "1",
					axis.setLabelSeries(tempStoreName);
					axis.setTitle("Time");
					axis.setMicroTicks("false");//"microTicks": "false"
					
					
					//axesY
					axes.setMinorLabels("false");
					axes.setMinorTicks("false");
					axes.setName("y");
					axes.setIncludeZero("true");//"includeZero": "true",
					axes.setVertical("true");//"vertical": "true",
					if (mode.equalsIgnoreCase("time")) 	
					axes.setTitle("");
					axes.setMicroTicks("false");//"microTicks": "false"
					
					tempWidget.getAxes().add(axis);
					tempWidget.getAxes().add(axes);
										
				
				//legend
				LegendType currentType = new LegendType();
				currentType.setStyle("margin: 10px 0 5px 10px; float:left;border:none !important;");
				currentType.setType("interactive");
				tempWidget.setLegend(currentType);
				
				
				//get & set tooltip for Widget
				tempWidget.setTooltip("true");
				//set chartidentity
				ChartIdentityType chartIdentityLineType = new ChartIdentityType();
				chartIdentityLineType.setName(tempWidget.getId()+tempWidget.getType());
				
				
				tempWidget.getChartidentity().add(chartIdentityLineType);
				}
				else if(mode.equalsIgnoreCase("series"))
				{			
					//set style
					tempWidget.setStyle("height:230px;");
					
					//set toolbar
					WidgettoolbarType tempToolBar = new WidgettoolbarType();
					tempToolBar.setIsMaster("false");
					try {
						tempToolBar.setChartFamily("graphical_time"); 
						tempToolBar.setTitle(tempWidget.getTitle());
						logger.debug("Toolbar Title set to " + tempWidget.getTitle());
						System.out.println("Toolbar Title set to " + tempWidget.getTitle());
					} catch (Exception e) {
						System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
					}
					tempWidget.setToolbar(tempToolBar);
					
					//colorRangeId
					tempWidget.setColorRangeId("tnpm1");
					
					//plots
					PlotType plotBar =  new PlotType();
					plotBar.setMinBarSize("3");
					plotBar.setGap("3");
					plotBar.setName("default");
					plotBar.setType("ClusteredColumns");
					plotBar.setMarkers("true");
					plotBar.setMaxBarSize("20");
					
					tempWidget.getPlots().add(plotBar);
					//axes
						AxisType axis_Xbar = new AxisType();
						AxisType axis_Ybar = new AxisType();
						
						//axisX
						//axis"majorLabels": "true",
						axis_Xbar.setMaxLabelCharCount("false");//"minorLabels": "false",
						axis_Xbar.setMinorTicks("false");//"minorTicks": "false",
						//"dropLabels": "false",
						axis_Xbar.setRotation("0");
						axis_Xbar.setName("x");
						axis_Xbar.setTitleOrientation("away");//"titleOrientation": "away",
						axis_Xbar.setMaxLabelCharCount("15");
						//"majorTickStep": "1",
						axis_Xbar.setLabelSeries(tempStoreName);
						axis_Xbar.setTitle("Time");
						axis_Xbar.setMicroTicks("false");//"microTicks": "false"
									
						//axesY
						axis_Ybar.setMinorLabels("false");
						axis_Ybar.setMinorTicks("false");
						axis_Ybar.setName("y");
						axis_Ybar.setIncludeZero("true");//"includeZero": "true",
						axis_Ybar.setVertical("true");//"vertical": "true",
						if (mode.equalsIgnoreCase("time")) 	
						axis_Ybar.setTitle("");
						axis_Ybar.setMicroTicks("false");//"microTicks": "false"
						
						tempWidget.getAxes().add(axis_Xbar);
						tempWidget.getAxes().add(axis_Ybar);
						//series
						JsonArray tempBartargets = (JsonArray) panelObject.get("targets");
						String titleBarSeries = ((panelObject.get("title")).getAsString().replace(" ", ""));
						for (JsonElement target : tempBartargets) {
							try {
								JsonObject targetObject = (JsonObject) target;
								StoreType tempStore = buildStoreFromTarget(key,titleBarSeries, targetObject); // store without datasource;
								tempStoreName = tempStore.getName();
								SeriesType seriesTime = new SeriesType();
								seriesTime.setPlot("default");
								seriesTime.setName(tempStoreName);
								seriesTime.setSortorder("asc");
								seriesTime.setStore(tempStoreName);
								//seriesTime.setLabel("item.value");
								seriesTime.setValue("item.value");
								seriesTime.setLabelColumn("item.metric");
								seriesTime.setSeriesColumn("item.metric");
								seriesTime.setLabelValue("Metric");
								
							
								
								tempWidget.getSeries().add(seriesTime);
							} catch (Exception e) {
								System.out.println("Error getting store name for widget: " + e.toString());
							}
						}
					//legend
					LegendType currentType = new LegendType();
					currentType.setStyle("font-size:10px;margin-left:20px;width:100px;");
					currentType.setType("interactive");
					tempWidget.setLegend(currentType);
					
					//get & set tooltip for Widget
					tempWidget.setTooltip("true");
					//set chartidentity
					ChartIdentityType chartIdentityLineType = new ChartIdentityType();
					chartIdentityLineType.setName(tempWidget.getId()+tempWidget.getType());
					tempWidget.setDynamicseries("true");
					tempWidget.getChartidentity().add(chartIdentityLineType);
				}
				else if(mode.equalsIgnoreCase("histogram"))
				{			
					//set style
					tempWidget.setStyle("height:230px;");
					
					//set toolbar
					WidgettoolbarType tempToolBar = new WidgettoolbarType();
					tempToolBar.setIsMaster("false");
					try {
						tempToolBar.setChartFamily("graphical_time"); 
						tempToolBar.setTitle(tempWidget.getTitle());
						logger.debug("Toolbar Title set to " + tempWidget.getTitle());
						System.out.println("Toolbar Title set to " + tempWidget.getTitle());
					} catch (Exception e) {
						System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
					}
					tempWidget.setToolbar(tempToolBar);
					
					//colorRangeId
					tempWidget.setColorRangeId("tnpm1");
					
					//plots
					PlotType plotBar =  new PlotType();
					plotBar.setMinBarSize("3");
					plotBar.setGap("1");
					plotBar.setName("default");
					plotBar.setType("ClusteredColumns");
					plotBar.setMarkers("true");
					plotBar.setMaxBarSize("20");
					
					tempWidget.getPlots().add(plotBar);
					//axes
						AxisType axis_Xbar = new AxisType();
						AxisType axis_Ybar = new AxisType();
						
						//axisX
						//axis"majorLabels": "true",
						axis_Xbar.setMaxLabelCharCount("false");//"minorLabels": "false",
						axis_Xbar.setMinorTicks("false");//"minorTicks": "false",
						//"dropLabels": "false",
						axis_Xbar.setRotation("0");
						axis_Xbar.setName("x");
						axis_Xbar.setTitleOrientation("away");//"titleOrientation": "away",
						axis_Xbar.setMaxLabelCharCount("15");
						//"majorTickStep": "1",
						axis_Xbar.setLabelSeries("Metric_2198");
						axis_Xbar.setTitle("Time");
						axis_Xbar.setMicroTicks("false");//"microTicks": "false"
									
						//axesY
						axis_Ybar.setMinorLabels("false");
						axis_Ybar.setMinorTicks("false");
						axis_Ybar.setName("y");
						axis_Ybar.setIncludeZero("true");//"includeZero": "true",
						axis_Ybar.setVertical("true");//"vertical": "true",
						if (mode.equalsIgnoreCase("time")) 	
						axis_Ybar.setTitle("");
						axis_Ybar.setMicroTicks("false");//"microTicks": "false"
						
						tempWidget.getAxes().add(axis_Xbar);
						tempWidget.getAxes().add(axis_Ybar);
						//series
						JsonArray tempBartargets = (JsonArray) panelObject.get("targets");
						String titleBarSeries = ((panelObject.get("title")).getAsString().replace(" ", ""));
						for (JsonElement target : tempBartargets) {
							try {
								JsonObject targetObject = (JsonObject) target;
								StoreType tempStore = buildStoreFromTarget(key,titleBarSeries, targetObject); // store without datasource;
								tempStoreName = tempStore.getName();
								SeriesType seriesTime = new SeriesType();
								seriesTime.setPlot("default");
								seriesTime.setName(tempStoreName);
								seriesTime.setSortorder("asc");
								seriesTime.setStore(tempStoreName);
								seriesTime.setLabel("item.metric");
								seriesTime.setValue("item.value");
							
								
								tempWidget.getSeries().add(seriesTime);
							} catch (Exception e) {
								System.out.println("Error getting store name for widget: " + e.toString());
							}
						}
					//legend
					LegendType currentType = new LegendType();
					currentType.setStyle("font-size:10px;margin-left:20px;width:100px;");
					currentType.setType("interactive");
					tempWidget.setLegend(currentType);
					
					//get & set tooltip for Widget
					tempWidget.setTooltip("true");
					//set chartidentity
					ChartIdentityType chartIdentityLineType = new ChartIdentityType();
					chartIdentityLineType.setName(tempWidget.getId()+tempWidget.getType());
					
					tempWidget.getChartidentity().add(chartIdentityLineType);
				}
				break;
			case "text":
				
				//text - markdown
				
				if(mode.equalsIgnoreCase("markdown")) {
					String textBox = panelObject.get("content").getAsString();
					System.out.println("Text box text: " + textBox);
					tempWidget.setBadgeType("titleBadge");
					tempWidget.setBadgelabel(textBox);
					tempWidget.setValue("");
					tempWidget.setDifferenceFontSize("0px");
					tempWidget.setStyle("height: 40px;text-align: left;padding: 0 0 0 0px;");
					tempWidget.setUnit("");
					tempWidget.setPrevValue("");
					tempWidget.setLabelStyle("TEXT_LABEL_STYLE");
					tempWidget.setLayoutClass("noBorder");
					tempWidget.setIsReverse("true");	
					
					//add Property to Grid Widget
					JsonArray tempTextTargets = (JsonArray) panelObject.get("targets");
					String titleText = ((panelObject.get("title")).getAsString().replace(" ", ""));
					PropertiesType tempProperty = new PropertiesType();
					tempProperty.setLabelFontSize("'18px'");
					tempProperty.setFontsize("'14px'");
					
					try {
							for (JsonElement target : tempTextTargets) {
								
									JsonObject targetObject = (JsonObject) target;
									StoreType tempStore = buildStoreFromTarget(key, titleText, targetObject); // store without datasource;
									tempStoreName = tempStore.getName();
									tempProperty.setDataStore(tempStoreName);
									tempWidget.setProperties(tempProperty);
							}		
					} catch (NullPointerException e) {
							System.out.println("Error getting store name for widget: " + e.toString());
							System.out.println("Setting null");
							tempProperty.setStore("");
							tempProperty.setColors("['#ea4d31','#14d603']");
							tempProperty.setRanges("[0,95,100]");
							tempWidget.setProperties(tempProperty);
					} catch (Exception e) {
							System.out.println("Error getting store name for widget: " + e.toString());
					}
					
					
				}
				
				//text - html
				
				else if(mode.equalsIgnoreCase("html")) 
				{
					String textBox = panelObject.get("content").getAsString();
					System.out.println("Text box text: " + textBox);
				}
				
				//legend
				
				//get & set tooltip for Widget
				//tempWidget.setTooltip("true");
				//set chartidentity
				ChartIdentityType chartIdentityTextType = new ChartIdentityType();
				chartIdentityTextType.setName(tempWidget.getId()+tempWidget.getType());
				
				tempWidget.getChartidentity().add(chartIdentityTextType);

				
				
				break;
			case "row":
				
				String textBox = panelObject.get("title").getAsString();
				System.out.println("Text box text: " + textBox);
				tempWidget.setBadgeType("titleBadge");
				tempWidget.setBadgelabel(textBox);
				tempWidget.setValue("");
				tempWidget.setDifferenceFontSize("0px");
				tempWidget.setStyle("height: 40px;text-align: left;padding: 0 0 0 0px;");
				tempWidget.setUnit("");
				tempWidget.setPrevValue("");
				tempWidget.setLabelStyle("TEXT_LABEL_STYLE");
				tempWidget.setLayoutClass("noBorder");
				tempWidget.setIsReverse("true");	
				
				//add Property to Grid Widget
				JsonArray tempTextTargets = (JsonArray) panelObject.get("targets");
				String titleText = ((panelObject.get("title")).getAsString().replace(" ", ""));
				PropertiesType tempProperty = new PropertiesType();
				tempProperty.setLabelFontSize("'18px'");
				tempProperty.setFontsize("'14px'");
				
				try {
						for (JsonElement target : tempTextTargets) {
							
								JsonObject targetObject = (JsonObject) target;
								StoreType tempStore = buildStoreFromTarget(key, titleText, targetObject); // store without datasource;
								tempStoreName = tempStore.getName();
								tempProperty.setDataStore(tempStoreName);
								tempWidget.setProperties(tempProperty);
						}		
				} catch (NullPointerException e) {
						System.out.println("Error getting store name for widget: " + e.toString());
						System.out.println("Setting null");
						tempProperty.setStore("");
						tempProperty.setColors("['#ea4d31','#14d603']");
						tempProperty.setRanges("[0,95,100]");
						tempWidget.setProperties(tempProperty);
				} catch (Exception e) {
						System.out.println("Error getting store name for widget: " + e.toString());
				}
				
			
			break;
			case "heatmap" :
				
				//set style
				tempWidget.setStyle("height:230px;");
				
				//set toolbar
				WidgettoolbarType tempToolbarHeatmap = new WidgettoolbarType();
				tempToolbarHeatmap.setIsMaster("false");
				try {
					tempToolbarHeatmap.setChartFamily("graphical_time"); 
					tempToolbarHeatmap.setTitle(tempWidget.getTitle());
					logger.debug("Toolbar Title set to " + tempWidget.getTitle());
					System.out.println("Toolbar Title set to " + tempWidget.getTitle());
				} catch (Exception e) {
					System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
				}
				tempWidget.setToolbar(tempToolbarHeatmap);
				
				//colorRangeId
				tempWidget.setColorRangeId("tnpmCSSRCSTR");
				
				//plots
				PlotType plotHeatmapType =  new PlotType();
				//plotLineType.setmarkerSize("0");
				plotHeatmapType.setMinBarSize("3");
				plotHeatmapType.setGap("3");
				plotHeatmapType.setName("default");
				plotHeatmapType.setType("Lines");
				plotHeatmapType.setMarkers("true");
				plotHeatmapType.setMaxBarSize("20");
				
				tempWidget.getPlots().add(plotHeatmapType);
				
				
				//axes
				
					AxisType axisHeatMap = new AxisType();
					AxisType axesHeatMap = new AxisType();
					
					//axisX
					//axis"majorLabels": "true",
					axisHeatMap.setMaxLabelCharCount("false");//"minorLabels": "false",
					axisHeatMap.setMinorTicks("false");//"minorTicks": "false",
					//"dropLabels": "false",
					axisHeatMap.setRotation("0");
					axisHeatMap.setName("x");
					axisHeatMap.setTitleOrientation("away");//"titleOrientation": "away",
					axisHeatMap.setMaxLabelCharCount("15");
					//"majorTickStep": "1",
					axisHeatMap.setLabelSeries("Metric_2198");
					axisHeatMap.setTitle("");
					axisHeatMap.setMicroTicks("false");//"microTicks": "false"
					
					
					//axesY
					axesHeatMap.setMinorLabels("false");
					axesHeatMap.setMinorTicks("false");
					axesHeatMap.setName("y");
					axesHeatMap.setIncludeZero("true");//"includeZero": "true",
					axesHeatMap.setVertical("true");//"vertical": "true",
					if (mode.equalsIgnoreCase("time")) 	
						axesHeatMap.setTitle("Time");
					axesHeatMap.setMicroTicks("false");//"microTicks": "false"
					
					tempWidget.getAxes().add(axisHeatMap);
					tempWidget.getAxes().add(axesHeatMap);
										
				
				
				
				
				
				//series
				
					JsonArray tempHeatMaptargets = (JsonArray) panelObject.get("targets");
					String titleHeatMapSeries = ((panelObject.get("title")).getAsString().replace(" ", ""));
					for (JsonElement target : tempHeatMaptargets) {
						try {
							JsonObject targetObject = (JsonObject) target;
							StoreType tempStore = buildStoreFromTarget(key, titleHeatMapSeries, targetObject); // store without datasource;
							tempStoreName = tempStore.getName();
							SeriesType seriesTime = new SeriesType();
							seriesTime.setPlot("default");
							seriesTime.setName(tempStoreName);
							seriesTime.setSortorder("asc");
							seriesTime.setStore(tempStoreName);
							seriesTime.setLabel("item.time_sec");
							seriesTime.setValue("item.value");
							seriesTime.setSortparam("time_sec");
							
							tempWidget.getSeries().add(seriesTime);
						} catch (Exception e) {
							System.out.println("Error getting store name for widget: " + e.toString());
						}
					}
				
				
				
				//legend
				LegendType currentHeatMapType = new LegendType();
				currentHeatMapType.setStyle("margin: 10px 0 5px 10px; float:left;border:none !important;");
				currentHeatMapType.setType("interactive");
				tempWidget.setLegend(currentHeatMapType);
				
				
				//get & set tooltip for Widget
				tempWidget.setTooltip("true");
				//set chartidentity
				ChartIdentityType chartIdentityHeatMapType = new ChartIdentityType();
				chartIdentityHeatMapType.setName(tempWidget.getId()+tempWidget.getType());
				
				
				tempWidget.getChartidentity().add(chartIdentityHeatMapType);
				break;
				
			case "grafana-piechart-panel" :
				
				//set style
				tempWidget.setStyle("height:230px;");
				
				//set toolbar
				WidgettoolbarType tempToolbarPieChart = new WidgettoolbarType();
				tempToolbarPieChart.setIsMaster("false");
				try {
					tempToolbarPieChart.setChartFamily("circular"); 
					tempToolbarPieChart.setTitle(tempWidget.getTitle());
					
					logger.debug("Toolbar Title set to " + tempWidget.getTitle());
					System.out.println("Toolbar Title set to " + tempWidget.getTitle());
				} catch (Exception e) {
					System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
				}
				tempWidget.setToolbar(tempToolbarPieChart);
				
				//colorRangeId
				tempWidget.setColorRangeId("tnpm7");
				//plots
				PlotType plotPieChartType =  new PlotType();
				//plotLineType.setmarkerSize("0");
				
				plotPieChartType.setRadius("60");
				plotPieChartType.setLabels("false");
				plotPieChartType.setMarkers("true");
				tempWidget.getPlots().add(plotPieChartType);
				
				
				//series
				
					JsonArray tempPieCharttargets = (JsonArray) panelObject.get("targets");
					String titlePieChartSeries = ((panelObject.get("title")).getAsString().replace(" ", ""));
					for (JsonElement target : tempPieCharttargets) {
						try {
							JsonObject targetObject = (JsonObject) target;
							StoreType tempStore = buildStoreFromTarget(key, titlePieChartSeries, targetObject); // store without datasource;
							tempStoreName = tempStore.getName();
							SeriesType seriesTime = new SeriesType();
							seriesTime.setPlot("default");
							seriesTime.setName(tempStoreName);
							seriesTime.setSortorder("asc");
							seriesTime.setStore(tempStoreName);
							seriesTime.setLabel("item.metric");
							seriesTime.setValue("item.value");
							seriesTime.setSortparam("time_sec");
							
							tempWidget.getSeries().add(seriesTime);
						} catch (Exception e) {
							System.out.println("Error getting store name for widget: " + e.toString());
						}
					}
				
				
				
				//legend
				LegendType currentPieChartType = new LegendType();
				currentPieChartType.setStyle("PIE_LABEL_STYLE");
				currentPieChartType.setType("interactive");
				
				tempWidget.setLegend(currentPieChartType);
				
				
				//get & set tooltip for Widget
				tempWidget.setTooltip("true");
				//set chartidentity
				ChartIdentityType PiechartIdentityType = new ChartIdentityType();
				PiechartIdentityType.setName(tempWidget.getId()+tempWidget.getType());
				
				
				tempWidget.getChartidentity().add(PiechartIdentityType);
				
				break;
				
				
			case "singlestat" :
				//toolbar
				tempWidget.setStyle("height:230px;");
				WidgettoolbarType tempToolGuage = new WidgettoolbarType();
				tempToolGuage.setIsMaster("false");
				try {
					//tempToolbarPieChart.setChartFamily("circular"); 
					tempToolGuage.setTitle(tempWidget.getTitle());
					
					logger.debug("Toolbar Title set to " + tempWidget.getTitle());
					System.out.println("Toolbar Title set to " + tempWidget.getTitle());
				} catch (Exception e) {
					System.out.println("Error getting title for Widget Toolbar : " + e.toString()); 
				}
				tempToolGuage.setMaximize("true");
				tempToolGuage.setTitleUpdation("replace");
				tempToolGuage.setCollapse("true");
				tempWidget.setToolbar(tempToolGuage);
				
				
				JsonObject tempGaugeObject = (panelObject.get("gauge")).getAsJsonObject(); 
				String max = (tempGaugeObject.get("maxValue")).getAsString(); 
				String min = (tempGaugeObject.get("minValue")).getAsString();  
				System.out.println("guage min value and max value :"+ min+"  " + max);
				String threshold = (panelObject.get("thresholds")).getAsString(); 
				System.out.println("threshld values:" + threshold);
				String ranges = min+","+threshold+","+max;
				System.out.println("Ranges" + ranges);
				//Properties
				tempStoreName=null;
				JsonArray tempGuageTargets = (JsonArray) panelObject.get("targets");
				String titleguage = ((panelObject.get("title")).getAsString().replace(" ", ""));
				for (JsonElement target : tempGuageTargets) {
					try {
						JsonObject targetObject = (JsonObject) target;
						StoreType tempStore = buildStoreFromTarget(key,titleguage, targetObject); // store without datasource;
						tempStoreName = tempStore.getName();
						
					} catch (Exception e) {
						System.out.println("Error getting store name for widget: " + e.toString());
					}
				}
				PropertiesType guageProp = new PropertiesType();
				guageProp.setRadius("150");
				guageProp.setAxisWidth("5");
				//guageProp.setMaximum("");
				guageProp.setMinimum(min);
			    guageProp.setValueAttr("value");
			    guageProp.setStore(tempStoreName);
			    guageProp.setRanges("["+ranges+"]");
			    guageProp.setColors("['#56cf87','#f5ef7d','#fd7a7b']");
			    guageProp.setMaximum(max);
			    tempWidget.setProperties(guageProp);
			    
			
			//Chart Identity
				ChartIdentityType guageIdentityType = new ChartIdentityType();
				guageIdentityType.setName(tempWidget.getId()+tempWidget.getType());	
				
				tempWidget.getChartidentity().add(guageIdentityType);
				
			//Legend
				LegendType currentGuage = new LegendType();
				currentGuage.setStyle("margin:5px;float:left;");
				currentGuage.setType("interactive");
				
				tempWidget.setLegend(currentGuage);
				
				
			default:
				System.out.println("Widget " +type+ " type is not compatible with tool yet");
				break;
			}
			
			
			int temp =(Integer.parseInt(tempWidget.getYpos()) + Integer.parseInt(tempWidget.getRowspan()));
			
			if (totalRows<temp) {
				totalRows=temp;
			}
			
			//tempWidget.setIndex(String.valueOf(index));
			index++;
			tempWidgets.add(tempWidget);
		}
		return tempWidgets;
	}
	
	

} 