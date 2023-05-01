/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.models.form;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.chart.ModelChart;
import main.dao.connexion;

/**
 *
 * @author HP
 */
public class form extends javax.swing.JPanel {

    /**
     * Creates new form form
     */
    public form( ) {
        initComponents();
      init();
        
    }
public void init(){
        try {
            try {
                chart.addLegend("Heure formation par département", new Color(186, 37, 37), new Color(241, 100, 120));
                chart.addLegend(" Pourcentage(%) de Salaré formé", new Color(54, 4, 143), new Color(104, 49, 200));
                //chart.addLegend("2Heure formation par département", new Color(12, 84, 175), new Color(0, 108, 247));
                //chart.addLegend(" 2Pourcentage(%) de Salaré formé", new Color(5, 125, 0), new Color(95, 209, 69));
                double[] tab1=new double[2];
                double[] tab2=new double[2];
                double[] tab3=new double[2];
                double[] tab4=new double[2];
                double[] tab5=new double[2];
                Connection con=connexion.seConnecter();
                Statement s = con.createStatement();
                ResultSet re=s.executeQuery(" select sum( decode(Lower(Departement) ,Lower( 'vente'),count(Matricule)*Duree,0)) as Vente ,sum( decode(Lower(Departement) ,Lower( 'Logistique'),count(Matricule)*Duree,0)) as Logistique ,sum( decode(Lower(Departement) ,Lower( 'Fianances'),count(Matricule)*Duree,0)) as Fianances,sum( decode(Lower(Departement) ,Lower( 'Ressources Humaines'),count(Matricule)*Duree,0)) as RH,sum( decode(Lower(Departement) ,Lower( 'Production'),count(Matricule)*Duree,0)) as Production from Employees join Plan_formation using(Matricule) join Formation using(Code_formation) group by Departement , Duree");
                
                while(re.next()){
                    
                    tab1[0]=re.getDouble(1);
                    tab2[0]=re.getDouble(2);
                    tab3[0]=re.getDouble(3);
                    tab4[0]=re.getDouble(4);
                    tab5[0]=re.getDouble(5);
                    
                    
                    
                }re.close();
                re=s.executeQuery(" select sum( decode(Lower(Departement) ,Lower( 'vente'),count(Matricule),0)) as Vente ,sum( decode(Lower(Departement) ,Lower( 'Logistique'),count(Matricule),0)) as Logistique ,sum( decode(Lower(Departement) ,Lower( 'Fianances'),count(Matricule),0)) as Fianances,sum( decode(Lower(Departement) ,Lower( 'Ressources Humaines'),count(Matricule),0)) as RH,sum( decode(Lower(Departement) ,Lower( 'Production'),count(Matricule),0)) as Production,count(Matricule) from Employees join Plan_formation using(Matricule) group by Departement , Matricule");
                
                while(re.next()){
                    
                    tab1[1]=(100/re.getInt(6))* re.getInt(1);
                    tab2[1]=(100/re.getInt(6))*re.getInt(2);
                    tab3[1]=(100/re.getInt(6))*re.getInt(3);
                    tab4[1]=(100/re.getInt(6))*re.getInt(4);
                    System.out.println(tab4[1]);
                    tab5[1]=(100/re.getInt(6))*re.getInt(5);
                    
                    
                    
                }re.close();
                chart.addData(new ModelChart("Vente", tab1));
                chart.addData(new ModelChart("Logistique", tab2));
                chart.addData(new ModelChart("Finances", tab3));
                chart.addData(new ModelChart("Ressources Humaines", tab4));
                chart.addData(new ModelChart("Production", tab5));
                
                chart.start();
            } catch (SQLException ex) {
                Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
            }
            double[] tabb1=new double[1];
            double[] tabb2=new double[1];
            double[] tabb3=new double[1];
            double[] tabb4=new double[1];
            double[] tabb5=new double[1];
            Connection con=connexion.seConnecter();
            Statement s = con.createStatement();
            ResultSet re=s.executeQuery(" select sum( decode(Lower(Competence_visee) ,Lower( 'Droit et Réglementation'),sum(Nb_heurs),0)) AS bb  ,sum( decode(Lower(Competence_visee) ,Lower( 'Techniques de vente'),sum(Nb_heurs),0)) AS ba ,sum(decode(Lower(Competence_visee),Lower( 'Management et Leadership'),sum(Nb_heurs),0))  as b,sum(decode(Lower(Competence_visee) ,Lower( 'Marketing et communication'),sum(Nb_heurs),0))  as aa, sum(decode(Lower(Competence_visee) ,Lower( 'Beaurutique et digital'),sum(Nb_heurs),0)) as a from Formation join Plan_formation using(Code_formation) group by Competence_visee");
            while(re.next()){
                
                tabb1[0]=re.getDouble(1);
                tabb2[0]=re.getDouble(2);
                tabb3[0]=re.getDouble(3);
                tabb4[0]=re.getDouble(4);
                tabb5[0]=re.getDouble(5);
                
                
                
            }re.close();
            chartAnimation.addLegend("Heurs formation par compétence Visée",   new Color(139, 229, 222));
           
            chartAnimation.addData(new ModelChart("Droit &  Réglementation",tabb1));
            chartAnimation.addData(new ModelChart("Techniques de Vente", tabb2));
            chartAnimation.addData(new ModelChart("Management & Leadership",tabb3));
            chartAnimation.addData(new ModelChart("Marketing & Communication", tabb4));
            chartAnimation.addData(new ModelChart("Bureautique & Digital",tabb5));
            
            chartAnimation.start();
        } catch (Exception ex) {
            Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        buttonGroup1 = new javax.swing.ButtonGroup();
        borderPane = new main.swing.BorderPane();
        chart = new main.chart.Chart();
        jPanel1 = new javax.swing.JPanel();
        buttonBadges1 = new main.swing.ButtonBadges();
        buttonBadges2 = new main.swing.ButtonBadges();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        borderPane2 = new main.swing.BorderPane();
        chart_Custom1 = new main.chart.Chart_Custom();
        jLabel4 = new javax.swing.JLabel();
        borderPane3 = new main.swing.BorderPane();
        chartAnimation = new main.chart.ChartAnimation();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        borderPane.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setOpaque(false);

        buttonBadges1.setBackground(new java.awt.Color(102, 0, 204));

        buttonBadges2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Heure formation par département");

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Pourcentage(%) de Salaré formé");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12))
        );

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Heure formation par département");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("               & Salaré formé");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout borderPaneLayout = new javax.swing.GroupLayout(borderPane);
        borderPane.setLayout(borderPaneLayout);
        borderPaneLayout.setHorizontalGroup(
            borderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPaneLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(borderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        borderPaneLayout.setVerticalGroup(
            borderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(borderPaneLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)))
                .addContainerGap())
        );

        jLabel5.getAccessibleContext().setAccessibleName("Heure formation par\n département & Salaré formé");

        borderPane2.setBackground(new java.awt.Color(51, 51, 51));

        chart_Custom1.setOpaque(false);

        javax.swing.GroupLayout chart_Custom1Layout = new javax.swing.GroupLayout(chart_Custom1);
        chart_Custom1.setLayout(chart_Custom1Layout);
        chart_Custom1Layout.setHorizontalGroup(
            chart_Custom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chart_Custom1Layout.setVerticalGroup(
            chart_Custom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Moyenne des Heures par CSP");

        javax.swing.GroupLayout borderPane2Layout = new javax.swing.GroupLayout(borderPane2);
        borderPane2.setLayout(borderPane2Layout);
        borderPane2Layout.setHorizontalGroup(
            borderPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPane2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
            .addComponent(chart_Custom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        borderPane2Layout.setVerticalGroup(
            borderPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPane2Layout.createSequentialGroup()
                .addComponent(chart_Custom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        borderPane3.setBackground(new java.awt.Color(51, 51, 51));

        chartAnimation.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Moyenne des Heures des Compétences Visée");

        javax.swing.GroupLayout borderPane3Layout = new javax.swing.GroupLayout(borderPane3);
        borderPane3.setLayout(borderPane3Layout);
        borderPane3Layout.setHorizontalGroup(
            borderPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPane3Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(borderPane3Layout.createSequentialGroup()
                .addComponent(chartAnimation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        borderPane3Layout.setVerticalGroup(
            borderPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPane3Layout.createSequentialGroup()
                .addComponent(chartAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borderPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(borderPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borderPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(borderPane, javax.swing.GroupLayout.PREFERRED_SIZE, 290, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(borderPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borderPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.swing.BorderPane borderPane;
    private main.swing.BorderPane borderPane2;
    private main.swing.BorderPane borderPane3;
    private main.swing.ButtonBadges buttonBadges1;
    private main.swing.ButtonBadges buttonBadges2;
    private javax.swing.ButtonGroup buttonGroup1;
    private main.chart.Chart chart;
    private main.chart.ChartAnimation chartAnimation;
    private main.chart.Chart_Custom chart_Custom1;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
