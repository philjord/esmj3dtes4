package esmj3dtes4.j3d.j3drecords.type;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.PointSound;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

import utils.ESConfig;
import utils.source.SoundSource;
import esmLoader.common.data.record.IRecordStore;
import esmj3d.j3d.j3drecords.type.J3dRECOType;
import esmj3dtes4.data.records.SOUN;

public class J3dSOUN extends J3dRECOType
{
	public J3dSOUN(SOUN soun, IRecordStore master, SoundSource soundSource)
	{
		super(soun, null);
		if (soun.FNAM != null)
		{

			if (soun.FNAM.str.endsWith(".wav") || soun.FNAM.str.endsWith(".mp3"))
			{
				playSound(soun, soundSource);
			}
			else
			{
				// I presume I pick one at random, I will do for now
				//TODO: how does this work now????
				/*File[] fs = f.listFiles();
				if (fs.length > 0)
				{
					int idx = (int) (Math.random() * fs.length);
					idx = (idx == fs.length) ? 0 : idx;
					playSound(fs[idx], soun);
				}*/
			}

			//TODO: I possibly need to add resume pause methods etc?
		}
	}

	private void playSound(SOUN soun, SoundSource soundSource)
	{
		//System.out.println("playing sound " + file);

		PointSound ps = new PointSound();

		ps.setSoundData(soundSource.getMediaContainer(soun.FNAM.str));
		ps.setPosition(new Point3f(0, 0, 0));
		float maxGain = soun.SNDX.staticAttenuation / 100f;
		ps.setInitialGain(0);
		ps.setDistanceGain(new float[]
		{ 0, soun.SNDX.minimumAttenuationDistance * 5 * ESConfig.ES_TO_METERS_SCALE,
				soun.SNDX.maximumAttenuationDistance * 100 * ESConfig.ES_TO_METERS_SCALE }, new float[]
		{ maxGain, maxGain, 0 });
		ps.setEnable(true);
		ps.setPause(false);

		ps.setSchedulingBounds(new BoundingSphere(new Point3d(), Double.POSITIVE_INFINITY));
		ps.setLoop(soun.SNDX.isLoop() ? -1 : 1);
		ps.setContinuousEnable(soun.SNDX.isLoop());
		//TODO: disbaled for now addChild(ps);

	}
}
