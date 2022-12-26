package br.com.emanuel.rolesapi.infra.integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IntegrationRestRequest {
	
	public <T> T get(String urlAddress, Class<T> clazz){
		
		T result = null;
		
		try {
			URL url = new URL(urlAddress);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			
			try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))){
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				result = objectMapper.readValue(in, clazz);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
