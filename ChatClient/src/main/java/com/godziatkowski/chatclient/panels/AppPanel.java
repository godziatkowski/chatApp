package com.godziatkowski.chatclient.panels;

import com.godziatkowski.chatclient.event.ChannelsLoadedEvent;
import com.godziatkowski.chatclient.event.MessageOnChannelEvent;
import com.godziatkowski.chatclient.service.CommunicationService;
import com.godziatkowski.chatclient.service.Statics;
import com.godziatkowski.chatprotocol.ChannelData;
import com.godziatkowski.chatprotocol.Technology;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.plaf.TabbedPaneUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppPanel extends javax.swing.JPanel implements ApplicationListener<ApplicationEvent> {

    private ApplicationEventPublisher publisher;
    private final CommunicationService communicationService;
    private Map<String, ChannelData> channels;
    private List<String> channelNames;
    private final Map<Long, ChatPanel> chatPanels = new HashMap<>();

    @Autowired
    public AppPanel(ApplicationEventPublisher publisher, CommunicationService communicationService) {
        this.communicationService = communicationService;
        this.publisher = publisher;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        burlapRadio = new javax.swing.JRadioButton();
        hessianRadio = new javax.swing.JRadioButton();
        xmlRpcRadio = new javax.swing.JRadioButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        channelList = new javax.swing.JList<>();
        joinChannelButton = new javax.swing.JButton();
        createChannelButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        messageWindow = new javax.swing.JTabbedPane();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        buttonGroup1.add(burlapRadio);
        burlapRadio.setSelected(true);
        burlapRadio.setText("Burlap");
        burlapRadio.setFocusable(false);
        burlapRadio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        burlapRadio.setMaximumSize(new java.awt.Dimension(80, 39));
        burlapRadio.setMinimumSize(new java.awt.Dimension(80, 39));
        burlapRadio.setPreferredSize(new java.awt.Dimension(80, 39));
        burlapRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burlapRadioActionPerformed(evt);
            }
        });
        jToolBar1.add(burlapRadio);

        buttonGroup1.add(hessianRadio);
        hessianRadio.setText("Hessian");
        hessianRadio.setFocusable(false);
        hessianRadio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        hessianRadio.setMaximumSize(new java.awt.Dimension(80, 39));
        hessianRadio.setMinimumSize(new java.awt.Dimension(80, 39));
        hessianRadio.setPreferredSize(new java.awt.Dimension(80, 39));
        hessianRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hessianRadioActionPerformed(evt);
            }
        });
        jToolBar1.add(hessianRadio);

        buttonGroup1.add(xmlRpcRadio);
        xmlRpcRadio.setText("XML-RPC");
        xmlRpcRadio.setFocusable(false);
        xmlRpcRadio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        xmlRpcRadio.setMaximumSize(new java.awt.Dimension(80, 39));
        xmlRpcRadio.setMinimumSize(new java.awt.Dimension(80, 39));
        xmlRpcRadio.setPreferredSize(new java.awt.Dimension(80, 39));
        xmlRpcRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlRpcRadioActionPerformed(evt);
            }
        });
        jToolBar1.add(xmlRpcRadio);

        jSplitPane1.setResizeWeight(0.1);

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 100));

        channelList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(channelList);
        setListener(channelList);

        joinChannelButton.setText("Join Channel");
        joinChannelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinChannelButtonActionPerformed(evt);
            }
        });

        createChannelButton.setText("Create Channel");
        createChannelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createChannelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createChannelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(joinChannelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(joinChannelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(createChannelButton))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        messageWindow.setMinimumSize(new java.awt.Dimension(150, 5));
        messageWindow.setPreferredSize(new java.awt.Dimension(150, 5));
        messageWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageWindowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(messageWindow, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(messageWindow, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void burlapRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burlapRadioActionPerformed
        Statics.setSelectedTechnology(Technology.BURLAP);
    }//GEN-LAST:event_burlapRadioActionPerformed

    private void hessianRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hessianRadioActionPerformed
        Statics.setSelectedTechnology(Technology.HESSIAN);
    }//GEN-LAST:event_hessianRadioActionPerformed

    private void createChannelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createChannelButtonActionPerformed
        createChannel();
    }//GEN-LAST:event_createChannelButtonActionPerformed

    private void joinChannelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinChannelButtonActionPerformed
        joinChannel();
    }//GEN-LAST:event_joinChannelButtonActionPerformed

    private void messageWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_messageWindowMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int tabNr = ((TabbedPaneUI) messageWindow.getUI()).tabForCoordinate(messageWindow, evt.getX(), evt.getY());
            ChatPanel chatPanel = (ChatPanel) messageWindow.getComponentAt(tabNr);
            leaveChannel(chatPanel);
        }
    }//GEN-LAST:event_messageWindowMouseClicked

    private void xmlRpcRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlRpcRadioActionPerformed
        Statics.setSelectedTechnology(Technology.XML_RPC);
    }//GEN-LAST:event_xmlRpcRadioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton burlapRadio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JList<String> channelList;
    private javax.swing.JButton createChannelButton;
    private javax.swing.JRadioButton hessianRadio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton joinChannelButton;
    private javax.swing.JTabbedPane messageWindow;
    private javax.swing.JRadioButton xmlRpcRadio;
    // End of variables declaration//GEN-END:variables

    public void loadChannels() {
        updateChannels(communicationService.loadChannels());
    }

    private void updateChannels(List<ChannelData> channelData) {
        channels = channelData
                .stream()
                .collect(Collectors.toMap(ChannelData::getName, channel -> channel));
        channelNames = channels.keySet()
                .stream()
                .collect(Collectors.toList());

        channelList.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return channels.size();
            }

            @Override
            public String getElementAt(int index) {
                return channelNames.get(index);
            }
        });
    }

    private void createChannel() {
        String channelName = JOptionPane.showInputDialog(this, "Podaj nazwę dla nowego kanału", null);
        if (channelName != null && !channelName.isEmpty()) {
            ChannelData channelData = communicationService.createChannel(channelName);
            openChannelWindow(channelData);
        }
        loadChannels();
    }

    private void joinChannel() {
        int selectedIndex = channelList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedChannelName = channelNames.get(selectedIndex);
            ChannelData channelData = channels.get(selectedChannelName);
            if (!chatPanels.keySet().contains(channelData.getId())) {
                communicationService.joinChannel(channelData.getId());
                openChannelWindow(channelData);
            }
        }
    }

    private void leaveChannel(ChatPanel chatPanel) {
        communicationService.leaveChannel(chatPanel.getChannelData().getId());
        messageWindow.remove(chatPanel);
        chatPanels.remove(chatPanel.getChannelData().getId());
    }

    private void openChannelWindow(ChannelData channelData) {
        ChatPanel chatPanel = new ChatPanel(channelData, communicationService);
        chatPanels.put(channelData.getId(), chatPanel);
        messageWindow.addTab(channelData.getName(), chatPanel);
        messageWindow.setSelectedComponent(chatPanel);
    }

    private void onMessageOnChannelEvent(MessageOnChannelEvent event) {
        chatPanels.get(event.getChannelId()).setText(event.getMessages());
    }

    private void onChannelsLoadedEvent(ChannelsLoadedEvent event) {
        updateChannels(event.getChannels());
    }

    private void setListener(JList<String> channelList) {
        channelList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    joinChannel();
                }
            }
        });
    }

    public void logout() {
        communicationService.logout();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event.getClass().equals(MessageOnChannelEvent.class)) {
            onMessageOnChannelEvent((MessageOnChannelEvent) event);
        } else if (event.getClass().equals(ChannelsLoadedEvent.class)) {
            onChannelsLoadedEvent((ChannelsLoadedEvent) event);
        }
    }
}
