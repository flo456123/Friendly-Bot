package net.clf.common.command;

import net.clf.common.lavaplayer.GuildMusicManager;
import net.clf.common.lavaplayer.PlayerManager;
import net.clf.jda.commands.VoiceAction;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Responsible for handling the "loop-queue" command, which loops the queue.
 */
public class LoopQueueCommand extends VoiceAction {

    /**
     * Handles the logic for looping the current song that is playing.
     *
     * @param ctx the CommandContext of the command event.
     */
    @Override
    protected void handleVoice(SlashCommandInteractionEvent ctx) {
        final Guild guild = ctx.getGuild();
        assert guild != null;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        final boolean newLooping = !musicManager.getScheduler().isLooping();

        musicManager.getScheduler().setLoopingQueue(newLooping);

        ctx.replyFormat("Loop queue has been **%s**", newLooping ? "started" : "stopped").queue();
    }

    @Override
    public String getName() {
        return "loop";
    }
}
