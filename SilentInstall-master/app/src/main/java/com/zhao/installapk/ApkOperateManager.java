package com.zhao.installapk;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;


public class ApkOperateManager {
    public static String TAG = "ApkOperateManager";
    public static final String EXCUTOR_RESULT = "com.zhao.install.EXCUTOR_RESULT";

    //安装apk
    public static void installApk(Context context, String fileName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + fileName),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    //卸载apk
    public static void uninstallApk(Context context, String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
    }

    //静默安装
    public static void installApkDefaul(Context context, String fileName, String pakcageName, boolean isopen) {
        Log.d(TAG, "jing mo an zhuang");
        File file = new File(fileName);
        int installFlags = 0;
        if (!file.exists())
            return;
        Log.d(TAG, "jing mo an zhuang  out");
        installFlags |= PackageManager.INSTALL_REPLACE_EXISTING;
        PackageManager pm = context.getPackageManager();
        IPackageInstallObserver observer = new MyPakcageInstallObserver(context, pakcageName, isopen);
        pm.installPackage(Uri.fromFile(file), observer, installFlags, pakcageName);
    }

    //静默卸载
    public static void uninstallApkDefaul(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        IPackageDeleteObserver observer = new MyPackageDeleteObserver(context);
        pm.deletePackage("com.ktc.launcher", observer, 4);
        Log.d("fengjw", "uninstallApkDefaul");
//        String appPackage = "com.mstar.tv.tvplayer.ui";
//        Intent intent = new Intent(context, context.getClass());
//        PendingIntent sender = PendingIntent.getActivity(context, 0, intent, 0);
//        PackageInstaller mPackageInstaller = context.getPackageManager().getPackageInstaller();
//        mPackageInstaller.uninstall(appPackage, sender.getIntentSender());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    // Process process = pb.command("rm -r system/app/TVAsistant_1.0_180429_0");
////            String[] cmd = {"chmod 06755 /system/xbin/su", "su", "mount -o remount,rw /system","rm -r TVAsistant_1.0_180429_0"};
////            Log.d("fengjw", run(cmd, "/system/app"));
////            String[] cmd = {"su;", "mount", "-o", "remount,rw", "/system;", "rm", "-r", "system/app/MTvPlayer/MTvPlayer.apk"};
////            String[] cmd1 = {"rm", "-r", "system/app/MTvPlayer/MTvPlayer.apk"};
//                    //run(cmd1, "/system/app");
////            String[] str3 = {"su"};
////            Log.d("fengjw", "exec : " + exec(str3));
////            String[] str1 = {"mount", "-o", "remount,rw", "/system"};
////            Log.d("fengjw", "str1 : " + exec(str1));
////            String[] str2 = {"rm", "-r", "system/app/MTvPlayer/MTvPlayer.apk"};
////            Log.d("fengjw", "exec : " + exec(str2));
////            Log.d("fengjw", "uninstallApkDefaul");
////            Runtime.getRuntime().exec("chmod 444 system/app/MTvTest");
//                    Log.d("fengjw", "isRoot : " + isRoot());
////                    Process localProcess = Runtime.getRuntime().exec("su");
////                    Log.d("fengjw", "getErrorStream : " + localProcess.getErrorStream().toString());
////                    Log.d("fengjw", "getInputStream : " + localProcess.getInputStream());
////                    Log.d("fengjw", "getOutputStream : " + localProcess.getOutputStream());
//                    String[] commands = {"reboot", "mount -o remount,rw /system", "rm -r /system/app/MTvFactory"};
////            //rm("/system/app/SystemUpdateClient");
////            //String str2 = "rm -r system/app/TVAsistant_1.0_180429_0\n";
//////            String str3 = "/system/bin/busybox chomd 444 system/app/MTvProvider\r";
//////            String str = "/system/bin/busybox chmod -o remount,rw /system\r/system/bin/busybox rm -rf" + " " + "-r" + " " + "/system/app/MTvProvider\r";
//                    OutputStream localOutputStream = localProcess.getOutputStream();
//                    DataOutputStream localDataOutputStream = new DataOutputStream(localOutputStream);
//                    int length = commands.length;
//                    for (int i = 0; i < length; i ++){
//                        localDataOutputStream.writeBytes(commands[i] + "\n");
//                    }
//                    localDataOutputStream.writeBytes("exit\n");
//                    localDataOutputStream.flush();
//                    localProcess.waitFor();
////            Runtime.getRuntime().exec("chmod 06755 /system/xbin/su");
////            Runtime runtime = Runtime.getRuntime();
////            Process process = runtime.exec("su");
////            Log.d("fengjw", "su : " + process.getErrorStream());
////            DataOutputStream os = new DataOutputStream(process.getOutputStream());
////            os.writeBytes("reboot\n");
////            Log.d("fengjw", "reboot : " + process.getErrorStream());
////            os.flush();
//                }catch (Exception e){
//                    e.printStackTrace();
//                    Log.d("fengjw", "error : " + e.getMessage());
//                    Log.d("fengjw", "Exception : " + e.toString());
//                }finally {
//
//                }
//            }
//        }).start();
    }

    public static boolean isRoot(){
        boolean bool = false;
        try {
            if ((!new File("/system/xbin/su").exists()) && (!new File("/system/xbin/su").exists())){
                bool = false;
            }else {
                bool = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bool;
    }

    public static void rm(String path)
    {
        String cmd = "/system/bin/busybox rm -rf" + " " + "-r" + " " + path;

        String s;
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);

            BufferedReader buff = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            while ((s = buff.readLine()) != null) {
//                System.out.println(s);
                Log.d("fengjw", "s : " + s);
                process.waitFor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String exec(String[] args) {

        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            baos.write('n');
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            byte[] data = baos.toByteArray();
            result = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (errIs != null) {
                    errIs.close();
                }
                if (inIs != null) {
                    inIs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        System.out.println(result);
        return result;
    }

    public static String run(String [] cmd, String workDirectory) throws IOException{
        StringBuffer result = new StringBuffer();
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            InputStream in = null;
            if (workDirectory != null){
                builder.directory(new File(workDirectory));
                builder.redirectErrorStream(true);
                Process process = builder.start();

                in = process.getInputStream();
                byte[] re = new byte[1024];
                while (in.read(re) != -1){
                    result = result.append(new String(re));
                }
            }

            if (in != null){
                in.close();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d("fengjw", "Exception : " + e.getMessage());
        }
        return result.toString();
    }


    //2秒后回馈结果
    private static void sendResultCode(Context context, int returnCode,
                                       String packageName) {
        Intent mResultIntent = new Intent(EXCUTOR_RESULT);
        mResultIntent.putExtra("result", returnCode);
        mResultIntent.putExtra("package", packageName);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Service.ALARM_SERVICE);
        PendingIntent pendingIntentExprie = PendingIntent.getBroadcast(context,
                0, mResultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC,
                System.currentTimeMillis() + 2 * 1000, pendingIntentExprie);
    }


    //静默安装回调
    private static class MyPakcageInstallObserver extends IPackageInstallObserver.Stub {
        private static final PackageManager Intent = null;
        Context context;
        String packageName;
        boolean isOpen;

        MyPakcageInstallObserver(Context conext, String packageName, boolean isopen) {
            this.context = conext;
            this.packageName = packageName;
            this.isOpen = isopen;
        }

        @Override
        public void packageInstalled(String packageName, int returnCode)
                throws RemoteException {
            Log.i(TAG, "returnCode = " + returnCode);//返回1代表安装成功
            sendResultCode(context,returnCode, packageName);
            if (isOpen) {
                // 启动目标应用
                PackageManager packManager = context.getPackageManager();
                // 这里的packageName就是从上面得到的目标apk的包名
                Intent resolveIntent = packManager.getLaunchIntentForPackage(packageName);
                context.startActivity(resolveIntent);
            }
        }
    }

    //静默卸载回调
    private static class MyPackageDeleteObserver extends IPackageDeleteObserver.Stub {
        Context context;
        MyPackageDeleteObserver(Context context){
            this.context = context;
        }
        @Override
        public void packageDeleted(String packageName, int returnCode) {
            Log.d(TAG, "returnCode = " + returnCode);//返回1代表卸载成功
            sendResultCode(context,returnCode, packageName);
        }

    }
}