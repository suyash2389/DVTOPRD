package com.ibm.csi.npm.dvreportmodel.main;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.csi.npm.dashjson.DashboardType;
import com.ibm.csi.npm.dashjson.StoreType;
import com.ibm.csi.npm.dashjson.WidgetType;
import com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.*;

public class GrafanaPrometheusTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		JsonParser parser = new JsonParser();
		
		String reportJsonString="{\"annotations\":{\"list\":[]},\"editable\":true,\"gnetId\":null,\"hideControls\":false,\"id\":60,\"links\":[],\"refresh\":\"10s\",\"rows\":[{\"collapse\":false,\"editable\":true,\"height\":\"100px\",\"panels\":[{\"cacheTimeout\":null,\"colorBackground\":false,\"colorValue\":false,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"datasource\":\"Prometheus10.222.50.32\",\"decimals\":1,\"editable\":true,\"error\":false,\"format\":\"s\",\"gauge\":{\"maxValue\":100,\"minValue\":0,\"show\":false,\"thresholdLabels\":false,\"thresholdMarkers\":true},\"hideTimeOverride\":true,\"id\":1,\"interval\":null,\"isNew\":true,\"links\":[],\"mappingType\":1,\"mappingTypes\":[{\"name\":\"value to text\",\"value\":1},{\"name\":\"range to text\",\"value\":2}],\"maxDataPoints\":100,\"nullPointMode\":\"connected\",\"nullText\":null,\"postfix\":\"s\",\"postfixFontSize\":\"80%\",\"prefix\":\"\",\"prefixFontSize\":\"50%\",\"rangeMaps\":[{\"from\":\"null\",\"text\":\"N/A\",\"to\":\"null\"}],\"span\":3,\"sparkline\":{\"fillColor\":\"rgba(31, 118, 189, 0.18)\",\"full\":false,\"lineColor\":\"rgb(31, 120, 193)\",\"show\":false},\"targets\":[{\"expr\":\"(time() - process_start_time_seconds{instance=\\\"localhost:9090\\\",job=\\\"prometheus\\\"})\",\"interval\":\"10s\",\"intervalFactor\":1,\"legendFormat\":\"\",\"refId\":\"A\",\"step\":10}],\"thresholds\":\"\",\"timeFrom\":\"10s\",\"timeShift\":null,\"title\":\"Prometheus Uptime\",\"type\":\"singlestat\",\"valueFontSize\":\"80%\",\"valueMaps\":[{\"op\":\"=\",\"text\":\"N/A\",\"value\":\"null\"}],\"valueName\":\"avg\"},{\"cacheTimeout\":null,\"colorBackground\":false,\"colorValue\":false,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"datasource\":\"Prometheus10.222.50.32\",\"decimals\":2,\"editable\":true,\"error\":false,\"format\":\"bytes\",\"gauge\":{\"maxValue\":100,\"minValue\":0,\"show\":false,\"thresholdLabels\":false,\"thresholdMarkers\":true},\"hideTimeOverride\":true,\"id\":5,\"interval\":null,\"isNew\":true,\"links\":[],\"mappingType\":1,\"mappingTypes\":[{\"name\":\"value to text\",\"value\":1},{\"name\":\"range to text\",\"value\":2}],\"maxDataPoints\":100,\"nullPointMode\":\"connected\",\"nullText\":null,\"postfix\":\"\",\"postfixFontSize\":\"50%\",\"prefix\":\"\",\"prefixFontSize\":\"50%\",\"rangeMaps\":[{\"from\":\"null\",\"text\":\"N/A\",\"to\":\"null\"}],\"span\":3,\"sparkline\":{\"fillColor\":\"rgba(31, 118, 189, 0.18)\",\"full\":false,\"lineColor\":\"rgb(31, 120, 193)\",\"show\":false},\"targets\":[{\"expr\":\"sum(container_memory_usage_bytes{container_label_org_label_schema_group=\\\"monitoring\\\"})\",\"interval\":\"10s\",\"intervalFactor\":1,\"legendFormat\":\"\",\"refId\":\"A\",\"step\":10}],\"thresholds\":\"\",\"timeFrom\":\"10s\",\"timeShift\":null,\"title\":\"Memory Usage\",\"type\":\"singlestat\",\"valueFontSize\":\"80%\",\"valueMaps\":[{\"op\":\"=\",\"text\":\"N/A\",\"value\":\"null\"}],\"valueName\":\"avg\"},{\"cacheTimeout\":null,\"colorBackground\":false,\"colorValue\":false,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"datasource\":\"Prometheus10.222.50.32\",\"editable\":true,\"error\":false,\"format\":\"none\",\"gauge\":{\"maxValue\":100,\"minValue\":0,\"show\":false,\"thresholdLabels\":false,\"thresholdMarkers\":true},\"hideTimeOverride\":true,\"id\":3,\"interval\":null,\"isNew\":true,\"links\":[],\"mappingType\":1,\"mappingTypes\":[{\"name\":\"value to text\",\"value\":1},{\"name\":\"range to text\",\"value\":2}],\"maxDataPoints\":100,\"nullPointMode\":\"connected\",\"nullText\":null,\"postfix\":\"\",\"postfixFontSize\":\"50%\",\"prefix\":\"\",\"prefixFontSize\":\"50%\",\"rangeMaps\":[{\"from\":\"null\",\"text\":\"N/A\",\"to\":\"null\"}],\"span\":3,\"sparkline\":{\"fillColor\":\"rgba(31, 118, 189, 0.18)\",\"full\":false,\"lineColor\":\"rgb(31, 120, 193)\",\"show\":false},\"targets\":[{\"expr\":\"prometheus_tsdb_head_chunks\",\"interval\":\"10s\",\"intervalFactor\":1,\"refId\":\"A\",\"step\":10}],\"thresholds\":\"\",\"timeFrom\":\"10s\",\"timeShift\":null,\"title\":\"In-Memory Chunks\",\"type\":\"singlestat\",\"valueFontSize\":\"80%\",\"valueMaps\":[{\"op\":\"=\",\"text\":\"N/A\",\"value\":\"null\"}],\"valueName\":\"avg\"},{\"cacheTimeout\":null,\"colorBackground\":false,\"colorValue\":false,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"datasource\":\"Prometheus10.222.50.32\",\"editable\":true,\"error\":false,\"format\":\"none\",\"gauge\":{\"maxValue\":100,\"minValue\":0,\"show\":false,\"thresholdLabels\":false,\"thresholdMarkers\":true},\"hideTimeOverride\":true,\"id\":2,\"interval\":null,\"isNew\":true,\"links\":[],\"mappingType\":1,\"mappingTypes\":[{\"name\":\"value to text\",\"value\":1},{\"name\":\"range to text\",\"value\":2}],\"maxDataPoints\":100,\"nullPointMode\":\"connected\",\"nullText\":null,\"postfix\":\"\",\"postfixFontSize\":\"50%\",\"prefix\":\"\",\"prefixFontSize\":\"50%\",\"rangeMaps\":[{\"from\":\"null\",\"text\":\"N/A\",\"to\":\"null\"}],\"span\":3,\"sparkline\":{\"fillColor\":\"rgba(31, 118, 189, 0.18)\",\"full\":false,\"lineColor\":\"rgb(31, 120, 193)\",\"show\":false},\"targets\":[{\"expr\":\"prometheus_tsdb_head_series\",\"interval\":\"10s\",\"intervalFactor\":1,\"refId\":\"A\",\"step\":10}],\"thresholds\":\"\",\"timeFrom\":\"10s\",\"timeShift\":null,\"title\":\"In-Memory Series\",\"type\":\"singlestat\",\"valueFontSize\":\"80%\",\"valueMaps\":[{\"op\":\"=\",\"text\":\"N/A\",\"value\":\"null\"}],\"valueName\":\"avg\"}],\"title\":\"Overview\"},{\"collapse\":false,\"editable\":true,\"height\":\"250px\",\"panels\":[{\"aliasColors\":{},\"bars\":false,\"datasource\":\"Prometheus10.222.50.32\",\"decimals\":2,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":6,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":false,\"max\":true,\"min\":true,\"rightSide\":true,\"show\":true,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":12,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"sum(rate(container_cpu_user_seconds_total{container_label_org_label_schema_group=\\\"monitoring\\\"}[1m]) * 100  / scalar(count(node_cpu{mode=\\\"user\\\"}))) by (name)\",\"intervalFactor\":10,\"legendFormat\":\"{{ name }}\",\"refId\":\"A\",\"step\":10}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Container CPU Usage\",\"tooltip\":{\"msResolution\":true,\"shared\":true,\"sort\":2,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"percent\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true}]},{\"aliasColors\":{},\"bars\":false,\"datasource\":\"Prometheus10.222.50.32\",\"decimals\":2,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":7,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":false,\"max\":true,\"min\":true,\"rightSide\":true,\"show\":true,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":12,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"sum(container_memory_usage_bytes{container_label_org_label_schema_group=\\\"monitoring\\\"}) by (name)\",\"interval\":\"\",\"intervalFactor\":10,\"legendFormat\":\"{{ name }}\",\"metric\":\"container_memory_usage_bytes\",\"refId\":\"A\",\"step\":10}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Container Memory Usage\",\"tooltip\":{\"msResolution\":true,\"shared\":true,\"sort\":2,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"bytes\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":false}]}],\"title\":\"Resources usage\"},{\"collapse\":false,\"editable\":true,\"height\":\"250px\",\"panels\":[{\"aliasColors\":{},\"bars\":false,\"datasource\":null,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":13,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":true,\"max\":true,\"min\":true,\"show\":true,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":6,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"prometheus_local_storage_chunks_to_persist\",\"intervalFactor\":2,\"legendFormat\":\"chunks\",\"refId\":\"A\",\"step\":2}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Chunks to persist\",\"tooltip\":{\"msResolution\":false,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":0,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true}]},{\"aliasColors\":{},\"bars\":false,\"datasource\":null,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":14,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":true,\"max\":true,\"min\":true,\"rightSide\":false,\"show\":true,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[{\"alias\":\"score\",\"color\":\"#705DA0\"}],\"span\":6,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"prometheus_local_storage_persistence_urgency_score\",\"intervalFactor\":2,\"legendFormat\":\"score\",\"refId\":\"A\",\"step\":2}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Persistence Urgency\",\"tooltip\":{\"msResolution\":false,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":0,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true}]},{\"aliasColors\":{},\"bars\":false,\"datasource\":null,\"decimals\":0,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":15,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":false,\"max\":true,\"min\":true,\"rightSide\":true,\"show\":true,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":6,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"rate(prometheus_local_storage_chunk_ops_total[1m])\",\"intervalFactor\":2,\"legendFormat\":\"{{ type }}\",\"refId\":\"A\",\"step\":2}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Chunk ops\",\"tooltip\":{\"msResolution\":false,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":0,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":false}]},{\"aliasColors\":{},\"bars\":false,\"datasource\":null,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":16,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":true,\"max\":true,\"min\":true,\"rightSide\":false,\"show\":true,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":6,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"prometheus_local_storage_checkpoint_duration_seconds\",\"intervalFactor\":2,\"legendFormat\":\"{{ job }}\",\"refId\":\"A\",\"step\":2}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Checkpoint duration\",\"tooltip\":{\"msResolution\":false,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"s\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":0,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":false}]}],\"title\":\"Prometheus stats\"},{\"collapse\":false,\"editable\":true,\"height\":\"250px\",\"panels\":[{\"aliasColors\":{},\"bars\":false,\"datasource\":\"Prometheus10.222.50.32\",\"decimals\":0,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":8,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":true,\"current\":false,\"max\":true,\"min\":true,\"show\":false,\"total\":false,\"values\":true},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":4,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"rate(prometheus_local_storage_ingested_samples_total[5m])\",\"intervalFactor\":1,\"legendFormat\":\"samples\",\"refId\":\"A\",\"step\":1}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Samples ingested 5m rate\",\"tooltip\":{\"msResolution\":true,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":10,\"max\":null,\"min\":null,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true}]},{\"aliasColors\":{},\"bars\":false,\"datasource\":\"Prometheus10.222.50.32\",\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":10,\"isNew\":true,\"legend\":{\"avg\":false,\"current\":false,\"max\":false,\"min\":false,\"show\":false,\"total\":false,\"values\":false},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":4,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"rate(prometheus_target_interval_length_seconds_count[5m])\",\"intervalFactor\":2,\"legendFormat\":\"{{ interval }}\",\"refId\":\"A\",\"step\":2}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Target Scrapes\",\"tooltip\":{\"msResolution\":true,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true}]},{\"aliasColors\":{},\"bars\":false,\"datasource\":\"Prometheus10.222.50.32\",\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":11,\"isNew\":true,\"legend\":{\"alignAsTable\":false,\"avg\":false,\"current\":false,\"max\":false,\"min\":false,\"show\":false,\"total\":false,\"values\":false},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":4,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"prometheus_target_interval_length_seconds{quantile!=\\\"0.01\\\", quantile!=\\\"0.05\\\"}\",\"intervalFactor\":2,\"legendFormat\":\"{{quantile}} ({{interval}})\",\"refId\":\"A\",\"step\":2}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Scrape Duration\",\"tooltip\":{\"msResolution\":true,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":true}]}],\"title\":\"Prometheus targets\"},{\"collapse\":false,\"editable\":true,\"height\":\"250px\",\"panels\":[{\"aliasColors\":{},\"bars\":false,\"datasource\":\"Prometheus10.222.50.32\",\"decimals\":0,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":9,\"isNew\":true,\"legend\":{\"avg\":false,\"current\":false,\"max\":false,\"min\":false,\"show\":false,\"total\":false,\"values\":false},\"lines\":true,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":5,\"points\":false,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":6,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"sum(irate(http_requests_total[1m])) \",\"interval\":\"10s\",\"intervalFactor\":2,\"legendFormat\":\"requests\",\"metric\":\"http_requests_total\",\"refId\":\"A\",\"step\":20}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"HTTP Requests\",\"tooltip\":{\"msResolution\":true,\"shared\":true,\"sort\":0,\"value_type\":\"cumulative\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":0,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":false}]},{\"aliasColors\":{},\"bars\":true,\"datasource\":null,\"decimals\":0,\"editable\":true,\"error\":false,\"fill\":1,\"grid\":{\"threshold1\":null,\"threshold1Color\":\"rgba(216, 200, 27, 0.27)\",\"threshold2\":null,\"threshold2Color\":\"rgba(234, 112, 112, 0.22)\"},\"id\":12,\"isNew\":true,\"legend\":{\"alignAsTable\":true,\"avg\":false,\"current\":true,\"hideEmpty\":false,\"hideZero\":false,\"max\":false,\"min\":false,\"rightSide\":true,\"show\":true,\"total\":false,\"values\":true},\"lines\":false,\"linewidth\":2,\"links\":[],\"nullPointMode\":\"connected\",\"percentage\":false,\"pointradius\":2,\"points\":true,\"renderer\":\"flot\",\"seriesOverrides\":[],\"span\":6,\"stack\":false,\"steppedLine\":false,\"targets\":[{\"expr\":\"sum(ALERTS{alertstate=\\\"firing\\\"}) by (alertname)\",\"interval\":\"30s\",\"intervalFactor\":1,\"legendFormat\":\"{{ alertname }}\",\"refId\":\"A\",\"step\":30}],\"timeFrom\":null,\"timeShift\":null,\"title\":\"Alerts\",\"tooltip\":{\"msResolution\":false,\"shared\":false,\"sort\":0,\"value_type\":\"individual\"},\"type\":\"graph\",\"xaxis\":{\"show\":true},\"yaxes\":[{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":0,\"show\":true},{\"format\":\"short\",\"label\":null,\"logBase\":1,\"max\":null,\"min\":null,\"show\":false}]}],\"title\":\"Prometheus requests\"}],\"schemaVersion\":12,\"sharedCrosshair\":true,\"style\":\"dark\",\"tags\":[\"prometheus\"],\"templating\":{\"list\":[]},\"time\":{\"from\":\"now-15m\",\"to\":\"now\"},\"timepicker\":{\"refresh_intervals\":[\"5s\",\"10s\",\"30s\",\"1m\",\"5m\",\"15m\",\"30m\",\"1h\",\"2h\",\"1d\"],\"time_options\":[\"5m\",\"15m\",\"1h\",\"6h\",\"12h\",\"24h\",\"2d\",\"7d\",\"30d\"]},\"timezone\":\"browser\",\"title\":\"Monitor Services\",\"uid\":\"p-z9-8Kmk\",\"version\":1}";
		
		System.out.println(reportJsonString);
		
		JsonObject reportJSON = (JsonObject) parser.parse(reportJsonString);
		
		JsonObject tempReport = reportJSON;
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
    	
    	
	
    	DashboardType tempDash = new DashboardType();
    	
    	//id
    	String key = (String) (tempReport.get("uid")).getAsString();
		tempDash.setId(key);
		System.out.print("with Id: " + tempDash.getId());
	
	//title
		tempDash.setTitle((String) (tempReport.get("title")).getAsString());
		System.out.println(" & Title: \"" + tempDash.getTitle() + "\"");
	
	
	//description
		tempDash.setDescription("Report " + ((String) (tempReport.get("uid")).getAsString()) + " version " + ((String) (tempReport.get("version")).getAsString()) + " generated by Grafana to PRD Migration Tool");
	
	//store
	try {
		
	
	ArrayList<StoreType> tempStores = com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.buildStoresFromPanels(key,tempPanels); 
	for (StoreType e : tempStores) {
		tempDash.getStores().add(e);
	}

	
	//filter
	//to be planned
	
	//widgets
	ArrayList<WidgetType> tempWidgets = com.ibm.csi.npm.dvreportmodel.main.GrafanaReportCollector.buildWidgetsFromPanels(key,tempPanels); 
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
	tempDash.setTotalrows((String.valueOf("100")));
	System.out.println("Total Rows as "+ tempDash.getTotalrows());
	
	
	//localized
	tempDash.setLocalized("false"); 
	
	//Add temp object in ArrayList
	
	System.out.println(tempDash.toString());
	
	System.out.println("\n Dashboard extracted Succesfully");
	
	} catch (Exception e) {
		System.out.println("\n Failed with error:" + e.toString());
		
	}
	System.out.println();
	System.out.println("==================================================");
	System.out.println();

    	
	
	
	}

}
