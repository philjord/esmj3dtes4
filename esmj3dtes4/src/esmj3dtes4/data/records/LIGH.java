package esmj3dtes4.data.records;

import java.util.List;

import org.jogamp.vecmath.Color3f;

import esmj3d.data.shared.records.CommonLIGH;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmmanager.common.data.record.Record;
import esmmanager.common.data.record.Subrecord;
import tools.io.ESMByteConvert;

public class LIGH extends CommonLIGH
{
	public ZString EDID;

	public LString FULL;

	public FormID SCRI;

	public byte[] SNAM = null;

	public int flags = 0;

	public LIGH(Record recordData)
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
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new LString(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				radius = ESMByteConvert.extractInt(bs, 4);

				int r = ESMByteConvert.extractUnsignedByte(bs, 8);
				int g = ESMByteConvert.extractUnsignedByte(bs, 9);
				int b = ESMByteConvert.extractUnsignedByte(bs, 10);
				color = new Color3f(r, g, b);

				flags = ESMByteConvert.extractInt(bs, 12);
				falloffExponent = ESMByteConvert.extractFloat(bs, 16);

				fieldOfView = ESMByteConvert.extractFloat(bs, 20);
				 
				/*	System.out.println("EDID " + EDID.editorId);
				 System.out.println("radius " + radius);
				 System.out.println("color " + color);
				 System.out.println("falloffExponent " + falloffExponent);
				 System.out.println("fieldOfView " + fieldOfView);

				 System.out.println("flags " + ((flags & 0x00000200) != 0));
				 */
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
				fade = ESMByteConvert.extractFloat(bs, 0);
			}
			else if (sr.getSubrecordType().equals("SCRI"))
			{
				SCRI = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MODB"))
			{
				MODL.addMODBSub(bs);
			}
			else if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}

			else if (sr.getSubrecordType().equals("SNAM"))
			{
				SNAM = bs;
			}
			else if (sr.getSubrecordType().equals("ICON"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
