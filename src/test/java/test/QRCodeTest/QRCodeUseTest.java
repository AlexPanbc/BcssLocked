package test.QRCodeTest;

import com.google.zxing.BarcodeFormat;
import org.junit.Test;

import java.io.File;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/4/10
 * \* Time: 下午3:39
 * \* Description:QRCode 生成二维码测试
 * \
 */
public class QRCodeUseTest {

    /**
     * 简单使用
     */
    @Test
    public void simpleUseTest(){
        File file = new File("/Users/liuhuichao/Downloads/test.png");
        QRCode.encodeQRCODE("万万没想到", file);
        System.out.println(QRCode.decode(file));
    }


}
