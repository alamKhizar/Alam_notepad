import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class Main extends JFrame implements ActionListener {
    JMenuItem newDocument, open, save, Print, Exit, Copy, Paste, Cut, help;
    JTextArea area;
    JScrollPane scrollPane;

    Main() {
        //Set title
        setTitle("Alam Notepad");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //setting icon of the panel
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image icon = imageIcon.getImage();
        setIconImage(icon);

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("FILE");
        file.setFont(new Font("AERIAL", Font.PLAIN, 15));

        newDocument = new JMenuItem("New");
        newDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newDocument.addActionListener(this);

        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        Print = new JMenuItem("Print");
        Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        Exit = new JMenuItem("Exit");
        Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        Exit.addActionListener(this);

        file.add(newDocument);
        file.add(open);
        file.add(save);
        file.add(Print);
        file.add(Exit);


        JMenu edit = new JMenu("EDIT");
        edit.setFont(new Font("AERIAL", Font.PLAIN, 15));


        Copy = new JMenuItem("Copy");
        Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        Copy.addActionListener(this);

        Paste = new JMenuItem("Paste");
        Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        Paste.addActionListener(this);

        Cut = new JMenuItem("Cut");
        Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        Cut.addActionListener(this);

        edit.add(Copy);
        edit.add(Paste);
        edit.add(Cut);


        JMenu Help = new JMenu("HELP");
        Help.setFont(new Font("AERIAL", Font.PLAIN, 15));


        help = new JMenuItem("Help");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        help.addActionListener(this);
        Help.add(help);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(Help);

        menuBar.setBackground(new Color(255, 255, 255));

        setJMenuBar(menuBar);


        area = new JTextArea();
        area.setFont(new Font("AERIAL", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        //add(area);

        setVisible(true);
    }

    public static void main(String[] args) {

        new Main();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newDocument) {
            area.setText("");
        } else if (e.getSource() == open) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            fileChooser.addChoosableFileFilter(restrict);
            int action = fileChooser.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                File files = fileChooser.getSelectedFile();

                try{
                    BufferedReader br = new BufferedReader(new FileReader(files));
                    try {
                        area.read(br,null);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == save) {
            JFileChooser saves = new JFileChooser();
            saves.setApproveButtonText("SAVE");

            int action = saves.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                File files = new File(saves .getSelectedFile()+ ".txt");

                BufferedWriter bW = null;

                try{
                    bW = new BufferedWriter(new FileWriter(files));
                    area.write(bW);

                }catch(Exception E){

                }
            }
        } else if (e.getSource() == Print) {
            try{

            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}