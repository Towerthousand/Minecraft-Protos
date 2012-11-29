package net.dirbaio.omg.functions;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Function2D extends Function
{
	public abstract double[][] get2DData(int px, int pz, int sx, int sz);
    
    public BufferedImage render(int px, int pz, int sx, int sz, double min, double max)
    {
        prepare(px, pz, sx, sz);
        return renderPrepared(px, pz, sx, sz, min, max);
    }
    
    public BufferedImage renderPrepared(int px, int pz, int sx, int sz, double min, double max)
    {
        BufferedImage res = new BufferedImage(sx, sz, BufferedImage.TYPE_INT_RGB);
        double[][] data = get2DData(px, pz, sx, sz);
        
        for(int x = 0; x < sx; x++)
            for(int z = 0; z < sz; z++)
            {
                double val = data[x][z];
                val = (val-min)/(max-min)*256;
                int shade = (int) val;
                int col = shade | (shade<<8) | (shade<<16);
                if(shade < 0 || shade > 255)
                    col = 0x000000FF;
                res.setRGB(x, z, col);
            }
        return res;
    }
    
    public BufferedImage renderChunks(int cx, int cz, int sx, int sz, double min, double max)
    {
        prepare(cx*16, cz*16, sx*16, sz*16);

        BufferedImage res = new BufferedImage(sx*16, sz*16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = res.createGraphics();

        for(int x = 0; x < sx; x++)
            for(int z = 0; z < sz; z++)
                g.drawImage(renderPrepared((cx+x)*16, (cz+z)*16, 16, 16, min, max), x*16, z*16, null);

        return res;
    }
}
