package net.dirbaio.protos.functions;

public class BiomeZoom extends BiomeFunction
{
    public BiomeFunction base;

    public BiomeZoom(BiomeFunction base)
    {
        this.base = base;
    }

    @Override
    public int[] getBiomeData(int px, int pz, int sx, int sz)
    {
        int baseX = px >> 1;
        int baseZ = pz >> 1;
        int baseSizeX = (sx >> 1) + 3;
        int baseSizeZ = (sz >> 1) + 3;
        int zoomedSizeX = baseSizeX * 2;
        int zoomedSizeZ = baseSizeZ * 2;
        int[] baseData = base.getBiomeData(baseX, baseZ, baseSizeX, baseSizeZ);
        int[] zoomedData = ArrayCache.newInt(zoomedSizeX * zoomedSizeZ);

        for (int z = 0; z < baseSizeZ - 1; ++z)
        {
            int currZoomedZ = (z << 1) * zoomedSizeX;
            int topleft = baseData[0 + (z + 0) * baseSizeX]; //baseData[z][0]
            int bottomleft = baseData[0 + (z + 1) * baseSizeX]; //baseData[z+1][0]

            for (int x = 0; x < baseSizeX - 1; ++x)
            {
                int topright = baseData[x + 1 + (z + 0) * baseSizeX]; //baseData[z][x+1]
                int bottomright = baseData[x + 1 + (z + 1) * baseSizeX]; //baseData[z+1][x+1]
                // Set topleft corner
                zoomedData[currZoomedZ] = topleft;
                // Set bottomleft corner
                zoomedData[currZoomedZ++ + zoomedSizeX] = this.choose(topleft, bottomleft, x + baseX, z + baseZ, 0);
                // Set topright corner
                zoomedData[currZoomedZ] = this.choose(topleft, topright, x + baseX, z + baseZ, 1);
                // Set bottomRight corner
                zoomedData[currZoomedZ++ + zoomedSizeX] = this.modeOrRandom(topleft, topright, bottomleft, bottomright, x + baseX, z + baseZ, 2);
                topleft = topright;
                bottomleft = bottomright;
            }
        }

        int[] result = ArrayCache.newInt(sx * sz);

        for (int z = 0; z < sz; ++z) {
            System.arraycopy(
                zoomedData,
                (z + (pz & 1)) * (baseSizeX << 1) + (px & 1),
                result,
                z * sx,
                sx
            );
        }

        return result;
    }

    /**
     * Chooses one of the two inputs randomly.
     */
    protected int choose(int par1, int par2, int x, int z, int n)
    {
        return this.randForPos(2, x, z, n) == 0 ? par1 : par2;
    }

    /**
     * returns the mode (most frequently occuring number) or a random number from the 4 integers provided
     */
    protected int modeOrRandom(int par1, int par2, int par3, int par4, int x, int z, int n)
    {
        if (par2 == par3 && par3 == par4) return par2;
        else if (par1 == par2 && par1 == par3) return par1;
        else if (par1 == par2 && par1 == par4) return par1;
        else if (par1 == par3 && par1 == par4) return par1;
        else if (par1 == par2 && par3 != par4) return par1;
        else if (par1 == par3 && par2 != par4) return par1;
        else if (par1 == par4 && par2 != par3) return par1;
        else if (par2 == par1 && par3 != par4) return par2;
        else if (par2 == par3 && par1 != par4) return par2;
        else if (par2 == par4 && par1 != par3) return par2;
        else if (par3 == par1 && par2 != par4) return par3;
        else if (par3 == par2 && par1 != par4) return par3;
        else if (par3 == par4 && par1 != par2) return par3;
        else if (par4 == par1 && par2 != par3) return par3;
        else if (par4 == par2 && par1 != par3) return par3;
        else if (par4 == par3 && par1 != par2) return par3;
        else
        {
            int var5 = this.randForPos(4, x, z, n);
            return var5 == 0 ? par1 : (var5 == 1 ? par2 : (var5 == 2 ? par3 : par4));
        }
    }
}
