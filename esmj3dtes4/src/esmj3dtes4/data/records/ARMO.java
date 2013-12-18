package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ANAM;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.BMDT;

public class ARMO extends RECO
{
	public ZString EDID;

	public FormID SCRI;

	public BMDT BMDT;

	public MODL MODL; // male worn (or if no sex)

	public MODL MOD2; // male ground

	public MODL MOD3; // female worn  

	public MODL MOD4; // female ground

	public ZString ICON;

	public ZString ICO2;

	public ANAM ANAM;

	public FormID ENAM;

	public DATA DATA;

	public ARMO(Record recordData)
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
			else if (sr.getType().equals("SCRI"))
			{
				SCRI = new FormID(bs);
			}
			else if (sr.getType().equals("BMDT"))
			{
				BMDT = new BMDT(bs);
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
			else if (sr.getType().equals("MOD2"))
			{
				MOD2 = new MODL(bs);
			}
			else if (sr.getType().equals("MO2B"))
			{
				MOD2.addMODBSub(bs);
			}
			else if (sr.getType().equals("MO2T"))
			{
				MOD2.addMODTSub(bs);
			}
			else if (sr.getType().equals("MOD3"))
			{
				MOD3 = new MODL(bs);
			}
			else if (sr.getType().equals("MO3B"))
			{
				MOD3.addMODBSub(bs);
			}
			else if (sr.getType().equals("MO3T"))
			{
				MOD3.addMODTSub(bs);
			}
			else if (sr.getType().equals("MOD4"))
			{
				MOD4 = new MODL(bs);
			}
			else if (sr.getType().equals("MO4B"))
			{
				MOD4.addMODBSub(bs);
			}
			else if (sr.getType().equals("MO4T"))
			{
				MOD4.addMODTSub(bs);
			}
			else if (sr.getType().equals("ICON"))
			{
				ICON = new ZString(bs);
			}
			else if (sr.getType().equals("ICO2"))
			{
				ICO2 = new ZString(bs);
			}
			else if (sr.getType().equals("ANAM"))
			{
				ANAM = new ANAM(bs);
			}
			else if (sr.getType().equals("ENAM"))
			{
				ENAM = new FormID(bs);
			}
			else if (sr.getType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}

			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}
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
