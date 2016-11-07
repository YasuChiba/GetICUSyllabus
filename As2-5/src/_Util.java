import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by YasuhiraChiba on 2016/11/06.
 */
public class _Util {


    public void saveToFile(String s, String filename, String saveDirectory) {
        File file = new File(saveDirectory + "/" + filename);
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(file);
            filewriter.write(s);
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void CreateDialog(String s) {
        JPanel panelForEnd = new JPanel();
        JLabel label = new JLabel(s);
        label.setForeground(Color.RED);
        JOptionPane.showMessageDialog(panelForEnd, label);

    }


    public JComponent createView(String view, String text, int sizeX, int sizeY) {
        JComponent component;


        if (view == Constatnts.TextField) {
            component = new JTextField(text);
            component = viewSetting(component, sizeX, sizeY);
            return component;
        }
        if (view == Constatnts.PassField) {
            component = new JPasswordField(text);
            component = viewSetting(component, sizeX, sizeY);
            return component;
        }
        if (view == Constatnts.TextArea) {
            component = new JTextArea();
            component = viewSetting(component, sizeX, sizeY);
            return component;
        }
        if (view == Constatnts.Button) {
            component = new JButton(text);
            component = viewSetting(component, sizeX, sizeY);
            return component;
        }
        if (view == Constatnts.Label) {
            component = new JLabel(text);
            component = viewSetting(component, sizeX, sizeY);
            return component;
        } else {
            return null;
        }


    }

    private JComponent viewSetting(JComponent component, int sizeX, int sizeY) {
        LineBorder border = new LineBorder(Color.BLACK, 1, true);
        if (sizeX != -1 && sizeY != -1) {
            component.setPreferredSize(new Dimension(sizeX, sizeY));
            component.setMaximumSize(new Dimension(sizeX, sizeY));
        }
        component.setAlignmentX(0.5f);
        component.setBorder(border);
        return component;
    }


}
