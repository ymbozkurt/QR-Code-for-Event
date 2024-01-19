package com.kingaspx.main;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import java.io.FileWriter;
import org.json.JSONTokener;

public class Menu extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private static String etkinlikAdi = "";

    public Menu() {
        initComponents();
        initWebcam();
        Action closeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ESCAPE"), "closeAction");
        getRootPane().getActionMap().put("closeAction", closeAction);
    }

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Border myBorder = BorderFactory.createLineBorder(Color.BLACK);
        jPanel1 = new javax.swing.JPanel();
        header_field = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        webcamjPanel = new javax.swing.JPanel();
        jPanelLeft = new javax.swing.JPanel();
        jPanelRight = new javax.swing.JPanel();
        welcome_Area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout());
        jPanel1.setBackground(new java.awt.Color(240, 237, 223));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new GridLayout(1, 2));
        jPanel1.setBorder(myBorder);
        jPanelLeft.setBackground(getForeground());
        jPanelRight.setBackground(getForeground());
        jSeparator1.setForeground(new java.awt.Color(126, 167, 206));
        webcamjPanel.setBackground(getForeground());
        webcamjPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.ORANGE));
        jPanel1.add(jPanelLeft);
        jPanel1.add(jPanelRight);
        jPanelLeft.setBorder(BorderFactory.createLineBorder(Color.PINK));
        jPanelRight.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        jPanelLeft.setLayout(new GridBagLayout());
        jPanelRight.setLayout(new GridLayout());

        header_field.setEditable(false);
        header_field.setBackground(getForeground());
        header_field.setFont(new Font("Arial", Font.BOLD, 85));
        header_field.setText(etkinlikAdi);
        header_field.setHorizontalAlignment(JTextField.CENTER);

        GridBagConstraints headerGbc = new GridBagConstraints();
        headerGbc.gridx = 0;
        headerGbc.gridy = 0;
        headerGbc.fill = GridBagConstraints.BOTH;
        headerGbc.weighty = 0.3;
        jPanelLeft.add(header_field, headerGbc);

        GridBagConstraints webcamGbc = new GridBagConstraints();
        webcamGbc.gridx = 0;
        webcamGbc.gridy = 1;
        webcamGbc.fill = GridBagConstraints.BOTH;
        webcamGbc.weighty = 0.7;
        webcamGbc.weightx = 1;
        jPanelLeft.add(webcamjPanel, webcamGbc);
        webcamjPanel.setLayout(new GridLayout());

        jPanelRight.add(welcome_Area, BorderLayout.CENTER);
        welcome_Area.setEditable(false);
        welcome_Area.setBackground(getForeground());
        welcome_Area.setBorder(null);
        welcome_Area.setFont(new Font("Arial", Font.BOLD, 55));
        welcome_Area.setLineWrap(true);
        welcome_Area.setWrapStyleWord(true);
        welcome_Area.setText("\n\n\nWELCOME\n\n\n\n\n\n\n\nNow you can scan your QR code.");

        
        add(jPanel1);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new GridLayout(1, 2));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    static String etkinlikSayıDegeri;

    public static void runQRReader(String s) {
        etkinlikAdi = s;

        switch (etkinlikAdi) {
            case "Act 1":
                etkinlikSayıDegeri = "1";
                break;
            case "Act 2":
                etkinlikSayıDegeri = "2";
                break;
            case "Act 3":
                etkinlikSayıDegeri = "3";
                break;
            case "Act 4":
                etkinlikSayıDegeri = "4";
                break;
            case "Act 5":
                etkinlikSayıDegeri = "5";
                break;
            case "Act 6":
                etkinlikSayıDegeri = "6";
                break;
            case "Act 7":
                etkinlikSayıDegeri = "7";
                break;
            case "Act 8":
                etkinlikSayıDegeri = "8";
                break;
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel webcamjPanel;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea welcome_Area;
    private javax.swing.JTextField header_field;
    // End of variables declaration//GEN-END:variables

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); // 0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        webcamjPanel.add(panel, new GridLayout());

        executor.execute(this);
    }

    

    @Override
    public void run() {
        boolean camOpen = true;
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                // No result...
            }

            if (result != null && camOpen == true) {

                

                String[] qrParts = result.getText().split("-");
                if (qrParts.length == 3 && qrParts[0] instanceof String && qrParts[1] instanceof String && qrParts[2] instanceof String ) {
                    try {
                        String jsonString = new String(Files.readAllBytes(Paths.get(
                                "Java\\QR-Code-em-Java-master\\QR-Code-em-Java-master\\src\\com\\kingaspx\\main\\output.json")));

                        JSONTokener tokener = new JSONTokener(jsonString);
                        JSONArray jsonArray = new JSONArray(tokener);
                        String qrJsonString = "{\"id\": \"" + qrParts[0] + "\"," + " \"name\": \"" + qrParts[1]
                                + "\", \"surname\": \"" + qrParts[2]
                                + "\", \"1\":false,\"2\":false,\"3\":false,\"4\":false,\"5\":false,\"6\":false,\"7\":false,\"8\":false}";

                        JSONObject qrJsonObject = new JSONObject(qrJsonString);

                        JSONObject[] jsonObjectArray = new JSONObject[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                jsonObjectArray[i] = jsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        for (int i = 0; i < jsonObjectArray.length; i++) {
                            if (qrParts[0].equals(jsonObjectArray[i].getString("id"))) {
                                jsonArray.getJSONObject(i).put(etkinlikSayıDegeri, true);
                                break;
                            } else if (i == jsonObjectArray.length - 1) {
                                qrJsonObject.put(etkinlikSayıDegeri, true);
                                jsonArray.put(qrJsonObject);
                            }
                        }

                        

                        try {
                            FileWriter file = new FileWriter(
                                    "Java\\QR-Code-em-Java-master\\QR-Code-em-Java-master\\src\\com\\kingaspx\\main\\output.json");
                            file.write(jsonArray.toString());
                            file.close();

                        } catch (Exception e) {

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    welcome_Area.setText("\n\n\n\nWelcome " + result.getText()
                            + ".\nGood to see you here.\n\n\n\n\n\n\n\n\n PLEASE WAIT");
                    welcome_Area.setBackground(new java.awt.Color(0, 255, 0));
                    camOpen = false;
                    try {
                        Thread.sleep(1000);
                        welcome_Area.setText("\n\n\n\nWELCOME\n\n\n\n\n\n\n\nNow you can scan your QR code.");
                        welcome_Area.setBackground(new java.awt.Color(240, 237, 223));
                        camOpen = true;
                    } catch (Exception e) {
                    }
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
