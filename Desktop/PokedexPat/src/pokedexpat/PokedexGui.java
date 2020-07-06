
package pokedexpat;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author SeaBassCarstens
 */

public class PokedexGui extends javax.swing.JFrame {
    private Vector poked = new Vector();
    private Vector dates = new Vector();
    private Vector original = new Vector();
  public boolean sorted = false;
  public boolean Add = false;
  public boolean Edit = false;
        BufferedImage[] images;

        /*for (int i = 0; i < original.size(); i++) {
                Pokedex selectPokemon = (Pokedex)original.get(i);
                listModel.addElement(selectPokemon.getName());
            }
            */

        
        
        public void End() {
        //prints out to a file to be saved in specific order so that it can be read again
        try {
            PrintWriter fout = new PrintWriter("Pokemon.txt");
            for (int i = 0; i < poked.size(); i++) {
                Pokedex selectPokemon = (Pokedex)poked.get(i);
                if (i == 0) {
                    fout.print(selectPokemon.getName());
                    fout.print(",");
                    fout.print(selectPokemon.getType());
                fout.print(",");
                fout.print(selectPokemon.getWeight());
                fout.print(",");
                fout.print(selectPokemon.getHeight());
                fout.print(",");
                fout.print(selectPokemon.getGender());
                fout.print(",");
                fout.print(selectPokemon.getCatchrate());
                fout.print(",");
                fout.print(selectPokemon.isFinalevo());
                fout.print(",");
                fout.print(selectPokemon.getPokenum());
                }
                else {
                                        fout.print(",");
                    fout.print(selectPokemon.getName());
                                        fout.print(",");
                    fout.print(selectPokemon.getType());
                fout.print(",");
                fout.print(selectPokemon.getWeight());
                fout.print(",");
                fout.print(selectPokemon.getHeight());
                fout.print(",");
                fout.print(selectPokemon.getGender());
                fout.print(",");
                fout.print(selectPokemon.getCatchrate());
                fout.print(",");
                fout.print(selectPokemon.isFinalevo());
                fout.print(",");
                fout.print(selectPokemon.getPokenum());
                }

                


               }
            fout.close(); 
            
        } catch(Exception e1) {
            e1.printStackTrace();
            System.out.println("Error during reading/writing");
        }
        
    }
        
    public void sort() {
        //Main sorting method called in the action of the button
        double[] sorts = new double[original.size()];
        for (int i = 0; i < original.size(); i++) {
            Pokedex selectPokemon = (Pokedex)original.get(i);
            sorts[i] = selectPokemon.getWeight();
        }
        doSelectionSort(sorts, 0);
        ltsPoke.updateUI();
    }
    
    public void doSelectionSort(double[] arr, int startP) {
        //Part of the code for the sorting
       for (int i = startP; i < arr.length; i++) {
           findSmallestAndSwap(arr , i);
       }
       
       
       
    }
    

   public void findSmallestAndSwap(double[] arr, int startP) { 
       //Part of the code for the sorting
        double low = arr[startP];
        int pos = startP;
       for (int i = startP; i < arr.length; i++) {
               if (arr[i]<low) {
               low = arr[i];
               pos = i;
           } 
             
       }
       arr[pos] = arr[startP];
       arr[startP] = low;
       
       Pokedex small = (Pokedex)original.get(pos);
       Pokedex init = (Pokedex)original.get(startP);
       original.set(startP, small);
       original.set(pos, init);
       
       
       
    }
  
 
    

//sets all buttons to be disabled
    public void DisableAll() {
        txfName.setEnabled(false);
        txfType.setEnabled(false);
        txfGender.setEnabled(false);
        cbFinalEvo.setEnabled(false);
        spCatchrate.setEnabled(false);
        spHeight.setEnabled(false);
        spWeight.setEnabled(false);
        btnAdd.setEnabled(false);
        btnCancel.setEnabled(false);
        btnConfirm.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisplay.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSearch.setEnabled(false);
        btnSort.setEnabled(false);
        
    }
    //sets all buttons to be enabled
    public void EnableAll() {
        txfName.setEnabled(true);
        txfType.setEnabled(true);
        txfGender.setEnabled(true);
        cbFinalEvo.setEnabled(true);
        spCatchrate.setEnabled(true);
        spHeight.setEnabled(true);
        spWeight.setEnabled(true);
        btnCancel.setEnabled(true);
        btnConfirm.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnDisplay.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSearch.setEnabled(true);
        btnSort.setEnabled(true);
    }
     
        
public void loadDates() throws Exception {
    //Made a custom date class to assign dates to all the different things inside my vector
    BufferedReader br = new BufferedReader(new FileReader("date.txt"));

        int count = 0;
        
        while(br.readLine()!=null)
        {
         count++;  
        }
        
        
        String[] arr = new String[count];
        
        
        
        br = new BufferedReader(new FileReader("date.txt"));
        
        for (int i = 0; i < count; i++) 
        {
          String line = br.readLine();
          arr = line.split("-");
 
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        int num = 0;
        for (int i = 0; i < arr.length; i=i+3 ) {
            
            String line0 = arr[i];
            int yyyy = Integer.parseInt(line0);
            String line1 = arr[i+1];
            int dd = Integer.parseInt(line1);
            String line2 = arr[i+2];
            int mm = Integer.parseInt(line2);
            
            Dates um = new Dates(mm, dd, yyyy);
            dates.add(um);

    } 
}
        
public void loadImages() throws Exception{
    //The method for my advanced function, it creates an array of images in the same location as the ones inside the vector and then matches them up
int max = 103;
int min = 99;

Random rand = new Random();
        


int n = rand.nextInt((max - min) + 1) + min;
Pokedex selectPokemon = (Pokedex)poked.get(ltsPoke.getSelectedIndex());
String code = selectPokemon.getPokenum();
String pos = "";
    for (int i = 1; i < 4; i++) {
        pos = pos + code.charAt(i);
    }
    int pes = Integer.parseInt(pos);
    pes = pes -1;
        int y = poked.size();
        String prefix = "images/";
        String[] ids = new String[104];
        String ext = ".png";
        for (int z = 0; z < 104; z++) {
            ids[z] = z+1+"";
        }

        BufferedImage[] images = new BufferedImage[ids.length];
        for(int i = 0; i < images.length; i++) {
            String path = prefix + ids[i] + ext;
            images[i] = ImageIO.read(new File(path));
        }
        
        if (ltsPoke.getSelectedIndex()>99) {
        
        ImageIcon icon = new ImageIcon(images[n]);
                JOptionPane.showMessageDialog(
                        null,
                        selectPokemon.getName(),
                        selectPokemon.getPokenum(), JOptionPane.INFORMATION_MESSAGE,
                       icon);
    }else {
            
            ImageIcon icon = new ImageIcon(images[pes]);
                JOptionPane.showMessageDialog(
                        null,
                        selectPokemon.getName(),
                        selectPokemon.getPokenum(), JOptionPane.INFORMATION_MESSAGE,
                       icon);
        }
        
        
        
}


    /**
     * Creates new form PokedexGui
     */
    public PokedexGui() throws Exception {
        try {
            loadDates();
            loadVectorFromFile(); 
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error occured");
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        initComponents();
        DisableAll();
        txfDate.setEnabled(false);
        txfPokenum.setEnabled(false);
        btnSearch.setEnabled(true);
        btnSort.setEnabled(true);
        btnAdd.setEnabled(true);
        
    }
   
    public void loadVectorFromFile() throws Exception {
        //loads my vector from the file
        BufferedReader br = new BufferedReader(new FileReader("Pokemon.txt"));
        
        int count = 0;
        
        while(br.readLine()!=null)
        {
         count++;  
        }
        
        
        String[] arr = new String[count];
        
        
        
        br = new BufferedReader(new FileReader("Pokemon.txt"));
        
        for (int i = 0; i < count; i++) 
        {
          String line = br.readLine();
          arr = line.split(",");
 
        }
        
        for (int i = 0; i < arr.length; i=i+8 ) {
            
            String name = arr[i];
            String type = arr[i+1];
            String line3 = arr[i+2];
            double weight = Double.parseDouble(line3);
            String line4 = arr[i+3];
            double height = Double.parseDouble(line4);
            String gender = arr[i+4];
            String line5 = arr[i+5];
            int catchrate = Integer.parseInt(line5);
            String line6 = arr[i+6];
            boolean finalevo = Boolean.parseBoolean(line6);
            String line7 = arr[i+7];
            String pokenum = line7;
            Pokedex seb = new Pokedex(name, type, weight, height, gender, catchrate, finalevo, pokenum);
            poked.add(seb);
            original.add(seb);
            
            
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblGender = new javax.swing.JLabel();
        lblCatchrate = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        txfName = new javax.swing.JTextField();
        txfType = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txfGender = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblWeight = new javax.swing.JLabel();
        lblHeight = new javax.swing.JLabel();
        cbFinalEvo = new javax.swing.JComboBox<>();
        lblFinalevo = new javax.swing.JLabel();
        txfPokenum = new javax.swing.JTextField();
        pic = new javax.swing.JLabel();
        btnSort = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        btnDisplay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ltsPoke = new javax.swing.JList(poked);
        spCatchrate = new javax.swing.JSpinner();
        spWeight = new javax.swing.JSpinner();
        spHeight = new javax.swing.JSpinner();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txfDate = new javax.swing.JTextField();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jLabel1.setText("jLabel1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(206, 7, 47));

        lblGender.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblGender.setText("Gender:");

        lblCatchrate.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblCatchrate.setText("CatchRate:");

        lblType.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblType.setText("Type:");

        txfName.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        txfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfNameActionPerformed(evt);
            }
        });

        txfType.setFont(new java.awt.Font("Silom", 0, 11)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txfGender.setFont(new java.awt.Font("Silom", 0, 11)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblWeight.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblWeight.setText("Weight:");

        lblHeight.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblHeight.setText("Height:");

        cbFinalEvo.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        cbFinalEvo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "False", "True" }));

        lblFinalevo.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblFinalevo.setText("Final Evo:");

        txfPokenum.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N

        btnSort.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnSort.setText("Sort");
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        lblDate.setFont(new java.awt.Font("Silom", 1, 13)); // NOI18N
        lblDate.setText("Date");

        btnDisplay.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnDisplay.setText("Display");
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });

        ltsPoke.setFont(new java.awt.Font("Silom", 0, 12)); // NOI18N
        ltsPoke.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ltsPokeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ltsPoke);

        spCatchrate.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        spCatchrate.setModel(new javax.swing.SpinnerNumberModel(0, null, null, 45));

        spWeight.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        spWeight.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));

        spHeight.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        spHeight.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));

        btnConfirm.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Silom", 0, 13)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblFinalevo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbFinalEvo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHeight)
                            .addGap(38, 38, 38)
                            .addComponent(spHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCatchrate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGender))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(spCatchrate)
                                .addComponent(txfGender, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblWeight)
                                .addComponent(lblType))
                            .addGap(34, 34, 34)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txfType)
                                .addComponent(spWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addGap(18, 18, 18)
                        .addComponent(txfDate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(pic)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txfName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfPokenum, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblType)
                            .addComponent(txfType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblWeight)
                            .addComponent(spWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHeight)
                            .addComponent(spHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCatchrate)
                            .addComponent(spCatchrate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGender)
                            .addComponent(txfGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFinalEvo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFinalevo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate)
                            .addComponent(txfName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfPokenum, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(pic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addGap(3, 3, 3)
                                .addComponent(btnDisplay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConfirm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel))
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txfNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfNameActionPerformed
        
    }//GEN-LAST:event_txfNameActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        boolean found = false;
        int pos = 0;
        String ans = JOptionPane.showInputDialog("Search by Name");
        for (int i = 0; i < poked.size(); i++) {
            //Runs through vector searching for the specifc name that is wanting to be found
            Pokedex selectPokemon = (Pokedex)poked.get(i);
            String uname = selectPokemon.getName();
            if (uname.equals(ans)) {
                //if the name is found
                pos = i;
                found = true;
            }
            
        }
        if (found == false) {
            //if the name is not found
            JOptionPane.showMessageDialog(this, "Not Found!");
        }
        else {
            ltsPoke.setSelectedIndex(pos);
            ltsPoke.updateUI();
            System.out.println("Found!");
            System.out.println(pos);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //Code for the action of the delete button
        poked.remove(poked.get(ltsPoke.getSelectedIndex()));
        ltsPoke.updateUI();
        End();
        original = poked;
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //Code for the action of the button "Add"
        EnableAll();
        if (ltsPoke.getSelectedIndex()>-1) {
            //if something is selected clear everything so it can be added
            txfName.setText("");
            txfType.setText("");
            spWeight.setValue(0.0);
            spHeight.setValue(0.0);
            spCatchrate.setValue(0);
            txfGender.setText("");
            cbFinalEvo.setSelectedIndex(0);
            txfPokenum.setText("");
            

        }

        ltsPoke.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisplay.setEnabled(false);
        btnEdit.setEnabled(false);
        btnAdd.setEnabled(false);
        btnSearch.setEnabled(false);
        btnSort.setEnabled(false);
        
        Add = true;
        End();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //Code for the button "Edit"
        EnableAll();
        ltsPoke.setEnabled(false);
        btnDelete.setEnabled(false);
        btnDisplay.setEnabled(false);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSearch.setEnabled(false);
        btnSort.setEnabled(false);
        Edit = true;
        End();
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        
     
        
        int response = JOptionPane.showConfirmDialog(this, "Sort By Weight?", "Confirm Sort" , JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            sort();
            String list = "";
            for (int i = 0; i < original.size(); i++) {
                //loads all the names inside this string in the order of weight
                Pokedex selectPokemon = (Pokedex)original.get(i);
                list = list +  ( i + 1 + " : " + selectPokemon.getName() + "\n");
            }
            //Prints out custom scroll panel for the list
     JTextArea textArea = new JTextArea(list);
JScrollPane scrollPane = new JScrollPane(textArea);  
textArea.setLineWrap(true);  
textArea.setWrapStyleWord(true); 
scrollPane.setPreferredSize( new Dimension( 200, 200 ) );
JOptionPane.showMessageDialog(this, scrollPane, "Sorted By Name",  
                                       JOptionPane.YES_NO_OPTION);
            
        }
        else {
            //Nothing
        }
        


     
     

    }//GEN-LAST:event_btnSortActionPerformed

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
 
        try {
            //Calls method to load the array and display the image
            loadImages();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error occured");
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void ltsPokeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ltsPokeValueChanged
        // TODO add your handling code here:
       if (ltsPoke.getSelectedIndex() == -1) {
           //what happens if nothing is selected
            txfName.setText("");
            txfType.setText("");
            spWeight.setValue(0.0);
            spHeight.setValue(0.0);
            spCatchrate.setValue(0);
            txfGender.setText("");
            cbFinalEvo.setSelectedIndex(0);
            txfPokenum.setText("");
            txfDate.setText("");
            btnEdit.setEnabled(false);
                    btnDelete.setEnabled(false);
                            btnDisplay.setEnabled(false);

        }
        else {
           //what happens if something is selected
            int x = 1;
            Pokedex selectPokemon = (Pokedex)poked.get(ltsPoke.getSelectedIndex());
            if (selectPokemon.isFinalevo() == false) {
               x = 1;
           }
            else{
                x = 0;
            }
            txfName.setText(selectPokemon.getName());
            txfType.setText(selectPokemon.getType());
            spWeight.setValue(selectPokemon.getWeight());
            spHeight.setValue(selectPokemon.getHeight());
            spCatchrate.setValue(selectPokemon.getCatchrate());
            txfGender.setText(selectPokemon.getGender());
            cbFinalEvo.setSelectedIndex(x);
            txfPokenum.setText(selectPokemon.getPokenum()+"");
            txfDate.setText(dates.get(ltsPoke.getSelectedIndex())+"");
                        btnEdit.setEnabled(true);
                                btnDelete.setEnabled(true);
                                        btnDisplay.setEnabled(true);
        }
    }//GEN-LAST:event_ltsPokeValueChanged

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        DisableAll();
        if (Add == true) {
            //if add was clicked this will then happen
            int size = poked.size()+1;
            boolean yes = false;
            int x = cbFinalEvo.getSelectedIndex();
            if (x == 1) {
                yes = true;
            }
            else {
                yes = false;
            }
            Pokedex adding = new Pokedex();
            adding.setName(txfName.getText());
            adding.setType(txfType.getText());
            adding.setWeight((double)spWeight.getValue());
            adding.setHeight((double)spHeight.getValue());
            adding.setCatchrate((Integer)spCatchrate.getValue());
            adding.setGender(txfGender.getText());
            adding.setFinalevo(yes);
            adding.setPokenum(size+"");
            
            
            poked.add(adding);
            ltsPoke.updateUI();
            Add = false;
            
            ltsPoke.setEnabled(true);
            btnAdd.setEnabled(true);
            btnDelete.setEnabled(true);
            btnDisplay.setEnabled(true);
            btnEdit.setEnabled(true);
            btnSearch.setEnabled(true);
            btnSort.setEnabled(true);
            End();
            original = poked;
        }
        if (Edit == true) {
            //if edit was clicked, this will then happen
            boolean yes = false;
            int x = cbFinalEvo.getSelectedIndex();
            Pokedex selectPokemon = (Pokedex)poked.get(ltsPoke.getSelectedIndex());
            if (x == 1) {
                yes = true;
            }
            else {
                yes = false;
            }
            selectPokemon.setName(txfName.getText());
            selectPokemon.setType(txfType.getText());
            selectPokemon.setWeight((double)spWeight.getValue());
            selectPokemon.setHeight((double)spHeight.getValue());
            selectPokemon.setCatchrate((Integer)spCatchrate.getValue());
            selectPokemon.setGender(txfGender.getText());
            selectPokemon.setFinalevo(yes);
            selectPokemon.setPokenum(selectPokemon.getPokenum()+"");
            ltsPoke.updateUI();
            Edit = false;
            
            ltsPoke.setEnabled(true);
            btnAdd.setEnabled(true);
            btnDelete.setEnabled(true);
            btnDisplay.setEnabled(true);
            btnEdit.setEnabled(true);
            btnSearch.setEnabled(true);
            btnSort.setEnabled(true);
            End();
            original = poked;
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        //Action code for when cancel is clicked
        DisableAll();
        ltsPoke.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        btnDisplay.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSearch.setEnabled(true);
        btnSort.setEnabled(true);
    }//GEN-LAST:event_btnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(PokedexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PokedexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PokedexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PokedexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PokedexGui().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(PokedexGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSort;
    private javax.swing.JComboBox<String> cbFinalEvo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCatchrate;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFinalevo;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblWeight;
    private javax.swing.JList<String> ltsPoke;
    private javax.swing.JLabel pic;
    private javax.swing.JSpinner spCatchrate;
    private javax.swing.JSpinner spHeight;
    private javax.swing.JSpinner spWeight;
    private javax.swing.JTextField txfDate;
    private javax.swing.JTextField txfGender;
    private javax.swing.JTextField txfName;
    private javax.swing.JTextField txfPokenum;
    private javax.swing.JTextField txfType;
    // End of variables declaration//GEN-END:variables
}
