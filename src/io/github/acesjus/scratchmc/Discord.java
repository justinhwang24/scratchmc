package io.github.acesjus.scratchmc;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Discord extends ListenerAdapter implements Listener {
    public Main plugin;
    public JDA jda;
    public Discord(Main main) {
        this.plugin = main;
        startBot();
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
        jda.addEventListener(this);
    }
    private void startBot() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(plugin.getConfig().getString("Bot.Token")).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e){
        String message = e.getMessage();
        TextChannel textChannel = jda.getTextChannelsByName("general-tsc",true).get(0);
        textChannel.sendMessage("**"+e.getPlayer().getName()+":** "+message).queue();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot() || event.getAuthor().isFake() || event.isWebhookMessage())return;
        String message = event.getMessage().getContentRaw();
        User user = event.getAuthor();
        Bukkit.broadcastMessage("§9§l"+user.getName()+"#"+user.getDiscriminator()+": §e"+message);
    }
}