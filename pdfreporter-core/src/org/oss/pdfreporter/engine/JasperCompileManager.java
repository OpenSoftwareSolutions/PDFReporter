/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.logging.Logger;

import org.oss.pdfreporter.compilers.jeval.JEvalCompiler;
import org.oss.pdfreporter.crosstabs.JRCrosstab;
import org.oss.pdfreporter.engine.design.JRCompiler;
import org.oss.pdfreporter.engine.design.JRValidationFault;
import org.oss.pdfreporter.engine.design.JRVerifier;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.fill.JREvaluator;
import org.oss.pdfreporter.engine.util.JRSaver;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.progress.ProgressManager;
import org.oss.pdfreporter.progress.IProgressHandler.ProgressState;



/**
 * Facade class for compiling report designs into the ready-to-fill form
 * and for getting the XML representation of report design objects for
 * storage or network transfer.
 * 
 * @see org.oss.pdfreporter.engine.design.JasperDesign
 * @see org.oss.pdfreporter.engine.JasperReport
 * @see org.oss.pdfreporter.engine.design.JRCompiler
 * @see org.oss.pdfreporter.engine.design.JRVerifier
 * @see org.oss.pdfreporter.engine.xml.JRXmlLoader
 * @see net.sf.jasperreports.engine.xml.JRXmlWriter
 * @see org.oss.pdfreporter.engine.util.JRLoader
 * @see org.oss.pdfreporter.engine.util.JRSaver
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperCompileManager.java 5420 2012-05-29 09:56:50Z lucianc $
 */
public final class JasperCompileManager
{
	private final static Logger logger = Logger.getLogger(JasperCompileManager.class.getName());
	private final static String JEVAL_COMPILER = JEvalCompiler.class.getName();
	private JasperReportsContext jasperReportsContext;


	/**
	 *
	 */
	private JasperCompileManager(JasperReportsContext jasperReportsContext)
	{
		this.jasperReportsContext = jasperReportsContext;
	}
	
	
	/**
	 *
	 */
	private static JasperCompileManager getDefaultInstance()
	{
		return new JasperCompileManager(DefaultJasperReportsContext.getInstance());
	}
	
	
	/**
	 *
	 */
	public static JasperCompileManager getInstance(JasperReportsContext jasperReportsContext)
	{
		return new JasperCompileManager(jasperReportsContext);
	}
	
	
	/**
	 * Compiles the XML report design file specified by the parameter.
	 * The result of this operation is another file that will contain the serialized  
	 * {@link org.oss.pdfreporter.engine.JasperReport} object representing the compiled report design,
	 * having the same name as the report design as declared in the XML plus the <code>*.jasper</code> extension,
	 * located in the same directory as the XML source file.
	 * 
	 * @param sourceFileName XML source file name
	 * @return resulting file name containing a serialized {@link org.oss.pdfreporter.engine.JasperReport} object 
	 */
	public String compileToFile(String sourceFileName) throws JRException
	{
		File sourceFile = new File(sourceFileName);

		JasperDesign jasperDesign = JRXmlLoader.load(sourceFileName);

		File destFile = new File(sourceFile.getParent(), jasperDesign.getName() + ".jasper");
		String destFileName = destFile.toString();

		compileToFile(jasperDesign, destFileName);
		
		return destFileName;
	}


	/**
	 * Compiles the XML report design file received as the first parameter, placing the result 
	 * in the file specified by the second parameter.
	 * The resulting file will contain a serialized instance of a 
	 * {@link org.oss.pdfreporter.engine.JasperReport} object representing 
	 * the compiled report design. 
	 * 
	 * @param sourceFileName XML source file name
	 * @param destFileName   file name to place the result into
	 */
	public void compileToFile(
		String sourceFileName,
		String destFileName
		) throws JRException
	{
		JasperDesign jasperDesign = JRXmlLoader.load(sourceFileName);

		compileToFile(jasperDesign, destFileName);
	}


	/**
	 * Compiles the report design object received as the first parameter, placing the result 
	 * in the file specified by the second parameter.
	 * The resulting file will contain a serialized instance of a 
	 * {@link org.oss.pdfreporter.engine.JasperReport} object representing the compiled report design.
	 * 
	 * @param jasperDesign source report design object
	 * @param destFileName file name to place the compiled report design into
	 */
	public void compileToFile(
		JasperDesign jasperDesign,
		String destFileName
		) throws JRException
	{
		JasperReport jasperReport = compile(jasperDesign);

		JRSaver.saveObject(jasperReport, destFileName);
	}


	/**
	 * Compiles the XML report design file received as parameter, and returns 
	 * the compiled report design object.
	 *  
	 * @param sourceFileName XML source file name
	 * @return compiled report design object 
	 */
	public  JasperReport compile(String sourceFileName) throws JRException
	{
		JasperDesign jasperDesign = JRXmlLoader.load(sourceFileName);

		return compile(jasperDesign);
	}


	/**
	 * Compiles the XML representation of the report design read from the supplied input stream and
	 * writes the generated compiled report design object to the output stream specified 
	 * by the second parameter.
	 * 
	 * @param inputStream  XML source input stream
	 * @param outputStream output stream to write the compiled report design to
	 */
	public void compileToStream(
		InputStream inputStream,
		OutputStream outputStream
		) throws JRException
	{
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

		compileToStream(jasperDesign, outputStream);
	}


	/**
	 * Compiles the report design object represented by the first parameter and
	 * writes the generated compiled report design object to the output stream specified 
	 * by the second parameter.
	 * 
	 * @param jasperDesign source report design object
	 * @param outputStream output stream to write the compiled report design to
	 */
	public void compileToStream(
		JasperDesign jasperDesign,
		OutputStream outputStream
		) throws JRException
	{
		JasperReport jasperReport = compile(jasperDesign);

		JRSaver.saveObject(jasperReport, outputStream);
	}


	/**
	 * Compiles the serialized report design object read from the supplied input stream and
	 * returns the generated compiled report design object.
	 * 
	 * @param inputStream XML source input stream
	 * @return compiled report design object 
	 */
	public JasperReport compile(InputStream inputStream) throws JRException
	{
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

		return compile(jasperDesign);
	}


	/**
	 * Compiles the report design object received as parameter and
	 * returns the generated compiled report design object.
	 *
	 * @param jasperDesign source report design object
	 * @return compiled report design object 
	 * @see org.oss.pdfreporter.engine.design.JRCompiler
	 */
	public JasperReport compile(JasperDesign jasperDesign) throws JRException
	{
		ProgressManager pm = new ProgressManager(ProgressState.COMPILING);
		try {			
			return getCompiler(jasperDesign).compileReport(jasperDesign);
		} finally {			
			pm.done();
		}

	}


	/**
	 * Verifies the validity and consistency of the report design object.
	 * Returns a collection of {@link JRValidationFault errors}, if problems are found in the report design.
	 *
	 * @param jasperDesign report design object to verify
	 * @return collection of {@link JRValidationFault JRValidationFault} if problems are found
	 * @see org.oss.pdfreporter.engine.design.JRVerifier
	 */
	public Collection<JRValidationFault> verify(JasperDesign jasperDesign)
	{
		return JRVerifier.verifyDesign(jasperDesign);
	}


	/**
	 * 
	 */
	public JREvaluator getEvaluator(JasperReport jasperReport, JRDataset dataset) throws JRException
	{
		JRCompiler compiler = getCompiler(jasperReport);
		
		return compiler.loadEvaluator(jasperReport, dataset);
	}


	/**
	 * 
	 */
	public JREvaluator getEvaluator(JasperReport jasperReport, JRCrosstab crosstab) throws JRException
	{
		JRCompiler compiler = getCompiler(jasperReport);
		
		return compiler.loadEvaluator(jasperReport, crosstab);
	}


	/**
	 * 
	 */
	public JREvaluator getEvaluator(JasperReport jasperReport) throws JRException
	{
		return getEvaluator(jasperReport, jasperReport.getMainDataset());
	}

	
	/**
	 * @see #compileToFile(String)
	 */
	public static String compileReportToFile(String sourceFileName) throws JRException
	{
		return getDefaultInstance().compileToFile(sourceFileName);
	}


	/**
	 * @see #compileToFile(String, String)
	 */
	public static void compileReportToFile(
		String sourceFileName,
		String destFileName
		) throws JRException
	{
		getDefaultInstance().compileToFile(sourceFileName, destFileName);
	}


	/**
	 * @see #compileToFile(JasperDesign, String)
	 */
	public static void compileReportToFile(
		JasperDesign jasperDesign,
		String destFileName
		) throws JRException
	{
		getDefaultInstance().compileToFile(jasperDesign, destFileName);
	}


	/**
	 * @see #compile(String)
	 */
	public static JasperReport compileReport(String sourceFileName) throws JRException
	{
		return getDefaultInstance().compile(sourceFileName);
	}


	/**
	 * @see #compileToStream(InputStream, OutputStream)
	 */
	public static void compileReportToStream(
		InputStream inputStream,
		OutputStream outputStream
		) throws JRException
	{
		getDefaultInstance().compileToStream(inputStream, outputStream);
	}


	/**
	 * @see #compileToStream(JasperDesign, OutputStream)
	 */
	public static void compileReportToStream(
		JasperDesign jasperDesign,
		OutputStream outputStream
		) throws JRException
	{
		getDefaultInstance().compileToStream(jasperDesign, outputStream);
	}


	/**
	 * @see #compile(InputStream)
	 */
	public static JasperReport compileReport(InputStream inputStream) throws JRException
	{
		return getDefaultInstance().compile(inputStream);
	}


	/**
	 * @see #compile(JasperDesign)
	 */
	public static JasperReport compileReport(JasperDesign jasperDesign) throws JRException
	{
		return getDefaultInstance().compile(jasperDesign);
	}


	/**
	 * @see #verify(JasperDesign)
	 */
	public static Collection<JRValidationFault> verifyDesign(JasperDesign jasperDesign)
	{
		return getDefaultInstance().verify(jasperDesign);
	}


	/**
	 * @see #getEvaluator(JasperReport, JRDataset)
	 */
	public static JREvaluator loadEvaluator(JasperReport jasperReport, JRDataset dataset) throws JRException
	{
		return getDefaultInstance().getEvaluator(jasperReport, dataset);
	}


	/**
	 * @see #getEvaluator(JasperReport, JRCrosstab)
	 */
	public static JREvaluator loadEvaluator(JasperReport jasperReport, JRCrosstab crosstab) throws JRException
	{
		return getDefaultInstance().getEvaluator(jasperReport, crosstab);
	}


	/**
	 * @see #getEvaluator(JasperReport)
	 */
	public static JREvaluator loadEvaluator(JasperReport jasperReport) throws JRException
	{
		return getDefaultInstance().getEvaluator(jasperReport);
	}
	

	/**
	 * Creates a compiler for compiled reports
	 */
	private JRCompiler getCompiler(JasperReport jasperReport) throws JRException
	{
		String compilerClassName = jasperReport.getCompilerClass();

		if(JEVAL_COMPILER.equals(compilerClassName)) {
			return new JEvalCompiler(jasperReportsContext);
		}
		throw new JRException("Report compiler '" + compilerClassName + "' not supported.");
	}

	


	/**
	 * Cerates a compiler to compile design reports 
	 */
	private JRCompiler getCompiler(JasperDesign jasperDesign) throws JRException
	{
		String language = jasperDesign.getLanguage();
		if (JRReport.LANGUAGE_JEVAL.equals(language)) {
			return new JEvalCompiler(jasperReportsContext,false);								
		}
		else
		{
			if (language==null) {
				throw new JRException("There is no default language set for compiler. You should include a dtd to your jrxml report file.");				
			}
			throw new JRException("No report compiler set for language : " + language);
		}
	}

	
}
