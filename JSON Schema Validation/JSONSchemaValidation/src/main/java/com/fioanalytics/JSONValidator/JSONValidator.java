package com.fioanalytics.JSONValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class JSONValidator {
	private static final JsonValidator VALIDATOR = JsonSchemaFactory.byDefault().getValidator();
	
	public static boolean validate(String JsonSchema, String JsonInstance) throws IOException, ProcessingException{
		final JsonNode schema = JsonLoader.fromString(JsonSchema);
		final JsonNode instance = JsonLoader.fromString(JsonInstance);
		
		final ProcessingReport r = VALIDATOR.validate(schema, instance);
		return r.isSuccess();
	}
	
	
	public static boolean validate(InputStream JsonSchema, String JsonInstance) throws IOException, ProcessingException{
		final String JsonSchemaString = getStringFromInputStream(JsonSchema);
		return validate(JsonSchemaString, JsonInstance);
	}
	
	public static boolean validate(InputStream JsonSchema, InputStream JsonInstance) throws IOException, ProcessingException{
		final String JsonSchemaString = getStringFromInputStream(JsonSchema);
		final String JsonInstanceString = getStringFromInputStream(JsonInstance);
		return validate(JsonSchemaString, JsonInstanceString);
	}
	
	 private static String getStringFromInputStream(InputStream is) {	 
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return sb.toString();
		}
}
