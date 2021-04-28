package com.gyp.jedis.util.parse;

import info.monitorenter.cpdetector.io.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Title: CpdetectorUtil
 * Description: 检测文件的编码和文本流的编码
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 */
public class CpdetectorUtil {
    /**
     * 获取探测到的文件对象
     *
     * @return CodepageDetectorProxy 探测对象
     */
    private static CodepageDetectorProxy getDetector() {
        /*
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。
         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、
         * JChardetFacade、ASCIIDetector、UnicodeDetector。
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar
         * cpDetector是基于统计学原理的，不保证完全正确。
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /*
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于
         * 指示是否显示探测过程的详细信息，为false不显示。
         */
        detector.add(new ParsingDetector(false));
        /*
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());

        return detector;
    }


    /**
     * 获取文件流编码
     *
     * @param in InputStream
     * @return 对应编码
     */
    public static String getIOEncode(InputStream in) {

        return getIOEncode(in, Integer.MAX_VALUE);
    }

    /**
     * 获取文件流编码
     *
     * @param in  InputStream
     * @param len 判断编码的字节长度
     * @return 对应编码
     */
    public static String getIOEncode(InputStream in, Integer len) {
        CodepageDetectorProxy detector = getDetector();

        Charset charset;
        try {
            charset = detector.detectCodepage(new BufferedInputStream(in), len); // len 表示读取 len 字节来判断文件流的编码,读得越多越精确,但是速度慢
        } catch (IOException e) {
            //这里获取编码失败,使用系统默认的编码
            charset = Charset.defaultCharset();
            System.out.println(e.getMessage());
        }
        return charset.name();
    }

    /**
     * 获取文件流编码
     */
    public static String parseTxtIO(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(in, out);

        String charset = getIOEncode(new ByteArrayInputStream(out.toByteArray()));
        String result = IOUtils.toString(new ByteArrayInputStream(out.toByteArray()), charset);

        in.close();
        out.close();
        return result;
    }
}
