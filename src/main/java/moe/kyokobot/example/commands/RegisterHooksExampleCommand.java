package moe.kyokobot.example.commands;

import moe.kyokobot.bot.command.Command;
import moe.kyokobot.bot.command.CommandContext;

import javax.annotation.Nonnull;

public class RegisterHooksExampleCommand extends Command {
    public RegisterHooksExampleCommand() {
        name = "registerhooksexample";
    }

    @Override
    public void execute(@Nonnull CommandContext context) {
        context.send("That's just an example for command registering hooks");
    }

    @Override
    public void onRegister() {
        logger.info("Command {} registered!", name);
    }

    @Override
    public void onUnregister() {
        logger.info("Command {} unregistered!", name);
    }
}
