package esmj3dtes4.data.records;

import java.util.ArrayList;

import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import esmmanager.common.data.record.Record;
import esmmanager.common.data.record.Subrecord;

public class REGN extends RECO
{
	public ZString EDID;

	public REGN(Record recordData)
	{
		super(recordData);
		ArrayList<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getData();

			if (sr.getType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getType().equals("ICON"))
			{

			}
			else if (sr.getType().equals("RDGS"))
			{

			}
			else if (sr.getType().equals("RCLR"))
			{
				//RCLR = new RCLR(bs);
			}
			else if (sr.getType().equals("WNAM"))
			{
				//WNAM = new WNAM(bs);
			}
			else if (sr.getType().equals("RPLI"))
			{
				//RPLI = new RPLI(bs);
			}
			else if (sr.getType().equals("RPLD"))
			{
				//RPLD = new RPLD(bs);
			}
			else if (sr.getType().equals("RDAT"))
			{
				//RDAT = new RDAT(bs);
			}
			else if (sr.getType().equals("RDWT"))
			{
				//RDWT = new RDWT(bs);
			}
			else if (sr.getType().equals("RDAT"))
			{
				//RDAT = new RDAT(bs);
			}
			else if (sr.getType().equals("RDOT"))
			{
				//RDOT = new RDOT(bs);
			}
			else if (sr.getType().equals("RDMD"))
			{
				//RDMD = new RDMD(bs);
			}
			else if (sr.getType().equals("RDMP"))
			{
				//RDMP = new RDMP(bs);
			}
			else if (sr.getType().equals("RDSD"))
			{
				//RDSD = new RDSD(bs);
			}
			else
			{
				System.out.println("unhandled : " + sr.getType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
