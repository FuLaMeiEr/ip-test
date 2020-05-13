package com.example.demo.util;

import java.util.ArrayList;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 10:02 2020/5/13
 **/
public class GetData {

    public Object[] GET_IP_ARR(String ipfrom, String ipto) {
        ArrayList<String> ips = new ArrayList<String>();
        String[] ipfromd = ipfrom.split("\\.");
        String[] iptod = ipto.split("\\.");
        int[] int_ipf = new int[4];
        int[] int_ipt = new int[4];
        for (int i = 0; i < 4; i++) {
            int_ipf[i] = Integer.parseInt(ipfromd[i]);
            int_ipt[i] = Integer.parseInt(iptod[i]);
        }
        for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
            for (int B = (A == int_ipf[0] ? int_ipf[1] : 0); B <= (A == int_ipt[0] ? int_ipt[1]
                    : 255); B++) {
                for (int C = (B == int_ipf[1] ? int_ipf[2] : 0); C <= (B == int_ipt[1] ? int_ipt[2]
                        : 255); C++) {
                    for (int D = (C == int_ipf[2] ? int_ipf[3] : 0); D <= (C == int_ipt[2] ? int_ipt[3]
                            : 255); D++) {
                        ips.add(new String(A + "." + B + "." + C + "." + D));
                    }
                }
            }
        }
        Object[] objects = ips.toArray();

        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        System.out.println(objects.length);
        return objects;
    }


    public static void main(String[] args) {

        GetData getData = new GetData();
        getData.GET_IP_ARR("14.145.0.0","14.147.255.255");
    }

}
