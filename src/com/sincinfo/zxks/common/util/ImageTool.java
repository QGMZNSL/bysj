package com.sincinfo.zxks.common.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 用于格式化image的工具类
 * 
 * @author liaodc
 * 
 */
public class ImageTool {
    private static int width;

    private static int height;

    private static int scaleWidth;

    private static double support = (double) 3.0;

    private static double PI = (double) 3.14159265358978;

    private static double[] contrib;

    private static double[] normContrib;

    private static double[] tmpContrib;

    private static int nDots;

    private static int nHalfDots;

    private static byte[] inputStream;

    /**
     * 
     * 格式化image<br>
     * 支持格式有：JPG, JPEG, JPE, JFIF, BMP, DIB, PNG, GIF,
     * 
     * @param srcFile
     *            要处理的image文件
     * @param format
     *            image格式
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标文件长高比例中的长比例
     * @param imgHeight
     *            目标文件长高比例中的高比例
     * @return 成功：目标文件路径 失败：空字符串
     */
    public static String dealImage(File srcFile, String format,
            String destFilePath, int imgWidth, int imgHeight) {
        if (format != null || !"".equals(format)) {
            if (format.equalsIgnoreCase(".gif")) {
                dealGifImage(srcFile, destFilePath, imgWidth, imgHeight);
            } else {
                dealOtherImage(srcFile, destFilePath, imgWidth, imgHeight);
            }
            return destFilePath;
        }

        return "";
    }

    /**
     * 生成缩略图，并格式化 <br>
     * 支持格式有：JPG, JPEG, JPE, JFIF, BMP, DIB, PNG, GIF
     * 
     * @param srcFile
     *            要处理的image文件
     * @param format
     *            image格式
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标image宽
     * @param imgHeight
     *            目标image高
     * @return 成功：目标文件路径 失败：空字符串
     */
    public static String createMiniImage(File srcFile, String format,
            String destFilePath, int imgWidth, int imgHeight) {
        if (format != null || !"".equals(format)) {
            if (format.equalsIgnoreCase(".gif")) {
                createGifMiniImage(srcFile, destFilePath, imgWidth, imgHeight);
            } else {
                createOtherMiniImage(srcFile, destFilePath, imgWidth, imgHeight);
            }
            return destFilePath;
        }

        return "";
    }

    /**
     * 格式化JPG, JPEG, JPE, JFIF, BMP, DIB, PNG图形文件
     * 
     * @param srcFile
     *            要处理的image文件
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            长高比例中的长比例
     * @param imgHeight
     *            长高比例中的高比例
     * @return 成功：目标文件路径 失败：空字符串
     */
    private static String dealOtherImage(File srcFile, String destFilePath,
            int imgWidth, int imgHeight) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(
                    srcFile));

            // 获取图片尺寸
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            int desWidth = 0;
            int desHeight = 0;
            float srcBili = (float) width / height;
            float desBili = (float) imgWidth / imgHeight;

            if (srcBili < desBili) {
                desHeight = height;
                desWidth = (int) (desHeight * desBili);
            } else {
                desWidth = width;
                desHeight = (int) (desWidth / desBili);
            }

            // 设置目标图片尺寸和填充底色
            BufferedImage bufImage = new BufferedImage(desWidth, desHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImage.createGraphics();
            g.fillRect(0, 0, desWidth, desHeight);

            // 插入原图像
            if (srcBili > desBili) {
                int top = (desHeight - height) / 2;
                if (top < 0) {
                    top *= -1;
                }
                g.drawImage(bufferedImage, null, 0, top);
            } else {
                int left = (desWidth - width) / 2;
                if (left < 0) {
                    left *= -1;
                }
                g.drawImage(bufferedImage, null, left, 0);
            }
            g.dispose();

            // 输出图片
            FileOutputStream out = new FileOutputStream(destFilePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufImage);
            param.setQuality(50f, true);
            encoder.encode(bufImage, param);
            out.flush();
            out.close();

            // 图片格式化成功
            return destFilePath;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 图片格式化失败
        return "";
    }

    /**
     * 格式化GIF图形文件
     * 
     * @param srcFile
     *            要处理的image文件
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            长高比例中的长比例
     * @param imgHeight
     *            长高比例中的高比例
     * @return 成功：目标文件路径 失败：空字符串
     */
    private static String dealGifImage(File srcFile, String destFilePath,
            int imgWidth, int imgHeight) {
        try {
            // 设置目标图片
            FileOutputStream out = new FileOutputStream(destFilePath);
            AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
            gifEncoder.setRepeat(0);
            gifEncoder.start(out);

            // 获取原图片信息
            GifDecoder gifDecoder = new GifDecoder();
            gifDecoder.read(srcFile.getPath());
            int frameCount = gifDecoder.getFrameCount();

            // 获取原图片尺寸
            int height = (int) gifDecoder.getFrameSize().getHeight();
            int width = (int) gifDecoder.getFrameSize().getWidth();
            int desWidth = 0;
            int desHeight = 0;
            float srcBili = (float) width / height;
            float desBili = (float) imgWidth / imgHeight;

            if (srcBili < desBili) {
                desHeight = height;
                desWidth = (int) (desHeight * desBili);
            } else {
                desWidth = width;
                desHeight = (int) (desWidth / desBili);
            }

            for (int i = 0; i < frameCount; i++) {
                BufferedImage bufferedImage = gifDecoder.getFrame(i);

                BufferedImage bi = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bi.createGraphics();
                g.fillRect(0, 0, desWidth, desHeight);

                // 插入原图像
                if (srcBili > desBili) {
                    int top = (desHeight - height) / 2;
                    if (top < 0) {
                        top *= -1;
                    }
                    g.drawImage(bufferedImage, null, 0, top);
                } else {
                    int left = (desWidth - width) / 2;
                    if (left < 0) {
                        left *= -1;
                    }
                    g.drawImage(bufferedImage, null, left, 0);
                }
                g.dispose();

                gifEncoder.setDelay(gifDecoder.getDelay(i));
                gifEncoder.addFrame(bi);

            }
            gifEncoder.finish();
            out.flush();
            out.close();

            return destFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式化JPG, JPEG, JPE, JFIF, BMP, DIB, PNG图形文件<br>
     * 并生成缩略图
     * 
     * @param srcFile
     *            要处理的image文件
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标image宽
     * @param imgHeight
     *            目标image高
     * @return 成功：目标文件路径 失败：空字符串
     */
    private static String createOtherMiniImage(File srcFile,
            String destFilePath, int imgWidth, int imgHeight) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(
                    srcFile));
            // imageZoomOut(ImageIO.read(new FileInputStream(srcFile)),
            // imgWidth,
            // imgHeight);

            // 获取图片尺寸
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            int desWidth = 0;
            int desHeight = 0;
            float srcBili = (float) width / height;
            float desBili = (float) imgWidth / imgHeight;

            if (srcBili < desBili) {
                desHeight = height;
                desWidth = (int) (desHeight * desBili);
            } else {
                desWidth = width;
                desHeight = (int) (desWidth / desBili);
            }

            // 缩略图尺寸
            int changeToWideth = 0;
            int changeToHeight = 0;
            if (desWidth / desHeight >= imgWidth / imgHeight) {
                if (desWidth > imgWidth) {
                    changeToWideth = imgWidth;
                    changeToHeight = (desHeight * imgWidth) / desWidth;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            } else {
                if (desHeight > imgHeight) {
                    changeToHeight = imgHeight;
                    changeToWideth = (desWidth * imgHeight) / desHeight;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            }

            // 设置目标图片尺寸和填充底色
            BufferedImage bufImage = new BufferedImage(desWidth, desHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImage.createGraphics();
            g.fillRect(0, 0, desWidth, desHeight);

            // 插入原图像
            if (srcBili > desBili) {
                int top = (desHeight - height) / 2;
                if (top < 0) {
                    top *= -1;
                }
                g.drawImage(bufferedImage, null, 0, top);
            } else {
                int left = (desWidth - width) / 2;
                if (left < 0) {
                    left *= -1;
                }
                g.drawImage(bufferedImage, null, left, 0);
            }
            g.dispose();

            // 输出图片
            FileOutputStream out = new FileOutputStream(destFilePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // 生成缩略图
            bufImage = imageZoomOut(bufImage, changeToWideth, changeToHeight);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufImage);
            param.setQuality(50f, true);
            encoder.encode(bufImage, param);
            out.flush();
            out.close();

            // 图片格式化成功
            return destFilePath;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 图片格式化失败
        return "";
    }

    /**
     * 格式化GIF图形文件<br>
     * 并生成缩略图
     * 
     * @param srcFile
     *            要处理的image文件
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标image宽
     * @param imgHeight
     *            目标image高
     * @return 成功：目标文件路径 失败：空字符串
     */
    private static String createGifMiniImage(File srcFile, String destFilePath,
            int imgWidth, int imgHeight) {
        try {
            // 设置目标图片
            FileOutputStream out = new FileOutputStream(destFilePath);
            AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
            gifEncoder.setRepeat(0);
            gifEncoder.start(out);

            // 获取原图片信息
            GifDecoder gifDecoder = new GifDecoder();
            gifDecoder.read(srcFile.getPath());
            int frameCount = gifDecoder.getFrameCount();

            // 获取原图片尺寸
            int height = (int) gifDecoder.getFrameSize().getHeight();
            int width = (int) gifDecoder.getFrameSize().getWidth();
            int desWidth = 0;
            int desHeight = 0;
            float srcBili = (float) width / height;
            float desBili = (float) imgWidth / imgHeight;

            if (srcBili < desBili) {
                desHeight = height;
                desWidth = (int) (desHeight * desBili);
            } else {
                desWidth = width;
                desHeight = (int) (desWidth / desBili);
            }

            // 缩略图尺寸
            int changeToWideth = 0;
            int changeToHeight = 0;
            if (desWidth / desHeight >= imgWidth / imgHeight) {
                if (desWidth > imgWidth) {
                    changeToWideth = imgWidth;
                    changeToHeight = (desHeight * imgWidth) / desWidth;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            } else {
                if (desHeight > imgHeight) {
                    changeToHeight = imgHeight;
                    changeToWideth = (desWidth * imgHeight) / desHeight;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            }

            for (int i = 0; i < frameCount; i++) {
                BufferedImage bufferedImage = gifDecoder.getFrame(i);

                BufferedImage bi = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bi.createGraphics();
                g.fillRect(0, 0, desWidth, desHeight);

                // 插入原图像
                if (srcBili > desBili) {
                    int top = (desHeight - height) / 2;
                    if (top < 0) {
                        top *= -1;
                    }
                    g.drawImage(bufferedImage, null, 0, top);
                } else {
                    int left = (desWidth - width) / 2;
                    if (left < 0) {
                        left *= -1;
                    }
                    g.drawImage(bufferedImage, null, left, 0);
                }
                g.dispose();

                gifEncoder.setDelay(gifDecoder.getDelay(i));
                // 生成缩略图
                bi = imageZoomOut(bi, changeToWideth, changeToHeight);
                gifEncoder.addFrame(bi);

            }
            gifEncoder.finish();
            out.flush();
            out.close();

            return destFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static BufferedImage imageZoomOut(BufferedImage srcBufferImage,
            int w, int h) {
        width = srcBufferImage.getWidth();
        height = srcBufferImage.getHeight();
        scaleWidth = w;

        if (DetermineResultSize(w, h) == 1) {
            return srcBufferImage;
        }
        CalContrib();
        BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
        BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
        return pbFinalOut;
    }

    /**
     * 决定图像尺寸
     * 
     * @param w
     *            宽
     * @param h
     *            高
     * @return 0：执行缩小操作标识 1：不执行缩小操作标识
     */
    private static int DetermineResultSize(int w, int h) {
        double scaleH, scaleV;
        scaleH = (double) w / (double) width;
        scaleV = (double) h / (double) height;
        // 需要判断一下scaleH，scaleV，不做放大操作
        if (scaleH >= 1.0 && scaleV >= 1.0) {
            return 1;
        }
        return 0;

    } // end of DetermineResultSize()

    private static double Lanczos(int i, int inWidth, int outWidth,
            double Support) {
        double x;

        x = (double) i * (double) outWidth / (double) inWidth;

        return Math.sin(x * PI) / (x * PI) * Math.sin(x * PI / Support)
                / (x * PI / Support);

    }

    private static void CalContrib() {
        nHalfDots = (int) ((double) width * support / (double) scaleWidth);
        nDots = nHalfDots * 2 + 1;
        try {
            contrib = new double[nDots];
            normContrib = new double[nDots];
            tmpContrib = new double[nDots];
        } catch (Exception e) {
            System.out.println("init   contrib,normContrib,tmpContrib" + e);
        }

        int center = nHalfDots;
        contrib[center] = 1.0;

        double weight = 0.0;
        int i = 0;
        for (i = 1; i <= center; i++) {
            contrib[center + i] = Lanczos(i, width, scaleWidth, support);
            weight += contrib[center + i];
        }

        for (i = center - 1; i >= 0; i--) {
            contrib[i] = contrib[center * 2 - i];
        }

        weight = weight * 2 + 1.0;

        for (i = 0; i <= center; i++) {
            normContrib[i] = contrib[i] / weight;
        }

        for (i = center + 1; i < nDots; i++) {
            normContrib[i] = normContrib[center * 2 - i];
        }
    } // end of CalContrib()

    // 处理边缘
    private static void CalTempContrib(int start, int stop) {
        double weight = 0;

        int i = 0;
        for (i = start; i <= stop; i++) {
            weight += contrib[i];
        }

        for (i = start; i <= stop; i++) {
            tmpContrib[i] = contrib[i] / weight;
        }

    } // end of CalTempContrib()

    private static int GetRedValue(int rgbValue) {
        int temp = rgbValue & 0x00ff0000;
        return temp >> 16;
    }

    private static int GetGreenValue(int rgbValue) {
        int temp = rgbValue & 0x0000ff00;
        return temp >> 8;
    }

    private static int GetBlueValue(int rgbValue) {
        return rgbValue & 0x000000ff;
    }

    private static int ComRGB(int redValue, int greenValue, int blueValue) {

        return (redValue << 16) + (greenValue << 8) + blueValue;
    }

    // 行水平滤波
    private static int HorizontalFilter(BufferedImage bufImg, int startX,
            int stopX, int start, int stop, int y, double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startX, j = start; i <= stopX; i++, j++) {
            valueRGB = bufImg.getRGB(i, y);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
                Clip((int) valueBlue));
        return valueRGB;

    } // end of HorizontalFilter()

    // 图片水平滤波
    private static BufferedImage HorizontalFiltering(BufferedImage bufImage,
            int iOutW) {
        int dwInW = bufImage.getWidth();
        int dwInH = bufImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iOutW, dwInH,
                BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < iOutW; x++) {

            int startX;
            int start;
            int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
            int y = 0;

            startX = X - nHalfDots;
            if (startX < 0) {
                startX = 0;
                start = nHalfDots - X;
            } else {
                start = 0;
            }

            int stop;
            int stopX = X + nHalfDots;
            if (stopX > (dwInW - 1)) {
                stopX = dwInW - 1;
                stop = nHalfDots + (dwInW - 1 - X);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start,
                            stop, y, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start,
                            stop, y, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }
        }

        return pbOut;

    } // end of HorizontalFiltering()

    private static int VerticalFilter(BufferedImage pbInImage, int startY,
            int stopY, int start, int stop, int x, double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startY, j = start; i <= stopY; i++, j++) {
            valueRGB = pbInImage.getRGB(x, i);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
            // System.out.println(valueRed+"->"+Clip((int)valueRed)+"<-");
            //   
            // System.out.println(valueGreen+"->"+Clip((int)valueGreen)+"<-");
            // System.out.println(valueBlue+"->"+Clip((int)valueBlue)+"<-"+"-->");
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
                Clip((int) valueBlue));
        // System.out.println(valueRGB);
        return valueRGB;

    } // end of VerticalFilter()

    private static BufferedImage VerticalFiltering(BufferedImage pbImage,
            int iOutH) {
        int iW = pbImage.getWidth();
        int iH = pbImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iW, iOutH,
                BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < iOutH; y++) {

            int startY;
            int start;
            int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);

            startY = Y - nHalfDots;
            if (startY < 0) {
                startY = 0;
                start = nHalfDots - Y;
            } else {
                start = 0;
            }

            int stop;
            int stopY = Y + nHalfDots;
            if (stopY > (int) (iH - 1)) {
                stopY = iH - 1;
                stop = nHalfDots + (iH - 1 - Y);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop,
                            x, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop,
                            x, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }

        }

        return pbOut;

    } // end of VerticalFiltering()

    static int Clip(int x) {
        if (x < 0)
            return 0;
        if (x > 255)
            return 255;
        return x;
    }

    /**
     * 生成缩略图，并格式化 <br>
     * 支持格式有：JPG, JPEG, JPE, JFIF, BMP, DIB, PNG, GIF
     * 
     * @param srcFile
     *            要处理的image文件
     * @param format
     *            image格式
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标image宽
     * @param imgHeight
     *            目标image高
     * @return 成功：目标文件路径 失败：空字符串
     */
    public static byte[] createMiniImage1(File srcFile, String format,
            String destFilePath, int imgWidth, int imgHeight) {
        if (format != null || !"".equals(format)) {
            if (format.equalsIgnoreCase(".gif")) {
                createGifMiniImage1(srcFile, destFilePath, imgWidth, imgHeight);
            } else {
                inputStream = createOtherMiniImage1(srcFile, destFilePath,
                        imgWidth, imgHeight);
            }
            return inputStream;
        }

        return inputStream;
    }

    /**
     * 格式化GIF图形文件<br>
     * 并生成缩略图
     * 
     * @param srcFile
     *            要处理的image文件
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标image宽
     * @param imgHeight
     *            目标image高
     * @return 成功：目标文件路径 失败：空字符串
     */
    private static byte[] createGifMiniImage1(File srcFile,
            String destFilePath, int imgWidth, int imgHeight) {
        try {
            // 设置目标图片
            FileOutputStream out = new FileOutputStream(destFilePath);
            AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
            gifEncoder.setRepeat(0);
            gifEncoder.start(out);

            // 获取原图片信息
            GifDecoder gifDecoder = new GifDecoder();
            gifDecoder.read(srcFile.getPath());
            int frameCount = gifDecoder.getFrameCount();

            // 获取原图片尺寸
            int height = (int) gifDecoder.getFrameSize().getHeight();
            int width = (int) gifDecoder.getFrameSize().getWidth();
            int desWidth = 0;
            int desHeight = 0;
            float srcBili = (float) width / height;
            float desBili = (float) imgWidth / imgHeight;

            desHeight = height;
            desWidth = width;

            // 缩略图尺寸
            int changeToWideth = 0;
            int changeToHeight = 0;
            if (desWidth / desHeight >= imgWidth / imgHeight) {
                if (desWidth > imgWidth) {
                    changeToWideth = imgWidth;
                    changeToHeight = (desHeight * imgWidth) / desWidth;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            } else {
                if (desHeight > imgHeight) {
                    changeToHeight = imgHeight;
                    changeToWideth = (desWidth * imgHeight) / desHeight;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            }

            for (int i = 0; i < frameCount; i++) {
                BufferedImage bufferedImage = gifDecoder.getFrame(i);

                BufferedImage bi = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bi.createGraphics();
                g.fillRect(0, 0, desWidth, desHeight);

                // 插入原图像
                if (srcBili > desBili) {
                    int top = (desHeight - height) / 2;
                    if (top < 0) {
                        top *= -1;
                    }
                    g.drawImage(bufferedImage, null, 0, top);
                } else {
                    int left = (desWidth - width) / 2;
                    if (left < 0) {
                        left *= -1;
                    }
                    g.drawImage(bufferedImage, null, left, 0);
                }
                g.dispose();

                gifEncoder.setDelay(gifDecoder.getDelay(i));
                // 生成缩略图
                bi = imageZoomOut(bi, changeToWideth, changeToHeight);
                gifEncoder.addFrame(bi);

            }
            gifEncoder.finish();
            out.flush();
            out.close();

            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 格式化JPG, JPEG, JPE, JFIF, BMP, DIB, PNG图形文件<br>
     * 并生成缩略图
     * 
     * @param srcFile
     *            要处理的image文件
     * @param destFilePath
     *            目标文件路径
     * @param imgWidth
     *            目标image宽
     * @param imgHeight
     *            目标image高
     * @return 成功：目标文件路径 失败：空字符串
     */
    private static byte[] createOtherMiniImage1(File srcFile,
            String destFilePath, int imgWidth, int imgHeight) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(
                    srcFile));

            // 获取图片尺寸
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            int desWidth = 0;
            int desHeight = 0;
            float srcBili = (float) width / height;
            float desBili = (float) imgWidth / imgHeight;

            desHeight = height;
            desWidth = (int) (desHeight * desBili);

            // 缩略图尺寸
            int changeToWideth = 0;
            int changeToHeight = 0;
            if (desWidth / desHeight >= imgWidth / imgHeight) {
                if (desWidth > imgWidth) {
                    changeToWideth = imgWidth;
                    changeToHeight = (desHeight * imgWidth) / desWidth;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            } else {
                if (desHeight > imgHeight) {
                    changeToHeight = imgHeight;
                    changeToWideth = (desWidth * imgHeight) / desHeight;
                } else {
                    changeToWideth = desWidth;
                    changeToHeight = desHeight;
                }
            }

            // 设置目标图片尺寸和填充底色
            BufferedImage bufImage = new BufferedImage(width
                    - ((width - desWidth) / 2), height
                    - ((height - desHeight) / 2), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImage.createGraphics();
            g.fillRect(0, 0, desWidth, desHeight);

            // 插入原图像
            if (srcBili > desBili) {
                int top = (desHeight - height) / 2;
                if (top < 0) {
                    top *= -1;
                }
                g.drawImage(bufferedImage, null, 0, top);
            } else {
                int left = (desWidth - width) / 2;
                if (left < 0) {
                    left *= -1;
                }
                g.drawImage(bufferedImage, null, left, 0);
            }
            g.dispose();

            // 生成缩略图
            bufImage = imageZoomOut(bufImage, changeToWideth, changeToHeight);
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufImage, "jpg", arrayOutputStream);
            inputStream = arrayOutputStream.toByteArray();
            arrayOutputStream.close();
        } catch (Exception ex) {
            //do nothing        	
        }

        // 图片格式化失败
        return inputStream;
    }
}
