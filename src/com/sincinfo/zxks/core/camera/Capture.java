package com.sincinfo.zxks.core.camera;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

public class Capture{
	private static String    preApplyCodex    ="" ;
	
    public void toPhoto(String preApplyCode) throws IOException{
    	preApplyCodex = preApplyCode;
        final Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        final JFrame window = new JFrame("摄像头");
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                webcam.close();
                window.dispose();
            }
        });
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton button = new JButton("拍照");
        window.add(panel, BorderLayout.CENTER);
        window.add(button, BorderLayout.SOUTH);
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间
                button.setEnabled(false);
                String fileName = "F:/tem/dev/simsun/PhotoImg/"+preApplyCodex;
                System.out.println("file=="+fileName);
                WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_JPG);
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run()
                    {
                        JOptionPane.showMessageDialog(null, "拍照成功!");
                        button.setEnabled(true);
                        return;
                    }
                });
            }
        });
    }
}