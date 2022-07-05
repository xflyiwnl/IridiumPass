package me.xflyiwnl.iridiumpass.util;

import me.xflyiwnl.iridiumpass.config.Config;
import me.xflyiwnl.iridiumpass.manager.PassManager;
import me.xflyiwnl.iridiumpass.pass.Pass;

import java.io.IOException;
import java.util.UUID;

public class PassUtil {

    public static void savePass() {

        for (Pass pass : PassManager.getPassArray()) {

            Config.getDatabaseYaml().set("database." + pass.getUUID() + ".name", pass.getName());
            Config.getDatabaseYaml().set("database." + pass.getUUID() + ".surname", pass.getSurname());
            Config.getDatabaseYaml().set("database." + pass.getUUID() + ".age", pass.getAge());
            Config.getDatabaseYaml().set("database." + pass.getUUID() + ".vk", pass.getVk());
            Config.getDatabaseYaml().set("database." + pass.getUUID() + ".discord", pass.getDiscord());

            try {
                Config.getDatabaseYaml().save(Config.getDatabaseFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void loadPass() {

        if (!Config.getDatabaseYaml().isConfigurationSection("database")) {
            return;
        }

        for (String id : Config.getDatabaseYaml().getConfigurationSection("database").getKeys(false)) {

            UUID uuid = UUID.fromString(id);
            String name = Config.getDatabaseYaml().getString("database." + id + ".name");
            String surname = Config.getDatabaseYaml().getString("database." + id + ".surname");
            Integer age = Config.getDatabaseYaml().getInt("database." + id + ".age");
            String vk = Config.getDatabaseYaml().getString("database." + id + ".vk");
            String discord = Config.getDatabaseYaml().getString("database." + id + ".discord");

            PassManager.getPassArray().add(new Pass(uuid, age, name, surname, vk, discord));
        }

    }

}
