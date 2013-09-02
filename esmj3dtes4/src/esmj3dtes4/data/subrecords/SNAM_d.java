package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class SNAM_d
{
	public int sound;

	public SNAM_d(byte[] bytes)
	{
		sound = ESMByteConvert.extractInt(bytes, 0);
	}
}
