package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardAction extends CommonHelper{
    private static Logger log= LogManager.getLogger(ClipboardAction.class.getName());
    /**
     * Sets the given value as the content of the system clipboard.
     * @param value The value to set in the clipboard.
     */
    public static void setClipBoardData(String value){
        StringSelection selection = new StringSelection(value);
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipBoard.setContents(selection, selection);
    }
    /**
     * Retrieves the content from the system clipboard.
     * @return The content retrieved from the clipboard.
     */
    public static String getDataFromClipBoard(){
        String data = "";
        try {
          data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
