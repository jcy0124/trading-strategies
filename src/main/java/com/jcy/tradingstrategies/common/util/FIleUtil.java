package com.jcy.tradingstrategies.common.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FIleUtil {

    public static void writeTxt(List<String> context, String path) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        for (String s : context) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
}