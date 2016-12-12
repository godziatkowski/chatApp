package com.godziatkowski.chatclient.panels;

import com.godziatkowski.chatclient.service.CommunicationService;
import com.godziatkowski.chatclient.service.Statics;
import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.Message;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class ChatPanel extends javax.swing.JPanel {

    private final StringBuilder messagesBuilder;
    private final ChannelData channelData;
    private final CommunicationService communicationService;

    public ChatPanel(ChannelData channelData, CommunicationService communicationService) {
        this.channelData = channelData;
        this.communicationService = communicationService;
        this.messagesBuilder = new StringBuilder();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane3.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
            public void adjustmentValueChanged(AdjustmentEvent e) {  
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
            }
        });
        channelMessages = new javax.swing.JTextPane();

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        messageText.setColumns(20);
        messageText.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        messageText.setRows(3);
        messageText.setTabSize(4);
        messageText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                messageTextKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(messageText);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        channelMessages.setContentType("text/html"); // NOI18N
        channelMessages.setFocusable(false);
        jScrollPane3.setViewportView(channelMessages);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMessage();

    }//GEN-LAST:event_sendButtonActionPerformed

    private void messageTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageTextKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && evt.isShiftDown()) {
            sendMessage();
        }
    }//GEN-LAST:event_messageTextKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane channelMessages;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea messageText;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables

    private void sendMessage() {
        String message = messageText.getText();
        messageText.setText("");
        communicationService.sendMessage(message, channelData);
    }

    public void setText(List<Message> messages) {
        messages.stream().forEach((message) -> {
            updateMessages(message);
            channelMessages.setText("<html><body>" + messagesBuilder.toString() + "</html></body>");
        });
    }

    private void updateMessages(Message receivedMessage) {
        boolean myMessage = receivedMessage.getAuthor().equals(Statics.getLoggedUser().getUsername());
        if (myMessage) {
            messagesBuilder.append("<strong>");
        } else {
            messagesBuilder.append("<span>");
        }
        messagesBuilder.append(receivedMessage.getAuthor());
        messagesBuilder.append(":");
        if (myMessage) {
            messagesBuilder.append("</strong>");
        } else {
            messagesBuilder.append("</span>");
        }
        messagesBuilder.append("<pre>");
        messagesBuilder.append(receivedMessage.getMessage());
        messagesBuilder.append("</pre>");
        messagesBuilder.append("<hr/>");
    }

    public ChannelData getChannelData() {
        return channelData;
    }

}
