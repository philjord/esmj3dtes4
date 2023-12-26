package esmj3dtes4.j3d.cell;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import esfilemanager.common.PluginException;
import esfilemanager.common.data.plugin.PluginGroup;
import esfilemanager.common.data.plugin.PluginRecord;
import esfilemanager.common.data.record.Record;
import esfilemanager.loader.WRLDChildren;
import esfilemanager.utils.ESMUtils;
import esmj3d.j3d.cell.AIActorServices;
import esmj3d.j3d.cell.AICellGeneral;
import esmj3d.j3d.cell.J3dICellFactory;
import esmj3d.j3d.cell.MorphingLandscape;
import esmj3dtes4.data.records.WRLD;

/**
 * This is no longer just for creating cells of teh correct class but now also handles the 
 * loading of the persistent references, and will return them if asked
 * @author phil
 *
 */
public class J3dCellFactory extends J3dICellFactory
{
	public J3dCellFactory()
	{

	}

	@Override
	public String getLODWorldName(int worldFormId)
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
		return "" + formId;
	}

	@Override
	public MorphingLandscape makeLODLandscape(int lodX, int lodY, int scale, String lodWorldFormId)
	{
		return new Tes4LODLandscape(lodX, lodY, scale, lodWorldFormId, mediaSources.getMeshSource(), mediaSources.getTextureSource());
	}

	private WRLD getWRLD(int formId)
	{
		try
		{
			PluginRecord record = esmManager.getWRLD(formId);
			WRLD wrld = new WRLD(record);
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
					return new J3dCELLPersistent(wrld, this, cell, formId, ESMUtils.getChildren(cellChildren, PluginGroup.CELL_PERSISTENT),
							makePhys, mediaSources);
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
	public Record getParentWRLDLAND(int wrldFormId, int x, int y)
	{

		WRLD wrld = getWRLD(wrldFormId);
		// use parent first
		if (wrld.WNAM != null && wrld.WNAM.formId != -1)
		{
			int parentFormId = -1;
			parentFormId = wrld.WNAM.formId;
			try
			{
				PluginRecord cellId = esmManager.getWRLDExtBlockCELL(parentFormId, x, y);
				if (cellId != null)
				{

					PluginGroup cellChildren = esmManager.getWRLDExtBlockCELLChildren(parentFormId, x, y);

					if (cellChildren != null)
					{
						//note distants are also part of close up
						for (Record record : ESMUtils.getChildren(cellChildren, PluginGroup.CELL_TEMPORARY))
						{
							if (record.getRecordType().equals("LAND"))
								return record;
						}

						for (Record record : ESMUtils.getChildren(cellChildren, PluginGroup.CELL_DISTANT))
						{
							if (record.getRecordType().equals("LAND"))
								return record;
						}
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

		}
		return null;
	}

	@Override
	public J3dCELLTemporary makeBGWRLDTemporary(int wrldFormId, int x, int y, boolean makePhys)
	{
		try
		{

			PluginRecord record = esmManager.getWRLDExtBlockCELL(wrldFormId, x, y);

			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getWRLDExtBlockCELLChildren(wrldFormId, x, y);

				if (cellChildren != null)
				{
					//note distants are also part of close up
					List<Record> records = ESMUtils.getChildren(cellChildren, PluginGroup.CELL_TEMPORARY);
					records.addAll(ESMUtils.getChildren(cellChildren, PluginGroup.CELL_DISTANT));
					return new J3dCELLTemporary(this, record, wrldFormId, records, makePhys, mediaSources);
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

		try
		{
			PluginRecord record = esmManager.getWRLDExtBlockCELL(wrldFormId, x, y);
			if (record != null)
			{
				PluginGroup cellChildren = esmManager.getWRLDExtBlockCELLChildren(wrldFormId, x, y);
				if (cellChildren != null)
				{
					List<Record> records = ESMUtils.getChildren(cellChildren, PluginGroup.CELL_DISTANT);
					return new J3dCELLDistant(this, record, wrldFormId, records, makePhys, mediaSources);
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

				return new J3dCELLPersistent(null, this, record, -1, ESMUtils.getChildren(cellChildren, PluginGroup.CELL_PERSISTENT),
						makePhys, mediaSources);
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

				return new J3dCELLTemporary(this, record, -1, ESMUtils.getChildren(cellChildren, PluginGroup.CELL_TEMPORARY), makePhys,
						mediaSources);
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

				return new J3dCELLDistant(this, record, -1, ESMUtils.getChildren(cellChildren, PluginGroup.CELL_DISTANT), makePhys,
						mediaSources);
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
	
	
	@Override
	public AICellGeneral makeAICell(int cellId, AIActorServices aiActorLocator)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AICellGeneral makeAICell(int wrldFormId, int x, int y, AIActorServices aiActorLocator)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
