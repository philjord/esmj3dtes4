package esmj3dtes4.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonWRLD;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.SNAM_d;

public class WRLD extends CommonWRLD
{

	public SNAM_d SNAM = null;

	public String ICON = null;

	public WRLD(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("SNAM"))
			{
				SNAM = new SNAM_d(bs);
			}
			else if (sr.getSubrecordType().equals("ICON"))
			{
				ICON = ZString.toString(bs);
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("NAM9"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("OFST"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("MNAM"))
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
