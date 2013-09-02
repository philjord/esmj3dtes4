package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class LNAM
{
	public float hairLength;

	public LNAM(byte[] bytes)
	{
		hairLength = ESMByteConvert.extractFloat(bytes, 0);
	}
}
