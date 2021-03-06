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

public class Clamp3D extends Function3D
{

    public Function3D f;
    public double min = -1, max = 1;
    
    public Clamp3D()
    {
    }

    public Clamp3D(Function3D f, double min, double max)
    {
        this.f = f;
        this.min = min;
        this.max = max;
    }

    @Override
    public double[] get3DData(int px, int pz, int py, int sy, int sx, int sz)
    {
        double[] d = f.get3DData(px, pz, py, sy, sx, sz);

        int s = sx * sy * sz;
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
