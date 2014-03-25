package esmj3dtes4.j3d.cell;

import java.util.Iterator;
import java.util.List;

import utils.source.MediaSources;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmj3d.data.shared.records.CommonWRLD;
import esmj3d.j3d.cell.GridSpaces;
import esmj3d.j3d.cell.J3dICELLPersistent;
import esmj3dtes4.data.records.ACHR;
import esmj3dtes4.data.records.ACRE;
import esmj3dtes4.data.records.REFR;

public class J3dCELLPersistent extends J3dCELL implements J3dICELLPersistent
{
	private GridSpaces gridSpaces = new GridSpaces(this);

	private CommonWRLD wrld;

	/**
	 * CELL presistent is different from temp and distant as it's dynamic refs and achar can move away
	 * but they are still managed by the cell persistent itself, so we have this crazy grid space sub system to quickly 
	 * clip wandering things away
	 * 
	 * @param master
	 * @param cellRecord
	 * @param children
	 * @param makePhys
	 * @param recordToRECO
	 */

	public J3dCELLPersistent(CommonWRLD wrld, IRecordStore master, Record cellRecord, List<Record> children, boolean makePhys,
			MediaSources mediaSources)
	{
		super(master, cellRecord, children, makePhys, mediaSources);
		this.wrld = wrld;
		addChild(gridSpaces);
		indexRecords();
	}

	private void indexRecords()
	{
		//NOTE they can be 10'000 's of these records do not load up front!
		for (Iterator<Record> i = children.iterator(); i.hasNext();)
		{
			Record record = i.next();

			if (record.getRecordType().equals("REFR"))
			{
				REFR refr = new REFR(record);
				gridSpaces.sortOutBucket(refr, record);
			}
			else if (record.getRecordType().equals("ACRE"))
			{
				ACRE acre = new ACRE(record);
				gridSpaces.sortOutBucket(acre, record);
			}
			else if (record.getRecordType().equals("ACHR"))
			{
				ACHR achr = new ACHR(record);
				gridSpaces.sortOutBucket(achr, record);
			}
			else
			{
				System.out.println("CELL_PERSISTENT Record type not converted to j3d " + record.getRecordType());
			}
		}
	}

	@Override
	public GridSpaces getGridSpaces()
	{
		return gridSpaces;
	}

	@Override
	public CommonWRLD getWRLD()
	{
		return wrld;
	}
}
