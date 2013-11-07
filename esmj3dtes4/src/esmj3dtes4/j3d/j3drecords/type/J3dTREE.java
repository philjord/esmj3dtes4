package esmj3dtes4.j3d.j3drecords.type;

import javax.media.j3d.Node;

import utils.ESConfig;
import utils.source.TextureSource;

import esmj3d.j3d.TreeMaker;
import esmj3d.j3d.j3drecords.type.J3dRECOType;
import esmj3dtes4.data.records.TREE;

public class J3dTREE extends J3dRECOType
{
	public J3dTREE(TREE tree, TextureSource textureSource)
	{
		super(tree, null);

		Node node = TreeMaker.makeLODTreeX(tree.MODL.model.str, tree.billBoardWidth * ESConfig.ES_TO_METERS_SCALE, tree.billBoardHeight
				* ESConfig.ES_TO_METERS_SCALE, textureSource);
		addChild(node);
	}
}
