package test.QRCodeTest;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/10
 * \* Time: 下午3:51
 * \* Description: 生成二维码，也支持其他条码生成
 * \
 */
public class QRCode {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRCode.class);

    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int QR_CODE_WIDTH=128;
    private static final int QR_CODE_HEIGHT=128;
    private static final String UTF_8="UTF-8";
    private static final String IMAGE_TYPE_PNG="png";


    /**
     * 生成二维码到文件
     */
    public static void encodeQRCODE(String contents, File file){
        encode(contents,file,BarcodeFormat.QR_CODE,QR_CODE_WIDTH,QR_CODE_HEIGHT,null);
    }


    /**
     * 生成各种码到文件
     * @param contents
     * @param file
     * @param format  生成码的类型
     * @param width
     * @param height
     * @param hints
     */
    public static void encode(String contents, File file, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        BitMatrix bitMatrix =buildBitMatrix(contents,format,width,height,hints);
        try {
            writeToFile(bitMatrix, IMAGE_TYPE_PNG, file);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    /**
     * 通过网络输出二维码
     * @param response
     * @param contents
     */
    public static void encodeQRCODEForResponse(HttpServletResponse response,String contents) throws IOException {
        ByteArrayOutputStream outputData=getQRCODEOutPutStream(contents,BarcodeFormat.QR_CODE,QR_CODE_WIDTH,QR_CODE_HEIGHT,null);
        response.setContentType(MediaType.PNG.toString());
        response.setContentLength(outputData.toByteArray().length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(outputData.toByteArray());
        outputStream.flush();
    }

    /**
     * 获取输出
      * @return
     */
    private static ByteArrayOutputStream getQRCODEOutPutStream(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints){
        Preconditions.checkNotNull(contents);
        Preconditions.checkNotNull(format);
        Preconditions.checkArgument(width>0);
        Preconditions.checkArgument(height>0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitMatrix bitMatrix =buildBitMatrix(contents,format,width,height,hints);
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_TYPE_PNG, outputStream);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return outputStream;
    }



    /**
     * 构建 BitMatrix
     * @return
     */
    private static BitMatrix buildBitMatrix(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints){
        Preconditions.checkNotNull(contents);
        Preconditions.checkNotNull(format);
        Preconditions.checkArgument(width>0);
        Preconditions.checkArgument(height>0);
        BitMatrix bitMatrix =null;
        try {
            contents = new String(contents.getBytes(UTF_8), "ISO-8859-1"); //消除中文乱码
            bitMatrix =new MultiFormatWriter().encode(contents,format,width,height,hints);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
        return bitMatrix;
    }

    /**
     * 写入文件
     * @param matrix
     * @param format
     * @param file
     * @throws IOException
     */
    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    /**
     * 生成二维码
     * @param matrix
     * @return
     */
    private static  BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
                 image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
             }
        }
        return image;
    }


    /**
     * 解析QRCode二维码
     */
    @SuppressWarnings("unchecked")
    public static String decode(File file) {
        Preconditions.checkNotNull(file);
        String resultStr = "";
        try {
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    LOGGER.error("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;
                @SuppressWarnings("rawtypes")
                Hashtable hints = new Hashtable();
                //解码设置编码方式为：utf-8
                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
                result = new MultiFormatReader().decode(bitmap, hints);
                resultStr = result.getText();
            } catch (IOException ioe) {
                LOGGER.error(ioe.toString());
            } catch (ReaderException re) {
                LOGGER.error(re.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        }
        return resultStr;
    }

}
