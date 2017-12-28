package esmj3dtes4.data.records;

import java.util.List;

import esmio.common.data.record.Record;
import esmio.common.data.record.Subrecord;
import esmj3d.data.shared.records.GenericDOOR;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import tools.io.ESMByteConvert;

public class DOOR extends GenericDOOR
{
	public FormID SCRI;

	public int TNAM = -1; //random teleport destination

	public DOOR(Record recordData)
	{
		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new LString(bs);
			}
			else if (sr.getSubrecordType().equals("SCRI"))
			{
				SCRI = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
				TNAM = ESMByteConvert.extractInt(bs, 0);
			}
			else
			{
				//System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
