package esmj3dtes4.j3d.j3drecords.inst;

import utils.source.MeshSource;
import utils.source.SoundSource;
import utils.source.TextureSource;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.j3d.BethRenderSettings;
import esmj3d.j3d.j3drecords.inst.J3dRECODynInst;
import esmj3d.j3d.j3drecords.inst.J3dRECOInst;
import esmj3d.j3d.j3drecords.inst.J3dRECOStatInst;
import esmj3d.j3d.j3drecords.type.J3dCONT;
import esmj3d.j3d.j3drecords.type.J3dDOOR;
import esmj3d.j3d.j3drecords.type.J3dRECOType;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeGeneral;
import esmj3dtes4.data.records.ACTI;
import esmj3dtes4.data.records.ALCH;
import esmj3dtes4.data.records.AMMO;
import esmj3dtes4.data.records.APPA;
import esmj3dtes4.data.records.ARMO;
import esmj3dtes4.data.records.BOOK;
import esmj3dtes4.data.records.CLOT;
import esmj3dtes4.data.records.CONT;
import esmj3dtes4.data.records.CREA;
import esmj3dtes4.data.records.DOOR;
import esmj3dtes4.data.records.FLOR;
import esmj3dtes4.data.records.FURN;
import esmj3dtes4.data.records.INGR;
import esmj3dtes4.data.records.KEYM;
import esmj3dtes4.data.records.LIGH;
import esmj3dtes4.data.records.LVLC;
import esmj3dtes4.data.records.MISC;
import esmj3dtes4.data.records.NPC_;
import esmj3dtes4.data.records.REFR;
import esmj3dtes4.data.records.SLGM;
import esmj3dtes4.data.records.SOUN;
import esmj3dtes4.data.records.STAT;
import esmj3dtes4.data.records.TREE;
import esmj3dtes4.data.records.WEAP;
import esmj3dtes4.data.subrecords.LVLO;
import esmj3dtes4.j3d.j3drecords.type.J3dCREA;
import esmj3dtes4.j3d.j3drecords.type.J3dLIGH;
import esmj3dtes4.j3d.j3drecords.type.J3dNPC_;
import esmj3dtes4.j3d.j3drecords.type.J3dSOUN;
import esmj3dtes4.j3d.j3drecords.type.J3dTREE;

public class J3dREFRFactory
{

	private static J3dRECODynInst makeJ3dRECODynInst(REFR refr, RECO reco, MODL modl, boolean makePhys, MeshSource meshSource,
			TextureSource textureSource)
	{
		if (modl != null)
		{
			J3dRECODynInst j3dinst = new J3dRECODynInst(refr, true, makePhys);
			j3dinst.setJ3dRECOType(new J3dRECOTypeGeneral(reco, modl.model.str, makePhys, meshSource, textureSource));
			return j3dinst;
		}
		else
		{
			System.out.println("null modl there " + reco);
			return null;
		}
	}

	private static J3dRECOStatInst makeJ3dRECOStatInst(REFR refr, RECO reco, MODL modl, boolean makePhys, MeshSource meshSource,
			TextureSource textureSource)
	{
		if (modl != null)
		{
			J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, true, makePhys);
			j3dinst.setJ3dRECOType(new J3dRECOTypeGeneral(reco, modl.model.str, makePhys, meshSource, textureSource));
			return j3dinst;
		}
		else
		{
			System.out.println("null modl there " + reco);
			return null;
		}
	}

	public static J3dRECOInst makeJ3DRefer(REFR refr, boolean makePhys, boolean noFade, IRecordStore master, MeshSource meshSource,
			TextureSource textureSource, SoundSource soundSource)
	{
		Record baseRecord = master.getRecord(refr.NAME.formId);

		if (baseRecord.getRecordType().equals("STAT"))
		{
			STAT stat = new STAT(baseRecord);
			if (!stat.isFlagSet(0x00800000) || BethRenderSettings.isShowEditorMarkers())
			{
				//distant stat no fade
				if (noFade)
				{
					return new J3dRECOStatInst(refr,
							new J3dRECOTypeGeneral(stat, stat.MODL.model.str, makePhys, meshSource, textureSource), false, makePhys);
				}
				else
				{
					return makeJ3dRECOStatInst(refr, stat, stat.MODL, makePhys, meshSource, textureSource);
				}
			}
			else
			{
				return null;
			}
		}
		else if (baseRecord.getRecordType().equals("APPA"))
		{
			APPA appa = new APPA(baseRecord);
			return makeJ3dRECODynInst(refr, appa, appa.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("FURN"))
		{
			FURN furn = new FURN(baseRecord);
			return makeJ3dRECOStatInst(refr, furn, furn.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("MISC"))
		{
			MISC misc = new MISC(baseRecord);
			return makeJ3dRECODynInst(refr, misc, misc.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("CONT"))
		{
			return new J3dRECOStatInst(refr, new J3dCONT(new CONT(baseRecord), makePhys, meshSource, textureSource), true, makePhys);
		}
		else if (baseRecord.getRecordType().equals("CLOT"))
		{
			CLOT clot = new CLOT(baseRecord);
			return makeJ3dRECODynInst(refr, clot, clot.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("FLOR"))
		{
			FLOR flor = new FLOR(baseRecord);
			return makeJ3dRECOStatInst(refr, flor, flor.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("BOOK"))
		{
			BOOK book = new BOOK(baseRecord);
			return makeJ3dRECODynInst(refr, book, book.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("ALCH"))
		{
			ALCH alch = new ALCH(baseRecord);
			return makeJ3dRECODynInst(refr, alch, alch.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("INGR"))
		{
			INGR ingr = new INGR(baseRecord);
			return makeJ3dRECODynInst(refr, ingr, ingr.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("ACTI"))
		{
			ACTI acti = new ACTI(baseRecord);
			return makeJ3dRECOStatInst(refr, acti, acti.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("WEAP"))
		{
			WEAP weap = new WEAP(baseRecord);
			return makeJ3dRECODynInst(refr, weap, weap.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("AMMO"))
		{
			AMMO ammo = new AMMO(baseRecord);
			return makeJ3dRECODynInst(refr, ammo, ammo.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("KEYM"))
		{
			KEYM keym = new KEYM(baseRecord);
			return makeJ3dRECODynInst(refr, keym, keym.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("SLGM"))
		{
			SLGM slgm = new SLGM(baseRecord);
			return makeJ3dRECODynInst(refr, slgm, slgm.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("ARMO"))
		{
			ARMO armo = new ARMO(baseRecord);
			return makeJ3dRECODynInst(refr, armo, armo.MODL, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("DOOR"))
		{
			return new J3dRECOStatInst(refr, new J3dDOOR(new DOOR(baseRecord), makePhys, meshSource, textureSource), true, makePhys);
		}
		else if (baseRecord.getRecordType().equals("LIGH"))
		{
			return new J3dRECOStatInst(refr, new J3dLIGH(new LIGH(baseRecord), makePhys, meshSource, textureSource), true, makePhys);
		}
		else if (baseRecord.getRecordType().equals("TREE"))
		{
			if (!makePhys)
			{
				return new J3dRECOStatInst(refr, new J3dTREE(new TREE(baseRecord), textureSource), false, makePhys);
			}
		}
		else if (baseRecord.getRecordType().equals("SOUN"))
		{
			if (!makePhys)
			{
				return new J3dRECOStatInst(refr, new J3dSOUN(new SOUN(baseRecord), master, soundSource), false, makePhys);
			}
		}
		else if (baseRecord.getRecordType().equals("SBSP"))
		{
			//SBSP sbsp = new SBSP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLC"))
		{
			if (!makePhys)
			{
				LVLC lvlc = new LVLC(baseRecord);
				J3dRECODynInst j3dinst = new J3dRECODynInst(refr, false, makePhys);
				j3dinst.setJ3dRECOType(makeLVLC(lvlc, master, meshSource, textureSource, soundSource));
				return j3dinst;
			}
		}
		else
		{
			System.out.println("REFR record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	/** TODO: Note does not bother with teh ACRE or ACHR system, but it should
	 * 
	 * @param lvlc
	 * @param master
	 * @param meshSource
	 * @param textureSource
	 * @param soundSource
	 * @return
	 */
	protected static J3dRECOType makeLVLC(LVLC lvlc, IRecordStore master, MeshSource meshSource, TextureSource textureSource,
			SoundSource soundSource)
	{
		// TODO: randomly picked for now
		LVLO[] LVLOs = lvlc.LVLOs;

		int idx = (int) (Math.random() * LVLOs.length);
		idx = idx == LVLOs.length ? 0 : idx;

		Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);

		if (baseRecord.getRecordType().equals("LVLC"))
		{
			// it is in fact a pointer across to another leveled creature (LVLC)
			LVLC lvlc2 = new LVLC(baseRecord);
			return makeLVLC(lvlc2, master, meshSource, textureSource, soundSource);
		}
		else if (baseRecord.getRecordType().equals("CREA"))
		{
			CREA crea = new CREA(baseRecord);
			return new J3dCREA(crea, master, meshSource, textureSource, soundSource);
		}
		else if (baseRecord.getRecordType().equals("NPC_"))
		{
			// it is in fact a pointer across to another leveled creature (LVLC)
			NPC_ npc_ = new NPC_(baseRecord);
			return new J3dNPC_(npc_, master, meshSource, textureSource, soundSource);
		}
		else
		{
			System.out.println("LVLC record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}
}
