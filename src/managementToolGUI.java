import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class managementToolGUI extends JFrame {
    private JPanel mainPanel;
    private JTable table1;
    private JRadioButton laatAllesZienRadioButton;
    private JRadioButton PEPUnresolvedRadioButton;
    private JRadioButton MandatoryActionsRadioButton;
    private JRadioButton sanctionsUnresolvedRadioButton;
    private JRadioButton REUnresolvedRadioButton;
    private JRadioButton LEUnresolvedRadioButton;
    private JRadioButton OBUnresolvedRadioButton;
    private JRadioButton WCUnresolvedRadioButton;
    private JButton openButton;
    private String currentFile = "";

    public managementToolGUI(String title) throws IOException, CsvException {
        super (title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        ArrayList<ArrayList> myList = new ArrayList();

        JTable table1 = new JTable();

        table1.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table1.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.setAutoCreateRowSorter(true);
        add(scrollPane);

        MandatoryActionsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(12)));
                table1.repaint();
            }
        });
        laatAllesZienRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList> myList = new ArrayList();
                try {
                    myList = Parser.getAllData(currentFile);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (CsvException csvException) {
                    csvException.printStackTrace();
                }
                table1.setModel(new ListTableModel(myList));
                table1.repaint();
            }
        });
        sanctionsUnresolvedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(14)));
                table1.repaint();
            }
        });
        REUnresolvedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(15)));
                table1.repaint();
            }
        });
        LEUnresolvedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(16)));
                table1.repaint();
            }
        });
        PEPUnresolvedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(17)));
                table1.repaint();
            }
        });
        OBUnresolvedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(18)));
                table1.repaint();
            }
        });
        WCUnresolvedRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(new ListTableModel(updateData(19)));
                table1.repaint();
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean incorrectFile = true;
                String file = FileSelector.chooseFile();
                //Test of er een file gekozen is
                if(file != null){
                    //Test of file langer dan 3 characters is
                    if (file.length() > 3) {
                        String extension = file.substring(file.length() - 3);
                        //Test of bestand een .csv bestand is
                        if (extension.equals("csv")) {
                            int rows = 0;
                            try {
                                rows = Parser.getRows(file);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            } catch (CsvException csvException) {
                                csvException.printStackTrace();
                            }
                            if(rows == 33) {
                                changeCurrentFile(file);
                                laatAllesZienRadioButton.doClick();
                                updateRadios();
                                incorrectFile = false;
                            }
                        }
                    }
                }
                if (incorrectFile == true){
                    JOptionPane.showMessageDialog(null, "Bestand is niet geldig", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
    }

    public void updateRadios(){
        MandatoryActionsRadioButton.setText("Mandatory Actions ("+ (updateData(12).size()-1)+")");
        sanctionsUnresolvedRadioButton.setText("Sanctions Unresolved ("+ (updateData(14).size()-1)+")");
        REUnresolvedRadioButton.setText("RE Unresolved ("+ (updateData(15).size()-1)+")");
        LEUnresolvedRadioButton.setText("LE Unresolved ("+ (updateData(16).size()-1)+")");
        PEPUnresolvedRadioButton.setText("PEP Unresolved ("+ (updateData(17).size()-1)+")");
        OBUnresolvedRadioButton.setText("OB Unresolved ("+ (updateData(18).size()-1)+")");
        WCUnresolvedRadioButton.setText("WC Unresolved ("+ (updateData(19).size()-1)+")");
    }

    public ArrayList<ArrayList> updateData(int x){
        ArrayList<ArrayList> myList = new ArrayList();
        try {
            myList = Parser.getSpecificData(x, currentFile);;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (CsvException csvException) {
            csvException.printStackTrace();
        }
        return myList;
    }

    public void changeCurrentFile(String newFile){
        currentFile = newFile;
    }

    public Boolean checkIfValidFile(){
        return true;
    }

    public static void main(String[] args) throws IOException, CsvException {
        JFrame frame = new managementToolGUI("Management Tool");
        frame.setSize(1500,800);
        frame.setVisible(true);
    }
}
