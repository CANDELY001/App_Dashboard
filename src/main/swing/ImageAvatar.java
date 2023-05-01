/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.swing;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author HP
 */
public class ImageAvatar extends JComponent {
      private Icon icon;
    private int bordersize;

    @Override
    protected  void paintComponent(Graphics g) {
        if(icon!=null){
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height);
            int x= width/ 2- diameter/2;
            int y= height/ 2- diameter/2;
             int border = bordersize * 2;
            Rectangle size= getAutoSize(icon,diameter);
            BufferedImage img = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2_img= img.createGraphics();
            g2_img.fillOval(0, 0, diameter, diameter);
             
            Graphics2D g2= (Graphics2D) g;
             g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(toImage(icon), size.x, size.y, size.width, size.height, null);
           Composite composite = g2_img.getComposite();
           g2_img.setComposite(composite);
            g2_img.dispose();
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (bordersize > 0) {
                diameter += border;
                g2.setColor(getForeground());
                g2.fillOval(x, y, diameter, diameter);
            }
            if (isOpaque()) {
                g2.setColor(getBackground());
                diameter -= border;
                g2.fillOval(x + bordersize, y + bordersize, diameter, diameter);
            }
            g2.drawImage(img, x + bordersize, y + bordersize, null);
        }
        
        super.paintComponent(g); 
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public int getBordersize() {
        return bordersize;
    }

    public void setBordersize(int bordersize) {
        this.bordersize = bordersize;
    }
    private String string;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public String getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(String string) {
        this.string = string;
    }

    private Rectangle getAutoSize(Icon image, int size){
        int w=size;
        int h=size;
        int iw=image.getIconWidth();
        int ih=image.getIconHeight();
        double Xscale=(double) w/iw;
        double Yscale=(double) h/ih;
                double  scale = Math.max(Xscale, Yscale);
                int width=(int) (scale*iw);
                 int height=(int) (scale*ih);
                  if (width < 1) {
            width = 1;
        }
        if (height < 1) {
            height = 1;
        }
        int cw = size;
        int ch =size;
        int x = (cw - width) / 2;
        int y = (ch - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
       
    }
    private Image toImage(Icon icon){
        return ((ImageIcon) icon).getImage();
    }
}
