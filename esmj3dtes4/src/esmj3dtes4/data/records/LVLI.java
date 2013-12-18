package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.LVLD;
import esmj3dtes4.data.subrecords.LVLF;
import esmj3dtes4.data.subrecords.LVLO;

public class LVLI extends RECO
{
	public ZString EDID = null;

	public LVLD LVLD = null;

	public LVLF LVLF = null;

	public LVLO[] LVLOs = null;

	public DATA DATA = null;

	public LVLI(Record recordData)
	{
		super(recordData);

		ArrayList<LVLO> LVLOsl = new ArrayList<LVLO>();

		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getData();

			if (sr.getType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getType().equals("LVLD"))
			{
				LVLD = new LVLD(bs);
			}
			else if (sr.getType().equals("LVLF"))
			{
				LVLF = new LVLF(bs);
			}
			else if (sr.getType().equals("LVLO"))
			{
				LVLOsl.add(new LVLO(bs));
			}
			else if (sr.getType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

			// transfer to arrays
			LVLOs = new LVLO[LVLOsl.size()];
			LVLOsl.toArray(LVLOs);
		}
	}

	public class DATA
	{
		public byte[] data;

		private DATA(byte[] bytes)
		{
			data = bytes;
		}
	}
}
