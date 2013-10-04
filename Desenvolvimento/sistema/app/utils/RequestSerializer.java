package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Jaime Daniel Corrêa Mendes
 * @version 0.1
 * @since 2013-01-23
 * 
 *
 * Manages serialization of Requests 
 *
 */
public class RequestSerializer {

	/**
	 * 
	 * Get the request.body from play as an
	 * InputStream and serializes it as an
	 * model (.class)
	 * 
	 * @param __reader
	 * @param __class
	 * @return <Model>, as the specified class.
	 */
	public static <Model> Model get(InputStream __reader, Class<Model> __class) {
		
		//TODO Implements a validation across incoming JSON and class type.
		
		if (__reader.equals(null)) {
			return null;
		}
		
		Model _unserialize = null;
		
		Gson _gson = new GsonBuilder().serializeNulls().setDateFormat("dd/MM/yyyy"). create();
		
		try {
		
			_unserialize = _gson.fromJson(new InputStreamReader(__reader), __class);
			
			return _unserialize;
			
		} catch (Exception __exception) {
			
			play.Logger.error("RequestSerializer::get() -> Tentando fazer com StringWriter :: ", __exception.toString());
			
			StringWriter _writer = new StringWriter();
            
			try {
            	
				IOUtils.copy(__reader, _writer, "UTF-8");
				return _gson.fromJson(_writer.toString(), __class);
				
            } catch (IOException __innerException) {
            	
            	play.Logger.error("RequestSerializer::get() -> Mesmo assim deu erro :: ", __innerException.getMessage());
            	return null;
            	
            }
			
			
		}
		
		
	}
	
	/**
	 * Return the serialized object (String), including null properties
	 * and with date format like dd/MM/yyyy
	 * @param __source
	 * @param __class
	 * @return
	 */
	public static String toJson(Object __source, Class __class) {
		return new GsonBuilder().serializeNulls().setDateFormat("dd/MM/yyyy").create().toJson(__source, __class);
	}
	
}
