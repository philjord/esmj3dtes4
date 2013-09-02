package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class TNAM
{
	public float turningSpeed;

	public TNAM(byte[] bytes)
	{
		turningSpeed = ESMByteConvert.extractFloat(bytes, 0);
	}
}
