package com.vladbudan.restapp.controller.command;

import com.vladbudan.restapp.controller.command.impl.CreateUser;
import com.vladbudan.restapp.controller.command.impl.DeleteUser;
import com.vladbudan.restapp.controller.command.impl.ShowUser;
import com.vladbudan.restapp.controller.command.impl.UpdateUser;

import java.util.HashMap;
import java.util.Map;

public class UsersCommandProvider {
    private Map<String, UsersCommand> commands = new HashMap<>();

    public UsersCommandProvider() {

        commands.put("show", new ShowUser());
        commands.put("create", new CreateUser());
        commands.put("update", new UpdateUser());
        commands.put("delete", new DeleteUser());

    }

    public UsersCommand getCommand(String commandName) {
        return commands.get(commandName);
    }

}
