package esmj3dtes4.data.records;

import java.util.List;

import esmj3d.data.shared.records.RECO;
import esmmanager.common.data.record.Record;
import esmmanager.common.data.record.Subrecord;

public class ROAD extends RECO
{

	public byte[] PGRP = null;

	public byte[] PGRR = null;

	public ROAD(Record recordData)
	{
		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("PGRP"))
			{
				PGRP = bs; //????
			}
			else if (sr.getSubrecordType().equals("PGRR"))
			{
				PGRR = bs; //????
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

	public String showDetails()
	{
		return "CELL : (" + formId + "|" + Integer.toHexString(formId) + ") ";
	}

}
