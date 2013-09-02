package esmj3dtes4.data.subrecords;

import java.util.ArrayList;

public class KFFZ
{
	public String[] fileNames;

	public KFFZ(byte[] bytes)
	{
		ArrayList<String> fns = new ArrayList<String>();
		int start = 0;
		// extra null at end
		for (int i = 0; i < bytes.length - 1; i++)
		{
			if (bytes[i] == 0)
			{
				fns.add(new String(bytes, start, i - start));
				start = i + 1;
			}
		}
		fileNames = new String[fns.size()];
		fns.toArray(fileNames);
	}
}
