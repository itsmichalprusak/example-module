package moe.kyokobot.example.commands;

import moe.kyokobot.bot.command.Command;
import moe.kyokobot.bot.command.CommandCategory;
import moe.kyokobot.bot.command.CommandContext;
import org.jetbrains.annotations.NotNull;

public class HelloCommand extends Command {
    public HelloCommand() {
        name = "hello";
        category = CommandCategory.FUN;
    }

    @Override
    public void execute(@NotNull CommandContext context) {
        context.send("Hello, " + context.getSender().getName());
    }
}
