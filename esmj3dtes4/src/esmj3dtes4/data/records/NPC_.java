package esmj3dtes4.data.records;

import java.util.ArrayList;

import utils.ESMByteConvert;
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
import esmj3dtes4.data.subrecords.FGGA;
import esmj3dtes4.data.subrecords.FGGS;
import esmj3dtes4.data.subrecords.FGTS;
import esmj3dtes4.data.subrecords.FNAM_b;
import esmj3dtes4.data.subrecords.HCLR;
import esmj3dtes4.data.subrecords.LNAM;
import esmj3dtes4.data.subrecords.SNAM;

public class NPC_ extends RECO
{
	public ZString EDID = null;

	public LString FULL = null;

	public MODL MODL = null;

	 

	public ACBS ACBS = null;

	public SNAM[] SNAMs = null;

	public FormID INAM = null;

	public FormID RNAM = null;

	public FormID[] SPLOs = null;

	public FormID SCRI = null;

	public CNTO[] CNTOs = null;

	public AIDT AIDT = null;

	public FormID[] PKIDs = null;

	public FormID CNAM = null;

	public DATA DATA = null;

	public FormID HNAM = null;

	public LNAM LNAM = null;

	public FormID ENAM = null;

	public HCLR HCLR = null;

	public FormID ZNAM = null;

	public FormID INGR = null;

	public FGGS FGGS = null;

	public FGGA FGGA = null;

	public FGTS FGTS = null;

	public FNAM_b FNAM = null;

	//data data
	public byte Armorer;

	public byte Athletics;

	public byte Blade;

	public byte Block;

	public byte Blunt;

	public byte HandtoHand;

	public byte HeavyArmor;

	public byte Alchemy;

	public byte Alteration;

	public byte Conjuration;

	public byte Destruction;

	public byte Illusion;

	public byte Mysticism;

	public byte Restoration;

	public byte Acrobatics;

	public byte LightArmor;

	public byte Marksman;

	public byte Mercantile;

	public byte Security;

	public byte Sneak;

	public byte Speechcraft;

	public int health;

	public byte Str;

	public byte Int;

	public byte Wil;

	public byte Agi;

	public byte Spd;

	public byte End;

	public byte Per;

	public byte Luc;

	public NPC_(Record recordData)
	{
		super(recordData);

		ArrayList<SNAM> SNAMsl = new ArrayList<SNAM>();
		ArrayList<FormID> SPLOsl = new ArrayList<FormID>();
		ArrayList<CNTO> CNTOsl = new ArrayList<CNTO>();
		ArrayList<FormID> PKIDsl = new ArrayList<FormID>();

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
				RNAM = new FormID(bs);
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
			else if (sr.getSubrecordType().equals("CNAM"))
			{
				CNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getSubrecordType().equals("HNAM"))
			{
				HNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("LNAM"))
			{
				LNAM = new LNAM(bs);
			}
			else if (sr.getSubrecordType().equals("ENAM"))
			{
				ENAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("HCLR"))
			{
				HCLR = new HCLR(bs);
			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{
				ZNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("INGR"))
			{
				INGR = new FormID(bs);
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
			else if (sr.getSubrecordType().equals("FNAM"))
			{
				FNAM = new FNAM_b(bs);
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

			//extract data data
			if (DATA != null)
			{
				Armorer = ESMByteConvert.extractByte(DATA.data, 0);
				Athletics = ESMByteConvert.extractByte(DATA.data, 1);
				Blade = ESMByteConvert.extractByte(DATA.data, 2);
				Block = ESMByteConvert.extractByte(DATA.data, 3);
				Blunt = ESMByteConvert.extractByte(DATA.data, 4);
				HandtoHand = ESMByteConvert.extractByte(DATA.data, 5);
				HeavyArmor = ESMByteConvert.extractByte(DATA.data, 6);
				Alchemy = ESMByteConvert.extractByte(DATA.data, 7);
				Alteration = ESMByteConvert.extractByte(DATA.data, 8);
				Conjuration = ESMByteConvert.extractByte(DATA.data, 9);
				Destruction = ESMByteConvert.extractByte(DATA.data, 10);
				Illusion = ESMByteConvert.extractByte(DATA.data, 11);
				Mysticism = ESMByteConvert.extractByte(DATA.data, 12);
				Restoration = ESMByteConvert.extractByte(DATA.data, 13);
				Acrobatics = ESMByteConvert.extractByte(DATA.data, 14);
				LightArmor = ESMByteConvert.extractByte(DATA.data, 15);
				Marksman = ESMByteConvert.extractByte(DATA.data, 16);
				Mercantile = ESMByteConvert.extractByte(DATA.data, 17);
				Security = ESMByteConvert.extractByte(DATA.data, 18);
				Sneak = ESMByteConvert.extractByte(DATA.data, 19);
				Speechcraft = ESMByteConvert.extractByte(DATA.data, 20);
				health = ESMByteConvert.extractInt(DATA.data, 21);
				Str = ESMByteConvert.extractByte(DATA.data, 25);
				Int = ESMByteConvert.extractByte(DATA.data, 26);
				Wil = ESMByteConvert.extractByte(DATA.data, 27);
				Agi = ESMByteConvert.extractByte(DATA.data, 28);
				Spd = ESMByteConvert.extractByte(DATA.data, 29);
				End = ESMByteConvert.extractByte(DATA.data, 30);
				Per = ESMByteConvert.extractByte(DATA.data, 31);
				Luc = ESMByteConvert.extractByte(DATA.data, 32);
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
