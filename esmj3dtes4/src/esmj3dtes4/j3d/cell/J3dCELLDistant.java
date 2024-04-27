package esmj3dtes4.j3d.cell;

import java.util.Iterator;
import java.util.List;

import org.jogamp.java3d.Node;

import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.j3d.cell.J3dCELLGeneral;
import utils.source.MediaSources;

/**
 * Notice this distant has it's own children list(defined by DISTANT type children)
 */
public class J3dCELLDistant extends J3dCELL
{	
	public J3dCELLDistant(IRecordStore master, Record cellRecord, int worldId, List<Record> children, boolean makePhys, MediaSources mediaSources)
	{
		super(master, cellRecord, worldId, makePhys, mediaSources);
		indexRecords(children);
	}

	private void indexRecords(List<Record> children)
	{
		for (Iterator<Record> i = children.iterator(); i.hasNext();)
		{
			while(J3dCELLGeneral.PAUSE_CELL_LOADING) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {					
				}
			}
			Record record = i.next();
			Node n = makeJ3dRECOFar(record);
			addChild(n);
		}
	}
}
