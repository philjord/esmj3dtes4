package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import esmmanager.common.data.record.Record;
import esmmanager.common.data.record.Subrecord;

public class QUST extends RECO
{

	public ZString EDID = null;

	public QUST(Record recordData)
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
			else if (sr.getType().equals("DATA"))
			{

			}
			else if (sr.getType().equals("ICON"))
			{

			}
			else if (sr.getType().equals("CTDA"))
			{

			}
			else if (sr.getType().equals("SCRI"))
			{

			}
			else if (sr.getType().equals("FULL"))
			{

			}
			else if (sr.getType().equals("INDX"))
			{

			}
			else if (sr.getType().equals("QSDT"))
			{

			}
			else if (sr.getType().equals("CNAM"))
			{

			}
			else if (sr.getType().equals("SCHR"))
			{

			}
			else if (sr.getType().equals("SCDA"))
			{

			}
			else if (sr.getType().equals("SCTX"))
			{

			}
			else if (sr.getType().equals("SCRO"))
			{

			}
			else if (sr.getType().equals("QOBJ"))
			{

			}
			else if (sr.getType().equals("NNAM"))
			{

			}
			else if (sr.getType().equals("QSTA"))
			{

			}
			else if (sr.getType().equals("NAM0"))
			{

			}

			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
