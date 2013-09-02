package esmj3dtes4.j3d.cell;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color4f;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3f;

import nif.NiObjectList;
import nif.NifFile;
import nif.NifToJ3d;
import nif.niobject.NiTriStrips;
import nif.niobject.NiTriStripsData;
import utils.convert.ConvertFromNif;
import utils.source.MeshSource;
import utils.source.TextureSource;

import com.sun.j3d.utils.geometry.GeometryInfo;

import esmj3d.j3d.cell.Beth32LODLandscape;
import esmj3d.j3d.j3drecords.inst.J3dLAND;

public class Tes4LODLandscape extends Beth32LODLandscape
{
	public Tes4LODLandscape(int lodX, int lodY, int scale, int worldFormId, MeshSource meshSource, TextureSource textureSource)
	{
		super(lodX, lodY);
		//TODO: there is a distantlod directory with lod files in it how do they work?
		String xy = (lodX == 0 ? "00" : "" + lodX) + "." + (lodY == 0 ? "00" : "" + lodY);
		String meshName = "landscape\\lod\\" + worldFormId + "." + xy + "." + scale + ".nif";
		String textureName = "landscapelod\\generated\\" + worldFormId + "." + xy + "." + scale + ".dds";

		setCapability(BranchGroup.ALLOW_DETACH);
		if (meshSource.nifFileExists(meshName))
		{
			NifFile nf = NifToJ3d.loadNiObjects(meshName, meshSource);
			if (nf != null)
			{
				NiObjectList blocks = nf.blocks;

				// we know it is a NiTriStripsData at block 1

				IndexedGeometryArray baseItsa = makeGeometry2((NiTriStripsData) blocks.getNiObjects()[1], false);

				Shape3D shape = new Shape3D();
				shape.setGeometry(baseItsa);
				Appearance app = new Appearance();
				app.setMaterial(J3dLAND.getLandMaterial());

				Texture tex = textureSource.getTexture(textureName);
				app.setTexture(tex);
				shape.setAppearance(app);

				TransformGroup tg = new TransformGroup();
				NiTriStrips n = (NiTriStrips) blocks.root();
				Transform3D t = new Transform3D(ConvertFromNif.toJ3d(n.rotation), ConvertFromNif.toJ3d(n.translation), n.scale);
				tg.setTransform(t);
				tg.addChild(shape);
				addChild(tg);

				baseItsa.setCapability(GeometryArray.ALLOW_REF_DATA_WRITE);
				this.setGeometryArray(baseItsa);
			}
		}
		else
		{
			//fine the systems just askign for empty space which is fine
			//			System.out.println("Bad landscape name " + meshName + " lodX " + lodX + " lodY " + lodY);
		}

	}

	public static IndexedGeometryArray makeGeometry2(NiTriStripsData data, boolean compact)
	{
		GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_STRIP_ARRAY);

		if (data.hasVertices)
		{
			Point3f[] vertices = new Point3f[data.numVertices];
			for (int i = 0; i < data.numVertices; i++)
			{
				vertices[i] = ConvertFromNif.toJ3dP3f(data.vertices[i]);
			}
			gi.setCoordinates(vertices);

		}

		if (data.hasNormals)
		{
			Vector3f[] normals = new Vector3f[data.numVertices];
			for (int i = 0; i < data.numVertices; i++)
			{
				normals[i] = ConvertFromNif.toJ3dNoScale(data.normals[i]);
			}
			gi.setNormals(normals);

		}

		Color4f[] colors = new Color4f[data.numVertices];
		for (int i = 0; i < data.numVertices; i++)
		{
			if (data.hasVertexColors)
			{
				colors[i] = ConvertFromNif.toJ3d(data.vertexColors[i]);
			}
			else
			{
				colors[i] = new Color4f(1, 1, 1, 1);
			}
		}
		gi.setColors(colors);

		// process UVsets hasUV or UVset2?? Num UV Sets 2
		int actNumUVSets = data.actNumUVSets;
		if (actNumUVSets > 0)
		{
			gi.setTextureCoordinateParams(actNumUVSets, 2);

			for (int i = 0; i < actNumUVSets; i++)
			{
				TexCoord2f[] texCoords = new TexCoord2f[data.uVSets[i].length];
				for (int j = 0; j < data.uVSets[i].length; j++)
				{
					texCoords[j] = ConvertFromNif.toJ3d(data.uVSets[i][j]);
				}
				gi.setTextureCoordinates(i, texCoords);

			}

		}

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

		IndexedGeometryArray g = gi.getIndexedGeometryArray(compact, !compact, compact, true, false);

		return g;

	}
}
