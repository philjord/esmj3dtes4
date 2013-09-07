package esmj3dtes4.data.subrecords;

import tools.io.ESMByteConvert;

public class TNAM_b
{
	public float turningSpeed;

	public TNAM_b(byte[] bytes)
	{
		turningSpeed = ESMByteConvert.extractFloat(bytes, 0);
	}
}
