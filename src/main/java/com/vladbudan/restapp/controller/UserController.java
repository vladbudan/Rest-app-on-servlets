package com.vladbudan.restapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladbudan.restapp.model.Cat;
import com.vladbudan.restapp.model.Dog;
import com.vladbudan.restapp.model.Pet;
import com.vladbudan.restapp.model.User;
import com.vladbudan.restapp.repository.impl.CatRepositoryImpl;
import com.vladbudan.restapp.repository.impl.DogRepositoryImpl;
import com.vladbudan.restapp.repository.impl.PetRepositoryImpl;
import com.vladbudan.restapp.repository.impl.UserRepositoryImpl;
import com.vladbudan.restapp.service.PetService;
import com.vladbudan.restapp.service.UserService;
import com.vladbudan.restapp.service.impl.PetServiceImpl;
import com.vladbudan.restapp.service.impl.UserServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "UserController", urlPatterns = "/users/*")
public class UserController extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private final UserService userService;
    private final PetService petService;

    public UserController() {
        userService = new UserServiceImpl(new UserRepositoryImpl());
        petService = new PetServiceImpl(new PetRepositoryImpl(), new CatRepositoryImpl(), new DogRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getPathInfo();
            String id = request.getParameter("id");
            if (action == null) {
                if (id == null) {
                    getAllUsers(request, response);
                } else {
                    getUser(request, response);
                }
            } else if(action.endsWith("/cats")) {
                if (id == null) {
                    getAllCats(request, response);
                } else {
                    getCat(request, response);
                }
            } else if(action.endsWith("/dogs")) {
                if(id == null) {
                    getAllDogs(request, response);
                } else {
                    getDog(request, response);
                }
            } else if(action.endsWith("/pets")) {
                getAllPets(request, response);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getPathInfo();
            if (action == null) {
                createUser(request, response);
            } else if (action.endsWith("/cats")) {
                createCat(request, response);
            } else if (action.endsWith("/dogs")) {
                createDog(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getPathInfo();
            if (action == null) {
                updateUser(request, response);
            } else if (action.endsWith("/cats")) {
                updateCat(request, response);
            } else if (action.endsWith("/dogs")) {
                updateDog(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            deleteUser(request, response);
        } else if (action.endsWith("/cats")) {
            deleteCat(request, response);
        } else if (action.endsWith("/dogs")) {
            deleteDog(request, response);
        }
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        User user = null;
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            user = userService.getById(userId);
            String userString = objectMapper.writeValueAsString(user);

            PrintWriter out = response.getWriter();
            out.print(userString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        }
    }

    private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        List<User> users = null;
        try {
            users = userService.getAllUser();
            String userString = objectMapper.writeValueAsString(users);

            PrintWriter out = response.getWriter();
            out.print(userString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (users == null) {
            throw new IllegalArgumentException("Users failed!");
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        if (json.containsKey("firstName")) {
            System.out.println(json.get("firstName"));
        }
        if (json.containsKey("lastName")) {
            System.out.println(json.get("lastName"));
        }

        User user = new User();
        user.setFirstName((String) json.get("firstName"));
        user.setLastName((String) json.get("lastName"));

        userService.save(user);

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        if (json.containsKey("firstName")) {
            System.out.println(json.get("firstName"));
        }
        if (json.containsKey("lastName")) {
            System.out.println(json.get("lastName"));
        }

        User user = userService.getById(id);
        user.setFirstName((String) json.get("firstName"));
        user.setLastName((String) json.get("lastName"));

        userService.update(user);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
    }

    private void getCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        User user = null;
        Pet cat = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]);
            user = userService.getById(userId);

            int catId = Integer.parseInt(request.getParameter("id"));
            cat = petService.getById(catId);
            String catString = objectMapper.writeValueAsString(cat);

            PrintWriter out = response.getWriter();
            out.print(catString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        } else if (cat == null) {
            throw new IllegalArgumentException("cat with such id not found!");
        }
    }

    private void getAllCats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/cats
        User user = null;
        List<Cat> cats = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]); // {value}
            user = userService.getById(userId);

            cats = petService.getAllCats();
            String catsString = objectMapper.writeValueAsString(cats);

            PrintWriter out = response.getWriter();
            out.print(catsString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        } else if (cats == null) {
            throw new IllegalArgumentException("cats with such id not found!");
        }
    }

    private void createCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/cats
        User user = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]); // {value}
            user = userService.getById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        }

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(collect);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (json.containsKey("name")) {
            System.out.println(json.get("name"));
        }
        if (json.containsKey("gender")) {
            System.out.println(json.get("gender"));
        }

        Cat cat = new Cat();
        cat.setName((String) json.get("name"));
        cat.setGender((String) json.get("gender"));
        cat.setUser(user);

        petService.addCat(cat);
    }

    private void updateCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        if (json.containsKey("name")) {
            System.out.println(json.get("name"));
        }
        if (json.containsKey("gender")) {
            System.out.println(json.get("gender"));
        }

        Cat cat = petService.getByIdCat(id);
        cat.setName((String) json.get("name"));
        cat.setGender((String) json.get("gender"));

        petService.update(cat);
    }

    private void deleteCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        petService.delete(id);
    }

    private void getDog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/dogs
        User user = null;
        Pet dog = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]); // {value}
            user = userService.getById(userId);

            int dogId = Integer.parseInt(request.getParameter("id"));
            dog = petService.getById(dogId);
            String catString = objectMapper.writeValueAsString(dog);

            PrintWriter out = response.getWriter();
            out.print(catString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        } else if (dog == null) {
            throw new IllegalArgumentException("dog with such id not found!");
        }

    }

    private void getAllDogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/cats
        User user = null;
        List<Dog> dogs = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]); // {value}
            user = userService.getById(userId);

            dogs = petService.getAllDogs();
            String dogsString = objectMapper.writeValueAsString(dogs);

            PrintWriter out = response.getWriter();
            out.print(dogsString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        } else if (dogs == null) {
            throw new IllegalArgumentException("cats with such id not found!");
        }
    }

    private void createDog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/dogs
        User user = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]); // {value}
            user = userService.getById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        }

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(collect);

        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(collect);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (json.containsKey("name")) {
            System.out.println(json.get("name"));
        }
        if (json.containsKey("color")) {
            System.out.println(json.get("color"));
        }

        Dog dog = new Dog();
        dog.setName((String) json.get("name"));
        dog.setColor((String) json.get("color"));
        dog.setUser(user);

        petService.addDog(dog);
    }

    private void updateDog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));

        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(collect);

        if (json.containsKey("name")) {
            System.out.println(json.get("name"));
        }
        if (json.containsKey("color")) {
            System.out.println(json.get("color"));
        }

        Dog dog = petService.getByIdDog(id);
        dog.setName((String) json.get("name"));
        dog.setColor((String) json.get("color"));

        petService.update(dog);
    }

    private void deleteDog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        petService.deleteDog(id);
    }

    private void getAllPets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/cats
        User user = null;
        List<Dog> dogs = null;
        List<Cat> cats = null;
        try {
            String[] pathParts = pathInfo.split("/");
            int userId = Integer.parseInt(pathParts[1]); // {value}
            user = userService.getById(userId);

            dogs = petService.getAllDogs();
            cats = petService.getAllCats();
            String dogsString = objectMapper.writeValueAsString(dogs);
            String catsString = objectMapper.writeValueAsString(cats);

            PrintWriter out = response.getWriter();
            out.print(dogsString);
            out.print(catsString);
            out.flush();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            throw new IllegalArgumentException("user with such id not found!");
        } else if (dogs == null) {
            throw new IllegalArgumentException("dogs with such id not found!");
        } else if (cats == null) {
            throw new IllegalArgumentException("cats with such id not found!");
        }
    }

}
