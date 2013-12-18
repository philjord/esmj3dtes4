package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;

public class PACK extends RECO
{

	public ZString EDID = null;

	public PACK(Record recordData)
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
			else if (sr.getType().equals("PKDT"))
			{

			}
			else if (sr.getType().equals("PSDT"))
			{

			}
			else if (sr.getType().equals("DATA"))
			{

			}
			else if (sr.getType().equals("PTDT"))
			{

			}
			else if (sr.getType().equals("CTDA"))
			{

			}
			else if (sr.getType().equals("CTDT"))
			{

			}
			else if (sr.getType().equals("PKDD"))
			{

			}
			else if (sr.getType().equals("POBA"))
			{

			}
			else if (sr.getType().equals("INAM"))
			{

			}
			else if (sr.getType().equals("SCHR"))
			{

			}
			else if (sr.getType().equals("TNAM"))
			{

			}
			else if (sr.getType().equals("POEA"))
			{

			}
			else if (sr.getType().equals("POCA"))
			{

			}
			else if (sr.getType().equals("PLDT"))
			{

			}
			else if (sr.getType().equals("SCDA"))
			{

			}
			else if (sr.getType().equals("SCTX"))
			{

			}
			else if (sr.getType().equals("PKPT"))
			{

			}
			else if (sr.getType().equals("PLD2"))
			{

			}
			else if (sr.getType().equals("SCRO"))
			{

			}
			else if (sr.getType().equals("PKW3"))
			{

			}
			else if (sr.getType().equals("DATA"))
			{

			}
			else if (sr.getType().equals("PTD2"))
			{

			}
			else if (sr.getType().equals("PKFD"))
			{

			}
			else if (sr.getType().equals("PKAM"))
			{

			}
			else if (sr.getType().equals("PUID"))
			{

			}
			else if (sr.getType().equals("CNAM"))
			{

			}
			else if (sr.getType().equals("IDLF"))
			{

			}
			else if (sr.getType().equals("IDLC"))
			{

			}
			else if (sr.getType().equals("IDLT"))
			{

			}
			else if (sr.getType().equals("IDLA"))
			{

			}
			else if (sr.getType().equals("IDLB"))
			{

			}
			else if (sr.getType().equals("PKED"))
			{

			}
			else if (sr.getType().equals("PKE2"))
			{

			}
			else if (sr.getType().equals("SLSD"))
			{

			}
			else if (sr.getType().equals("SCVR"))
			{

			}
			else if (sr.getType().equals("SCRV"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
