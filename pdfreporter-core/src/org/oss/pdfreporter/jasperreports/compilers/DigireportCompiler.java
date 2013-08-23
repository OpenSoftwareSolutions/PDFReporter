package org.oss.pdfreporter.jasperreports.compilers;

import java.io.File;
import java.io.Serializable;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRReport;
import org.oss.pdfreporter.engine.JasperReportsContext;
import org.oss.pdfreporter.engine.design.JRAbstractCompiler;
import org.oss.pdfreporter.engine.design.JRCompilationSourceCode;
import org.oss.pdfreporter.engine.design.JRCompilationUnit;
import org.oss.pdfreporter.engine.design.JRSourceCompileTask;
import org.oss.pdfreporter.engine.fill.JREvaluator;


public class DigireportCompiler extends JRAbstractCompiler {
	private static final Logger logger = Logger.getLogger(DigireportCompiler.class.getName());
	private final static GenericDataEvaluator evaluator = new GenericDataEvaluator();


	public DigireportCompiler(JasperReportsContext jasperReportsContext,
			boolean needsSourceFiles) {
		super(jasperReportsContext, needsSourceFiles);
	}

	public DigireportCompiler(JasperReportsContext jasperReportsContext) {
		this(jasperReportsContext,false);
	}

	@Override
	protected JREvaluator loadEvaluator(Serializable compileData,
			String unitName) throws JRException {
		// TODO (12.04.2013, Donat, Digireport): Implement with deserialization of evaluator
		logger.finest("loadEvaluator: compileData=" + compileData + ", unitName=" + unitName);
		return evaluator;
	}

	@Override
	protected void checkLanguage(String language) throws JRException {
		if (!JRReport.LANGUAGE_JAVA.equals(language))
		{
			throw 
				new JRException(
					"Language \"" + language 
					+ "\" not supported by this report compiler.\n"
					+ "Expecting \"objectivec\" instead."
					);
		}
		
	}

	@Override
	protected JRCompilationSourceCode generateSourceCode(
			JRSourceCompileTask sourceTask) throws JRException {
		// TODO (12.04.2013, Donat, Digireport): Create a evaluator instance per call and serialize it
		evaluator.initializeWithDefaults(sourceTask);
		evaluator.parseExpressions(sourceTask);
		return null;
	}

	@Override
	protected String compileUnits(JRCompilationUnit[] units, String classpath,
			File tempDirFile) throws JRException {
		return null;
	}

	@Override
	protected String getSourceFileName(String unitName) {
		return null;
	}

}
