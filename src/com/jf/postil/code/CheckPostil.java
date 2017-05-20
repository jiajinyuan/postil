package com.jf.postil.code;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class CheckPostil
{
//	private static final int ALL_CODE_COUNT = 0;
//	private static final int ALL_NOTE_COUNT = 0;
//	private static final double NOTE_PERCENT = 0;
	
    private static final String JAVA = "java";

    public static void main(String[] arg) throws IOException
    {
        Scanner s = new Scanner(System.in);
        out.print("请输入要统计的目录(多个路径使用\";\"分隔)：");
        String filePath = s.nextLine();

        String[] paths = filePath.split(";");

        LogUtil.initFile();
        for (String path : paths)
        {

            LogUtil.writerLog(LogUtil.tableTitle(path));
            LogUtil.writerLog(LogUtil.tableHead());
            recursionFile(new File(path));
            LogUtil.writerLog(LogUtil.tableBottom());
        }
        LogUtil.writerLog(LogUtil.htmlBottom());
        out.println("===| 统计报告：" + LogUtil.getLogFilePath());
    }

    private static void recursionFile(File file) throws IOException
    {
        if (file.isDirectory())
        {
            File[] files = file.listFiles(new FileFilter()
            {
                @Override
                public boolean accept(File pathname)
                {
                    return pathname.isDirectory() || isJavaFile(pathname.getName());
                }
            });

            for (File subFile : files != null ? files : new File[0])
            {
                recursionFile(subFile);
            }
        }
        else
        {
            if (isJavaFile(file.getName()))
            {
                new CountFile().checkFile(file);
            }
        }
    }

    private static boolean isJavaFile(String name)
    {
        int x = name.lastIndexOf('.');
        return (x > -1) && (x < name.length() - 1) && JAVA.equals(name.substring(x + 1));
    }
}
