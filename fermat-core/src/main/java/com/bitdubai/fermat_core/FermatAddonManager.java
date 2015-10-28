package com.bitdubai.fermat_core;

import com.bitdubai.fermat_api.layer.all_definition.common.abstract_classes.AbstractAddon;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantPauseAddonException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantResumeAddonException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantStartAddonException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantStopAddonException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.UnexpectedServiceStatusException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.VersionNotFoundException;
import com.bitdubai.fermat_api.layer.all_definition.common.utils.AddonVersionReference;

/**
 * The class <code>com.bitdubai.fermat_core.FermatAddonManager</code>
 * centralizes all service actions of the addons in fermat.
 * <p/>
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 23/10/2015.
 */
public class FermatAddonManager {

    private final FermatSystemContext systemContext;

    public FermatAddonManager(final FermatSystemContext systemContext) {

        this.systemContext = systemContext;
    }

    public final void startAddon(final AddonVersionReference addonVersionReference) throws CantStartAddonException  ,
                                                                                           VersionNotFoundException {

        AbstractAddon abstractAddon = systemContext.getAddonVersion(addonVersionReference);

        startAddon(abstractAddon);

    }

    public final void startAddon(final AbstractAddon abstractAddon) throws CantStartAddonException {

        if (abstractAddon.isStarted())
            return;

        try {

            if (abstractAddon.isDealsWithOsContext())
                abstractAddon.setOsContext(systemContext.getOsContext());

            abstractAddon.start();
        } catch (com.bitdubai.fermat_api.CantStartPluginException e) {

            throw new CantStartAddonException(
                    e,
                    abstractAddon.getAddonVersionReference().toString(),
                    "There was a captured problem during the addon start."
            );
        } catch (Exception e) {

            throw new CantStartAddonException(
                    e,
                    abstractAddon.getAddonVersionReference().toString(),
                    "Unhandled exception trying to start the addon."
            );
        }

    }

    public final void stopAddon(final AddonVersionReference addonVersionReference) throws CantStopAddonException           ,
                                                                                          VersionNotFoundException         ,
                                                                                          UnexpectedServiceStatusException {

        AbstractAddon abstractAddon = systemContext.getAddonVersion(addonVersionReference);

        stopAddon(abstractAddon);

    }

    public final void stopAddon(final AbstractAddon abstractAddon) throws CantStopAddonException           ,
                                                                          UnexpectedServiceStatusException {

        if (!abstractAddon.isStarted()) {
            throw new UnexpectedServiceStatusException(
                    "Service Status: "+abstractAddon.getStatus()+" || "+abstractAddon.getAddonVersionReference().toString(),
                    "The addon cannot be stopped because is not started."
            );
        }

        try {

            abstractAddon.stop();

        } catch (Exception e) {
            throw new CantStopAddonException(e, abstractAddon.toString(), "Unhandled exception trying to stop the addon.");
        }
    }

    public final void pauseAddon(final AddonVersionReference addonVersionReference) throws CantPauseAddonException          ,
                                                                                           VersionNotFoundException         ,
                                                                                           UnexpectedServiceStatusException {

        AbstractAddon abstractAddon = systemContext.getAddonVersion(addonVersionReference);

        pauseAddon(abstractAddon);

    }

    public final void pauseAddon(final AbstractAddon abstractAddon) throws CantPauseAddonException          ,
                                                                           UnexpectedServiceStatusException {

        if (!abstractAddon.isStarted()) {
            throw new UnexpectedServiceStatusException(
                    "Service Status: "+abstractAddon.getStatus()+" || "+abstractAddon.getAddonVersionReference().toString(),
                    "The addon cannot be paused because is not started."
            );
        }

        try {

            abstractAddon.pause();

        } catch (Exception e) {
            throw new CantPauseAddonException(e, abstractAddon.toString(), "Unhandled exception trying to pause the addon.");
        }
    }

    public final void resumeAddon(final AddonVersionReference addonVersionReference) throws CantResumeAddonException         ,
                                                                                            VersionNotFoundException         ,
                                                                                            UnexpectedServiceStatusException {

        AbstractAddon abstractAddon = systemContext.getAddonVersion(addonVersionReference);

        resumeAddon(abstractAddon);

    }

    public final void resumeAddon(final AbstractAddon abstractAddon) throws CantResumeAddonException         ,
            UnexpectedServiceStatusException {

        if (!abstractAddon.isPaused()) {
            throw new UnexpectedServiceStatusException(
                    "Service Status: "+abstractAddon.getStatus()+" || "+abstractAddon.getAddonVersionReference().toString(),
                    "The addon cannot be resumed because is not paused."
            );
        }

        try {

            abstractAddon.pause();

        } catch (Exception e) {
            throw new CantResumeAddonException(e, abstractAddon.toString(), "Unhandled exception trying to resume the addon.");
        }
    }
}
