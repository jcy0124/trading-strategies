package com.jcy.tradingstrategies.common.util;

import cn.hutool.core.date.DateUtil;

import javax.swing.*;
import java.util.List;

public class JOptionPaneUtil {

    public static void showMessageDialogWithList(String title, List<String> stringList) {
        String now = DateUtil.now();
        StringBuilder message = new StringBuilder(now + ":\n");
        for (String item : stringList) {
            message.append(item).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

}
