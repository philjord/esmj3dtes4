package esmj3dtes4.j3d.cell;

import java.util.ArrayList;
import java.util.List;

import org.jogamp.java3d.Node;
import org.jogamp.vecmath.Quat4f;

import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.data.shared.records.InstRECO;
import esmj3d.data.shared.records.LAND;
import esmj3d.j3d.BethRenderSettings;
import esmj3d.j3d.BethRenderSettings.UpdateListener;
import esmj3d.j3d.cell.J3dCELLGeneral;
import esmj3d.j3d.j3drecords.inst.J3dLAND;
import esmj3d.j3d.j3drecords.inst.J3dRECOInst;
import esmj3dtes4.data.records.ACHR;
import esmj3dtes4.data.records.ACRE;
import esmj3dtes4.data.records.CELL;
import esmj3dtes4.data.records.REFR;
import esmj3dtes4.j3d.j3drecords.inst.J3dACHR;
import esmj3dtes4.j3d.j3drecords.inst.J3dACRE;
import esmj3dtes4.j3d.j3drecords.inst.J3dREFRFactory;
import utils.source.MediaSources;

public abstract class J3dCELL extends J3dCELLGeneral implements UpdateListener
{
	protected CELL cell;

	private ArrayList<J3dRECOInst> j3dRECOInsts = new ArrayList<J3dRECOInst>();
	
	protected J3dLAND j3dLAND;

	public J3dCELL(IRecordStore master, Record cellRecord, int worldId, List<Record> children, boolean makePhys, MediaSources mediaSources)
	{
		super(master, worldId, children, makePhys, mediaSources);
		cell = new CELL(cellRecord);
		setCell(cell);

		BethRenderSettings.addUpdateListener(this);
	}

	@Override
	public void renderSettingsUpdated()
	{
		if (!makePhys)
		{
			for (J3dRECOInst j3dRECOInst : j3dRECOInsts)
			{
				j3dRECOInst.renderSettingsUpdated();
			}
		}
	}

	protected void addJ3dRECOInst(J3dRECOInst j3dRECOInst)
	{
		if (j3dRECOInst != null)
		{
			addChild((Node) j3dRECOInst);
			j3dRECOInsts.add(j3dRECOInst);
		}
	}

	@Override
	public Node makeJ3dRECOFar(Record record)
	{
		Node ret = null;
		try
		{
			if (record.getRecordType().equals("REFR"))
			{
				ret = J3dREFRFactory.makeJ3DReferFar(new REFR(record), master, mediaSources);
			}
			else
			{
				System.err.println("Far record not REFR " + record.getRecordType());
			}
		}
		catch (NullPointerException e)
		{
			if (e.getStackTrace().length > 0)
				System.out.println("J3dCELL.makeJ3dRECOFar " + cell.formId + " - null pointer making record " + record + " " + record.getRecordType()
						+ " in " + e.getStackTrace()[0]);
			else
				System.out.println("J3dCELL.makeJ3dRECOFar " + cell.formId + " - null pointer making record " + record + " " + record.getRecordType());

			if (record.getRecordType().equals("REFR"))
			{
				REFR refr = new REFR(record);
				Record baseRecord = master.getRecord(refr.NAME.formId);
				System.out.println("And it's a REFR with base of " + baseRecord.getRecordType());
			}
		}

		return ret;
	}

	@Override
	public J3dRECOInst makeJ3dRECO(Record record)
	{
		J3dRECOInst ret = null;
		try
		{
			if (record.getRecordType().equals("REFR"))
			{
				ret = J3dREFRFactory.makeJ3DRefer(new REFR(record), makePhys, master, mediaSources);
			}
			else if (record.getRecordType().equals("ACRE"))
			{
				if (!makePhys)
				{
					ACRE acre = new ACRE(record);

					// does a parent enablage flag exists, and is is defaulted to off?
					if (acre.xesp != null && InstRECO.getParentEnable(acre, master) != BethRenderSettings.isFlipParentEnableDefault())
						return null;

					if (J3dREFRFactory.ACREallowed(acre, master))
						ret = new J3dACRE(new ACRE(record), master, mediaSources);
					else
						return null;
				}
			}
			else if (record.getRecordType().equals("ACHR"))
			{
				if (!makePhys)
				{
					ACHR achr = new ACHR(record);
					// does a parent enablage flag exists, and is is defaulted to off?
					if (achr.xesp != null && InstRECO.getParentEnable(achr, master) != BethRenderSettings.isFlipParentEnableDefault())
						return null;

					if (!J3dREFRFactory.NATURAL_ANIMALS_ONLY)
						ret = new J3dACHR(new ACHR(record), master, mediaSources);
					else
						return null;
				}
			}
			else if (record.getRecordType().equals("PGRD"))
			{
			}
			else if (record.getRecordType().equals("LAND"))
			{
				J3dLAND j3dLAND;
				if (makePhys)
				{
					Record parentLANDrec = ((J3dCellFactory) master).getParentWRLDLAND(worldId, (int) instCell.getTrans().x,
							(int) instCell.getTrans().y);
					if (parentLANDrec != null)
						j3dLAND = new J3dLAND(new LAND(parentLANDrec));
					else
						j3dLAND = new J3dLAND(new LAND(record));
				}
				else
				{
					Record parentLANDrec = ((J3dCellFactory) master).getParentWRLDLAND(worldId, (int) instCell.getTrans().x,
							(int) instCell.getTrans().y);
					if (parentLANDrec != null)
						j3dLAND = new J3dLAND(new LAND(parentLANDrec), master, mediaSources.getTextureSource());
					else
						j3dLAND = new J3dLAND(new LAND(record), master, mediaSources.getTextureSource());
				}
				j3dLAND.setLocation(cellLocation, new Quat4f(0, 0, 0, 1));
				ret = j3dLAND;
			}
			else
			{
				System.out.println("makeJ3dRECO in J3dCELL Record type not converted to j3d " + record.getRecordType());
			}
		}
		catch (NullPointerException e)
		{
			if (e.getStackTrace().length > 0)
				System.out.println("J3dCELL.makeJ3dRECO " + cell.formId + " - null pointer making record " + record + " " + record.getRecordType()
						+ " in " + e.getStackTrace()[0]);
			else
				System.out.println("J3dCELL.makeJ3dRECO " + cell.formId + " - null pointer making record " + record + " " + record.getRecordType());

			if (record.getRecordType().equals("REFR"))
			{
				REFR refr = new REFR(record);
				Record baseRecord = master.getRecord(refr.NAME.formId);
				System.out.println("And it's a REFR with base of " + baseRecord.getRecordType());
			}
		}

		if (ret != null)
		{
			j3dRECOs.put(ret.getRecordId(), ret);
		}
		return ret;
	}
	
	@Override
	public J3dLAND getJ3dLAND() {
		//If  didn't load land from a record above, check if the parent land should be used
		if(j3dLAND == null) {		
			if (makePhys)
			{
				Record parentLANDrec = ((J3dCellFactory) master).getParentWRLDLAND(worldId, (int) instCell.getTrans().x,
						(int) instCell.getTrans().y);
				if (parentLANDrec != null) {
					j3dLAND = new J3dLAND(new LAND(parentLANDrec));
					j3dLAND.setLocation(cellLocation, new Quat4f(0, 0, 0, 1));
				}
			}
			else
			{
				Record parentLANDrec = ((J3dCellFactory) master).getParentWRLDLAND(worldId, (int) instCell.getTrans().x,
						(int) instCell.getTrans().y);
									
				if (parentLANDrec != null) {
					j3dLAND = new J3dLAND(new LAND(parentLANDrec), master, mediaSources.getTextureSource());
					j3dLAND.setLocation(cellLocation, new Quat4f(0, 0, 0, 1));
				}				
			}
		}
		
		return j3dLAND;
	}
}
