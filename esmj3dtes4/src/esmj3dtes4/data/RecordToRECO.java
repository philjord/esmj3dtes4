package esmj3dtes4.data;

import java.util.HashSet;
import java.util.List;

import esmj3d.data.shared.records.LAND;
import esmj3d.data.shared.records.LTEX;
import esmj3d.data.shared.records.RECO;
import esmj3dtes4.data.records.ACHR;
import esmj3dtes4.data.records.ACTI;
import esmj3dtes4.data.records.ALCH;
import esmj3dtes4.data.records.AMMO;
import esmj3dtes4.data.records.ANIO;
import esmj3dtes4.data.records.APPA;
import esmj3dtes4.data.records.ARMO;
import esmj3dtes4.data.records.BOOK;
import esmj3dtes4.data.records.BSGN;
import esmj3dtes4.data.records.CELL;
import esmj3dtes4.data.records.CLAS;
import esmj3dtes4.data.records.CLMT;
import esmj3dtes4.data.records.CLOT;
import esmj3dtes4.data.records.CONT;
import esmj3dtes4.data.records.CREA;
import esmj3dtes4.data.records.CSTY;
import esmj3dtes4.data.records.DIAL;
import esmj3dtes4.data.records.DOOR;
import esmj3dtes4.data.records.EFSH;
import esmj3dtes4.data.records.ENCH;
import esmj3dtes4.data.records.EYES;
import esmj3dtes4.data.records.FACT;
import esmj3dtes4.data.records.FLOR;
import esmj3dtes4.data.records.FURN;
import esmj3dtes4.data.records.GLOB;
import esmj3dtes4.data.records.GMST;
import esmj3dtes4.data.records.GRAS;
import esmj3dtes4.data.records.HAIR;
import esmj3dtes4.data.records.IDLE;
import esmj3dtes4.data.records.INGR;
import esmj3dtes4.data.records.KEYM;
import esmj3dtes4.data.records.LIGH;
import esmj3dtes4.data.records.LSCR;
import esmj3dtes4.data.records.LVLC;
import esmj3dtes4.data.records.LVLI;
import esmj3dtes4.data.records.LVSP;
import esmj3dtes4.data.records.MGEF;
import esmj3dtes4.data.records.MISC;
import esmj3dtes4.data.records.NPC_;
import esmj3dtes4.data.records.PACK;
import esmj3dtes4.data.records.QUST;
import esmj3dtes4.data.records.RACE;
import esmj3dtes4.data.records.REFR;
import esmj3dtes4.data.records.REGN;
import esmj3dtes4.data.records.SBSP;
import esmj3dtes4.data.records.SCPT;
import esmj3dtes4.data.records.SGST;
import esmj3dtes4.data.records.SKIL;
import esmj3dtes4.data.records.SLGM;
import esmj3dtes4.data.records.SOUN;
import esmj3dtes4.data.records.SPEL;
import esmj3dtes4.data.records.STAT;
import esmj3dtes4.data.records.TREE;
import esmj3dtes4.data.records.WATR;
import esmj3dtes4.data.records.WEAP;
import esmj3dtes4.data.records.WTHR;
import esmj3dtes4.data.subrecords.LVLO;
import esmmanager.common.data.record.IRecordStore;
import esmmanager.common.data.record.Record;
 

public class RecordToRECO
{
	//NOTE testing only
	public static void makeRECOsForCELL(IRecordStore master, Record cellRecord, List<Record> children)
	{
		new esmj3dtes4.data.records.CELL(cellRecord);

		for (Record record : children)
		{
			try
			{
				if (record.getRecordType().equals("REFR"))
				{
					REFR refr = new REFR(record);
					makeREFR(refr, master);
				}
				else if (record.getRecordType().equals("ACHR"))
				{
					ACHR achr = new ACHR(record);
					makeACHR(achr, master);
				}
				else if (record.getRecordType().equals("LAND"))
				{
					LAND land = new LAND(record);
				}
				else if (record.getRecordType().equals("PGRD"))
				{

				}
				else if (record.getRecordType().equals("PGRE"))
				{

				}
				else if (record.getRecordType().equals("NAVM"))
				{

				}
				else
				{
					System.out.println("Record type not converted to RECO " + record.getRecordType());
				}
			}
			catch (NullPointerException e)
			{
				// probably just a bum pointer in the ESM
			}

		}
	}

	private static RECO makeREFR(REFR refr, IRecordStore master)
	{
		Record baseRecord = master.getRecord(refr.NAME.formId);

		RECO reco = makeRECO(baseRecord);
		if (reco instanceof LVLC)
		{
			reco = makeLVLC((LVLC) reco, master);
		}

		return reco;

	}

	private static RECO makeACHR(ACHR achr, IRecordStore master)
	{
		Record baseRecord = master.getRecord(achr.NAME.formId);
		if (baseRecord.getRecordType().equals("NPC_"))
		{
			return new NPC_(baseRecord);
		}
		else
		{
			System.out.println("ACHR record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	private static RECO makeLVLC(LVLC lvlc, IRecordStore master)
	{
		// TODO: randomly picked for now
		LVLO[] LVLOs = lvlc.LVLOs;
		if (LVLOs.length > 0)
		{
			int idx = (int) (Math.random() * LVLOs.length);
			idx = idx == LVLOs.length ? 0 : idx;

			Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);

			if (baseRecord.getRecordType().equals("CREA"))
			{
				return new CREA(baseRecord);
			}
			else if (baseRecord.getRecordType().equals("LVLC"))
			{
				// it is in fact a pointer across to another leveled creature (LVLC)
				return new LVLC(baseRecord);
			}
			else if (baseRecord.getRecordType().equals("NPC_"))
			{
				// it is in fact a pointer across to another leveled creature (LVLC)
				return new NPC_(baseRecord);
			}
			else
			{
				System.out.println("LVLC record type not converted to j3d " + baseRecord.getRecordType());
			}
		}

		return null;
	}
	public static RECO makeLVLI(LVLI lvli, IRecordStore master)
	{
		// TODO: randomly picked for now
		LVLO[] LVLOs = lvli.LVLOs;

		int idx = (int) (Math.random() * LVLOs.length);
		idx = idx == LVLOs.length ? 0 : idx;

		Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);

		if (baseRecord.getRecordType().equals("NPC_"))
		{
			// it is in fact a pointer across to another leveled creature (LVLC)
			return new NPC_(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLI"))
		{
			// it is in fact a pointer across to another leveled character (LVLN)
			makeLVLI(new LVLI(baseRecord), master);
		}
		else
		{
			System.out.println("LVLI record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	public static RECO makeRECO(Record baseRecord)
	{

		if (baseRecord.getRecordType().equals("GMST"))
		{
			return new GMST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("GLOB"))
		{
			return new GLOB(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CLAS"))
		{
			return new CLAS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FACT"))
		{
			return new FACT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("HAIR"))
		{
			return new HAIR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("EYES"))
		{
			return new EYES(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("RACE"))
		{
			return new RACE(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SOUN"))
		{
			return new SOUN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SKIL"))
		{
			return new SKIL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MGEF"))
		{
			return new MGEF(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SCPT"))
		{
			return new SCPT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LTEX"))
		{
			return new LTEX(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ENCH"))
		{
			return new ENCH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SPEL"))
		{
			return new SPEL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("BSGN"))
		{
			return new BSGN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ACTI"))
		{
			return new ACTI(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("APPA"))
		{
			return new APPA(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ARMO"))
		{
			return new ARMO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("BOOK"))
		{
			return new BOOK(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CLOT"))
		{
			return new CLOT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CONT"))
		{
			return new CONT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DOOR"))
		{
			return new DOOR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("INGR"))
		{
			return new INGR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LIGH"))
		{
			return new LIGH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MISC"))
		{
			return new MISC(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("STAT"))
		{
			return new STAT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("GRAS"))
		{
			return new GRAS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("TREE"))
		{
			return new TREE(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FLOR"))
		{
			return new FLOR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FURN"))
		{
			return new FURN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("WEAP"))
		{
			return new WEAP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("AMMO"))
		{
			return new AMMO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("NPC_"))
		{
			return new NPC_(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CREA"))
		{
			return new CREA(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SLGM"))
		{
			return new SLGM(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("KEYM"))
		{
			return new KEYM(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ALCH"))
		{
			return new ALCH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SBSP"))
		{
			return new SBSP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SGST"))
		{
			return new SGST(baseRecord);
		}

		else if (baseRecord.getRecordType().equals("WTHR"))
		{
			return new WTHR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CLMT"))
		{
			return new CLMT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("REGN"))
		{
			return new REGN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DIAL"))
		{
			return new DIAL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("QUST"))
		{
			return new QUST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IDLE"))
		{
			return new IDLE(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("PACK"))
		{
			return new PACK(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CSTY"))
		{
			return new CSTY(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LSCR"))
		{
			return new LSCR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ANIO"))
		{
			return new ANIO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("WATR"))
		{
			return new WATR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("EFSH"))
		{
			return new EFSH(baseRecord);
		}
		
		
		//********************* special cases below, called for testing only
		else if (baseRecord.getRecordType().equals("LVLI"))
		{
			return new LVLI(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVSP"))
		{
			return new LVSP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLC"))
		{
			return new LVLC(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CELL"))
		{
			return new CELL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("GRUP"))
		{
			//return new GRUP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("REFR"))
		{
			return new REFR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ACHR"))
		{
			return new ACHR(baseRecord);
		}
		else
		{
			if (!constructorsShowen.contains(baseRecord.getRecordType()))
			{
				System.out.println("else if (baseRecord.getRecordType().equals(\"" + baseRecord.getRecordType() + "\"))");
				System.out.println("{");
				System.out.println("return new " + baseRecord.getRecordType() + "(baseRecord);");
				System.out.println("}");
				constructorsShowen.add(baseRecord.getRecordType());
			}
		}
		return null;
	}

	private static HashSet<String> constructorsShowen = new HashSet<String>();

}
