package me.xflyiwnl.iridiumpass.pass;

import java.util.UUID;

public class Pass {

    private UUID uuid; // сам игрок

    private Integer age; // возраст
    private String name; // имя
    private String surname; // фамилия

    private String vk; // вконтакте
    private String discord; // дискорд игрока

    private Boolean mask;

    public Pass (UUID uuid, Integer age, String name, String surname, String vk, String discord) {
        this.uuid = uuid;
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.vk = vk;
        this.discord = discord;

    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getVk() {
        return vk;
    }

    public String getDiscord() {
        return discord;
    }

    public Boolean getMask() {
        return mask;
    }

    public void setMask(Boolean mask) {
        this.mask = mask;
    }

}
