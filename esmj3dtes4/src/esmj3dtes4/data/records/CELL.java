package esmj3dtes4.data.records;

import java.util.ArrayList;

import tools.io.ESMByteConvert;
import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonCELL;

public class CELL extends CommonCELL
{

	public DATA DATA;

	public byte musicType = 1;

	public int XGLB = -1;

	public int XRNK = 0;

	public CELL(Record recordData)
	{
		super(recordData);

		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getData();

			if (sr.getType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getType().equals("XCMT"))
			{
				musicType = bs[0];
			}
			else if (sr.getType().equals("XRNK"))
			{
				XRNK = ESMByteConvert.extractInt(bs, 0);
			}
			else if (sr.getType().equals("XGLB"))
			{

			}
			else
			{
				// no longer possible with CommonCELL
				//System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

	public String showDetails()
	{
		return "CELL : (" + formId + "|" + Integer.toHexString(formId) + ") " + EDID.str + " : " + FULL.str;
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
