package me.xflyiwnl.iridiumpass.manager;

import me.xflyiwnl.iridiumpass.pass.Pass;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PassManager {

    private static Set<Pass> pass = new HashSet<Pass>();

    public static Pass getPass(UUID uuid) {
        for (Pass pass : pass) {
            if (pass.getUUID().equals(uuid)) {
                return pass;
            }
        }
        return null;
    }

    public static Set<Pass> getPassArray() {
        return pass;
    }

}
