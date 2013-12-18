package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;

public class SCPT extends RECO
{

	public ZString EDID = null;

	public SCPT(Record recordData)
	{
		super(recordData);
		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getData();

			if (sr.getType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getType().equals("SCRV"))
			{

			}
			else if (sr.getType().equals("SCVR"))
			{

			}
			else if (sr.getType().equals("SCTX"))
			{

			}
			else if (sr.getType().equals("SLSD"))
			{

			}
			else if (sr.getType().equals("SCDA"))
			{

			}
			else if (sr.getType().equals("SCRO"))
			{

			}
			else if (sr.getType().equals("SCHR"))
			{

			}

			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
