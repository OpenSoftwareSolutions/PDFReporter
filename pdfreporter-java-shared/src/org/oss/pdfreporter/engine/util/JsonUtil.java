package org.oss.pdfreporter.engine.util;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.type.JsonOperatorEnum;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 * @version $Id: JsonUtil.java 5627 2012-08-31 09:59:29Z narcism $
 */
public class JsonUtil {

	public static boolean evaluateJsonExpression(JsonNode contextNode, String attributeExpression) throws JRException {

		if (attributeExpression == null) {
			return true;
		}

		String attribute = null;
		JsonOperatorEnum operator = null;
		String value = null;
		boolean result = false;

		for (JsonOperatorEnum joe: JsonOperatorEnum.values()) {
			int indexOfOperator = attributeExpression.indexOf(joe.getValue());
			if (indexOfOperator != -1) {
				operator = joe;
				attribute = attributeExpression.substring(0, indexOfOperator).trim();
				value = attributeExpression.substring(indexOfOperator + joe.getValue().length()).trim();
				break;
			}
		}

		if (operator == null) {
			StringBuffer possibleOperations = new StringBuffer();
			for (JsonOperatorEnum op: JsonOperatorEnum.values()) {
				possibleOperations.append(op.getValue()).append(",");
			}
			throw new JRException("Unknown operator in expression: " + attributeExpression + "; Operator must be one of: " + possibleOperations);
		}

		if (attribute != null && operator != null && value != null) {
			// going down the path of the attribute must return a value node
			if (!contextNode.path(attribute).isValueNode()) {
				result = false;
			} else {
				String contextValue = contextNode.path(attribute).asText();
				switch(operator) {
				case LT:
					try {
						result = Double.parseDouble(contextValue) < Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case LE:
					try {
						result = Double.parseDouble(contextValue) <= Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case GT:
					try {
						result = Double.parseDouble(contextValue) > Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case GE:
					try {
						result = Double.parseDouble(contextValue) >= Double.parseDouble(value);
					} catch (NumberFormatException nfe) {
						result = false;
					}
					break;
				case EQ:
					result = contextValue.equals(value);
					break;
				case NE:
					result = !contextValue.equals(value);
					break;
				}
			}
		}

		return result;
	}
}