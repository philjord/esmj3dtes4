package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmj3d.data.shared.records.CommonREFR;
import esmj3dtes4.data.subrecords.XPCI;
import esmj3dtes4.data.subrecords.XSED;
import esmmanager.common.data.record.Record;
import esmmanager.common.data.record.Subrecord;

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
			byte[] bs = sr.getData();

			if (sr.getType().equals("XSED"))
			{
				XSED = new XSED(bs);
			}

			else if (sr.getType().equals("XPCI"))
			{
				XPCI = new XPCI(bs);
			}
			else
			{
				//CommonREFR makes this no use now
				//	System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}

	}

}
