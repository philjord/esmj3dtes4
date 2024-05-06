Esm Record Formats
===
 
## URL

<p><a href="https://en.uesp.net/wiki/Oblivion:Oblivion">https://en.uesp.net/wiki/Oblivion:Oblivion</a></p>

<p><a href="https://en.uesp.net/wiki/Oblivion_Mod:Modding#Toolmaker_Info">https://en.uesp.net/wiki/Oblivion_Mod:Modding#Toolmaker_Info</a></p>

<p><a href="https://en.uesp.net/wiki/Oblivion_Mod:Mod_File_Format">https://en.uesp.net/wiki/Oblivion_Mod:Mod_File_Format</a></p>
 
## My cut of it  



					 
<p>An <b>ACHR</b> record represents an <a href="https://en.uesp.net/wiki/Oblivion:NPCs" title="Oblivion:NPCs">NPC</a> placed in a cell.</p>
<table class="wikitable" width="100%">
<tbody><tr>
<th width="10%">Subrecord</th>
<th width="10%"><a href="https://en.uesp.net/wiki/Oblivion_Mod:File_Format_Conventions" title="Oblivion Mod:File Format Conventions">Type</a></th>
<th>Info</th>
</tr>
<tr>
<td>EDID</td>
<td>zstring</td>
<td>Reference editor ID</td>
</tr>
<tr>
<td>NAME</td>
<td>formid</td>
<td>Base object</td>
</tr>
<tr>
<td>XRGD</td>
<td>28 byte structure</td>
<td>Ragdoll Data (optional)</td>
</tr>
<tr>
<td rowspan="2">XESP</td>
<td>formid</td>
<td>Parent object (optional)</td>
</tr>
<tr>
<td>ulong</td>
<td>Flags</td>
</tr>
<tr>
<td>XHRS</td>
<td>formid</td>
<td>Horse (optional)</td>
</tr>
<tr>
<td>XMRC</td>
<td>formid</td>
<td>Merchant container (optional)</td>
</tr>
<tr>
<td>XSCL</td>
<td>float</td>
<td>Scale (if not 1.00)</td>
</tr>
<tr>
<td rowspan="6">DATA</td>
<td>float</td>
<td>X position</td>
</tr>
<tr>
<td>float</td>
<td>Y position</td>
</tr>
<tr>
<td>float</td>
<td>Z position</td>
</tr>
<tr>
<td>float</td>
<td>X rotation (radians)</td>
</tr>
<tr>
<td>float</td>
<td>Y rotation (radians)</td>
</tr>
<tr>
<td>float</td>
<td>Z rotation (radians)</td>
</tr>
</tbody></table>


<p>An <b>ACRE</b> record represents a creature placed in a cell.</p>
<table class="wikitable" width="100%">
<tbody><tr>
<th width="10%">Subrecord</th>
<th width="10%"><a href="/wiki/Oblivion_Mod:File_Format_Conventions" title="Oblivion Mod:File Format Conventions">Type</a></th>
<th>Info</th>
</tr>
<tr>
<td>EDID</td>
<td>zstring</td>
<td>Reference editor ID</td>
</tr>
<tr>
<td>NAME</td>
<td>formid</td>
<td>Base object</td>
</tr>
<tr>
<td>XRGD</td>
<td>28 byte structure</td>
<td>Ragdoll Data (optional)</td>
</tr>
<tr>
<td rowspan="2">XESP</td>
<td>formid</td>
<td>Parent object (optional)</td>
</tr>
<tr>
<td>ulong</td>
<td>Flags</td>
</tr>
<tr>
<td>XOWN</td>
<td>formid</td>
<td>Owner (Optional and references an NPC or faction)</td>
</tr>
<tr>
<td>XGLB</td>
<td>formid</td>
<td>Global variable (Optional if owner is NPC)</td>
</tr>
<tr>
<td>XRNK</td>
<td>long</td>
<td>Faction rank (Optional if owner is faction)</td>
</tr>
<tr>
<td>XSCL</td>
<td>float</td>
<td>Scale (if not 1.00)</td>
</tr>
<tr>
<td rowspan="6">DATA</td>
<td>float</td>
<td>X position</td>
</tr>
<tr>
<td>float</td>
<td>Y position</td>
</tr>
<tr>
<td>float</td>
<td>Z position</td>
</tr>
<tr>
<td>float</td>
<td>X rotation (radians)</td>
</tr>
<tr>
<td>float</td>
<td>Y rotation (radians)</td>
</tr>
<tr>
<td>float</td>
<td>Z rotation (radians)</td>
</tr>
</tbody></table>



<p>An <b>EFSH</b> record represents a particle or other magic effect.</p>
<table class="wikitable" width="100%">
<tbody><tr>
<th width="10%">Subrecord</th>
<th width="10%"><a href="/wiki/Oblivion_Mod:File_Format_Conventions" title="Oblivion Mod:File Format Conventions">Type</a></th>
<th>Info</th>
</tr>
<tr>
<td>EDID</td>
<td>zstring</td>
<td>Reference editor ID</td>
</tr>
<tr>
<td>ICON</td>
<td>ZString</td>
<td>points to an Effects\ Fire\ Magic\ type texture, can be a blank string (Optional for 2 entries)</td>
</tr>
<tr>
<td>ICO2</td>
<td>ZString</td>
<td>points to an Effects\ Fire\ Magic\ type texture, can be a blank string (Optional with ICON for 2 entries)</td>
</tr>
<tr>
<tr>
<td>DATA</td>
<td>224 bytes or 96 bytes</td>
<td>looks like 64 byes per ICON or ICO2 and 96 other bytes (2 entries with no ICON/ICO2 have 96 bytes)</td>
</tr>
<tr>
</tbody></table>




<p>An <b>IDLE</b> record represents idle animation for a ACRE or ACHR ?.</p>
<table class="wikitable" width="100%">
<tbody><tr>
<th width="10%">Subrecord</th>
<th width="10%"><a href="/wiki/Oblivion_Mod:File_Format_Conventions" title="Oblivion Mod:File Format Conventions">Type</a></th>
<th>Info</th>
</tr>
<tr>
<td>EDID</td>
<td>zstring</td>
<td>Reference editor ID</td>
</tr>
<tr>
<td>MODL</td>
<td>ZString</td>
<td>points to a animation file like Characters\_Male\IdleAnims\SE_SpecialIdle_BlackSmith01.kf or a folder like Characters\_Male\IdleAnims</td>
</tr>
<tr>
<td>MODB</td>
<td>Integer</td>
<td>a low number?</td>
</tr>
<tr>
<td>CTDT</td>
<td>20 bytes</td>
<td>1 record has 3 of these, with no CTDA corruption? (Optional)</td>
</tr>
<tr>
<tr>
<td>CTDA</td>
<td>24 bytes</td>
<td>?? (Optional)</td>
</tr>
<td>ANAM</td>
<td>??</td>
<td>??</td>
</tr>
<tr>
<td>DATA</td>
<td>??</td>
<td>??</td>
</tr>

</tbody></table>




<p>An <b>RACE</b> record represents a race.</p>
<table class="wikitable" width="100%">
<tbody><tr>
<th width="4%">C</th>
<th width="4%">Subrecord</th>
<th width="10%"><a href="/wiki/Oblivion_Mod:File_Format_Conventions" title="Oblivion Mod:File Format Conventions">Type</a></th>
<th>Info</th>
</tr>
<tr>
<td>+</td>
<td>EDID</td>
<td>zstring</td>
<td>Reference editor ID</td>
</tr>
<tr>
<td>-</td>
<td>FULL</td>
<td>ZString</td>
<td>name of race (optional 14 out of 15 have it)</td>
</tr>
<tr>
<td>+</td>
<td>DESC</td>
<td>ZString</td>
<td>a description of the race</td>
</tr>
<tr>
<td>*</td>
<td>SPLO</td>
<td>FormID</td>
<td>points to a SPEL record so presumably a race ability</td>
</tr>
<tr>
<td>-</td>
<td>XNAM</td>
<td>8 bytes</td>
<td>4b to formId of RACE reco, 4b?</td>
</tr>
<tr>
<td>+</td>
<td>DATA</td>
<td>36 bytes</td>
<td>  b12 1 int?     b16-32 4 floats  b32 1 int</td>
</tr>
<tr>
<td>-</td>
<td>VNAM</td>
<td>8 bytes</td>
<td> 2 identical formids to RACE reco</td>
</tr>
<tr>
<td>-</td>
<td>DNAM</td>
<td>8 bytes</td>
<td>4 byte formId to a HAIR reco, not sure what second 4 is</td>
</tr>
<tr>
<td>+</td>
<td>CNAM</td>
<td>1 byte</td>
<td>??</td>
</tr>
<tr>
<td>-</td>
<td>PNAM</td>
<td>float</td>
<td>if present value is 5.0</td>
</tr>
<tr>
<td>-</td>
<td>UNAM</td>
<td>float</td>
<td>if present value is 3.0 PNAM and UNAM appear together for Sheogorath,GoldenSaint,DarkSeducer,Nord,Breton,WoodElf,DarkElf</td>
</tr>
<tr>
<td>+</td>
<td>ATTR</td>
<td>16bytes</td>
<td>??</td>
</tr>
<tr><td>
NAM0 marks the start of the INDX/MODL/MODB/ICON list for generic head parts, 
NAM1 has a 
	MNAM followed by INDX/ICON for male body, hands, legs, feet dds files and final INDX 
	FNAM followed by INDX/ICON for female body, hands, legs, feet dds files and final INDX </td></tr>
<tr>
<td>*</td>
<td>INDX</td>
<td>Integer</td>
<td>part name reference, starts at 0 and goes up</td>
</tr>
<tr>
<td>*</td>
<td><dl><dd>MODL</dl></dd></td>
<td>ZString</td>
<td>Model filename</td>
</tr>
<tr>
<td>*</td>
<td><dl><dd>MODB</dl></dd></td>
<td>Integer</td>
<td>Unknown, always follows a MODL</td>
</tr>
<tr>
<td>*</td>
<td><dl><dd>ICON</dl></dd></td>
<td>ZString</td>
<td>Texture filename</td>
</tr>
<tr>
<td>+</td>
<td>HNAM</td>
<td>56bytes</td>
<td>Hair data?</td>
</tr>
<tr>
<td>+</td>
<td>ENAM</td>
<td>FormId</td>
<td>EYES reco if 4, but some are 0,4,8,12,16 bytes, formId and modifiers?</td>
</tr>
<tr>
<td>+</td>
<td>FGGS</td>
<td>200b</td>
<td> </td>
</tr>
<tr>
<td>+</td>
<td>FGGA</td>
<td>120b</td>
<td> </td>
</tr>
<tr>
<td>+</td>
<td>FGTS</td>
<td>200b</td>
<td> </td>
</tr>
<tr>
<td>+</td>
<td>SNAM</td>
<td>2b</td>
<td> </td>
</tr>



</tbody></table>



