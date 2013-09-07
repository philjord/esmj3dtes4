package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonREFR;
import esmj3dtes4.data.subrecords.XACT;
import esmj3dtes4.data.subrecords.XCNT;
import esmj3dtes4.data.subrecords.XESP_b;
import esmj3dtes4.data.subrecords.XLCM;
import esmj3dtes4.data.subrecords.XLOD;
import esmj3dtes4.data.subrecords.XPCI;
import esmj3dtes4.data.subrecords.XSED;

public class REFR extends CommonREFR
{

	public XSED XSED;

	public XLOD XLOD;//not in tes5

	public XPCI XPCI;

	public XESP_b XESP;

	public XLCM XLCM;

	public XACT XACT;

	public XCNT XCNT;


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
			else if (sr.getSubrecordType().equals("XLOD"))
			{
				XLOD = new XLOD(bs);
			}
			else if (sr.getSubrecordType().equals("XPCI"))
			{
				XPCI = new XPCI(bs);
			}
			else if (sr.getSubrecordType().equals("XESP"))
			{
				XESP = new XESP_b(bs);
			}
			else if (sr.getSubrecordType().equals("XLCM"))
			{
				XLCM = new XLCM(bs);
			}
			else if (sr.getSubrecordType().equals("XACT"))
			{
				XACT = new XACT(bs);
			}
			else if (sr.getSubrecordType().equals("XCNT"))
			{
				XCNT = new XCNT(bs);
			}
			else
			{
				//CommonREFR makes this no use now
				//	System.out.println("unhandled : " + sr.getSubrecordType() + " in " + recordData);
			}
		}

	}

}
