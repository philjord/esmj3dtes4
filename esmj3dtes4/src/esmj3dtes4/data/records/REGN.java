package esmj3dtes4.data.records;

import java.util.List;

import esmio.common.data.record.Record;
import esmio.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;

public class REGN extends RECO
{
	public ZString EDID;

	public REGN(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("ICON"))
			{

			}
			else if (sr.getSubrecordType().equals("RDGS"))
			{

			}
			else if (sr.getSubrecordType().equals("RCLR"))
			{
				//RCLR = new RCLR(bs);
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
				//WNAM = new WNAM(bs);
			}
			else if (sr.getSubrecordType().equals("RPLI"))
			{
				//RPLI = new RPLI(bs);
			}
			else if (sr.getSubrecordType().equals("RPLD"))
			{
				//RPLD = new RPLD(bs);
			}
			else if (sr.getSubrecordType().equals("RDAT"))
			{
				//RDAT = new RDAT(bs);
			}
			else if (sr.getSubrecordType().equals("RDWT"))
			{
				//RDWT = new RDWT(bs);
			}
			else if (sr.getSubrecordType().equals("RDAT"))
			{
				//RDAT = new RDAT(bs);
			}
			else if (sr.getSubrecordType().equals("RDOT"))
			{
				//RDOT = new RDOT(bs);
			}
			else if (sr.getSubrecordType().equals("RDMD"))
			{
				//RDMD = new RDMD(bs);
			}
			else if (sr.getSubrecordType().equals("RDMP"))
			{
				//RDMP = new RDMP(bs);
			}
			else if (sr.getSubrecordType().equals("RDSD"))
			{
				//RDSD = new RDSD(bs);
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
