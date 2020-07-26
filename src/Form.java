import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.SystemInfo;

public class Form extends javax.swing.JFrame {
    public SystemInfo si = new SystemInfo();
    public HardwareAbstractionLayer hal = si.getHardware();
    public GlobalMemory memory = hal.getMemory();
    public int memorySize = Math.toIntExact(memory.getTotal()/1024/1024);
    public static String os = System.getProperty("os.name").toLowerCase();
    public static String command;
    public static String filename="";
    public String path;
    public File g;
    public File data = null;
    public ArrayList<File>dat = new ArrayList<>();
    public Form(){
        initComponents();
        setLocationRelativeTo(null);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        jTextField1.setVisible(false);
        if(os.contains("win")){
            jRadioButton2.setSelected(true);
        }else if(os.contains("nux")){
            jRadioButton1.setSelected(true);
        }else{
            JOptionPane.showMessageDialog(rootPane, "It seems your operating system is not supported or not detected\nHowever, you can choose manually if you are using Linux or Windows", "Ouch!", HEIGHT);
        }
        if(memorySize<2000){
            JOptionPane.showMessageDialog(rootPane, "It seems your system RAM is too small to be able to run servers", "Ouch!", HEIGHT);
            System.exit(0);
        }
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(((((int)memorySize*1/4)<768)?768:(int)memorySize*1/4), 768, memorySize-1000, 1)); //(initial value, min, max, step)
        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(((((int)memorySize*3/4)>(memorySize-1000)?(memorySize-1000):(int)memorySize*3/4)), 768, memorySize-1000, 1)); //(initial value, min, max, step)
        command = "java -Xmx"+(int)jSpinner2.getValue()+"M -Xms"+(int)jSpinner1.getValue()+"M -jar "+filename+" nogui";
        jTextField1.setText(command);
        try {
            path = java.net.URLDecoder.decode(Form.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6), StandardCharsets.UTF_8.name());
            if(jRadioButton2.isSelected()){
                path = path.replace("/", "\\");
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        data = new File("zerrium.txt");
        if(data.exists()){
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(data));
                String x;
                while((x = br.readLine())!=null){
                    dat.add(new File(x));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int i=0; i<dat.size(); i++){
                    jComboBox1.addItem(dat.get(i).getName());
                }
            }
        }else{
            try {
                data.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();

        jFileChooser1.setFileFilter(new MyCustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minecraft Server Starter by Zerrium");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Choose platform:");

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton1.setText("Linux");

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton2.setText("Windows");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Choose server jar file:");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Browse...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Enter minimum RAM size:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Enter maximum RAM size:");

        jSpinner1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jSpinner2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("MB");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("MB");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setText("Custom command:");

        jTextField1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Start!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jCheckBox2.setText("BungeeCord/Waterfall");
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(27, 27, 27))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(24, 24, 24))
        );

        jCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jTextField1.setVisible(jCheckBox1.isSelected());
                revalidate();
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFileChooser1.setCurrentDirectory(new File(path));
        jFileChooser1.showOpenDialog(null);
        File f = jFileChooser1.getSelectedFile();
        filename = f.getName();
        command = "java -Xmx"+(int)jSpinner2.getValue()+"M -Xms"+(int)jSpinner1.getValue()+"M -jar "+filename+" nogui";
        jTextField1.setText(command);
        dat.add(f);
        jComboBox1.addItem(filename);
        jComboBox1.setSelectedItem(filename);
        g = null;
        g = new File(f.getAbsolutePath().replace(f.getName(), ""));
        try{
            FileWriter f2 = new FileWriter(data, false);
            for(int i=0; i<dat.size(); i++){
                f2.write(dat.get(i).getAbsolutePath()+"\n");
            }
            f2.close();
        }catch(IOException e){
            e.printStackTrace();
        }     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!jRadioButton1.isSelected() && !jRadioButton2.isSelected()){
            JOptionPane.showMessageDialog(rootPane, "You have not chosen the platform!", "Hey!", HEIGHT);
        }else if(filename.equals("")){
            JOptionPane.showMessageDialog(rootPane, "You have not chosen the jar file!", "Hey!", HEIGHT);
        }else if(!filename.contains(".jar")){
            JOptionPane.showMessageDialog(rootPane, "Wrong file!", "Hey!", HEIGHT);
        }else if((int)jSpinner1.getValue()>(int)jSpinner2.getValue()){
            JOptionPane.showMessageDialog(rootPane, "Minimum RAM size cannot be larger than maximal RAM size!", "Hey!", HEIGHT);
        }else if(jCheckBox1.isSelected() && jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "You have not filled in the custom command!\nConfused? Just uncheck the custom command instead!", "Hey!", HEIGHT);
        }else if(jCheckBox1.isSelected() && (!jTextField1.getText().contains("-jar") || !jTextField1.getText().contains("java") || !jTextField1.getText().contains(".jar"))){
            JOptionPane.showMessageDialog(rootPane, "Invalid custom command!\nConfused? Just uncheck the custom command instead!", "Hey!", HEIGHT);
        }else{
            if(jCheckBox1.isSelected()){
                command = jTextField1.getText();
            }
            String x;
            ArrayList<String> y = new ArrayList<>();
            boolean error=false;
            try{
                File h = null;
                if(jRadioButton1.isSelected()){
                    h = new File(g+"/eula.txt");
                }else if(jRadioButton2.isSelected()){
                    h = new File(g+"\\eula.txt");
                }
                BufferedReader br = new BufferedReader(new FileReader(h));
                while((x = br.readLine())!=null){
                    y.add(x);
                }
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(rootPane, "eula.txt does not exist!\nMake sure to run the jar first and edit the eula.txt by changing eula=false to eula=true", "Error", HEIGHT);
                error = true;
            } catch (IOException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "An error has occured!\n"+ex, "Error", HEIGHT);
                error = true;
            }
            if(!error){
                for(String s:y){
                    if(s.contains("false")){
                        JOptionPane.showMessageDialog(rootPane, "Unable to run the server!\nCaused by: eula=false in eula.txt\nMake sure to edit eula.txt by changing eula=false to eula=true", "Error", HEIGHT);
                        error = true;
                        break;
                    }
                }
                if(!error){
                    y.clear();
                    ProcessBuilder processBuilder = new ProcessBuilder();
                    if(jRadioButton1.isSelected()){
                        try {
                            Runtime.getRuntime().exec("/usr/bin/x-terminal-emulator --disable-factory -e "+command, null, g);
                        } catch (IOException ex) {
                            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(rootPane, "An error has occured!\n"+ex, "Error", HEIGHT);
                        }
                        try {
                            Runtime.getRuntime().exec("/usr/bin/x-terminal-emulator --disable-factory -e "+"gnome-system-monitor", null, g);
                        } catch (IOException ex) {
                            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(rootPane, "An error has occured!\n"+ex, "Error", HEIGHT);
                        }
                    }else if(jRadioButton2.isSelected()){
                        try {
                            Runtime.getRuntime().exec("cmd /c start cmd.exe /K "+command, null, g);
                        } catch (IOException ex) {
                            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(rootPane, "An error has occured!\n"+ex, "Error", HEIGHT);
                        }
                        try {
                            Runtime.getRuntime().exec("cmd /c start cmd.exe /K "+"Taskmgr.exe", null, g);
                        } catch (IOException ex) {
                            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(rootPane, "An error has occured!\n"+ex, "Error", HEIGHT);
                        }
                    }
                    System.exit(0);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        command = "java -Xmx"+(int)jSpinner2.getValue()+"M -Xms"+(int)jSpinner1.getValue()+"M -jar "+filename+" nogui";
        jTextField1.setText(command);
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        command = "java -Xmx"+(int)jSpinner2.getValue()+"M -Xms"+(int)jSpinner1.getValue()+"M -jar "+filename+" nogui";
        jTextField1.setText(command);
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        filename=jComboBox1.getSelectedItem().toString();
        command = "java -Xmx"+(int)jSpinner2.getValue()+"M -Xms"+(int)jSpinner1.getValue()+"M -jar "+filename+" nogui";
        jTextField1.setText(command);
        g = null;
        g = new File(dat.get(jComboBox1.getSelectedIndex()).getAbsolutePath().replace(dat.get(jComboBox1.getSelectedIndex()).getName(), ""));
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
        if(jCheckBox2.isSelected()){
            jSpinner1.setModel(new javax.swing.SpinnerNumberModel(64, 32, memorySize-1000, 1)); //(initial value, min, max, step)
            jSpinner2.setModel(new javax.swing.SpinnerNumberModel(128, 48, memorySize-1000, 1)); //(initial value, min, max, step)
        }else{
            jSpinner1.setModel(new javax.swing.SpinnerNumberModel(((((int)memorySize*1/4)<768)?768:(int)memorySize*1/4), 768, memorySize-1000, 1)); //(initial value, min, max, step)
            jSpinner2.setModel(new javax.swing.SpinnerNumberModel(((((int)memorySize*3/4)>(memorySize-1000)?(memorySize-1000):(int)memorySize*3/4)), 768, memorySize-1000, 1)); //(initial value, min, max, step)
        }
    }//GEN-LAST:event_jCheckBox2StateChanged

    public static void main(String args[]) {
        try{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".jar" extension
            return file.isDirectory() || (file.getAbsolutePath().endsWith(".jar"));
        }
        @Override
        public String getDescription() {
            return "Jar file (*.jar)";
        }
    } 
}