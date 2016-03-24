package esmj3dtes4.j3d.cell;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.J3DBuffer;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleArray;

import esmj3d.j3d.cell.MorphingLandscape;
import esmj3d.j3d.j3drecords.inst.J3dLAND;
import nif.NiObjectList;
import nif.NifFile;
import nif.NifToJ3d;
import nif.j3d.J3dNiTriStrips;
import nif.niobject.NiAVObject;
import nif.niobject.NiTriStripsData;
import tools3d.utils.Utils3D;
import utils.convert.ConvertFromNif;
import utils.source.MeshSource;
import utils.source.TextureSource;

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

				//the only scale is 32 and it morphs
				boolean morphable = true;
				IndexedGeometryArray baseItsa = J3dNiTriStrips.createGeometry(data, morphable);
				baseItsa.setName(meshName);
				if (morphable)
				{
					this.addGeometryArray(baseItsa);
				}

				Shape3D shape = new Shape3D();
				shape.clearCapabilities();
				shape.setGeometry(baseItsa);

				//names are generated
				String textureName = "landscapelod\\generated\\" + lodWorldFormId + "." + xy + "." + scale + ".dds";
				shape.setAppearance(createAppearance(textureSource.getTexture(textureName)));

				TransformGroup tg = new TransformGroup();
				NiAVObject root = (NiAVObject) blocks.root();
				Transform3D t = new Transform3D(ConvertFromNif.toJ3d(root.rotation), ConvertFromNif.toJ3d(root.translation), root.scale);
				tg.setTransform(t);
				tg.addChild(shape);

				// the water needs to disappear up close 
				Shape3D w = createBasicWater(scale * J3dLAND.LAND_SIZE, scale * J3dLAND.LAND_SIZE);
				if (morphable)
				{
					this.addGeometryArray((GeometryArray) w.getGeometry());
				}				
				tg.addChild(w);

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
	
	private static Shape3D createBasicWater(float rectWidth, float rectHeight)
	{
		// ready for prebaking coords if required
		float x = 0;
		float y = 0;
		float z = 0;

		float yPosition = 0f;

		float[] verts1 = { x + (rectWidth / 2), y + yPosition, z + (-rectHeight / 2), //1
				x + (rectWidth / 2), y + yPosition, z + (rectHeight / 2), //2
				x + (-rectWidth / 2), y + yPosition, z + (rectHeight / 2), //3
				x + (rectWidth / 2), y + yPosition, z + (-rectHeight / 2), //1
				x + (-rectWidth / 2), y + yPosition, z + (rectHeight / 2), //3
				x + (-rectWidth / 2), y + yPosition, z + (-rectHeight / 2) //4
		};

		float[] normals = { 0f, 0f, 1f, //1
				0f, 0f, 1f, //2
				0f, 0f, 1f, //3
				0f, 0f, 1f, //1
				0f, 0f, 1f, //3
				0f, 0f, 1f, //4
		};

		TriangleArray rect = new TriangleArray(6,
				GeometryArray.COORDINATES | GeometryArray.NORMALS | GeometryArray.USE_NIO_BUFFER | GeometryArray.BY_REFERENCE);
		//rect.setCoordinates(0, verts1);
		//rect.setNormals(0, normals);
		rect.setCoordRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(verts1)));
		rect.setNormalRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(normals)));

		Shape3D shape = new Shape3D(rect, createBasicWaterApp());
		shape.clearCapabilities();
		rect.setCapability(TriangleArray.ALLOW_COORDINATE_WRITE);
		return shape;
	}
}
