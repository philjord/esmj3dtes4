package esmj3dtes4.data.subrecords;

import tools.io.ESMByteConvert;

public class RNAM
{
	public byte attackReach;

	public RNAM(byte[] bytes)
	{
		attackReach = ESMByteConvert.extractByte(bytes, 0);
	}
}
