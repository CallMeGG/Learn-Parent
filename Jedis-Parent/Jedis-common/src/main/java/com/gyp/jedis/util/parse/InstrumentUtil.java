package com.gyp.jedis.util.parse;

import com.gyp.jedis.exception.SystemExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Title: InstrumentServiceUtil
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 */
public class InstrumentUtil {

    /**
     * 转换文书的数据
     *
     * @param ext  文件后缀名
     * @param file 文件
     * @return 文件内容的字符串格式
     */
    public static String parse(String ext, MultipartFile file) throws IOException {
        String result = null;

        InputStream in = file.getInputStream();
        SystemExceptionHandler.flagCheck(in.available() == 0, "请不要上传空白的文书");

        if (Ext.PDF.equals(ext)) {
            result = PDFUtil.parse(in);
        } else if (Ext.DOC.equals(ext) || Ext.DOCX.equals(ext)) {
            result = WordUtil.parse(in, ext);
        } else if (Ext.TXT.equals(ext)) {
            // 1. txt默认的选项是ANSI，即GBK编码
            // 2. txt文本文档有四种编码选项：ANSI、Unicode、Unicode big endian、UTF-8
            // 3. 因此我们读取txt文件可能有时候并不知道其编码格式，所以需要用程序动态判断获取txt文件编码
            //      ANSI： 无格式定义
            //      Unicode：  前两个字节为FFFE Unicode文档以0xFFFE开头
            //      Unicode big endian： 前两字节为FEFF
            //      UTF-8： 前两字节为EFBB UTF-8以0xEFBBBF开头
            //      用程序取出前几个字节并进行判断即可。
            // 4.java编码与txt编码对应
            //      java	txt
            //      Unicode	Unicode big endian
            //      utf-8 	utf-8
            //      utf-16	utf-8
            //      gb2312	ANSI
            result = CpdetectorUtil.parseTxtIO(in);
        } else {
            SystemExceptionHandler.throwException("文书类型不支持，解析失败");
        }
        return result;
    }

    private interface Ext {
        String PDF = ".pdf";
        String DOC = ".doc";
        String DOCX = ".docx";
        String TXT = ".txt";
    }
}

