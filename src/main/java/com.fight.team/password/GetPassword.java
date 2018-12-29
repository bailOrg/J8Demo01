package com.fight.team.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bail
 * @date 2018/12/29
 */
public class GetPassword {
    public static void main(String[] args){
        // employeeId
        String userCode = "2c8080815cd3a74a015cd3ae86850001";
        // 密码
        String password = "123456";
        // 盐
        String salt = "991c96c88b421ac565b41f622d56978f7ffffea37fae86872711464da0e4c99b";
        String secPassword = toHexString(md5Hex(userCode + toHexString(md5Hex(password)) + salt));
        System.out.println(secPassword);
    }


    /**
     * hex加密
     * @param b 待加密字节数组
     * @return 加密后的字符串
     */
    private static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            sb.append("0123456789abcdef".charAt(b[i] >>> 4 & 15));
            sb.append("0123456789abcdef".charAt(b[i] & 15));
        }

        return sb.toString();
    }

    /**
     * md5加密
     * @param data 待加密的字符串
     * @return 加密后的字节数组
     */
    private static byte[] md5Hex(String data) {
        try {
            return MessageDigest.getInstance("MD5").digest(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
