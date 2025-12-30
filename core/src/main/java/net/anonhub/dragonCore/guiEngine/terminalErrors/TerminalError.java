package net.anonhub.dragonCore.guiEngine.terminalErrors;

import static net.anonhub.dragonCore.engine.Settings.logger;

public class TerminalError {
    private final String msg;

    public TerminalError() {
        this.msg = this.getClass().getSimpleName();
        logger.log(this.msg);
    }
    public TerminalError(String msg) {
        this.msg = this.getClass().getSimpleName()+": "+msg;
        logger.log(this.msg);
    }


    @Override
    public String toString() {
        return msg;
    }
}
