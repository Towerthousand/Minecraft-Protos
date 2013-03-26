package net.dirbaio.protos.functions;

public class GenLayerIsland extends BiomeFunction
{
    @Override
    public int[] getBiomeData(int px, int pz, int sx, int sz)
    {
        int[] res = ArrayCache.newInt(sx * sz);

        for (int z = 0; z < sz; ++z)
            for (int x = 0; x < sx; ++x)
                res[x + z * sx] = this.randForPos(10, px + x, pz + z, 0) == 0 ? 1 : 0;

        return res;
    }
}
