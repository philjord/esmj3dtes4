package esmj3dtes4.data.records;

import java.util.ArrayList;
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
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
import tools.io.ESMByteConvert;

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

		List<Subrecord> subrecords = recordData.getSubrecords();
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
				SPLOs.add(new FormID(bs));
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
				CNAM = new CNAM(bs);
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
				INDXs.add(new INDX(bs));
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODLs.add(new MODL(bs));
			}
			else if (sr.getSubrecordType().equals("MODB"))
			{
				MODBs.add(new MODB(bs));
			}
			else if (sr.getSubrecordType().equals("ICON"))
			{
				ICONs.add(new ZString(bs));
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
				HNAM = new HNAM(bs);
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
