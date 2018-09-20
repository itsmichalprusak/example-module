package moe.kyokobot.example;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import moe.kyokobot.bot.command.Command;
import moe.kyokobot.bot.manager.CommandManager;
import moe.kyokobot.bot.module.KyokoModule;
import moe.kyokobot.example.commands.HelloCommand;
import moe.kyokobot.example.commands.RegisterHooksExampleCommand;
import moe.kyokobot.example.commands.SubCommandExampleCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Module implements KyokoModule {
    private final Logger logger = LoggerFactory.getLogger(Module.class);

    @Inject
    private CommandManager commandManager;

    @Inject
    private EventBus eventBus;

    // easiest way to unregister the commands later without much code bloat
    private ArrayList<Command> commands;
    private EventHandlerExample eventHandlerExample;

    public Module() {
        commands = new ArrayList<>();
        eventHandlerExample = new EventHandlerExample();
    }

    @Override
    public void startUp() {
        // clean up the instances by creating new command array list
        commands = new ArrayList<>();

        commands.add(new HelloCommand());
        commands.add(new SubCommandExampleCommand());
        commands.add(new RegisterHooksExampleCommand());

        commands.forEach(commandManager::registerCommand);

        eventBus.register(eventHandlerExample);
    }

    @Override
    public void shutDown() {
        commands.forEach(commandManager::unregisterCommand);

        eventBus.unregister(eventHandlerExample);
    }
}