import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;


/**
 * Created by YasuhiraChiba on 2016/11/06.
 */


public class Main extends JFrame {
    HTTPControler controller;
    String saveDirectory = "";
    _Util util = new _Util();
    JTextField textID;
    JPasswordField textPass;
    JTextField textOutputFilename;
    JTextArea textLog;


    Main() {
        JPanel pannel = new JPanel();
        pannel.setLayout(new BoxLayout(pannel, BoxLayout.PAGE_AXIS));

        JButton button = (JButton) util.createView(Constatnts.Button, "シラバス取得", 150, 100);
        button.addActionListener(new ButtonAction1());

        textID = (JTextField) util.createView(Constatnts.TextField, "", 150, 20);
        textPass = (JPasswordField) util.createView(Constatnts.PassField, "", 150, 20);
        textOutputFilename = (JTextField) util.createView(Constatnts.TextField, "", 150, 20);

        textLog = (JTextArea) util.createView(Constatnts.TextArea, "", -1, -1);
        textLog.setEditable(false);

        JScrollPane scrollTextLog = new JScrollPane(textLog);
        scrollTextLog.setAlignmentX(0.5f);
        scrollTextLog.setSize(150, 300);
        scrollTextLog.createVerticalScrollBar();
        scrollTextLog.createHorizontalScrollBar();


        JLabel labelID = (JLabel) util.createView(Constatnts.Label, "ID", 150, 20);
        JLabel labelPass = (JLabel) util.createView(Constatnts.Label, "Pass", 150, 20);
        JLabel labelOutPutFileName = (JLabel) util.createView(Constatnts.Label, "Output File Name", 150, 20);

        pannel.add(button);
        pannel.add(Box.createRigidArea(new Dimension(10, 10)));
        pannel.add(labelID);
        pannel.add(textID);
        pannel.add(labelPass);
        pannel.add(textPass);
        pannel.add(labelOutPutFileName);
        pannel.add(textOutputFilename);
        pannel.add(Box.createRigidArea(new Dimension(10, 10)));
        pannel.add(scrollTextLog);


        getContentPane().add(pannel);


    }


    public static void main(String[] args) {
        Main frame = new Main();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 500, 500);
        frame.setTitle("");
        frame.setVisible(true);
    }


    public void PrintLog(String s) {
        System.out.println(s);
        textLog.append("\n" + s);
    }

    class ButtonAction1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            controller = new HTTPControler(Main.this);
            JFileChooser filechooser = new JFileChooser();
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            PrintLog("Start Download");
            JPanel panelForDialog = new JPanel();
            panelForDialog.setVisible(true);
            int selected = filechooser.showOpenDialog(panelForDialog);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                saveDirectory = file.getAbsolutePath();
                PrintLog("===========saveDirectory===========");
                PrintLog(saveDirectory);

                int loginSuccess = controller.Login(textID.getText(), textPass.getText());
                if (loginSuccess == -1) {
                    util.CreateDialog("Login error");
                    controller = null;

                } else {
                    int lastPageNum = controller.initForGetSyllabus(saveDirectory);
                    for (int i = 2; i <= lastPageNum; i++) {
                        controller.getSyllabus(i);
                    }

                    HtmlAnyalise anyalise = new HtmlAnyalise(saveDirectory, "Out" + textOutputFilename.getText() + ".txt");
                    try {
                        for (int i = 1; i <= lastPageNum; i++) {
                            PrintLog("Anyalise page" + String.valueOf(i));
                            anyalise.Anyalise("filePageNUM=" + i + ".html");
                        }
                    } catch (NullPointerException nulE) {
                        PrintLog("ぬるぽ at Anyalisis   多分分析終わり");
                    }
                    anyalise.endOfProcess();

                    util.CreateDialog("完了");
                    controller = null;

                }

            }
        }
    }
}



