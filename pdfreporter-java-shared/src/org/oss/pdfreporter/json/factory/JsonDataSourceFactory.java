package org.oss.pdfreporter.json.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.oss.pdfreporter.engine.DefaultJasperReportsContext;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.data.JsonDataSource;
import org.oss.pdfreporter.json.IJsonDataSource;
import org.oss.pdfreporter.registry.ApiRegistry;

public class JsonDataSourceFactory implements IJsonDataSourceFactory {


	private JsonDataSourceFactory() {
		// just for initializing
	}


	public static void registerFactory() {
		ApiRegistry.register(new JsonDataSourceFactory());
	}

	@Override
	public IJsonDataSource newJsonDataSource(InputStream jsonStream) throws JRException {
		return new JsonDataSource(jsonStream, null);
	}

	@Override
	public IJsonDataSource newJsonDataSource(InputStream jsonStream, String selectExpression) throws JRException {
		return new JsonDataSource(jsonStream, selectExpression);
	}

	@Override
	public IJsonDataSource newJsonDataSource(File file) throws FileNotFoundException, JRException {
		return new JsonDataSource(new FileInputStream(file), null);
	}

	@Override
	public IJsonDataSource newJsonDataSource(File file, String selectExpression) throws FileNotFoundException, JRException {
		return new JsonDataSource(new FileInputStream(file), selectExpression);
	}

	@Override
	public IJsonDataSource newJsonDataSource(JasperReportsContext jasperReportsContext, String location, String selectExpression) throws JRException {
		return new JsonDataSource(jasperReportsContext, location, selectExpression);
	}

	@Override
	public IJsonDataSource newJsonDataSource(String location, String selectExpression) throws JRException {
		return new JsonDataSource(DefaultJasperReportsContext.getInstance(), location, selectExpression);
	}

}
