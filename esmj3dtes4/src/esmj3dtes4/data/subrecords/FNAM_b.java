package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class FNAM_b
{
	public int faceRace;

	public FNAM_b(byte[] bytes)
	{
		faceRace = ESMByteConvert.extractShort(bytes, 0);
	}
}
