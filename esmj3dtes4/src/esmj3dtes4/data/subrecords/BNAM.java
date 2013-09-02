package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class BNAM
{
	public float baseScale;

	public BNAM(byte[] bytes)
	{
		baseScale = ESMByteConvert.extractFloat(bytes, 0);
	}
}
