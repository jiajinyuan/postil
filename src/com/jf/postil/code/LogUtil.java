package com.jf.postil.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static java.lang.System.out;

public class LogUtil
{
    private static File logFile;

    private static BufferedWriter bw;

    static void writerLog(String info) throws IOException
    {
        out.println(info);
        bw.write(info);
        bw.newLine();
    }

    static void initFile() throws IOException
    {
        logFile = new File("./Check_Comments_report.html");
        if (logFile.exists())
        {
            logFile.delete();
        }
        logFile.createNewFile();

        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile), "UTF-8"));

        writerLog(htmlHead());
    }

    static String tableTitle(String title)
    {
        return "<h4>&nbsp;" + title + "</h4>";
    }

    static String tableBottom()
    {

        return "    </tbody>\n" +
                "</table>";
    }

    static String tableHead()
    {
        return "<table id=\"hor-minimalist-b\" summary=\"Employee Pay Sheet\">\n" +
                "    <thead>\n" +
                "    \t<tr>\n" +
                "        \t<th scope=\"col\">File Name</th>\n" +
                "            <th scope=\"col\">Comments Num</th>\n" +
                "            <th scope=\"col\">Empty Num</th>\n" +
                "            <th scope=\"col\">Code Num</th>\n" +
                "            <th scope=\"col\">All Num</th>\n" +
                "            <th scope=\"col\">Comments Ratio</th>\n" +
                "            <th scope=\"col\">Path</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tbody>";
    }

    static String htmlBottom()
    {
        return "</body>\n" +
                "</html>";
    }

    static String htmlHead()
    {

        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>Check Comments</title>\n" +
                "<style type=\"text/css\">\n" +
                "body\n" +
                "{\n" +
                "\tline-height: 1.6em;\n" +
                "}\n" +
                "#hor-minimalist-b\n" +
                "{\n" +
                "\tfont-family: \"Lucida Sans Unicode\", \"Lucida Grande\", Sans-Serif;\n" +
                "\tfont-size: 12px;\n" +
                "\tbackground: #fff;\n" +
                "\tmargin: 10px;\n" +
                "\twidth: 98%;\n" +
                "\tborder-collapse: collapse;\n" +
                "\ttext-align: left;\n" +
                "}\n" +
                "#hor-minimalist-b th\n" +
                "{\n" +
                "\tfont-size: 14px;\n" +
                "\tfont-weight: normal;\n" +
                "\tcolor: #039;\n" +
                "\tpadding: 10px 8px;\n" +
                "\tborder-bottom: 2px solid #6678b1;\n" +
                "}\n" +
                "#hor-minimalist-b td\n" +
                "{\n" +
                "\tborder-bottom: 1px solid #ccc;\n" +
                "\tcolor: #669;\n" +
                "\tpadding: 6px 8px;\n" +
                "}\n" +
                "#hor-minimalist-b tbody tr:hover td\n" +
                "{\n" +
                "\tcolor: #009;\n" +
                "}\n" +
                "h4{\n" +
                "\tcolor: #009;\n" +
                "}\t\n" +
                ".redTr td{\n" +
                "\tcolor:red !important;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>";
    }

    static String getLogFilePath() throws IOException
    {
        bw.close();
        return logFile.getCanonicalPath();
    }
}
