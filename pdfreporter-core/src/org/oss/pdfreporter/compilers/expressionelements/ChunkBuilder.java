/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.compilers.expressionelements;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.compilers.IExpressionChunk;
import org.oss.pdfreporter.compilers.IVariable;
import org.oss.pdfreporter.compilers.IVariableExpressionChunk;
import org.oss.pdfreporter.compilers.IExpressionChunk.ExpresionType;


public class ChunkBuilder {

	private final ChunkList chunkList = new ChunkList();

	public ChunkBuilder addText(String text) {
		chunkList.add(new Chunk(ExpresionType.TYPE_TEXT, text));
		return this;
	}


	public ChunkBuilder addResource(String text) {
		chunkList.add(new Chunk(ExpresionType.TYPE_RESOURCE, text));
		return this;
	}

	public ChunkBuilder addVariable(IVariable variable) {
		chunkList.add(new VariableChunk(variable));
		return this;
	}

	public List<IExpressionChunk> getChunkList() {
		return chunkList;
	}

	private static class ChunkList extends AbstractList<IExpressionChunk> {
		private final List<IExpressionChunk> delegate = new ArrayList<IExpressionChunk>();

		@Override
		public IExpressionChunk get(int index) {
			return delegate.get(index);
		}

		@Override
		public int size() {
			return delegate.size();
		}

		@Override
		public boolean add(IExpressionChunk e) {
			return delegate.add(e);
		}
	}

	private static class Chunk implements IExpressionChunk {

		private final ExpresionType type;
		private final String text;

		Chunk(ExpresionType type, String text) {
			this.type = type;
			this.text = text;
		}

		@Override
		public ExpresionType getType() {
			return type;
		}

		@Override
		public String getText() {
			return text;
		}

		@Override
		public String toString() {
			return "Chunk [type=" + type + ", text=" + text + "]";
		}


	}

	private static class VariableChunk extends Chunk implements IVariableExpressionChunk {

		private final IVariable variable;
		public VariableChunk(IVariable variable) {
			super(variable.getType(), variable.getName());
			this.variable = variable;
		}

		@Override
		public IVariable getVariable() {
			return variable;
		}

		@Override
		public String toString() {
			return "VariableChunk [variable=" + variable + "]";
		}

	}

}
