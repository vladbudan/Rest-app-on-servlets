package com.vladbudan.restapp.command;

import com.vladbudan.restapp.command.entityImpl.cat.CreateCat;
import com.vladbudan.restapp.command.entityImpl.dog.CreateDog;
import com.vladbudan.restapp.command.entityImpl.dog.DeleteDog;
import com.vladbudan.restapp.command.entityImpl.dog.GetAllDogs;
import com.vladbudan.restapp.command.entityImpl.dog.GetDog;
import com.vladbudan.restapp.command.entityImpl.dog.UpdateDog;
import com.vladbudan.restapp.command.entityImpl.pet.GetAllPets;
import com.vladbudan.restapp.command.entityImpl.user.CreateUser;
import com.vladbudan.restapp.command.entityImpl.cat.DeleteCat;
import com.vladbudan.restapp.command.entityImpl.user.DeleteUser;
import com.vladbudan.restapp.command.entityImpl.cat.GetAllCats;
import com.vladbudan.restapp.command.entityImpl.user.GetAllUsers;
import com.vladbudan.restapp.command.entityImpl.cat.GetCat;
import com.vladbudan.restapp.command.entityImpl.user.GetUser;
import com.vladbudan.restapp.command.entityImpl.cat.UpdateCat;
import com.vladbudan.restapp.command.entityImpl.user.UpdateUser;
import com.vladbudan.restapp.exception.ResourceNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.vladbudan.restapp.constants.Constants.DELETE;
import static com.vladbudan.restapp.constants.Constants.GET;
import static com.vladbudan.restapp.constants.Constants.POST;
import static com.vladbudan.restapp.constants.Constants.PUT;

public class CommandProvider {

    private static final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {

    }

    static {
        commands.put("get_users/id", new GetUser());
        commands.put("get_users", new GetAllUsers());
        commands.put("post_users", new CreateUser());
        commands.put("put_users/id", new UpdateUser());
        commands.put("delete_users/id", new DeleteUser());

        commands.put("get_users/id/cats/id", new GetCat());
        commands.put("get_users/id/cats", new GetAllCats());
        commands.put("post_users/id/cats", new CreateCat());
        commands.put("put_users/id/cats/id", new UpdateCat());
        commands.put("delete_users/id/cats/id", new DeleteCat());

        commands.put("get_users/id/dogs/id", new GetDog());
        commands.put("get_users/id/dogs", new GetAllDogs());
        commands.put("post_users/id/dogs", new CreateDog());
        commands.put("put_users/id/dogs/id", new UpdateDog());
        commands.put("delete_users/id/dogs/id", new DeleteDog());

        commands.put("get_users/id/pets", new GetAllPets());
    }

    public static Command getCommand(String commandName) {

        if (commandName.isEmpty() || Arrays.asList(GET, POST, PUT, DELETE).contains(commandName)) {
            throw new ResourceNotFoundException("Not found!");
        }
        return commands.get(commandName);
    }


}
