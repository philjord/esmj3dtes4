package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmj3d.data.shared.records.CommonWRLD;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.SNAM_d;
import esmmanager.common.data.record.Record;
import esmmanager.common.data.record.Subrecord;

public class WRLD extends CommonWRLD
{

	public SNAM_d SNAM = null;

	public ZString ICON = null;

	public WRLD(Record recordData)
	{
		super(recordData);
		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getData();

			if (sr.getType().equals("SNAM"))
			{
				SNAM = new SNAM_d(bs);
			}
			else if (sr.getType().equals("ICON"))
			{
				ICON = new ZString(bs);
			}
			else if (sr.getType().equals("NAM0"))
			{
				//
			}
			else if (sr.getType().equals("NAM9"))
			{
				//
			}
			else if (sr.getType().equals("OFST"))
			{
				//
			}
			else if (sr.getType().equals("MNAM"))
			{
				//
			}
			else
			{
				//	System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
