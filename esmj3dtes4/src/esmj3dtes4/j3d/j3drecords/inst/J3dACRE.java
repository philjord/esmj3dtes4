package esmj3dtes4.j3d.j3drecords.inst;

import utils.source.MediaSources;
import esmLoader.common.data.record.IRecordStore;
import esmLoader.common.data.record.Record;
import esmj3d.j3d.j3drecords.inst.J3dRECODynInst;
import esmj3dtes4.data.records.ACRE;
import esmj3dtes4.data.records.CREA;
import esmj3dtes4.j3d.j3drecords.type.J3dCREA;

public class J3dACRE extends J3dRECODynInst
{
	public J3dACRE(ACRE acre, IRecordStore master, MediaSources mediaSources)
	{
		super(acre, false, false);
		Record baseRecord = master.getRecord(acre.NAME.formId);
		if (baseRecord.getRecordType().equals("CREA"))
		{
			CREA crea = new CREA(baseRecord);
			setJ3dRECOType(new J3dCREA(crea, master, mediaSources));
		}
		else
		{
			System.out.println("ACRE record type not converted to j3d " + baseRecord.getRecordType());
		}

	}
}
