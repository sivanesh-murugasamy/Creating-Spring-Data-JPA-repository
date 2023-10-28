package com.jpa.jpaopr;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    Repo repo;
   
    @GetMapping("/login")
    public String home()
    {
        return "index";
    }
    @GetMapping("/users_home")
    public String homepage(Model model)
    {
        List<User> users=(List<User>)repo.findAll();
        model.addAttribute("users", users);
        return "user";
    }
    @GetMapping("/add_user")
    public String newUser(Model model)
    {
       User user=new User();
       model.addAttribute("user", user);
       return "form";
    }

    @PostMapping("/user_save")
    public String addUser(User user)
    {
        repo.save(user);
        return "redirect:/users_home";
    }

    @GetMapping("update/user{id}")
    public String updateUser(@PathVariable int id,Model model)
    {
      Optional<User> user= repo.findById(id);
      model.addAttribute("user", user.get());
      return "form";
    }
    
    @GetMapping("delete/user{id}")
    public String deleteUser(@PathVariable int id)
    {
        repo.deleteById(id);
        return "redirect:/users_home";
    }

    //interface
    @RequestMapping("/interface")
    public java.lang.String index(){
      return "interface";
    }

     @RequestMapping("/table")
    public java.lang.String table(){
      return "table";
    }

     @RequestMapping("/about")
    public java.lang.String about(){
      return "aboutus";
    }

    
}
