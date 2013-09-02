package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class LVLF
{
	public byte flags;

	public LVLF(byte[] bytes)
	{
		flags = ESMByteConvert.extractByte(bytes, 0);
	}
}
