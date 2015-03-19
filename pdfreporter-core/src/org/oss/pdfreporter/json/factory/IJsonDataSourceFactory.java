package org.oss.pdfreporter.json.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.json.IJsonDataSource;


public interface IJsonDataSourceFactory {

	/**
	 * @param jsonStream
	 * @param selectExpression
	 * @return
	 */
	IJsonDataSource newJsonDataSource(InputStream jsonStream, String selectExpression) throws JRException;

	/**
	 * @param jsonStream
	 * @return
	 * @throws JRException
	 */
	IJsonDataSource newJsonDataSource(InputStream jsonStream) throws JRException;

	/**
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	IJsonDataSource newJsonDataSource(File file) throws FileNotFoundException, JRException;
	/**
	 * @param file
	 * @param selectExpression
	 * @return
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	IJsonDataSource newJsonDataSource(File file, String selectExpression) throws FileNotFoundException, JRException;
	/**
	 * @param jasperReportsContext
	 * @param location
	 * @param selectExpression
	 * @return
	 * @throws JRException
	 */
	IJsonDataSource newJsonDataSource(JasperReportsContext jasperReportsContext, String location, String selectExpression) throws JRException;
	/**
	 * @param location
	 * @param selectExpression
	 * @return
	 * @throws JRException
	 */
	IJsonDataSource newJsonDataSource(String location, String selectExpression) throws JRException;

}
