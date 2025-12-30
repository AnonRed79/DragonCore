package net.anonhub.dragonCore.engine.interfaces;

import java.util.ArrayList;

public interface ICommand {
    boolean runCommand(ArrayList<String> arguments);
}
