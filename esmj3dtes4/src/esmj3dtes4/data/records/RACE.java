package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.DESC;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODB;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.ATTR;
import esmj3dtes4.data.subrecords.CNAM_b;
import esmj3dtes4.data.subrecords.DNAM;
import esmj3dtes4.data.subrecords.FGGA;
import esmj3dtes4.data.subrecords.FGGS;
import esmj3dtes4.data.subrecords.FGTS;
import esmj3dtes4.data.subrecords.HNAM_b;
import esmj3dtes4.data.subrecords.INDX;
import esmj3dtes4.data.subrecords.PNAM;
import esmj3dtes4.data.subrecords.SNAM_c;
import esmj3dtes4.data.subrecords.UNAM;
import esmj3dtes4.data.subrecords.VNAM;
import esmj3dtes4.data.subrecords.XNAM;

public class RACE extends RECO
{
	public ZString EDID = null;

	public LString FULL = null;

	public DESC DESC = null;

	public FormID[] SPLOs = null;

	public XNAM XNAM = null;

	public DATA DATA = null;

	public DNAM DNAM = null;

	public CNAM_b CNAM = null;

	public ATTR ATTR = null;

	public boolean NAM0 = false;

	// each index is an int idx and may be followed by a modl/modb/icon 
	public INDX[] INDXs = null;

	public MODL[] MODLs = null;

	public MODB[] MODBs = null;

	public ZString[] ICONs = null;

	public boolean NAM1 = false;

	public boolean MNAM = false;

	public boolean FNAM = false;

	public HNAM_b HNAM = null;

	public FGGS FGGS = null;

	public FGGA FGGA = null;

	public FGTS FGTS = null;

	public SNAM_c SNAM = null;

	public VNAM VNAM = null;

	public PNAM PNAM = null;

	public UNAM UNAM = null;

	//data data

	public RACE(Record recordData)
	{
		super(recordData);

		ArrayList<FormID> SPLOsl = new ArrayList<FormID>();
		ArrayList<INDX> INDXsl = new ArrayList<INDX>();
		ArrayList<MODL> MODLsl = new ArrayList<MODL>();
		ArrayList<MODB> MODBsl = new ArrayList<MODB>();
		ArrayList<ZString> ICONsl = new ArrayList<ZString>();

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
			else if (sr.getSubrecordType().equals("DESC"))
			{
				DESC = new DESC(bs);
			}
			else if (sr.getSubrecordType().equals("SPLO"))
			{
				SPLOsl.add(new FormID(bs));
			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{
				XNAM = new XNAM(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{
				DNAM = new DNAM(bs);
			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{
				CNAM = new CNAM_b(bs);
			}
			else if (sr.getSubrecordType().equals("ATTR"))
			{
				ATTR = new ATTR(bs);
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
				NAM0 = true;
			}
			else if (sr.getSubrecordType().equals("INDX"))
			{
				INDXsl.add(new INDX(bs));
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODLsl.add(new MODL(bs));
			}
			else if (sr.getSubrecordType().equals("MODB"))
			{
				MODBsl.add(new MODB(bs));
			}
			else if (sr.getSubrecordType().equals("ICON"))
			{
				ICONsl.add(new ZString(bs));
			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{
				NAM1 = true;
			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
				MNAM = true;
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
				FNAM = true;
			}
			else if (sr.getSubrecordType().equals("HNAM"))
			{
				HNAM = new HNAM_b(bs);
			}
			else if (sr.getSubrecordType().equals("ENAM"))
			{

			}
			else if (sr.getSubrecordType().equals("FGGS"))
			{
				FGGS = new FGGS(bs);
			}
			else if (sr.getSubrecordType().equals("FGGA"))
			{
				FGGA = new FGGA(bs);
			}
			else if (sr.getSubrecordType().equals("FGTS"))
			{
				FGTS = new FGTS(bs);
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
				SNAM = new SNAM_c(bs);
			}
			else if (sr.getSubrecordType().equals("VNAM"))
			{
				VNAM = new VNAM(bs);
			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{
				PNAM = new PNAM(bs);
			}
			else if (sr.getSubrecordType().equals("UNAM"))
			{
				UNAM = new UNAM(bs);
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

			// transfer to arrays
			SPLOs = new FormID[SPLOsl.size()];
			SPLOsl.toArray(SPLOs);

			INDXs = new INDX[INDXsl.size()];
			INDXsl.toArray(INDXs);

			MODLs = new MODL[MODLsl.size()];
			MODLsl.toArray(MODLs);

			MODBs = new MODB[MODBsl.size()];
			MODBsl.toArray(MODBs);

			ICONs = new ZString[ICONsl.size()];
			ICONsl.toArray(ICONs);

			//extract data data
			if (DATA != null)
			{

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
