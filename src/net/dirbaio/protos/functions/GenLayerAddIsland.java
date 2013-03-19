package net.dirbaio.protos.functions;

public class GenLayerAddIsland extends BiomeFunction
{
    public BiomeFunction base;
    
    public GenLayerAddIsland(BiomeFunction parent)
    {
        this.base = parent;
    }

    @Override
    public int[] getBiomeData(int px, int pz, int sx, int sz)
    {
        int var5 = px - 1;
        int var6 = pz - 1;
        int var7 = sx + 2;
        int var8 = sz + 2;
        int[] var9 = this.base.getBiomeData(var5, var6, var7, var8);
        int[] var10 = IntCache.getIntCache(sx * sz);

        for (int var11 = 0; var11 < sz; ++var11)
        {
            for (int var12 = 0; var12 < sx; ++var12)
            {
                int var13 = var9[var12 + 0 + (var11 + 0) * var7];
                int var14 = var9[var12 + 2 + (var11 + 0) * var7];
                int var15 = var9[var12 + 0 + (var11 + 2) * var7];
                int var16 = var9[var12 + 2 + (var11 + 2) * var7];
                int var17 = var9[var12 + 1 + (var11 + 1) * var7];
                this.setPosForRandom(var12 + px, var11 + pz);

                if (var17 == 0 && (var13 != 0 || var14 != 0 || var15 != 0 || var16 != 0))
                {
                    int var18 = 1;
                    int var19 = 1;

                    if (var13 != 0 && this.nextInt(var18++) == 0)
                    {
                        var19 = var13;
                    }

                    if (var14 != 0 && this.nextInt(var18++) == 0)
                    {
                        var19 = var14;
                    }

                    if (var15 != 0 && this.nextInt(var18++) == 0)
                    {
                        var19 = var15;
                    }

                    if (var16 != 0 && this.nextInt(var18++) == 0)
                    {
                        var19 = var16;
                    }

                    if (this.nextInt(3) == 0)
                    {
                        var10[var12 + var11 * sx] = var19;
                    }
                    else if (var19 == Biome.icePlains.biomeID)
                    {
                        var10[var12 + var11 * sx] = Biome.frozenOcean.biomeID;
                    }
                    else
                    {
                        var10[var12 + var11 * sx] = 0;
                    }
                }
                else if (var17 > 0 && (var13 == 0 || var14 == 0 || var15 == 0 || var16 == 0))
                {
                    if (this.nextInt(5) == 0)
                    {
                        if (var17 == Biome.icePlains.biomeID)
                        {
                            var10[var12 + var11 * sx] = Biome.frozenOcean.biomeID;
                        }
                        else
                        {
                            var10[var12 + var11 * sx] = 0;
                        }
                    }
                    else
                    {
                        var10[var12 + var11 * sx] = var17;
                    }
                }
                else
                {
                    var10[var12 + var11 * sx] = var17;
                }
            }
        }

        return var10;
    }
}