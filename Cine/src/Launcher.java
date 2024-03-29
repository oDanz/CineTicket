

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author josue
 */
public class Launcher extends javax.swing.JFrame{
    
    /**
     * Creates new form Launcher
     */
    private Connection cn=null;
    private ResultSet rs=null;
    private Statement st=null;
    final String user;
    final String pass;
    	
    ArrayList<JCheckBox> boletosSelect = new ArrayList<>();
    ArrayList<Integer> asientosSelect = new ArrayList<>();
    private int cantBoletos = 0;
    private double totalVen = 0.00;
    
    final double precioAdulto = 60;
    final double precioNino = 40;
    final double porcDescuento = 0.70;
    private boolean[] asientos = new boolean[40];
    private int selectProyec = -1;
    private DefaultTableModel model;

    ChangeListener listenerSpinner = new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        cantBoletos = (Integer)cantAdulto.getValue() + (Integer)cantNino.getValue() + (Integer)cantEspecial.getValue();
        totalBoletos.setText(String.valueOf(cantBoletos));
        totalVen =    ((Integer)cantAdulto.getValue() * precioAdulto) + 
                        ((Integer)cantNino.getValue() * precioNino) +
                        ((Integer)cantEspecial.getValue() * (precioAdulto * porcDescuento));
        totalVenta.setText(String.valueOf(totalVen));
        estadoAsientos(false);
      }
    };
    public Launcher(String user, String pass) {
        initComponents();
        cantAdulto.addChangeListener(listenerSpinner);
        cantNino.addChangeListener(listenerSpinner);
        cantEspecial.addChangeListener(listenerSpinner);

        this.user = user;
        this.pass = pass;
        validaUsuario();
        
        conexion();
        actualizaFilm();
        actualizaSala();
        actualizaHorario();
        actualizaProyeccion();
        actualizaDetalleVenta();
        actualizaVenta();
        actualizaComboProyecciones();   
        if(user.equals("programador"))
            jTabbedPane1.setSelectedIndex(4);
        /*
            Detecta un cambio de pestaña y ejecuta una accion
        */
        jTabbedPane1.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            //System.out.println(""+jTabbedPane1.getSelectedIndex());
            if(jTabbedPane1.getSelectedIndex() == 1)    //Venta
                actualizaComboProyecciones();
            if(jTabbedPane1.getSelectedIndex()==2) //Proyeccion
            {
                try{     
                    st=cn.createStatement();
                    st.executeQuery("SELECT num_sala FROM Funciones.T_Sala;");
                    rs = st.getResultSet();
                    jComboBoxNumeroProyeccion.removeAllItems();
                    while(rs.next()){
                       String c = rs.getString("num_sala");
                       jComboBoxNumeroProyeccion.addItem(c);
                    }
                    st.executeQuery("SELECT id_horario FROM Funciones.T_Horario ORDER BY id_horario");
                    rs = st.getResultSet();
                    jComboBoxHorarioProyeccion.removeAllItems();
                    while(rs.next()){
                       String c = rs.getString("id_horario");
                       jComboBoxHorarioProyeccion.addItem(c);
                    }
                }catch(Exception err){
                    System.out.println(err);
                }
            }
            if(jTabbedPane1.getSelectedIndex() == 3)    //Sala
                actualizaProyeccion();
            if(jTabbedPane1.getSelectedIndex()==4) //Film
            {
                actualizaFilm();               
            }            
            if(jTabbedPane1.getSelectedIndex() == 5)//Horario
            {
             actualizaHorario();
             try{     
                    st=cn.createStatement();
                    st.executeQuery("SELECT nombre_film FROM Funciones.T_Film");
                    rs = st.getResultSet();
                    jComboBoxNombreHorario.removeAllItems();
                    while(rs.next()){
                       String c = rs.getString("nombre_film");
                       jComboBoxNombreHorario.addItem(c);
                    }
                }catch(Exception err){
                    System.out.println(err);
                }
            }
                
                
            //if(jTabbedPane1.getSelectedIndex() == 5)
             
        }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        proyecciones = new javax.swing.JComboBox<>();
        cantAdulto = new javax.swing.JSpinner();
        cantNino = new javax.swing.JSpinner();
        cantEspecial = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        totalBoletos = new javax.swing.JLabel();
        aceptar = new javax.swing.JButton();
        totalVenta = new javax.swing.JLabel();
        panelAsientos = new javax.swing.JPanel();
        s1 = new javax.swing.JCheckBox();
        s2 = new javax.swing.JCheckBox();
        s3 = new javax.swing.JCheckBox();
        s4 = new javax.swing.JCheckBox();
        s5 = new javax.swing.JCheckBox();
        s6 = new javax.swing.JCheckBox();
        s7 = new javax.swing.JCheckBox();
        s11 = new javax.swing.JCheckBox();
        s12 = new javax.swing.JCheckBox();
        s13 = new javax.swing.JCheckBox();
        s14 = new javax.swing.JCheckBox();
        s8 = new javax.swing.JCheckBox();
        s9 = new javax.swing.JCheckBox();
        s10 = new javax.swing.JCheckBox();
        s18 = new javax.swing.JCheckBox();
        s20 = new javax.swing.JCheckBox();
        s19 = new javax.swing.JCheckBox();
        s17 = new javax.swing.JCheckBox();
        s21 = new javax.swing.JCheckBox();
        s16 = new javax.swing.JCheckBox();
        s15 = new javax.swing.JCheckBox();
        s23 = new javax.swing.JCheckBox();
        s25 = new javax.swing.JCheckBox();
        s28 = new javax.swing.JCheckBox();
        s22 = new javax.swing.JCheckBox();
        s26 = new javax.swing.JCheckBox();
        s27 = new javax.swing.JCheckBox();
        s24 = new javax.swing.JCheckBox();
        s29 = new javax.swing.JCheckBox();
        s35 = new javax.swing.JCheckBox();
        s33 = new javax.swing.JCheckBox();
        s31 = new javax.swing.JCheckBox();
        s30 = new javax.swing.JCheckBox();
        s32 = new javax.swing.JCheckBox();
        s34 = new javax.swing.JCheckBox();
        s40 = new javax.swing.JCheckBox();
        s38 = new javax.swing.JCheckBox();
        s36 = new javax.swing.JCheckBox();
        s37 = new javax.swing.JCheckBox();
        s39 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableDetalleVenta = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableProyeccion = new javax.swing.JTable();
        jComboBoxNumeroProyeccion = new javax.swing.JComboBox<>();
        jComboBoxHorarioProyeccion = new javax.swing.JComboBox<>();
        jButtonInsertaProyeccion = new javax.swing.JButton();
        jButtonModificaProyeccion = new javax.swing.JButton();
        jButtonEliminaProyeccion = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNumeroSala = new javax.swing.JTextField();
        jTextFieldCupoSala = new javax.swing.JTextField();
        jComboBoxTipoSala = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSala = new javax.swing.JTable();
        jButtonInsertarSala = new javax.swing.JButton();
        jButtonModificarSala = new javax.swing.JButton();
        jButtonEliminarSala = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombreFilm = new javax.swing.JTextField();
        jTextFieldDuracionFilm = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFilm = new javax.swing.JTable();
        jButtonInsertaFilm = new javax.swing.JButton();
        jButtonModificaFilm = new javax.swing.JButton();
        jButtonEliminaFilm = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableHorario = new javax.swing.JTable();
        jTextFieldHoraHorario = new javax.swing.JTextField();
        jComboBoxNombreHorario = new javax.swing.JComboBox<>();
        jButtonInsertaHorario = new javax.swing.JButton();
        jButtonModificaHorario = new javax.swing.JButton();
        jButtonEliminaHorario = new javax.swing.JButton();
        jDateChooserHorario = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1200, 800));

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Proyeccion:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 43, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Cantidad Boletos:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 79, -1, -1));

        jLabel13.setText("Adulto:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 115, -1, -1));

        jLabel14.setText("Niño:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 141, -1, -1));

        jLabel15.setText("Especial:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 167, -1, -1));

        proyecciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Seleccionar -" }));
        jPanel1.add(proyecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 41, 240, -1));

        cantAdulto.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel1.add(cantAdulto, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 112, 71, -1));

        cantNino.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel1.add(cantNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 138, 71, -1));

        cantEspecial.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel1.add(cantEspecial, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 164, 71, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 202, 340, 10));

        totalBoletos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totalBoletos.setText("0");
        jPanel1.add(totalBoletos, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 79, -1, -1));

        aceptar.setText("Realizar Compra");
        aceptar.setEnabled(false);
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        jPanel1.add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 130, 30));

        totalVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        totalVenta.setText("0.00");
        jPanel1.add(totalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 223, -1, -1));

        javax.swing.GroupLayout panelAsientosLayout = new javax.swing.GroupLayout(panelAsientos);
        panelAsientos.setLayout(panelAsientosLayout);
        panelAsientosLayout.setHorizontalGroup(
            panelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        panelAsientosLayout.setVerticalGroup(
            panelAsientosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanel1.add(panelAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 260, 250));

        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 41, -1, -1));

        s2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 41, -1, -1));

        s3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 41, -1, -1));

        s4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 41, -1, -1));

        s5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s5, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 41, -1, -1));

        s6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s6, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 41, -1, -1));

        s7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s7, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 41, -1, -1));

        s11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s11, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 74, -1, -1));

        s12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s12, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 74, -1, -1));

        s13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s13, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 74, -1, -1));

        s14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s14, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 74, -1, -1));

        s8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s8, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 74, -1, -1));

        s9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 74, -1, -1));

        s10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s10, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 74, -1, -1));

        s18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s18, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 111, -1, -1));

        s20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s20, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 111, -1, -1));

        s19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s19, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 111, -1, -1));

        s17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s17, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 111, -1, -1));

        s21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s21, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 111, -1, -1));

        s16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 111, -1, -1));

        s15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s15, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 111, -1, -1));

        s23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, -1, -1));

        s25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s25, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 150, -1, -1));

        s28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s28, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 150, -1, -1));

        s22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s22, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 150, -1, -1));

        s26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s26, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 150, -1, -1));

        s27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s27, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 150, -1, -1));

        s24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s24, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 150, -1, -1));

        s29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s29, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 189, -1, -1));

        s35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s35, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 189, -1, -1));

        s33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s33, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 189, -1, -1));

        s31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s31, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 189, -1, -1));

        s30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s30, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 189, -1, -1));

        s32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s32, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 189, -1, -1));

        s34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s34, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 189, -1, -1));

        s40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s40, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 228, -1, -1));

        s38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s38, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 228, -1, -1));

        s36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s36, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 228, -1, -1));

        s37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s37, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 228, -1, -1));

        s39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsiento(evt);
            }
        });
        jPanel1.add(s39, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 228, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("7");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(651, 25, 10, -1));
        jLabel16.getAccessibleContext().setAccessibleDescription("");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("E");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("D");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 115, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("C");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 152, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("B");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 191, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("A");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(479, 228, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("F");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 43, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("F");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 43, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("1");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 25, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("2");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 25, 10, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("3");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 25, 10, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("4");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 25, 10, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("5");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 25, 10, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("6");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 25, 10, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("TOTAL:  $");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 223, -1, -1));

        jTableDetalleVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTableDetalleVenta);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 740, 190));

        jTabbedPane1.addTab("Detalle de venta", jPanel1);
        jPanel1.getAccessibleContext().setAccessibleName("");

        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTableVenta);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venta", jPanel2);

        jLabel9.setText("N° de Sala:");

        jLabel10.setText("Horarios:");

        jTableProyeccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableProyeccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProyeccionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableProyeccion);

        jComboBoxNumeroProyeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jComboBoxHorarioProyeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jButtonInsertaProyeccion.setText("Insertar");
        jButtonInsertaProyeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertaProyeccionActionPerformed(evt);
            }
        });

        jButtonModificaProyeccion.setText("Modificar");
        jButtonModificaProyeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificaProyeccionActionPerformed(evt);
            }
        });

        jButtonEliminaProyeccion.setText("Eliminar");
        jButtonEliminaProyeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaProyeccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(31, 31, 31)
                                .addComponent(jComboBoxNumeroProyeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(44, 44, 44)
                                .addComponent(jComboBoxHorarioProyeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonInsertaProyeccion)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonModificaProyeccion)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonEliminaProyeccion)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxNumeroProyeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsertaProyeccion)
                    .addComponent(jButtonModificaProyeccion)
                    .addComponent(jButtonEliminaProyeccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxHorarioProyeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Proyección", jPanel3);

        jLabel3.setText("N° de Sala:");

        jLabel4.setText("Cupo:");

        jLabel5.setText("Tipo:");

        jComboBoxTipoSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "3D", "4D"}));

        jTableSala.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSalaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSala);

        jButtonInsertarSala.setText("Insertar");
        jButtonInsertarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarSalaActionPerformed(evt);
            }
        });

        jButtonModificarSala.setText("Modificar");
        jButtonModificarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarSalaActionPerformed(evt);
            }
        });

        jButtonEliminarSala.setText("Eliminar");
        jButtonEliminarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarSalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCupoSala, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldNumeroSala, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(jButtonInsertarSala)
                                .addGap(45, 45, 45)
                                .addComponent(jButtonModificarSala)
                                .addGap(45, 45, 45)
                                .addComponent(jButtonEliminarSala))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jComboBoxTipoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(117, 117, 117))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNumeroSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsertarSala)
                    .addComponent(jButtonModificarSala)
                    .addComponent(jButtonEliminarSala))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCupoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTipoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        jTabbedPane1.addTab("Sala", jPanel4);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Duración:");

        jTableFilm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFilmMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFilm);

        jButtonInsertaFilm.setText("Insertar");
        jButtonInsertaFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertaFilmActionPerformed(evt);
            }
        });

        jButtonModificaFilm.setText("Modificar");
        jButtonModificaFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificaFilmActionPerformed(evt);
            }
        });

        jButtonEliminaFilm.setText("Eliminar");
        jButtonEliminaFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaFilmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldDuracionFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jTextFieldNombreFilm))
                        .addGap(31, 31, 31)
                        .addComponent(jButtonInsertaFilm)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonModificaFilm)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonEliminaFilm)))
                .addGap(117, 117, 117))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNombreFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsertaFilm)
                    .addComponent(jButtonModificaFilm)
                    .addComponent(jButtonEliminaFilm))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldDuracionFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        jTabbedPane1.addTab("Film", jPanel5);

        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel6FocusGained(evt);
            }
        });

        jLabel6.setText("Nombre:");

        jLabel7.setText("Fecha:");

        jLabel8.setText("Hora:");

        jTableHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHorarioMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableHorario);

        jComboBoxNombreHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jButtonInsertaHorario.setText("Insertar");
        jButtonInsertaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertaHorarioActionPerformed(evt);
            }
        });

        jButtonModificaHorario.setText("Modificar");
        jButtonModificaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificaHorarioActionPerformed(evt);
            }
        });

        jButtonEliminaHorario.setText("Eliminar");
        jButtonEliminaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldHoraHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jComboBoxNombreHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonInsertaHorario)
                                .addGap(45, 45, 45)
                                .addComponent(jButtonModificaHorario)
                                .addGap(45, 45, 45)
                                .addComponent(jButtonEliminaHorario))
                            .addComponent(jDateChooserHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxNombreHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInsertaHorario)
                    .addComponent(jButtonModificaHorario)
                    .addComponent(jButtonEliminaHorario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooserHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldHoraHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );

        jTabbedPane1.addTab("Horario", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
     private void validaUsuario()
    {
        if(user.equals( "cajero"))
        {
            jTabbedPane1.setEnabledAt(2, false);
            jTabbedPane1.setEnabledAt(3, false);
            jTabbedPane1.setEnabledAt(4, false);
            jTabbedPane1.setEnabledAt(5, false);
        }
        else if(user.equals("programador"))
        {
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, false);
            
        }
    }
     
    private void actualizaComboProyecciones()
    {
        try{   
            
            proyecciones.removeItemListener(proyecciones.getItemListeners()[0]);
            proyecciones.removeAllItems();
            proyecciones.addItem("- Seleccionar -");
            st=cn.createStatement();
            st.executeQuery("SELECT P.id_proyeccion, F.nombre_film, H.hora, H.fecha FROM Funciones.T_Proyeccion AS P INNER JOIN Funciones.T_Horario AS H ON P.id_horario = H.id_horario INNER JOIN Funciones.T_Film AS F ON H.id_film = F.id_film");
            rs = st.getResultSet();
            while(rs.next()){
               String proyeccion = rs.getString("id_proyeccion") +" - "+ rs.getString("nombre_film") + " - " + rs.getString("hora") + ", " + rs.getString("fecha");
               proyecciones.addItem(proyeccion);
            }

            proyecciones.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    String strAux = String.valueOf(proyecciones.getSelectedItem());
                    if(strAux.equals("- Seleccionar -"))
                    {
                        panelAsientos.setVisible(true);
                        cantAdulto.setValue(new Integer(0));
                        cantNino.setValue(new Integer(0));
                        cantEspecial.setValue(new Integer(0));
                        cantAdulto.setEnabled(false);                        
                        cantNino.setEnabled(false);
                        cantEspecial.setEnabled(false);
                        aceptar.setEnabled(false);
                        cantBoletos = 0;
                        totalBoletos.setText("0");
                        totalVenta.setText("0.00");
                        totalVen = 0;
                        boletosSelect = new ArrayList<JCheckBox>();
                        asientosSelect = new ArrayList<Integer>();
                        selectProyec = -1;
                        model = (DefaultTableModel) jTableDetalleVenta.getModel();
                        model.setRowCount(0);
                    }
                    else
                    {
                        selectProyec =  Integer.valueOf(strAux.substring(0, strAux.indexOf("-") - 1));
                        actualizaAsientosDisponibles(selectProyec);
                        if(cantBoletos == 0)
                            deshabilitarAsientos();
                        actualizaDetalleVenta();
                        panelAsientos.setVisible(false);
                        cantAdulto.setEnabled(true);                        
                        cantNino.setEnabled(true);
                        cantEspecial.setEnabled(true);
                        aceptar.setEnabled(false);
                    } 
                }
            });
        }catch(Exception err){
            System.out.println(err);
        }
    }    
   
    private void actualizaFilm(){
        /*
        Actualiza la tabla
        */
        try{
            String renglon[][] = {};
            String columna[] = {"id_film","nombre_film","duracion"};
            String query="SELECT * FROM Funciones.T_Film";
            model = new DefaultTableModel(renglon,columna);
            jTableFilm.setModel(model);
            rs = st.executeQuery(query);
            Object tuplas[] = new Object[3];
            while(rs.next())
            {
                tuplas[0] = rs.getObject("id_film");
                tuplas[1] = rs.getObject("nombre_film");
                tuplas[2] = rs.getObject("duracion");
                model.addRow(tuplas);
                System.out.println(model.getRowCount());
            }
        }catch(Exception e){
            System.out.println("Error al cargar los datos");
        }
    }
   
    private void actualizaSala(){
        /*
        Actualiza la tabla
        */
        try{
            String renglon[][] = {};
            String columna[] = {"id_sala","num_sala","capacidad", "tipo_sala"};
            String query = "SELECT * FROM Funciones.T_Sala";
            model = new DefaultTableModel(renglon,columna);
            jTableSala.setModel(model);
            rs = st.executeQuery(query);
            Object tuplas[] = new Object[4];
            while(rs.next())
            {
                tuplas[0] = rs.getObject("id_sala");
                tuplas[1] = rs.getObject("num_sala");
                tuplas[2] = rs.getObject("capacidad");
                tuplas[3] = rs.getObject("tipo_sala");
                model.addRow(tuplas);               
            }
            
        }catch(Exception e){
            System.out.println("Error al cargar los datos");
        }
    }
    
    private void actualizaHorario(){
        /*
        Actualiza la tabla
        */
        try{
            String renglon[][] = {};
            String columna[] = {"id_horario","id_film","hora", "fecha"};
            String query = "SELECT * FROM Funciones.T_Horario";
            model = new DefaultTableModel(renglon,columna);
            jTableHorario.setModel(model);
            rs = st.executeQuery(query);
            Object tuplas[] = new Object[4];
            while(rs.next())
            {
                tuplas[0] = rs.getObject("id_horario");
                tuplas[1] = rs.getObject("id_film");
                tuplas[2] = rs.getObject("hora");
                tuplas[3] = rs.getObject("fecha");
                model.addRow(tuplas);
                System.out.println(model.getRowCount());
            }
            
        }catch(Exception e){
            System.out.println("Error al cargar los datos");
        }
    }
    
    private void actualizaProyeccion(){
        /*
        Actualiza la tabla T_Proyeccion
        */
        try{
            String renglon[][] = {};
            String columna[] = {"id_proyeccion", "id_sala", "id_horario", "asientos_disponibles"};
            String query = "SELECT * FROM Funciones.T_Proyeccion";
            model = new DefaultTableModel(renglon,columna);
            jTableProyeccion.setModel(model);
            rs = st.executeQuery(query);
            Object tuplas[] = new Object[4];
            while(rs.next())
            {
                tuplas[0] = rs.getObject("id_proyeccion");
                tuplas[1] = rs.getObject("id_sala");
                tuplas[2] = rs.getObject("id_horario");
                tuplas[3] = rs.getObject("asientos_disponibles");
                model.addRow(tuplas);
                System.out.println(model.getRowCount());
            }
            
        }catch(Exception e){
            System.out.println("Error al cargar los datos");
        }
    }
    
    private void actualizaDetalleVenta(){
    /*
    Actualiza la tabla T_DetalleVenta
    */
        try{
            if(selectProyec != -1)
            {
                String renglon[][] = {};
                String columna[] = {"id_detalleVenta", "id_venta", "id_proyeccion", "tipo_boleto", "asiento", "subtotal"};
                String query = "SELECT * FROM Ventas.T_DetalleVenta WHERE id_Proyeccion = " + selectProyec;
                model = new DefaultTableModel(renglon,columna);
                jTableDetalleVenta.setModel(model);
                rs = st.executeQuery(query);
                Object tuplas[] = new Object[6];
                while(rs.next())
                {
                    tuplas[0] = rs.getObject("id_detalleVenta");
                    tuplas[1] = rs.getObject("id_venta");
                    tuplas[2] = rs.getObject("id_proyeccion");
                    tuplas[3] = rs.getObject("tipo_boleto");
                    tuplas[4] = rs.getObject("asiento");
                    tuplas[5] = rs.getObject("subtotal");
                    model.addRow(tuplas);
                    System.out.println(model.getRowCount());
                }
            }
            
        }catch(Exception e){
            System.out.println("Error al cargar los datos");
        }
    }
    
     private void actualizaVenta(){
    /*
    Actualiza la tabla T_Venta
    */
        try{
            String renglon[][] = {};
            String columna[] = {"id_venta", "hora", "total", "iva"};
            String query = "SELECT * FROM Ventas.T_Venta";
            model = new DefaultTableModel(renglon,columna);
            jTableVenta.setModel(model);
            rs = st.executeQuery(query);
            Object tuplas[] = new Object[4];
            while(rs.next())
            {
                tuplas[0] = rs.getObject("id_venta");
                tuplas[1] = rs.getObject("hora");
                tuplas[2] = rs.getObject("total");
                tuplas[3] = rs.getObject("iva");
                model.addRow(tuplas);
                System.out.println(model.getRowCount());
            }
            
        }catch(Exception e){
            System.out.println("Error al cargar los datos de ventas");
        }
    }
        
    private void conexion(){
        try{
            String url = "jdbc:postgresql://localhost:5432/Cinema";
            Class.forName("org.postgresql.Driver");
            cn=DriverManager.getConnection(url,user,pass);
            st=cn.createStatement();
            if(cn!=null)
                System.out.println("Conexion establecida");
            
        }catch(Exception e)
        {
            System.out.println("Error de conexion");
        }
    }
    
    private void actualizaAsientosDisponibles(int idProyeccion){
         try{    
            for(int i = 0; i < 40 ; i++)
              asientos[i] = false;
            
            st = cn.createStatement();
            st.executeQuery("SELECT * FROM Ventas.T_DetalleVenta WHERE id_Proyeccion = " + idProyeccion);
            rs = st.getResultSet();
            while(rs.next()){
               String asiento = rs.getString("asiento");
               asientos[Integer.valueOf(asiento)-1] = true;
            }
        }catch(Exception err){
            System.out.println(err);
        }
        
        s1.setSelected(asientos[0]);
        s2.setSelected(asientos[1]);
        s3.setSelected(asientos[2]);
        s4.setSelected(asientos[3]);
        s5.setSelected(asientos[4]);
        s6.setSelected(asientos[5]);
        s7.setSelected(asientos[6]);
        s8.setSelected(asientos[7]);
        s9.setSelected(asientos[8]);
        s10.setSelected(asientos[9]);
        s11.setSelected(asientos[10]);
        s12.setSelected(asientos[11]);
        s13.setSelected(asientos[12]);
        s14.setSelected(asientos[13]);
        s15.setSelected(asientos[14]);
        s16.setSelected(asientos[15]);
        s17.setSelected(asientos[16]);
        s18.setSelected(asientos[17]);
        s19.setSelected(asientos[18]);
        s20.setSelected(asientos[19]);
        s21.setSelected(asientos[20]);
        s22.setSelected(asientos[21]);
        s23.setSelected(asientos[22]);
        s24.setSelected(asientos[23]);
        s25.setSelected(asientos[24]);
        s26.setSelected(asientos[25]);
        s27.setSelected(asientos[26]);
        s28.setSelected(asientos[27]);
        s29.setSelected(asientos[28]);
        s30.setSelected(asientos[29]);
        s31.setSelected(asientos[30]);
        s32.setSelected(asientos[31]);
        s33.setSelected(asientos[32]);
        s34.setSelected(asientos[33]);
        s35.setSelected(asientos[34]);
        s36.setSelected(asientos[35]);
        s37.setSelected(asientos[36]);
        s38.setSelected(asientos[37]);
        s39.setSelected(asientos[38]);
        s40.setSelected(asientos[39]);
        estadoAsientos(false);
        
    }
    
    public void deshabilitarAsientos(){
        s1.setEnabled(false);
        s2.setEnabled(false);
        s3.setEnabled(false);
        s4.setEnabled(false);
        s5.setEnabled(false);
        s6.setEnabled(false);
        s7.setEnabled(false);
        s8.setEnabled(false);
        s9.setEnabled(false);
        s10.setEnabled(false);
         s11.setEnabled(false);
        s12.setEnabled(false);
        s13.setEnabled(false);
        s14.setEnabled(false);
        s15.setEnabled(false);
        s16.setEnabled(false);
        s17.setEnabled(false);
        s18.setEnabled(false);
        s19.setEnabled(false);
        s20.setEnabled(false);
         s21.setEnabled(false);
        s22.setEnabled(false);
        s23.setEnabled(false);
        s24.setEnabled(false);
        s25.setEnabled(false);
        s26.setEnabled(false);
        s27.setEnabled(false);
        s28.setEnabled(false);
        s29.setEnabled(false);
        s30.setEnabled(false);
         s31.setEnabled(false);
        s32.setEnabled(false);
        s33.setEnabled(false);
        s34.setEnabled(false);
        s35.setEnabled(false);
        s36.setEnabled(false);
        s37.setEnabled(false);
        s38.setEnabled(false);
        s39.setEnabled(false);
        s40.setEnabled(false);
        
            
    }
    
    private void jButtonInsertaFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertaFilmActionPerformed
        /*
        *Inserta tupla en la tabla Film
        */
        try
        {
            st = cn.createStatement();
            String nomFilm = jTextFieldNombreFilm.getText();
            int duracion = Integer.parseInt(jTextFieldDuracionFilm.getText());
            String query = "INSERT INTO Funciones.T_Film(nombre_film,duracion) VALUES('" + nomFilm + "'," + duracion + ")";
            st.executeUpdate(query);
            //cn.commit();
            actualizaFilm();
            jTextFieldNombreFilm.setText("");
            jTextFieldDuracionFilm.setText("");
            System.out.println("Tupla insertada correctamente");
        }catch(Exception ex)
        {
            String exc = "ERROR: llave duplicada viola restricción de unicidad «unico_film»";
            final String ss = ex.getMessage();
            if(ss.contains(exc)){
                JOptionPane.showMessageDialog(null, "La película ya existe");  
            }
            System.out.println(ss);
        }
    }//GEN-LAST:event_jButtonInsertaFilmActionPerformed

    private void jTableFilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFilmMouseClicked
        // TODO add your handling code here:
        jTextFieldNombreFilm.setText(jTableFilm.getValueAt(jTableFilm.getSelectedRow(), 1).toString());
        jTextFieldDuracionFilm.setText(jTableFilm.getValueAt(jTableFilm.getSelectedRow(), 2).toString());
    }//GEN-LAST:event_jTableFilmMouseClicked

    private void jButtonModificaFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificaFilmActionPerformed
        /*
        Modifica la tabla T_Film
        */
        try
        {
            int id_film = Integer.parseInt(jTableFilm.getValueAt(jTableFilm.getSelectedRow(), 0).toString());
            st=cn.createStatement();
            String nomFilm = jTextFieldNombreFilm.getText();
            int duracion = Integer.parseInt(jTextFieldDuracionFilm.getText());
            String query = "UPDATE Funciones.T_Film SET nombre_film='" + nomFilm + "', duracion=" + duracion + "  WHERE id_film=" + id_film + ";";
            st.executeUpdate(query);
            //cn.commit();
            actualizaFilm();
            jTextFieldNombreFilm.setText("");
            jTextFieldDuracionFilm.setText("");
            System.out.println("Tupla modificada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al modificar");
        }
    }//GEN-LAST:event_jButtonModificaFilmActionPerformed

    private void jButtonEliminaFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaFilmActionPerformed
        /*
        Elimina una tupla de la tabla T_Film
        */
        try
        {
            int id_film = Integer.parseInt(jTableFilm.getValueAt(jTableFilm.getSelectedRow(), 0).toString());
            st=cn.createStatement();
            String query = "DELETE FROM Funciones.T_Film WHERE id_film=" + id_film + ";";
            st.executeUpdate(query);
            //cn.commit();
            actualizaFilm();
            jTextFieldNombreFilm.setText("");
            jTextFieldDuracionFilm.setText("");
            System.out.println("Tupla eliminada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al eliminar");
        }
    }//GEN-LAST:event_jButtonEliminaFilmActionPerformed

    private void jTableSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSalaMouseClicked
        // TODO add your handling code here:
        jTextFieldNumeroSala.setText(jTableSala.getValueAt(jTableSala.getSelectedRow(), 1).toString());
        jTextFieldCupoSala.setText(jTableSala.getValueAt(jTableSala.getSelectedRow(), 2).toString());
        
        jComboBoxTipoSala.setSelectedItem(jTableSala.getValueAt(jTableSala.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_jTableSalaMouseClicked

    private void jButtonInsertarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarSalaActionPerformed
        /*
        *Inserta tupla en la tabla Sala
        */
        try
        {
            st=cn.createStatement();
            int num_sala = Integer.parseInt(jTextFieldNumeroSala.getText());
            int cupo = Integer.parseInt(jTextFieldCupoSala.getText());
            String tipo = jComboBoxTipoSala.getSelectedItem().toString();
            String query = "INSERT INTO Funciones.T_Sala(num_sala,capacidad,tipo_sala) VALUES("+ num_sala + "," + cupo + ",'" + tipo +"')";
            st.executeUpdate(query);
            //cn.commit();
            actualizaSala();
            jTextFieldNumeroSala.setText("");
            jTextFieldCupoSala.setText("");
            System.out.println("Tupla insertada correctamente");
        }catch(Exception ex)
        {
            String exc = "ERROR: llave duplicada viola restricción de unicidad «unica_sala»";
            final String ss = ex.getMessage();
            if(ss.contains(exc)){
                JOptionPane.showMessageDialog(null, "El número de sala ya existe");  
            }
            //JOptionPane.showMessageDialog(null, "El número de sala ya existe");
            System.out.println(ss);
        }
    }//GEN-LAST:event_jButtonInsertarSalaActionPerformed

    private void jButtonModificarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarSalaActionPerformed
        /*
        Modifica la tabla T_Sala
        */
        try
        {
            int id_sala = Integer.parseInt(jTableSala.getValueAt(jTableSala.getSelectedRow(), 0).toString());
            st=cn.createStatement();
            int num_sala = Integer.parseInt(jTextFieldNumeroSala.getText());
            int capacidad = Integer.parseInt(jTextFieldCupoSala.getText());
            String tipo_sala = jComboBoxTipoSala.getSelectedItem().toString();
            String query = "UPDATE Funciones.T_Sala SET num_sala=" + num_sala + ", capacidad=" + capacidad + ", tipo_sala='" + tipo_sala +"'  WHERE id_sala=" + id_sala + ";";
            st.executeUpdate(query);
            //cn.commit();
            actualizaSala();
            jTextFieldNumeroSala.setText("");
            jTextFieldCupoSala.setText("");
            System.out.println("Tupla modificada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al modificar");
        }
    }//GEN-LAST:event_jButtonModificarSalaActionPerformed

    private void jButtonEliminarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarSalaActionPerformed
        /*
        Elimina una tupla de la tabla T_Sala
        */
        try
        {
            int id_sala = Integer.parseInt(jTableSala.getValueAt(jTableSala.getSelectedRow(), 0).toString());
            st=cn.createStatement();
            String query = "DELETE FROM Funciones.T_Sala WHERE id_sala=" + id_sala + ";";
            st.executeUpdate(query);
            //cn.commit();
            actualizaSala();
            jTextFieldNumeroSala.setText("");
            jTextFieldCupoSala.setText("");
            System.out.println("Tupla eliminada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al eliminar");
        }
    }//GEN-LAST:event_jButtonEliminarSalaActionPerformed

    private void jTableHorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHorarioMouseClicked
        try{
            st=cn.createStatement();
        int id_film = Integer.parseInt(jTableHorario.getValueAt(jTableHorario.getSelectedRow(), 1).toString());
        String select = "SELECT nombre_film FROM Funciones.T_Film WHERE id_film=" + id_film + ";";
        rs = st.executeQuery(select);
        rs.next();
        String nombre_film = rs.getString("nombre_film");
        jComboBoxNombreHorario.setSelectedItem(nombre_film);
        }
        catch(Exception ex){
           System.out.println(ex); 
        }
        
        jTextFieldHoraHorario.setText(jTableHorario.getValueAt(jTableHorario.getSelectedRow(), 2).toString());
        //jComboBoxNombreHorario.setSelectedItem(jTableHorario.getValueAt(jTableHorario.getSelectedRow(), 1).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(jTableHorario.getValueAt(jTableHorario.getSelectedRow(), 3));
        java.util.Date date;
        try {
            date = sdf.parse(fecha);
            jDateChooserHorario.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableHorarioMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6FocusGained

    private void jButtonInsertaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertaHorarioActionPerformed
        /*
        *Inserta tupla en la tabla Horario
        */
        try
        {
            st=cn.createStatement();
            
            String nombre_film = jComboBoxNombreHorario.getSelectedItem().toString();
            String select = "SELECT id_film FROM Funciones.T_Film WHERE nombre_film='" + nombre_film + "';";
            rs = st.executeQuery(select);
            rs.next();
            int id_film = Integer.parseInt(rs.getString("id_film"));
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(jDateChooserHorario.getDate());
            //int id_film = Integer.parseInt(jComboBoxNombreHorario.getSelectedItem().toString());
            String hora = jTextFieldHoraHorario.getText();
            String query = "INSERT INTO Funciones.T_Horario(id_film, hora, fecha) VALUES("+ id_film + ",'" + hora + "','" + fecha +"')";
            st.executeUpdate(query);
            actualizaHorario();
            jTextFieldHoraHorario.setText("");
            System.out.println("Tupla insertada correctamente");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButtonInsertaHorarioActionPerformed

    private void jButtonModificaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificaHorarioActionPerformed
        /*
        Modifica tabla T_Horario
        */
        try
        {
            int id_horario = Integer.parseInt(jTableHorario.getValueAt(jTableHorario.getSelectedRow(), 0).toString());
            st=cn.createStatement();
            
            String nombre_film  = jComboBoxNombreHorario.getSelectedItem().toString();
            String select = "SELECT id_film FROM Funciones.T_Film WHERE nombre_film='" + nombre_film + "';";
            rs = st.executeQuery(select);
            rs.next();
            int id_film = Integer.parseInt(rs.getString("id_film"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(jDateChooserHorario.getDate());
            //int id_film = Integer.parseInt(jComboBoxNombreHorario.getSelectedItem().toString());
            String hora = jTextFieldHoraHorario.getText();
            String query = "UPDATE Funciones.T_Horario SET id_film="+ id_film + ",hora='" + hora + "',fecha='" + fecha +"' WHERE id_horario=" + id_horario;
            st.executeUpdate(query);
            actualizaHorario();
            jTextFieldHoraHorario.setText("");
            System.out.println("Tupla modificada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al modificar");
        }
    }//GEN-LAST:event_jButtonModificaHorarioActionPerformed

    private void jButtonEliminaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaHorarioActionPerformed
        /*
        Elimina tabla T_Horario
        */
        try
        {
            int id_horario = Integer.parseInt(jTableHorario.getValueAt(jTableHorario.getSelectedRow(), 0).toString());
            st=cn.createStatement();
            String query = "DELETE FROM Funciones.T_Horario WHERE id_horario="+ id_horario;
            st.executeUpdate(query);
            actualizaHorario();
            jTextFieldHoraHorario.setText("");
            System.out.println("Tupla eliminada correctamente");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButtonEliminaHorarioActionPerformed

    private void jButtonInsertaProyeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertaProyeccionActionPerformed
        /*
        *Inserta tupla en la tabla T_Proyeccion
        */
        try
        {
            st=cn.createStatement();
            int num_sala = Integer.parseInt(jComboBoxNumeroProyeccion.getSelectedItem().toString());
            int id_horario = Integer.parseInt(jComboBoxHorarioProyeccion.getSelectedItem().toString());
            String select = "SELECT id_sala FROM Funciones.T_Sala WHERE num_sala=" + num_sala + ";";
            rs = st.executeQuery(select);
            rs.next();
            int id_sala = Integer.parseInt(rs.getString("id_sala"));
            String query = "INSERT INTO Funciones.T_Proyeccion(id_sala, id_horario, asientos_disponibles) VALUES("+ id_sala + "," + id_horario + "," + 40 +")";
            st.executeUpdate(query);
            actualizaProyeccion();
            System.out.println("Tupla insertada correctamente");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButtonInsertaProyeccionActionPerformed

    private void jTableProyeccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProyeccionMouseClicked
        try{
            st=cn.createStatement();
            int id_sala = Integer.parseInt(jTableProyeccion.getValueAt(jTableProyeccion.getSelectedRow(), 1).toString());
            String select = "SELECT num_sala FROM Funciones.T_Sala WHERE id_sala=" + id_sala + ";";
            rs = st.executeQuery(select);
            rs.next();
            String num_sala = rs.getString("num_sala");
            jComboBoxNumeroProyeccion.setSelectedItem(num_sala);
            jComboBoxHorarioProyeccion.setSelectedItem(jTableProyeccion.getValueAt(jTableProyeccion.getSelectedRow(), 2).toString());   
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jTableProyeccionMouseClicked

    private void jButtonModificaProyeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificaProyeccionActionPerformed
        /*
        *Modifica tupla en la tabla T_Proyeccion
        */
        try
        {
            st=cn.createStatement();
            int num_sala = Integer.parseInt(jComboBoxNumeroProyeccion.getSelectedItem().toString());
            String select = "SELECT id_sala FROM Funciones.T_Sala WHERE num_sala=" + num_sala + ";";
            rs = st.executeQuery(select);
            rs.next();
            int id_sala = Integer.parseInt(rs.getString("id_sala"));
            int id_horario = Integer.parseInt(jComboBoxHorarioProyeccion.getSelectedItem().toString());
            int asientos_disponibles = Integer.parseInt(jTableProyeccion.getValueAt(jTableProyeccion.getSelectedRow(), 3).toString());
            int id_proyeccion = Integer.parseInt(jTableProyeccion.getValueAt(jTableProyeccion.getSelectedRow(), 0).toString());
            String query = "UPDATE Funciones.T_Proyeccion SET id_sala="+ id_sala + ",id_horario=" + id_horario + ",asientos_disponibles=" + asientos_disponibles + "WHERE id_proyeccion="
                    + id_proyeccion;
            st.executeUpdate(query);
            actualizaProyeccion();
            System.out.println("Tupla modificada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al modificar");
        }
    }//GEN-LAST:event_jButtonModificaProyeccionActionPerformed

    private void jButtonEliminaProyeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaProyeccionActionPerformed
        /*
        *Elimina tupla en la tabla T_Proyeccion
        */
        try
        {
            st=cn.createStatement();
            int id_proyeccion = Integer.parseInt(jTableProyeccion.getValueAt(jTableProyeccion.getSelectedRow(), 0).toString());
            String query = "DELETE FROM Funciones.T_Proyeccion WHERE id_proyeccion=" + id_proyeccion + ";";
            st.executeUpdate(query);
            actualizaProyeccion();
            System.out.println("Tupla eliminada correctamente");
        }catch(Exception e)
        {
            System.out.println("Error al eliminar");
        }
    }//GEN-LAST:event_jButtonEliminaProyeccionActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        int idVenta = -1, hora, minutos;
        Calendar calendario = Calendar.getInstance();
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        String horaActual = String.valueOf(hora) + ":" +String.valueOf(minutos);
        
        try{
            st=cn.createStatement();
            String query = "INSERT INTO Ventas.T_Venta(hora, total, iva) VALUES('"+ horaActual +"', 0, 0)";
            st.executeUpdate(query);            
        }catch(Exception ex){}
        
        try{
            st = cn.createStatement();
            st.executeQuery("SELECT id_venta FROM Ventas.T_Venta ORDER BY id_venta DESC LIMIT 1");
            rs = st.getResultSet();
            while(rs.next()){
               String id_venta = rs.getString("id_venta");
               idVenta = Integer.valueOf(id_venta);
            }
        }catch(Exception ex){}  
        Iterator<Integer> asiento = asientosSelect.iterator();
        while(asiento.hasNext()){
            for(int i = 0; i < (Integer)cantAdulto.getValue(); i++)
            {
                try
                {                  
                    st=cn.createStatement();
                    String query = "INSERT INTO Ventas.T_DetalleVenta(id_venta, id_proyeccion, tipo_boleto, asiento, subtotal) VALUES(" + idVenta + "," + selectProyec + ", 'Adulto', " + asiento.next() + ","+ precioAdulto +")";
                    st.executeUpdate(query);
                    
                }catch(Exception ex){ }
            }
            for(int i = 0; i < (Integer)cantNino.getValue(); i++)
            {
                try
                {                  
                    st=cn.createStatement();
                    String query = "INSERT INTO Ventas.T_DetalleVenta(id_venta, id_proyeccion, tipo_boleto, asiento, subtotal) VALUES(" + idVenta + "," + selectProyec + ", 'Niño', " + asiento.next() + ","+ precioNino +")";
                    st.executeUpdate(query);
                    
                }catch(Exception ex){ }
            }
            for(int i = 0; i < (Integer)cantEspecial.getValue(); i++)
            {
                try
                {                  
                    st=cn.createStatement();
                    String query = "INSERT INTO Ventas.T_DetalleVenta(id_venta, id_proyeccion, tipo_boleto, asiento, subtotal) VALUES(" + idVenta + "," + selectProyec + ", 'Especial', " + asiento.next() + ","+ precioAdulto * porcDescuento  +")";
                    st.executeUpdate(query);
                    
                }catch(Exception ex){ }
            }
        }
        actualizaDetalleVenta();
        actualizaVenta();
        JOptionPane.showMessageDialog(null, "Venta realizada con exito", "Ventas", JOptionPane.INFORMATION_MESSAGE);

        proyecciones.setSelectedIndex(0);
        
    }//GEN-LAST:event_aceptarActionPerformed

    private void selectAsiento(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsiento
        JCheckBox box = (JCheckBox)evt.getSource();
        if(box.isSelected())
            boletosSelect.add(box);
        else
            boletosSelect.remove(box);
        
        if(boletosSelect.size() < cantBoletos)
        {
           estadoAsientos(false);
           aceptar.setEnabled(false);
        }
        else
        {
            aceptar.setEnabled(true);
            deshabilitarAsientos();
            Iterator<JCheckBox> asiento = boletosSelect.iterator();
            while(asiento.hasNext()){
                JCheckBox JCB = asiento.next();
                JCB.setEnabled(true);
            }
        } 
        asientosSelect =  new ArrayList<Integer>();
        Iterator<JCheckBox> asiento = boletosSelect.iterator();
        while(asiento.hasNext()){
            JCheckBox JCB = asiento.next();
            if(JCB == s1)
                asientosSelect.add(1);
            if(JCB == s2)
                asientosSelect.add(2);
            if(JCB == s3)
                asientosSelect.add(3);
            if(JCB == s4)
                asientosSelect.add(4);
            if(JCB == s5)
                asientosSelect.add(5);
            if(JCB == s6)
                asientosSelect.add(6);
            if(JCB == s7)
                asientosSelect.add(7);
            if(JCB == s8)
                asientosSelect.add(8);
            if(JCB == s9)
                asientosSelect.add(9);
            if(JCB == s10)
                asientosSelect.add(10);
            if(JCB == s11)
                asientosSelect.add(11);
            if(JCB == s12)
                asientosSelect.add(12);
            if(JCB == s13)
                asientosSelect.add(13);
            if(JCB == s14)
                asientosSelect.add(14);
            if(JCB == s15)
                asientosSelect.add(15);
            if(JCB == s16)
                asientosSelect.add(16);
            if(JCB == s17)
                asientosSelect.add(17);
            if(JCB == s18)
                asientosSelect.add(18);
            if(JCB == s19)
                asientosSelect.add(19);
            if(JCB == s20)
                asientosSelect.add(20); 
            if(JCB == s21)
                asientosSelect.add(21);
            if(JCB == s22)
                asientosSelect.add(22);
            if(JCB == s23)
                asientosSelect.add(23);
            if(JCB == s24)
                asientosSelect.add(24);
            if(JCB == s25)
                asientosSelect.add(25);
            if(JCB == s26)
                asientosSelect.add(26);
            if(JCB == s27)
                asientosSelect.add(27);
            if(JCB == s28)
                asientosSelect.add(28);
            if(JCB == s29)
                asientosSelect.add(29);
            if(JCB == s30)
                asientosSelect.add(30); 
            if(JCB == s31)
                asientosSelect.add(31);
            if(JCB == s32)
                asientosSelect.add(32);
            if(JCB == s33)
                asientosSelect.add(33);
            if(JCB == s34)
                asientosSelect.add(34);
            if(JCB == s35)
                asientosSelect.add(35);
            if(JCB == s36)
                asientosSelect.add(36);
            if(JCB == s37)
                asientosSelect.add(37);
            if(JCB == s38)
                asientosSelect.add(38);
            if(JCB == s39)
                asientosSelect.add(39);
            if(JCB == s40)
                asientosSelect.add(40);   
        }
        
        
    }//GEN-LAST:event_selectAsiento
  
    public void estadoAsientos(boolean activo){
        if(activo)
        {
            s1.setEnabled(asientos[0]);
            s2.setEnabled(asientos[1]);
            s3.setEnabled(asientos[2]);
            s4.setEnabled(asientos[3]);
            s5.setEnabled(asientos[4]);
            s6.setEnabled(asientos[5]);
            s7.setEnabled(asientos[6]);
            s8.setEnabled(asientos[7]);
            s9.setEnabled(asientos[8]);
            s10.setEnabled(asientos[9]);
            s11.setEnabled(asientos[10]);
            s12.setEnabled(asientos[11]);
            s13.setEnabled(asientos[12]);
            s14.setEnabled(asientos[13]);
            s15.setEnabled(asientos[14]);
            s16.setEnabled(asientos[15]);
            s17.setEnabled(asientos[16]);
            s18.setEnabled(asientos[17]);
            s19.setEnabled(asientos[18]);
            s20.setEnabled(asientos[19]);
            s21.setEnabled(asientos[20]);
            s22.setEnabled(asientos[21]);
            s23.setEnabled(asientos[22]);
            s24.setEnabled(asientos[23]);
            s25.setEnabled(asientos[24]);
            s26.setEnabled(asientos[25]);
            s27.setEnabled(asientos[26]);
            s28.setEnabled(asientos[27]);
            s29.setEnabled(asientos[28]);
            s30.setEnabled(asientos[29]);
            s31.setEnabled(asientos[30]);
            s32.setEnabled(asientos[31]);
            s33.setEnabled(asientos[32]);
            s34.setEnabled(asientos[33]);
            s35.setEnabled(asientos[34]);
            s36.setEnabled(asientos[35]);
            s37.setEnabled(asientos[36]);
            s38.setEnabled(asientos[37]);
            s39.setEnabled(asientos[38]);
            s40.setEnabled(asientos[39]);
        }
        else{
            s1.setEnabled(!asientos[0]);
            s2.setEnabled(!asientos[1]);
            s3.setEnabled(!asientos[2]);
            s4.setEnabled(!asientos[3]);
            s5.setEnabled(!asientos[4]);
            s6.setEnabled(!asientos[5]);
            s7.setEnabled(!asientos[6]);
            s8.setEnabled(!asientos[7]);
            s9.setEnabled(!asientos[8]);
            s10.setEnabled(!asientos[9]);
            s11.setEnabled(!asientos[10]);
            s12.setEnabled(!asientos[11]);
            s13.setEnabled(!asientos[12]);
            s14.setEnabled(!asientos[13]);
            s15.setEnabled(!asientos[14]);
            s16.setEnabled(!asientos[15]);
            s17.setEnabled(!asientos[16]);
            s18.setEnabled(!asientos[17]);
            s19.setEnabled(!asientos[18]);
            s20.setEnabled(!asientos[19]);
            s21.setEnabled(!asientos[20]);
            s22.setEnabled(!asientos[21]);
            s23.setEnabled(!asientos[22]);
            s24.setEnabled(!asientos[23]);
            s25.setEnabled(!asientos[24]);
            s26.setEnabled(!asientos[25]);
            s27.setEnabled(!asientos[26]);
            s28.setEnabled(!asientos[27]);
            s29.setEnabled(!asientos[28]);
            s30.setEnabled(!asientos[29]);
            s31.setEnabled(!asientos[30]);
            s32.setEnabled(!asientos[31]);
            s33.setEnabled(!asientos[32]);
            s34.setEnabled(!asientos[33]);
            s35.setEnabled(!asientos[34]);
            s36.setEnabled(!asientos[35]);
            s37.setEnabled(!asientos[36]);
            s38.setEnabled(!asientos[37]);
            s39.setEnabled(!asientos[38]);
            s40.setEnabled(!asientos[39]);
        }
            
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Launcher().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JSpinner cantAdulto;
    private javax.swing.JSpinner cantEspecial;
    private javax.swing.JSpinner cantNino;
    private javax.swing.JButton jButtonEliminaFilm;
    private javax.swing.JButton jButtonEliminaHorario;
    private javax.swing.JButton jButtonEliminaProyeccion;
    private javax.swing.JButton jButtonEliminarSala;
    private javax.swing.JButton jButtonInsertaFilm;
    private javax.swing.JButton jButtonInsertaHorario;
    private javax.swing.JButton jButtonInsertaProyeccion;
    private javax.swing.JButton jButtonInsertarSala;
    private javax.swing.JButton jButtonModificaFilm;
    private javax.swing.JButton jButtonModificaHorario;
    private javax.swing.JButton jButtonModificaProyeccion;
    private javax.swing.JButton jButtonModificarSala;
    private javax.swing.JComboBox<String> jComboBoxHorarioProyeccion;
    private javax.swing.JComboBox<String> jComboBoxNombreHorario;
    private javax.swing.JComboBox<String> jComboBoxNumeroProyeccion;
    private javax.swing.JComboBox<String> jComboBoxTipoSala;
    private com.toedter.calendar.JDateChooser jDateChooserHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDetalleVenta;
    private javax.swing.JTable jTableFilm;
    private javax.swing.JTable jTableHorario;
    private javax.swing.JTable jTableProyeccion;
    private javax.swing.JTable jTableSala;
    private javax.swing.JTable jTableVenta;
    private javax.swing.JTextField jTextFieldCupoSala;
    private javax.swing.JTextField jTextFieldDuracionFilm;
    private javax.swing.JTextField jTextFieldHoraHorario;
    private javax.swing.JTextField jTextFieldNombreFilm;
    private javax.swing.JTextField jTextFieldNumeroSala;
    private javax.swing.JPanel panelAsientos;
    private javax.swing.JComboBox<String> proyecciones;
    private javax.swing.JCheckBox s1;
    private javax.swing.JCheckBox s10;
    private javax.swing.JCheckBox s11;
    private javax.swing.JCheckBox s12;
    private javax.swing.JCheckBox s13;
    private javax.swing.JCheckBox s14;
    private javax.swing.JCheckBox s15;
    private javax.swing.JCheckBox s16;
    private javax.swing.JCheckBox s17;
    private javax.swing.JCheckBox s18;
    private javax.swing.JCheckBox s19;
    private javax.swing.JCheckBox s2;
    private javax.swing.JCheckBox s20;
    private javax.swing.JCheckBox s21;
    private javax.swing.JCheckBox s22;
    private javax.swing.JCheckBox s23;
    private javax.swing.JCheckBox s24;
    private javax.swing.JCheckBox s25;
    private javax.swing.JCheckBox s26;
    private javax.swing.JCheckBox s27;
    private javax.swing.JCheckBox s28;
    private javax.swing.JCheckBox s29;
    private javax.swing.JCheckBox s3;
    private javax.swing.JCheckBox s30;
    private javax.swing.JCheckBox s31;
    private javax.swing.JCheckBox s32;
    private javax.swing.JCheckBox s33;
    private javax.swing.JCheckBox s34;
    private javax.swing.JCheckBox s35;
    private javax.swing.JCheckBox s36;
    private javax.swing.JCheckBox s37;
    private javax.swing.JCheckBox s38;
    private javax.swing.JCheckBox s39;
    private javax.swing.JCheckBox s4;
    private javax.swing.JCheckBox s40;
    private javax.swing.JCheckBox s5;
    private javax.swing.JCheckBox s6;
    private javax.swing.JCheckBox s7;
    private javax.swing.JCheckBox s8;
    private javax.swing.JCheckBox s9;
    private javax.swing.JLabel totalBoletos;
    private javax.swing.JLabel totalVenta;
    // End of variables declaration//GEN-END:variables
}




