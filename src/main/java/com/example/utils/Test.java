package com.example.utils;

import java.io.*;

/**
 * @author baoguangyu
 * @version 1.0
 * @date 2021/4/11 14:31
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        dataBaseDump("3306", "root", "root", "mango", "mango");
        backup("localhost:3306", "root", "root", "E:\\test", "mango", "mango");
    }

    /**
     * @param hostIP ip地址，可以是本机也可以是远程
     * @param userName 数据库的用户名
     * @param password 数据库的密码
     * @param savePath 备份的路径
     * @param fileName 备份的文件名
     * @param databaseName 需要备份的数据库的名称
     * @return
     */
    public static boolean backup(String hostIP, String userName, String password, String savePath, String fileName,
                                 String databaseName) {
        fileName +=".sql";
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        //拼接命令行的命令
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump").append(" --opt").append(" -h").append(hostIP);
        stringBuilder.append(" --user=").append(userName).append(" --password=").append(password)
                .append(" --lock-all-tables=true");
        stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ")
                .append(databaseName);
        try {
            //调用外部执行exe文件的javaAPI
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    //mysqldump -h端口号 -u用户 -p密码 数据库 > d:/test.sql --备份D盘
    //备份
    public static void dataBaseDump(String port, String username, String password, String databasename, String sqlname) throws Exception {
        File file = new File("E:\\test");
        if (!file.exists()) {
            file.mkdir();
        }
        File datafile = new File(file + File.separator + sqlname + ".sql");
        if (datafile.exists()) {
            System.out.println(sqlname + "文件名已存在，请更换");
            return;
        }
        //拼接cmd命令
        Process exec = Runtime.getRuntime().exec("cmd /c mysqldump -h" + port + " -u " + username + " -p" + password + " " + databasename + " > " + datafile);
        if (exec.waitFor() == 0) {
            System.out.println("数据库备份成功,备份路径为：" + datafile);
        }
    }

    public static void convert(String docPath,String storePath){

        try {
            File dir = new File("F:\\install\\install_LibreOffice5\\program");//此处是指定路径
            String[] cmd = new String[] { "cmd", "/c",
                    "soffice --headless -invisible --convert-to pdf "+docPath+ " --outdir "+storePath
            };// cmd[2]是要执行的dos命令
            System.out.println(cmd[2]);
            Process process = Runtime.getRuntime().exec(cmd,null,dir);

            // 记录dos命令的返回信息
            StringBuffer resStr = new StringBuffer();
            // 获取返回信息的流
            InputStream in = process.getInputStream();
            Reader reader = new InputStreamReader(in);
            BufferedReader bReader = new BufferedReader(reader);
            for (String res = ""; (res = bReader.readLine()) != null;) {
                resStr.append(res + "\n");
            }
            System.out.println(resStr.toString());
            bReader.close();
            reader.close();
            process.getOutputStream().close();  // 不要忘记了一定要关

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}