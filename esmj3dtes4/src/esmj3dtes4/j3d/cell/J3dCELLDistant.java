package esmj3dtes4.j3d.cell;

import java.util.Iterator;
import java.util.List;

import org.jogamp.java3d.Node;

import esmmanager.common.data.record.IRecordStore;
import esmmanager.common.data.record.Record;
import utils.source.MediaSources;

public class J3dCELLDistant extends J3dCELL
{
	//Notice this distant has it's own children list(defined by DISTANT type children)
	public J3dCELLDistant(IRecordStore master, Record cellRecord, int worldId, List<Record> children, boolean makePhys, MediaSources mediaSources)
	{
		super(master, cellRecord, worldId, children, makePhys, mediaSources);
		indexRecords();
	}

	private void indexRecords()
	{
		if (makePhys == true)
			System.out.println("Hahahaha distant physics!");

		for (Iterator<Record> i = children.iterator(); i.hasNext();)
		{
			Record record = i.next();
			Node n = makeJ3dRECOFar(record);
			addChild(n);
		}
	}
}
