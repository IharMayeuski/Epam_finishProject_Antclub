package by.epam.club.entity;

import java.util.List;

public class Letter extends Entity  {
    private long id;
    private String title;
    private String text;
    private String banned;
    private String deleted;

    private long fromUser;
    private long toUser;
}