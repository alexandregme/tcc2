package utils;

import java.text.DateFormat;
import java.util.Calendar;

import play.i18n.Messages;

/**
 * @author Jaime Daniel Corrêa Mendes
 * @version 0.1
 * @since 2013-01-23
 * 
 *
 * Helps handling messages for output 
 *
 */
public class MessageHelper {

	/**
	 * 
	 * @param __key
	 * @param __args
	 * @return A string for the protocol object	
	 */
	public static String get(String __key, Object... __args) {
		
		String _message = Messages.get(__key, __args);
		
		if (_message.equals(__key)) {
			return "Mensagem não informada ou inválida. " +__key +" "+ DateFormat.getInstance().format(Calendar.getInstance().getTime());
		} else {
			return _message + " "  + DateFormat.getInstance().format(Calendar.getInstance().getTime());
		}
		
	}
	
}
