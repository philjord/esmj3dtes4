package esmj3dtes4.data.records;

import java.util.ArrayList;

import tools.io.ESMByteConvert;
import esmLoader.common.data.record.Record;
import esmLoader.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.CNTO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dtes4.data.subrecords.ACBS;
import esmj3dtes4.data.subrecords.AIDT;
import esmj3dtes4.data.subrecords.BNAM;
import esmj3dtes4.data.subrecords.CSCR;
import esmj3dtes4.data.subrecords.CSDC;
import esmj3dtes4.data.subrecords.CSDT;
import esmj3dtes4.data.subrecords.KFFZ;
import esmj3dtes4.data.subrecords.NIFT;
import esmj3dtes4.data.subrecords.NIFZ;
import esmj3dtes4.data.subrecords.RNAM;
import esmj3dtes4.data.subrecords.SNAM;
import esmj3dtes4.data.subrecords.TNAM_b;
import esmj3dtes4.data.subrecords.WNAM;

public class CREA extends RECO
{
	public ZString EDID = null;

	public LString FULL = null;

	public MODL MODL = null;

	public NIFZ NIFZ = null;

	public NIFT NIFT = null;

	public ACBS ACBS = null;

	public SNAM[] SNAMs = null;

	public FormID INAM = null;

	public RNAM RNAM = null;

	public FormID[] SPLOs = null;

	public FormID SCRI = null;

	public CNTO[] CNTOs = null;

	public AIDT AIDT = null;

	public FormID[] PKIDs = null;

	public DATA DATA = null;

	public FormID ZNAM = null;

	public CSCR CSCR = null;

	public CSDT[] CSDTs = null;

	public BNAM BNAM = null;

	public TNAM_b TNAM = null;

	public WNAM WNAM = null;

	public ZString NAM0 = null;

	public ZString NAM1 = null;

	public KFFZ KFFZ = null;

	//data data
	public byte unknown1;

	public byte combat;

	public byte magic;

	public int Soul;

	public int health;

	public int unknown2;

	public int damage;

	public byte Str;

	public byte Int;

	public byte Wil;

	public byte Agi;

	public byte Spd;

	public byte End;

	public byte Per;

	public byte Luc;

	public CREA(Record recordData)
	{
		super(recordData);
		ArrayList<SNAM> SNAMsl = new ArrayList<SNAM>();
		ArrayList<FormID> SPLOsl = new ArrayList<FormID>();
		ArrayList<CNTO> CNTOsl = new ArrayList<CNTO>();
		ArrayList<FormID> PKIDsl = new ArrayList<FormID>();
		ArrayList<CSDT> CSDTsl = new ArrayList<CSDT>();
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
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MODB"))
			{
				MODL.addMODBSub(bs);
			}
			else if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("NIFZ"))
			{
				NIFZ = new NIFZ(bs);
			}
			else if (sr.getSubrecordType().equals("NIFT"))
			{
				NIFT = new NIFT(bs);
			}
			else if (sr.getSubrecordType().equals("ACBS"))
			{
				ACBS = new ACBS(bs);
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
				SNAMsl.add(new SNAM(bs));
			}
			else if (sr.getSubrecordType().equals("INAM"))
			{
				INAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
				RNAM = new RNAM(bs);
			}
			else if (sr.getSubrecordType().equals("SPLO"))
			{
				SPLOsl.add(new FormID(bs));
			}
			else if (sr.getSubrecordType().equals("SCRI"))
			{
				SCRI = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("CNTO"))
			{
				CNTOsl.add(new CNTO(bs));
			}
			else if (sr.getSubrecordType().equals("AIDT"))
			{
				AIDT = new AIDT(bs);
			}
			else if (sr.getSubrecordType().equals("PKID"))
			{
				PKIDsl.add(new FormID(bs));
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{
				ZNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("CSCR"))
			{
				CSCR = new CSCR(bs);
			}
			else if (sr.getSubrecordType().equals("CSDT"))
			{
				CSDT csdt = new CSDT(bs);
				i++;
				Subrecord sr2 = subrecords.get(i);
				csdt.csdi = new FormID(sr2.getSubrecordData());
				i++;
				Subrecord sr3 = subrecords.get(i);
				csdt.csdc = new CSDC(sr3.getSubrecordData());
				CSDTsl.add(csdt);
			}
			else if (sr.getSubrecordType().equals("BNAM"))
			{
				BNAM = new BNAM(bs);
			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
				TNAM = new TNAM_b(bs);
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
				WNAM = new WNAM(bs);
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
				NAM0 = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{
				NAM1 = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("KFFZ"))
			{
				KFFZ = new KFFZ(bs);
			}

			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in " + recordData);
			}

			// transfer to arrays
			SNAMs = new SNAM[SNAMsl.size()];
			SNAMsl.toArray(SNAMs);

			SPLOs = new FormID[SPLOsl.size()];
			SPLOsl.toArray(SPLOs);

			CNTOs = new CNTO[CNTOsl.size()];
			CNTOsl.toArray(CNTOs);

			PKIDs = new FormID[PKIDsl.size()];
			PKIDsl.toArray(PKIDs);

			CSDTs = new CSDT[CSDTsl.size()];
			CSDTsl.toArray(CSDTs);

			//extract data data
			if (DATA != null)
			{
				unknown1 = ESMByteConvert.extractByte(DATA.data, 0);
				combat = ESMByteConvert.extractByte(DATA.data, 1);
				magic = ESMByteConvert.extractByte(DATA.data, 2);
				Soul = ESMByteConvert.extractShort(DATA.data, 3);
				health = ESMByteConvert.extractShort(DATA.data, 5);
				unknown2 = ESMByteConvert.extractShort(DATA.data, 7);
				damage = ESMByteConvert.extractShort(DATA.data, 9);
				Str = ESMByteConvert.extractByte(DATA.data, 11);
				Int = ESMByteConvert.extractByte(DATA.data, 12);
				Wil = ESMByteConvert.extractByte(DATA.data, 13);
				Agi = ESMByteConvert.extractByte(DATA.data, 14);
				Spd = ESMByteConvert.extractByte(DATA.data, 15);
				End = ESMByteConvert.extractByte(DATA.data, 16);
				Per = ESMByteConvert.extractByte(DATA.data, 17);
				Luc = ESMByteConvert.extractByte(DATA.data, 18);
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
