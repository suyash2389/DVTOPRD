<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dvr="http://www.ibm.com/csi/npm/dvreportermodel"
	xmlns:tns="http://www.ibm.com/csi/npm/dashjson" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:template match="dvr:reporter">
		<tns:Dashboard xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://www.ibm.com/csi/npm/dashjson file:D:\Study\PRD\Convertor\resources\DashJSON.xsd">
			<tns:id>
				<xsl:value-of select="./dvr:id" />
			</tns:id>
			<tns:title>
				<xsl:value-of select="./dvr:name" />
			</tns:title>
			<tns:description>%25AP.global.Dashboard_Report</tns:description>
			<tns:blockHeight><xsl:value-of select="./dvr:blockHeight" /></tns:blockHeight>
			<tns:bootstrapCols>60</tns:bootstrapCols>
			<tns:totalrows><xsl:value-of select="./dvr:totalrows" /></tns:totalrows>
			<tns:totalcols><xsl:value-of select="./dvr:totalcols" /></tns:totalcols>
			<tns:localized><xsl:value-of select="./dvr:localized"/></tns:localized>
			<xsl:for-each select="dvr:ReportWidget/dvr:storeUrl">
				<tns:stores>
					<tns:name>
						<xsl:value-of select="../dvr:storeName" />
					</tns:name>
					<tns:query>
						<xsl:value-of select="."></xsl:value-of> <!-- TO DO should come from widgets -->
					</tns:query>
					<tns:datasource>DataView</tns:datasource>
					<tns:dvApiStoreCase>dvApiStoreCase</tns:dvApiStoreCase>
				</tns:stores>
			</xsl:for-each>
			
			<!-- mandatory stores added on 2018 02 12 -->>	
				<tns:stores>
					<tns:name>infraContextRootStore</tns:name>
					<tns:query>select 'PV' as contextrootvalue  from dual</tns:query>
					<tns:datasource>default</tns:datasource>
					<tns:cases>
						<tns:default></tns:default>
					</tns:cases>
				</tns:stores>
				<tns:stores>
					<tns:name>infraHiddenDBTimeFilterStore</tns:name>
					<tns:query>select to_char(sysdate,'yyyy.MM.dd') || '.00.00.00' as startTime, to_char(sysdate1,'yyyy.MM.dd') || '.00.00.00' as endTime from dual</tns:query>
					<tns:datasource>default</tns:datasource>
					<tns:default></tns:default>
				</tns:stores>
				<tns:stores>
					<tns:name>infraSubElmtGrpIDStore</tns:name>
					<tns:query>URL_subElementGrp</tns:query>
					<tns:datasource>DataView</tns:datasource>
					<tns:default></tns:default>
				</tns:stores>
				<tns:stores>
					<tns:name>infraSubElmtIDStore</tns:name>
					<tns:query>URL_sEs</tns:query>
					<tns:datasource>DataView</tns:datasource>
					<tns:default></tns:default>
				</tns:stores>
				<tns:stores>
					<tns:name>infraTzStore</tns:name>
					<tns:query>{query}</tns:query>
					<tns:datasource>DataView</tns:datasource>
					<tns:cases>
						<tns:default><tns:query>URL__defaultTimeSpan</tns:query></tns:default>
						<tns:timeSpancustom><tns:query>URL_timeSpancustom</tns:query></tns:timeSpancustom>
					</tns:cases>
				</tns:stores>
				<tns:stores>
					<tns:name>infraTzStartTimeStore</tns:name>
					<tns:query>{query}</tns:query>
					<tns:datasource>default</tns:datasource>
					<tns:cases>
						<tns:default></tns:default>
						<tns:timePeriodLastDay><tns:query>URL_LastDay</tns:query></tns:timePeriodLastDay>
						<tns:timePeriodToday><tns:query>URL_Today</tns:query></tns:timePeriodToday>
						<tns:timePeriodLastMonth><tns:query>URL_LastMonth</tns:query></tns:timePeriodLastMonth>
						<tns:timePeriodLastWeek><tns:query>URL_LastWeek</tns:query></tns:timePeriodLastWeek>
						<tns:timePeriodLastYear><tns:query>select(yr||'.01.01.00.00.00') as value from (select to_char(value,'yyyy') as yr from (select (dateadd('year',-1,getdate())) as value from dual))/* Last Year*/</tns:query></tns:timePeriodLastYear>
						<tns:timePeriodCustom><tns:query>select '{fancyTimeFrom}' as value, '{fancyTimeTo}' as value1 from dual</tns:query></tns:timePeriodCustom>
					</tns:cases>
				</tns:stores>				
			<!-- mandatory stores added on 2018 02 12 -->>	
				
			<tns:filterbar>
				<tns:drilldown>true</tns:drilldown>
				<tns:displaynames>Context Root,Start Time,End Time,Sub-Element Group,Time Period,Date and Time,Time Zone End Time,Time Span,Time Zone,Sub-Element</tns:displaynames>
				<tns:names>contextRoot,startTime,endTime,subElmtGrpID,timePeriod,tzStartTime,tzEndTime,timeSpan,timeZone,subElmtID</tns:names>
				
				<tns:icons>
								<tns:contextRoot>
									<tns:hidden>false</tns:hidden>
									<tns:store>infraContextRootStore</tns:store>
									<tns:valueColumn>contextrootvalue</tns:valueColumn>
								</tns:contextRoot>
								<tns:endTime>
									<tns:hidden>false</tns:hidden>
									<tns:store>infraHiddenDBTimeFilterStore</tns:store>
									<tns:valueColumn>endtime</tns:valueColumn>
								</tns:endTime>
								<tns:startTime>
									<tns:hidden>false</tns:hidden>
									<tns:store>infraHiddenDBTimeFilterStore</tns:store>
									<tns:valueColumn>starttime</tns:valueColumn>
								</tns:startTime>
								<tns:subElmtGrpID>
									<tns:store>infraSubElmtGrpIDStore</tns:store>
									<tns:hidden>false</tns:hidden>
									<tns:valueColumn>idx_ind</tns:valueColumn>
									<tns:displayValueColumn>str_name</tns:displayValueColumn>
									<tns:defaultValue>50503</tns:defaultValue>
								</tns:subElmtGrpID>
								<tns:subElmtID>
									<tns:store>infraSubElmtIDStore</tns:store>
									<tns:valueColumn>idx_ind</tns:valueColumn>
									<tns:customStore>infraSubElmtIDStore</tns:customStore>
									<tns:displayValueColumn>str_name</tns:displayValueColumn>
									<tns:defaultValue>0</tns:defaultValue>
									<!--  tns:dependencies>contextRoot,subElmtGrpID,startTime,endTime</tns:dependencies>
									<tns:storeAndValues>
										<tns:contextRoot>*</tns:contextRoot>
										<tns:subElmtGrpID>*</tns:subElmtGrpID>
										<tns:startTime>*</tns:startTime>
										<tns:endTime>*</tns:endTime>
										<tns:subElmtGrpID>50503</tns:subElmtGrpID>
										<tns:values>0</tns:values>
										<tns:displayValues>Null</tns:displayValues>
										<tns:defaultValue>0</tns:defaultValue>
									</tns:storeAndValues>
									<tns:storeAndValues>
										<tns:contextRoot>*</tns:contextRoot>
										<tns:subElmtGrpID>*</tns:subElmtGrpID>
										<tns:startTime>*</tns:startTime>
										<tns:endTime>*</tns:endTime>
										<tns:store>infraSubElmtIDStore</tns:store>
										<tns:valueColumn>resourceNumber</tns:valueColumn>
										<tns:customStore>infraSubElmtIDStore</tns:customStore>
										<tns:displayValueColumn>resourceName</tns:displayValueColumn>
										<tns:defaultValue>0</tns:defaultValue>
									</tns:storeAndValues -->
									<tns:hidden>false</tns:hidden>
								</tns:subElmtID>
								<tns:timePeriod>
									<tns:defaultValue>Last Day</tns:defaultValue>
									<tns:displayValues>Today,Last Day,Last Week,Last Month,Last Year,Custom</tns:displayValues>
									<tns:showTime>no</tns:showTime>
									<tns:values>Today,Last Day,Last Week,Last Month,Last Year,Custom</tns:values>
									<tns:hidden>false</tns:hidden>
								</tns:timePeriod>
								<tns:timeSpan>
									<tns:dependencies>timePeriod</tns:dependencies>
									<tns:hidden>false</tns:hidden>
									<tns:storeAndValues>
											<tns:displayValues>Today</tns:displayValues>
											<tns:timePeriod>Today</tns:timePeriod>
											<tns:values>day</tns:values>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:displayValues>Last Day</tns:displayValues>
											<tns:timePeriod>Last Day</tns:timePeriod>
											<tns:values>day</tns:values>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:displayValues>Last Week</tns:displayValues>
											<tns:timePeriod>Last Week</tns:timePeriod>
											<tns:values>week</tns:values>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:displayValues>Last Month</tns:displayValues>
											<tns:timePeriod>Last Month</tns:timePeriod>
											<tns:values>month</tns:values>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:displayValues>Last Year</tns:displayValues>
											<tns:timePeriod>Last Year</tns:timePeriod>
											<tns:values>year</tns:values>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:displayValues>Custom</tns:displayValues>
											<tns:timePeriod>Custom</tns:timePeriod>
											<tns:values>custom</tns:values>
									</tns:storeAndValues>
								</tns:timeSpan>
								<tns:timeZone>
									<tns:dependencies>contextRoot,subElmtGrpID,timePeriod,tzStartTime,tzEndTime,timeSpan</tns:dependencies>
									<tns:hidden>false</tns:hidden>
									<tns:storeAndValues>
											<tns:subElmtGrpID>*</tns:subElmtGrpID>
											<tns:contextRoot>*</tns:contextRoot>
											<tns:customStore>infraTzStore</tns:customStore>
											<tns:timePeriod>Custom</tns:timePeriod>
											<tns:timeSpan>*</tns:timeSpan>
											<tns:tzEndTime>*</tns:tzEndTime>
											<tns:tzStartTime>*</tns:tzStartTime>
											<tns:valueColumn>aggoffset</tns:valueColumn>
									</tns:storeAndValues>
									<tns:storeAndValues>
											<tns:subElmtGrpID>*</tns:subElmtGrpID>
											<tns:contextRoot>*</tns:contextRoot>
											<tns:store>infraTzStore</tns:store>
											<tns:timePeriod>*</tns:timePeriod>
											<tns:timeSpan>*</tns:timeSpan>
											<tns:tzEndTime>*</tns:tzEndTime>
											<tns:tzStartTime>*</tns:tzStartTime>
											<tns:valueColumn>aggoffset</tns:valueColumn>
									</tns:storeAndValues>
								</tns:timeZone>
								<tns:tzEndTime>
									<tns:dependencies>timePeriod</tns:dependencies>
									<tns:hidden>false</tns:hidden>
									<tns:storeAndValues>
											<tns:customStore>infraTzStartTimeStore</tns:customStore>
											<tns:timePeriod>Custom</tns:timePeriod>
											<tns:valueColumn>value1</tns:valueColumn>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:store>infraTzStartTimeStore</tns:store>
											<tns:timePeriod>*</tns:timePeriod>
											<tns:valueColumn>value1</tns:valueColumn>
									</tns:storeAndValues>
								</tns:tzEndTime>
								<tns:tzStartTime>
									<tns:dependencies>timePeriod</tns:dependencies>
									<tns:hidden>false</tns:hidden>
									<tns:storeAndValues>
											<tns:customStore>infraTzStartTimeStore</tns:customStore>
											<tns:timePeriod>Custom</tns:timePeriod>
											<tns:valueColumn>value</tns:valueColumn>
									</tns:storeAndValues>
									<tns:storeAndValues>		
											<tns:store>infraTzStartTimeStore</tns:store>
											<tns:timePeriod>*</tns:timePeriod>
											<tns:valueColumn>value</tns:valueColumn>
									</tns:storeAndValues>
								</tns:tzStartTime>
				</tns:icons>
				
				<!-- tns:toolbarPageId>filterBar</tns:toolbarPageId -->
				<tns:dashboardId>
					<xsl:value-of select="./dvr:id" />
				</tns:dashboardId>
			</tns:filterbar>
			<xsl:apply-templates />
		</tns:Dashboard>
	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='StackBarHorizWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>pie</tns:type><!-- TO DO - confirm value -->
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster> <!-- TO DO -->
				<tns:chartFamily>circular</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title> <!-- TO DO -->
			</tns:toolbar>
			<tns:style>height: 200px;</tns:style>
			<!-- <tns:colorRangeId>tnpm7</tns:colorRangeId> -->
			<tns:colorRangeId><xsl:value-of select="./dvr:widget/dvr:colorRangeId" /></tns:colorRangeId>
			<tns:plots>
				<tns:radius>90</tns:radius>
				<tns:markers>true</tns:markers>
				<tns:labels>false</tns:labels>
			</tns:plots>
			<tns:plot1>
			</tns:plot1>
			<tns:series>
				<tns:name>Resource Count</tns:name>
				<tns:store>
					<xsl:value-of select="./dvr:storeName" />
				</tns:store>
				<tns:value>item.resourcecount</tns:value><!-- TO DO -->
				<tns:label>item.rangegroup</tns:label><!-- TO DO -->
				<tns:labelValue>Range Group</tns:labelValue>
			</tns:series>
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- TO DO JSON internal ID for widget? -->
				<tns:drilldowntype>navigation</tns:drilldowntype> <!-- TO DO DV will need drill down to another dashboard -->
				<tns:type>master</tns:type> <!-- TO DO is it manadatory? If so what all other values -->
				<tns:parameter>resourceCount</tns:parameter>  <!-- For drill down to other report, need to pass Metric Range as parameter 
					to next report -->
				<!-- </tns:chartId> -->
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:drilldown/dvr:leaf/dvr:reporter" /></tns:targetDashboard>
				<tns:tooltipText>Metric </tns:tooltipText>
				
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 10px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>
	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='RankVariationChartWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Bar</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:chartFamily>graphical</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
				<tns:navigation>true</tns:navigation>
				<tns:showall>true</tns:showall>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:colorRangeId>tnpm7</tns:colorRangeId>
			<tns:plots>
				<tns:name>default</tns:name>
				<tns:type>StackedBars</tns:type>
				<tns:gap>3</tns:gap>
				<tns:minBarSize>3</tns:minBarSize>
				<tns:maxBarSize>20</tns:maxBarSize>
			</tns:plots>
			<tns:axes>
				<tns:name>x</tns:name>
				<tns:title>Name</tns:title>
				<tns:titleOrientation>away</tns:titleOrientation>
				<tns:includeZero>true</tns:includeZero>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
			</tns:axes>
			<tns:axes>
				<tns:name>y</tns:name>
				<tns:title>Rank Variation</tns:title>
				<tns:vertical>true</tns:vertical>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
				<tns:minorLabels>false</tns:minorLabels>
				<tns:labelSeries>rankVariation</tns:labelSeries>
			</tns:axes>
			<tns:series>
				<tns:name>rankVariation</tns:name>
				<tns:plot>default</tns:plot>
				<tns:store>
					<xsl:value-of select="./dvr:storeName" />
				</tns:store>
				<tns:value>rankVariation</tns:value>
				<tns:label>resourceName</tns:label>
			</tns:series>
			<tns:chartidentity>
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name>
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>resourceName</tns:parameter>
				<tns:tooltipText>Metric </tns:tooltipText>
				<tns:extraParams>{'titleVal' : ''}</tns:extraParams>
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 0 5px 0 5px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='BarHorizWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Bar</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:chartFamily>graphical</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
				<tns:navigation>true</tns:navigation>
				<tns:showall>true</tns:showall>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:colorRangeId>tnpm7</tns:colorRangeId>
			<tns:plots>
				<tns:name>default</tns:name>
				<tns:type>ClusteredBars</tns:type>
				<tns:gap>3</tns:gap>
				<tns:minBarSize>3</tns:minBarSize>
				<tns:maxBarSize>20</tns:maxBarSize>
			</tns:plots>
			<tns:axes>
				<tns:name>x</tns:name>
				<tns:title></tns:title>
				<tns:titleOrientation>away</tns:titleOrientation>
				<tns:includeZero>true</tns:includeZero>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
			</tns:axes>
			<tns:axes>
				<tns:name>y</tns:name>
				<tns:title></tns:title>
				<tns:vertical>true</tns:vertical>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
				<tns:minorLabels>false</tns:minorLabels>
				<xsl:for-each select="./dvr:mediator/dvr:stats/dvr:stat">
					<tns:labelSeries>
						<xsl:value-of select="." />
					</tns:labelSeries>
				</xsl:for-each>
				
				
			</tns:axes>
			<xsl:variable name="storeName" select="./dvr:storeName" />
			<xsl:for-each select="./dvr:mediator/dvr:stats/dvr:stat">				
				<tns:series>
				<tns:name><xsl:value-of select="." /></tns:name>
				<tns:plot>default</tns:plot>
				<tns:store><xsl:value-of select="$storeName"/></tns:store>
				<tns:value>item.<xsl:value-of select="." />	</tns:value>
				<tns:label>item.resourceName</tns:label>
				</tns:series>				
			</xsl:for-each>		
			
			<tns:chartidentity>
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name>
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>resourceName</tns:parameter>
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:drilldown/dvr:reporter" /></tns:targetDashboard>
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 0 5px 0 5px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>
	<!-- End of Top N Chart -->


	<xsl:template
		match="dvr:ReportWidget[dvr:mediator[@xsi:type='TimeSeriesMediator']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Area</tns:type><!-- TO DO - confirm value -->
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster> <!-- TO DO -->
				<tns:chartFamily>graphical_time</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title> <!-- TO DO -->
			</tns:toolbar>
			<tns:style>height: 200px;</tns:style>
			<tns:colorRangeId>tnpm7</tns:colorRangeId>
			<tns:dynamicseries>false</tns:dynamicseries>
			<tns:plots>
				<tns:name>default</tns:name>
				<tns:type>Areas</tns:type>
				<tns:gap>3</tns:gap>
				<tns:minBarSize>3</tns:minBarSize>
				<tns:maxBarSize>20</tns:maxBarSize>
				<tns:markers>true</tns:markers>
				<tns:labels>false</tns:labels>
				
			</tns:plots>
			<tns:axes>
				<tns:name>x</tns:name>
				<tns:title>Date</tns:title>
				<tns:titleOrientation>away</tns:titleOrientation>
				<tns:includeZero>true</tns:includeZero>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
				<tns:rotation>-30</tns:rotation>				
				<tns:labelSeries><xsl:value-of select="./dvr:title/dvr:text" /></tns:labelSeries>				
			</tns:axes>
			<tns:axes>
				<tns:name>y</tns:name>
				<tns:title>			
				</tns:title>
				<tns:vertical>true</tns:vertical>
				<tns:includeZero>true</tns:includeZero>				
				<tns:minorTicks>true</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
				<tns:minorLabels>false</tns:minorLabels>				
				<!--  <tns:labelSeries>
					<xsl:value-of select="./dvr:mediator/dvr:stats" />
				</tns:labelSeries>
				-->
			</tns:axes>
			<!-- 
			<xsl:variable name="storeName" select="./dvr:storeName" />
			<xsl:for-each select="./dvr:mediator/dvr:stats/dvr:stat">				
				<tns:series>
				<tns:name><xsl:value-of select="../../../dvr:title/dvr:text" />:<xsl:value-of select="." /></tns:name>
				<tns:plot>default</tns:plot>
				<tns:store><xsl:value-of select="$storeName"/></tns:store>
				<tns:value>item.value</tns:value>
				<tns:label>(new Date(item.date)).toGMTString()</tns:label>
				</tns:series>				
			</xsl:for-each>		
			-->
		
			<!--  "If dynamicseries = true " <tns:series>
				<tns:name><xsl:value-of select="./dvr:title/dvr:text" /></tns:name>				
				<tns:store>
					<xsl:value-of select="./dvr:storeName"/>
				</tns:store>
				<tns:value>item.value</tns:value>				
				<tns:seriesColumn>item.metricName</tns:seriesColumn>
				<tns:labelColumn>item.date</tns:labelColumn>
			</tns:series>  -->
			
			<tns:series>
				<tns:name><xsl:value-of select="./dvr:title/dvr:text" /></tns:name>				
				<tns:store>
					<xsl:value-of select="./dvr:storeName"/>
				</tns:store>
				<tns:value>item.value</tns:value>				
				<tns:labelvalue>item.metricname</tns:labelvalue>
				<tns:label>item.date</tns:label>
				<tns:labelType>date and time</tns:labelType>
				<tns:labelFormat>d-MM-yyyy hh:mm:ss</tns:labelFormat>
			</tns:series>
			
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name>
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>resourceName</tns:parameter>
				<!-- </tns:chartId> -->
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 10px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>
	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='PieChartWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>pie</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:chartFamily>circular</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 200px;</tns:style>
			<tns:colorRangeId>range3kpi</tns:colorRangeId>
			<tns:plots>
				<tns:radius>90</tns:radius>
				<tns:markers>true</tns:markers>
				<tns:labels>false</tns:labels>
			</tns:plots>
			<tns:series>
				<tns:name>metricName</tns:name>
				<tns:store>
					<xsl:value-of select="./dvr:storeName" />
				</tns:store>
				<tns:value>item.value</tns:value>
				<tns:label>item.metricName</tns:label>
			</tns:series>
			<tns:chartidentity>
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name>
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>value</tns:parameter>
				<tns:tooltipText>Metric </tns:tooltipText>
				<tns:extraParams>{'titleVal' : ''}</tns:extraParams>
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 10px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>
	<xsl:template match="dvr:ReportWidget[dvr:widget[@xsi:type='BaselineChartWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Columns</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:chartFamily>graphical</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
				<tns:navigation>true</tns:navigation>
				<tns:showall>true</tns:showall>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:colorRangeId>tnpm7</tns:colorRangeId>
			<tns:dynamicseries>true</tns:dynamicseries>
			<tns:plots>
				<tns:name>default</tns:name>
				<tns:type>Columns</tns:type>
				<tns:gap>3</tns:gap>
				<tns:minBarSize>3</tns:minBarSize>
				<tns:maxBarSize>20</tns:maxBarSize>
			</tns:plots>
			<tns:plots>
				<tns:name>other</tns:name>
				<tns:type>Lines</tns:type>
				<tns:gap>3</tns:gap>
				<tns:markers>true</tns:markers>
			</tns:plots>
			<tns:axes>
				<tns:name>x</tns:name>
				<tns:title>Date</tns:title>
				<tns:titleOrientation>away</tns:titleOrientation>
				<tns:includeZero>true</tns:includeZero>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
				<tns:minorLabels>false</tns:minorLabels>
				<tns:labelSeries>value</tns:labelSeries>
			</tns:axes>
			<tns:axes>
				<tns:name>y</tns:name>
				<tns:title>Value</tns:title>
				<tns:vertical>true</tns:vertical>
				<tns:includeZero>true</tns:includeZero>
				<tns:minorTicks>false</tns:minorTicks>
				<tns:microTicks>false</tns:microTicks>
			</tns:axes>
			<tns:series>
				<tns:name>value</tns:name>
				<tns:plot>default</tns:plot>
				<tns:store>
					<xsl:value-of select="./dvr:storeName" />
				</tns:store>
				<tns:value>item.value</tns:value>
				<tns:seriesColumn>item.metricName</tns:seriesColumn>
				<tns:labelColumn>item.date</tns:labelColumn>
			</tns:series>
			<tns:series>
				<tns:name>value</tns:name>
				<tns:plot>other</tns:plot>
				<tns:store>
					<xsl:value-of select="./dvr:storeName" />
				</tns:store>
				<tns:value>item.resourceName</tns:value>
				<tns:seriesColumn>item.metricName</tns:seriesColumn>
				<tns:labelColumn>item.date</tns:labelColumn>
			</tns:series>
			<tns:chartidentity>
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name>
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>listener</tns:type>
				<tns:parameter>date</tns:parameter>
				<tns:tooltipText>Metric </tns:tooltipText>
				<tns:extraParams>{'titleVal' : ''}</tns:extraParams>
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 0 5px 0 5px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='GstTableWidget']]">
		<!-- Handle GSTTable nodes here - can refer to mediator and widget child 
			in source -->

		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>groupName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<xsl:variable name="groupLabel" select="dvr:widget/dvr:group/dvr:label" />
			<tns:columns>
				<tns:name>
					<xsl:value-of select="$groupLabel" />
				</tns:name>
				<tns:id>groupname</tns:id>
				<tns:field>groupname</tns:field>
			</tns:columns>

			<xsl:for-each select="dvr:widget/dvr:formula">
				<xsl:variable name="columnLabel" select="dvr:metricFormat/dvr:label/text()" />
				<xsl:for-each
					select="../../dvr:mediator/dvr:formula[dvr:formulaId/text()=current()/dvr:formulaId/text()]/dvr:stats/dvr:stat">
					<xsl:variable name="metricId" select="../../dvr:metric/text()" />
					<xsl:variable name="statName" select="./text()" />
					<xsl:variable name="fieldName" select="concat($metricId,':',./text())" />
					<tns:columns>
						<tns:name>
							<xsl:value-of select="concat($columnLabel,'-',$statName)" />
						</tns:name>
						<tns:id>
							<xsl:value-of select="$fieldName" />
						</tns:id>
						<tns:field>
							<xsl:value-of select="$fieldName" />
						</tns:field>
					</tns:columns>
				</xsl:for-each>
			</xsl:for-each>
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:group/dvr:drilldown/dvr:leaf/dvr:reporter" /></tns:targetDashboard>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<!-- </tns:chartId> -->
				<tns:tooltipText>Metric </tns:tooltipText>
				<tns:extraparameters></tns:extraparameters>
				<tns:extracolumns></tns:extracolumns>
			</tns:chartidentity>
		</tns:widgets>

	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='RstTableWidget']]">

		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>resourceName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<xsl:variable name="resourceLabel" select="./dvr:widget/dvr:resource/dvr:Label" />
			<tns:columns>
				<tns:name>
					<xsl:value-of select="$resourceLabel" />
				</tns:name>
				<tns:id>resourcename</tns:id>
				<tns:field>resourcename</tns:field>
			</tns:columns>
			<xsl:for-each select="./dvr:mediator/dvr:properties/dvr:property">
				<xsl:variable name="propertyId" select="./dvr:idIdx" />
				<xsl:variable name="PropFieldName" select="concat($propertyId,':value')" />
				<tns:columns>
					<tns:name>
						<xsl:value-of select="./dvr:label" />
					</tns:name>
					<tns:id>
						<xsl:value-of select="$PropFieldName" />
					</tns:id>
					<tns:field>
						<xsl:value-of select="$PropFieldName" />
					</tns:field>
				</tns:columns>
			</xsl:for-each>

			<xsl:for-each select="dvr:widget/dvr:formula">
				<xsl:variable name="columnLabel" select="dvr:formula/dvr:label/text()" />
				<xsl:for-each
					select="../../dvr:mediator/dvr:formula[dvr:formulaId/text()=current()/dvr:formulaId/text()]/dvr:stats/dvr:stat">
					<xsl:variable name="metricId" select="../../dvr:metric/text()" />
					<xsl:variable name="statName" select="./text()" />
					<xsl:variable name="fieldName" select="concat($metricId,':',./text())" />
					<tns:columns>
						<tns:name>
							<xsl:value-of select="concat($columnLabel,'-',$statName)" />
						</tns:name>
						<tns:id>
							<xsl:value-of select="$fieldName" />
						</tns:id>
						<tns:field>
							<xsl:value-of select="$fieldName" />
						</tns:field>
					</tns:columns>
				</xsl:for-each>
			</xsl:for-each>
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<!-- </tns:chartId> -->
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:drilldown/dvr:reporter" /></tns:targetDashboard>
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
			</tns:chartidentity>
		</tns:widgets>

	</xsl:template>


	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='RankVariationTableWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>resourceName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<tns:columns>
				<tns:name>
					<xsl:value-of select="./dvr:widget/dvr:resourceColumnLabel" />
				</tns:name>
				<tns:id>resourcename</tns:id>
				<tns:field>resourcename</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Rank Variation</tns:name>
				<tns:id>rankvariation</tns:id>
				<tns:field>rankvariation</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Previous Rank</tns:name>
				<tns:id>rankprevioustp</tns:id>
				<tns:field>rankprevioustp</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Current Rank</tns:name>
				<tns:id>rankcurrenttp</tns:id>
				<tns:field>rankcurrenttp</tns:field>
			</tns:columns>

			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:drilldown/dvr:reporter" /></tns:targetDashboard>
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<!-- </tns:chartId> -->
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
			</tns:chartidentity>
		</tns:widgets>

	</xsl:template>

	<xsl:template match="dvr:ReportWidget[dvr:widget[@xsi:type='TopNTable']]">
		<!-- Handle GSTTable nodes here - can refer to mediator and widget child 
			in source -->

		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>resourceName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<tns:columns>
				<tns:name>Name</tns:name>
				<tns:id>resourcename</tns:id>
				<tns:field>resourcename</tns:field>
			</tns:columns>

			<xsl:for-each select="./dvr:mediator/dvr:stats/dvr:stat">
				<tns:columns>
					<tns:name>
						<xsl:value-of select="." />
					</tns:name>
					<tns:id>
						<xsl:value-of select="." />
					</tns:id>
					<tns:field>
						<xsl:value-of select="." />
					</tns:field>
				</tns:columns>
			</xsl:for-each>


			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:group/dvr:drilldown/dvr:reporter" /></tns:targetDashboard>
			
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<!-- </tns:chartId> -->
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
			</tns:chartidentity>
		</tns:widgets>

	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='MatrixTableWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>resourceName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<!-- TO DO Proper column field mapping -->
			<tns:columns>
				<tns:name></tns:name>
				<tns:id>rowlabel</tns:id>
				<tns:field>rowlabel</tns:field>
			</tns:columns>

			<tns:columns>
				<tns:name>
					<xsl:value-of
						select="./dvr:mediator/dvr:sourceDestination/dvr:sourcePropertyLabel" />
				</tns:name>
				<tns:id>
					<xsl:value-of
						select="./dvr:mediator/dvr:sourceDestination/dvr:sourcePropertyLabel" />
				</tns:id>
				<tns:field>
					<xsl:value-of
						select="./dvr:mediator/dvr:sourceDestination/dvr:sourcePropertyLabel" />
				</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>
					<xsl:value-of
						select="./dvr:mediator/dvr:sourceDestination/dvr:destinationPropertyLabel" />
				</tns:name>
				<tns:id>
					<xsl:value-of
						select="./dvr:mediator/dvr:sourceDestination/dvr:destinationPropertyLabel" />
				</tns:id>
				<tns:field>
					<xsl:value-of
						select="./dvr:mediator/dvr:sourceDestination/dvr:destinationPropertyLabel" />
				</tns:field>
			</tns:columns>


			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
				<!-- </tns:chartId> -->
			</tns:chartidentity>
		</tns:widgets>
	</xsl:template>



	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='ThViolationTableWidget']]">
		<!-- Handle GSTTable nodes here - can refer to mediator and widget child 
			in source -->

		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>resourceName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<tns:columns>
				<tns:name>Name</tns:name>
				<tns:id>resourcename</tns:id>
				<tns:field>resourcename</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Thr Level</tns:name>
				<tns:id>thrlevel</tns:id>
				<tns:field>thrlevel</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>SLA Time Limit(hrs:mins)</tns:name>
				<tns:id>thrduration</tns:id>
				<tns:field>thrduration</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Pecent of value over Threshold</tns:name>
				<tns:id>maxoverpercentage</tns:id>
				<tns:field>maxoverpercentage * 100</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Threshold Vialotions</tns:name>
				<tns:id>thrcount</tns:id>
				<tns:field>thrcount</tns:field>
			</tns:columns>
			<xsl:call-template name="commaSeparatorValues">
				<xsl:with-param name="strValue" select="./dvr:mediator/dvr:stats" />
			</xsl:call-template>
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<tns:tooltipText>Metric</tns:tooltipText>
				<tns:extraparameters>groupname</tns:extraparameters>
				<tns:extracolumns>groupname</tns:extracolumns>
				<tns:extraParams>{'titleVal' : ''}</tns:extraParams>
				<!-- </tns:chartId> -->
			</tns:chartidentity>
		</tns:widgets>

	</xsl:template>

	<!-- Added changes  by Madhukar on 10/12/2015 -->
	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='DistributionBarChartWidget']]">
		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>pie</tns:type><!-- TO DO - confirm value -->
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster> <!-- TO DO -->
				<tns:chartFamily>circular</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title> <!-- TO DO -->
			</tns:toolbar>
			<tns:style>height: 200px;</tns:style>
			<tns:colorRangeId>tnpm7</tns:colorRangeId>
			<tns:plots>
				<tns:radius>90</tns:radius>
				<tns:markers>true</tns:markers>
				<tns:labels>false</tns:labels>
			</tns:plots>
			<tns:series>
				<tns:name>metricName</tns:name>
				<tns:sortorder>desc</tns:sortorder>
				<tns:sortparam>int_order</tns:sortparam>
				<tns:store>
					<xsl:value-of select="./dvr:storeName" />
				</tns:store>
				<tns:value>item.resourceCount</tns:value><!-- TO DO -->
				<tns:label>item.metricName</tns:label><!-- TO DO -->
			</tns:series>
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- TO DO JSON internal ID for widget? -->
				<tns:drilldowntype>navigation</tns:drilldowntype> <!-- TO DO DV will need drill down to another dashboard -->
				<tns:type>listener</tns:type> <!-- TO DO is it manadatory? If so what all other values -->
				<tns:parameter>resourceCount</tns:parameter>  <!-- For drill down to other report, need to pass Metric Range as parameter 
					to next report -->
				<!-- </tns:chartId> -->
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:drilldown/dvr:leaf/dvr:reporter" /></tns:targetDashboard>
				<tns:tooltipText>Metric </tns:tooltipText>
				<tns:extraParams>{'titleVal' : ''}</tns:extraParams>
			</tns:chartidentity>
			<tns:legend>
				<tns:type>interactive</tns:type>
				<tns:style>margin: 10px;</tns:style>
			</tns:legend>
			<tns:tooltip>true</tns:tooltip>
		</tns:widgets>
	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='RptTableWidget']]">
		<!-- Handle GSTTable nodes here - can refer to mediator and widget child 
			in source -->

		<tns:widgets>
			<tns:id>
				<xsl:value-of select="./dvr:widget/dvr:widgetId" />
			</tns:id>
			<tns:title></tns:title>
			<tns:type>Grid</tns:type>
			<tns:rowspan><xsl:value-of select="./dvr:widget/dvr:rowspan" /></tns:rowspan>
			<tns:colspan><xsl:value-of select="./dvr:widget/dvr:colspan" /></tns:colspan>
			<tns:xpos><xsl:value-of select="./dvr:widget/dvr:xpos" /></tns:xpos> 
			<tns:ypos><xsl:value-of select="./dvr:widget/dvr:ypos" /></tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>
					<xsl:value-of select="./dvr:title/dvr:text" />
				</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
				<tns:title></tns:title>
				<tns:primaryKey>groupName</tns:primaryKey>
				<tns:dataStore>
					<xsl:value-of select="./dvr:storeName" />
				</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<tns:columns>
				<tns:name>Name</tns:name>
				<tns:id>resourcename</tns:id>
				<tns:field>resourcename</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Upgrade Level</tns:name>
				<tns:id>plevel</tns:id>
				<tns:field>plevel</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name><xsl:value-of select="./dvr:widget/dvr:metricFormat/dvr:label" /> </tns:name>
				<!-- - <xsl:value-of select="./dvr:mediator/dvr:stats" /> -->
				<tns:id>mId94601-value</tns:id>
				<tns:field>mId94601-value</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Upgrade Rule</tns:name>
				<tns:id>prule</tns:id>
				<tns:field>prule</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Upgrade Condition</tns:name>
				<tns:id>upgradecondition</tns:id>
				<tns:field>upgradecondition</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Predicted Upgrade</tns:name>
				<tns:id>preupgradedate</tns:id>
				<tns:field>preupgradedate</tns:field>
			</tns:columns>
			<tns:columns>
				<tns:name>Trend</tns:name>
				<tns:id>trend</tns:id>
				<tns:field>trend</tns:field>
			</tns:columns>
			<tns:chartidentity>
				<!-- <tns:chartId> -->
				<tns:name>
					<xsl:value-of select="./dvr:id" /><xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:name> <!-- To make all widget.chartIdentity have widgetId as widget.chartIdentity.name-->
				<!-- tns:class>grid</tns:class-->
				<tns:drilldowntype>navigation</tns:drilldowntype>
				<tns:type>master</tns:type>
				<tns:parameter>drilldownParam</tns:parameter>
				<tns:targetDashboard><xsl:value-of select="./dvr:widget/dvr:drilldown/dvr:reporter" /></tns:targetDashboard>
				<tns:master>
					<xsl:value-of select="./dvr:widget/dvr:widgetId" />
				</tns:master>
				<tns:tooltipText>Metric </tns:tooltipText><tns:extraparameters></tns:extraparameters><tns:extracolumns></tns:extracolumns>
				<!-- </tns:chartId> -->
			</tns:chartidentity>
		</tns:widgets>

	</xsl:template>
	
	<!-- end of changes  by Madhukar on 10/12/2015 -->
	
	<xsl:template name="commaSeparatorValues">
		<xsl:param name="strValue" />
		<xsl:param name="delimitor" select="','" />
		<xsl:choose>
			<xsl:when test="contains($strValue,$delimitor)">
				<tns:columns>
					<tns:name>
						<xsl:value-of select="substring-before($strValue,$delimitor)" />
					</tns:name>
					<tns:id>
						<xsl:value-of select="substring-before($strValue,$delimitor)" />
					</tns:id>
					<tns:field>
						<xsl:value-of select="substring-before($strValue,$delimitor)" />
					</tns:field>
				</tns:columns>
				<xsl:call-template name="commaSeparatorValues">
					<xsl:with-param name="strValue"
						select="substring-after($strValue,$delimitor)" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<!-- <tns:columns> <tns:name><xsl:value-of select="$strValue"/></tns:name> 
					<tns:id><xsl:value-of select="$strValue"/></tns:id> <tns:field><xsl:value-of 
					select="$strValue"/></tns:field> </tns:columns> -->
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>



	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>