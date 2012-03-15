package net.dirbaio.omg.functions.terrain;

import net.dirbaio.omg.*;
import net.dirbaio.omg.functions.Function3D;
import net.dirbaio.omg.functions.FunctionTerrain;

public class Terrain3D extends FunctionTerrain
{

	public Function3D field;
	short block;

	public Terrain3D()
	{
	}

	public Terrain3D(Function3D field, short block)
	{
		this.field = field;
		this.block = block;
	}

	@Override
	public short[] getBlockData(int px, int pz, int sx, int sz)
	{
		short[] res = new short[sx*sz*Chunk.MAP_HEIGHT];
		double[][][] data = field.get3DData(px, 0, pz, sx, Chunk.MAP_HEIGHT, sz);
		
		int i = 0;
		for(int y = 0; y < Chunk.MAP_HEIGHT-1; y++)
			for(int x = 0; x < sx; x++)
				for(int z = 0; z < sz; z++)
				{
					double h = data[x][y][z];
					if(h > 0)
						res[i] = block;
					
					i++;
				}

		return res;
	}

	@Override
	public void prepare(int x, int z, int sx, int sz)
	{
		field.prepare(x, z, sx, sz);
	}
}
