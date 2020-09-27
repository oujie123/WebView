package com.gacrnd.gcs.webview.command;

import com.gacrnd.gcs.webview.IMainProcessorToWebViewCallBack;

import java.util.Map;

/**
 * @author Jack_Ou  created on 2020/9/25.
 */
public interface ICommand {
    String name();
    void execute(Map params, IMainProcessorToWebViewCallBack callBack);
}
