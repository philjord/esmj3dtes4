package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonREFR;
import esmj3dtes4.data.subrecords.XPCI;
import esmj3dtes4.data.subrecords.XSED;

public class REFR extends CommonREFR
{

	public XSED XSED;//in fallout3 but string



	public XPCI XPCI;//not in fallout3, not in tes5

	public REFR(Record recordData)
	{
		super(recordData);

		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("XSED"))
			{
				XSED = new XSED(bs);
			}
			
			else if (sr.getSubrecordType().equals("XPCI"))
			{
				XPCI = new XPCI(bs);
			}
			else
			{
				//CommonREFR makes this no use now
				//	System.out.println("unhandled : " + sr.getSubrecordType() + " in " + recordData);
			}
		}

	}

}
