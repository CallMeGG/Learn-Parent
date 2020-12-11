package com.gyp.jedis.util.generator;

import java.io.*;
import java.security.MessageDigest;

/**
 * Description: 加密类Util
 *
 * @author GYP
 * @version 1.0
 */
public class SecretUtil {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static class MD5Util {


        public static String MD5(String content) {
            if (null == content) {
                return null;
            }

            try {
                MessageDigest mdInst = MessageDigest.getInstance("MD5");
                byte[] digest = mdInst.digest(content.getBytes());
                return getFormattedText(digest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        /**
         * Takes the raw bytes from the digest and formats them correct.
         *
         * @param bytes the raw bytes from the digest.
         * @return the formatted bytes.
         */
        private static String getFormattedText(byte[] bytes) {
            int len = bytes.length;
            StringBuilder buf = new StringBuilder(len * 2);
            // 把密文转换成十六进制的字符串形式
            for (byte aByte : bytes) {
                buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
                buf.append(HEX_DIGITS[aByte & 0x0f]);
            }
            return buf.toString();
        }

        /**
         * 获取输入流的MD5值
         *
         * @param is
         * @return
         * @throws IOException
         */
        public static String getMD5(InputStream is) throws IOException {
            StringBuilder md5 = new StringBuilder();

            InputStreamReader in = new InputStreamReader(is);
            BufferedReader bin = new BufferedReader(in);
            String line;
            while ((line = bin.readLine()) != null) {
                md5.append(line);
            }
            bin.close();
            in.close();

            return MD5(md5.toString());
        }

        /**
         * 获取该文件的MD5值
         *
         * @param file
         * @return
         * @throws IOException
         */
        public static String getMD5(File file) throws IOException {
            FileInputStream fis = new FileInputStream(file);
            return getMD5(fis);
        }

        /**
         * 获取指定路径文件的MD5值
         *
         * @param path
         * @return
         * @throws IOException
         */
        public static String getMD5(String path) throws IOException {
            FileInputStream fis = new FileInputStream(path);
            return getMD5(fis);
        }

        /**
         * 校验该输入流的MD5值
         *
         * @param is
         * @param toBeCheckSum
         * @return
         * @throws IOException
         */
        public static boolean md5CheckSum(InputStream is, String toBeCheckSum) throws IOException {
            return getMD5(is).equals(toBeCheckSum.toLowerCase());
        }

        /**
         * 校验该文件的MD5值
         *
         * @param file
         * @param toBeCheckSum
         * @return
         * @throws IOException
         */
        public static boolean md5CheckSum(File file, String toBeCheckSum) throws IOException {
            return getMD5(file).equals(toBeCheckSum.toLowerCase());
        }

        /**
         * 校验指定路径文件的MD5值
         *
         * @param path
         * @param toBeCheckSum
         * @return
         * @throws IOException
         */
        public static boolean md5CheckSum(String path, String toBeCheckSum) throws IOException {
            return getMD5(path).equals(toBeCheckSum.toLowerCase());
        }

    }


    public static class SHA1Util {

        public static String SHA1(String content) {
            if (null == content) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                byte[] digest = messageDigest.digest(content.getBytes());
                return getFormattedText(digest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        /**
         * Takes the raw bytes from the digest and formats them correct.
         *
         * @param bytes the raw bytes from the digest.
         * @return the formatted bytes.
         */
        private static String getFormattedText(byte[] bytes) {
            int len = bytes.length;
            StringBuilder buf = new StringBuilder(len * 2);
            // 把密文转换成十六进制的字符串形式
            for (byte aByte : bytes) {
                buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
                buf.append(HEX_DIGITS[aByte & 0x0f]);
            }
            return buf.toString();
        }
    }

}