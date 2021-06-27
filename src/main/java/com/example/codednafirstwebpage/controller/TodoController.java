package com.example.codednafirstwebpage.controller;

import com.example.codednafirstwebpage.model.Todo;
import com.example.codednafirstwebpage.service.TodoRepository;
import com.example.codednafirstwebpage.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
//@SessionAttributes("name")
public class TodoController extends Todo{
//    @Autowired
//    TodoService service; //System repo

    @Autowired
    TodoRepository repository; //JPA repo

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        //Date-dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    @RequestMapping(value="/list-todos",method= RequestMethod.GET)
    public String showTodos(ModelMap model)
    {
        String obj = getLoggedInUserName(model);
        model.put("todos",repository.findByUser(obj));
//        model.put("todos",service.retrieveTodos("Siva"));
        return "list-todos";
    }

//    private String getLoggedInUserName(ModelMap model) {
//        return (String) model.get("name");
//    }
    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }
    @RequestMapping(value="/add-todo",method= RequestMethod.GET)
    public String showAddTodoPage(ModelMap model)
    {
        model.addAttribute("todo",new Todo(0, getLoggedInUserName(model),"",new Date(),false));
        String obj = getLoggedInUserName(model);
        return "todo";
    }

    @RequestMapping(value="/add-todo",method= RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result)
    {
        System.out.println("tODO Comingsssss");

        if(result.hasErrors()){
            return "todo";
        }
        else if(todo.getDesc().length() <=5)
        {
            model.put("errorMessage"," Description Length must be greater than or equal to 5 ");
            return "todo";
        }
        todo.setUser(getLoggedInUserName(model));
        repository.save(todo);
        System.out.println("This is date format: " + todo.getTargetDate());
//        service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(),false);
        return "redirect:/list-todos";
    }
    @RequestMapping(value="/delete-todo",method= RequestMethod.GET)
    public String deleteTodo(@RequestParam int id)
    {
//        if(id ==1) throw new RuntimeException("Wrong ID");
        System.out.println(id);
        repository.deleteById(id);
//        service.deleteTodo(id);
        return "redirect:/list-todos";
    }
    @RequestMapping(value="/update-todo",method= RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model)
    {
        System.out.println(id);
//        Todo todo = service.retrieveTodo(id);
        Optional<Todo> todo = repository.findById(id);
        System.out.println("Hitman show " +todo);
        model.put("todo",todo);
        return "todo";
    }


    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        System.out.println("Update This is date format: " + todo.getTargetDate());
        if (result.hasErrors()) {
            return "todo";
        }
        else if(todo.getDesc().length() <6)
        {
            model.put("errorMessage"," Description Length must be greater than or equal to 6 ");
            return "todo";
        }
        todo.setUser(getLoggedInUserName(model));
        System.out.println("Update This is date format: " + todo.getTargetDate());
        repository.save(todo);
//        service.updateTodo(todo);

        return "redirect:/list-todos";
    }
}
