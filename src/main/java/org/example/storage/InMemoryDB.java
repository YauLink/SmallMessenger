package org.example.storage;

import org.example.models.User;
import org.example.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDB {

    public static Map<String, User> users = new HashMap<>();
    public static List<Message> messages = new ArrayList<>();

}
