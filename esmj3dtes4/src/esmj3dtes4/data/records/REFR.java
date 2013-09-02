package esmj3dtes4.data.records;

import java.util.ArrayList;

import utils.ESMByteConvert;
import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.InstRECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.XTEL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.FNAM;
import esmj3dtes4.data.subrecords.TNAM_c;
import esmj3dtes4.data.subrecords.XACT;
import esmj3dtes4.data.subrecords.XCNT;
import esmj3dtes4.data.subrecords.XESP_b;
import esmj3dtes4.data.subrecords.XLCM;
import esmj3dtes4.data.subrecords.XLOC;
import esmj3dtes4.data.subrecords.XLOD;
import esmj3dtes4.data.subrecords.XPCI;
import esmj3dtes4.data.subrecords.XRNK;
import esmj3dtes4.data.subrecords.XSED;

public class REFR extends InstRECO
{
	public FormID NAME;

	public ZString EDID;

	public boolean XMRK = false;

	public FNAM FNAM;

	public FormID XOWN;

	public XRNK XRNK;

	public FormID XGLB;

	public XTEL XTEL;

	public FormID XTRG;

	public XSED XSED;

	public XLOD XLOD;

	public XLOC XLOC;

	public XPCI XPCI;

	public XESP_b XESP;

	public XLCM XLCM;

	public FormID XRTM;

	public XACT XACT;

	public XCNT XCNT;

	public LString FULL;

	public TNAM_c TNAM;

	public boolean ONAM = false;

	public REFR(Record recordData)
	{
		super(recordData);

		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("NAME"))
			{
				NAME = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("XMRK"))
			{
				XMRK = true;
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
				FNAM = new FNAM(bs);
			}
			else if (sr.getSubrecordType().equals("XOWN"))
			{
				XOWN = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XRNK"))
			{
				XRNK = new XRNK(bs);
			}
			else if (sr.getSubrecordType().equals("XGLB"))
			{
				XGLB = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XSCL"))
			{
				scale = ESMByteConvert.extractFloat(bs, 0);
			}
			else if (sr.getSubrecordType().equals("XTEL"))
			{
				XTEL = new XTEL(bs);
			}
			else if (sr.getSubrecordType().equals("XTRG"))
			{
				XTRG = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XSED"))
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
			else if (sr.getSubrecordType().equals("XLOC"))
			{
				XLOC = new XLOC(bs);
			}
			else if (sr.getSubrecordType().equals("XESP"))
			{
				XESP = new XESP_b(bs);
			}
			else if (sr.getSubrecordType().equals("XLCM"))
			{
				XLCM = new XLCM(bs);
			}
			else if (sr.getSubrecordType().equals("XRTM"))
			{
				XRTM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XACT"))
			{
				XACT = new XACT(bs);
			}
			else if (sr.getSubrecordType().equals("XCNT"))
			{
				XCNT = new XCNT(bs);
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new LString(bs);
			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
				TNAM = new TNAM_c(bs);
			}
			else if (sr.getSubrecordType().equals("ONAM"))
			{
				ONAM = true;
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				this.extractInstData(bs);
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in " + recordData);
			}
		}

	}

}
