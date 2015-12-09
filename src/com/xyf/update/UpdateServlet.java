package com.xyf.update;

import com.xyf.update.model.UpdateConfigBean;
import com.xyf.update.model.UpdateRequestBean;
import com.xyf.update.model.UpdateResponseBean;
import com.xyf.update.utils.ConfigUtils;
import com.xyf.update.utils.JsonUtils;
import com.xyf.update.utils.LogUtils;
import com.xyf.update.utils.Streamutils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by shxiayf on 2015/12/8.
 */
public class UpdateServlet extends HttpServlet {

    private static final String TAG = UpdateServlet.class.getName();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String content = Streamutils.parseStream2String(req.getInputStream());

        UpdateRequestBean requestBean = (UpdateRequestBean) JsonUtils.parseString2Obj(content,UpdateRequestBean.class);

        String configFilePath = ConfigUtils.getInstances().getWebINFPath() + "WEB-INF" + File.separator + "update.config";
        File configFile = new File(configFilePath);

        if (!configFile.exists())
        {
            responseNoUpdate(resp);
            return;
        }

        String configContent = Streamutils.parseStream2String(new FileInputStream(configFile));
        UpdateConfigBean configBean = (UpdateConfigBean) JsonUtils.parseString2Obj(configContent,UpdateConfigBean.class);
        LogUtils.i(TAG,String.valueOf(configBean.getPackagename().compareToIgnoreCase(requestBean.getVersion())));
        LogUtils.i(TAG,String.valueOf(configBean.getPackagename().equals(requestBean.getPackageName())));
        if (configBean.getPackagename().compareToIgnoreCase(requestBean.getVersion()) > 0 && configBean.getPackagename().equals(requestBean.getPackageName()))
        {
            String apkPath = ConfigUtils.getInstances().getWebINFPath() + File.separator + configBean.getPath();

            File apkFile = new File(apkPath);

            if (!apkFile.exists())
            {
                responseNoUpdate(resp);
                return;
            }

            UpdateResponseBean responseBean = new UpdateResponseBean();
            String respContent = "";

            responseBean.setIsUpdate(1);
            responseBean.setFilesize(apkFile.length());
            responseBean.setUrl("http://192.168.0.77:8080/" + configBean.getPath());
            responseBean.setCanNotShowDialog(configBean.getCanNotShowDialog());

            respContent = JsonUtils.parseObj2String(responseBean);

            resp.getWriter().write(respContent);
            resp.getWriter().flush();
            resp.getWriter().close();

            return;
        }

    }

    private void responseNoUpdate(HttpServletResponse resp) throws IOException {
        UpdateResponseBean responseBean = new UpdateResponseBean();
        String respContent = "";

        responseBean.setIsUpdate(0);
        responseBean.setFilesize(0);
        responseBean.setUrl("");
        responseBean.setCanNotShowDialog(1);

        respContent = JsonUtils.parseObj2String(responseBean);

        resp.getWriter().write(respContent);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LogUtils.i(TAG,"get accessed");
    }
}
