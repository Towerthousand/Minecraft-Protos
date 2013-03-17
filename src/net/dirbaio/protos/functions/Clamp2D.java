/*
 * Copyright (C) 2013 dirbaio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.dirbaio.protos.functions;

public class Clamp2D extends Function2D
{

    public Function2D f;
    public double min = -1, max = 1;

    public Clamp2D()
    {
    }

    public Clamp2D(Function2D f, double min, double max)
    {
        this.f = f;
        this.min = min;
        this.max = max;
    }

    @Override
    public double[] get2DData(int px, int pz, int sx, int sz)
    {
        double[] d = f.get2DData(px, pz, sx, sz);

        int s = sx * sz;
        for (int i = 0; i < s; i++)
        {
            double n = d[i];
            if (n < min)
                n = min;
            if (n > max)
                n = max;

            d[i] = n;
        }

        return d;
    }

    @Override
    public void prepare(int x, int z, int sx, int sz)
    {
        f.prepare(x, z, sx, sz);
    }
}
