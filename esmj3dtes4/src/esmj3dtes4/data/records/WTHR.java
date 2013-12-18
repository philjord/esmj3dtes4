package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;

public class WTHR extends RECO
{
	public ZString EDID = null;

	public MODL MODL;

	public WTHR(Record recordData)
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
			else if (sr.getType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}
			else if (sr.getType().equals("MODB"))
			{
				MODL.addMODBSub(bs);
			}
			else if (sr.getType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}
			else if (sr.getType().equals("INAM"))
			{

			}
			else if (sr.getType().equals("ANAM"))
			{

			}
			else if (sr.getType().equals("CNAM"))
			{

			}
			else if (sr.getType().equals("PNAM"))
			{

			}
			else if (sr.getType().equals("FNAM"))
			{

			}
			else if (sr.getType().equals("LNAM"))
			{

			}
			else if (sr.getType().equals("NAM0"))
			{

			}
			else if (sr.getType().equals("ONAM"))
			{

			}
			else if (sr.getType().equals("BNAM"))
			{

			}
			else if (sr.getType().equals("SNAM"))
			{

			}
			else if (sr.getType().equals("DATA"))
			{

			}
			else if (sr.getType().equals("DNAM"))
			{

			}
			else if (sr.getType().equals("HNAM"))
			{

			}
			else if (sr.getType().endsWith("IAD"))
			{
				//CRAZY CRAZY!
			}
			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
