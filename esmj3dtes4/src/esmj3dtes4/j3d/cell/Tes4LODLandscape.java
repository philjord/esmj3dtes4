package esmj3dtes4.j3d.cell;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import nif.NiObjectList;
import nif.NifFile;
import nif.NifToJ3d;
import nif.j3d.J3dNiTriBasedGeom;
import nif.niobject.NiTriStrips;
import nif.niobject.NiTriStripsData;
import utils.convert.ConvertFromNif;
import utils.source.MeshSource;
import utils.source.TextureSource;

import com.sun.j3d.utils.geometry.GeometryInfo;

import esmj3d.j3d.cell.MorphingLandscape;

public class Tes4LODLandscape extends MorphingLandscape
{
	public Tes4LODLandscape(int lodX, int lodY, int scale, String lodWorldFormId, MeshSource meshSource, TextureSource textureSource)
	{
		super(lodX, lodY, scale);

		String xy = (lodX == 0 ? "00" : "" + lodX) + "." + (lodY == 0 ? "00" : "" + lodY);
		String meshName = "landscape\\lod\\" + lodWorldFormId + "." + xy + "." + scale + ".nif";
		String textureName = "landscapelod\\generated\\" + lodWorldFormId + "." + xy + "." + scale + ".dds";

		setCapability(BranchGroup.ALLOW_DETACH);
		if (meshSource.nifFileExists(meshName))
		{
			NifFile nf = NifToJ3d.loadNiObjects(meshName, meshSource);
			if (nf != null)
			{
				NiObjectList blocks = nf.blocks;

				// we know it is a NiTriStripsData at block 1

				IndexedGeometryArray baseItsa = makeGeometry((NiTriStripsData) blocks.getNiObjects()[1]);

				Shape3D shape = new Shape3D();
				shape.setGeometry(baseItsa);
				shape.setAppearance(createAppearance(textureSource.getTexture(textureName)));

				TransformGroup tg = new TransformGroup();
				NiTriStrips n = (NiTriStrips) blocks.root();
				Transform3D t = new Transform3D(ConvertFromNif.toJ3d(n.rotation), ConvertFromNif.toJ3d(n.translation), n.scale);
				tg.setTransform(t);
				tg.addChild(shape);
				addChild(tg);

				baseItsa.setCapability(GeometryArray.ALLOW_REF_DATA_WRITE);
				this.setGeometryArray(baseItsa);
			}
			else
			{
				System.out.println("Bad landscape nf " + meshName);
			}
		}
		else
		{
			//fine the systems just askign for empty space which is fine
			System.out.println("Bad landscape name " + meshName);
		}

	}

	private static IndexedGeometryArray makeGeometry(NiTriStripsData data)
	{
		GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_STRIP_ARRAY);

		J3dNiTriBasedGeom.loadGIBaseData(gi, data);

		int numStrips = data.numStrips;
		int[] stripLengths = data.stripLengths;
		int[] points = null;
		if (data.hasPoints)
		{
			// get full length
			int length = 0;
			for (int i = 0; i < numStrips; i++)
			{
				length += data.points[i].length;
			}

			gi.setStripCounts(stripLengths);
			points = new int[length];
			int idx = 0;
			for (int i = 0; i < numStrips; i++)
			{
				for (int j = 0; j < stripLengths[i]; j++)
				{
					points[idx] = data.points[i][j];
					idx++;
				}
			}

			gi.setCoordinateIndices(points);
			gi.setUseCoordIndexOnly(true);
		}

		boolean compact = false;
		IndexedGeometryArray g = gi.getIndexedGeometryArray(compact, !compact, compact, true, false);

		return g;

	}
}
