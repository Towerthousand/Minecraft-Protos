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

package net.dirbaio.protos;

import javax.swing.ImageIcon;

public class Images
{
    public static ImageIcon neww, open, save, gen, delete, settings, prev, prevstop;

    static
    {
        init();
    }
    
    public static void init()
    {
        neww = createIcon("new.gif");
        open = createIcon("open.png");
        save = createIcon("save.png");
        gen = createIcon("gen.png");
        delete = createIcon("delete.png");
        settings = createIcon("settings.png");
        prev = createIcon("preview.png");
        prevstop = createIcon("stop.png");
    }
    /*
    private static ImageIcon createAdd(ImageIcon icon )
    {
        if(icon == null) return add;
        Image i = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics g = i.getGraphics();
        g.drawImage(icon.getImage(), 0, 0, null);
        g.drawImage(add.getImage(), 0, 0, null);
        return new ImageIcon(i);
    }*/

    private static ImageIcon createIcon(String path) 
    {
        java.net.URL imgURL = Images.class.getResource("/icons/"+path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}