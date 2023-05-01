/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.models.form;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import main.dao.connexion;
import main.swing.SwitchButton.EventSwitchSelected;
import main.swing.scrollbar.scollBarCustom;
import oracle.net.aso.l;

/**
 *
 * @author HP
 */
public class form_3 extends javax.swing.JPanel {

    /**
     * Creates new form form_3
     */
     Connection con= null;
     ResultSet rs=null;
     DefaultListModel mod=null;
     DefaultListModel mod2=null;
    public form_3() {
         try {
             initComponents();
             jScrollPane1.setVerticalScrollBar(new scollBarCustom());
             jScrollPane1.getViewport().setBackground(new Color(30, 30, 30));
             jScrollPane1.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
             mod= new DefaultListModel();
             list1.setModel(mod);
             mod2= new DefaultListModel();
             List2.setModel(mod2);
             con=connexion.seConnecter();
             Statement s=con.createStatement();
             rs=s.executeQuery("select Nom,Prenom from Employees");
             while(rs.next()){
                 mod.addElement(rs.getString(1)+" - "+rs.getString(2));
             }
             rs.close();
             
          // String nom=list1.getSelectedValue().toString().split("-")[0].trim();
           //String Prenom=list1.getSelectedValue().toString().split("-")[1].trim();
             rs=s.executeQuery("select Nom, Prenom , Site , Departement , CSP,(sysdate-Date_Naissance)/365, Fonction from Employees  where Matricule='E01' ");
             if(rs.next()){
               txtnom.setText(rs.getString(1));
               txtprenom.setText(rs.getString(2));
               txtsite.setText(rs.getString(3));
               txtdepartement.setText(rs.getString(4));
               txtCSP.setText(rs.getString(5));
               txtanciennté.setText(rs.getInt(6)+" An(s)");
               txtfonction.setText(rs.getString(7));
               
             }
            
             rs.close();
             rs=s.executeQuery(" select sum(Nb_heurs) from Formation join Plan_formation using(Code_formation) join Employees using(Matricule) where Matricule='E01' group by Matricule ");
             if(rs.next()){
               txthrform.setText(rs.getInt(1)+" Heures");
              if(rs.getInt(1)==0){
                  switchButton1.setSelected(false);
                   txtAssduite.setText(" 0%");
               }else{
                    txtAssduite.setText(" 100%");
                    switchButton1.setSelected(true);
              }
             }
              rs.close();
             rs=s.executeQuery(" select count(distinct Code_formation) from Plan_formation where Matricule='E01' ");
             if(rs.next()){
               txtnbform.setText(rs.getInt(1)+"");
               
             }
              rs.close();
             rs=s.executeQuery(" select distinct Intitule_formation from Formation join Plan_formation  using (Code_formation) where Matricule='E01' group by Matricule, Intitule_formation");
             mod2.removeAllElements();
             while(rs.next()){
               mod2.addElement(rs.getString(1));
               
             }
             rs.close();
              rs=s.executeQuery("select sum( decode(Lower(Performance) ,Lower( 'Excellent'),1,0)),sum( decode(Lower(Performance) ,Lower( 'mauvaise'),1,0)),sum( decode(Lower(Performance) ,Lower( 'EN déça des attentes'),1,0)),sum( decode(Lower(Performance) ,Lower( 'Répond aux attentes'),1,0)),count(*) from Plan_formation join Employees using(Matricule)   where Matricule='E01'  group by Performance");
             while(rs.next()){
                jProgressBar1.setValue((100/rs.getInt(5))*rs.getInt(1));
                jProgressBar1.setBackground(Color.BLACK);
                jProgressBar4.setValue((100/rs.getInt(5))*rs.getInt(2));
                jProgressBar4.setBackground(Color.BLACK);
                jProgressBar2.setValue((100/rs.getInt(5))*rs.getInt(3));
                jProgressBar2.setBackground(Color.BLACK);
                jProgressBar3.setValue((100/rs.getInt(5))*rs.getInt(4));
                jProgressBar3.setBackground(Color.BLACK);
                 txtExcellent.setText((100/rs.getInt(5))*rs.getInt(1)+"%");
                 txtM.setText((rs.getInt(5)/100)*rs.getInt(2)+"%");
                 txtR.setText((rs.getInt(5)/100)*rs.getInt(3)+"%");
                 txtD.setText((rs.getInt(5)/100)*rs.getInt(4)+"%");
             } 
             } catch (Exception ex) {
             Logger.getLogger(form_3.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }

    /**a&                                                                                                                                       
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is alwaysr
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        list1 = new javax.swing.JList<>();
        switchButton1 = new main.swing.SwitchButton.SwitchButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        borderPane1 = new main.swing.BorderPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtprenom = new javax.swing.JTextField();
        txtnom = new javax.swing.JTextField();
        txtsite = new javax.swing.JTextField();
        txtdepartement = new javax.swing.JTextField();
        txtCSP = new javax.swing.JTextField();
        txtanciennté = new javax.swing.JTextField();
        txtfonction = new javax.swing.JTextField();
        txthrform = new javax.swing.JTextField();
        txtAssduite = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        txtExcellent = new javax.swing.JLabel();
        txtR = new javax.swing.JLabel();
        txtD = new javax.swing.JLabel();
        txtM = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        List2 = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtnbform = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        list1.setBackground(new java.awt.Color(102, 102, 102));
        list1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOM & PRENOM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        list1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        list1.setForeground(new java.awt.Color(255, 255, 255));
        list1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list1.setMaximumSize(new java.awt.Dimension(60, 534));
        list1.setValueIsAdjusting(true);
        list1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list1);

        switchButton1.setBackground(new java.awt.Color(0, 255, 0));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Formé");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Non formé");

        borderPane1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nom");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Pernom");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Site");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Département");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("CSP");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Ancinneté (Année)");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Fonction");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Heures formation");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Tx.Assiduité formation");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setText("Perf. post_fomtion");

        jLabel13.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel13.setText("Excellent");

        jLabel14.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel14.setText("Répond aus attentes");

        jLabel15.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel15.setText("En déça des attentes");

        jLabel16.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel16.setText("Maucaise");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator5)
            .addComponent(jSeparator6)
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator8)
            .addComponent(jSeparator9)
            .addComponent(jSeparator10)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 63, Short.MAX_VALUE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtprenom.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtprenom.setForeground(new java.awt.Color(153, 153, 153));
        txtprenom.setText("jTextField1");

        txtnom.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtnom.setForeground(new java.awt.Color(153, 153, 153));
        txtnom.setText("jTextField1");

        txtsite.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtsite.setForeground(new java.awt.Color(153, 153, 153));
        txtsite.setText("jTextField3");

        txtdepartement.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtdepartement.setForeground(new java.awt.Color(153, 153, 153));
        txtdepartement.setText("jTextField4");

        txtCSP.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtCSP.setForeground(new java.awt.Color(153, 153, 153));
        txtCSP.setText("jTextField5");

        txtanciennté.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtanciennté.setForeground(new java.awt.Color(153, 153, 153));
        txtanciennté.setText("jTextField6");

        txtfonction.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtfonction.setForeground(new java.awt.Color(153, 153, 153));
        txtfonction.setText("jTextField7");

        txthrform.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txthrform.setForeground(new java.awt.Color(153, 153, 153));
        txthrform.setText("jTextField8");

        txtAssduite.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtAssduite.setForeground(new java.awt.Color(153, 153, 153));
        txtAssduite.setText("jTextField9");

        jProgressBar1.setBackground(new java.awt.Color(102, 255, 0));
        jProgressBar1.setValue(50);

        txtExcellent.setText("jLabel18");

        txtR.setText("jLabel19");

        txtD.setText("jLabel20");

        txtM.setText("jLabel21");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtprenom)
            .addComponent(txtnom)
            .addComponent(txtsite, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtdepartement, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtCSP, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtanciennté, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtfonction)
            .addComponent(txthrform, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(txtAssduite, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtExcellent)
                    .addComponent(txtR)
                    .addComponent(txtD)
                    .addComponent(txtM))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtsite, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtdepartement, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtanciennté, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtfonction, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txthrform, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAssduite, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtExcellent))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtD)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtM))
                .addContainerGap())
        );

        List2.setBackground(new java.awt.Color(204, 153, 0));
        List2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        List2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        List2.setMaximumSize(new java.awt.Dimension(49, 100));
        jScrollPane2.setViewportView(List2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Liste des formation");

        txtnbform.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnbform.setText("jLabel18");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addGap(122, 122, 122)
                .addComponent(txtnbform)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtnbform)))
        );

        javax.swing.GroupLayout borderPane1Layout = new javax.swing.GroupLayout(borderPane1);
        borderPane1.setLayout(borderPane1Layout);
        borderPane1Layout.setHorizontalGroup(
            borderPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borderPane1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(borderPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, borderPane1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        borderPane1Layout.setVerticalGroup(
            borderPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderPane1Layout.createSequentialGroup()
                .addGroup(borderPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(borderPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(switchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(switchButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(borderPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void list1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list1MouseClicked
         try {
             // TODO add your handling code here:
             switchButton1.setSelected(false);
             String nom=list1.getSelectedValue().split("-")[0].trim();
            String Prenom=list1.getSelectedValue().split("-")[1].trim();
             System.out.println(nom+" "+Prenom);
            
             PreparedStatement s=con.prepareStatement("select Nom, Prenom , Site , Departement , CSP,(sysdate-Date_Naissance)/365, Fonction from Employees  where Nom=?  and Prenom =?");
             s.setString(1, nom);
              s.setString(2, Prenom);
             rs=s.executeQuery();
             
             if(rs.next()){
                 txtnom.setText(rs.getString(1));
                 txtprenom.setText(rs.getString(2));
                 txtsite.setText(rs.getString(3));
                 txtdepartement.setText(rs.getString(4));
                 txtCSP.setText(rs.getString(5));
                 txtanciennté.setText(rs.getInt(6)+" An(s)");
                 txtfonction.setText(rs.getString(7));
                 
             }
            
             rs.close();
             s=con.prepareStatement(" select sum(Nb_heurs) from Formation join Plan_formation using(Code_formation) join Employees using(Matricule) where  Nom =? and Prenom =? group by Matricule ");
              s.setString(1, nom);
              s.setString(2, Prenom);
             rs=s.executeQuery();
             if(rs.next()){
                 txthrform.setText(rs.getInt(1)+" Heures");
                 if(rs.getInt(1)==0){
                     switchButton1.setSelected(false);
                     txtAssduite.setText(" 0%");
                 }else{
                     txtAssduite.setText(" 100%");
                     switchButton1.setSelected(true);
                 }
             }
             rs.close();
              s=con.prepareStatement(" select count(distinct Code_formation) from Plan_formation join Employees using(Matricule) where Nom=?  and Prenom=? ");
               s.setString(1, nom);
              s.setString(2, Prenom);
              rs=s.executeQuery();
             if(rs.next()){
                 txtnbform.setText(rs.getInt(1)+"");
                 
             }
             rs.close();
              s=con.prepareStatement(" select distinct Intitule_formation from Formation join Plan_formation  using (Code_formation) join Employees using(Matricule)  where Nom =? and Prenom =? group by Matricule, Intitule_formation");
               s.setString(1, nom);
              s.setString(2, Prenom);
             rs=s.executeQuery();
             mod2.removeAllElements();
             while(rs.next()){
                 mod2.addElement(rs.getString(1));
                 
             }
             // select sum( decode(Lower(Performance) ,Lower( 'Excellent'),1,0)),sum( decode(Lower(Performance) ,Lower( 'mauvaise'),1,0)),sum( decode(Lower(Performance) ,Lower( 'EN déça des attentes'),1,0)),sum( decode(Lower(Performance) ,Lower( 'Répond aux attentes'),1,0)) from Plan_formation join Employees using(Matricule)  group by Performance;
             rs.close();
              s=con.prepareStatement("select sum( decode(Lower(Performance) ,Lower( 'Excellent'),1,0)),sum( decode(Lower(Performance) ,Lower( 'mauvaise'),1,0)),sum( decode(Lower(Performance) ,Lower( 'EN déça des attentes'),1,0)),sum( decode(Lower(Performance) ,Lower( 'Répond aux attentes'),1,0)),count(*) from Plan_formation join Employees using(Matricule)   where Nom =? and Prenom =?  group by Performance");
               s.setString(1, nom);
              s.setString(2, Prenom);
             rs=s.executeQuery();
            
             while(rs.next()){
                jProgressBar1.setValue((100/rs.getInt(5))*rs.getInt(1));
                jProgressBar1.setBackground(Color.BLACK);
                jProgressBar4.setValue((100/rs.getInt(5))*rs.getInt(2));
                jProgressBar4.setBackground(Color.BLACK);
                jProgressBar2.setValue((100/rs.getInt(5))*rs.getInt(3));
                jProgressBar2.setBackground(Color.BLACK);
                jProgressBar3.setValue((100/rs.getInt(5))*rs.getInt(4));
                jProgressBar3.setBackground(Color.BLACK);
                 txtExcellent.setText((100/rs.getInt(5))*rs.getInt(1)+"%");
                 txtM.setText((rs.getInt(5)/100)*rs.getInt(2)+"%");
                 txtR.setText((rs.getInt(5)/100)*rs.getInt(3)+"%");
                 txtD.setText((rs.getInt(5)/100)*rs.getInt(4)+"%");
             }
         } catch (SQLException ex) {
             Logger.getLogger(form_3.class.getName()).log(Level.SEVERE, null, ex);
         }
              
        
    }//GEN-LAST:event_list1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> List2;
    private main.swing.BorderPane borderPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JList<String> list1;
    private main.swing.SwitchButton.SwitchButton switchButton1;
    private javax.swing.JTextField txtAssduite;
    private javax.swing.JTextField txtCSP;
    private javax.swing.JLabel txtD;
    private javax.swing.JLabel txtExcellent;
    private javax.swing.JLabel txtM;
    private javax.swing.JLabel txtR;
    private javax.swing.JTextField txtanciennté;
    private javax.swing.JTextField txtdepartement;
    private javax.swing.JTextField txtfonction;
    private javax.swing.JTextField txthrform;
    private javax.swing.JLabel txtnbform;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtprenom;
    private javax.swing.JTextField txtsite;
    // End of variables declaration//GEN-END:variables
}
