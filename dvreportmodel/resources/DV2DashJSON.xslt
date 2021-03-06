<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dvr="http://www.ibm.com/csi/npm/dvreportermodel"
	xmlns:tns="http://www.ibm.com/csi/npm/dashjson" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:template match="dvr:reporter">
		<tns:Dashboard xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://www.ibm.com/csi/npm/dashjson file://D:\Study\PRD\Convertor\resources\DashJSON.xsd">
			<tns:id>Dashboard1</tns:id>
			<tns:title>Dashboard1</tns:title>							
			<tns:description>Dashboard1 Details</tns:description>
			<tns:blockHeight><xsl:value-of select="./dvr:blockHeight" /></tns:blockHeight>
			<tns:bootstrapCols>60</tns:bootstrapCols>
			<tns:totalrows>2</tns:totalrows>
			<tns:totalcols>2</tns:totalcols>
			<xsl:for-each select="dvr:ReportWidget/dvr:dataStoreUrl">
				<tns:stores>
					<tns:name>	<xsl:value-of select="."></xsl:value-of></tns:name> <!-- TO DO should come from widgets -->
					<!-- new changes - 16th Oct -->
					<tns:query>SELECT * FROM (SELECT * FROM (SELECT {specificColumns},{aggregatedColumns} FROM {tableName} {whereCondition} {groupBy} ) {orderBy} {limit} ) {outerOrderBy}</tns:query>
					<tns:defaultValues>
						<tns:specificColumns>fromIpAddress(exporter_ip) AS ip_address, ingress_interface AS interface</tns:specificColumns>
						<tns:aggregatedColumns>sum(nvl(sum_in_octet_delta_count, 0)) as in_octets, sum(nvl(sum_out_octet_delta_count, 0)) as out_octets</tns:aggregatedColumns>
						<tns:tableName>TOPN_AS_SYSTEMS_INTERFACE_1MIN</tns:tableName>
						<tns:whereCondition>where condition</tns:whereCondition>
						<tns:groupBy>GROUP BY ip_address, interface</tns:groupBy>
						<tns:orderBy>ORDER BY in_octets DESC</tns:orderBy>
						<tns:limit>LIMIT {topN}</tns:limit>
						<tns:outerOrderBy>ORDER BY in_octets ASC</tns:outerOrderBy>
						<tns:timeFrom>500000</tns:timeFrom>
						<tns:timeTo>10</tns:timeTo>
						<tns:topN>5</tns:topN>
						<tns:Bytes>1</tns:Bytes>
						<tns:KB>1024</tns:KB>
						<tns:MB>1048576</tns:MB>
						<tns:GB>1073741824</tns:GB>
						<tns:K>1</tns:K>
						<tns:M>1000</tns:M>
						<tns:G>1000000</tns:G>
					</tns:defaultValues>
					<!-- end of new changes - 16th Oct -->
				</tns:stores>
			</xsl:for-each>
			<tns:filterbar>
				<tns:name>level,filterBy,timePeriod,topN,direction,reportType,unitType,measurement</tns:name><!-- new changes - 16th Oct -->
				<tns:drilldown>true</tns:drilldown>
				<tns:navigation>Y</tns:navigation><!-- new changes - 16th Oct -->
				<tns:toolbarPageId>filterBar</tns:toolbarPageId><!-- new changes - 16th Oct -->
				<!-- new changes - 16th Oct -->
				<tns:icons>
					<tns:unitType>
						<tns:dependencies>reportType</tns:dependencies>
						<tns:storeAndValues>
							<tns:reportType>Bytes</tns:reportType>
							<tns:values>Bytes,KB,MB,GB</tns:values>
						</tns:storeAndValues>
						<tns:storeAndValues>
							<tns:reportType>Packets</tns:reportType>
							<tns:values>K,M,G</tns:values>
						</tns:storeAndValues>
					</tns:unitType>
					<tns:filterBy>
						<tns:dependencies>level</tns:dependencies>
						<tns:storeAndValues>
							<tns:level>Interface</tns:level>
							<tns:store>levelGroupStore</tns:store>
						</tns:storeAndValues>
						<tns:storeAndValues>
							<tns:level>Group</tns:level>
							<tns:store>levelGroupStore</tns:store>
						</tns:storeAndValues>
					</tns:filterBy>
				</tns:icons>
				<!-- end of new changes - 16th Oct -->
				<tns:wherecondition></tns:wherecondition>
				<tns:vendor>n</tns:vendor>
				<tns:region>n</tns:region>
				<tns:granularity>n</tns:granularity>
			</tns:filterbar>
			<xsl:apply-templates />
		</tns:Dashboard>
	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='StackBarHorizWidget']]">
		<!-- Handle StackBarHoriz nodes here - can refer to mediator and widget 
			child in source -->
		 <tns:widgets>
			<tns:id>widget1</tns:id>
			<tns:type>Bar</tns:type><!-- TO DO - confirm value -->
			<tns:rowspan>1</tns:rowspan>
			<tns:colspan>1</tns:colspan>
			<tns:xpos>0</tns:xpos> <!-- TO DO -->
			<tns:ypos>0</tns:ypos><!-- TO DO -->
			<tns:toolbar>
				<tns:isMaster>false</tns:isMaster> <!-- TO DO -->
				<tns:chartFamily>graphical</tns:chartFamily>
				<tns:title>
					<xsl:value-of select="dvr:widget/title/text" />
				</tns:title> <!-- TO DO -->
				<tns:seriesorder>Inbound,Outbound</tns:seriesorder><!-- new changes - 16th Oct -->
				<tns:allSeriesFilter>Both</tns:allSeriesFilter><!-- new changes - 16th Oct -->
				<tns:filterKey>direction</tns:filterKey><!-- new changes - 16th Oct -->
				<tns:subFilters>reportType</tns:subFilters><!-- new changes - 16th Oct -->
				<tns:seriesAttributes>name,value</tns:seriesAttributes><!-- new changes - 16th Oct -->
				<!-- new changes - 16th Oct -->
				<tns:seriesList>
					<tns:inbound>
						<tns:name>Inbound Octets</tns:name>
						<tns:reportType>Bytes</tns:reportType>
						<tns:value>item.in_octets</tns:value>
					</tns:inbound>
					<tns:inbound>
						<tns:name>Inbound Packets</tns:name>
						<tns:reportType>Packets</tns:reportType>
						<tns:value>item.in_packets</tns:value>
					</tns:inbound>
					<tns:outbound>
						<tns:name>Outbound Octets</tns:name>
						<tns:reportType>Bytes</tns:reportType>
						<tns:value>item.in_octets</tns:value>
					</tns:outbound>
					<tns:outbound>
						<tns:name>Outbound Packets</tns:name>
						<tns:reportType>Packets</tns:reportType>
						<tns:value>item.in_packets</tns:value>
					</tns:outbound>
				</tns:seriesList>
				<!-- end of new changes - 16th Oct -->
			</tns:toolbar>
			<tns:style>height: 160px;width:98.5%;</tns:style>
			<tns:colorRangeId>tnpm7</tns:colorRangeId>
				<tns:plots>				
					<!--<tns:plot> -->
						<tns:name>default</tns:name> <!-- TO DO -->
						<tns:type>StackedBars</tns:type> <!-- TO DO -->
						<tns:gap>3</tns:gap>
						<tns:minBarSize>3</tns:minBarSize>
						<tns:maxBarSize>20</tns:maxBarSize>
						<tns:markers>true</tns:markers><!-- new changes - 16th Oct -->
					<!-- </tns:plot> -->
				</tns:plots>			
				<tns:axes>
					<!-- <tns:axis> -->
						<tns:name>x</tns:name>
						<tns:title>metricName</tns:title> <!-- TO DO a column from data returned - hard code to "Resource Count"? -->
						<tns:titleOrientation>away</tns:titleOrientation>
						<tns:maxLabelCharCount>15</tns:maxLabelCharCount>
						<tns:includeZero>true</tns:includeZero>
						<tns:minorTicks>false</tns:minorTicks>
						<tns:microTicks>false</tns:microTicks>						
					<!-- </tns:axis>
					<tns:axis> -->
					</tns:axes>
					<tns:axes>
						<tns:name>y</tns:name>
						<tns:title>resourceCount</tns:title> <!-- TO DO a column name or hard code to "Metric Name" -->
						<tns:vertical>true</tns:vertical>
						<tns:includeZero>true</tns:includeZero>
						<tns:minorTicks>false</tns:minorTicks>
						<tns:microTicks>false</tns:microTicks>
						<tns:minorLabels>false</tns:minorLabels>
						<tns:fixUpper>major</tns:fixUpper>
						<tns:rotation>-45</tns:rotation><!-- new changes - 16th Oct -->
						<tns:labelSeries>item.metricName</tns:labelSeries> <!-- Can take from some data column? -->
					<!-- </tns:axis> -->
				</tns:axes>
				<tns:series>				
					<!--<tns:plotSeries> -->
						<tns:name>metricName</tns:name> 
						<tns:plot>default</tns:plot>
						<tns:store>widget1Store</tns:store>
						<tns:value>item.resourceCount</tns:value><!-- TO DO -->
						<tns:label>item.metricName</tns:label><!-- TO DO -->
						<tns:direction>Direction Name</tns:direction><!-- new changes - 16th Oct -->
					<!-- </tns:plotSeries> -->
				</tns:series>
				<tns:series>				
					<!--<tns:plotSeries> -->
						<tns:name>metricName</tns:name> 
						<tns:plot>default</tns:plot>
						<tns:store>widget1Store</tns:store>
						<tns:value>item.resourceCount</tns:value><!-- TO DO -->
						<tns:label>item.metricName</tns:label><!-- TO DO -->
						<tns:direction>Direction Name</tns:direction><!-- new changes - 16th Oct -->
					<!-- </tns:plotSeries> -->
				</tns:series>
			<tns:chartIdentity>
				<!-- <tns:chartId> -->
					<tns:name>widget1</tns:name> <!-- TO DO JSON internal ID for widget? -->
					<tns:drilldowntype>masterlistener</tns:drilldowntype> <!-- TO DO DV will need drill down to another dashboard -->
					<tns:type>master</tns:type> <!-- TO DO is it manadatory? If so what all other values -->
					<tns:parameter>item.resourceCount</tns:parameter>  <!-- For drill down to other report, need to pass Metric Range as parameter to next report -->
					<tns:master>Master Name</tns:master><!-- new changes - 16th Oct -->
					<tns:targetDashboard>Target Dashboard Name</tns:targetDashboard><!-- new changes - 16th Oct -->
				<!-- </tns:chartId> -->
			</tns:chartIdentity>
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
			<tns:id>widget2</tns:id>
			<tns:type>Grid</tns:type>
			<tns:rowspan>1</tns:rowspan>
			<tns:colspan>2</tns:colspan>
			<tns:xpos>0</tns:xpos>
			<tns:ypos>1</tns:ypos>
			<tns:toolbar>
				<tns:isMaster>true</tns:isMaster>
				<tns:title>Widget 3 Title</tns:title>
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
					<tns:title></tns:title>
					<tns:primaryKey>region</tns:primaryKey>
					<tns:dataStore>widget3Store</tns:dataStore>
				<!-- </tns:property> -->
			</tns:properties>
			<xsl:for-each select="dvr:widget/dvr:formula">
			<xsl:variable name="columnLabel" select="dvr:metricFormat/dvr:label/text()"/>
				<xsl:for-each select="../../dvr:mediator/dvr:formula[dvr:formulaId/text()=current()/dvr:formulaId/text()]/dvr:stats/dvr:stat">
					<xsl:variable name="metricId" select="../../dvr:metric/text()"/>
					<xsl:variable name="statName" select="./text()"/>
					<xsl:variable name="fieldName" select="concat('mid',$metricId,'-',./text())"/>
					<tns:columns>
						<tns:name><xsl:value-of select="concat($columnLabel,'-',$statName)"/></tns:name>
						<tns:id><xsl:value-of select="$fieldName"/></tns:id>
						<tns:field><xsl:value-of select="$fieldName"/></tns:field>
					</tns:columns>
				</xsl:for-each>
			</xsl:for-each>
			<tns:chartIdentity>
				<!-- <tns:chartId> -->
					<tns:class>grid</tns:class>
					<tns:drilldowntype>masterlistener</tns:drilldowntype>
					<tns:type>listener</tns:type>
					<tns:parameter>drilldownParam</tns:parameter>
					<tns:master>widget1</tns:master>
				<!-- </tns:chartId> -->
			</tns:chartIdentity>
		</tns:widgets>

	</xsl:template>

	<xsl:template
		match="dvr:ReportWidget[dvr:widget[@xsi:type='RstTableWidget']]">
		<!-- Handle RSTTable nodes here - can refer to mediator and widget child 
			in source -->
		<tns:widgets> 
			<tns:id>widget2</tns:id> <!-- TO DO -->
			<tns:type>Grid</tns:type> <!--  TO DO -->
			<tns:rowspan>1</tns:rowspan> <!-- TO DO -->
			<tns:colspan>2</tns:colspan> <!--  TO DO -->
			<tns:xpos>0</tns:xpos> <!--  TO DO -->
			<tns:ypos>1</tns:ypos> <!-- TO DO -->
			<tns:toolbar>
				<tns:isMaster>false</tns:isMaster>
				<tns:title><xsl:value-of select="title/text"> </xsl:value-of></tns:title> 
			</tns:toolbar>
			<tns:style>height: 300px;</tns:style>
			<tns:properties>
				<!-- <tns:property> -->
					<tns:title></tns:title> 
					<tns:primaryKey>region</tns:primaryKey>
					<tns:dataStore>widget3Store</tns:dataStore> 
				<!-- </tns:property> -->
			</tns:properties>
			<tns:columns>
			<!-- TO DO Via loop using metrics / formula in mediator for table -->
				<tns:column>
					<tns:name>Column_1_Name</tns:name>
					<tns:id>Column_1_ID</tns:id>
					<tns:field>Column_1_Mapping</tns:field>
				</tns:column>
				<tns:column>
					<tns:name>Column_2_Name</tns:name>
					<tns:id>Column_2_ID</tns:id>
					<tns:field>Column_2_Mapping</tns:field>
				</tns:column>
				<tns:column>
					<tns:name>Column_3_Name</tns:name>
					<tns:id>Column_3_ID</tns:id>
					<tns:field>Column_3_Mapping</tns:field>
				</tns:column>
			</tns:columns>

			<tns:chartIdentity>
				<!-- <tns:chartId> -->
					<tns:class>grid</tns:class>
					<tns:drilldowntype>masterlistener</tns:drilldowntype> 
					<tns:type>listener</tns:type> 
					<tns:parameter>drilldownParam</tns:parameter> 
					<tns:master>widget1</tns:master>
				<!-- </tns:chartId> -->
			</tns:chartIdentity>
		</tns:widgets>
	</xsl:template>

	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>