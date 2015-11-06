package esmj3dtes4.j3d.j3drecords.type;

import java.util.ArrayList;

import javax.vecmath.Color3f;

import nif.character.NifCharacter;
import tools3d.utils.scenegraph.Fadable;
import utils.ESConfig;
import utils.source.MediaSources;
import esmj3d.data.shared.subrecords.CNTO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.j3d.BethRenderSettings;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeCha;
import esmj3dtes4.data.records.ARMO;
import esmj3dtes4.data.records.CLOT;
import esmj3dtes4.data.records.LVLI;
import esmj3dtes4.data.records.NPC_;
import esmj3dtes4.data.records.RACE;
import esmj3dtes4.data.records.WEAP;
import esmj3dtes4.data.subrecords.LVLO;
import esmmanager.common.data.record.IRecordStore;
import esmmanager.common.data.record.Record;

public class J3dNPC_ extends J3dRECOTypeCha
{
	private String helmetStr = null;

	private String headStr = null;

	private String upperStr = null;

	private String lowerStr = null;

	private String handStr = null;

	private String footStr = null;

	private String shieldStr = null;

	private String weapStr = null;

	private boolean female = false;

	public J3dNPC_(NPC_ npc_, IRecordStore master, MediaSources mediaSources)
	{
		super(npc_);
		female = npc_.ACBS.isFemale();

		Record rrec = master.getRecord(npc_.RNAM.formId);
		RACE race = new RACE(rrec);
		// to attach neutral head models
		ArrayList<MODL> modls = race.MODLs;

		// the defaults
		if (female)
		{
			headStr = modls.get(0).model.str;//TODO: no female head
			//All beast races are just humans with a different texture
			upperStr = ESConfig.TES_MESH_PATH + "characters\\_male\\femaleupperbodynude.nif";
			lowerStr = ESConfig.TES_MESH_PATH + "characters\\_male\\femalelowerbody.nif";
			handStr = ESConfig.TES_MESH_PATH + "characters\\_male\\femalehand.nif";
			footStr = ESConfig.TES_MESH_PATH + "characters\\_male\\femalefoot.nif";
			helmetStr = ESConfig.TES_MESH_PATH + "characters\\hair\\style01.nif";
		}
		else
		{
			headStr = modls.get(0).model.str;
			upperStr = ESConfig.TES_MESH_PATH + "characters\\_male\\upperbody.nif";
			lowerStr = ESConfig.TES_MESH_PATH + "characters\\_male\\lowerbody.nif";
			handStr = ESConfig.TES_MESH_PATH + "characters\\_male\\hand.nif";
			footStr = ESConfig.TES_MESH_PATH + "characters\\_male\\foot.nif";
			helmetStr = ESConfig.TES_MESH_PATH + "characters\\hair\\imperialheadband.nif";
		}

		CNTO[] cntos = npc_.CNTOs;
		for (int i = 0; i < cntos.length; i++)
		{
			//	int count = cntos[i].count;
			Record rec = master.getRecord(cntos[i].itemFormId);
			if (rec.getRecordType().equals("CLOT"))
			{
				CLOT clot = new CLOT(rec);
				addCLOT(clot);
			}
			else if (rec.getRecordType().equals("ARMO"))
			{
				ARMO armo = new ARMO(rec);
				addARMO(armo);
			}
			else if (rec.getRecordType().equals("WEAP"))
			{
				WEAP weap = new WEAP(rec);
				addWEAP(weap);
			}
			else if (rec.getRecordType().equals("AMMO"))
			{
				//AMMO ammo = new AMMO(rec);
			}
			else if (rec.getRecordType().equals("MISC"))
			{
				//MISC misc = new MISC(rec);
			}
			else if (rec.getRecordType().equals("KEYM"))
			{
				//KEYM keym = new KEYM(rec);
			}
			else if (rec.getRecordType().equals("INGR"))
			{
				//INGR keym = new INGR(rec);
			}
			else if (rec.getRecordType().equals("LIGH"))
			{
				//LIGH ligh = new LIGH(rec);
			}
			else if (rec.getRecordType().equals("ALCH"))
			{
				//ALCH alch = new ALCH(rec);
			}
			else if (rec.getRecordType().equals("BOOK"))
			{
				//BOOK book = new BOOK(rec);
			}
			else if (rec.getRecordType().equals("LVLI"))
			{
				LVLI lvli = new LVLI(rec);
				LVLO[] LVLOs = lvli.LVLOs;

				int idx = (int) (Math.random() * LVLOs.length);
				idx = idx == LVLOs.length ? 0 : idx;

				Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);
				if (baseRecord.getRecordType().equals("CLOT"))
				{
					CLOT clot = new CLOT(baseRecord);
					addCLOT(clot);
				}
				else if (baseRecord.getRecordType().equals("ARMO"))
				{
					ARMO armo = new ARMO(baseRecord);
					addARMO(armo);
				}
				else if (baseRecord.getRecordType().equals("WEAP"))
				{
					WEAP weap = new WEAP(baseRecord);
					addWEAP(weap);
				}
				else if (baseRecord.getRecordType().equals("LVLI"))
				{
				}
				else if (baseRecord.getRecordType().equals("MISC"))
				{
				}
				else if (baseRecord.getRecordType().equals("AMMO"))
				{
				}
				else if (baseRecord.getRecordType().equals("INGR"))
				{
				}
				else if (baseRecord.getRecordType().equals("ALCH"))
				{
				}

				else
				{
					System.out.println("LVLI record type not converted to j3d " + baseRecord.getRecordType());
				}

			}
			else
			{
				System.out.println("NPC_ has unknown contained item " + rec.getRecordType());
			}

		}

		String skeletonNifFile = ESConfig.TES_MESH_PATH + "characters\\_male\\skeleton.nif";

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(headStr);
		fileNames.add(helmetStr);
		fileNames.add(upperStr);
		fileNames.add(lowerStr);
		fileNames.add(handStr);
		fileNames.add(footStr);
		fileNames.add(shieldStr);
		fileNames.add(weapStr);

		ArrayList<String> idleAnimations = new ArrayList<String>();

		idleAnimations.addAll(mediaSources.getMeshSource().getFilesInFolder(ESConfig.TES_MESH_PATH + "characters\\_male\\idleanims"));

		nifCharacter = new NifCharacter(skeletonNifFile, fileNames, mediaSources, idleAnimations);
		addChild(nifCharacter);
		
		setOutline(new Color3f(1.0f, 1.0f, 0f));
		if (!BethRenderSettings.isOutlineChars())
			((Fadable) nifCharacter).setOutline(null);
	}

	private void addCLOT(CLOT clot)
	{
		MODL m = clot.MODL;
		if (female && clot.MOD3 != null)
		{
			m = clot.MOD3;
		}
		helmetStr = clot.BMDT.isHair() ? m.model.str : helmetStr;
		upperStr = clot.BMDT.isUpperBody() ? m.model.str : upperStr;
		lowerStr = clot.BMDT.isLowerBody() ? m.model.str : lowerStr;
		handStr = clot.BMDT.isHand() ? m.model.str : handStr;
		footStr = clot.BMDT.isFoot() ? m.model.str : footStr;

	}

	private void addARMO(ARMO armo)
	{

		MODL m = armo.MODL;
		if (female && armo.MOD3 != null)
		{
			m = armo.MOD3;
		}
		helmetStr = armo.BMDT.isHair() ? m.model.str : helmetStr;
		upperStr = armo.BMDT.isUpperBody() ? m.model.str : upperStr;
		lowerStr = armo.BMDT.isLowerBody() ? m.model.str : lowerStr;
		handStr = armo.BMDT.isHand() ? m.model.str : handStr;
		footStr = armo.BMDT.isFoot() ? m.model.str : footStr;
		shieldStr = armo.BMDT.isShield() ? m.model.str : shieldStr;
	}

	private void addWEAP(WEAP weap)
	{
		weapStr = weap.MODL.model.str;
	}
}
