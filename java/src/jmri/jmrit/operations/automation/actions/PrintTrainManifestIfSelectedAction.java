package jmri.jmrit.operations.automation.actions;

import jmri.jmrit.operations.trains.Train;
import jmri.jmrit.operations.trains.TrainManager;

public class PrintTrainManifestIfSelectedAction extends Action {

    private static final int _code = ActionCodes.PRINT_TRAIN_MANIFEST_IF_SELECTED;

    @Override
    public int getCode() {
        return _code;
    }

    @Override
    public String getName() {
        if (TrainManager.instance().isPrintPreviewEnabled())
            return Bundle.getMessage("PreviewTrainManifestIfSelected");
        else
            return Bundle.getMessage("PrintTrainManifestIfSelected");
    }

    @Override
    public void doAction() {
        if (getAutomationItem() != null) {
            Train train = getAutomationItem().getTrain();
            if (train != null && train.isBuilt() && train.isBuildEnabled()) {
                setRunning(true);
                train.printManifest(TrainManager.instance().isPrintPreviewEnabled());
                finishAction(true);
            } else {
                finishAction(false);
            }
        }
    }

    @Override
    public void cancelAction() {
        // no cancel for this action     
    }

}
