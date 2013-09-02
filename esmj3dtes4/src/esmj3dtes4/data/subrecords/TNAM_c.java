package esmj3dtes4.data.subrecords;

import utils.ESMByteConvert;

public class TNAM_c
{
	public int markerData;

	public TNAM_c(byte[] bytes)
	{
		markerData = ESMByteConvert.extractShort(bytes, 0);

	}
}
