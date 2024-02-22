package esmj3dtes4.j3d.j3drecords.type;

import java.util.ArrayList;

import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.Color3f;

import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.data.shared.subrecords.CNTO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.j3d.BethRenderSettings;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeCha;
import esmj3dtes4.data.records.ARMO;
import esmj3dtes4.data.records.CLOT;
import esmj3dtes4.data.records.CREA;
import esmj3dtes4.data.records.LVLI;
import esmj3dtes4.data.records.WEAP;
import esmj3dtes4.data.subrecords.LVLO;
import nif.character.NifCharacter;
import tools3d.utils.scenegraph.Fadable;
import utils.ESConfig;
import utils.source.MediaSources;

public class J3dCREA extends J3dRECOTypeCha
{
	private String helmetStr = null;

	private String upperStr = null;

	private String lowerStr = null;

	private String handStr = null;

	private String footStr = null;

	private String shieldStr = null;

	private String weapStr = null;

	private boolean female = false;

	public J3dCREA(CREA crea, IRecordStore master, MediaSources mediaSources)
	{
		super(crea, false);
		//MODL is a bone file and the NIFZ are no path othe files to add
		if (crea.NIFZ != null && crea.MODL != null)
		{
			String path = crea.MODL.model.str.substring(0, crea.MODL.model.str.lastIndexOf("\\"));

			String skeletonNifFile = crea.MODL.model.str;

			ArrayList<String> fileNames = new ArrayList<String>();

			for (int i = 0; i < crea.NIFZ.fileNames.length; i++)
			{
				fileNames.add(path + "\\" + crea.NIFZ.fileNames[i]);
			}

			female = crea.ACBS.isFemale();

			CNTO[] cntos = crea.CNTOs;
			for (int i = 0; i < cntos.length; i++)
			{
				//	int count = cntos[i].count;
				Record rec = master.getRecord(cntos[i].itemFormId);
				if (rec.getRecordType().equals("CLOT"))
				{
					CLOT clot = new CLOT(rec);
					addCLOT(clot);
				}
				else if (rec.getRecordType().equals("WEAP"))
				{
					WEAP weap = new WEAP(rec);
					addWEAP(weap);
				}
				else if (rec.getRecordType().equals("ARMO"))
				{
					ARMO armo = new ARMO(rec);
					addARMO(armo);
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
					else if (baseRecord.getRecordType().equals("WEAP"))
					{
						WEAP weap = new WEAP(baseRecord);
						addWEAP(weap);
					}
					else
					{
						System.out.println("LVLI record type not converted to j3d " + baseRecord.getRecordType());
					}
				}
				else
				{
					System.out.println("CREA has unknown contained item " + rec.getRecordType());
				}

			}

			fileNames.add(helmetStr);
			fileNames.add(upperStr);
			fileNames.add(lowerStr);
			fileNames.add(handStr);
			fileNames.add(footStr);
			fileNames.add(shieldStr);
			fileNames.add(weapStr);
			
			
			/*FormID[] PKIDs = crea.PKIDs;
			for (int i = 0; i < PKIDs.length; i++)
			{
				Record pkRecord = master.getRecord(PKIDs[i].formId);
				System.out.println("pkRecord " + pkRecord.getSubrecords() );
			}*/

			ArrayList<String> idleAnimations = new ArrayList<String>();
			if (crea.KFFZ != null)
			{
				for (int i = 0; i < crea.KFFZ.fileNames.length; i++)
				{
					//Mainly specials under specialanims??
					//System.out.println(""+crea.MODL.model.str+" crea.KFFZ.fileNames[i] " + crea.KFFZ.fileNames[i]);
					//idleAnimations.add(path + "\\specialanims\\" + crea.KFFZ.fileNames[i]);
				}
			}

			idleAnimations.add(ESConfig.TES_MESH_PATH + path + "\\idle.kf");
			//FIXME:!!! idleanims aren't even nothing like the idles! mtidle seem to be the main one
			
			//ok so bear has \\idle.kf and under idleanims 3 specialidle_* that look good
			addIdleAnimations( idleAnimations, mediaSources.getMeshSource().getFilesInFolder(ESConfig.TES_MESH_PATH + path + "\\idleanims"), 
					new String[]{
						"specialidle_aware","specialidle_sit","specialidle_smell",//bear
						"specialidle_rooting",//boar
						"look","search",//clanfear lich,ogre
						"roar","scan","dig", //daedroth //imp//landdrugh
						"graze","startle",//deer						
						"specialidle_dogbark","specialidle_howl","specialidle_scratch","specialidle_sit","specialidle_sitbark","specialidle_sleeplookaround","specialidle_smell",//dog
						"idleb","idlec",//flame atronach
						"idleclench","idlelook",//frost atronach
						"specialidle_b","specialidle_c",//ghost
						"specialldle_guard", //goblin						
						"graze","look","paw","rest","scratch","swat",//horse
						"idledrool","idlescratch","idleslack","idlestamp",//minotaur
						"specialidle_aware","specialidle_clean","specialidle_look",//lion
						"specialidle_eat",//mudcrab
						"check",//ogre
						"scratch2",//rat
						"snif",//scamp
						"bah","grazes",//sheep
						
						
						
			});

			nifCharacter = new NifCharacter(skeletonNifFile, fileNames, mediaSources, idleAnimations);

			if (crea.BNAM.baseScale == 1)
			{
				addChild(nifCharacter);
			}
			else
			{
				TransformGroup scaler = new TransformGroup();
				Transform3D t = new Transform3D();
				t.setScale(crea.BNAM.baseScale);
				scaler.setTransform(t);
				addChild(scaler);
				scaler.addChild(nifCharacter);
			}
			
			setOutline(new Color3f(1.0f, 1.0f, 0f));
			if (!BethRenderSettings.isOutlineChars())
				((Fadable) nifCharacter).setOutline(null);

		}
		else
		{
			//CREA has no NIFs like the will o the wisp (but it has skeleton with particles effects)
			// let's do these later shall we
		}
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
