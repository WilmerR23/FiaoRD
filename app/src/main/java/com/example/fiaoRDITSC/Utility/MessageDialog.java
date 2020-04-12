package com.example.fiaoRDITSC.Utility;

public class MessageDialog {

    public String Title, Message, PositiveButtonMessage, NegativeButtonMessage;

    public MessageDialog(String title, String message, String positiveButtonMessage, String negativeButtonMessage) {
        Title = title;
        Message = message;
        PositiveButtonMessage = positiveButtonMessage;
        NegativeButtonMessage = negativeButtonMessage;
    }
}
