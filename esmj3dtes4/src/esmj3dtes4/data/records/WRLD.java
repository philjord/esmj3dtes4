package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.SNAM_d;

public class WRLD extends RECO
{
	public ZString EDID = null;

	public LString FULL = null;

	public FormID WNAM = null;

	public SNAM_d SNAM = null;

	public ZString ICON = null;

	public FormID CNAM = null;

	public FormID NAM2 = null;

	public byte DATA = 0;

	public WRLD(Record recordData)
	{
		super(recordData);
		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new LString(bs);
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
				WNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
				SNAM = new SNAM_d(bs);
			}
			else if (sr.getSubrecordType().equals("ICON"))
			{
				ICON = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{
				CNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("NAM2"))
			{
				NAM2 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				DATA = bs[0];
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
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

	public String showDetails()
	{
		return "WRLD : (" + formId + "|" + Integer.toHexString(formId) + ") " + EDID.str + " : " + FULL.str;
	}

}
