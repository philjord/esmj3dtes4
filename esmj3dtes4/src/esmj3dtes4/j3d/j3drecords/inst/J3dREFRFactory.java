package esmj3dtes4.j3d.j3drecords.inst;

import org.jogamp.java3d.Node;

import esmio.common.data.record.IRecordStore;
import esmio.common.data.record.Record;
import esmj3d.data.shared.records.CommonREFR;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.j3d.BethRenderSettings;
import esmj3d.j3d.LODNif;
import esmj3d.j3d.j3drecords.inst.J3dRECOChaInst;
import esmj3d.j3d.j3drecords.inst.J3dRECODynInst;
import esmj3d.j3d.j3drecords.inst.J3dRECOInst;
import esmj3d.j3d.j3drecords.inst.J3dRECOStatInst;
import esmj3d.j3d.j3drecords.type.J3dCONT;
import esmj3d.j3d.j3drecords.type.J3dDOOR;
import esmj3d.j3d.j3drecords.type.J3dGeneralLIGH;
import esmj3d.j3d.j3drecords.type.J3dRECOType;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeActionable;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeCha;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeDynamic;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeStatic;
import esmj3d.j3d.j3drecords.type.J3dGeneralSOUN;
import esmj3d.j3d.trees.TreeMaker;
import esmj3dtes4.data.records.ACRE;
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
import esmj3dtes4.j3d.j3drecords.type.J3dNPC_;
import utils.source.MediaSources;

public class J3dREFRFactory
{
	public static boolean DEBUG_FIRST_LIST_ITEM_ONLY = false;

	public static boolean NATURAL_ANIMALS_ONLY = false;

	private static J3dRECODynInst makeJ3dRECODynInst(REFR refr, RECO reco, MODL modl, boolean makePhys, MediaSources mediaSources)
	{
		if (modl != null)
		{
			J3dRECODynInst j3dinst = new J3dRECODynInst(refr, true, makePhys);
			j3dinst.setJ3dRECOType(new J3dRECOTypeDynamic(reco, modl.model.str, makePhys, mediaSources));
			return j3dinst;
		}
		else
		{
			System.out.println("null modl there " + reco);
			return null;
		}
	}

	private static J3dRECOStatInst makeJ3dRECOActionInst(REFR refr, RECO reco, MODL modl, boolean makePhys, MediaSources mediaSources)
	{
		if (modl != null)
		{
			J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, true, makePhys);
			j3dinst.setJ3dRECOType(new J3dRECOTypeActionable(reco, modl.model.str, makePhys, mediaSources));
			return j3dinst;
		}
		else
		{
			System.out.println("null modl there " + reco);
			return null;
		}
	}

	private static J3dRECOStatInst makeJ3dRECOStatInst(REFR refr, RECO reco, MODL modl, boolean makePhys, MediaSources mediaSources)
	{
		if (modl != null)
		{
			J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, true, makePhys);
			j3dinst.setJ3dRECOType(new J3dRECOTypeStatic(reco, modl.model.str, makePhys, mediaSources));
			return j3dinst;
		}
		else
		{
			System.out.println("null modl there " + reco);
			return null;
		}
	}

	public static Node makeJ3DReferFar(REFR refr, IRecordStore master, MediaSources mediaSources)
	{
		// does a parent enablage flag exists, and is is defaulted to off?
		if (refr.xesp != null && CommonREFR.getParentEnable(refr, master) != BethRenderSettings.isFlipParentEnableDefault())
			return null;

		Record baseRecord = master.getRecord(refr.NAME.formId);

		if (baseRecord.getRecordType().equals("STAT"))
		{
			STAT stat = new STAT(baseRecord);

			String farNif = stat.MODL.model.str.substring(0, stat.MODL.model.str.toLowerCase().indexOf(".nif")) + "_far.nif";
			J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, false, false);
			if (mediaSources.getMeshSource().nifFileExists(farNif))
				j3dinst.addNodeChild(new LODNif(farNif, mediaSources));
			else
				j3dinst.addNodeChild(new J3dRECOTypeStatic(stat, stat.MODL.model.str, false, mediaSources));
			return j3dinst;

		}
		else if (baseRecord.getRecordType().equals("TREE"))
		{
			TREE tree = new TREE(baseRecord);
			String treeNif = tree.MODL.model.str;
			return TreeMaker.makeTreeFar(refr, false, mediaSources, treeNif, tree.billBoardWidth, tree.billBoardHeight);
		}
		else if (baseRecord.getRecordType().equals("FLOR"))
		{
			FLOR flor = new FLOR(baseRecord);
			return makeJ3dRECOStatInst(refr, flor, flor.MODL, false, mediaSources);
		}
		else
		{
			System.out.println("Far REFR record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	public static J3dRECOInst makeJ3DRefer(REFR refr, boolean makePhys, IRecordStore master, MediaSources mediaSources)
	{
		Record baseRecord = master.getRecord(refr.NAME.formId);

		// does a parent enablage flag exists, and is is defaulted to off?
		if (refr.xesp != null && CommonREFR.getParentEnable(refr, master) != BethRenderSettings.isFlipParentEnableDefault())
			return null;

		if (baseRecord.getRecordType().equals("STAT"))
		{
			STAT stat = new STAT(baseRecord);

			if (stat.MODL != null)
			{
				String farNif = stat.MODL.model.str.substring(0, stat.MODL.model.str.toLowerCase().indexOf(".nif")) + "_far.nif";
				if (mediaSources.getMeshSource().nifFileExists(farNif))
				{
					J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, true, makePhys);
					j3dinst.setJ3dRECOType(new J3dRECOTypeStatic(stat, stat.MODL.model.str, makePhys, mediaSources),
							J3dRECOType.loadNif(farNif, false, mediaSources).getRootNode());
					return j3dinst;

				}
				else
				{
					J3dRECOStatInst j3dinst = new J3dRECOStatInst(refr, true, makePhys);
					j3dinst.setJ3dRECOType(new J3dRECOTypeStatic(stat, stat.MODL.model.str, makePhys, mediaSources));
					return j3dinst;
				}
			}

		}

		else if (baseRecord.getRecordType().equals("FURN"))
		{
			FURN furn = new FURN(baseRecord);
			return makeJ3dRECOActionInst(refr, furn, furn.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("MISC"))
		{
			MISC misc = new MISC(baseRecord);
			return makeJ3dRECODynInst(refr, misc, misc.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("CONT"))
		{
			return new J3dRECOStatInst(refr, new J3dCONT(new CONT(baseRecord), makePhys, mediaSources), true, makePhys);
		}
		else if (baseRecord.getRecordType().equals("CLOT"))
		{
			CLOT clot = new CLOT(baseRecord);
			return makeJ3dRECODynInst(refr, clot, clot.MOD2, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("ARMO"))
		{
			ARMO armo = new ARMO(baseRecord);
			return makeJ3dRECODynInst(refr, armo, armo.MOD2, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("WEAP"))
		{
			WEAP weap = new WEAP(baseRecord);
			return makeJ3dRECODynInst(refr, weap, weap.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("AMMO"))
		{
			AMMO ammo = new AMMO(baseRecord);
			return makeJ3dRECODynInst(refr, ammo, ammo.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("FLOR"))
		{
			FLOR flor = new FLOR(baseRecord);
			return makeJ3dRECOActionInst(refr, flor, flor.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("BOOK"))
		{
			BOOK book = new BOOK(baseRecord);
			return makeJ3dRECODynInst(refr, book, book.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("APPA"))
		{
			APPA appa = new APPA(baseRecord);
			return makeJ3dRECODynInst(refr, appa, appa.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("ALCH"))
		{
			ALCH alch = new ALCH(baseRecord);
			return makeJ3dRECODynInst(refr, alch, alch.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("INGR"))
		{
			INGR ingr = new INGR(baseRecord);
			return makeJ3dRECODynInst(refr, ingr, ingr.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("ACTI"))
		{
			ACTI acti = new ACTI(baseRecord);
			return makeJ3dRECOActionInst(refr, acti, acti.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("KEYM"))
		{
			KEYM keym = new KEYM(baseRecord);
			return makeJ3dRECODynInst(refr, keym, keym.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("SLGM"))
		{
			SLGM slgm = new SLGM(baseRecord);
			return makeJ3dRECODynInst(refr, slgm, slgm.MODL, makePhys, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("DOOR"))
		{
			return new J3dRECOStatInst(refr, new J3dDOOR(new DOOR(baseRecord), makePhys, mediaSources), true, makePhys);
		}
		else if (baseRecord.getRecordType().equals("LIGH"))
		{
			return new J3dRECOStatInst(refr, new J3dGeneralLIGH(new LIGH(baseRecord), makePhys, mediaSources), true, makePhys);
		}
		else if (baseRecord.getRecordType().equals("TREE"))
		{
			TREE tree = new TREE(baseRecord);
			String treeNif = tree.MODL.model.str;
			J3dRECOStatInst j3dinst = TreeMaker.makeTree(refr, makePhys, mediaSources, treeNif, tree.billBoardWidth, tree.billBoardHeight,
					false);
			return j3dinst;
		}
		else if (baseRecord.getRecordType().equals("SOUN"))
		{
			if (!makePhys)
			{
				return new J3dRECOStatInst(refr, new J3dGeneralSOUN(new SOUN(baseRecord), master, mediaSources), false, makePhys);
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
				J3dRECOChaInst j3dinst = new J3dRECOChaInst(refr);
				j3dinst.setJ3dRECOType(makeLVLC(lvlc, master, mediaSources));
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
	protected static J3dRECOTypeCha makeLVLC(LVLC lvlc, IRecordStore master, MediaSources mediaSources)
	{

		// TODO: randomly picked for now
		LVLO[] LVLOs = lvlc.LVLOs;

		int idx = (int) (Math.random() * LVLOs.length);
		idx = idx == LVLOs.length ? 0 : idx;

		if (DEBUG_FIRST_LIST_ITEM_ONLY)
			idx = 0;

		Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);

		if (baseRecord.getRecordType().equals("LVLC"))
		{
			// it is in fact a pointer across to another leveled creature (LVLC)
			LVLC lvlc2 = new LVLC(baseRecord);
			return makeLVLC(lvlc2, master, mediaSources);
		}
		else if (baseRecord.getRecordType().equals("CREA"))
		{
			CREA crea = new CREA(baseRecord);
			if (CREAallowed(crea))
				return new J3dCREA(crea, master, mediaSources);
			else
				return null;
		}
		else if (baseRecord.getRecordType().equals("NPC_"))
		{
			NPC_ npc_ = new NPC_(baseRecord);
			if (!NATURAL_ANIMALS_ONLY)
				return new J3dNPC_(npc_, master, mediaSources);
			else
				return null;
		}
		else
		{
			System.out.println("LVLC record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	public static boolean ACREallowed(ACRE acre, IRecordStore master)
	{
		Record baseRecord = master.getRecord(acre.NAME.formId);
		if (baseRecord.getRecordType().equals("CREA"))
		{
			return CREAallowed(new CREA(baseRecord));
		}
		return false;
	}

	public static boolean CREAallowed(CREA crea)
	{
		if (NATURAL_ANIMALS_ONLY)
		{
			for (String nat : naturalAnimals)
				if (crea.MODL.model.str.startsWith(nat))
					return true;

			return false;
		}
		else
		{
			return true;
		}
	}

	public static String[] naturalAnimals = new String[] { "Creatures\\Bear\\", //
			"Creatures\\Boar\\", //
			"Creatures\\Deer\\", //
			"Creatures\\Dog\\", //
			"Creatures\\Horse\\", //
			"Creatures\\Mountainlion\\", //
			"Creatures\\Mudcrab\\", //
			"Creatures\\Rat\\", //
			"Creatures\\Sheep\\", //
			"Creatures\\Slaughterfish",//
	};
}
