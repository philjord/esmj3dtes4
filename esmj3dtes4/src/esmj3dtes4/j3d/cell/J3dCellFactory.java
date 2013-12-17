package esmj3dtes4.j3d.cell;

import java.io.IOException;
import java.util.zip.DataFormatException;

import utils.source.MeshSource;
import utils.source.SoundSource;
import utils.source.TextureSource;
import esmLoader.common.PluginException;
import esmLoader.common.data.plugin.PluginGroup;
import esmLoader.common.data.plugin.PluginRecord;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmLoader.loader.ESMManager;
import esmLoader.loader.WRLDChildren;
import esmj3d.j3d.cell.MorphingLandscape;
import esmj3d.j3d.cell.J3dICellFactory;
import esmj3dtes4.data.records.WRLD;

public class J3dCellFactory implements J3dICellFactory
{
	private ESMManager esmManager;

	private IRecordStore recordStore;

	private MeshSource meshSource;

	private TextureSource textureSource;

	private SoundSource soundSource;

	public J3dCellFactory(ESMManager esmManager, IRecordStore recordStore, MeshSource meshSource, TextureSource textureSource,
			SoundSource soundSource)
	{
		this.esmManager = esmManager;
		this.recordStore = recordStore;
		this.meshSource = meshSource;
		this.textureSource = textureSource;
		this.soundSource = soundSource;
	}

	@Override
	public MorphingLandscape makeLODLandscape(int lodX, int lodY, int scale, int worldFormId)
	{
		int formId = -1;
		WRLD wrld = getWRLD(worldFormId);
		// use parent first
		if (wrld.WNAM != null && wrld.WNAM.formId != -1)
		{
			formId = wrld.WNAM.formId;
		}
		else
		{
			formId = worldFormId;
		}
		return new Tes4LODLandscape(lodX, lodY, scale, formId, meshSource, textureSource);
	}

	private WRLD getWRLD(int formId)
	{
		try
		{
			PluginRecord record = esmManager.getWRLD(formId);
			WRLD wrld = new WRLD(new Record(record, -1));
			return wrld;
		}
		catch (DataFormatException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (PluginException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isWRLD(int formId)
	{
		return getWRLD(formId) != null;
	}

	@Override
	public J3dCELLPersistent makeBGWRLDPersistent(int formId, boolean makePhys)
	{

		WRLD wrld = getWRLD(formId);
		if (wrld != null)
		{
			WRLDChildren children = esmManager.getWRLDChildren(formId);

			PluginRecord cell = children.getCell();
			if (cell != null)
			{
				PluginGroup cellChildren = children.getCellChildren();
				if (cellChildren != null)
				{
					return new J3dCELLPersistent(wrld, recordStore, new Record(cell, -1), ESMManager.getChildren(cellChildren,
							PluginGroup.CELL_PERSISTENT), makePhys, meshSource, textureSource, soundSource);
				}
			}

		}
		else
		{
			System.out.println("makeBGWRLDPersistent bad formId not wrld " + formId);
		}
		return null;
	}

	@Override
	public J3dCELLTemporary makeBGWRLDTemporary(int wrldFormId, int x, int y, boolean makePhys)
	{
		int cellId = esmManager.getWRLDExtBlockCELLId(wrldFormId, x, y);
		return makeBGWRLDTemporary(cellId, makePhys);
	}

	@Override
	public J3dCELLTemporary makeBGWRLDTemporary(int cellId, boolean makePhys)
	{
		if (cellId == -1)
			return null;

		try
		{

			PluginRecord record = esmManager.getWRLDExtBlockCELL(cellId);

			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getWRLDExtBlockCELLChildren(record.getFormID());

				if (cellChildren != null)
				{
					return new J3dCELLTemporary(recordStore, new Record(record, -1), ESMManager.getChildren(cellChildren,
							PluginGroup.CELL_TEMPORARY), makePhys, meshSource, textureSource, soundSource);
				}
			}

		}
		catch (PluginException e1)
		{
			e1.printStackTrace();
		}
		catch (DataFormatException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		return null;
	}

	@Override
	public J3dCELLDistant makeBGWRLDDistant(int wrldFormId, int x, int y, boolean makePhys)
	{
		int cellId = esmManager.getWRLDExtBlockCELLId(wrldFormId, x, y);
		return makeBGWRLDDistant(cellId, makePhys);
	}

	@Override
	public J3dCELLDistant makeBGWRLDDistant(int cellId, boolean makePhys)
	{
		if (cellId == -1)
			return null;

		try
		{
			PluginRecord record = esmManager.getWRLDExtBlockCELL(cellId);
			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getWRLDExtBlockCELLChildren(record.getFormID());
				if (cellChildren != null)
				{
					return new J3dCELLDistant(recordStore, new Record(record, -1), ESMManager.getChildren(cellChildren,
							PluginGroup.CELL_DISTANT), makePhys, meshSource, textureSource, soundSource);
				}
			}
		}
		catch (PluginException e1)
		{
			e1.printStackTrace();
		}
		catch (DataFormatException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public J3dCELLPersistent makeBGInteriorCELLPersistent(int cellId, boolean makePhys)
	{
		try
		{
			PluginRecord record = esmManager.getInteriorCELL(cellId);

			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getInteriorCELLChildren(cellId);

				return new J3dCELLPersistent(null, recordStore, new Record(record, -1), ESMManager.getChildren(cellChildren,
						PluginGroup.CELL_PERSISTENT), makePhys, meshSource, textureSource, soundSource);
			}
		}
		catch (PluginException e1)
		{
			e1.printStackTrace();
		}
		catch (DataFormatException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public J3dCELLTemporary makeBGInteriorCELLTemporary(int cellId, boolean makePhys)
	{

		try
		{
			PluginRecord record = esmManager.getInteriorCELL(cellId);

			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getInteriorCELLChildren(cellId);

				return new J3dCELLTemporary(recordStore, new Record(record, -1), ESMManager.getChildren(cellChildren,
						PluginGroup.CELL_TEMPORARY), makePhys, meshSource, textureSource, soundSource);
			}
		}
		catch (PluginException e1)
		{
			e1.printStackTrace();
		}
		catch (DataFormatException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public J3dCELLDistant makeBGInteriorCELLDistant(int cellId, boolean makePhys)
	{

		try
		{
			PluginRecord record = esmManager.getInteriorCELL(cellId);

			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getInteriorCELLChildren(cellId);

				return new J3dCELLDistant(recordStore, new Record(record, -1), ESMManager.getChildren(cellChildren,
						PluginGroup.CELL_DISTANT), makePhys, meshSource, textureSource, soundSource);
			}
		}
		catch (PluginException e1)
		{
			e1.printStackTrace();
		}
		catch (DataFormatException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public String getMainESMFileName()
	{
		return esmManager.getName();
	}
}
