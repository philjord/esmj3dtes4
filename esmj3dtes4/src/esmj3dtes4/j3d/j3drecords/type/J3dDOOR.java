package esmj3dtes4.j3d.j3drecords.type;

import nif.NifToJ3d;
import nif.j3d.J3dNiAVObject;
import utils.source.MeshSource;
import utils.source.TextureSource;
import esmj3d.j3d.j3drecords.type.J3dRECOType;
import esmj3d.j3d.j3drecords.type.J3dRECOTypeGeneral;
import esmj3dtes4.data.records.DOOR;

public class J3dDOOR extends J3dRECOType
{

	public J3dDOOR(DOOR door, boolean makePhys, MeshSource meshSource, TextureSource textureSource)
	{
		super(door, door.MODL.model.str);
		J3dNiAVObject j3dNiAVObject;
		String nifFileName = door.MODL.model.str;
		if (makePhys)
		{
			j3dNiAVObject = NifToJ3d.loadHavok(nifFileName, meshSource).getHavokRoot();
		}
		else
		{
			j3dNiAVObject = NifToJ3d.loadShapes(nifFileName, meshSource, textureSource).getVisualRoot();
		}

		J3dRECOTypeGeneral.setupDemoControllerTrigger(j3dNiAVObject);
		addChild(j3dNiAVObject);
	}

}
