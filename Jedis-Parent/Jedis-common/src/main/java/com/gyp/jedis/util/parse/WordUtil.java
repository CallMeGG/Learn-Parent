package com.gyp.jedis.util.parse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * Title: WordUtil
 * Description:
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 */
public class WordUtil {
    public static final String WORD_TYPE_DOC = ".doc";
    public static final String WORD_TYPE_DOCX = ".docx";

    /**
     * @param in   输入流
     * @param type 文件类型
     * @return 处理得到的字符串内容
     * @throws IOException IOException
     */
    public static String parse(InputStream in, String type) throws IOException {
        if (WORD_TYPE_DOC.equals(type)) {
            HWPFDocument document = new HWPFDocument(in);
            // return standardText(document.getText().toString());

            WordExtractor ex = new WordExtractor(document);
            return standardText(ex.getText());
        } else if (WORD_TYPE_DOCX.equals(type)) {
            XWPFDocument xdoc = new XWPFDocument(in);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            return standardText(extractor.getText());
        }
        return "";
    }

    /**
     * //正反去掉/n中间的数字
     */
    private static String REGEX_N = "(?<=\\n)\\d(?=[\\n])";//这是只有一个的情况
    /**
     * 这是有很多/r/n的情况,不能同时前后都匹配，小标题的页签都是/r/n处理的如果前后都匹配会把小标题页签弄掉
     */
    private static String REGEX_R = "(?<=\\r\\n\\r\\n)\\d(?=[\\r\\n\\r\\n])";
    private static Pattern PATTERN_SPACE_N = Pattern.compile(REGEX_N);
    private static Pattern PATTERN_SPACE_R = Pattern.compile(REGEX_R);

    // 去除前后的 \r\n
    private static Pattern PATTERN_SPACE_PREFIX = Pattern.compile("^((\r\n)*)");
    private static Pattern PATTERN_SPACE_SUFFIX = Pattern.compile("((\r\n)*)$");

    /**
     * /**
     * 去掉paga
     *
     * @param result 待处理
     * @return 处理后的
     */
    private static String standardText(String result) {
        result = result.replaceAll("PAGE", "");
        result = PATTERN_SPACE_N.matcher(result).replaceAll("");
        result = PATTERN_SPACE_R.matcher(result).replaceAll("");
        result = PATTERN_SPACE_PREFIX.matcher(result).replaceAll("");
        result = PATTERN_SPACE_SUFFIX.matcher(result).replaceAll("");

        return result;
    }
}
