package esmj3dtes4.j3d.cell;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import nif.NiObjectList;
import nif.NifFile;
import nif.NifToJ3d;
import nif.j3d.J3dNiTriStrips;
import nif.niobject.NiAVObject;
import nif.niobject.NiTriStripsData;
import utils.convert.ConvertFromNif;
import utils.source.MeshSource;
import utils.source.TextureSource;
import esmj3d.j3d.cell.MorphingLandscape;
import esmj3d.j3d.j3drecords.inst.J3dLAND;

public class Tes4LODLandscape extends MorphingLandscape
{
	public Tes4LODLandscape(int lodX, int lodY, int scale, String lodWorldFormId, MeshSource meshSource, TextureSource textureSource)
	{
		super(lodX, lodY, scale);
		String xy = (lodX == 0 ? "00" : "" + lodX) + "." + (lodY == 0 ? "00" : "" + lodY);
		String meshName = "landscape\\lod\\" + lodWorldFormId + "." + xy + "." + scale + ".nif";

		setCapability(BranchGroup.ALLOW_DETACH);
		if (meshSource.nifFileExists(meshName))
		{
			NifFile nf = NifToJ3d.loadNiObjects(meshName, meshSource);
			if (nf != null)
			{
				NiObjectList blocks = nf.blocks;

				// we know it is a NiTriStripsData at block 1
				NiTriStripsData data = (NiTriStripsData) blocks.getNiObjects()[1];

				//all scales will get morph treatment later
				boolean morphable = true;
				IndexedGeometryArray baseItsa = J3dNiTriStrips.createGeometry(data, morphable);

				if (morphable)
				{
					this.addGeometryArray(baseItsa);
				}

				Shape3D shape = new Shape3D();
				shape.setGeometry(baseItsa);

				//names are generated
				String textureName = "landscapelod\\generated\\" + lodWorldFormId + "." + xy + "." + scale + ".dds";
				shape.setAppearance(createAppearance(textureSource.getTexture(textureName)));

				TransformGroup tg = new TransformGroup();
				NiAVObject root = (NiAVObject) blocks.root();
				Transform3D t = new Transform3D(ConvertFromNif.toJ3d(root.rotation), ConvertFromNif.toJ3d(root.translation), root.scale);
				tg.setTransform(t);
				tg.addChild(shape);

				tg.addChild(createBasicWater(scale * J3dLAND.LAND_SIZE, scale * J3dLAND.LAND_SIZE));

				addChild(tg);
			}
			else
			{
				System.out.println("Bad landscape NifFile " + meshName);
			}
		}
		else
		{
			//fine the systems just askign for empty space which is fine
			//System.out.println("Bad landscape name " + meshName);
		}

	}
}
