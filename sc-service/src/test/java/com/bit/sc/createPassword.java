package com.bit.sc;

import com.bit.utils.MD5Util;

public class createPassword {

    public static void main(String[] args) {

        String pw1 = MD5Util.compute("111111");//96e79218965eb72c92a549dd5a330112    96e79218965eb72c92a549dd5a330112
        String pw11 = MD5Util.compute("222222");//e3ceb5881a0a1fdaad01296d7554868d
        String pw12 = MD5Util.compute("123456");//e10adc3949ba59abbe56e057f20f883e
        System.out.println(pw1);
        System.out.println(pw11);
        System.out.println(pw12);
    }

}
