package esmj3dtes4.data.records;

import java.util.ArrayList;

import tools.io.ESMByteConvert;
import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.DESC;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODB;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.DNAM;
import esmj3dtes4.data.subrecords.FGGA;
import esmj3dtes4.data.subrecords.FGGS;
import esmj3dtes4.data.subrecords.FGTS;
import esmj3dtes4.data.subrecords.INDX;
import esmj3dtes4.data.subrecords.PNAM;
import esmj3dtes4.data.subrecords.SNAM_c;
import esmj3dtes4.data.subrecords.UNAM;
import esmj3dtes4.data.subrecords.VNAM;

public class RACE extends RECO
{
	public ZString EDID = null;

	public LString FULL = null;

	public DESC DESC = null;

	public ArrayList<FormID> SPLOs = new ArrayList<FormID>();

	public XNAM XNAM = null;

	public DATA DATA = null;

	public DNAM DNAM = null;

	public CNAM CNAM = null;

	public ATTR ATTR = null;

	public boolean NAM0 = false;

	// each index is an int idx and may be followed by a modl/modb/icon 
	public ArrayList<INDX> INDXs = new ArrayList<INDX>();

	public ArrayList<MODL> MODLs = new ArrayList<MODL>();

	public ArrayList<MODB> MODBs = new ArrayList<MODB>();

	public ArrayList<ZString> ICONs = new ArrayList<ZString>();

	public boolean NAM1 = false;

	public boolean MNAM = false;

	public boolean FNAM = false;

	public HNAM HNAM = null;

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
				FULL = new LString(bs);
			}
			else if (sr.getType().equals("DESC"))
			{
				DESC = new DESC(bs);
			}
			else if (sr.getType().equals("SPLO"))
			{
				SPLOs.add(new FormID(bs));
			}
			else if (sr.getType().equals("XNAM"))
			{
				XNAM = new XNAM(bs);
			}
			else if (sr.getType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getType().equals("DNAM"))
			{
				DNAM = new DNAM(bs);
			}
			else if (sr.getType().equals("CNAM"))
			{
				CNAM = new CNAM(bs);
			}
			else if (sr.getType().equals("ATTR"))
			{
				ATTR = new ATTR(bs);
			}
			else if (sr.getType().equals("NAM0"))
			{
				NAM0 = true;
			}
			else if (sr.getType().equals("INDX"))
			{
				INDXs.add(new INDX(bs));
			}
			else if (sr.getType().equals("MODL"))
			{
				MODLs.add(new MODL(bs));
			}
			else if (sr.getType().equals("MODB"))
			{
				MODBs.add(new MODB(bs));
			}
			else if (sr.getType().equals("ICON"))
			{
				ICONs.add(new ZString(bs));
			}
			else if (sr.getType().equals("NAM1"))
			{
				NAM1 = true;
			}
			else if (sr.getType().equals("MNAM"))
			{
				MNAM = true;
			}
			else if (sr.getType().equals("FNAM"))
			{
				FNAM = true;
			}
			else if (sr.getType().equals("HNAM"))
			{
				HNAM = new HNAM(bs);
			}
			else if (sr.getType().equals("ENAM"))
			{

			}
			else if (sr.getType().equals("FGGS"))
			{
				FGGS = new FGGS(bs);
			}
			else if (sr.getType().equals("FGGA"))
			{
				FGGA = new FGGA(bs);
			}
			else if (sr.getType().equals("FGTS"))
			{
				FGTS = new FGTS(bs);
			}
			else if (sr.getType().equals("SNAM"))
			{
				SNAM = new SNAM_c(bs);
			}
			else if (sr.getType().equals("VNAM"))
			{
				VNAM = new VNAM(bs);
			}
			else if (sr.getType().equals("PNAM"))
			{
				PNAM = new PNAM(bs);
			}
			else if (sr.getType().equals("UNAM"))
			{
				UNAM = new UNAM(bs);
			}
			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}

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

	public class XNAM
	{
		public byte[] unknown;

		public XNAM(byte[] bytes)
		{
			unknown = bytes;
		}
	}

	public class CNAM
	{
		public byte unknown;

		public CNAM(byte[] bytes)
		{
			unknown = ESMByteConvert.extractByte(bytes, 0);
		}
	}

	public class ATTR
	{
		public byte[] unknown;

		public ATTR(byte[] bytes)
		{
			unknown = bytes;
		}
	}

	public class HNAM
	{
		public byte[] unknown;

		public HNAM(byte[] bytes)
		{
			unknown = bytes;
		}
	}
}
