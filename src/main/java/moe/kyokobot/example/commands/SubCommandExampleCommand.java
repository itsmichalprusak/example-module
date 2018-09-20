package moe.kyokobot.example.commands;

import moe.kyokobot.bot.command.Command;
import moe.kyokobot.bot.command.CommandCategory;
import moe.kyokobot.bot.command.CommandContext;
import moe.kyokobot.bot.command.SubCommand;
import moe.kyokobot.bot.util.EmbedBuilder;

import java.util.concurrent.TimeUnit;

public class SubCommandExampleCommand extends Command {
    public SubCommandExampleCommand() {
        name = "subcommandexample";
        category = CommandCategory.FUN;
    }

    @Override
    public void execute(CommandContext context) {
        context.send("Available subcommands: test, meme, lol");
    }

    @SubCommand
    public void test(CommandContext context) {
        context.send("Yeah, that's a test");
    }

    @SubCommand
    public void meme(CommandContext context) {
        context.error("MEME XD");
    }

    @SubCommand
    public void lol(CommandContext context) {
        EmbedBuilder eb = context.getNormalEmbed();

        eb.setTitle("LOL");
        eb.setDescription("looooooooooool");

        context.send(eb.build(), message ->
                message.editMessage("That was weird").override(true).queueAfter(3, TimeUnit.SECONDS));
    }
}
