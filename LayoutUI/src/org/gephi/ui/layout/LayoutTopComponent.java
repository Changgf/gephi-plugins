/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.ui.layout;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.gephi.layout.api.Layout;
import org.gephi.layout.api.LayoutBuilder;
import org.gephi.layout.api.LayoutController;
import org.gephi.layout.api.LayoutControllerObserver;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.ChoiceView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
final class LayoutTopComponent extends TopComponent
    implements LayoutControllerObserver, ExplorerManager.Provider {

    private static LayoutTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "LayoutTopComponent";
    private LayoutController layoutController;
    private Action requestPlayAction;
    private Action requestStopAction;
    private Action chooseLayoutAction;
    private final ExplorerManager mgr = new ExplorerManager();

    private LayoutTopComponent() {
        initActions();
        initLayoutController();
        initComponents();
        mgr.setRootContext(new AbstractNode(new MyChildren()));
        setName(NbBundle.getMessage(LayoutTopComponent.class, "CTL_LayoutTopComponent"));
        setToolTipText(NbBundle.getMessage(LayoutTopComponent.class, "HINT_LayoutTopComponent"));
//        setIcon(Utilities.loadImage(ICON_PATH, true));
        putClientProperty("netbeans.winsys.tc.keep_preferred_size_when_slided_in", Boolean.TRUE);
    }

    private void initActions() {
        requestPlayAction = new RequestPlayAction();
        requestStopAction = new RequestStopAction();
        chooseLayoutAction = new ChooseLayoutAction();
    }

    private void initLayoutController() {
        layoutController = Lookup.getDefault().lookup(LayoutController.class);
        layoutController.addObserver(this);
    }

    class MyChildren extends Children.Keys {

        public MyChildren() {
        }

        @Override
        protected Node[] createNodes(Object o) {
            LayoutBuilder obj = (LayoutBuilder) o;
            AbstractNode result = new AbstractNode(new MyChildren(), Lookups.singleton(obj));
            result.setDisplayName(obj.toString());
            return new Node[]{result};
        }

        protected void addNotify() {
            setKeys(layoutController.getLayouts().toArray());
        }
    }

    private void initLayoutComboBox() {
        layoutComboBox.setAction(chooseLayoutAction);
        List<LayoutBuilder> layouts = layoutController.getLayouts();
        System.out.println("layouts: " + layouts.size());
        for (LayoutBuilder layoutBuilder : layouts) {
            layoutComboBox.addItem(new LayoutBuilderWrapper(layoutBuilder));
            System.out.println(layoutBuilder.getClass().getName());
            System.out.println(layoutBuilder.getName());
            System.out.println(layoutBuilder.getDescription() + "\n");
        }
        chooseLayoutAction.actionPerformed(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layoutComboBox = new javax.swing.JComboBox();
        topLabel = new javax.swing.JLabel();
        playButton = new javax.swing.JButton(requestPlayAction);
        stopButton = new javax.swing.JButton(requestStopAction);
        jComboBox1 = new ChoiceView();

        layoutComboBox.setFont(new java.awt.Font("Tahoma", 1, 14));
        initLayoutComboBox();

        org.openide.awt.Mnemonics.setLocalizedText(topLabel, org.openide.util.NbBundle.getMessage(LayoutTopComponent.class, "LayoutTopComponent.topLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(playButton, org.openide.util.NbBundle.getMessage(LayoutTopComponent.class, "LayoutTopComponent.playButton.text")); // NOI18N
        playButton.setToolTipText(org.openide.util.NbBundle.getMessage(LayoutTopComponent.class, "LayoutTopComponent.playButton.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(stopButton, org.openide.util.NbBundle.getMessage(LayoutTopComponent.class, "LayoutTopComponent.stopButton.text")); // NOI18N
        stopButton.setToolTipText(org.openide.util.NbBundle.getMessage(LayoutTopComponent.class, "LayoutTopComponent.stopButton.toolTipText")); // NOI18N
        stopButton.setActionCommand(org.openide.util.NbBundle.getMessage(LayoutTopComponent.class, "LayoutTopComponent.stopButton.actionCommand")); // NOI18N
        stopButton.setEnabled(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(layoutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(topLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layoutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(stopButton))
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox layoutComboBox;
    private javax.swing.JButton playButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized LayoutTopComponent getDefault() {
        if (instance == null) {
            instance = new LayoutTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the LayoutTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized LayoutTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(LayoutTopComponent.class.getName()).warning(
                "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof LayoutTopComponent) {
            return (LayoutTopComponent) win;
        }
        Logger.getLogger(LayoutTopComponent.class.getName()).warning(
            "There seem to be multiple components with the '" + PREFERRED_ID +
            "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    public void executeEvent() {
        stopButton.setEnabled(true);
        playButton.setEnabled(false);
    }

    public void stopEvent() {
        stopButton.setEnabled(false);
        playButton.setEnabled(true);
    }

    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    final static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return LayoutTopComponent.getDefault();
        }
    }

    class RequestPlayAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            layoutController.executeLayout();
        }
    }

    class RequestStopAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            layoutController.stopLayout();
        }
    }

    class ChooseLayoutAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            LayoutBuilderWrapper selected = (LayoutBuilderWrapper) layoutComboBox.getSelectedItem();
            Layout layout = selected.getLayoutBuilder().buildLayout();
            System.out.println(layout.getClass().getName());
            layoutController.setLayout(layout);
        }
    }
}

class LayoutBuilderWrapper {

    private LayoutBuilder layoutBuilder;

    public LayoutBuilderWrapper(LayoutBuilder layoutBuilder) {
        this.layoutBuilder = layoutBuilder;
    }

    public LayoutBuilder getLayoutBuilder() {
        return layoutBuilder;
    }

    @Override
    public String toString() {
        return layoutBuilder.getName();
    }
}
