package org.oss.pdfreporter.engine.fill;

import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.engine.JRPrintElement;
import org.oss.pdfreporter.engine.JRPrintPage;
import org.oss.pdfreporter.engine.type.FooterPositionEnum;


class SavePoint
{
	protected JRPrintPage page;
	protected int columnIndex;
	protected boolean isNewPage;
	protected boolean isNewColumn;
	protected int startOffsetY;
	protected int endOffsetY;
	protected int startElementIndex;
	protected int endElementIndex;
	protected int heightOffset;
	protected int groupIndex;
	protected FooterPositionEnum footerPosition = FooterPositionEnum.NORMAL;
	protected List<JRPrintElement> elementsToMove = new ArrayList<JRPrintElement>();
	
	protected SavePoint(
		JRPrintPage page,
		int columnIndex,
		boolean isNewPage,
		boolean isNewColumn,
		int startOffsetY
		)
	{
		this.page = page;
		this.columnIndex = columnIndex;
		this.isNewPage = isNewPage;
		this.isNewColumn = isNewColumn;

		this.startElementIndex = page.getElements().size();
		this.endElementIndex = startElementIndex;
		
		this.startOffsetY = startOffsetY;
	}
	
	protected void saveHeightOffset(int heightOffset)
	{
		this.heightOffset = heightOffset;
		
		save();
	}
	
	protected void saveEndOffsetY(int endOffsetY)
	{
		this.endOffsetY = endOffsetY;
		
		save();
	}
	
	protected void save()
	{
		this.endElementIndex = page.getElements().size();
	}
	
	/**
	 *
	 */
	protected void removeContent()
	{
		for(int i = endElementIndex - 1; i >= startElementIndex; i--)
		{
			elementsToMove.add(page.getElements().remove(i));//FIXME this breaks delayed evaluations
		}
	}

	/**
	 *
	 */
	protected void addContent(JRPrintPage printPage, int xdelta, int ydelta)
	{
		for(int i = elementsToMove.size() - 1; i >= 0; i--)// elementsToMove were added in reverse order
		{
			JRPrintElement printElement = elementsToMove.get(i);

			printElement.setX(printElement.getX() + xdelta);
			printElement.setY(printElement.getY() + ydelta);

			printPage.addElement(printElement);
		}
	}

	/**
	 *
	 */
	protected void moveSavePointContent()
	{
		if (footerPosition != FooterPositionEnum.NORMAL)//FIXME is footerPosition testing required here?
		{
			//no page/column break occurred
			for(int i = startElementIndex; i < endElementIndex; i++)
			{
				JRPrintElement printElement = page.getElements().get(i);
				printElement.setY(printElement.getY() + heightOffset);
			}
		}
	}

}