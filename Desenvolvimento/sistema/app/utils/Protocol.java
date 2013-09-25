package utils;

/**
 * @author Jaime Daniel Corrêa Mendes
 * @version 0.1
 * @since 2013-01-22
 * 
 * Protocol for interface communication
 * Every JSON or XML to the interface should
 * be return in this format.
 *
 */
public class Protocol {

	/**
	 * Indicates the 'code' of the message
	 * s = success; e = error; a = warning
	 */
	public char status;
	
	/**
	 * Message for interface. This message
	 * is displayed for the user
	 */
	public String message;
	
	/**
	 * The content returned.
	 * Models, lists, arrays, welcome!
	 */
	public Object data;
	
	/**
	 * Amount of data returned.
	 * When concerning about too long lists,
	 * this field indicates the amount of data
	 * existing (universe). In data, as an array,
	 * should be just a sample of this whole data
	 */
	public int length;
	
	/**
	 * Empty constructor
	 */
	public Protocol() {
		
	}
	
	/**
	 * Parameterized constructor
	 */
	public Protocol(char __status, String __message, Object __data, int __length) {
		this.status = Character.toLowerCase(__status);
		this.message = __message;
		this.data = __data;
		this.length = __length;
	}
	
	
}
