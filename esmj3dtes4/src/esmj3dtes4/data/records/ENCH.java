package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;

public class ENCH extends RECO
{

	public ZString EDID = null;

	public ENCH(Record recordData)
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
			else if (sr.getType().equals("FULL"))
			{

			}
			else if (sr.getType().equals("ENIT"))
			{

			}
			else if (sr.getType().equals("EFID"))
			{

			}
			else if (sr.getType().equals("EFIT"))
			{

			}
			else if (sr.getType().equals("CTDA"))
			{

			}
			else if (sr.getType().equals("SCIT"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
