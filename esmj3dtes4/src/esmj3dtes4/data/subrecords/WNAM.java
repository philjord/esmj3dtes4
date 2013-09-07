package esmj3dtes4.data.subrecords;

import tools.io.ESMByteConvert;

public class WNAM
{
	public float footWeight;

	public WNAM(byte[] bytes)
	{
		footWeight = ESMByteConvert.extractFloat(bytes, 0);
	}
}
