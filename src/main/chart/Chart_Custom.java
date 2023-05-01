package main.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import main.dao.connexion;

public class Chart_Custom extends javax.swing.JPanel {

    private Map<String, Chart_Item> map;
    private List<Chart_Item> list;
    double maxValues;

    public Chart_Custom() {
        initComponents();
        init();
    }

    private void sort() {
        List<Map.Entry<String, Chart_Item>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Chart_Item>>() {
            @Override
            public int compare(Map.Entry<String, Chart_Item> o1, Map.Entry<String, Chart_Item> o2) {
                return o1.getValue().getValues() > o2.getValue().getValues() ? -1 : o1.getValue().getValues() == o2.getValue().getValues() ? 0 : -1;
            }
        });
        list.clear();
        maxValues = 0;
        for (Map.Entry<String, Chart_Item> item : entryList) {
            list.add(item.getValue());
            if (item.getValue().getValues() > maxValues) {
                maxValues = item.getValue().getValues();
            }
        }
    }

    private void init() {
        try {
            map = new HashMap<>();
            list = new ArrayList<>();
            Connection con=connexion.seConnecter();
            Statement s = con.createStatement();
            int A=0;
            int B=0;
            int C=0;
            int D=0;
            ResultSet re=s.executeQuery(" select avg(NVL( decode(Lower(CSP) ,Lower( 'Top manager'),sum(Nb_heurs),0),0)) AS ba ,avg(NVL(decode(Lower(CSP),Lower( 'agent de maitrice'),sum(Nb_heurs),0),0))  as b,avg(NVL(decode(Lower(CSP) ,Lower( 'Technicien'),sum(Nb_heurs),0),0))  as aa, avg(NVL(decode(Lower(CSP) ,Lower( 'Cadre'),sum(Nb_heurs),0),0)) as a from Employees join Plan_formation using(Matricule) join Formation using (Code_formation) group by CSP");
            while(re.next()){
                
                A=(int) re.getDouble(1);
                B=(int) re.getDouble(2);
                C=(int) re.getDouble(3);
                D=(int) re.getDouble(4);
               
                
                
                
            }
            re.close();
            con.close();
            map.put("Top Manager", new Chart_Item("Top Manager", A, new Color(255, 255, 0)));
            map.put("Technicien", new Chart_Item("Technicien", C, new Color(44, 96, 198)));
            map.put("Carde", new Chart_Item("Carde", D, new Color(107, 44, 198)));
            map.put("Agent de maitrice", new Chart_Item("Agent de maitrice", B, new Color(205, 61, 198)));
            sort();
        } catch (Exception ex) {
            Logger.getLogger(Chart_Custom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //  for image smooth
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);    //  for text smooth
        g2.setFont(new Font("sansserif", 1, 17));
        int size = map.size();
        int space = 5;
        int itemHeight = ((getHeight() - ((space * size) + 1)) / size);
        int y = space;
        int maxWidth = getWidth() - 300;    //  300 space right
        for (Chart_Item item : map.values()) {
            g2.setColor(item.getColor());
            double width = item.getValues() * maxWidth / maxValues;
            g2.fillRect(5, y, (int) width, itemHeight);
            paintIcon(g2, item, (int) width + 10, y, itemHeight);
            y += itemHeight + space;
        }
    }

    private void paintIcon(Graphics2D g2, Chart_Item item, int x, int y, int size) {
        //  size for space icon
        DecimalFormat df = new DecimalFormat("#,##0.##");
        g2.setColor(new Color(88, 88, 88));
       
        g2.drawString(df.format(item.getValues()), x + size + 5, y + (size / 2 + 7));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
