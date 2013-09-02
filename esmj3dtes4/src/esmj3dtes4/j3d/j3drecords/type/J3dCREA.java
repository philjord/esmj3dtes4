package esmj3dtes4.j3d.j3drecords.type;

import javax.media.j3d.Node;

import nif.NifToJ3d;
import utils.source.MeshSource;
import utils.source.SoundSource;
import utils.source.TextureSource;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmj3d.data.shared.subrecords.CNTO;
import esmj3d.j3d.j3drecords.type.J3dRECOType;
import esmj3dtes4.data.records.ARMO;
import esmj3dtes4.data.records.CLOT;
import esmj3dtes4.data.records.CREA;
import esmj3dtes4.data.records.LVLI;
import esmj3dtes4.data.records.WEAP;
import esmj3dtes4.data.subrecords.LVLO;

public class J3dCREA extends J3dRECOType
{
	private boolean upper = false;

	private boolean lower = false;

	private boolean hand = false;

	private boolean foot = false;

	private boolean shield = false;

	private boolean female = false;

	public J3dCREA(CREA crea, IRecordStore master, MeshSource meshSource, TextureSource textureSource, SoundSource soundSource)
	{
		super(crea,null);
		if (crea.NIFZ != null && crea.MODL != null)
		{
			String path = crea.MODL.model.str.substring(0, crea.MODL.model.str.lastIndexOf("\\"));
			for (int i = 0; i < crea.NIFZ.fileNames.length; i++)
			{
				Node node = NifToJ3d.loadShapes(path + "\\" + crea.NIFZ.fileNames[i], meshSource, textureSource).getVisualRoot();
				addChild(node);
			}
		}
		else
		{
			//CREA has no NIFs like the will o the wisp
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
				addCLOT(clot, meshSource, textureSource);
			}
			else if (rec.getRecordType().equals("WEAP"))
			{
				WEAP weap = new WEAP(rec);
				addWEAP(weap);
			}
			else if (rec.getRecordType().equals("ARMO"))
			{
				ARMO armo = new ARMO(rec);
				addARMO(armo, meshSource, textureSource);
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
					addCLOT(clot, meshSource, textureSource);
				}
				else if (baseRecord.getRecordType().equals("ARMO"))
				{
					ARMO armo = new ARMO(baseRecord);
					addARMO(armo, meshSource, textureSource);
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
	}

	private void addCLOT(CLOT clot, MeshSource meshSource, TextureSource textureSource)
	{
		if ((clot.BMDT.isUpperBody() && upper != true) || (clot.BMDT.isLowerBody() && lower != true)
				|| (clot.BMDT.isHand() && hand != true) || (clot.BMDT.isFoot() && foot != true) || (clot.BMDT.isShield() && shield != true))
		{
			upper = clot.BMDT.isUpperBody() ? true : upper;
			lower = clot.BMDT.isLowerBody() ? true : lower;
			hand = clot.BMDT.isHand() ? true : hand;
			foot = clot.BMDT.isFoot() ? true : foot;

			if (!female || clot.MOD3 == null)
			{
				addChild(NifToJ3d.loadShapes(clot.MODL.model.str, meshSource, textureSource).getVisualRoot());
			}
			else
			{
				addChild(NifToJ3d.loadShapes(clot.MOD3.model.str, meshSource, textureSource).getVisualRoot());
			}

		}
	}

	private void addARMO(ARMO armo, MeshSource meshSource, TextureSource textureSource)
	{
		if ((armo.BMDT.isUpperBody() && upper != true) || (armo.BMDT.isLowerBody() && lower != true)
				|| (armo.BMDT.isHand() && hand != true) || (armo.BMDT.isFoot() && foot != true) || (armo.BMDT.isShield() && shield != true))
		{
			upper = armo.BMDT.isUpperBody() ? true : upper;
			lower = armo.BMDT.isLowerBody() ? true : lower;
			hand = armo.BMDT.isHand() ? true : hand;
			foot = armo.BMDT.isFoot() ? true : foot;
			shield = armo.BMDT.isShield() ? true : shield;

			if (!female || armo.MOD3 == null)
			{
				addChild(NifToJ3d.loadShapes(armo.MODL.model.str, meshSource, textureSource).getVisualRoot());
			}
			else
			{
				addChild(NifToJ3d.loadShapes(armo.MOD3.model.str, meshSource, textureSource).getVisualRoot());
			}
		}
	}

	private void addWEAP(WEAP weap)
	{
		//TODO: add weapon!
		weap.getClass();
	}

}
