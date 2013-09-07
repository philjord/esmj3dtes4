package esmj3dtes4.data.subrecords;

import tools.io.ESMByteConvert;
import esmj3d.data.shared.subrecords.FormID;

public class CSDT
{
	public int soundType;

	public FormID csdi;

	public CSDC csdc;

	public CSDT(byte[] bytes)
	{
		soundType = ESMByteConvert.extractInt(bytes, 0);
	}
}
