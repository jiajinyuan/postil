package com.jf.postil.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

public class CountFile
{
    private int cntCode = 0;
    private int cntNode = 0;
    private int cntSpace = 0;

    private boolean flagNode = false;


    void checkFile(File file) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null)
        {
            pattern(line);
        }

        int allLine = cntNode + cntCode;

        StringBuilder sb = new StringBuilder();

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) cntNode / (float) allLine * 100);

        double parseDouble;
        try
        {
            parseDouble = Double.parseDouble(result);
        }
        catch (Exception e)
        {
            parseDouble = 0.0;
        }
        if (parseDouble < 25.00)
        {
            sb.append("<tr class='redTr'>");
        }
        else
        {
            sb.append("<tr>");
        }
        sb.append("<td>").append(file.getName()).append("</td>");
        sb.append("<td>").append(cntNode).append("</td>");
        sb.append("<td>").append(cntSpace).append("</td>");
        sb.append("<td>").append(cntCode).append("</td>");
        sb.append("<td>").append(allLine).append("</td>");
        sb.append("<td>").append(result).append("%").append("</td>");
        sb.append("<td>").append(file.getAbsolutePath()).append("</td>");
        sb.append("</tr>");

        System.out.println(sb.toString());
        LogUtil.writerLog(sb.toString());
    }

    private void pattern(String line)
    {
        String regxNodeBegin = "\\s*/\\*.*";
        String regxNodeEnd = ".*\\*/\\s*";
        String regx = "//.*";
        String regxSpace = "\\s*";
        if (line.matches(regxNodeBegin) && line.matches(regxNodeEnd))
        {
            ++cntNode;
            return;
        }
        if (line.matches(regxNodeBegin))
        {
            ++cntNode;
            flagNode = true;
        }
        else if (line.matches(regxNodeEnd))
        {
            ++cntNode;
            flagNode = false;
        }
        else if (line.matches(regxSpace))
        {
            ++cntSpace;
        }
        else if (line.matches(regx))
        {
            ++cntNode;
        }
        else if (flagNode)
        {
            ++cntNode;
        }
        else
        {
            ++cntCode;
        }
    }

}