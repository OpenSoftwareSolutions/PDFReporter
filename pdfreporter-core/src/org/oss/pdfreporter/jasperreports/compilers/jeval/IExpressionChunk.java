package org.oss.pdfreporter.jasperreports.compilers.jeval;

/**
 * @author donatmuller, 2013, last change 10:09:52 AM
 * 
 */
public interface IExpressionChunk {
	/**
	 * Type of Chunk
	 */
	public enum ExpresionType {
		TYPE_TEXT,
		TYPE_PARAMETER,
		TYPE_FIELD,
		TYPE_VARIABLE,
		TYPE_RESOURCE
	}

	/**
	 * 
	 */
	public ExpresionType getType();

	/**
	 * 
	 */
	public String getText();

}
