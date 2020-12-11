package com.gyp.jedis.util.parse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Title: PDFUtil
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 */
public class PDFUtil {

    /**
     * @param in 输入流
     * @return 解析出的字符串
     * @throws IOException IOException
     */
    public static String parse(InputStream in) throws IOException {
        PDDocument document = PDDocument.load(in);
        return parse(document);
    }

    public static String parse(PDDocument document) throws IOException {
        // 获取文档的页数
        int numberOfPages = document.getNumberOfPages();

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setStartPage(1);
        pdfTextStripper.setEndPage(numberOfPages);

        return pdfTextStripper.getText(document);
    }

}
