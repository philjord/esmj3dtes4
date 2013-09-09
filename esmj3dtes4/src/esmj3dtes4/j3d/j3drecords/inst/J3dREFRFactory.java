package esmj3dtes4.j3d.j3drecords.inst;

import utils.source.MeshSource;
import utils.source.SoundSource;
import utils.source.TextureSource;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import esmj3d.j3d.BethRenderSettings;
import esmj3d.j3d.j3drecords.inst.J3dRECODynInst;
import esmj3d.j3d.j3drecords.inst.J3dRECOInst;
import esmj3d.j3d.j3drecords.inst.J3dRECOStatInst;
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

	private static J3dRECODynInst makeJ3dRECOInstFader(REFR refr, RECO reco, ZString nifFileName, boolean makePhys, MeshSource meshSource,
			TextureSource textureSource)
	{
		J3dRECODynInst j3dinst = new J3dRECODynInst(refr, true, makePhys);
		j3dinst.setJ3dRECOType(new J3dRECOTypeGeneral(reco, nifFileName.str, makePhys, meshSource, textureSource));
		return j3dinst;
	}

	private static J3dRECOStatInst makeJ3dRECOStatInst(REFR refr, RECO reco, ZString nifFileName, boolean makePhys, MeshSource meshSource,
			TextureSource textureSource)
	{
		J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, makePhys);
		j3dinst.setJ3dRECOType(new J3dRECOTypeGeneral(reco, nifFileName.str, makePhys, meshSource, textureSource));
		return j3dinst;
	}

	public static J3dRECOInst makeJ3DRefer(REFR refr, boolean makePhys, IRecordStore master, MeshSource meshSource,
			TextureSource textureSource, SoundSource soundSource)
	{

		Record baseRecord = master.getRecord(refr.NAME.formId);

		if (baseRecord.getRecordType().equals("STAT"))
		{
			STAT stat = new STAT(baseRecord);
			if (!stat.isFlagSet(0x00800000) || BethRenderSettings.isShowEditorMarkers())
			{
				return makeJ3dRECOStatInst(refr, stat, stat.MODL.model, makePhys, meshSource, textureSource);
			}
			else
			{
				return null;
			}
		}
		else if (baseRecord.getRecordType().equals("APPA"))
		{
			APPA appa = new APPA(baseRecord);
			return makeJ3dRECOInstFader(refr, appa, appa.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("FURN"))
		{
			FURN furn = new FURN(baseRecord);
			return makeJ3dRECOStatInst(refr, furn, furn.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("MISC"))
		{
			MISC misc = new MISC(baseRecord);
			return makeJ3dRECOInstFader(refr, misc, misc.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("CONT"))
		{
			CONT cont = new CONT(baseRecord);
			return makeJ3dRECOStatInst(refr, cont, cont.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("CLOT"))
		{
			CLOT clot = new CLOT(baseRecord);
			return makeJ3dRECOInstFader(refr, clot, clot.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("FLOR"))
		{
			FLOR flor = new FLOR(baseRecord);
			return makeJ3dRECOStatInst(refr, flor, flor.MODL.model, makePhys, meshSource, textureSource);
		}

		else if (baseRecord.getRecordType().equals("BOOK"))
		{
			BOOK book = new BOOK(baseRecord);
			return makeJ3dRECOInstFader(refr, book, book.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("ALCH"))
		{
			ALCH alch = new ALCH(baseRecord);
			return makeJ3dRECOInstFader(refr, alch, alch.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("INGR"))
		{
			INGR ingr = new INGR(baseRecord);
			return makeJ3dRECOInstFader(refr, ingr, ingr.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("ACTI"))
		{
			ACTI acti = new ACTI(baseRecord);
			return makeJ3dRECOStatInst(refr, acti, acti.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("WEAP"))
		{
			WEAP weap = new WEAP(baseRecord);
			return makeJ3dRECOInstFader(refr, weap, weap.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("AMMO"))
		{
			AMMO ammo = new AMMO(baseRecord);
			return makeJ3dRECOInstFader(refr, ammo, ammo.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("KEYM"))
		{
			KEYM keym = new KEYM(baseRecord);
			return makeJ3dRECOInstFader(refr, keym, keym.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("SLGM"))
		{
			SLGM slgm = new SLGM(baseRecord);
			return makeJ3dRECOInstFader(refr, slgm, slgm.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("ARMO"))
		{
			ARMO armo = new ARMO(baseRecord);
			return makeJ3dRECOInstFader(refr, armo, armo.MODL.model, makePhys, meshSource, textureSource);
		}
		else if (baseRecord.getRecordType().equals("DOOR"))
		{

			DOOR door = new DOOR(baseRecord);
			J3dDOOR j3dDoor = new J3dDOOR(door, door.MODL.model.str, makePhys, meshSource, textureSource);
			J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, makePhys);
			j3dinst.setJ3dRECOType(j3dDoor);
			return j3dinst;
		}
		else if (baseRecord.getRecordType().equals("SBSP"))
		{
			//SBSP sbsp = new SBSP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LIGH"))
		{
			LIGH ligh = new LIGH(baseRecord);
			J3dLIGH j3dLIGH = new J3dLIGH(ligh, makePhys, meshSource, textureSource);
			J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, makePhys);
			j3dinst.setJ3dRECOType(j3dLIGH);
			return j3dinst;
		}
		else if (baseRecord.getRecordType().equals("TREE"))
		{
			if (!makePhys)
			{
				TREE tree = new TREE(baseRecord);
				J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, false, makePhys);
				j3dinst.addNodeChild(new J3dTREE(tree, textureSource));
				return j3dinst;
			}
		}
		else if (baseRecord.getRecordType().equals("SOUN"))
		{
			if (!makePhys)
			{
				SOUN soun = new SOUN(baseRecord);
				J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, makePhys);
				j3dinst.addNodeChild(new J3dSOUN(soun, master, soundSource));
				return j3dinst;
			}
		}
		else if (baseRecord.getRecordType().equals("LVLC"))
		{
			if (!makePhys)
			{
				LVLC lvlc = new LVLC(baseRecord);
				J3dRECODynInst j3dinst = new J3dRECODynInst(refr, makePhys);
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
