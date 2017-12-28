package esmj3dtes4.j3d.j3drecords.inst;

import esmio.common.data.record.IRecordStore;
import esmio.common.data.record.Record;
import esmj3d.j3d.j3drecords.inst.J3dRECOChaInst;
import esmj3dtes4.data.records.ACRE;
import esmj3dtes4.data.records.CREA;
import esmj3dtes4.j3d.j3drecords.type.J3dCREA;
import utils.source.MediaSources;

public class J3dACRE extends J3dRECOChaInst
{
	public J3dACRE(ACRE acre, IRecordStore master, MediaSources mediaSources)
	{
		super(acre);
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
