package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRException;

/**
 * Generic delayed evaluation action.
 */
interface EvaluationBoundAction
{
	void execute(byte evaluation, JREvaluationTime evaluationTime) throws JRException;
}