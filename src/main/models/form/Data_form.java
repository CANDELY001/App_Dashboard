/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.models.form;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.parser.JSONParser;
import main.dao.connexion;
import org.json.simple.JSONArray;
/**
 *
 * @author HP
 */
public class Data_form extends javax.swing.JPanel {
private DefaultListModel mod;
DefaultTableModel mode1=null;
 DefaultTableModel mode2=null;
    /**
     * Creates new form Data_form
     */
    Connection conn=null;
    ResultSet rs,rs1;
    
    public Data_form() {
        initComponents();
        popmenusearchformation.add(jPanel1);
        mod= new DefaultListModel();
         jList1.setModel(mod);
        //jPanel1.setVisible(false);
        Tabs.setBackgroundAt(0, Color.darkGray);
         Tabs.remove(form);
     Tabs.remove(formation);
     Tabs.remove(plan_formation);
        //table employee-------------------------------------
        TableEmploye.fixTable(jScrollPane1);
        TableEmploye.setColumnAlignment(0, JLabel.CENTER);
        TableEmploye.setCellAlignment(0, JLabel.CENTER);
        TableEmploye.setColumnAlignment(2, JLabel.CENTER);
        TableEmploye.setCellAlignment(2, JLabel.CENTER);
        TableEmploye.setColumnAlignment(4, JLabel.RIGHT);
        TableEmploye.setCellAlignment(4, JLabel.RIGHT);
        TableEmploye.setColumnWidth(0, 50);
        TableEmploye.setColumnWidth(2, 100);
         DefaultTableModel mode = (DefaultTableModel) TableEmploye.getModel();
        mode.setRowCount(0);
        
          try {
              String sexe=null;
        conn=  connexion.seConnecter();
             Statement st=conn.createStatement();
            rs=st.executeQuery("select Matricule, Nom, Prenom, Site, Date_naissance,Sexe,Date_recrutement,Departement,CSP,Fonction, Round(months_between(sysdate,Date_naissance)/12,0), Round(months_between(Date_naissance,Date_recrutement)/12,0) from Employees");
             while(rs.next()){
                sexe = (rs.getInt(6)==1)?"Homme":"Femme";
                mode.addRow(new Object []{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),sexe,rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(11)});
         }
             rs.close();
         
     }  catch (Exception ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage());
            
         }
         //pour update the Data base
          mode.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
               int row=e.getFirstRow();
                      int col=e.getColumn();
               
   if( col==1||col==2|| col==3||col==4 || col==5|| col==5 || col==6 || col==7 ||col==8 ||col==9 ){
   
        try {
            Connection con=connexion.seConnecter();
            String mat=TableEmploye.getValueAt(row, 0).toString();
            String nom=TableEmploye.getValueAt(row, 1).toString();
              String prenom=TableEmploye.getValueAt(row, 2).toString();
                String site=TableEmploye.getValueAt(row, 3).toString();
                 java.sql.Date date=java.sql.Date.valueOf(TableEmploye.getValueAt(row, 4).toString());
                 String index=TableEmploye.getValueAt(row, 5).toString();
                 int sexe = (index.equals("Homme"))?1:0;
                   java.sql.Date date2=java.sql.Date.valueOf(TableEmploye.getValueAt(row, 6).toString());
                   String departement=TableEmploye.getValueAt(row, 7).toString();
                   String csp=TableEmploye.getValueAt(row, 8).toString();
                   String Fonction=TableEmploye.getValueAt(row, 9).toString();
                  
           
             
            PreparedStatement st=con.prepareStatement("update Employees set Nom=? , Prenom=? , Site=? , Date_naissance=? , Date_recrutement=? , Sexe=? , Departement=?, CSP=? , Fonction=? where Matricule=?"); 
            st.setString(1, nom);
            st.setString(2,prenom );
            st.setString(3,site );
            st.setDate(4,date );
            st.setDate(5,date2 );
            st.setInt(6,sexe);
            st.setString(8, csp);
            st.setString(7,departement );
            st.setString(9,Fonction );
            st.setString(10,mat );
            st.executeUpdate();
             JOptionPane.showMessageDialog(null, "Modifiée avec succes", "WELL DONE", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/main/icons/images.png"));
             // ALertDone me = new ALertDone(null, true);
              //  me.setVisible(true);
           
        } catch (Exception ex) {
            Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
        }
            }}
            
        });
      //table formation ----------------------------
       
       Tableformation.fixTable(jScrollPane3);
        Tableformation.setColumnAlignment(0, JLabel.CENTER);
        Tableformation.setCellAlignment(0, JLabel.CENTER);
        Tableformation.setColumnAlignment(2, JLabel.CENTER);
        Tableformation.setCellAlignment(2, JLabel.CENTER);
        Tableformation.setColumnAlignment(4, JLabel.RIGHT);
        Tableformation.setCellAlignment(4, JLabel.RIGHT);
        Tableformation.setColumnWidth(0, 50);
        Tableformation.setColumnWidth(2, 100);
        DefaultTableModel mode1 = (DefaultTableModel) Tableformation.getModel();
        mode1.setRowCount(0);
         
          try {
              
        
             Statement stt=conn.createStatement();
            rs1=stt.executeQuery("select Code_formation, Date_debut, Nb_heurs, Duree,Prestataire, Intitule_formation ,Type_formation,Competence_visee,Parcours,Objectifs,Formateur, cout_total from Formation");
             while(rs1.next()){
                
                mode1.addRow(new Object []{rs1.getInt(1),rs1.getDate(2),rs1.getInt(3),rs1.getInt(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11),rs1.getFloat(12)});
         }
           
             rs1.close();
         
     }  catch (Exception ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage()+"table2");
            
         }
            mode1.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
               int row=e.getFirstRow();
               int col=e.getColumn();
               
   if( col==1||col==2|| col==3||col==4 || col==5|| col==5 || col==6 || col==7 ||col==8 ||col==9 ||col==10 ||col==11){
        try {
            Connection con=connexion.seConnecter();
            int code=Integer.parseInt(Tableformation.getValueAt(row, 0).toString());
            int days=Integer.parseInt(Tableformation.getValueAt(row, 2).toString());
            int hours=Integer.parseInt(Tableformation.getValueAt(row, 3).toString());
            
            String prestataire=Tableformation.getValueAt(row, 4).toString();
              String Info=Tableformation.getValueAt(row, 5).toString();
              
                String type=Tableformation.getValueAt(row, 6).toString();
                 java.sql.Date date=java.sql.Date.valueOf(Tableformation.getValueAt(row, 1).toString());
                 String Cv=Tableformation.getValueAt(row, 7).toString();
                   String parcours=Tableformation.getValueAt(row, 8).toString();
                   String objects=Tableformation.getValueAt(row, 9).toString();
                   String Formateur=Tableformation.getValueAt(row, 10).toString();
                   float cout=Float.parseFloat(Tableformation.getValueAt(row, 11).toString());
 
            PreparedStatement st=con.prepareStatement("update Formation set Date_debut=? , Duree=? , Nb_heurs=? , Prestataire=? , Intitule_formation=? , Type_formation=? , Competence_visee=?, Parcours=? , Objectifs=? ,Formateur=?,cout_total=? where Code_formation=?"); 
            st.setDate(1, date);
            st.setInt(2,days );
            st.setInt(3,hours );
            st.setString(4,prestataire );
            st.setString(5,Info );
            
            st.setString(6, type);
            st.setString(7,Cv );
             
            st.setString(9, objects);
            st.setString(10,Formateur );
            st.setString(8,parcours );
            st.setFloat(11,cout );
             st.setInt(12,code );
            st.executeUpdate();
           JOptionPane.showMessageDialog(null, "Modifiée avec succes", "WELL DONE", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/main/icons/images.png"));
             // ALertDone me = new ALertDone(null, true);
              //  me.setVisible(true);
           
        } catch (Exception ex) {
            Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
        }
            }} });
       //table Plan formation ----------------------------
       TablePlanf.fixTable(jScrollPane5);
        TablePlanf.setColumnAlignment(0, JLabel.CENTER);
        TablePlanf.setCellAlignment(0, JLabel.CENTER);
        TablePlanf.setColumnAlignment(2, JLabel.CENTER);
        TablePlanf.setCellAlignment(2, JLabel.CENTER);
        TablePlanf.setColumnAlignment(4, JLabel.RIGHT);
        TablePlanf.setCellAlignment(4, JLabel.RIGHT);
        TablePlanf.setColumnWidth(0, 50);
        TablePlanf.setColumnWidth(2, 100);
         mode2 = (DefaultTableModel) TablePlanf.getModel();
        mode2.setRowCount(0);
          
          try {
              
        
            Statement stt=conn.createStatement();
            rs1=stt.executeQuery("select f.Type_formation,a.Matricule,f.Date_debut, f.Duree,a.Presence,a.Note ,a.Performance,a.Commentaire,a.Id from Plan_formation a, Formation f, Employees e where a.Matricule=e.Matricule and f.Code_formation=a.Code_Formation ");
             while(rs1.next()){
                
                mode2.addRow(new Object []{rs1.getString(1),rs1.getString(2),rs1.getDate(3),rs1.getInt(4),rs1.getInt(5),rs1.getInt(6),rs1.getString(7),rs1.getString(8),rs1.getInt(9)});
         }
           
             rs1.close();
    } catch (Exception ex) {
           JOptionPane.showMessageDialog(null,ex.getMessage()+" table3");
            
         }
  mode2.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
               int row=e.getFirstRow();
               int col=e.getColumn();
                if(col==1 || col==4|| col==5 || col==6 || col==7 ){
   
        try {
            Connection con=connexion.seConnecter();
            int Id=Integer.parseInt(TablePlanf.getValueAt(row, 8).toString());
            int days=Integer.parseInt(TablePlanf.getValueAt(row, 4).toString());
            int note=Integer.parseInt(TablePlanf.getValueAt(row, 5).toString());
            
            String mat=TablePlanf.getValueAt(row, 1).toString().split("-")[0].trim();
              String performance=TablePlanf.getValueAt(row, 6).toString();
              
                String comment=TablePlanf.getValueAt(row, 7).toString();
                
                  
                  
             
            PreparedStatement st=con.prepareStatement("update Plan_formation set Matricule=? , Presence=? , Note=? ,Performance =? ,Commentaire =?  where Id=?"); 
            st.setString(1, mat);
            st.setInt(2,days );
            st.setInt(3,note );
            st.setString(4,performance );
            st.setString(5,comment );
            
            st.setInt(6, Id);
            
            st.executeUpdate();
             JOptionPane.showMessageDialog(null, "Modifiée avec succes", "WELL DONE", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("/main/icons/images.png"));
             // ALertDone me = new ALertDone(null, true);
              //  me.setVisible(true);
           
        } catch (Exception ex) {
            Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
        }
                } } });}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        popmenusearchformation = new javax.swing.JPopupMenu();
        Tabs = new javax.swing.JTabbedPane();
        plan_formation = new javax.swing.JPanel();
        Backbtn2 = new javax.swing.JLabel();
        buttonsingin3 = new main.swing.Buttonsingin();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtformationid = new javax.swing.JTextField();
        txtemployeeid = new javax.swing.JTextField();
        txtpresence = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtcomment = new javax.swing.JTextArea();
        txtnote = new javax.swing.JTextField();
        cbxperformance = new javax.swing.JComboBox<>();
        paneformation = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tableformation = new main.swing.DarkTable();
        buttonaddformation = new main.swing.Buttonsingin();
        buttonDeleteformation = new main.swing.Buttonsingin();
        jLabel1 = new javax.swing.JLabel();
        txtsearchf = new javax.swing.JTextField();
        paneplanf = new javax.swing.JPanel();
        buttonaddPlanf = new main.swing.Buttonsingin();
        buttondeletPlan = new main.swing.Buttonsingin();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablePlanf = new main.swing.DarkTable();
        paneemploye = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEmploye = new main.swing.DarkTable();
        buttonaddemploye = new main.swing.Buttonsingin();
        buttondeleteemploye = new main.swing.Buttonsingin();
        jLabel30 = new javax.swing.JLabel();
        txtsearchemp = new javax.swing.JTextField();
        form = new javax.swing.JPanel();
        Backbtn = new javax.swing.JLabel();
        buttonsingin1 = new main.swing.Buttonsingin();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtdate1 = new javax.swing.JTextField();
        txtmat = new javax.swing.JTextField();
        txtnom = new javax.swing.JTextField();
        txtepartement = new javax.swing.JTextField();
        txtfonction = new javax.swing.JTextField();
        txtcsp = new javax.swing.JTextField();
        RH = new javax.swing.JRadioButton();
        RF = new javax.swing.JRadioButton();
        txtprenom = new javax.swing.JTextField();
        txtdate2 = new javax.swing.JTextField();
        txtsite2 = new javax.swing.JTextField();
        formation = new javax.swing.JPanel();
        Backbtn1 = new javax.swing.JLabel();
        buttonsingin2 = new main.swing.Buttonsingin();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtnb = new javax.swing.JTextField();
        txtdatedebut = new javax.swing.JTextField();
        txtduree = new javax.swing.JTextField();
        txtef = new javax.swing.JTextField();
        txttype = new javax.swing.JTextField();
        txtob = new javax.swing.JTextField();
        txtp = new javax.swing.JTextField();
        txtII = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtpre = new javax.swing.JTextField();
        txtcv = new javax.swing.JTextField();
        txtcoout = new javax.swing.JTextField();

        jPanel1.setOpaque(false);

        jList1.setBackground(new java.awt.Color(0, 0, 0));
        jList1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jList1.setForeground(new java.awt.Color(204, 204, 204));
        jList1.setValueIsAdjusting(true);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
        );

        popmenusearchformation.setFocusable(false);

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        Tabs.setBackground(new java.awt.Color(0, 0, 0));
        Tabs.setForeground(new java.awt.Color(0, 102, 102));
        Tabs.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        Tabs.setOpaque(true);
        Tabs.setPreferredSize(new java.awt.Dimension(1221, 667));

        plan_formation.setBackground(new java.awt.Color(51, 51, 51));
        plan_formation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Backbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/outline_arrow_back_white_24dp.png"))); // NOI18N
        Backbtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Backbtn2MouseClicked(evt);
            }
        });
        plan_formation.add(Backbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 63, 36));

        buttonsingin3.setBackground(new java.awt.Color(0, 0, 0));
        buttonsingin3.setForeground(new java.awt.Color(204, 204, 204));
        buttonsingin3.setText("Enregistrer");
        buttonsingin3.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        buttonsingin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonsingin3ActionPerformed(evt);
            }
        });
        plan_formation.add(buttonsingin3, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 393, 330, 90));

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Formation");
        plan_formation.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 121, 41));

        jLabel24.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Présence");
        plan_formation.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 121, 41));

        jLabel25.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Employée");
        plan_formation.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 121, 41));

        jLabel26.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Perfermonce");
        plan_formation.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 121, 41));

        jLabel27.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Commentair");
        plan_formation.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 140, -1));

        jLabel28.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Note");
        plan_formation.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 121, 41));

        txtformationid.setBackground(new java.awt.Color(102, 102, 102));
        txtformationid.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtformationid.setForeground(new java.awt.Color(255, 255, 255));
        plan_formation.add(txtformationid, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 250, 60));

        txtemployeeid.setBackground(new java.awt.Color(102, 102, 102));
        txtemployeeid.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtemployeeid.setForeground(new java.awt.Color(255, 255, 255));
        plan_formation.add(txtemployeeid, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 250, 60));

        txtpresence.setBackground(new java.awt.Color(102, 102, 102));
        txtpresence.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtpresence.setForeground(new java.awt.Color(255, 255, 255));
        plan_formation.add(txtpresence, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 250, 60));

        txtcomment.setBackground(new java.awt.Color(102, 102, 102));
        txtcomment.setColumns(20);
        txtcomment.setRows(5);
        jScrollPane4.setViewportView(txtcomment);

        plan_formation.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 290, 120));

        txtnote.setBackground(new java.awt.Color(102, 102, 102));
        txtnote.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtnote.setForeground(new java.awt.Color(255, 255, 255));
        plan_formation.add(txtnote, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 290, 54));

        cbxperformance.setBackground(new java.awt.Color(102, 102, 102));
        cbxperformance.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        cbxperformance.setForeground(new java.awt.Color(255, 255, 255));
        cbxperformance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mauvaise", "Répond aux attente", "En deça des attentes", "Excellent", " " }));
        plan_formation.add(cbxperformance, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 290, 41));

        Tabs.addTab("Plan_formation", plan_formation);

        paneformation.setBackground(new java.awt.Color(51, 51, 51));
        paneformation.setPreferredSize(new java.awt.Dimension(1221, 667));
        paneformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneformationMouseClicked(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(51, 51, 51));

        Tableformation.setBackground(new java.awt.Color(51, 51, 51));
        Tableformation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Date début", "Durée", "Nombre heures", "Prestataire", "Intitulé Formation", "Type Formation", "Compétence Visée", "Parcours", "Objectifs", "Formateurs", "Cout total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tableformation.setOpaque(false);
        Tableformation.setSurrendersFocusOnKeystroke(true);
        jScrollPane3.setViewportView(Tableformation);

        buttonaddformation.setBackground(new java.awt.Color(51, 204, 0));
        buttonaddformation.setForeground(new java.awt.Color(255, 255, 255));
        buttonaddformation.setText("Ajouter Formation");
        buttonaddformation.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        buttonaddformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddformationActionPerformed(evt);
            }
        });

        buttonDeleteformation.setBackground(new java.awt.Color(255, 0, 0));
        buttonDeleteformation.setForeground(new java.awt.Color(255, 255, 255));
        buttonDeleteformation.setText("Supprimer");
        buttonDeleteformation.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        buttonDeleteformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteformationActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Cherche ici :");

        txtsearchf.setBackground(new java.awt.Color(0, 0, 0));
        txtsearchf.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        txtsearchf.setForeground(new java.awt.Color(204, 204, 204));
        txtsearchf.setInheritsPopupMenu(true);
        txtsearchf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchfMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtsearchfMousePressed(evt);
            }
        });
        txtsearchf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchfKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchfKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout paneformationLayout = new javax.swing.GroupLayout(paneformation);
        paneformation.setLayout(paneformationLayout);
        paneformationLayout.setHorizontalGroup(
            paneformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneformationLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(paneformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneformationLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearchf))
                    .addGroup(paneformationLayout.createSequentialGroup()
                        .addComponent(buttonaddformation, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addComponent(buttonDeleteformation, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
                .addGap(4, 4, 4))
        );
        paneformationLayout.setVerticalGroup(
            paneformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(paneformationLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(paneformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonaddformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(paneformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtsearchf, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(445, Short.MAX_VALUE))
        );

        Tabs.addTab("Formation", paneformation);

        paneplanf.setBackground(new java.awt.Color(51, 51, 51));
        paneplanf.setPreferredSize(new java.awt.Dimension(1221, 667));
        paneplanf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneplanfMouseClicked(evt);
            }
        });

        buttonaddPlanf.setBackground(new java.awt.Color(51, 204, 0));
        buttonaddPlanf.setForeground(new java.awt.Color(255, 255, 255));
        buttonaddPlanf.setText("Planifier");
        buttonaddPlanf.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        buttonaddPlanf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddPlanfActionPerformed(evt);
            }
        });

        buttondeletPlan.setBackground(new java.awt.Color(255, 0, 0));
        buttondeletPlan.setForeground(new java.awt.Color(255, 255, 255));
        buttondeletPlan.setText("Supprimer");
        buttondeletPlan.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        buttondeletPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttondeletPlanActionPerformed(evt);
            }
        });

        TablePlanf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Formation", "Employée", "Date début", "Durée", "Présence", "Note Formation", "Performance", "Commentaie", "Plan number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablePlanf.setAutoscrolls(false);
        TablePlanf.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jScrollPane5.setViewportView(TablePlanf);

        javax.swing.GroupLayout paneplanfLayout = new javax.swing.GroupLayout(paneplanf);
        paneplanf.setLayout(paneplanfLayout);
        paneplanfLayout.setHorizontalGroup(
            paneplanfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneplanfLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonaddPlanf, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(buttondeletPlan, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        paneplanfLayout.setVerticalGroup(
            paneplanfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneplanfLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(paneplanfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonaddPlanf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttondeletPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );

        Tabs.addTab("Plan Fomation", paneplanf);

        paneemploye.setBackground(new java.awt.Color(51, 51, 51));
        paneemploye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneemployeMouseClicked(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));

        TableEmploye.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricule", "Nom", "Prenom", "Site", "Date Naissance", "Sexe", "Date recreutement", "Département", "CSP", "Fonction", "Age", "Ancienneté"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableEmploye);

        buttonaddemploye.setBackground(new java.awt.Color(0, 204, 0));
        buttonaddemploye.setForeground(new java.awt.Color(255, 255, 255));
        buttonaddemploye.setText("Ajouter Employée");
        buttonaddemploye.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        buttonaddemploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddemployeActionPerformed(evt);
            }
        });

        buttondeleteemploye.setBackground(new java.awt.Color(255, 51, 0));
        buttondeleteemploye.setForeground(new java.awt.Color(255, 255, 255));
        buttondeleteemploye.setText("Supprimer");
        buttondeleteemploye.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        buttondeleteemploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttondeleteemployeActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(204, 204, 204));
        jLabel30.setText("Cherche ici :");

        txtsearchemp.setBackground(new java.awt.Color(0, 0, 0));
        txtsearchemp.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        txtsearchemp.setForeground(new java.awt.Color(204, 204, 204));
        txtsearchemp.setInheritsPopupMenu(true);
        txtsearchemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchempMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtsearchempMousePressed(evt);
            }
        });
        txtsearchemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchempKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchempKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchempKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout paneemployeLayout = new javax.swing.GroupLayout(paneemploye);
        paneemploye.setLayout(paneemployeLayout);
        paneemployeLayout.setHorizontalGroup(
            paneemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneemployeLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(paneemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(paneemployeLayout.createSequentialGroup()
                        .addComponent(buttonaddemploye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addComponent(buttondeleteemploye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneemployeLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearchemp, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))
                .addGap(4, 4, 4))
        );
        paneemployeLayout.setVerticalGroup(
            paneemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(paneemployeLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(paneemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonaddemploye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttondeleteemploye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(paneemployeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtsearchemp, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(432, Short.MAX_VALUE))
        );

        Tabs.addTab("Employées", paneemploye);

        form.setBackground(new java.awt.Color(51, 51, 51));
        form.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/outline_arrow_back_white_24dp.png"))); // NOI18N
        Backbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackbtnMouseClicked(evt);
            }
        });
        form.add(Backbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 63, 36));

        buttonsingin1.setBackground(new java.awt.Color(0, 0, 0));
        buttonsingin1.setForeground(new java.awt.Color(204, 204, 204));
        buttonsingin1.setText("Enregistrer");
        buttonsingin1.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        buttonsingin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonsingin1ActionPerformed(evt);
            }
        });
        form.add(buttonsingin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, 350, 70));

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Maticule");
        form.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 121, 41));

        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Site");
        form.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 121, 41));

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nom");
        form.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 167, 121, 41));

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Prenom");
        form.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 121, 41));

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date naissance");
        form.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 121, 41));

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Date recrutement");
        form.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 121, 41));

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fonction");
        form.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, 121, 41));

        jLabel12.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Depatement");
        form.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 121, 41));

        jLabel13.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CSP");
        form.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, 121, 41));

        jLabel11.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sexe");
        form.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 121, 41));

        txtdate1.setBackground(new java.awt.Color(102, 102, 102));
        txtdate1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtdate1.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 308, 260, 41));

        txtmat.setBackground(new java.awt.Color(102, 102, 102));
        txtmat.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtmat.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtmat, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 264, 41));

        txtnom.setBackground(new java.awt.Color(102, 102, 102));
        txtnom.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtnom.setForeground(new java.awt.Color(255, 255, 255));
        txtnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomActionPerformed(evt);
            }
        });
        form.add(txtnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 260, 41));

        txtepartement.setBackground(new java.awt.Color(102, 102, 102));
        txtepartement.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtepartement.setForeground(new java.awt.Color(255, 255, 255));
        txtepartement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtepartementActionPerformed(evt);
            }
        });
        form.add(txtepartement, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 167, 300, 41));

        txtfonction.setBackground(new java.awt.Color(102, 102, 102));
        txtfonction.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtfonction.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtfonction, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 358, 300, 41));

        txtcsp.setBackground(new java.awt.Color(102, 102, 102));
        txtcsp.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtcsp.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtcsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 238, 300, 41));

        RH.setBackground(new java.awt.Color(153, 153, 153));
        RH.setForeground(new java.awt.Color(255, 255, 255));
        RH.setText("Homme");
        RH.setOpaque(false);
        form.add(RH, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 128, -1));

        RF.setBackground(new java.awt.Color(0, 0, 0));
        RF.setForeground(new java.awt.Color(255, 255, 255));
        RF.setText("Femme");
        RF.setOpaque(false);
        RF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RFActionPerformed(evt);
            }
        });
        form.add(RF, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 110, 111, -1));

        txtprenom.setBackground(new java.awt.Color(102, 102, 102));
        txtprenom.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtprenom.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtprenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 238, 260, 41));

        txtdate2.setBackground(new java.awt.Color(102, 102, 102));
        txtdate2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtdate2.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtdate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(866, 308, 300, 41));

        txtsite2.setBackground(new java.awt.Color(102, 102, 102));
        txtsite2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtsite2.setForeground(new java.awt.Color(255, 255, 255));
        form.add(txtsite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 368, 260, 41));

        Tabs.addTab("Employées", form);

        formation.setBackground(new java.awt.Color(51, 51, 51));
        formation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Backbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/images/outline_arrow_back_white_24dp.png"))); // NOI18N
        Backbtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Backbtn1MouseClicked(evt);
            }
        });
        formation.add(Backbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 63, 36));

        buttonsingin2.setBackground(new java.awt.Color(0, 0, 0));
        buttonsingin2.setForeground(new java.awt.Color(204, 204, 204));
        buttonsingin2.setText("Enregistrer");
        buttonsingin2.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        buttonsingin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonsingin2ActionPerformed(evt);
            }
        });
        formation.add(buttonsingin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, 340, 70));

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date début");
        formation.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 90, 41));

        jLabel14.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Prestataire");
        formation.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 80, 41));

        jLabel15.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Durée");
        formation.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 70, 41));

        jLabel16.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Type");
        formation.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 50, 41));

        jLabel17.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nombre heurs");
        formation.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 100, 41));

        jLabel18.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Parcours");
        formation.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 80, 41));

        jLabel19.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Objectifs");
        formation.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 70, 41));

        jLabel20.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Intitulé");
        formation.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 80, 41));

        jLabel21.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Compétence Visée");
        formation.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 130, 41));

        jLabel22.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Formateur");
        formation.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 90, 41));

        txtnb.setBackground(new java.awt.Color(102, 102, 102));
        txtnb.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtnb.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtnb, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 300, 41));

        txtdatedebut.setBackground(new java.awt.Color(102, 102, 102));
        txtdatedebut.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtdatedebut.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtdatedebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 300, 41));

        txtduree.setBackground(new java.awt.Color(102, 102, 102));
        txtduree.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtduree.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtduree, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 300, 41));

        txtef.setBackground(new java.awt.Color(102, 102, 102));
        txtef.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtef.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtef, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 340, 41));

        txttype.setBackground(new java.awt.Color(102, 102, 102));
        txttype.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txttype.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txttype, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 300, 41));

        txtob.setBackground(new java.awt.Color(102, 102, 102));
        txtob.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtob.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtob, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 340, 41));

        txtp.setBackground(new java.awt.Color(102, 102, 102));
        txtp.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtp.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtp, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 340, 41));

        txtII.setBackground(new java.awt.Color(102, 102, 102));
        txtII.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtII.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtII, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 340, 41));

        jLabel23.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cout total");
        formation.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 90, 41));

        txtpre.setBackground(new java.awt.Color(102, 102, 102));
        txtpre.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtpre.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtpre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 300, 41));

        txtcv.setBackground(new java.awt.Color(102, 102, 102));
        txtcv.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtcv.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtcv, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 340, 41));

        txtcoout.setBackground(new java.awt.Color(102, 102, 102));
        txtcoout.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtcoout.setForeground(new java.awt.Color(255, 255, 255));
        formation.add(txtcoout, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 300, 41));

        Tabs.addTab("Formation", formation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void paneplanfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneplanfMouseClicked
        // TODO add your handling code here:
        popmenusearchformation.hide();
    }//GEN-LAST:event_paneplanfMouseClicked

    private void buttondeletPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttondeletPlanActionPerformed
        // TODO add your handling code here:

        int res= JOptionPane.showConfirmDialog(null,"veuillez vous vraiment supprimé ce plan","Information",JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.YES_OPTION) {
            try {
                conn=connexion.seConnecter();
                PreparedStatement s=conn.prepareStatement("delete from Plan_formation where Id=?");
                int row[] =TablePlanf.getSelectedRows();
                DefaultTableModel mode1 = (DefaultTableModel) TablePlanf.getModel();
                System.out.print(row.length);
                for(int i=row.length-1;i>=0;i--){
                    s.setInt(1,Integer.parseInt( Tableformation.getValueAt(row[i], 8).toString()));
                    mode1.removeRow(row[i]);
                }

            } catch (Exception ex) {
                Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_buttondeletPlanActionPerformed

    private void buttonaddPlanfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddPlanfActionPerformed
        // TODO add your handling code here:
        Tabs.remove(paneplanf);
        Tabs.add(plan_formation, 1);
        Tabs.setTitleAt(1, "Plan_frmation");
        Tabs.setSelectedComponent(plan_formation);
    }//GEN-LAST:event_buttonaddPlanfActionPerformed

    private void paneformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneformationMouseClicked
        // TODO add your handling code here:
        popmenusearchformation.hide();
    }//GEN-LAST:event_paneformationMouseClicked

    private void txtsearchfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchfKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsearchfKeyTyped

    private void txtsearchfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchfKeyReleased
        // TODO add your handling code here:
        try{
            String searchtxt =txtsearchf.getText().trim();
            conn=connexion.seConnecter();
            Statement s=conn.createStatement();
            ResultSet res=s.executeQuery("select Prestataire, Intitule_formation ,Type_formation,Competence_visee,Parcours,Objectifs,Formateur from Formation where Prestataire like '%"+txtsearchf.getText()+"%' or Intitule_formation like '%"+txtsearchf.getText()+"%' or Type_formation like '%"+txtsearchf.getText()+"%' or Competence_visee like '%"+txtsearchf.getText()+"%' or Parcours like '%"+txtsearchf.getText()+"%' or Objectifs like '%"+txtsearchf.getText()+"%' or Formateur like '%"+txtsearchf.getText()+"%' ");
            if(!searchtxt.equals("")){
                mod.removeAllElements();

                /*  for(String item:searchSuggestion(searchtxt)){

                    mod.addElement(item);
                }*/
                while(res.next()){
                    mod.addElement(res.getString(1));
                    mod.addElement(res.getString(2));
                    mod.addElement(res.getString(3));
                    mod.addElement(res.getString(4));
                    mod.addElement(res.getString(5));
                    mod.addElement(res.getString(6));
                    mod.addElement(res.getString(7));
                }
                // popmenusearchformation.setVisible(true);
                popmenusearchformation.show(txtsearchf, 0, txtsearchf.getHeight());
                //jPanel1.setVisible(true);
            } }catch(Exception e){
                e.printStackTrace();
            }
    }//GEN-LAST:event_txtsearchfKeyReleased

    private void txtsearchfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchfKeyPressed
        // TODO add your handling code here:

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {

                mode1=(DefaultTableModel) Tableformation.getModel();

                mode1.setRowCount(0);

                conn=connexion.seConnecter();
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select Code_formation, Date_debut, Nb_heurs, Duree,Prestataire, Intitule_formation ,Type_formation,Competence_visee,Parcours,Objectifs,Formateur, cout_total from Formation where Prestataire like '%"+txtsearchf.getText()+"%' or Intitule_formation like '%"+txtsearchf.getText()+"%' or Type_formation like '%"+txtsearchf.getText()+"%' or Competence_visee like '%"+txtsearchf.getText()+"%' or Parcours like '%"+txtsearchf.getText()+"%' or Objectifs like '%"+txtsearchf.getText()+"%' or Formateur like '%"+txtsearchf.getText()+"%' ");

                while(rs.next()){
                    mode1.addRow(new Object []{rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getFloat(12)});
                }
                rs.close();
            } catch (Exception ex) {

                Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtsearchfKeyPressed

    private void txtsearchfMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchfMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsearchfMousePressed

    private void txtsearchfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchfMouseClicked
        // TODO add your handling code here:
        popmenusearchformation.show(txtsearchf, 10, txtsearchf.getHeight());
        // jPanel1.setVisible(true);
    }//GEN-LAST:event_txtsearchfMouseClicked

    private void buttonDeleteformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteformationActionPerformed
        // TODO add your handling code here:
        int res= JOptionPane.showConfirmDialog(null,"veuillez vous vraiment supprimé ce Formation","Information",JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.YES_OPTION) {
            try {
                conn=connexion.seConnecter();
                PreparedStatement s=conn.prepareStatement("delete from  Formation where Code_formation=?");
                int row[] =Tableformation.getSelectedRows();
                DefaultTableModel mode1 = (DefaultTableModel) Tableformation.getModel();
                System.out.print(row.length);
                for(int i=row.length-1;i>=0;i--){
                    s.setInt(1,Integer.parseInt( Tableformation.getValueAt(row[i], 0).toString()));
                    mode1.removeRow(row[i]);
                }

            } catch (Exception ex) {
                Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_buttonDeleteformationActionPerformed

    private void buttonaddformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddformationActionPerformed
        // TODO add your handling code here:
        Tabs.remove(paneformation);
        Tabs.add(formation, 0);
        Tabs.setTitleAt(0, "Formation");
        Tabs.setSelectedComponent(formation);
    }//GEN-LAST:event_buttonaddformationActionPerformed

    private void buttonsingin3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonsingin3ActionPerformed
        // TODO add your handling ctxtode here:
        if(txtformationid.getText().equals("") || txtpresence.getText().equals("") ||txtnote.getText().equals("") ||txtemployeeid.getText().equals("") ||txtcomment.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Remplir tous les champes","attention",JOptionPane.WARNING_MESSAGE);
       }else{
             try {

            int formattion=Integer.parseInt(txtformationid.getText());
            int presence=Integer.parseInt(txtpresence.getText());
            int note=Integer.parseInt(txtnote.getText());
            String matricult=txtemployeeid.getText();
            String performance=cbxperformance.getSelectedItem().toString();
            String comment=txtcomment.getText();

            conn=connexion.seConnecter();
            PreparedStatement s=conn.prepareStatement("insert into Plan_formation values (seq_Plan_formation.nextval,?,?,?,?,?,?)");
            s.setInt(1,formattion);
            s.setString(2,matricult);
            s.setInt(3,presence);
            s.setInt(4,note);
            s.setString(5,performance);

            s.setString(6,comment);

            s.executeUpdate();

            JOptionPane.showMessageDialog(null,"Formation été planifié avec success");
            txtformationid.setText("");
            txtpresence.setText("");
            txtnote.setText("");
            txtemployeeid.setText("");
            txtcomment.setText("");
        } catch (Exception ex) {
            Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_buttonsingin3ActionPerformed

    private void Backbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Backbtn2MouseClicked
        // TODO add your handling code here:
        Tabs.remove(plan_formation);
        Tabs.add(paneplanf, 1);
        Tabs.setTitleAt(1, "Plan_formation");
        Tabs.setSelectedComponent(paneplanf);
    }//GEN-LAST:event_Backbtn2MouseClicked

    private void buttonsingin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonsingin2ActionPerformed
        // TODO add your handling code here:
          if(txtduree.getText().equals("") || txtnb.getText().equals("") ||txtpre.getText().equals("") ||txtp.getText().equals("") ||txttype.getText().equals("") ||txtcv.getText().equals("") ||txtII.getText().equals("") ||txtob.getText().equals("") ||txtcoout.getText().equals("") ||txtef.getText().equals("") ||txtdatedebut.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Remplir tous les champes","attention",JOptionPane.WARNING_MESSAGE);
       }else{
        try {

            int duree=Integer.parseInt(txtduree.getText());
            int nb=Integer.parseInt(txtnb.getText());
            String prestataire=txtpre.getText();
            String parcours=txtp.getText();
            String type=txttype.getText();
            String cv=txtcv.getText();
            String intitule=txtII.getText();
            String obct=txtob.getText();
            String formateur=txtef.getText();
            float cout=Float.parseFloat(txtcoout.getText());
            java.sql.Date date=java.sql.Date.valueOf(txtdatedebut.getText());

            conn=connexion.seConnecter();
            PreparedStatement s=conn.prepareStatement("insert into Formation values (seq_formation.nextval,?,?,?,?,?,?,?,?,?,?,?)");
            s.setDate(1,date);
            s.setInt(2,duree);
            s.setInt(3,nb);
            s.setString(4,prestataire);

            s.setString(5,intitule);

            s.setString(6,type);
            s.setString(7,cv);
            s.setString(8,parcours);
            s.setString(9,obct);
            s.setString(10,formateur);
            s.setFloat(11, cout);
            s.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null,"Formation est ajouté avec success");
            txtduree.setText("");
            txtnb.setText("");
            txtpre.setText("");
            txtp.setText("");
            txttype.setText("");
            txtcv.setText("");
            txtII.setText("");
            txtob.setText("");
            txtef.setText("");
            txtcoout.setText("");
            txtdatedebut.setText("");
        } catch (Exception ex) {
            Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
    }//GEN-LAST:event_buttonsingin2ActionPerformed

    private void Backbtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Backbtn1MouseClicked
        // TODO add your handling code here:
        Tabs.remove(formation);
        Tabs.add(paneformation, 0);
        Tabs.setTitleAt(0, "Formation");
        Tabs.setSelectedIndex(0);
        Tabs.setSelectedComponent(paneformation);
    }//GEN-LAST:event_Backbtn1MouseClicked

    private void RFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RFActionPerformed

    private void txtepartementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtepartementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtepartementActionPerformed

    private void txtnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomActionPerformed

    private void buttonsingin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonsingin1ActionPerformed
        // TODO add your handling code here:
         if(txtmat.getText().equals("") || txtnom.getText().equals("") ||txtprenom.getText().equals("") ||txtsite2.getText().equals("") ||txtepartement.getText().equals("") ||txtcsp.getText().equals("") ||txtfonction.getText().equals("") ||txtdate1.getText().equals("") ||txtdate2.getText().equals("")  ){
            JOptionPane.showMessageDialog(null,"Remplir tous les champes","attention",JOptionPane.WARNING_MESSAGE);
       }else{
        try {

            String nop=txtmat.getText();
            String nom= txtnom.getText();
            String prenom= txtprenom.getText();
            int sexe= 1;
            if(this.RF.isSelected()){
                sexe= 0;}
            String site=txtsite2.getText();
            String departement=txtepartement.getText();
            String csp=txtcsp.getText();
            String fonction=txtfonction.getText();
            java.sql.Date date=java.sql.Date.valueOf(txtdate1.getText());
            java.sql.Date dater=java.sql.Date.valueOf(txtdate2.getText());
            conn=connexion.seConnecter();
            PreparedStatement s=conn.prepareStatement("insert into Employees values (?,?,?,?,?,?,?,?,?,?)");
            s.setString(1,nop);
            s.setString(2,nom);
            s.setString(3,prenom);
            s.setInt(6,sexe);
            s.setString(4,site);
            s.setDate(7,dater);
            s.setDate(5,date);
            s.setString(8,departement);
            s.setString(9,csp);
            s.setString(10,fonction);

            s.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null,"Employée est ajouté avec success");
            txtmat.setText("");
            txtnom.setText("");
            txtprenom.setText("");
            txtdate1.setText("");
            txtepartement.setText("");
            txtcsp.setText("");
            txtfonction.setText("");
            txtdate2.setText("");
            txtsite2.setText("");
        } catch (Exception ex) {
            Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }//GEN-LAST:event_buttonsingin1ActionPerformed

    private void BackbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackbtnMouseClicked
        // TODO add your handling code here:
        Tabs.remove(form);
        Tabs.add(paneemploye);
        Tabs.setTitleAt(2, "Employées");
        Tabs.setSelectedIndex(2);
        Tabs.setSelectedComponent(paneemploye);
    }//GEN-LAST:event_BackbtnMouseClicked

    private void paneemployeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneemployeMouseClicked
        // TODO add your handling code here:
        popmenusearchformation.hide();
    }//GEN-LAST:event_paneemployeMouseClicked

    private void txtsearchempKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchempKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchempKeyTyped

    private void txtsearchempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchempKeyReleased
        // TODO add your handling code here:
        try{
            String searchtxt =txtsearchemp.getText().trim();
            if(!searchtxt.equals("")){
                mod.removeAllElements();
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select  Matricule, Nom, Prenom, Site ,Departement,CSP,Fonction from Employees where Matricule  like '%"+txtsearchf.getText()+"%' or Nom like '%"+txtsearchf.getText()+"%' or Prenom like '%"+txtsearchf.getText()+"%' or Site like '%"+txtsearchf.getText()+"%' or Departement like '%"+txtsearchf.getText()+"%' or CSP like '%"+txtsearchf.getText()+"%' or Fonction  like '%"+txtsearchf.getText()+"%'");
                /*  for(String item:searchSuggestion(searchtxt)){

                    mod.addElement(item);
                }*/
                while(rs.next()){
                    mod.addElement(rs.getString(1));
                    mod.addElement(rs.getString(2));
                    mod.addElement(rs.getString(3));
                    mod.addElement(rs.getString(4));
                    mod.addElement(rs.getString(5));
                    mod.addElement(rs.getString(6));
                    mod.addElement(rs.getString(7));

                }
                // popmenusearchformation.setVisible(true);
                popmenusearchformation.show(txtsearchemp, 0, txtsearchemp.getHeight());
                //jPanel1.setVisible(true);
            } }catch(Exception e){
                e.printStackTrace();
            }
    }//GEN-LAST:event_txtsearchempKeyReleased

    private void txtsearchempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchempKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {

                mode1=(DefaultTableModel) TableEmploye.getModel();

                mode1.setRowCount(0);

                conn=connexion.seConnecter();
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select Matricule, Nom, Prenom, Site, Date_naissance,Sexe,Date_recrutement,Departement,CSP,Fonction, Round(months_between(sysdate,Date_naissance)/12,0), Round(months_between(Date_naissance,Date_recrutement)/12,0) from Employees where Matricule like  '%"+txtsearchf.getText()+"%' or Nom like  '%"+txtsearchf.getText()+"%' or Prenom like  '%"+txtsearchf.getText()+"%' or Site like  '%"+txtsearchf.getText()+"%' or Sexe like  '%"+txtsearchf.getText()+"%' or CSP like '%"+txtsearchf.getText()+"%'  or Fonction like '%"+txtsearchf.getText()+"%' ");

                while(rs.next()){
                    String sexe = (rs.getInt(6)==1)?"Homme":"Femme";
                    mode1.addRow(new Object []{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),sexe,rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12)});
                }
                rs.close();
            } catch (Exception ex) {

                Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtsearchempKeyPressed

    private void txtsearchempMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchempMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchempMousePressed

    private void txtsearchempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchempMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchempMouseClicked

    private void buttondeleteemployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttondeleteemployeActionPerformed
        // TODO add your handling code here:
        int res= JOptionPane.showConfirmDialog(null,"veuillez vous vraiment supprimé ce Employée","Information",JOptionPane.YES_NO_OPTION);
        if(res==JOptionPane.YES_OPTION) {
            try {
                conn=connexion.seConnecter();
                PreparedStatement s=conn.prepareStatement("delete from Employees where Matricule=?");
                int row[] =TableEmploye.getSelectedRows();
                DefaultTableModel mode1 = (DefaultTableModel) TableEmploye.getModel();
                System.out.print(row.length);
                for(int i=row.length-1;i>=0;i--){
                    s.setString(1,TableEmploye.getValueAt(row[i], 0).toString());
                    mode1.removeRow(row[i]);
                }

            } catch (Exception ex) {
                Logger.getLogger(Data_form.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_buttondeleteemployeActionPerformed

    private void buttonaddemployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddemployeActionPerformed
        // TODO add your handling code here:

        Tabs.remove(paneemploye);
        Tabs.add(form);
        Tabs.setTitleAt(2, "Employées");

        Tabs.setSelectedComponent(form);

    }//GEN-LAST:event_buttonaddemployeActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        String txtlist=jList1.getSelectedValue();
        txtsearchemp.setText(txtlist);
        txtsearchf.setText(txtlist);
        
    }//GEN-LAST:event_jList1MouseClicked
public String[] searchSuggestion(String search) throws MalformedURLException, IOException, ParseException, org.json.simple.parser.ParseException {
    
    org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
    search = search.replace("", "+");
    URL oracle = new URL("http://suggestqueries.google.com/complete/search?q="+ search+ "&client=firefox&hl=fr");
    URLConnection yc= oracle.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
    String inputLine;
    String val = "";
    while ((inputLine = in.readLine()) !=null) {
        JSONArray a = (JSONArray) parser.parse(inputLine);
        for (Object o : a) {
            val = o.toString();
        }}
    in.close();
    String v[] = val.replace("[", " ").replace("]", " ").replace("\"", " ").split(",");
    if (v.length == 1 && v[0].equals(" ")) {
        return new String[0];
        
     } else {
        System.out.println(v);
        return v;}
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backbtn;
    private javax.swing.JLabel Backbtn1;
    private javax.swing.JLabel Backbtn2;
    private javax.swing.JRadioButton RF;
    private javax.swing.JRadioButton RH;
    private main.swing.DarkTable TableEmploye;
    private main.swing.DarkTable TablePlanf;
    private main.swing.DarkTable Tableformation;
    private javax.swing.JTabbedPane Tabs;
    private main.swing.Buttonsingin buttonDeleteformation;
    private main.swing.Buttonsingin buttonaddPlanf;
    private main.swing.Buttonsingin buttonaddemploye;
    private main.swing.Buttonsingin buttonaddformation;
    private main.swing.Buttonsingin buttondeletPlan;
    private main.swing.Buttonsingin buttondeleteemploye;
    private main.swing.Buttonsingin buttonsingin1;
    private main.swing.Buttonsingin buttonsingin2;
    private main.swing.Buttonsingin buttonsingin3;
    private javax.swing.JComboBox<String> cbxperformance;
    private javax.swing.JPanel form;
    private javax.swing.JPanel formation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel paneemploye;
    private javax.swing.JPanel paneformation;
    private javax.swing.JPanel paneplanf;
    private javax.swing.JPanel plan_formation;
    private javax.swing.JPopupMenu popmenusearchformation;
    private javax.swing.JTextField txtII;
    private javax.swing.JTextArea txtcomment;
    private javax.swing.JTextField txtcoout;
    private javax.swing.JTextField txtcsp;
    private javax.swing.JTextField txtcv;
    private javax.swing.JTextField txtdate1;
    private javax.swing.JTextField txtdate2;
    private javax.swing.JTextField txtdatedebut;
    private javax.swing.JTextField txtduree;
    private javax.swing.JTextField txtef;
    private javax.swing.JTextField txtemployeeid;
    private javax.swing.JTextField txtepartement;
    private javax.swing.JTextField txtfonction;
    private javax.swing.JTextField txtformationid;
    private javax.swing.JTextField txtmat;
    private javax.swing.JTextField txtnb;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtnote;
    private javax.swing.JTextField txtob;
    private javax.swing.JTextField txtp;
    private javax.swing.JTextField txtpre;
    private javax.swing.JTextField txtprenom;
    private javax.swing.JTextField txtpresence;
    private javax.swing.JTextField txtsearchemp;
    private javax.swing.JTextField txtsearchf;
    private javax.swing.JTextField txtsite2;
    private javax.swing.JTextField txttype;
    // End of variables declaration//GEN-END:variables
}
