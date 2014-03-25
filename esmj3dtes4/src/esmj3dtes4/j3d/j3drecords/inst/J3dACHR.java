package esmj3dtes4.j3d.j3drecords.inst;

import utils.source.MediaSources;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmj3d.j3d.j3drecords.inst.J3dRECODynInst;
import esmj3dtes4.data.records.ACHR;
import esmj3dtes4.data.records.NPC_;
import esmj3dtes4.j3d.j3drecords.type.J3dNPC_;

public class J3dACHR extends J3dRECODynInst
{
	public J3dACHR(ACHR achr, IRecordStore master, MediaSources mediaSources)
	{
		super(achr, false, false);
		Record baseRecord = master.getRecord(achr.NAME.formId);
		if (baseRecord.getRecordType().equals("NPC_"))
		{
			NPC_ npc_ = new NPC_(baseRecord);
			setJ3dRECOType(new J3dNPC_(npc_, master, mediaSources));
		}
		else
		{
			System.out.println("ACHR record type not converted to j3d " + baseRecord.getRecordType());
		}

	}
}
