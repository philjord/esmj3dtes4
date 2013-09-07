package esmj3dtes4.data.subrecords;

import tools.io.ESMByteConvert;

public class CSCR
{
	public int inheritsSoundsFromFormId;

	public CSCR(byte[] bytes)
	{
		inheritsSoundsFromFormId = ESMByteConvert.extractInt(bytes, 0);
	}
}
