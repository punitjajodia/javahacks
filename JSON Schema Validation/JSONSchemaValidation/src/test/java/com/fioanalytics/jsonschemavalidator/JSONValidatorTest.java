package com.fioanalytics.jsonschemavalidator;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.fioanalytics.JSONValidator.JSONValidator;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

public class JSONValidatorTest extends TestCase {
	
	 public JSONValidatorTest( String testName )
    {
        super( testName );
    }
	 
	 public static Test suite()
	    {
	        return new TestSuite( JSONValidatorTest.class );
	    }
	
    public void testJSONValidator() throws IOException, ProcessingException
    {
    	final InputStream schemaStream = getClass().getResourceAsStream("/JSONSchema.json");
    	final InputStream jsonStream = getClass().getResourceAsStream("/JSON.json");
    	System.out.println(JSONValidator.validate(schemaStream, jsonStream));
    }    

}
