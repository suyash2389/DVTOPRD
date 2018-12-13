package com.ibm.csi.npm.dvreportmodel.main;


import java.awt.PageAttributes.MediaType;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.client.RestTemplate;

public class GrafanaReportBuilder {
	
	private static final Logger log = LoggerFactory.getLogger(GrafanaReportBuilder.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());*/
        
        /*Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://host:8080/context/rest/method");
        JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);*/
		
		String uri ="http://10.222.50.32:3000";
		
		try {
			URL url = new URL(uri);
			
			System.out.println("URI" + uri);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			System.out.println("URL" + url);
			
			
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "/api/search/");
			
			System.out.println("Get on /api/search/");
			
			JAXBContext jc = JAXBContext.newInstance(GrafanaReportBuilder.class);
			InputStream json = connection.getInputStream();
			
			System.out.println("get Input on json");
			
			System.out.println("Json: /n ");
			
			int c;
			while ((c=json.read()) != -1)
	        {
	            System.out.print((char) c);
	        }
			
			connection.disconnect();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		


	}

}
